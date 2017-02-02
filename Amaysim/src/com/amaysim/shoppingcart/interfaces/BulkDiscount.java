package com.amaysim.shoppingcart.interfaces;

import java.util.*;

public interface BulkDiscount {

	public int getQty();
	public void setQty(int arg);
	
	public double getDiscount();
	public void setDiscount(double arg);
	
	public Set<Item> getIncludedItems();
	public void setIncludedItems(Set<Item> arg);
	
	public double getBulkPriceDiscount(List<Item> items);
}
