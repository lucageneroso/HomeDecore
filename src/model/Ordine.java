package model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ordine {
    private int id_ORDINE;
    private String indirizzo;
    private int IVA_cliente; 
    private Date dataOrdine;
    private double totale;
    private String stato;
    private int numeroProdotti;
    private String EmailCliente;
    private String citta;
    private int CAP;
    private String provincia;
    
    public void setCitta(String x) {
    	this.citta=x;
    }
    public String getCitta() {
    	return this.citta;
    }
    public void setCAP(int x) {
    	this.CAP=x;
    }
    public int getCAP() {
    	return this.CAP;
    }
    public void setProvincia(String x) {
    	this.provincia=x;
    }
    public String getProvincia() {
    	return this.provincia;
    }
    public String getEmailCliente() {
    	return this.EmailCliente;
    }
    public void setEmailCliente(String x) {
    	this.EmailCliente=x;
    }
    
    public int getNumeroProdotti() {
    	return this.numeroProdotti;
    }
    public void setNumeroProdotti(int x) {
    	this.numeroProdotti=x;
    }
    
   public int getIVA_cliente() {
	   return this.IVA_cliente;
   }
   public void setIVA_cliente(int IVA) {
	   this.IVA_cliente=IVA;
   }

    
    public int getNumeroOrdine() {
        return id_ORDINE;
    }
    
    public void setNumeroOrdine(int numeroOrdine) {
        this.id_ORDINE = numeroOrdine;
    }
    
    
    
    public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public Date getData() {
        return dataOrdine;
    }
    
    public void setData(Date data) {
        this.dataOrdine = data;
    }
    
    public double getTotale() {
        return totale;
    }
    
    public void setTotale(double totale) {
        this.totale = totale;
    }
    
    public String getStato() {
        return stato;
    }
    
    public void setStato(String stato) {
        this.stato = stato;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormattata = dateFormat.format(dataOrdine);
        return "Numero ordine: " + id_ORDINE +
               ", Data: " + dataFormattata +
               ", Totale: " + totale +
               ", Stato: " + stato;
    }

}