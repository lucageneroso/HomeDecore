package model.RequestManagement;

import jakarta.persistence.*;
import model.UserManagement.GestoreOrdini;
import model.UserManagement.Magazziniere;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class OrderRequest extends Request implements Serializable {

    private Long ordineID;

    public OrderRequest() {}

    /*
    public OrderRequest(Magazziniere magazziniere, GestoreOrdini gestoreOrdini, LocalDateTime dataOra, int ordineID) {
        super(magazziniere, gestoreOrdini, dataOra);
        this.ordineID = ordineID;
    }
     */

    public OrderRequest(Long magazziniereID, Long gestoreOrdiniID, LocalDateTime dataOra, Long ordineID, String message ) {
        super(magazziniereID, gestoreOrdiniID, dataOra, message);
        this.ordineID = ordineID;
    }

    public Long getOrdineID() {
        return ordineID;
    }
    public void setOrdineID(Long ordineID) {
        this.ordineID = ordineID;
    }

    @Override
    public String toString() {
        return "OrderRequest: { "+super.toString() + ", OrdineID='" + ordineID + '\'' + '}';
    }

}
