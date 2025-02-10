package model.OrderManagement;

import enumerativeTypes.Categoria;
import jakarta.persistence.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;


@NamedQueries({
        @NamedQuery(name="TROVA_TUTTI", query="SELECT p FROM Prodotto p"),
        @NamedQuery(name="TROVA_IN_CATALOGO", query="SELECT p FROM Prodotto p WHERE p.inCatalogo=true"),
        @NamedQuery(name="TROVA_IN_MAGAZZINO", query="SELECT p FROM Prodotto p WHERE p.inMagazzino=true"),
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
    @Lob private byte[] image;
    private Categoria categoria;

    private int disponibilita;
    private boolean inCatalogo;
    private boolean inMagazzino;


    //@Column(name="fornitore_id")
    //private FornitoreDTO fornitore;
    private Long fornitore;


    public Prodotto() {}


    public Prodotto(String nome, String descrizione, Double prezzo, byte[] image,
                    Categoria categoria, int disponibilita, boolean inCatalogo,
                    boolean inMagazzino) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.image = image; // SALVATA COME BYTE[]
        this.categoria = categoria;
        this.disponibilita = disponibilita;
        this.inCatalogo = inCatalogo;
        this.inMagazzino = inMagazzino;
    }

    public Prodotto(String nome, String descrizione, Double prezzo, byte[] image,
                    Categoria categoria, int disponibilita, boolean inCatalogo,
                    boolean inMagazzino, Long fornitore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.image = image; // SALVATA COME BYTE[]
        this.categoria = categoria;
        this.disponibilita = disponibilita;
        this.inCatalogo = inCatalogo;
        this.inMagazzino = inMagazzino;
        this.fornitore = fornitore;
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

    public boolean isInMagazzino() {return inMagazzino;}
    public void setInMagazzino(boolean inMagazzino) {this.inMagazzino = inMagazzino;}

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


    /*
    public ImageIcon getImageIcon() {
        if (image != null && image.length > 0) {  // Controlla che image non sia null o vuoto
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(image);
                Image img = ImageIO.read(bis);
                return img != null ? new ImageIcon(img) : null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    */

    public byte[] getImageBytes() {
        return this.image; // Assumendo che 'image' sia un campo di tipo byte[]
    }



    public void setImageFromIcon(ImageIcon icon) {
        if (icon != null) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write((ImageIO.read((File) icon.getImage().getSource())), "png", bos);
                this.image = bos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                ", inMagazzino=" + inMagazzino +
                ", fornitore=" + (fornitore != null ? fornitore.toString() : "Nessun fornitore") +
                '}';
    }

}
