package com.aptech.demo.Services;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	private final TemplateEngine templateEngine;

	public MailSenderService(JavaMailSender mailSender, TemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}

	public void sendEmailHTML(String to, String subject, Map<String, Object> model, String templateName) {
		
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);

			String content = templateEngine.process(templateName, new Context(Locale.getDefault(), model));
			helper.setText(content, true);

			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	public void sendEmailContact(String email, String name,  String message) {
		Map<String, Object> model = new HashMap<>();
		model.put("name", name);
		model.put("email", email);
		model.put("message", message);

		sendEmailHTML("vanchien0830@gmail.com", "Quote", model, "email-contact");
	}
}

