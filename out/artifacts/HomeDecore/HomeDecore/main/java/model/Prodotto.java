package model;

import enumerativeTypes.Categoria;
import jakarta.persistence.*;

import javax.swing.*;
import java.io.Serializable;


@NamedQueries({
        @NamedQuery(name="TROVA_TUTTI", query="SELECT p FROM Prodotto p"),
        @NamedQuery(name="TROVA_PER_ID", query="SELECT p FROM Prodotto p WHERE p.id = :ID "),
        @NamedQuery(name="TROVA_PER_PREZZO_MINORE", query="SELECT p FROM Prodotto p WHERE p.prezzo <= :prezzo"),
        @NamedQuery(name="TROVA_PER_PREZZO_MAGGIORE", query="SELECT p FROM Prodotto p WHERE p.prezzo >= :prezzo"),
        @NamedQuery(name="TROVA_PER_CATEGORIA", query="SELECT p FROM Prodotto p WHERE p.categoria= :categoria"),
        @NamedQuery(name="TROVA_PER_NOME", query="SELECT p FROM Prodotto p WHERE p.nome= :nome")
})
@Entity
public class Prodotto implements Serializable {

    public static final String TROVA_PER_ID= "Product.findById";
    public static final String TROVA_PER_PREZZO_MINORE= "Product.findMinusPrize";
    public static final String TROVA_PER_CATEGORIA= "Product.findCategoria";
    public static final String TROVA_PER_NOME= "Product.findByNome";
    public static final String TROVA_PER_PREZZO_MAGGIORE= "Product.findMajorPrize";
    public static final String TROVA_TUTTI= "Product.findTutti";

    @Id @GeneratedValue
    private int id;
    private String nome;
    private String descrizione;
    private Double prezzo;
    //private ImageIcon image;
    private Categoria categoria;

    public Prodotto() {}
    public Prodotto(String nome, String descrizione, Double prezzo, /*ImageIcon image,*/ Categoria categoria) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        //this.image = image;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }
    //public ImageIcon getImage() {
        return image;
    }
    //public void setImage(ImageIcon image) {
        this.image = image;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
