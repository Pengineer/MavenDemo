package hust.account.email;

import static junit.framework.Assert.assertEquals;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Message;

/**
 * Created by liangjian on 2017/5/14.
 */
public class AccountEmailServiceTest {
	private GreenMail greenMail;

	@Before
	public void startMailServer() {
		greenMail = new GreenMail(ServerSetup.SMTP);
		greenMail.setUser("test@pengineer.com", "123456");  // 创建邮件服务器账号密码
		greenMail.start();
	}

	@Test
	public void testSendMail() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
		AccountEmailService accountEmailService = (AccountEmailService)ctx.getBean("accountEmailServiceImpl");

		String to = "test2@pengineer.com";
		String subject = "Test Subject";
		String htmlText = "<h1>Just for test</h1>";

		accountEmailService.sendMail(to, subject, htmlText);

		greenMail.waitForIncomingEmail(2000, 1);

		Message[] msgs = greenMail.getReceivedMessages();
		assertEquals(1, msgs.length);
		assertEquals(subject, msgs[0].getSubject());
		assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
	}

	@After
	public void stopMailServer() {
		greenMail.stop();
	}
}
