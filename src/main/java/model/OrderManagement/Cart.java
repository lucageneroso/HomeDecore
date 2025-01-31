package model.OrderManagement;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name="Cart.TROVA_COSTUMER", query="SELECT c FROM Cart c WHERE c.userId = :userId"),
        @NamedQuery(name="Cart.TROVA_ID", query="SELECT c FROM Cart c WHERE c.id= :id")
})

public class Cart implements Serializable {
    @Id @GeneratedValue
    private int id;

    @Transient
    private List<ItemCart> items;

    private long userId;

    public Cart() {
        this.items = new ArrayList<ItemCart>();
    }
    public Cart(List<ItemCart> items, long userId) {
        this.items = items;
        this.userId = userId;
    }
    public int getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
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

    public double calculateTotal() {
        double total = 0;
        if (this.items.isEmpty()){
            total=0.0;
        }
        else {
            for (ItemCart item : this.getItems()) {
                total += item.getProdotto().getPrezzo() * item.getQuantity();
            }
        }
        return total;
    }

    public void addItem(ItemCart item) {
        this.items.add(item);
    }


    public void removeItem(int productId) {
        Iterator<ItemCart> iterator = items.iterator();
        while (iterator.hasNext()) {
            ItemCart item = iterator.next();
            if (item.getProdotto().getId() == productId) {
                iterator.remove(); // Usa iterator.remove() per evitare ConcurrentModificationException
                break; // Esci dopo aver rimosso l'elemento
            }
        }
        }

    public void updateProductQuantity(int productId, int quantity) {
        Iterator<ItemCart> iterator = items.iterator();
        while (iterator.hasNext()) {
            ItemCart item = iterator.next();
            if (item.getProdotto().getId() == productId) {
                    item.setQuantity(quantity);
                    break;
            }
        }
    }
}
