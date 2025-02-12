package model.UserManagement;

import enumerativeTypes.Ruolo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserReceiver extends Utente{
    @Id @GeneratedValue
    private int id;
    private int unreadMessagesCount;      // Numero di messaggi non letti
    private String lastReceivedMessage;   // Ultimo messaggio ricevuto

    public UserReceiver() {

    }
    public UserReceiver(String nome, String cognome, String email, String password, Ruolo ruolo) {
        super(nome,cognome,email,password, Ruolo.CLIENTE);
        this.unreadMessagesCount = 0;
        this.lastReceivedMessage = null;
    }



    public int getUnreadMessagesCount() {
        return unreadMessagesCount;
    }

    public void incrementUnreadMessages() {
        this.unreadMessagesCount++;
    }

    public void resetUnreadMessages() {
        this.unreadMessagesCount = 0;
    }

    public String getLastReceivedMessage() {
        return lastReceivedMessage;
    }

    public void setLastReceivedMessage(String lastReceivedMessage) {
        this.lastReceivedMessage = lastReceivedMessage;
    }


}
