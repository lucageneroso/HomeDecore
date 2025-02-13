package remoteInterfaces;

import model.UserManagement.Utente;

import java.util.List;

public interface UserServiceRemote {
    void addUser(Utente utente);
    Utente findUserById(Long id);
    Utente findUserByEmail(String email);
    void updateUser(Utente utente);
    void removeUser(Utente utente);
    Utente findUserByUsername(String username);
    boolean isLogged(Utente utente);
    List<Utente> findAllUsers();
}
