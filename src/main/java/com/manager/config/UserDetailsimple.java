package com.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.manager.dao.*;
import com.manager.entities.*;

public class UserDetailsimple implements UserDetailsService
{

	@Autowired
	private UserRepo userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//fetch instructor from database
		 Instructor instructor =userRepository.getInstructorByUserName(username);
		if(instructor==null)
		{
			throw new UsernameNotFoundException("instructor could not be found !!");
		}
		OurDetails ourDetails=new OurDetails(instructor);
		
		return ourDetails;
	}

}