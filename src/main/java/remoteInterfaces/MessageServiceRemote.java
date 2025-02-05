package remoteInterfaces;

import model.ChatManagement.Message;
import model.UserManagement.Utente;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageServiceRemote {

    void addMessage(Message message);
    Message updateMessage(Message message);
    void deleteMessage(int id);

    List<Message> findById(int id);
    List<Message> findByDate(LocalDateTime date);

    List<Message> findMessagesByUser(Utente utente);
}
