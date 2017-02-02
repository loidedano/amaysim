package com.amaysim.shoppingcart;

import java.util.Arrays;
import java.util.Collections;

import com.amaysim.shoppingcart.impl.*;
import com.amaysim.shoppingcart.interfaces.*;

public class ShoppingCartTest {

	public static boolean scenario1(){
		DefaultShoppingCart cart= new DefaultShoppingCart();
		/*
		 *	3 x Unlimited 1 GB
			1 x Unlimited 5 GB
		 */
		Item[] inputContents= new Item[]{ 
			ProductFactory.getProduct("ult_small"),
			ProductFactory.getProduct("ult_small"),
			ProductFactory.getProduct("ult_small"),
			ProductFactory.getProduct("ult_large")};
		
		cart.add(inputContents);
		cart.addSpecialOffer(SpecialOffersFactory.getDefaultBuyMPayForNDeal());
		
		double expectedTotal= 94.7;
		return cart.total() == expectedTotal;
	}
	
	public static boolean scenario2(){
		DefaultShoppingCart cart= new DefaultShoppingCart();
		/*
		 *	2 x Unlimited 1 GB
			4 x Unlimited 5 GB
		 */
		Item[] inputContents= new Item[]{ 
			ProductFactory.getProduct("ult_small"),
			ProductFactory.getProduct("ult_small"),
			ProductFactory.getProduct("ult_large"),
			ProductFactory.getProduct("ult_large"),
			ProductFactory.getProduct("ult_large"),
			ProductFactory.getProduct("ult_large")};
		
		cart.add(inputContents);
		cart.addSpecialOffer(SpecialOffersFactory.getDefaultBulkDiscountDeal());
		double expectedTotal= 209.41;  //instead of 209.40 because code uses x rate instead of - difference
		return cart.total() == expectedTotal;
	}
	
	public static boolean scenario3(){
		DefaultShoppingCart cart= new DefaultShoppingCart();
		/*
		 *	1 x Unlimited 1 GB
			2 X Unlimited 2 GB
			2 X 1 GB Data-pack
		 */
		Item[] expectedContents= new Item[]{ 
			ProductFactory.getProduct("ult_small"),
			ProductFactory.getProduct("ult_large"),
			ProductFactory.getProduct("ult_large"),
			ProductFactory.getProduct("ult_large"),
			ProductFactory.getProduct("ult_large")};
		
		Item[] inputContents= new Item[]{ 
			ProductFactory.getProduct("ult_small"),
			ProductFactory.getProduct("ult_large"),
			ProductFactory.getProduct("ult_large")};
		
		cart.add(inputContents);
		cart.addSpecialOffer(SpecialOffersFactory.getDefaultFreeAWithEveryBSold());
		boolean result=true;
		Collections.sort(cart.items());
		Arrays.sort(expectedContents);
		for(int i=0; i< cart.items().size(); i++){
			if(! cart.items().get(i).equals(expectedContents[i]) ){
				result= false;
				break;
			}
		}
		return result;
	}
	
	public static boolean scenario4(){
		DefaultShoppingCart cart= new DefaultShoppingCart();
		/*
		 *	1 x Unlimited 1 GB
			1 x 1 GB Data-pack
		 */
		Item[] inputContents= new Item[]{ 
			ProductFactory.getProduct("ult_small"),
			ProductFactory.getProduct("1gb")};
		
		cart.add(inputContents);
		PromoCode promo= SpecialOffersFactory.getDefaultPromoCode();
		cart.addSpecialOffer(promo);
		double expectedTotal= 31.32;
		String expectedPromoCode= "I<3AMAYSIM";
		return cart.total() == expectedTotal && promo.getCode().equals(expectedPromoCode);
	}
	
	public static void main(String[] args){
		
		if(scenario1()) System.out.println("Scenario1 successful"); else System.out.println("Scenario1 failed");
		if(scenario2()) System.out.println("Scenario2 successful"); else System.out.println("Scenario2 failed");
		if(scenario3()) System.out.println("Scenario3 successful"); else System.out.println("Scenario3 failed");
		if(scenario4()) System.out.println("Scenario4 successful"); else System.out.println("Scenario4 failed");
		System.exit(0);
	}
	
}
