package com.prasanna.vendingMachine;

public enum Item {
	
	COKE("Coke",25), THUMSUP("ThumsUp",35), SODA("Soda",55), WATER("Water",20), REDBULL("RedBull",100);
	
	private String name;
	private int price;
	
	private Item(String name, int price) {
		this.name=name;
		this.price=price;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	
}
