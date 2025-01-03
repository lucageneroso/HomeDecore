package control;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Recensione;
import remoteInterfaces.RecensioneServiceRemote;

import java.util.Date;
import java.util.List;

@Stateless
public class ReviewService implements RecensioneServiceRemote {
    @Inject
    private EntityManager em;

    @Override
    public void addReview(Recensione r) {
        em.persist(r);
    }

    @Override
    public void removeReview(Recensione r) {
        em.remove(em.merge(r));
    }

    @Override
    public void updateReview(Recensione r) {
        em.merge(r);
    }

    @Override
    public Recensione findById(int ID) {
        TypedQuery<Recensione> query = em.createNamedQuery("TROVA_PER_IDENT", Recensione.class);
        query.setParameter("ID", ID);
        return query.getSingleResult();
    }

    @Override
    public List<Recensione> findAll() {
        TypedQuery<Recensione> query= em.createNamedQuery("TROVA_TUTTE", Recensione.class);
        return query.getResultList();
    }

    @Override
    public List<Recensione> findByDate(Date date) {
        TypedQuery<Recensione> query= em.createNamedQuery("TROVA_PER_DATE", Recensione.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Recensione> findByUser(int userID) {
        TypedQuery<Recensione> query= em.createNamedQuery("TROVA_PER_CLIENTE", Recensione.class);
        query.setParameter("userID", userID);
        return query.getResultList();
    }

    @Override
    public List<Recensione> findByProduct(int productID) {
        TypedQuery<Recensione> query= em.createNamedQuery("TROVA_PER_PRODOTTO", Recensione.class);
        query.setParameter("productID", productID);
        return query.getResultList();
    }

    @Override
    public List<Recensione> findByRating(int rating) {
        TypedQuery<Recensione> query=em.createNamedQuery("TROVA_PER_RATING", Recensione.class);
        query.setParameter("rating", rating);
        return query.getResultList();
    }
}
