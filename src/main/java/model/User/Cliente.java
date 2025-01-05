package model.User;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Utente{
    private String username;
    private String indirizzo;

    public Cliente(String username, String indirizzo){
        super();
        this.username = username;
        this.indirizzo = indirizzo;
    }
    public Cliente(){}

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Cliente{" + "username=" + username + '}';
    }





}
