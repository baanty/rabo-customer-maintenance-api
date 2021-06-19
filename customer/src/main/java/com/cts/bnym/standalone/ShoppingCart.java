package com.cts.bnym.standalone;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ShoppingCart {

	@SuppressWarnings("deprecation")
	public static double findLowestPrice(List<List<String>> products, List<List<String>> discounts)

	{
		if (products.size() < 1 || discounts.size() > 1000) {
			return 0;
		}
		Hashtable<String, String> discountStoreage = new Hashtable<String, String>();

		for (List<String> discount : discounts) {
			
			if (discountStoreage.containsKey(discount.get(0) + "-" + discount.get(1)) == false) {
				discountStoreage.put(discount.get(0) + "-" + discount.get(1), discount.get(2));
			}

		}
		List<Double> prices = new ArrayList<Double>(products.size());

		for (List<String> product : products) {
			
			int basePrice = Integer.parseInt(product.get(0));
			List<Double> discountedPrices = new ArrayList<Double>(discounts.size());

			for (int i = 1; i < product.size(); i++) {

				if (discountStoreage.containsKey(product.get(i) + "-" + "0") == true) {
					double discountvalue = Double.parseDouble((discountStoreage.get(product.get(i) + "-" + "0")));
					discountedPrices.add(new Long(Math.round(discountvalue)).doubleValue());
				} else if (discountStoreage.containsKey(product.get(i) + "-" + "1") == true) {
					double discountvalue = Double.parseDouble(discountStoreage.get(product.get(i) + "-" + "1"));
					discountedPrices.add((new Long(Math.round((basePrice - basePrice * discountvalue / 100))).doubleValue()));
				} else if (discountStoreage.containsKey(product.get(i) + "-" + "2") == true) {
					double discountvalue = Double.parseDouble(discountStoreage.get(product.get(i) + "-" + "2"));
					discountedPrices.add((new Long(Math.round((basePrice - discountvalue))).doubleValue()));
				}

			}

			if (discountedPrices.size() > 0) {
				prices.add(discountedPrices.stream().min(Double::compare).get());
			}

		}
		return prices.stream().mapToDouble(Double::doubleValue).sum();
	}

}
