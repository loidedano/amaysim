package com.amaysim.shoppingcart.interfaces;

import java.util.List;

public interface ShoppingCart {

	public boolean add(Item... item);
	public boolean add(Item item, int qty);
	public boolean add(Item item, PromoCode promoCode);
	public boolean add(List<Item> items, PromoCode promoCode);
	
	public void discardAllItems();
	public void discardAllPromotions();
	
	public boolean addSpecialOffer(BulkDiscount arg);
	public boolean addSpecialOffer(BuyMPayForNDeal arg);
	public boolean addSpecialOffer(FreeAWithEveryBSold arg);
	public boolean addSpecialOffer(PromoCode promo);
	
	public double total();
	public List<Item> items();
}
