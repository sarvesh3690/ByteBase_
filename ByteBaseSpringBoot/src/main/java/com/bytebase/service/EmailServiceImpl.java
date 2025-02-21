package com.bytebase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bytebase.dto.ApiResponse;
import com.bytebase.entities.EmailDetails;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
    
    @Value("${spring.mail.username}")
    private String sender;

//    public EmailServiceImpl() {}
//    // Constructor injection for JavaMailSender
//    public EmailServiceImpl(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
	
	@Override
	public ApiResponse sendMail(EmailDetails details) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			System.out.println("Sender: " + sender);
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());
			
			javaMailSender.send(mailMessage);
			System.out.println("Email sent");
			return new ApiResponse("Email sent too "+details.getRecipient());
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ApiResponse(e.getMessage());
			
		}
		
	}

}
