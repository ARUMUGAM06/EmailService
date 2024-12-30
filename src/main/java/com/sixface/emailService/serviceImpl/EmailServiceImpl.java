package com.sixface.emailService.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixface.emailService.config.EmailConfig;
import com.sixface.emailService.model.EmailRequest;
import com.sixface.emailService.service.EmailService;

import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailConfig emailConfig;

	@Override
	public boolean sentEmail(EmailRequest emailRequest) {
		try {
			Session session = emailConfig.getSession(emailRequest.getFromEmail(), emailRequest.getPassword());
			MimeMessage message = emailConfig.prepareMessage(session, emailRequest);
			Transport.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
