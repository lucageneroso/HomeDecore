package service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.UserManagement.Utente;
import remoteInterfaces.UserServiceRemote;

import java.util.List;

@Stateless(name = "UserService")//, mappedName = "java:app/HomeDecore/UserService")
public class UserService implements UserServiceRemote {
    public UserService() {}

    @Inject
    private EntityManager em;

    //CRUD
    public void addUser(Utente utente) {
        em.persist(utente);
    }
    public void removeUser(Utente utente) {
        em.remove(em.merge(utente));
    }
    public void updateUser(Utente utente) {
        em.merge(utente);
    }


    public Utente findUserById(int id) {
        return em.find(Utente.class, id);
    }

    public Utente findUserByUsername(String username) {
        TypedQuery<Utente> query=em.createNamedQuery("Utente.TROVA_PER_USERNAME", Utente.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
    public List<Utente> findAllUsers() {
        TypedQuery<Utente> query=em.createNamedQuery("Utente.TROVA_TUTTI", Utente.class);
        return query.getResultList();
    }

    public boolean isLogged(Utente utente) {
        //return findUserByUsername(utente.getUsername())!=null;
        return findUserByEmail(utente.getEmail()) != null;
    }

    public boolean isAdmin(Utente utente) {
        Utente u = findUserByEmail(utente.getEmail());
        if(u.getRuolo().equals("admin"))
            return true;
        else
            return false;
    }

    public Utente findUserByEmail(String email) {
        TypedQuery<Utente> query=em.createNamedQuery("Utente.TROVA_PER_EMAIL", Utente.class);
        query.setParameter("email", email);
        System.out.println("Aui");
        return query.getSingleResult();
    }
}
