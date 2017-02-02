package com.amaysim.shoppingcart.impl;

import java.util.*;

import com.amaysim.shoppingcart.interfaces.*;

public class DefaultPromoCode implements PromoCode {
	private String code;
	private double discount;
	private Set<Item> includedItems= new HashSet<Item>();
	private Set<Item> excludedItems= new HashSet<Item>();

	public DefaultPromoCode(String code, double discount, Item... itemsIncluded){
		this.code= code;
		this.discount= discount;
		this.includedItems.addAll(Arrays.asList(itemsIncluded));
	}
	
	@Override
	public void setCode(String arg) {
		// TODO Auto-generated method stub
		code= arg;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public void setDiscount(double arg) {
		// TODO Auto-generated method stub
		discount= arg;
	}

	@Override
	public double getDiscount() {
		// TODO Auto-generated method stub
		return discount;
	}

	@Override
	public void setExcludedItems(Set<Item> items) {
		// TODO Auto-generated method stub
		List<Item> cloned= new ArrayList<Item>(items);
		excludedItems.removeAll(excludedItems);
		
		excludedItems.addAll(cloned);
		includedItems.removeAll(excludedItems);
	}

	@Override
	public Set<Item> getExcludedItems() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableSet(excludedItems);
	}

	
	@Override
	public void setIncludedItems(Set<Item> items) {
		// TODO Auto-generated method stub
		List<Item> cloned= new ArrayList<Item>(items);
		includedItems.removeAll(includedItems);
		
		includedItems.addAll(cloned);
		excludedItems.removeAll(includedItems);
	}

	@Override
	public Set<Item> getIncludedItems() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableSet(includedItems);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		DefaultPromoCode other = (DefaultPromoCode) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
