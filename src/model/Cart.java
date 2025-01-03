package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<Prodotto> products;
	
	public Cart() {
		products = new ArrayList<Prodotto>();
	}
	
	public void addProduct(Prodotto product) {
		products.add(product);
	}
	
	public void deleteProduct(Prodotto product) {
		for(Prodotto prod : products) {
			if(prod.getID() == product.getID()) {
				products.remove(prod);
				break;
			}
		}
 	}

	public void deleteAllProduct() {
		products.clear();
		}
	public List<Prodotto> getProducts() {
		return  products;
	}
	
	public boolean presente(int productId) {
	    for (Prodotto p : products) {
	        if (p.getID()==(productId)) {
	            return true;
	        }
	    }
	    return false;
	}

}
