package com.manager.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.manager.dao.StudentRepo;
import com.manager.dao.UserRepo;
import com.manager.entities.Instructor;
import com.manager.entities.Student;
import com.manager.utility.Messages;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	@Autowired
	public StudentRepo studentRepo;
	@Autowired
	public UserRepo userRepo;
	// to add a data 
	
	
	@ModelAttribute
	public void addcommonStuData(Model model, Principal principal) {
		String userNameString = principal.getName();
		System.out.println("the username or stunamed "+userNameString);
		
		Instructor instructor = userRepo.getInstructorByUserName(userNameString);
		System.out.println("Instructor:"+instructor);
		model.addAttribute("instructor" ,instructor);
		
		
	}
	
	@GetMapping("/add-student")
	public String NewStudentForm(Model model) {
		model.addAttribute("title", "New Student");
		model.addAttribute("student", new Student());
		
		return "about/add_student_form";
	}
	
	@RequestMapping("/home")
	public String Opendashboard(Model model, Principal principal) {
		model.addAttribute("title","Instructor Dashboard");
		
		return "Instructor/instructor_dashboard";
		
		
	}
	@RequestMapping("/{sid}/student")
	public String showStudentDetails(@PathVariable("id") Integer id,Model model,
			Principal principal)
	{
		Optional<Student> studentOptional = this.studentRepo.findById(id);
		Student student = studentOptional.get();
		 String nameString = principal.getName();
		 Instructor instructor = this.userRepo.getInstructorByUserName(nameString);
		 if(instructor.getId() == student.getIns().getId()) {
			 model.addAttribute("student" , student);
			 model.addAttribute("title ", student.getName());
			 }
	return "/Instructor/student_detail";
	}
	
	
	@RequestMapping("/show-students/{page}")
	public String showStudents(@PathVariable("page") Integer page,Model model,
			Principal principal)
	{
		model.addAttribute("title", "all details of Student");
		String nameString = principal.getName();
		Instructor instructor = this.userRepo.getInstructorByUserName(nameString);
		// pages of Students
		Pageable pageable = PageRequest.of(page, 3);
		
		Page<Student> studentsPage = this.studentRepo.findStudentByInstructor(instructor.getId(),pageable);
		model.addAttribute("students", studentsPage);
		model.addAttribute("initial page", page);
		model.addAttribute("total pages", studentsPage.getTotalPages());
		
		
		
		
	return "/Instructor/show_students";
	}
	
	
	@RequestMapping("/{sid}/student")
	public String deleteStudent(@PathVariable("sid") Integer id, Model model ,
			Principal principal, HttpSession session) {
		// this method defi.........
		Optional<Student> studentOptional = this.studentRepo.findById(id);
		Student student = studentOptional.get();
		Instructor instructor = this.userRepo.getInstructorByUserName(principal.getName());
		instructor.getStu().remove(student);
		
		// after deletion 
		this.userRepo.save(instructor);
		// give a message 
		session.setAttribute("message", new Messages("The student has been deleted","success"));
		
		
		//Student student = th
		
		return "redirect:/Instructor/show_students/0";
	}
	
	@PostMapping("/update-student/{sid}")
	public String UpdateStudent(@PathVariable("sid") Integer id,Model model) {
		model.addAttribute("title","Update the Student");
		this.studentRepo.findById(id);
		Student student = this.studentRepo.findById(id).get();
		model.addAttribute("student",student);
		
		
		return"Instructor/update_form";
	}
	// proccessing the update 
	@RequestMapping(value="/process-update", method = RequestMethod.POST)
	public String updateDetail(@ModelAttribute Student student, Model model,@RequestParam("imageUrl") MultipartFile file, HttpSession session , Principal principal) {
		try {
			// previous details 
			
			Student prevdetailStudent = this.studentRepo.findById(student.getId()).get();
			if (!file.isEmpty()) {
				// 
				File deletFile =  new ClassPathResource("static/img").getFile();
				File filedelFile = new File(deletFile, prevdetailStudent.getImageurl());
				filedelFile.delete();
				// 
				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
				
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				student.setImageurl(file.getOriginalFilename());
				
			
			}else {
				student.setImageurl(prevdetailStudent.getImageurl());
			}
			Instructor instructor = this.userRepo.getInstructorByUserName(principal.getName());
			student.setIns(instructor);
			this.studentRepo.save(student);
			session.setAttribute("message", new Messages("New student has been updated!!"));
			
			
			
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return "redirect:/instructor/"+student.getId()+"/student";
	}
	//for the profile 
	@GetMapping("/profile")
	public String profile(Model model) {
		model.addAttribute("title", "Profile page");
		return"Instructor/profile";
	}

}