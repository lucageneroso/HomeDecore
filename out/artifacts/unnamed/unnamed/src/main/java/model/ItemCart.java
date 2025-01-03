package model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ItemCart {

    @OneToOne
    private Prodotto prodotto;
    private int quantity;
    @Id
    private Long id;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
