package com.eComm.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.eComm.user.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	
	  @Autowired
	    private JavaMailSender mailSender;

	@Override
	public void sendRegistrationMail(String reciever, String name) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(reciever);
		message.setSubject("Welcome to Ecomm");
		message.setText("Helloo " + name + " "+"Your Registration Succesfull");
		mailSender.send(message);
		
		
		
	}

}
