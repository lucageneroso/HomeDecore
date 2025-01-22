package model.RequestManagement;

import jakarta.persistence.*;
import model.UserManagement.GestoreOrdini;
import model.UserManagement.Magazziniere;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class OrderRequest extends Request implements Serializable {

    private int ordineID;

    public OrderRequest() {}

    /*
    public OrderRequest(Magazziniere magazziniere, GestoreOrdini gestoreOrdini, LocalDateTime dataOra, int ordineID) {
        super(magazziniere, gestoreOrdini, dataOra);
        this.ordineID = ordineID;
    }
     */

    public OrderRequest(int magazziniereID, int gestoreOrdiniID, LocalDateTime dataOra) {
        super(magazziniereID, gestoreOrdiniID, dataOra);
        this.ordineID = gestoreOrdiniID;
    }

    public int getOrdineID() {
        return ordineID;
    }
    public void setOrdineID(int ordineID) {
        this.ordineID = ordineID;
    }

    @Override
    public String toString() {
        return super.toString() + ", OrdineID='" + ordineID + '\'' + '}';
    }

}
