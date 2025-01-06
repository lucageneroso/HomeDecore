package model.UserManagement;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import model.OrderManagement.Prodotto;
import java.util.List;

@Entity
@DiscriminatorValue("FORNITORE")
public class Fornitore extends Utente{

    private List<Prodotto> prodottiForniti;

    public Fornitore(){}

    public Fornitore(List<Prodotto> prodottiForniti){
        super();
        this.prodottiForniti = prodottiForniti;
    }

    public List<Prodotto> getProdottiForniti() {
        return prodottiForniti;
    }

    public void setProdottiForniti(List<Prodotto> prodottiForniti) {
        this.prodottiForniti = prodottiForniti;
    }

    @Override
    public String toString() {
        return super.toString()+" Prodotti:"+prodottiForniti.toString();
    }

}
