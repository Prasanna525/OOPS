package com.prasanna.vendingMachine;

public class VendingMachineFactory {
	
	public static VendingMachine createVendingMachine() {
		
		return new VendingMachineImpl();
	}
}
