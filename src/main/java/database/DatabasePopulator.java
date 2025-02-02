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
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;
import model.OrderManagement.Prodotto;
import model.UserManagement.*;

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
        password = "root",
        properties={"connectionAttributes=;create=true"}
)
@LocalBean
public class DatabasePopulator {

    @Inject private EntityManager em;

    public void createProdotto(Prodotto prodotto, Fornitore fornitore) {
        fornitore.addProdotto(prodotto.getId()); // Aggiungi i prodotti alla lista del fornitore
        prodotto.setFornitore(fornitore.toDTO());
        em.merge(prodotto);
        em.merge(fornitore);
    }

    // Creazione di un admin
    /*Admin admin = new Admin();
        admin.setNome("Admin User");
        admin.setEmail("admin@example.com");
        admin.setPassword("admin123");
        admin.setPermessi("GESTIONE_COMPLETA");
        admin.setRuolo("ADMIN"); */
    // record


    Fornitore fornitore1= new Fornitore("Mario", "Rossi", "mario.rossi@example.com", "mrossi", "abc");
    //Fornitore fornitore1=new Fornitore(utenteFornitore1);

    Prodotto p1=new Prodotto("Panpers", "carta igienica", 10.0, Categoria.BAGNO, 300, false);
    Prodotto p2=new Prodotto("Mario", "persona", 10.0, Categoria.SOGGIORNO, 200, true);
    Prodotto p3=new Prodotto("Scottex", "carta asciugante", 10.0, Categoria.CUCINA, 150, true);
    Prodotto p4=new Prodotto("Mastro Lindo", "detersivo", 10.0, Categoria.CUCINA, 270, true);

    Indirizzo ind= new Indirizzo("Italia","Salerno","Sarno","Via Vesuvio", 4, 8006);
    Cliente cliente = new Cliente("Pietro", "Fasolino", "p.fasolino@gmail.com", "pietro", "password", ind);
    //Cliente cliente = new




    @PostConstruct
    public void populateDB(){
        System.out.println("HO INIZIATO IL POPOLAMENTOOOOOOOOO!! \n");

        em.createQuery("DELETE FROM Prodotto p").executeUpdate();
        em.createQuery("DELETE FROM Fornitore f").executeUpdate();


        em.persist(fornitore1);
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.persist(cliente);

        em.flush(); // Sincronizza con il DB

        createProdotto(p1, fornitore1);
        createProdotto(p2, fornitore1);
        createProdotto(p3, fornitore1);
        createProdotto(p4, fornitore1);

        em.flush();

        System.out.println("Popolamento completato");


    }

    @PreDestroy
    public void clearDB(){
        em.remove(p1);
        em.remove(p2);
        em.remove(p3);
        em.remove(p4);
        em.remove(cliente);
        em.remove(fornitore1);
        em.clear();
    }

}