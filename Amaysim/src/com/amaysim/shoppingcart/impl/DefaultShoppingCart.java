package com.amaysim.shoppingcart.impl;

import java.math.*;
import java.util.*;

import com.amaysim.shoppingcart.interfaces.*;

public class DefaultShoppingCart implements ShoppingCart {

	private List<Item> items= new ArrayList<Item>();
	
	private Set<BuyMPayForNDeal> buyMPayForNDeals= new HashSet<BuyMPayForNDeal>();
	private Set<BulkDiscount> bulkDiscounts= new HashSet<BulkDiscount>();
	private Set<FreeAWithEveryBSold> freebies= new HashSet<FreeAWithEveryBSold>(); 
	private Set<PromoCode> promoCodes= new HashSet<PromoCode>();
	
	@Override
	public boolean add(Item item, int qty) {
		// TODO Auto-generated method stub
		for(int i=0; i< qty; i++){
			items.add(item);
		}
		return true;
	}
	
	@Override
	public boolean add(Item... item) {
		// TODO Auto-generated method stub
		return items.addAll(Arrays.asList(item));
	}

	@Override
	public boolean add(Item item, PromoCode promoCode) {
		// TODO Auto-generated method stub
		promoCodes.add(promoCode);
		return items.add(item);
	}
	
	@Override
	public boolean add(List<Item> items, PromoCode promoCode) {
		// TODO Auto-generated method stub
		promoCodes.add(promoCode);
		return items.addAll(items);
	}
	

	@Override
	public boolean addSpecialOffer(PromoCode promo) {
		// TODO Auto-generated method stub
		return promoCodes.add(promo);
	}
	
	@Override
	public boolean addSpecialOffer(BulkDiscount arg) {
		// TODO Auto-generated method stub
		return bulkDiscounts.add(arg);
	}

	@Override
	public boolean addSpecialOffer(BuyMPayForNDeal arg) {
		// TODO Auto-generated method stub
		return buyMPayForNDeals.add(arg);
	}

	@Override
	public boolean addSpecialOffer(FreeAWithEveryBSold arg) {
		// TODO Auto-generated method stub
		return freebies.add(arg);
	}

	@Override
	public double total() {
		// TODO Auto-generated method stub

		for(BuyMPayForNDeal deal: buyMPayForNDeals){
			deal.adjustPrice(items); //side effect on items
		}
		
		double bulkDiscount=0;
		for(BulkDiscount bulk: bulkDiscounts){
			bulkDiscount += bulk.getBulkPriceDiscount(items);
		}
		
		boolean discountAll= false;
		double discountAllRate= 0;
		Map<Item, Double> discountedItems= new HashMap<Item, Double>();
		for(PromoCode promo: promoCodes) {
			for(Item item: promo.getIncludedItems()){
				discountedItems.put(item, promo.getDiscount());
			}

			if (discountedItems.containsKey(AllItem.ALL_ITEMS)){
				discountAll= true;
				discountAllRate= promo.getDiscount();
				break;
			}
		}
		
		double total=0;
		for(Item item: items) {
			double discountedItemPrice= 0;
			if (discountAll == true) {
				discountedItemPrice= item.getPrice() - (item.getPrice() * discountAllRate);
			} else {
				if (discountedItems.containsKey(item)){
					discountedItemPrice= item.getPrice() - (item.getPrice() * discountedItems.get(item));
				}else{ //no discount for this item
					discountedItemPrice= item.getPrice();
				}
			}
			total += discountedItemPrice;
		}
		//System.out.println("total=" + total);
		//System.out.println("bulkDiscount=" + bulkDiscount);
		BigDecimal bd = new BigDecimal(total - bulkDiscount);
	    bd = bd.setScale(2, RoundingMode.HALF_EVEN);
	    return bd.doubleValue();
	}

	@Override
	public List<Item> items() {
		// TODO Auto-generated method stub
		
		List<Item> allItems= new ArrayList<Item>();

		allItems.addAll(items);
		for(FreeAWithEveryBSold freebie: freebies){
			allItems.addAll( freebie.getFreeItems(items) );
		}
		
		return allItems;
	}

	@Override
	public void discardAllItems() {
		// TODO Auto-generated method stub
		items.clear();
	}

	@Override
	public void discardAllPromotions() {
		// TODO Auto-generated method stub
		buyMPayForNDeals.clear();
		bulkDiscounts.clear();
		freebies.clear(); 
		promoCodes.clear();
	}

	


}
