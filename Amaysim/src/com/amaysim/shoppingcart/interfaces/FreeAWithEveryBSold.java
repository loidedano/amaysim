package com.amaysim.shoppingcart.interfaces;

import java.util.*;

public interface FreeAWithEveryBSold {	
	public void setIncludedItems(Set<Item> items);
	public Set<Item> getIncludedItems();
	
	public void setRequiredAmount(int arg);
	public int getRequiredAmount();
	
	public List<Item> getIncludedFreeItems();
	public void setIncludedFreeItems(List<Item> arg);
	
	public List<Item> getFreeItems(List<Item> boughtItems);
}
