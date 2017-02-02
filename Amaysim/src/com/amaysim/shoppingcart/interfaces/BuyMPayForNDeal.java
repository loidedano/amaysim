package com.amaysim.shoppingcart.interfaces;

import java.util.*;

//Lloyd: where N is preferably less than M
public interface BuyMPayForNDeal {
	public void setIncludedItems(Set<Item> items);
	public Set<Item> getIncludedItems();
	
	public void setBuyQty(int arg) throws BadDealException;
	public int getBuyQty();
	
	public void setPayForQty(int arg) throws BadDealException;
	public int getPayForQty();
	
	public void adjustPrice(List<Item> items);
}
