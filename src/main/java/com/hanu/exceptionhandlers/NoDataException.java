package com.hanu.exceptionhandlers;


public class NoDataException extends Exception {
	String msg;

	public NoDataException(String msg) {
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
