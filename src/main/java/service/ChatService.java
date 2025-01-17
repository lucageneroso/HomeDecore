package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import model.ChatManagement.Chat;
import model.UserManagement.UserReceiver;
import model.UserManagement.UserSender;
import remoteInterfaces.ChatServiceRemote;

import java.util.List;

import static model.ChatManagement.Chat.FINDBYSENDER;

public class ChatService implements ChatServiceRemote {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addChat(Chat chat) {
        em.persist(chat);
    }

    @Override
    public void removeChat(Chat chat) {
          em.remove(chat);
    }

    @Override
    public Chat findById(int id) {
            return em.find(Chat.class, id);
    }


    //Recupera tutte le chat associate all'utente mittente
    @Override
    public List<Chat> findChatsBySender(UserSender sender) {
        TypedQuery<Chat> query = em.createNamedQuery(FINDBYSENDER, Chat.class);
        query.setParameter("sender", sender);
        return query.getResultList();
    }


    //Recupera tutte le chat associate all'utente destinatario
    @Override
    public List<Chat> findChatsByReceiver(UserReceiver receiver) {
        TypedQuery<Chat> query = em.createNamedQuery(FINDBYSENDER, Chat.class);
        query.setParameter("receiver", receiver);
        return query.getResultList();
    }
}
