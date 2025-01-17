package model.ChatManagement;


import jakarta.inject.Named;
import jakarta.persistence.*;
import jdk.jfr.Name;
import model.UserManagement.UserReceiver;
import model.UserManagement.UserSender;

import java.io.Serial;
import java.io.Serializable;


@Entity
@NamedQueries({
    @NamedQuery(name="findById",query="SELECT c From Chat c"),
    @NamedQuery(name="findSender",query="SELECT c From Chat c WHERE c.sender=:sender"),
    @NamedQuery(name="findReceiver",query="SELECT c FROM Chat c WHERE c.receiver=:receiver"),
    @NamedQuery(name="findByMessage",query="SELECT c FROM Chat c WHERE c.currentMessage=:currentMessage"),


        })
public class Chat implements Serializable {

    public static final String FINDBYID="findById";
    public static final String FINDBYSENDER="findSender";
    public static final String FINDBYRECEIVER="findReceiver";
    public static final String FINDBYMESSAGE="findMessage";

    @Id @GeneratedValue
    private Integer id;

    @ManyToOne
    private UserSender sender;

    @ManyToOne
    private UserReceiver receiver;
    private String currentMessage;

    public Chat(){}
    public Chat(UserSender sender,UserReceiver receiver,String currentMessage) {
        this.sender = sender;
        this.receiver = receiver;
        this.currentMessage = null;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public UserSender getSender(){
        return sender;
    }
    
    public void setSender(UserSender send){
        this.sender=send;
    }
    
    public UserReceiver getReceiver(){
        return this.receiver;
    }
    
    public String getCurrentMessage(){
        return this.currentMessage;
    }
    
    public void setCurrentMessage(String currentMessage){
        this.currentMessage=currentMessage;

    }
}
