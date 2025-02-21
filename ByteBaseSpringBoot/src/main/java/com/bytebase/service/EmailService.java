package com.bytebase.service;

import com.bytebase.dto.ApiResponse;
import com.bytebase.entities.EmailDetails;


public interface EmailService {
	ApiResponse sendMail(EmailDetails details);
}
