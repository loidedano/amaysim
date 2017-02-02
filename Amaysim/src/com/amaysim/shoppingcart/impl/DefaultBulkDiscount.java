package com.amaysim.shoppingcart.impl;

import java.util.*;

import com.amaysim.shoppingcart.interfaces.*;

public class DefaultBulkDiscount implements BulkDiscount {
	private int qty;
	private double discount;
	private Set<Item> includedItems= new HashSet<Item>();
	
	public DefaultBulkDiscount(int qty, double discount, Item... items) {
		this.qty= qty;
		this.discount= discount;
		includedItems.addAll(Arrays.asList(items));
	}
	
	@Override
	public int getQty() {
		// TODO Auto-generated method stub
		return qty;
	}

	@Override
	public void setQty(int arg) {
		// TODO Auto-generated method stub
		qty= arg;
	}

	@Override
	public double getDiscount() {
		// TODO Auto-generated method stub
		return discount;
	}

	@Override
	public void setDiscount(double arg) {
		// TODO Auto-generated method stub
		discount= arg;
	}

	@Override
	public Set<Item> getIncludedItems() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableSet(includedItems);
	}

	@Override
	public void setIncludedItems(Set<Item> arg) {
		// TODO Auto-generated method stub
		includedItems.removeAll(includedItems);
		List<Item> cloned= new ArrayList<Item>(arg);
		includedItems.addAll(cloned);
	}

	@Override
	public double getBulkPriceDiscount(List<Item> items) {
		// TODO Auto-generated method stub
		//double nonDiscounted= 0;
		double toBeDiscounted= 0;
		double discount= 0;
		double includedItemCount= 0;
		for(Item item: items) {
			if(this.includedItems.contains(item)){
				//System.out.println("includedItems for bulk discount=" + item);
				includedItemCount ++;
				toBeDiscounted += item.getPrice();
			}/*else{
				
				nonDiscounted += item.getOriginalPrice();
				System.out.println("nonDiscounted for bulk discount=" + nonDiscounted);
			}*/
		}
		//System.out.println("includedItemCount=" + includedItemCount);
		//System.out.println("this.getQty()=" + this.getQty());
		if(includedItemCount > this.getQty()){
			//System.out.println("toBeDiscounted=" + toBeDiscounted);
			discount= toBeDiscounted * this.getDiscount();
		}
		//System.out.println("nonDiscounted=" + nonDiscounted + ", discounted=" + discount);
		return discount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + qty;
		long temp;
		temp = Double.doubleToLongBits(discount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((includedItems == null) ? 0 : includedItems.hashCode());
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
		DefaultBulkDiscount other = (DefaultBulkDiscount) obj;
		if (qty != other.qty)
			return false;
		if (Double.doubleToLongBits(discount) != Double
				.doubleToLongBits(other.discount))
			return false;
		if (includedItems == null) {
			if (other.includedItems != null)
				return false;
		} else if (!includedItems.equals(other.includedItems))
			return false;
		return true;
	}

}
