package service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.ChatManagement.Message;
import model.Utente;
import remoteInterfaces.MessageServiceRemote;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static model.ChatManagement.Message.*;

public class MessageService implements MessageServiceRemote {
    @PersistenceContext
    EntityManager em;


    //struttura per tenere traccia dei messaggi associati e resi persistenti dell'utente
    private HashMap<Utente, ArrayList<Message>> messaggi=new HashMap<>();


    @Override
    public void addMessage(Message message) {
        em.persist(message);
    }

    @Override
    public Message updateMessage(Message message) {
        return em.merge(message);
    }

    @Override
    public void deleteMessage(int id) {
        em.remove(em.find(Message.class, id));
    }

    @Override
    public List<Message> findById(int id) {
        TypedQuery<Message> query=em.createNamedQuery(FIND_BY_ID,Message.class);
        query.setParameter("id",id);
        return query.getResultList();
    }

    @Override
    public List<Message> findByDate(LocalDateTime date) {
        TypedQuery<Message> query=em.createNamedQuery(FIND_BY_DATE,Message.class);
        query.setParameter("date",date);
        return query.getResultList();
    }

    @Override
    public List<Message> findMessagesByUser(Utente utente) {
        ArrayList<Message> lista = new ArrayList<>();

        if (!messaggi.containsKey(utente)) {
            System.out.println("nessun messaggio inviato/ricevuto");
        } else
            lista = messaggi.get(utente);

        return lista;
    }


}
