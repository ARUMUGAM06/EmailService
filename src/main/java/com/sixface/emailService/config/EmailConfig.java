package com.sixface.emailService.config;

import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sixface.emailService.model.EmailRequest;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailConfig {

	@Value("${email.host}")
	private String smtpHost;

	@Value("${email.port}")
	private int smtpPort;

	public Session getSession(String userEmail, String password) throws Exception {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		return Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userEmail, password);
			}
		});
	}

	public MimeMessage prepareMessage(Session session, EmailRequest emailRequest) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailRequest.getFromEmail()));
		
		if(emailRequest.getToEmail()==null || emailRequest.getToEmail().isEmpty()) {
			throw new Exception("To email is empty");
		}
		for(String toemail:emailRequest.getToEmail()) {
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toemail));
			message.setRecipient(Message.RecipientType.CC, new InternetAddress(toemail));
		}
//		message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailRequest.getToEmail()));
//		message.setRecipient(Message.RecipientType.BCC, new InternetAddress(emailRequest.getToEmail()));
		message.setSubject(emailRequest.getSubject());
		message.setText(emailRequest.getBody());
		return message;
	}
}
