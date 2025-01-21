package remoteInterfaces;

import model.ChatManagement.Chat;
import model.ChatManagement.Message;
import model.UserManagement.UserReceiver;
import model.UserManagement.UserSender;

import java.util.List;

public interface ChatServiceRemote {
    void addChat(Chat chat);
    void removeChat(Chat chat);

    Chat findById(int id);

    List<Chat> findChatsBySender(UserSender sender);
    List<Chat> findChatsByReceiver(UserReceiver receiver);


}
