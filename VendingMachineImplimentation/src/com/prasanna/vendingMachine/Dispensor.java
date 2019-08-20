package com.prasanna.vendingMachine;

public class Dispensor<T1, T2> {
	
	private T1 item;
	private T2 change;
	
	public Dispensor(T1 item, T2 change) {
		
		this.item = item;
		this.change = change;
	}

	public T1 getItem() {
		return item;
	}

	public T2 getChange() {
		return change;
	}

	@Override
	public String toString() {
		return "Dispensor [Item=" + item + ", Change=" + change + "]";
	}

	
}
