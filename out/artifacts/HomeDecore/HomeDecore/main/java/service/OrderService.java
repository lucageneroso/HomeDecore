package control;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Ordine;
import model.Prodotto;
import enumerativeTypes.Stato;
import remoteInterfaces.OrderServiceRemote;

import java.sql.Date;
import java.util.List;

@Stateless
public class OrderService implements OrderServiceRemote {
    @Inject
    private EntityManager em;

    @Override
    public void addOrder(Ordine order) {
        em.persist(order);
    }

    @Override
    public Ordine findOrderById(int id) {
       return em.find(Ordine.class, id);
    }

    @Override
    public List<Prodotto> findAllOrders() {
        TypedQuery<Prodotto> query=em.createNamedQuery("TROVA_TUTTI", Prodotto.class);
        return query.getResultList();
    }

    @Override
    public void updateOrder(Ordine order) {
        em.merge(order);
    }

    @Override
    public void removeOrder(int id) {
        em.remove(em.find(Ordine.class, id));
    }

    @Override
    public List<Prodotto> findOrdersByCostumer(int userId) {
        TypedQuery<Prodotto> query=em.createNamedQuery("TROVA_PER_UTENTE", Ordine.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Prodotto> findByPrize(Double prezzo) {
        TypedQuery<Prodotto> query=em.createNamedQuery("TROVA_PER_TOTALE", Ordine.class);
        query.setParameter("totale", prezzo);
        return query.getResultList();
    }

    @Override
    public List<Prodotto> findByDate(Date date) {
        TypedQuery<Prodotto> query=em.createNamedQuery("TROVA_PER_DATA", Ordine.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Prodotto> findByState(Stato stato){
        TypedQuery<Prodotto> query=em.createNamedQuery("TROVA_PER_STATO", Ordine.class);
        query.setParameter("stato", stato);
        return query.getResultList();
    }
}
