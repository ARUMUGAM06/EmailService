package com.sixface.emailService.service;

import com.sixface.emailService.model.EmailRequest;

public interface EmailService {
	boolean sentEmail(EmailRequest emailRequest);
}
