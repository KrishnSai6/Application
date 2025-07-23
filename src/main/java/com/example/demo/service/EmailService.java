package com.example.demo.service;
/*
types of email
plain -> text mail
html page -> template with images
for the send mail method we need the from mail and to mail
*/

//import spring mailer in the autowired

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	public JavaMailSender javaMailSender;
	
	@Autowired
	public TemplateEngine templateEngine;

	public void sendPlainEmail(String fromEmail, String toEmail, String subject, String mainBody) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(mainBody);

		javaMailSender.send(message);

	}

	public void sendHtmlEmail(String fromEmail, String toEmail, String subject, String mainBody) throws Exception {

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
		
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject(subject);
		helper.setText(mainBody,true);
		javaMailSender.send(message);
	}
	
	public void sendTempleteEmail(String fromEmail, String toEmail, String subject, String filename) throws Exception {
		
		
		
		
     	Context context = new Context();
		
		context.setVariable("name", "krishna");
		
		String mainBody = templateEngine.process(filename, context);
		
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
		
		helper.setFrom(fromEmail);
		helper.setTo(toEmail);
		helper.setSubject(subject);
		helper.setText(mainBody,true);
		javaMailSender.send(message);
		
	}
}
