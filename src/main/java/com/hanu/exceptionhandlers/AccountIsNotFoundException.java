package com.hanu.exceptionhandlers;

public class AccountIsNotFoundException extends Exception {
	String msg;

	public AccountIsNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	// public void test() {

	// }

}
