package model.UserManagement;

import enumerativeTypes.Ruolo;
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


    private List<Integer> prodottiForniti; //Lista di interi per gli ID dei prodotti forniti

    public Fornitore(){}

    // Constructor with basic fields
    public Fornitore(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password, Ruolo.FORNITORE);
        this.prodottiForniti = new ArrayList<>();
    }

    // Constructor with basic fields and a list of prodottiForniti
    public Fornitore(String nome, String cognome, String email, String password, List<Integer> prodottiForniti) {
        super(nome, cognome, email, password, Ruolo.FORNITORE);
        this.prodottiForniti = prodottiForniti;
    }


    /*public List<Integer> getProdottiForniti() {
        return prodottiForniti;
    }
    */


    public void setProdottiForniti(List<Integer> prodottiForniti) {
        this.prodottiForniti = prodottiForniti;
    }

    public void addProdotto(Integer prodottoID){
        prodottiForniti.add(prodottoID);
    }

    public void removeProdotto(Integer prodottoID){
        prodottiForniti.remove(prodottoID);
    }

    public FornitoreDTO toDTO(){
        FornitoreDTO fornitoreDTO = new FornitoreDTO(super.getId(), super.getEmail()   );
        System.out.println(fornitoreDTO.toString());
        return fornitoreDTO;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
