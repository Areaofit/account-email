package com.areaofit.mvnbook.account.email;

import static org.junit.Assert.assertEquals;

import javax.mail.Message;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

/**
 * 
 * @Description 测试发送邮件服务
 * @Author Huangjinwen
 * @Date 2017年11月20日-下午3:01:19
 */
public class AccountEmailServiceTest {
		
	private GreenMail greenMail;
	
	/**
	 * 启动邮件服务
	 * @throws Exception
	 */
	@Before
	public void startMailServer() throws Exception {
		greenMail = new GreenMail(ServerSetup.SMTP);
		greenMail.setUser("hjwhugh@163.com", "Love0327");
		greenMail.start();
	}
	
	/**
	 * 测试发邮件接口
	 * @throws Exception
	 */
	@Test
	public void testSendMail() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("account-email.xml");
		AccountEmailService accountEmailService = (AccountEmailService) applicationContext.getBean("accountEmailService");
		String subject = "Test Subject";
		String htmlText = "<h3>Test1</h3>";
		accountEmailService.sendMail("areaofit@qq.com", subject, htmlText);
		System.out.println(greenMail.waitForIncomingEmail(2000,1));
		Message[] msgs = greenMail.getReceivedMessages();
		assertEquals(1, msgs.length);
		assertEquals(subject, msgs[0].getSubject());
		assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
	}
	
	/**
	 * 关闭邮件服务
	 * @throws Exception
	 */
	@After
	public void stopMailServer() throws Exception {
		greenMail.stop();
	}
}
