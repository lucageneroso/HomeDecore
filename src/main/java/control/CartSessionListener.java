package control;


import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.Cart;
import model.UserManagement.Utente;
import remoteInterfaces.CartServiceRemote;
import jakarta.ejb.EJB;

@WebListener
public class CartSessionListener implements HttpSessionListener {

    @EJB
    CartServiceRemote cartService;

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        Utente user = (Utente) session.getAttribute("loggedUser");
        Cart cart = (Cart) session.getAttribute("cart");
        cart.setUserId(user.getId());

        if (user != null && cart != null && !cart.getItems().isEmpty()) {
            cartService.addCart(cart);
        }
    }
}
