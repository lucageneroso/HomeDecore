package model.UserManagement;

import enumerativeTypes.Ruolo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Utente{

    private Indirizzo indirizzo;

    // Constructor for Cliente
    public Cliente(String nome, String cognome, String email, String password, Indirizzo indirizzo) {
        super(nome, cognome, email, password, Ruolo.CLIENTE); // Call the superclass constructor
        this.indirizzo = indirizzo;
    }

    public Cliente(){}

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }


    public Ruolo getRuolo() {
        return Ruolo.CLIENTE;
    }


}
