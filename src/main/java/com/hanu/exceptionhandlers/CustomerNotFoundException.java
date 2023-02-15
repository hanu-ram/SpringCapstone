package com.hanu.exceptionhandlers;

public class CustomerNotFoundException extends Exception {
	String msg;

	public CustomerNotFoundException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
