package com.amaysim.shoppingcart.impl;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import com.amaysim.shoppingcart.interfaces.*;

public final class ProductFactory {
	private static Map<String,Item> allItems= new HashMap<String,Item>();
	
	static {
		try {
			File xmlFile = new File("src/resources/products.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("product");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
					Element e = (Element) nNode;
	
					String code= e.getAttribute("code");
					String name= e.getElementsByTagName("name").item(0).getTextContent();
					double price=  Double.parseDouble(e.getElementsByTagName("price").item(0).getTextContent());
					Item product= new NonPersistentProduct(code, name, price);
					allItems.put(code, product);
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static Item getProduct(String code){
		return allItems.get(code).clone();
	}
	
	public static List<Item> getAllProducts(){
		List<Item> newList= new ArrayList<Item>(allItems.values());
		Collections.sort(newList);
		return Collections.unmodifiableList( newList );
	}
	
	public static Item createNewProduct(String code, String name, double price) {
		if(allItems.containsKey(code)) throw new IllegalArgumentException("This product: " + code + " already exist in registry");
		Item product= new NonPersistentProduct(code, name, price);
		allItems.put(code, product);
		return product;
	}
}
