package hust.account.exception;

import hust.account.enums.EmailError;

/**
 * Created by liangjian on 2017/5/12.
 */
public class AccountEmailException extends RuntimeException {

	private static final long serialVersionUID = -5144319033963911488L;

	EmailError emailError;

	public AccountEmailException(EmailError emailError) {
		this.emailError = emailError;
	}

	public AccountEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailError getEmailError () {
		return emailError;
	}
}
