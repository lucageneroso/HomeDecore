package model.RequestManagement;

import jakarta.persistence.*;
import model.UserManagement.Magazziniere;

import java.io.Serializable;
import java.time.LocalDateTime;
import model.UserManagement.Utente;

@Entity
@NamedQueries({
        @NamedQuery(name = "Request.TROVA_TUTTI", query="SELECT r FROM Request r"),
        @NamedQuery(name=  "Request.TROVA_PER_ID", query = "SELECT r FROM Request r WHERE r.id= :id"),
        @NamedQuery(name=  "Request.TROVA_PER_MAGAZZINIERE", query = "SELECT r FROM Request r WHERE r.magazziniere= :magazziniere"),

        @NamedQuery(name = "Request.TROVA_PER_DATA", query = "SELECT r FROM Request r WHERE DATE(r.dataOra) = :data"),
        @NamedQuery(name = "Request.TROVA_PRIMA_DI_DATA", query = "SELECT r FROM Request r WHERE r.dataOra < :data"),
        @NamedQuery(name = "Request.TROVA_DOPO_DI_DATA", query = "SELECT r FROM Request r WHERE r.dataOra > :data")
})
public class Request implements Serializable {

    @Id @GeneratedValue private int id;

    /*
    @OneToOne @JoinColumn(name = "magazziniere_id")
    private Magazziniere magazziniere; // Chi effettua la richiesta

    @OneToOne @JoinColumn(name = "destinatario_id")
    private Utente destinatario;      // Destinatario della richiesta
    */

    private int magazziniereID; // Chi effettua la richiesta
    private int destinatarioID;      // Destinatario della richiesta

    private LocalDateTime dataOra;    // Data e ora della richiesta

    public Request() {}

    public Request(int magazziniereID, int destinatarioID, LocalDateTime dataOra) {
        this.magazziniereID = magazziniereID;
        this.destinatarioID = destinatarioID;
        this.dataOra = dataOra;
    }

    // Getter e setter
    /*
    public Magazziniere getMagazziniere() { return magazziniere;}
    public void setMagazziniere(Magazziniere magazziniere) { this.magazziniere = magazziniere;}

    public Utente getDestinatario() { return destinatario; }
    public void setDestinatario(Utente destinatario) { this.destinatario = destinatario; }
    */

    public int getId() { return id;}
    public void setId(int id) { this.id = id;}

    public int getMagazziniereID() { return magazziniereID; }
    public void setMagazziniereID(int magazziniereID) { this.magazziniereID = magazziniereID; }

    public LocalDateTime getDataOra() { return dataOra; }
    public void setDataOra(LocalDateTime dataOra) { this.dataOra = dataOra;}

    /*
    @Override
    public String toString() {
        return "Request{" +
                "magazziniere=" + magazziniere +
                ", destinatario=" + destinatario +
                ", dataOra=" + dataOra +
                '}';
    }

     */

    @Override
    public String toString() {
        return "Request{" +
                "magazziniere=" + magazziniereID +
                ", destinatario=" + destinatarioID +
                ", dataOra=" + dataOra +
                '}';
    }


}
