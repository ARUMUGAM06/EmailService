package com.sixface.emailService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sixface.emailService.model.EmailRequest;
import com.sixface.emailService.service.EmailService;

@RestController
@RequestMapping("api/v1/email/")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("sent")
	public void mailSent(@RequestBody EmailRequest emailRequest) {
		if(emailService.sentEmail(emailRequest)) {
			System.out.println("Email sent successfully");
		}else {
			System.out.println("Email sent Failed!!");
		}
	}
}
