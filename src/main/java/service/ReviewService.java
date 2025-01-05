package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.ReviewManagement.Recensione;
import remoteInterfaces.RecensioneServiceRemote;

import java.util.Date;
import java.util.List;

@Stateless
public class ReviewService implements RecensioneServiceRemote {
    @PersistenceContext(unitName = "HomeDecorePU")
    private EntityManager em;


    @Override
    public void addReview(model.ReviewManagement.Recensione r) {

    }

    @Override
    public void removeReview(model.ReviewManagement.Recensione r) {

    }

    @Override
    public void updateReview(model.ReviewManagement.Recensione r) {

    }

    @Override
    public Recensione findById(int ID) {
        TypedQuery<Recensione> query = em.createNamedQuery("Recensione.TROVA_PER_ID", Recensione.class);
        query.setParameter("ID", ID);
        return query.getSingleResult();
    }

    @Override
    public List<Recensione> findAll() {
        TypedQuery<Recensione> query= em.createNamedQuery("Recensione.TROVA_TUTTE", Recensione.class);
        return query.getResultList();
    }

    @Override
    public List<Recensione> findByDate(Date date) {
        TypedQuery<Recensione> query= em.createNamedQuery("Recensione.TROVA_PER_DATA", Recensione.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Recensione> findByUser(int userID) {
        TypedQuery<Recensione> query= em.createNamedQuery("Recensione.TROVA_PER_CLIENTE", Recensione.class);
        query.setParameter("userID", userID);
        return query.getResultList();
    }

    @Override
    public List<Recensione> findByProduct(int productID) {
        TypedQuery<Recensione> query= em.createNamedQuery("Recensione.TROVA_PER_PRODOTTO", Recensione.class);
        query.setParameter("productID", productID);
        return query.getResultList();
    }

    @Override
    public List<Recensione> findByRating(int rating) {
        TypedQuery<Recensione> query=em.createNamedQuery("Recensione.TROVA_PER_RATING", Recensione.class);
        query.setParameter("rating", rating);
        return query.getResultList();
    }
}
