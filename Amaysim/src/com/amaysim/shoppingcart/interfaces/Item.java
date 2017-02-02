package com.amaysim.shoppingcart.interfaces;

public interface Item extends Comparable<Item> {
	public void setCode(String arg);
	public String getCode();
	
	public void setName(String arg);
	public String getName();
	
	public void setPrice(double arg);
	public double getPrice();
	
	public double getOriginalPrice();
	
	public Item clone();
}
