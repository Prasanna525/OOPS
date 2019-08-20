package com.prasanna.vendingMachine;

import java.util.List;

public interface VendingMachine {
	
	public int selectItemAndGetPrice(Item item);
	public void insertMoney(Money money);
	public List<Money> refund();
	public Dispensor<Item, List<Money>> collectItemAndGetChange();
	public void reset();
	
}
