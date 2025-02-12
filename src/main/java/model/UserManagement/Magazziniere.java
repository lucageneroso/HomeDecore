package model.UserManagement;

import enumerativeTypes.Ruolo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import model.OrderManagement.Prodotto;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MAGAZZINIERE")
public class Magazziniere extends Utente{

    private Magazzino magazzino;

    public Magazziniere() {}

    // Constructor with basic fields
    public Magazziniere(String nome, String cognome, String email, String password, Magazzino magazzino) {
        super(nome, cognome, email, password, Ruolo.MAGAZZINIERE);
        this.magazzino = magazzino;
    }

    public Magazzino getMagazzino() {return magazzino;}
    public void setMagazzino(Magazzino magazzino) {this.magazzino = magazzino;}

    @Override
    public String toString(){
        return super.toString() + " " + magazzino.toString();
    }


}
