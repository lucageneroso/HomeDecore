package remoteInterfaces;

import model.OrderManagement.Cart;

//import javax.ejb.Remote;
import jakarta.ejb.Remote;

@Remote
public interface CartServiceRemote {
    void addCart(Cart cart);
    void clearCart(Cart cart);
    void updateCart(Cart cart);
    Cart findCartById(int id);
    Cart findCartByCostumer(long userId);
    void removeProductFromCart(int cartId, int productId); // Nuovo metodo
}
