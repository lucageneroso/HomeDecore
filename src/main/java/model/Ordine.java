package model;

import enumerativeTypes.Stato;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;


@NamedQueries({
        @NamedQuery(name="Ordine.TROVA_TUTTI", query="SELECT o FROM Ordine o"),
        @NamedQuery(name="Ordine.TROVA_PER_ID", query="SELECT o FROM Ordine o WHERE o.id = :id "),
        @NamedQuery(name="Ordine.TROVA_PER_UTENTE", query="SELECT o FROM Ordine o WHERE o.userId = :userId"),
        @NamedQuery(name="Ordine.TROVA_PER_DATA", query="SELECT o FROM Ordine o WHERE o.date =:date "),
        @NamedQuery(name="Ordine.TROVA_PER_STATO", query="SELECT o FROM Ordine o WHERE o.stato =:stato "),
        @NamedQuery(name="Ordine.TROVA_PER_TOTALE", query="SELECT o FROM Ordine o WHERE o.totale = :totale")
})
@Entity
public class Ordine implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private double totale=0.0;
    private Long userId;
    private Date date;
    private Stato stato;

    public Ordine() {}
    public Ordine(Long userId, Date date, Stato stato) {
        this.totale = totale;
        this.userId = userId;
        this.date = date;
        this.stato = stato;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getTotale() {
        return totale;
    }
    public void setTotale(double totale) {
        this.totale = totale;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Date getDate() {
        return date;
    }

    public Stato getStato() {
        return stato;
    }
    public void setStato(Stato stato) {
        this.stato = stato;
    }

}
