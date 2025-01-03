package util;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Prodotto;
import  enumerativeTypes.Categoria;

import javax.swing.*;

@Singleton
@Startup
@DataSourceDefinition(
        name ="java:app/jdbc/HomeDecoreDS",
        className ="com.mysql.cj.jdbc.Driver",
        url="jdbc:mysql://localhost:3306/ingrosso",
        user ="root",
        password ="root"
)
@LocalBean
public class DatabasePopulator {


    @PersistenceContext(unitName = "HomeDecorePU")
    private EntityManager em;

    ImageIcon icon = ImageUtil.loadImageIcon("bagno.jpeg");

    Prodotto p1=new Prodotto("Panpers", "carta igienica", 10.0, icon ,Categoria.BAGNO);
    Prodotto p2=new Prodotto("Mario", "persona", 10.0, icon,  Categoria.SOGGIORNO);
    Prodotto p3=new Prodotto("Scottex", "carta asciugante", 10.0, icon, Categoria.CUCINA);
    Prodotto p4=new Prodotto("Mastro Lindo", "detersivo", 10.0, icon, Categoria.CUCINA);

    // record

    @PostConstruct
    public void populateDB(){
        System.out.println("HO INIZIATO IL POPOLAMENTOOOOOOOOO!! \n");
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
    }

    @PreDestroy
    public void clearDB(){
        em.remove(p1);
        em.remove(p2);
        em.remove(p3);
        em.remove(p4);
        em.clear();
    }
}
