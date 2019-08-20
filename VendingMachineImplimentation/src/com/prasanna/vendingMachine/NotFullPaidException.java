package com.prasanna.vendingMachine;


public class NotFullPaidException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private long remaining;
	
	public NotFullPaidException(String message, long remaining) {
		this.message= message;
		this.remaining=remaining;
	}
	
	@Override
	public String getMessage() {
		return message + remaining;
	}

	public long getRemaining() {
		return remaining;
	}
	
	

}
