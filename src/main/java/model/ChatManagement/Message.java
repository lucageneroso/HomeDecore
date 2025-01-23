package model.ChatManagement;


import jakarta.ejb.Local;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAll", query = "SELECT m FROM Message m"),
        @NamedQuery(name = "findByIdMess", query = "SELECT m FROM Message m WHERE m.id = :id"),
        @NamedQuery(name = "findByDate", query = "SELECT m FROM Message m WHERE m.date=:date"),

})
public class Message implements Serializable {
    public static final String FIND_ALL="findAll";
    public static final String FIND_BY_ID="findByIdMess";
    public static final String FIND_BY_DATE="findByDate";


    @Id
    private int id;
    private String message;
    private LocalDateTime date;

    public void setId(int id,String message,String date) {
        this.id = id;
        this.message=message;
        this.date=LocalDateTime.parse(date);
    }

    public int getId() {
        return id;
    }
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message=message;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date=LocalDateTime.parse(date);

    }

    @Override
    public String toString(){
        return "Messaggio{" +
                "id=" + id +
                ", corpo='" +message  + '\'' +
                ", data e ora ='" + date + '\'' +
                '}';
    }


}
