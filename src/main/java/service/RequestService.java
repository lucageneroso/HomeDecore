package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.OrderManagement.Prodotto;
import model.RequestManagement.Request;
import remoteInterfaces.RequestServiceRemote;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Stateless
public class RequestService implements RequestServiceRemote {

    @PersistenceContext(unitName = "HomeDecorePU")
    private EntityManager em;


    @Override
    public void addRequest(Request request) {
        em.persist(request);
    }

    @Override
    public void removeRequest(Request request) {
        em.remove(request);
    }

    @Override
    public void updateRequest(Request request) {
        em.merge(request);
    }

    @Override
    public Request findById(int ID) {
        TypedQuery<Request> query=  em.createNamedQuery("Request.TROVA_PER_ID", Request.class);
        query.setParameter("id", ID);
        return query.getSingleResult();
    }

    @Override
    public List<Request> findAll() {
        TypedQuery<Request> query= em.createNamedQuery("Request.TROVA_TUTTI", Request.class);
        return query.getResultList();
    }

    @Override
    public List<Request> findByDate(LocalDateTime date) {
        TypedQuery<Request> query=  em.createNamedQuery("Request.TROVA_PER_DATA", Request.class);
        query.setParameter("data", date);
        return query.getResultList();
    }

    @Override
    public List<Request> findByPostDate(LocalDateTime date) {
        TypedQuery<Request> query=  em.createNamedQuery("Request.TROVA_DOPO_DI_DATA", Request.class);
        query.setParameter("data", date);
        return query.getResultList();
    }

    @Override
    public List<Request> findByPreviousDate(LocalDateTime date) {
        TypedQuery<Request> query=  em.createNamedQuery("Request.TROVA_PRIMA_DI_DATA", Request.class);
        query.setParameter("data", date);
        return query.getResultList();
    }

    @Override
    public List<Request> orderByDate() {
        return List.of();
    }

    @Override
    public List<Request> findByMagazziniere(int userID) {
        return List.of();
    }

    @Override
    public List<Request> findByDestinatario(int userID) {
        return List.of();
    }
}
