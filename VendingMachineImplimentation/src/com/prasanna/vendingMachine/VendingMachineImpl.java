package com.prasanna.vendingMachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {
	
	private Inventory<Money> cashInventory = new Inventory<Money>();
	private Inventory<Item> itemInventory = new Inventory<Item>();
	
	private int totalSales;
	private Item currentItem;
	private int moneyInserted;
	
	public VendingMachineImpl() {
		initialize();
	}

	private void initialize() {
		
		for(Money m:Money.values()) {
			cashInventory.put(m,5);
		}
		
		for(Item i:Item.values()) {
			itemInventory.put(i,5);
		}
		
	}

	@Override
	public int selectItemAndGetPrice(Item item) {
		
		if(itemInventory.hasItem(item)) {
			currentItem = item;
			return currentItem.getPrice();
		}
		throw new SoldOutException("Sold out, please buy other Item.");
	}

	@Override
	public void insertMoney(Money money) {
		
		moneyInserted = moneyInserted+ money.getValue();
		cashInventory.add(money);

	}

	@Override
	public Dispensor<Item, List<Money>> collectItemAndGetChange() {
		
		Item item = collectItem();
		
		totalSales = totalSales+ item.getPrice();
		
		List<Money> change = collectChange();
		
		return new Dispensor<Item, List<Money>>(item, change);
	}

	private Item collectItem() throws NotFullPaidException, NotSufficientChangeException{
		
		if(isFullPaid()) {
			if(hasSufficientChange()) {
				itemInventory.deduct(currentItem);
				return currentItem;
			}
			else {
				throw new NotSufficientChangeException("Not sufficient change in Inventory");
			}	
		}
		long remainingBalance = currentItem.getPrice()-moneyInserted;
		
		throw new NotFullPaidException("Price not full paid, pay remaining : ",remainingBalance);
	}

	private boolean isFullPaid() {
		
		if(moneyInserted >=currentItem.getPrice()) {
			return true;
		}
		return false;
	}
	
	private boolean hasSufficientChange() {
		
		return hasSufficientChangeForAmount(moneyInserted-currentItem.getPrice());
	}
	
	private boolean hasSufficientChangeForAmount(int amount) {
		
		boolean hasChange = true;
		try {
			getChange(amount);
		}
		catch(NotSufficientChangeException nsce){
			return hasChange= false;
		}
		return hasChange;
	}

	private List<Money> getChange(int amount) throws NotSufficientChangeException {
		
		@SuppressWarnings("unchecked")
		List<Money> changes= Collections.EMPTY_LIST;
		
		if(amount>0) {
			changes = new ArrayList<Money>();
			
			long balance = amount;
			
			while(balance>0) {
				if(balance>=Money.HUNDRED.getValue() && cashInventory.hasItem(Money.HUNDRED)) {
					changes.add(Money.HUNDRED);
					balance = balance - Money.HUNDRED.getValue();
				}
				else if(balance>=Money.FIFTY.getValue() && cashInventory.hasItem(Money.FIFTY)) {
					changes.add(Money.FIFTY);
					balance = balance - Money.FIFTY.getValue();
					continue;
				}
				else if(balance>=Money.TWENTY.getValue() && cashInventory.hasItem(Money.TWENTY)) {
					changes.add(Money.TWENTY);
					balance = balance - Money.TWENTY.getValue();
					continue;
				}
				else if(balance>=Money.TEN.getValue() && cashInventory.hasItem(Money.TEN)) {
					changes.add(Money.TEN);
					balance = balance - Money.TEN.getValue();
					continue;
				}
				else if(balance>=Money.FIVE.getValue() && cashInventory.hasItem(Money.FIVE)) {
					changes.add(Money.FIVE);
					balance = balance - Money.FIVE.getValue();
					continue;
				}
				else {
					throw new NotSufficientChangeException("Not sufficient change, please try other item");
				}
			}
		}
		return changes;		
		
	}

	private List<Money> collectChange() {
		
		int changeAmount = moneyInserted - currentItem.getPrice();
		
		List<Money> change = getChange(changeAmount);
		
		updateCashInventory(change);
		moneyInserted=0;
		currentItem = null;
		return change;
	}

	
	private void updateCashInventory(List<Money> change) {
		
		for(Money m: change) {
			cashInventory.deduct(m);
		}
		
	}

	@Override
	public List<Money> refund() {
		
		List<Money> refund = getChange(moneyInserted);
		updateCashInventory(refund);
		moneyInserted=0;
		currentItem = null;
		return refund;
	}
	
	@Override
	public void reset() {
		
		cashInventory.clear();
		itemInventory.clear();
		totalSales = 0;
		currentItem = null;
		moneyInserted = 0;
	}
	
	public long getTotalSales() {
		return totalSales;
	}
	
	public void printStats() {
		
		System.out.println("Total sales: "+ totalSales);
		System.out.println("Current Item Inventory: "+ itemInventory);
		System.out.println("Current Cash Inventory: "+ cashInventory);
	}

}
