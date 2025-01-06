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
import model.OrderManagement.Prodotto;
import model.UserManagement.Utente;

@Singleton
@Startup
@DataSourceDefinition(
        name = "java:app/jdbc/HomeDecoreDS",
        className = "com.mysql.cj.jdbc.Driver",
        url = "jdbc:mysql://localhost:3306/HomeDecoreDB",
        user = "root",
        password = "root"
)
@LocalBean
public class DatabasePopulator {
    @Inject
    private EntityManager em;

    Prodotto p1=new Prodotto("Panpers", "carta igienica", 10.0, Categoria.BAGNO, 300, false);
    Prodotto p2=new Prodotto("Mario", "persona", 10.0, Categoria.SOGGIORNO, 200, true);
    Prodotto p3=new Prodotto("Scottex", "carta asciugante", 10.0, Categoria.CUCINA, 150, true);
    Prodotto p4=new Prodotto("Mastro Lindo", "detersivo", 10.0, Categoria.CUCINA, 270, true);

    Utente cliente = new Utente("Pietro", "Fasolino", "p.fasolino@gmail.com", "pietro", "password", Ruolo.CLIENTE);



    // Creazione di un admin
    /*Admin admin = new Admin();
        admin.setNome("Admin User");
        admin.setEmail("admin@example.com");
        admin.setPassword("admin123");
        admin.setPermessi("GESTIONE_COMPLETA");
        admin.setRuolo("ADMIN"); */
    // record

    @PostConstruct
    public void populateDB(){
        System.out.println("HO INIZIATO IL POPOLAMENTOOOOOOOOO!! \n");
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.persist(cliente);
    }

    @PreDestroy
    public void clearDB(){
        em.remove(p1);
        em.remove(p2);
        em.remove(p3);
        em.remove(p4);
        em.remove(cliente);
        em.clear();
    }
}