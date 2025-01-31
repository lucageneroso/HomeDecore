package database;

import enumerativeTypes.Categoria;
import enumerativeTypes.Ruolo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.OrderManagement.Prodotto;
import model.UserManagement.Fornitore;
import model.UserManagement.Utente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
@Startup
@DataSourceDefinition(
        name = "jdbc/HomeDecoreDS",
        className = "com.mysql.cj.jdbc.Driver",
        url = "jdbc:mysql://localhost:3306/HomeDecoreDB",
        user = "root",
        password = "root"
)
@LocalBean
public class DatabasePopulator {

    @Inject
    private EntityManager em;



    // Creazione di un admin
    /*Admin admin = new Admin();
        admin.setNome("Admin User");
        admin.setEmail("admin@example.com");
        admin.setPassword("admin123");
        admin.setPermessi("GESTIONE_COMPLETA");
        admin.setRuolo("ADMIN"); */
    // record


    //Utente utenteFornitore1=new Utente("Mario", "Rossi", "mario.rossi@example.com", "mrossi", "abc", Ruolo.FORNITORE);
   // Fornitore fornitore1=new Fornitore(utenteFornitore1);

    Prodotto p1=new Prodotto("Panpers", "carta igienica", 10.0, Categoria.BAGNO, 300, false);
    Prodotto p2=new Prodotto("Mario", "persona", 10.0, Categoria.SOGGIORNO, 200, true);
    Prodotto p3=new Prodotto("Scottex", "carta asciugante", 10.0, Categoria.CUCINA, 150, true);
    Prodotto p4=new Prodotto("Mastro Lindo", "detersivo", 10.0, Categoria.CUCINA, 270, true);


    Utente cliente = new Utente("Pietro", "Fasolino", "p.fasolino@gmail.com", "pietro", "password", Ruolo.CLIENTE);




    @PostConstruct
    @Transactional
    public void populateDB(){

        System.out.println("HO INIZIATO IL POPOLAMENTOOOOOOOOO!! \n");

       /* em.createQuery("DELETE FROM Prodotto p").executeUpdate();
        em.createQuery("DELETE FROM Fornitore f").executeUpdate();

        // Aggiungi i prodotti alla lista del fornitore
        fornitore1.setProdottiForniti(new ArrayList<>(Arrays.asList(p1, p2, p3, p4)));

       // em.persist(fornitore1); */
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.persist(cliente);

        System.out.println("Popolamento completato");

    }

    @PreDestroy
    public void clearDB(){
        System.out.println("Pulizia del database in corso...");

        em.createQuery("DELETE FROM Prodotto").executeUpdate();
        em.createQuery("DELETE FROM Utente").executeUpdate();
        em.createQuery("DELETE FROM Cart").executeUpdate();

        em.flush();
        em.clear();

        System.out.println("Database pulito con successo.");
    }
}