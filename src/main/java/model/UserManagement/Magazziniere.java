package model.UserManagement;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import model.OrderManagement.Prodotto;
import java.util.List;

@Entity
@DiscriminatorValue("MAGAZZINIERE")
public class Magazziniere extends Utente{

    private Magazzino magazzino;

    public Magazziniere() {}
    public Magazziniere(Magazzino magazzino) {
        super();
        this.magazzino = magazzino;
    }

    public Magazzino getMagazzino() {return magazzino;}
    public void setMagazzino(Magazzino magazzino) {this.magazzino = magazzino;}

    @Override
    public String toString(){
        return super.toString() + " " + magazzino.toString();
    }


}
