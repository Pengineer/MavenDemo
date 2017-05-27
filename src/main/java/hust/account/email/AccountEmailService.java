package hust.account.email;

import hust.account.exception.AccountEmailException;

/**
 * Created by liangjian on 2017/5/12.
 */
public interface AccountEmailService {
	public void sendMail(String to, String subject, String htmlText) throws AccountEmailException;
}
