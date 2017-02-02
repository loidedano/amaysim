package com.amaysim.shoppingcart.impl;

import com.amaysim.shoppingcart.interfaces.Item;

public class NonPersistentProduct implements Item {
	private String code;
	private String name;
	private double price;
	private final double origPrice;
	
	public NonPersistentProduct(String code, String name, double price) {
		this.code=code;
		this.name=name;
		this.price=price;
		this.origPrice=price;
	}
	
	@Override
	public void setCode(String arg) {
		// TODO Auto-generated method stub
		code=arg;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public void setName(String arg) {
		// TODO Auto-generated method stub
		name=arg;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setPrice(double arg) {
		// TODO Auto-generated method stub
		price=arg;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return price;
	}
	
	@Override
	public double getOriginalPrice() {
		// TODO Auto-generated method stub
		return origPrice;
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
		NonPersistentProduct other = (NonPersistentProduct) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public Item clone() {
		return new NonPersistentProduct(this.code, this.name, this.price); 
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name
				+ ", price=" + price + "]";
	}

	@Override
	public int compareTo(Item o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.getName());
	}

	
	
}
