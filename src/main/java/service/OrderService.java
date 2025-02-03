package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.OrderManagement.ItemCartDTO;
import model.OrderManagement.Ordine;
import enumerativeTypes.Stato;
import remoteInterfaces.OrderServiceRemote;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class OrderService implements OrderServiceRemote {
    @PersistenceContext(unitName = "HomeDecorePU")
    private EntityManager em;

    @Override
    public Ordine addOrder(Ordine order) {
        em.persist(order);
        em.flush(); // Assicurati che l'ID venga generato
        System.out.println("Ordine salvato con ID: " + order.getId());
        return order;
    }

    @Override
    public Ordine findOrderById(int id) {
        return em.find(Ordine.class, id);
    }

    @Override
    public List<Ordine> findAllOrders() {
        TypedQuery<Ordine> query=em.createNamedQuery("Ordine.TROVA_TUTTI", Ordine.class);
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
    public List<Ordine> findOrdersByCostumer(long userId) {
        TypedQuery<Ordine> query=em.createNamedQuery("Ordine.TROVA_PER_UTENTE", Ordine.class);
        query.setParameter("userId", userId);
        List<Ordine> orders=query.getResultList();
        System.out.println("Ordini trovati per l'utente " + userId + ": " + orders.size());
        return orders;
    }

    @Override
    public List<Ordine> findByPrize(Double prezzo) {
        TypedQuery<Ordine> query=em.createNamedQuery("Ordine.TROVA_PER_TOTALE", Ordine.class);
        query.setParameter("totale", prezzo);
        return query.getResultList();
    }

    @Override
    public List<Ordine> findByDate(Date date) {
        TypedQuery<Ordine> query=em.createNamedQuery("Ordine.TROVA_PER_DATA", Ordine.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Ordine> findByState(Stato stato){
        TypedQuery<Ordine> query=em.createNamedQuery("Ordine.TROVA_PER_STATO", Ordine.class);
        query.setParameter("stato", stato);
        return query.getResultList();
    }

    @Override
    public List<ItemCartDTO> deserializeItems(List<String> serializedItems) {
        List<ItemCartDTO> items = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            for (String json : serializedItems) {
                ItemCartDTO item = objectMapper.readValue(json, ItemCartDTO.class);
                items.add(item);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return items;
    }
}
