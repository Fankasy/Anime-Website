package com.shaofan.spring.exception;

public class WholeException extends Exception {

	public WholeException(String message)
	{
		super("UserException-"+message);
	}
	
	public WholeException(String message, Throwable cause)
	{
		super("UserException-"+message,cause);
	}
	
}