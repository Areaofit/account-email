package com.areaofit.mvnbook.account.email;

public interface AccountEmailService {

	public void sendMail(String to, String subject, String htmlText);

}
