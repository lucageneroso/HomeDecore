package model.ChatManagement;


import jakarta.persistence.*;
import model.UserManagement.UserReceiver;
import model.UserManagement.UserSender;

@Entity
public class Chat {
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
