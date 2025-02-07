package model.UserManagement;

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
    public Magazziniere(String nome, String cognome, String email, String username, String password, Magazzino magazzino) {
        super(nome, cognome, email, username, password);
        this.magazzino = magazzino;
    }

    public Magazzino getMagazzino() {return magazzino;}
    public void setMagazzino(Magazzino magazzino) {this.magazzino = magazzino;}

    @Override
    public String toString(){
        return super.toString() + " " + magazzino.toString();
    }


}
