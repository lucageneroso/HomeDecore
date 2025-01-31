package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.OrderManagement.Cart;
import model.OrderManagement.ItemCart;
import remoteInterfaces.CartServiceRemote;

@Stateless (name= "CartService")
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
    public Cart findCartByCostumer(long userId) {
        TypedQuery<Cart> query = em.createNamedQuery("Cart.TROVA_COSTUMER", Cart.class);
        query.setParameter("userId", userId);
        Cart cart;
        try {
            cart = query.getSingleResult();
        } catch (NoResultException e) {
            cart = new Cart();
            // Puoi anche impostare l'utente associato al carrello, se necessario
             cart.setUserId(userId);
            em.persist(cart); // Salva il nuovo carrello nel database
        }
        return cart;
    }

    @Override
    public void removeProductFromCart(int cartId, int productId) {
        Cart cart = em.find(Cart.class, cartId);
        if (cart != null) {
            cart.getItems().removeIf(item -> item.getProdotto().getId() == productId);
            em.merge(cart);
        }
    }


}
