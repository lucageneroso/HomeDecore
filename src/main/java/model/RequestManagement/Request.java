package model.RequestManagement;

import enumerativeTypes.StatoRichiesta;
import jakarta.persistence.*;
import model.UserManagement.Magazziniere;

import java.io.Serializable;
import java.time.LocalDateTime;
import model.UserManagement.Utente;

@Entity
@NamedQueries({
        @NamedQuery(name = "Request.TROVA_TUTTI", query="SELECT r FROM Request r"),
        @NamedQuery(name=  "Request.TROVA_PER_ID", query = "SELECT r FROM Request r WHERE r.id= :id"),

        @NamedQuery(name=  "Request.TROVA_PER_MAGAZZINIERE", query = "SELECT r FROM Request r WHERE r.magazziniereID= :magazziniereID"),
        @NamedQuery(name=  "Request.TROVA_PER_DESTINATARIO", query = "SELECT r FROM Request r WHERE r.destinatarioID= :destinatarioID"),

        @NamedQuery(name = "Request.TROVA_PER_DATA", query = "SELECT r FROM Request r WHERE r.dataOra >= :dataStart AND r.dataOra < :dataEnd"),
        @NamedQuery(name = "Request.TROVA_PRIMA_DI_DATA", query = "SELECT r FROM Request r WHERE r.dataOra < :data"),
        @NamedQuery(name = "Request.TROVA_DOPO_DI_DATA", query = "SELECT r FROM Request r WHERE r.dataOra > :data")
})
public class Request implements Serializable {

    @Id @GeneratedValue private int id;

    private Long magazziniereID;      // Chi effettua la richiesta
    private Long destinatarioID;      // Destinatario della richiesta

    private LocalDateTime dataOra;    // Data e ora della richiesta

    private StatoRichiesta stato;

    private String message;

    public Request() {}

    public Request(Long magazziniereID, Long destinatarioID, LocalDateTime dataOra, String message) {
        this.magazziniereID = magazziniereID;
        this.destinatarioID = destinatarioID;
        this.dataOra = dataOra;
        this.stato = StatoRichiesta.NON_ACCETTATO;
        this.message = message;
    }

    public int getId() { return id;}
    public void setId(int id) { this.id = id;}

    public Long getMagazziniereID() { return magazziniereID; }
    public void setMagazziniereID(Long magazziniereID) { this.magazziniereID = magazziniereID; }

    public Long getDestinatarioID() { return destinatarioID; }
    public void setDestinatarioID(Long destinatarioID) { this.destinatarioID = destinatarioID; }

    public LocalDateTime getDataOra() { return dataOra; }
    public void setDataOra(LocalDateTime dataOra) { this.dataOra = dataOra;}

    public StatoRichiesta getStato() { return stato; }
    public void accettaRichiesta() { this.stato = StatoRichiesta.ACCETTATO; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public String toString() {
        return "Request{" +
                " magazziniere=" + magazziniereID +
                ", destinatario=" + destinatarioID +
                ", dataOra=" + dataOra +
                ", messaggio=" + message +
                ", stato=" + stato +
                '}';
    }


}
