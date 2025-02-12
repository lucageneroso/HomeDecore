package model.RequestManagement;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class ProductRequest extends Request implements Serializable {

    private int prodottoRichiestoID;
    private int quantita;

    public ProductRequest(){}

    /*
    public ProductRequest(Magazziniere magazziniere, Fornitore fornitore, LocalDateTime dataOra, int prodottoRichiestoID, int quantita) {
        super(magazziniere, fornitore, dataOra);
        this.prodottoRichiestoID = prodottoRichiestoID;
        this.quantita=quantita;
    }
     */

    public ProductRequest(Long magazziniereID, Long fornitoreID, LocalDateTime dataOra, int prodottoRichiestoID, int quantita, String message) {
        super(magazziniereID, fornitoreID, dataOra, message);
        this.prodottoRichiestoID = prodottoRichiestoID;
        this.quantita=quantita;
    }

    public int getProdottoRichiestoID() {
        return prodottoRichiestoID;
    }
    public void setProdottoRichiestoID(int prodottoRichiestoID) {
        this.prodottoRichiestoID = prodottoRichiestoID;
    }

    public int getQuantita() {
        return quantita;
    }
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        return super.toString() + ", prodottoRichiestoID='" + prodottoRichiestoID + '\'' + '}';
    }

}
