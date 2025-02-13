package service;

import enumerativeTypes.StatoRichiesta;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.Order;
import model.OrderManagement.Prodotto;
import model.RequestManagement.OrderRequest;
import model.RequestManagement.ProductRequest;
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

        if (request instanceof ProductRequest) {
            ProductRequest managedRequest = em.merge((ProductRequest) request);
            em.remove(managedRequest);
        }

        if (request instanceof OrderRequest) {
            OrderRequest managedRequest = em.merge((OrderRequest) request);
            em.remove(managedRequest);
        }

    }

    @Override
    public void updateRequest(Request request) {
        em.merge(request);
    }

    @Override
    public Request findById(Long ID) {
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
    public void cambiaStato(Request request) {
        request.accettaRichiesta();
        em.merge(request);
    }

    @Override
    public List<Request> findByDate(LocalDateTime dataStart, LocalDateTime dataEnd) {
        TypedQuery<Request> query=  em.createNamedQuery("Request.TROVA_PER_DATA", Request.class);
        query.setParameter("dataStart", dataStart);
        query.setParameter("dataEnd", dataEnd);
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
    public List<Request> findByMagazziniere(Long magazziniereID) {
        TypedQuery<Request> query=  em.createNamedQuery("Request.TROVA_PER_MAGAZZINIERE", Request.class);
        query.setParameter("magazziniereID", magazziniereID);
        return query.getResultList();
    }

    @Override
    public List<Request> findByDestinatario(Long destinatarioID) {
        TypedQuery<Request> query=  em.createNamedQuery("Request.TROVA_PER_DESTINATARIO", Request.class);
        query.setParameter("destinatarioID", destinatarioID);
        return query.getResultList();
    }
}
