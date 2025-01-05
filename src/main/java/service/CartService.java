package control;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.Cart;
import remoteInterfaces.CartServiceRemote;

public class CartService implements CartServiceRemote {
    @PersistenceContext(unitName = "HomeDecorePU")
    private EntityManager em;

    @Override
    public void addCart(Cart cart) {
        em.persist(cart);
    }

    @Override
    public void clearCart(Cart cart) {
        em.remove(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        em.merge(cart);
    }

    @Override
    public Cart findCartById(int id) {
        TypedQuery<Cart> query=em.createNamedQuery("Cart.TROVA_ID", Cart.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Cart findCartByCostumer(int userId) {
        TypedQuery<Cart> query=em.createNamedQuery("Cart.TROVA_COSTUMER", Cart.class);
        query.setParameter("userId", userId);
        return query.getSingleResult();
    }
}
