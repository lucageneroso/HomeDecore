package model.ChatManagement;


import jakarta.ejb.Local;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAll", query = "SELECT m FROM Message m"),
        @NamedQuery(name = "findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
        @NamedQuery(name = "findByDate", query = "SELECT m FROM Message m WHERE m.date=:date"),
        @NamedQuery(name = "findByMessageContent", query = "SELECT m FROM Message m WHERE m.message LIKE :content"),
        @NamedQuery(name = "deleteById", query = "DELETE FROM Message m WHERE m.id = :id")
})
public class Message {
    private static final String FIND_ALL="findAll";
    private static final String FIND_BY_ID="findById";
    private static final String FIND_BY_DATE="findByDate";
    private static final String FIND_BY_CONTENT="findByMessageContent";
    private static final String DELETE_BY_ID="findById";

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
