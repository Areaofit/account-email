package com.areaofit.mvnbook.account.email.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.areaofit.mvnbook.account.email.AccountEmailService;

public class AccountEmailServiceImpl implements AccountEmailService {
	
	private JavaMailSender javaMailSender;
	
	private String systemEmail;

	public void sendMail(String to, String subject, String htmlText) {
		try {
			// 创建一个邮件对象
			MimeMessage msg = javaMailSender.createMimeMessage();
			// 创建邮件助手
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg);
			msgHelper.setFrom(systemEmail);
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
			msgHelper.setText(htmlText,true);
			javaMailSender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}
	
	

}
