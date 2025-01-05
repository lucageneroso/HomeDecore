package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import model.ItemCart;

@Entity
@NamedQueries({
        @NamedQuery(name="Cart.TROVA_COSTUMER", query="SELECT c FROM Cart c WHERE c.userId = :userId"),
        @NamedQuery(name="Cart.TROVA_ID", query="SELECT c FROM Cart c WHERE c.id= :id")
})

public class Cart {
    @Id @GeneratedValue
    private int id;

    @OneToMany
    private List<ItemCart> items;

    private int userId;

    public Cart() {
        this.items = new ArrayList<ItemCart>();
    }
    public Cart(List<ItemCart> items, int userId) {
        this.items = items;
        this.userId = userId;
    }
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemCart> getItems() {
        return items;
    }
    public void setItems(List<ItemCart> items) {
        this.items = items;
    }
}
