package model.UserManagement;

import java.io.Serializable;

public class Indirizzo implements Serializable {

    private String stato;
    private String provincia;
    private String citta;
    private String via;
    private int numCivico;
    private int CAP;

    public Indirizzo(String stato, String provincia, String citta, String via, int numCivico, int CAP) {
        this.stato = stato;
        this.provincia = provincia;
        this.citta = citta;
        this.via = via;
        this.numCivico = numCivico;
        this.CAP = CAP;
    }

    public void setCitta(String citta) { this.citta = citta; }
    public void setProvincia(String provincia) {this.provincia = provincia;}
    public void setStato(String stato) {this.stato = stato;}
    public void setNumCivico(int numCivico) {this.numCivico = numCivico;}
    public void setCAP(int CAP) { this.CAP = CAP;}
    public void setVia(String via) {this.via = via;}

    public String getStato() {return this.stato;}
    public String getProvincia() {return this.provincia;}
    public String getCitta() {return this.citta;}
    public int getNumCivico() {return this.numCivico;}
    public int getCAP() {return this.CAP;}
    public String getVia() {return this.via;}

    @Override
    public String toString() {
        return "Indirizzo: ["+this.via+" n."+this.numCivico+", CAP:"+this.CAP+" "+this.citta+" "+this.provincia+" "+this.stato+"]";
    }



}
