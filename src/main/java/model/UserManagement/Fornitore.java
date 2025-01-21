package model.UserManagement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import model.OrderManagement.Prodotto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("FORNITORE")
public class Fornitore extends Utente implements Serializable {

    @OneToMany(mappedBy = "fornitore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prodotto> prodottiForniti;

    public Fornitore(){}

    public Fornitore(List<Prodotto> prodottiForniti){
        super();
        this.prodottiForniti = prodottiForniti;
    }

    // Costruttore che copia i dati da un Utente
    public Fornitore(Utente utente) {
        super(utente.getNome(), utente.getCognome(), utente.getEmail(), utente.getUsername(), utente.getPassword(), utente.getRuolo());
        this.prodottiForniti = new ArrayList<>();
    }

    // Costruttore che copia i dati da un Utente e aggiunta di una lista di prodotti
    public Fornitore(Utente utente, List<Prodotto> prodottiForniti) {
        super(utente.getNome(), utente.getCognome(), utente.getEmail(), utente.getUsername(), utente.getPassword(), utente.getRuolo());
        this.prodottiForniti = prodottiForniti;
    }

    public List<Prodotto> getProdottiForniti() {
        return prodottiForniti;
    }

    public void setProdottiForniti(List<Prodotto> prodottiForniti) {
        this.prodottiForniti = prodottiForniti;
    }

    public void addProdotto(Prodotto prodotto){
        prodottiForniti.add(prodotto);
    }

    public void removeProdotto(Prodotto prodotto){
        prodottiForniti.remove(prodotto);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
