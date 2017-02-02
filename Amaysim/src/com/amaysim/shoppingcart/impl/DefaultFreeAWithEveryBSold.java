package com.amaysim.shoppingcart.impl;

import java.util.*;

import com.amaysim.shoppingcart.interfaces.*;

public class DefaultFreeAWithEveryBSold implements FreeAWithEveryBSold {
	private Set<Item> includedItems= new HashSet<Item>();
	private int requiredAmount=1;
	private List<Item> freebies= new ArrayList<Item>();
	
	public DefaultFreeAWithEveryBSold(Item productToBeSold, Item free){
		includedItems.add( productToBeSold );
		freebies.add(free);
	}
	
	@Override
	public void setIncludedItems(Set<Item> items) {
		// TODO Auto-generated method stub
		List<Item> cloned= new ArrayList<Item>(items);
		includedItems.removeAll(includedItems);
		includedItems.addAll( cloned );
	}

	@Override
	public Set<Item> getIncludedItems() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableSet(includedItems);
	}

	@Override
	public void setRequiredAmount(int arg) {
		// TODO Auto-generated method stub
		requiredAmount=arg;
	}

	@Override
	public int getRequiredAmount() {
		// TODO Auto-generated method stub
		return requiredAmount;
	}

	@Override
	public List<Item> getIncludedFreeItems() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableList(freebies);
	}

	@Override
	public void setIncludedFreeItems(List<Item> arg) {
		// TODO Auto-generated method stub
		freebies.removeAll(freebies);
		List<Item> cloned= new ArrayList<Item>(arg);
		freebies.addAll(cloned);
	}

	@Override
	public List<Item> getFreeItems(List<Item> boughtItems) {
		// TODO Auto-generated method stub
		List<Item> freeItems= new ArrayList<Item>();
		for(Item item: boughtItems) {
			if(this.includedItems.contains(item)){
				freeItems.addAll(freebies);
			}
		}
		return freeItems;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((freebies == null) ? 0 : freebies.hashCode());
		result = prime * result
				+ ((includedItems == null) ? 0 : includedItems.hashCode());
		result = prime * result + requiredAmount;
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
		DefaultFreeAWithEveryBSold other = (DefaultFreeAWithEveryBSold) obj;
		if (freebies == null) {
			if (other.freebies != null)
				return false;
		} else if (!freebies.equals(other.freebies))
			return false;
		if (includedItems == null) {
			if (other.includedItems != null)
				return false;
		} else if (!includedItems.equals(other.includedItems))
			return false;
		if (requiredAmount != other.requiredAmount)
			return false;
		return true;
	}

}
