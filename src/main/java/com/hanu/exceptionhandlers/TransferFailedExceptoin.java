package com.hanu.exceptionhandlers;

public class TransferFailedExceptoin extends Exception {
	String msg;

	

	public TransferFailedExceptoin(String msg) {
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
