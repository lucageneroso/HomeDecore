package model.Review;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name="TROVA_TUTTE", query="SELECT r FROM Recensione r"),
        @NamedQuery(name="TROVA_PER_ID", query="SELECT r FROM Recensione r WHERE r.ID= :ID"),
        @NamedQuery(name="TROVA_PER_RATING", query="SELECT r FROM Recensione r WHERE r.rating= :rating"),
        @NamedQuery(name="TROVA_PER_CLIENTE", query="SELECT r FROM Recensione r WHERE r.userID= :userID"),
        @NamedQuery(name="TROVA_PER_PRODOTTO", query="SELECT r FROM Recensione r WHERE r.productID= :productID"),
        @NamedQuery(name="TROVA_PER_DATA", query="SELECT r FROM Recensione r WHERE r.date= :date")
})

@Entity
public class Recensione extends ReviewComponent implements Serializable{
    @Id @GeneratedValue
    private int ID;


    private double rating;
    private int userID;
    private int productID;
    private String commento;
    private Date date;

    public Recensione(){}
    public Recensione(double rating, int userID, int productID, String commento, Date date) {
        this.rating = rating;
        this.userID = userID;
        this.productID = productID;
        this.commento = commento;
        this.date = date;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public List<Recensione> showReview(){
        List<Recensione> group= new ArrayList<>();
        group.add(this);
        return group;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public String getCommento() {
        return commento;
    }
    public void setCommento(String commento) {
        this.commento = commento;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }


}

