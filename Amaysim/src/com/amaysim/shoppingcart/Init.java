package com.amaysim.shoppingcart;

import com.amaysim.shoppingcart.impl.*;
import com.amaysim.shoppingcart.interfaces.*;
import java.util.*;

public class Init {
	
	
	public static void drawBorder(){
		System.out.println("****************************************************");
	}
	
	public static void goBackOption(){
		System.out.println("Press 0 to go back to main menu...");
	}
	
	public static void showSpecialOfferConfig(){
		drawBorder();
		System.out.println("showing settings for special offers...");
		for(Map.Entry<Object,Object> prop: SpecialOffersFactory.prop.entrySet()) {
			String key= prop.getKey().toString();
			String value= prop.getValue().toString();
			System.out.println("key=" + key + ", value=" + value);
		}
		goBackOption();
		drawBorder();
	}
	
	public static void showInventory(){
		drawBorder();
		System.out.println("showing inventory of products...");
		for(Item item: ProductFactory.getAllProducts()) {
			System.out.println(item);
		}
		goBackOption();
		drawBorder();
	}
	
	public static void addProductsMenu(Scanner in, ShoppingCart cart){
		drawBorder();
		System.out.println("Press corresponding number to add the product...");
		int i=0;
		for(Item item: ProductFactory.getAllProducts()) {
			System.out.println("Press " + (++i) + " to add " + item.getName());
		}
		goBackOption();
		drawBorder();
		int option=0;
		do {
			option= in.nextInt();
		    switch ( option ) {
		      case 0:
		    	  showMainMenu();
		    	  break;
		      case 1:
		    	  System.out.println("Adding 1 GB Data-pack");
		    	  cart.add( ProductFactory.getProduct("1gb") );
		    	  break;
		      case 2:
		    	  System.out.println("Adding Unlimited 1GB");
		    	  cart.add( ProductFactory.getProduct("ult_small") );
		    	  break;
		      case 3:
		    	  System.out.println("Adding Unlimited 2GB");
		    	  cart.add( ProductFactory.getProduct("ult_medium") );
		    	  break;
		      case 4:
		    	  System.out.println("Adding Unlimited 5GB");
		    	  cart.add( ProductFactory.getProduct("ult_large") );
		    	  break;
		      default:
		    	  System.err.println( "Unrecognized option" );
		    	  break;
		    }
		}while(option != 0);
	}
	
	public static void showCartContents(ShoppingCart cart){
		if(cart.items().isEmpty()) System.out.println("Cart is empty!");
		for(Item item: cart.items()){
			System.out.println(item);
		}
	}
	
	public static void getTotalPriceOfCart(ShoppingCart cart){
		System.out.println("total price: $" + cart.total());
	}
	
	public static void inputPromoCode( Scanner in, ShoppingCart cart) {
		System.out.println("Promo code: ");
		String inputPromoCode= in.next();
		PromoCode promoCode= SpecialOffersFactory.getDefaultPromoCode();
		if(promoCode.getCode().equals(inputPromoCode)){
			cart.addSpecialOffer(promoCode);
			System.out.println("Promo code " + inputPromoCode + " accepted");
		}else{
			System.out.println("Promo code " + inputPromoCode + " rejected");
		}
	}
	
	public static void showMainMenu(){
		drawBorder();
		System.out.println("Welcome to Amaysim Shopping");
		System.out.println("Press 1 to show inventory");
		System.out.println("Press 2 to start adding products to cart");
		System.out.println("Press 3 to show all products in the cart");
		System.out.println("Press 4 to calculate total price of the cart");
		System.out.println("Press 5 to input promo code");
		System.out.println("Press 6 to quit");
		drawBorder();
	}
	
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner ( System.in );
	    
		ShoppingCart cart= new DefaultShoppingCart();
		cart.addSpecialOffer(SpecialOffersFactory.getDefaultFreeAWithEveryBSold());
		cart.addSpecialOffer(SpecialOffersFactory.getDefaultBuyMPayForNDeal());
		cart.addSpecialOffer(SpecialOffersFactory.getDefaultBulkDiscountDeal());
		
		showMainMenu();
		int option= 0;
		do {
			option= in.nextInt();
		    switch ( option ) {
		      case 0:
		    	  showMainMenu();
		    	  break;
		      case 1:
		    	  showInventory();
		    	  break;
		      case 2:
		    	  addProductsMenu(in, cart);
		    	  break;
		      case 3:
		    	  showCartContents(cart);
		    	  break;
		      case 4:
		    	  getTotalPriceOfCart(cart);
		    	  break;
		      case 5:
		    	  inputPromoCode(in, cart);
		    	  break;
		      case 6:
		    	  System.out.println( "Good Bye" );
		    	  break;
		      default:
		    	  System.err.println( "Unrecognized option" );
		    	  break;
		    }
		}while(option != 6);
		
		System.exit(0);
	}

}
