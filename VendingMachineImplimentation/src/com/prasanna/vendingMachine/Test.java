package com.prasanna.vendingMachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		VendingMachineImpl vm = new VendingMachineImpl();
		
	
		int price = vm.selectItemAndGetPrice(Item.SODA);
		
		assertEquals(Item.SODA.getPrice(),price);
		
		vm.insertMoney(Money.FIFTY);
		vm.insertMoney(Money.TWENTY);
		
		Dispensor<Item, List<Money>> dispensor = vm.collectItemAndGetChange();

		
		Item item = dispensor.getItem();
		List<Money> change = dispensor.getChange();
		
		assertEquals(Item.SODA, item);
		
		assertTrue(!change.isEmpty());
		
		assertEquals(70 - Item.SODA.getPrice(), getTotal(change));
		
	}

	private static int getTotal(List<Money> change) {
		int total=0;
		
		for(Money m: change) {
			total = total+ m.getValue();
		}
		return total;
	}	

}
