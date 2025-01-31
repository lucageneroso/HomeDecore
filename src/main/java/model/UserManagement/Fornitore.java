package model.UserManagement;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import model.OrderManagement.Prodotto;
import model.RequestManagement.ProductRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("FORNITORE")
public class Fornitore extends Utente implements Serializable {


    @OneToMany(mappedBy = "fornitore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prodotto> prodottiForniti;



    public Fornitore(){}

    // Constructor with basic fields
    public Fornitore(String nome, String cognome, String email, String username, String password) {
        super(nome, cognome, email, username, password);
        this.prodottiForniti = new ArrayList<>();
    }

    // Constructor with basic fields and a list of prodottiForniti
    public Fornitore(String nome, String cognome, String email, String username, String password, List<Prodotto> prodottiForniti) {
        super(nome, cognome, email, username, password);
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
