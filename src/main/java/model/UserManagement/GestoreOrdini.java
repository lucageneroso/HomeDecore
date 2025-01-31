package model.UserManagement;

import enumerativeTypes.Stato;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import model.OrderManagement.Ordine;
import model.OrderManagement.Prodotto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("GESTOREORDINI")
public class GestoreOrdini extends Utente implements Serializable {



    private List<Integer> ordiniID;

    public GestoreOrdini() {}

    // Costruttore che copia i dati da un Utente
    /*
    public GestoreOrdini(List<Ordine> ordini){
        super();
        this.ordini = ordini;
    }

    // Costruttore che copia i dati da un Utente e aggiunta di una lista di ordini
    public GestoreOrdini(Utente utente) {
        super(utente.getNome(), utente.getCognome(), utente.getEmail(), utente.getUsername(), utente.getPassword(), utente.getRuolo());
        this.ordini = new ArrayList<>();
    }

    public List<Ordine> getOrdini() {
        return ordini;
    }
    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

    public void addOrdine(Ordine ordine){
        this.ordini.add(ordine);
    }

    public void removeOrdine(Ordine ordine){
        this.ordini.remove(ordine);
    }

    @Override
    public String toString() {
        return super.toString();
    }*/


    public GestoreOrdini(List<Integer> ordiniID){
        super();
        this.ordiniID = ordiniID;
    }

    // Costruttore che copia i dati da un Utente e aggiunta di una lista di ordini
    public GestoreOrdini(Utente utente) {
        super(utente.getNome(), utente.getCognome(), utente.getEmail(), utente.getUsername(), utente.getPassword());
        this.ordiniID = new ArrayList<>();
    }

    public void cambiaStatoOrdine(Ordine ordine, Stato stato){
        ordine.setStato(stato);
    }


}
