package model.OrderManagement;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.io.Serializable;


public class ItemCart implements Serializable {


    private Prodotto prodotto;
    private int quantity;


    public ItemCart() {}

    public ItemCart(Prodotto prodotto, int quantity) {
        this.prodotto = prodotto;
        this.quantity = quantity;
    }
    public Prodotto getProdotto() {
        return prodotto;
    }
    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
