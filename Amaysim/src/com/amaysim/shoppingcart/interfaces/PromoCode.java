package com.amaysim.shoppingcart.interfaces;

import java.util.*;

public interface PromoCode {
	
	public void setCode(String arg);
	public String getCode();
	
	public void setDiscount(double arg);
	public double getDiscount();
	
	public void setExcludedItems(Set<Item> items);
	public Set<Item> getExcludedItems();
	
	public void setIncludedItems(Set<Item> items);
	public Set<Item> getIncludedItems();
}
