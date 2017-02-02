package com.amaysim.shoppingcart.impl;

import java.io.*;
import java.util.*;

import com.amaysim.shoppingcart.interfaces.*;

public final class SpecialOffersFactory {

	public static Properties prop = new Properties();

	static {
		InputStream input = null;

		try {
			input = new FileInputStream("src/resources/specialoffers.properties");
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static PromoCode getDefaultPromoCode() {
		String code= prop.getProperty("defaultPromoCode.code");
		double discount= Double.parseDouble(prop.getProperty("defaultPromoCode.discountRate"));
		return new DefaultPromoCode(code, discount, AllItem.ALL_ITEMS);
	}

	public static PromoCode createNewPromoCode(String code, double discount){
		return new DefaultPromoCode(code, discount);
	}

	public static FreeAWithEveryBSold getDefaultFreeAWithEveryBSold() {
		String includedItemCode= prop.getProperty("defaultFreeAWithEveryBSold.includedItem");
		String freeItemCode= prop.getProperty("defaultFreeAWithEveryBSold.freeItem");
		Item includedItem= ProductFactory.getProduct(includedItemCode);
		Item freeItem= ProductFactory.getProduct(freeItemCode);
		//System.out.println("includedItem=" + includedItem);
		//System.out.println("freeItem=" + freeItem);
		return new DefaultFreeAWithEveryBSold(includedItem, freeItem);
	}
	
	public static FreeAWithEveryBSold createNewFreeAWithEveryBSoldOffer(String prodCodeToBeSold, String prodCodeFree) {
		return new DefaultFreeAWithEveryBSold(
				ProductFactory.getProduct(prodCodeToBeSold),
				ProductFactory.getProduct(prodCodeFree));
	}
	
	public static BuyMPayForNDeal getDefaultBuyMPayForNDeal() {
		int buyQty= Integer.parseInt(prop.getProperty("defaultBuyMPayForNDeal.buyAtLeast"));
		int payQty= Integer.parseInt(prop.getProperty("defaultBuyMPayForNDeal.payForOnly"));
		String item= prop.getProperty("defaultBuyMPayForNDeal.includedItem");
		Item includedItem= ProductFactory.getProduct(item);
		
		return new DefaultBuyMPayForNDeal(buyQty, payQty, includedItem);
	}
	
	public static BuyMPayForNDeal createNewDefaultBuyMPayForNDeal(String prodCode, int buyAmount, int payAmount) {
		return new DefaultBuyMPayForNDeal(buyAmount, payAmount, ProductFactory.getProduct(prodCode));
	}
	
	public static BulkDiscount getDefaultBulkDiscountDeal() {
		int qty= Integer.parseInt(prop.getProperty("defaultBulkDiscount.leastQtyRequired"));
		double discount= Double.parseDouble(prop.getProperty("defaultBulkDiscount.discountRate"));
		String item= prop.getProperty("defaultBulkDiscount.includedItem");
		Item includedItem= ProductFactory.getProduct(item);
		
		return new DefaultBulkDiscount(qty, discount, includedItem);
	}
	
	public static BulkDiscount createNewDefaultBulkDiscountDeal(int qty, double discount, String prodCode) {
		return new DefaultBulkDiscount(qty, discount, ProductFactory.getProduct(prodCode));
	}
	
}
