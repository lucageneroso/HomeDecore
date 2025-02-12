package model.UserManagement;

import enumerativeTypes.Ruolo;
import enumerativeTypes.Stato;
import jakarta.persistence.*;
import model.OrderManagement.Ordine;
import model.OrderManagement.Prodotto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("GESTOREORDINI")
@NamedQueries({
        @NamedQuery(name="GestoreOrdini.TROVA_TUTTI", query="SELECT g FROM GestoreOrdini g"),
})
public class GestoreOrdini extends Utente implements Serializable {



    private List<Long> listaOrdini;

    public GestoreOrdini() {}

    public GestoreOrdini(List<Long> ordiniID){
        super();
        this.listaOrdini = ordiniID;
    }

    // Constructor with basic fields
    public GestoreOrdini(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password, Ruolo.GESTOREORDINI);
        this.listaOrdini = new ArrayList<>();
    }

    public void cambiaStatoOrdine(Ordine ordine, Stato stato){
        ordine.setStato(stato);
    }

    /*public List<Long> getOrdini() {
        return listaOrdini;
    }
    */

    public void setOrdini(List<Long> ordiniID) {
        this.listaOrdini = ordiniID;
    }
    public void aggiungiOrdine(Ordine ordine){
        listaOrdini.add(ordine.getId());
    }
    public void removeOrdine(Ordine ordine){
        listaOrdini.remove(ordine.getId());
    }


    @Override
    public String toString() {
        return super.toString();
    }


}