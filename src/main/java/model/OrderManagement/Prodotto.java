package model.OrderManagement;

import enumerativeTypes.Categoria;
import jakarta.persistence.*;
import model.UserManagement.FornitoreDTO;

import java.io.Serializable;


@NamedQueries({
        @NamedQuery(name="TROVA_TUTTI", query="SELECT p FROM Prodotto p"),
        @NamedQuery(name="TROVA_PER_IDENT", query="SELECT p FROM Prodotto p WHERE p.id = :ID "),
        @NamedQuery(name="TROVA_PER_PREZZO_MINORE", query="SELECT p FROM Prodotto p WHERE p.prezzo <= :prezzo"),
        @NamedQuery(name="TROVA_PER_PREZZO_MAGGIORE", query="SELECT p FROM Prodotto p WHERE p.prezzo >= :prezzo"),
        @NamedQuery(name="TROVA_PER_CATEGORIA", query="SELECT p FROM Prodotto p WHERE p.categoria= :categoria"),
        @NamedQuery(name="TROVA_PER_NOME", query="SELECT p FROM Prodotto p WHERE p.nome= :nome"),

        @NamedQuery(name = "TROVA_PER_FORNITORE", query = "SELECT p FROM Prodotto p WHERE p.fornitore = :fornitore")
})
@Entity
public class Prodotto implements Serializable {

    public static final String TROVA_PER_IDENT= "TROVA_PER_IDENT";
    public static final String TROVA_PER_PREZZO_MINORE= "TROVA_PER_PREZZO_MINORE";
    public static final String TROVA_PER_CATEGORIA= "TROVA_PER_CATEGORIA";
    public static final String TROVA_PER_NOME= "TROVA_PER_NOME";
    public static final String TROVA_PER_PREZZO_MAGGIORE= "TROVA_PER_PREZZO_MAGGIORE";
    public static final String TROVA_TUTTI= "TROVA_TUTTI";

    public static final String TROVA_PER_FORNITORE= "Product.findFornitore";

    @Id @GeneratedValue
    private int id;
    private String nome;
    private String descrizione;
    private Double prezzo;
    //private ImageIcon image;
    private Categoria categoria;

    private int disponibilita;
    private boolean inCatalogo;


    @Column(name="fornitore_id")
    private Long fornitore;
    /*
    @ManyToOne
    @JoinColumn(name = "fornitore_id", referencedColumnName = "id")
    private Fornitore fornitore; */


    public Prodotto() {}

    public Prodotto(String nome, String descrizione, Double prezzo, /*ImageIcon image,*/
                    Categoria categoria,int disponibilita,boolean inCatalogo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        //this.image = image;
        this.categoria = categoria;
        this.disponibilita = disponibilita;
        this.inCatalogo = inCatalogo;
    }


    public Prodotto(String nome, String descrizione, Double prezzo, //ImageIcon image,
                    Categoria categoria,int disponibilita,boolean inCatalogo,Long fornitore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        //this.image = image;
        this.categoria = categoria;
        this.disponibilita = disponibilita;
        this.inCatalogo = inCatalogo;
        this.fornitore=fornitore;
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
    /*public ImageIcon getImage() {
        return image;
    }
    public void setImage(ImageIcon image) {
        this.image = image;
    }*/
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
    public int getDisponibilita() {return disponibilita;}
    public void setDisponibilita(int disponibilita) {this.disponibilita = disponibilita;}

    public boolean isInCatalogo() {return inCatalogo;}
    public void setInCatalogo(boolean inCatalogo) {this.inCatalogo = inCatalogo;}
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    /*
    public Fornitore getFornitore() {return fornitore;}
    public void setFornitore(Fornitore fornitore) {
        //System.out.println("Siamo qui!");
        //System.out.println(fornitore.toString());
        this.fornitore = fornitore;
        //System.out.println(this.fornitore);
    }*/

    public Long getFornitore() {
        return fornitore;
    }
    public void setFornitore(Long fornitore) {
        this.fornitore = fornitore;
    }



    @Override
    public String toString() {
        //System.out.println(this.fornitore);
        return "Prodotto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", categoria=" + categoria +
                ", disponibilita=" + disponibilita +
                ", inCatalogo=" + inCatalogo +
            /*    ", fornitore=" + (fornitore != null ? fornitore.toString() : "Nessun fornitore") +*/
                '}';
    }

}
