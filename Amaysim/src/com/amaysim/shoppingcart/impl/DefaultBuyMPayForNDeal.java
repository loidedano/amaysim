package com.amaysim.shoppingcart.impl;

import java.util.*;
import com.amaysim.shoppingcart.interfaces.*;

public class DefaultBuyMPayForNDeal implements BuyMPayForNDeal {

	private int buyQty;
	private int payQty;
	private Set<Item> includedItems= new HashSet<Item>();
	
	public DefaultBuyMPayForNDeal(int buyQty, int payQty, Item... items){
		this.buyQty= buyQty;
		this.payQty= payQty;
		includedItems.addAll( Arrays.asList(items));
	}
	
	@Override
	public void setIncludedItems(Set<Item> items) {
		// TODO Auto-generated method stub
		includedItems.removeAll(includedItems);
		List<Item> cloned= new ArrayList<Item>(items);
		includedItems.addAll(cloned);
	}

	@Override
	public Set<Item> getIncludedItems() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableSet(includedItems);
	}

	@Override
	public void setBuyQty(int arg)  throws BadDealException {
		// TODO Auto-generated method stub
		if(buyQty <= payQty) throw new BadDealException("The deal buy " + buyQty + " pay for " + payQty + " is not good");
		buyQty= arg;
	}

	@Override
	public int getBuyQty() {
		// TODO Auto-generated method stub
		return buyQty;
	}

	@Override
	public void setPayForQty(int arg)  throws BadDealException {
		// TODO Auto-generated method stub
		if(payQty >= buyQty) throw new BadDealException("The deal buy " + buyQty + " pay for " + payQty + " is not good");
		payQty= arg;
	}

	@Override
	public int getPayForQty() {
		// TODO Auto-generated method stub
		return payQty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buyQty;
		result = prime * result
				+ ((includedItems == null) ? 0 : includedItems.hashCode());
		result = prime * result + payQty;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultBuyMPayForNDeal other = (DefaultBuyMPayForNDeal) obj;
		if (buyQty != other.buyQty)
			return false;
		if (includedItems == null) {
			if (other.includedItems != null)
				return false;
		} else if (!includedItems.equals(other.includedItems))
			return false;
		if (payQty != other.payQty)
			return false;
		return true;
	}

	@Override
	public void adjustPrice(List<Item> items) {
		// TODO Auto-generated method stub
		Stack<Item> included= new Stack<Item>();
		for(Item item: items) {
			if (this.getIncludedItems().contains(item)) {
				included.add(item);
				if(included.size() % buyQty == 0) {
					int size= included.size();
					while(size > payQty) {
						included.pop().setPrice(0); //free
						size --;
					}
					included.empty();
				}

			}
		}
	}
}
