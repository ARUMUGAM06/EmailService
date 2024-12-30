package com.sixface.emailService.model;

import java.util.List;

import lombok.Data;

@Data
public class EmailRequest {
	private String fromEmail;
	private List<String> toEmail;
	private String subject;
	private String body;
	private String password;
}
