package remoteInterfaces;

import model.User.Utente;

import java.util.List;

public interface UserServiceRemote {
    void addUser(Utente utente);
    Utente findUserById(int id);
    Utente findUserByEmail(String email);
    void updateUser(Utente utente);
    void removeUser(Utente utente);
    Utente findUserByUsername(String username);
    boolean isLogged(Utente utente);
    List<Utente> findAllUsers();
}
