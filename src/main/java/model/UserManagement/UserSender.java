package model.UserManagement;


import enumerativeTypes.Ruolo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserSender extends Utente{
    @Id
    private int id;

    private boolean stato;
    private int numMessageCount;

    public UserSender() {

    }

    public UserSender(String nome, String cognome, String email, String password, Ruolo ruolo,boolean stato,int numMessageCount){
        super(nome,cognome,email,password, Ruolo.CLIENTE);
        this.stato=stato;
        this.numMessageCount=numMessageCount;
    }



    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public int getNumMessagesCount() {
        return numMessageCount;
    }

    public void IncrementNumMessagesCount() {
        this.numMessageCount++;
    }


}
