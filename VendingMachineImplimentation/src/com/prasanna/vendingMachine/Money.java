package com.prasanna.vendingMachine;

public enum Money {
	
	FIVE(5), TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100);
	
	private int value;
	
	private Money(int value) {
		
		this.value=value;
	}
	
	public int getValue() {
		return value;
	}
}
