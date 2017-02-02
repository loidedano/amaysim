package com.amaysim.shoppingcart.impl;

import com.amaysim.shoppingcart.interfaces.*;

//Lloyd: acts like an enum that exploits polymorphism and at the same time as registry
public final class AllItem implements Item {
	private final String code="ALL_ITEMS";
	private final String name="ALL_ITEMS";
	
	public static final AllItem ALL_ITEMS= new AllItem();
	
	private AllItem(){
		
	}
	
	@Override
	public void setCode(String arg) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Cannot setCode on ALL_ITEMS");
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public void setName(String arg) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Cannot setName on ALL_ITEMS");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setPrice(double arg) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Cannot setPrice on ALL_ITEMS");
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Cannot getPrice on ALL_ITEMS");
	}

	@Override
	public double getOriginalPrice() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Cannot getOriginalPrice on ALL_ITEMS");
	}
	
	public Item clone() {
		throw new UnsupportedOperationException("Cannot clone ALL_ITEMS");
	}

	@Override
	public String toString() {
		return "AllItem";
	}

	@Override
	public int compareTo(Item o) {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

}
