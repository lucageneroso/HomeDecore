package model;

import java.util.ArrayList;

public class Prodotto {
	int id;
	int Quantita;
	String Nome;
	String Descrizione;
	double Prezzo;
	String categoria;
	 double sconto;
	
	byte [] img;
	ArrayList<immagine> allimg;
	
	
	public Prodotto() {
		super();
	}
	public Prodotto(int id, String nome, double prezzo) {
		super();
		this.id = id;
		Nome = nome;
		Prezzo = prezzo;
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	
	
	
	public int getQuantita() {
		return Quantita;
	}
	public void setQuantita(int quantita) {
		Quantita= quantita;
	}
	public String getDescrizione() {
		return Descrizione;
	}
	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public double getPrezzo() {
		return this.Prezzo;
		
	}
	public void setPrezzo(double prezzo) {
		Prezzo = prezzo;
	}
	public byte[] c() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public ArrayList<immagine> getAllimg() {
		return allimg;
	}
	public void setAllimg(ArrayList<immagine> allimg) {
		this.allimg = allimg;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

}