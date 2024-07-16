package com.manager.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Name field is required !!")
    @Size(min = 2, max = 20, message = "Min 2 and max 20 characters are allowed !!")
    private String name;

    private String course;

    @Column(unique = true)
    private String email;

    private String password;
    private String grades;
    private String role;
    private String imageurl;
    private String payment;
    private boolean enrolled;

    @Column(length = 500)
    private String about;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Instructor ins;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Instructor getIns() {
        return ins;
    }

    public void setIns(Instructor ins) {
        this.ins = ins;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", course=" + course + ", email=" + email + ", password="
                + password + ", grades=" + grades + ", role=" + role + ", imageurl=" + imageurl + ", payment=" + payment
                + ", enrolled=" + enrolled + ", about=" + about + ", ins=" + ins + "]";
    }

    public Student(int id,
                   @NotBlank(message = "Name field is required !!") @Size(min = 2, max = 20, message = "Min 2 and max 20 characters are allowed !!") String name,
                   String course, String email, String password, String grades, String role, String imageurl,
                   String payment, boolean enrolled, String about, Instructor ins) {
        super();
        this.id = id;
        this.name = name;
        this.course = course;
        this.email = email;
        this.password = password;
        this.grades = grades;
        this.role = role;
        this.imageurl = imageurl;
        this.payment = payment;
        this.enrolled = enrolled;
        this.about = about;
        this.ins = ins;
    }

    public Student() {
        super();
    }
}
