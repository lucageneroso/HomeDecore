package model.UserManagement;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Utente{

    private Indirizzo indirizzo;

    public Cliente(Indirizzo indirizzo){
        super();
        this.indirizzo = indirizzo;
    }

    public Cliente(){}

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }


}
