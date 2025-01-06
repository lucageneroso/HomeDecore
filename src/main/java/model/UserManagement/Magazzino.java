package model.UserManagement;

import model.OrderManagement.Prodotto;

import java.util.List;

public class Magazzino {

    private List<Prodotto> prodotti;
    private Indirizzo indirizzo;

    public Magazzino(Indirizzo indirizzo, List<Prodotto> prodotti) {
        this.indirizzo = indirizzo;
        this.prodotti = prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {this.prodotti = prodotti;}
    public void setIndirizzo(Indirizzo indirizzo) {this.indirizzo = indirizzo;}

    public List<Prodotto> getProdotti() {return prodotti;}
    public Indirizzo getIndirizzo() {return indirizzo;}

    @Override
    public String toString() {
        return "Magazzino:[ "+indirizzo.toString()+", Prodotti:"+prodotti.toString()+"]";
    }
}
