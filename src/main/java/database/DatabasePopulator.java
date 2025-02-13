package database;
import Utils.ImageUtil;
import enumerativeTypes.Categoria;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import model.OrderManagement.ItemCartDTO;
import model.OrderManagement.Ordine;
import model.OrderManagement.Prodotto;
import model.RequestManagement.OrderRequest;
import model.RequestManagement.ProductRequest;
import model.UserManagement.*;

import java.time.LocalDateTime;
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
        prodotto.setFornitore(fornitore.toDTO().getFornitoreID());
        em.merge(prodotto);
        em.merge(fornitore);
    }

    public void giveOrdine(Ordine ordine, GestoreOrdini gestoreOrdini) {
        gestoreOrdini.aggiungiOrdine(ordine);
        ordine.setIdGestore(gestoreOrdini);
        em.merge(ordine);
        em.merge(gestoreOrdini);
    }

    // Creazione di un admin
    /*Admin admin = new Admin();
        admin.setNome("Admin User");
        admin.setEmail("admin@example.com");
        admin.setPassword("admin123");
        admin.setPermessi("GESTIONE_COMPLETA");
        admin.setRuolo("ADMIN"); */
    // record



    Fornitore fornitore1= new Fornitore("Mario", "Rossi", "mario.rossi@example.com", "password");



    /* Prodotti */

    //Cucina
    Prodotto p1=new Prodotto("Set di pentole antiaderenti", "Pentole e padelle con rivestimento antiaderente per una cottura uniforme e senza attaccare.", 19.99,  ImageUtil.readImageFromResources("images/cucina/pentole.jpg") ,Categoria.CUCINA, 300, true, true);
    //Prodotto p2=new Prodotto("Robot da cucina multifunzione", "Tritura, impasta e frulla con facilità grazie alle sue funzioni avanzate.", 49.99,  ImageUtil.readImageFromResources("images/cucina/robot.jpg") ,Categoria.CUCINA, 200, true, true);
    Prodotto p3=new Prodotto("Macchina per il caffè espresso", "Prepara caffè cremoso come al bar con un solo tocco.", 9.99,  ImageUtil.readImageFromResources("images/cucina/caffe.jpg") ,Categoria.CUCINA, 150, true, true);
    Prodotto p4=new Prodotto("Friggitrice ad aria", "Cucina in modo sano con meno olio e la massima croccantezza.", 29.9, ImageUtil.readImageFromResources("images/cucina/friggitrice.jpg") ,Categoria.CUCINA, 270, true, true);
    Prodotto p5=new Prodotto("Set di coltelli professionali", "Lame affilate in acciaio inox per tagli precisi e senza sforzo.", 39.99, ImageUtil.readImageFromResources("images/cucina/coltelli.jpg") ,Categoria.CUCINA, 270, true, true);
    Prodotto p6=new Prodotto("Bilancia da cucina digitale", "Misura con precisione gli ingredienti per ricette perfette.", 4.99, ImageUtil.readImageFromResources("images/cucina/bilancia.jpg") ,Categoria.CUCINA, 270, true, true);
    Prodotto p7=new Prodotto("Tagliere in legno di bambù", "Resistente, antibatterico e ideale per ogni tipo di taglio.", 9.99, ImageUtil.readImageFromResources("images/cucina/tagliere.jpg") ,Categoria.CUCINA, 270, false, true);
    Prodotto p8=new Prodotto("Impastatrice planetaria", "Perfetta per impasti morbidi e dolci fatti in casa.", 69.99, ImageUtil.readImageFromResources("images/cucina/planetaria.jpg") ,Categoria.CUCINA, 270, false, true);
    Prodotto p9=new Prodotto("Bollitore elettrico", "Riscalda l'acqua in pochi secondi per tè e tisane.", 9.99, ImageUtil.readImageFromResources("images/cucina/bollitore.jpg") ,Categoria.CUCINA, 270, false, false);
    Prodotto p10=new Prodotto("Set di contenitori ermetici", "Mantiene gli alimenti freschi più a lungo, perfetti per la dispensa.", 2.99, ImageUtil.readImageFromResources("images/cucina/contenitori.jpg") ,Categoria.CUCINA, 270, false, false);

    //Soggiorno
    //Prodotto p11= new Prodotto("Divano modulare in tessuto","Comodo e personalizzabile, adatto a qualsiasi spazio.",89.99,ImageUtil.readImageFromResources("images/soggiorno/libreria.jpg"),Categoria.SOGGIORNO, 270, true, true );
    //Prodotto p12= new Prodotto("Libreria moderna in legno","Design elegante con ripiani spaziosi per libri e decorazioni.",59.99,ImageUtil.readImageFromResources("images/soggiorno/libreria.jpg"),Categoria.SOGGIORNO, 270, true, true );
    Prodotto p13= new Prodotto("Lampada da terra LED dimmerabile","Illumina l’ambiente con intensità regolabile e design minimal.",9.99,ImageUtil.readImageFromResources("images/soggiorno/led.jpg"),Categoria.SOGGIORNO, 270, true, true );
    Prodotto p14= new Prodotto("Tavolino da salotto in vetro","Raffinato ed elegante, perfetto per un soggiorno moderno.",24.99,ImageUtil.readImageFromResources("images/soggiorno/tavolino.jpg"),Categoria.SOGGIORNO, 270, true, true );
    Prodotto p15= new Prodotto("Poltrona reclinabile ergonomica","Comfort assoluto per lettura e relax.",19.99,ImageUtil.readImageFromResources("images/soggiorno/tavolino.jpg"),Categoria.SOGGIORNO, 270, true, true );
    Prodotto p16= new Prodotto("Porta TV con cassetti","Funzionale e spazioso, ideale per l’organizzazione del soggiorno.",19.99,ImageUtil.readImageFromResources("images/soggiorno/portaTV.jpg"),Categoria.SOGGIORNO, 270, true, true );
    Prodotto p17= new Prodotto("Tappeto in fibra naturale","Morbido e resistente, dona calore all’ambiente.",4.99,ImageUtil.readImageFromResources("images/soggiorno/tappeto.jpg"),Categoria.SOGGIORNO, 270, false, true );
    Prodotto p18= new Prodotto("Diffusore di aromi smart","Profuma la casa con oli essenziali regolabili via app.",4.99,ImageUtil.readImageFromResources("images/soggiorno/diffusore.jpg"),Categoria.SOGGIORNO, 270, false, true );
    Prodotto p19= new Prodotto("Specchio decorativo da parete","Aggiunge luce e profondità agli spazi.",14.99,ImageUtil.readImageFromResources("images/soggiorno/specchio.jpg"),Categoria.SOGGIORNO, 270, false, false );
    //Prodotto p20= new Prodotto("Scaffale portaoggetti industrial","Stile moderno con ripiani in legno e struttura in metallo.",9.99,ImageUtil.readImageFromResources("images/soggiorno/scaffale.jpg"),Categoria.SOGGIORNO, 270, false, false );

    //Bagno
    //Prodotto p21= new Prodotto("Set di asciugamani in cotone egiziano","Morbidi, assorbenti e delicati sulla pelle.",9.99,ImageUtil.readImageFromResources("images/bagno/asciugamani.jpg"),Categoria.BAGNO, 270, true, true );
    Prodotto p22= new Prodotto("Doccia a soffione con LED","Getto regolabile e illuminazione colorata per un’esperienza rilassante.",9.99,ImageUtil.readImageFromResources("images/bagno/doccia.jpg"),Categoria.BAGNO, 270, true, true );
    Prodotto p23= new Prodotto("Mobiletto sospeso con specchio","Pratico e salvaspazio, con vano portaoggetti nascosto.",19.99,ImageUtil.readImageFromResources("images/bagno/mobile.jpg"),Categoria.BAGNO, 270, true, true );
    Prodotto p24= new Prodotto("Tappetino antiscivolo per doccia","Sicurezza e comfort con materiale drenante.",4.99,ImageUtil.readImageFromResources("images/bagno/tappeto.jpg"),Categoria.BAGNO, 270, true, true );
    Prodotto p25= new Prodotto("Dispenser automatico di sapone","Igienico e moderno, con sensore di movimento.",2.99,ImageUtil.readImageFromResources("images/bagno/tappeto.jpg"),Categoria.BAGNO, 270, true, true );
    Prodotto p26= new Prodotto("Portasciugamani riscaldato","Mantiene gli asciugamani caldi e asciutti.",14.99,ImageUtil.readImageFromResources("images/bagno/doccia.jpg"),Categoria.BAGNO, 270, true, true );
    Prodotto p27= new Prodotto("Organizer per trucchi e cosmetici","Struttura trasparente con scomparti pratici.",6.99,ImageUtil.readImageFromResources("images/bagno/trucchi.jpg"),Categoria.BAGNO, 270, false, true );
    Prodotto p28= new Prodotto("Set di accessori in ceramica","Porta sapone, spazzolini e dispenser dal design coordinato.",14.99,ImageUtil.readImageFromResources("images/bagno/ceramica.jpg"),Categoria.BAGNO, 270, false, true );
    Prodotto p29= new Prodotto("Cesto portabiancheria pieghevole","Capiente, leggero e facile da riporre.",4.99,ImageUtil.readImageFromResources("images/bagno/cesto.jpg"),Categoria.BAGNO, 270, false, false );
    Prodotto p30= new Prodotto("Piantina da bagno purificante","Assorbe umidità e dona un tocco di verde naturale.",1.99,ImageUtil.readImageFromResources("images/bagno/pianta.jpg"),Categoria.BAGNO, 270, false, false );

    //Giardino
    //Prodotto p31= new Prodotto("Set di sedie e tavolo da esterno","Arredo elegante e resistente alle intemperie, perfetto per rilassarsi all’aperto.",14.99,ImageUtil.readImageFromResources("images/giardino/set.jpg"),Categoria.GIARDINO, 270, true, true );
    Prodotto p32= new Prodotto("Barbecue a carbone con coperchio","Ideale per grigliate all’aperto con cottura uniforme e saporita.",29.99,ImageUtil.readImageFromResources("images/giardino/barbecue.png"),Categoria.GIARDINO, 270, true, true );
    Prodotto p33= new Prodotto("Fontana da giardino a energia solare","Aggiunge un tocco rilassante con il suono dell’acqua che scorre.",24.99,ImageUtil.readImageFromResources("images/giardino/fontana.jpg"),Categoria.GIARDINO, 270, true, true );
    //Prodotto p34= new Prodotto("Amaca in cotone con supporto","Perfetta per momenti di relax immersi nel verde.",29.99,ImageUtil.readImageFromResources("images/giardino/amaca.webp"),Categoria.GIARDINO, 270, true, true );
    Prodotto p35= new Prodotto("Lampade solari da esterno","Ricaricabili al sole, illuminano vialetti e giardini senza fili.",7.99,ImageUtil.readImageFromResources("images/giardino/lampade.jpg"),Categoria.GIARDINO, 270, true, true );
    Prodotto p36= new Prodotto("Serra da giardino in policarbonato","Protegge piante e ortaggi dalle intemperie.",14.99,ImageUtil.readImageFromResources("images/giardino/serra.jpg"),Categoria.GIARDINO, 270, true, true );
    Prodotto p37= new Prodotto("Set di attrezzi da giardinaggio","Include pala, rastrello, forbici e guanti per la cura delle piante",9.99,ImageUtil.readImageFromResources("images/giardino/irrigatore.jpg"),Categoria.GIARDINO, 270, false, true );
    Prodotto p38= new Prodotto("Casetta in legno per attrezzi","Spaziosa e resistente, perfetta per organizzare gli strumenti da giardinaggio.",4.99,ImageUtil.readImageFromResources("images/giardino/casetta.jpg"),Categoria.GIARDINO, 270, false, true );
    //Prodotto p39= new Prodotto("Dondolo a 3 posti con tettuccio","Comfort assicurato con seduta imbottita e protezione dal sole.",49.99,ImageUtil.readImageFromResources("images/giardino/dondolo.webp"),Categoria.GIARDINO, 270, false, false );
    Prodotto p40= new Prodotto("Irrigatore automatico programmabile","Distribuisce l’acqua in modo uniforme per un prato sempre verde.",9.99,ImageUtil.readImageFromResources("images/giardino/irrigatore.jpg"),Categoria.GIARDINO, 270, false, false );

    Indirizzo ind= new Indirizzo("Italia","Salerno","Sarno","Via Vesuvio", 4, 8006);
    Cliente cliente = new Cliente("Pietro", "Fasolino", "p.fasolino@gmail.com", "password", ind);
    //Cliente cliente = new


    /*
    List<Prodotto> prodotti = Arrays.asList(p1,p2, p3, p4);
    Magazzino magazzino= new Magazzino(ind, prodotti);*/
    Magazzino magazzino= new Magazzino(ind);
    Magazziniere magazziniere = new Magazziniere("Luigi","Bianchi","lbianchi@geg.it","password", magazzino);



    ItemCartDTO item1= new ItemCartDTO(p1.getId(),2);
    ItemCartDTO item2= new ItemCartDTO(p3.getId(),3);
    List<ItemCartDTO> listItem = Arrays.asList(item1, item2);

    GestoreOrdini gestore1= new GestoreOrdini("Luca","Cammarota","l.cammarota3@geg.it","password");






    @PostConstruct
    public void populateDB(){
        System.out.println("HO INIZIATO IL POPOLAMENTOOOOOOOOO!! \n");

        em.createQuery("DELETE FROM Prodotto p").executeUpdate();
        em.createQuery("DELETE FROM Fornitore f").executeUpdate();
        em.createQuery("DELETE FROM Cliente c").executeUpdate();
        em.createQuery("DELETE FROM Utente").executeUpdate();



        em.flush();

        em.persist(fornitore1);
        em.persist(p1); em.flush();
        //em.persist(p2); em.flush();
        em.persist(p3);em.flush();
        em.persist(p4);em.flush();
        em.persist(p5);em.flush();
        em.persist(p6);em.flush();
        em.persist(p7);em.flush();
        em.persist(p8);em.flush();
        em.persist(p9);em.flush();
        em.persist(p10);em.flush();
        //em.persist(p11);em.flush();
        //em.persist(p12);em.flush();
        em.persist(p13);em.flush();
        em.persist(p14);em.flush();
        em.persist(p15);em.flush();
        em.persist(p16);em.flush();
        em.persist(p17);em.flush();
        em.persist(p18);em.flush();
        em.persist(p19);em.flush();
        //em.persist(p20);em.flush();
        //em.persist(p21);em.flush();
        em.persist(p22);em.flush();
        em.persist(p23);em.flush();
        em.persist(p24);em.flush();
        em.persist(p25);em.flush();
        em.persist(p26);em.flush();
        em.persist(p27);em.flush();
        em.persist(p28);em.flush();
        em.persist(p29);em.flush();
        em.persist(p30);em.flush();
        //em.persist(p31);em.flush();
        em.persist(p32);em.flush();
        em.persist(p33);em.flush();
        //em.persist(p34);em.flush();
        //em.persist(p35);em.flush();
        em.persist(p36);em.flush();
        em.persist(p37);em.flush();
        em.persist(p38);em.flush();
        //em.persist(p39);em.flush();
        em.persist(p40);em.flush();

        em.persist(cliente);



        createProdotto(p1, fornitore1);
        //createProdotto(p2, fornitore1);
        createProdotto(p3, fornitore1);
        createProdotto(p4, fornitore1);
        createProdotto(p5, fornitore1);
        createProdotto(p6, fornitore1);
        createProdotto(p7, fornitore1);
        createProdotto(p8, fornitore1);
        createProdotto(p9, fornitore1);
        createProdotto(p10, fornitore1);
        //createProdotto(p11, fornitore1);
        //createProdotto(p12, fornitore1);
        createProdotto(p13, fornitore1);
        createProdotto(p14, fornitore1);
        createProdotto(p15, fornitore1);
        createProdotto(p16, fornitore1);
        createProdotto(p17, fornitore1);
        createProdotto(p18, fornitore1);
        createProdotto(p19, fornitore1);
        //createProdotto(p20, fornitore1);
        //createProdotto(p21, fornitore1);
        createProdotto(p22, fornitore1);
        createProdotto(p23, fornitore1);
        createProdotto(p24, fornitore1);
        createProdotto(p25, fornitore1);
        createProdotto(p26, fornitore1);
        createProdotto(p27, fornitore1);
        createProdotto(p28, fornitore1);
        createProdotto(p29, fornitore1);
        createProdotto(p30, fornitore1);
        //createProdotto(p31, fornitore1);
        createProdotto(p32, fornitore1);
        createProdotto(p33, fornitore1);
        //createProdotto(p34, fornitore1);
        //createProdotto(p35, fornitore1);
        createProdotto(p36, fornitore1);
        createProdotto(p37, fornitore1);
        createProdotto(p38, fornitore1);
        //createProdotto(p39, fornitore1);
        createProdotto(p40, fornitore1);


        em.flush();

        Ordine ordine = new Ordine(cliente.getId(),10.3,listItem);
        Ordine ordine2 = new Ordine(cliente.getId(),24.1,listItem);

        em.persist(ordine);
        em.persist(ordine2);
        em.persist(gestore1);
        giveOrdine(ordine, gestore1);

        em.flush();


        em.persist(magazziniere);
        OrderRequest orderRequest= new OrderRequest(magazziniere.getId(), gestore1.getId(), LocalDateTime.now(), ordine.getId(), "Ao bello");
        em.persist(orderRequest);

        em.flush();
        ProductRequest productRequest = new ProductRequest(magazziniere.getId(), fornitore1.getId(), LocalDateTime.now(), p4.getId(), 3, "ProvaRichiesta");
        em.persist(productRequest);
        em.flush();

        System.out.println("Popolamento completato");


    }

    @PreDestroy
    public void clearDB(){
        em.remove(p1);
        //em.remove(p2);
        em.remove(p3);
        em.remove(p4);
        em.remove(cliente);
        em.remove(fornitore1);
        em.clear();
    }

}