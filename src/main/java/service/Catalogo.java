package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import enumerativeTypes.Categoria;
import model.OrderManagement.Prodotto;
import remoteInterfaces.CatalogoRemote;

import java.util.List;

@Stateless(name = "Catalogo")//, mappedName = "java:app/HomeDecore/Catalogo")
public class Catalogo implements CatalogoRemote {

    @PersistenceContext(unitName = "HomeDecorePU")
    private EntityManager em;


    @Override
    public void addProduct(Prodotto prodotto) {
        em.persist(prodotto);
    }

    @Override
    public void removeProduct(Prodotto prodotto) {
        em.remove(prodotto);
    }

    @Override
    public void removeProductFromCatalogo(Prodotto prodotto) {
        prodotto.setInCatalogo(false);
        em.merge(prodotto);
    }

    @Override
    public void removeProductFromCatalogo(Long IdProdotto) {
        Prodotto prodotto = em.find(Prodotto.class, IdProdotto);
        prodotto.setInCatalogo(false);
        em.merge(prodotto);
    }

    @Override
    public void removeProductFromMagazzino(Prodotto prodotto) {
        prodotto.setInCatalogo(false);
        em.merge(prodotto);
    }

    @Override
    public void removeProductFromMagazzino(Long IdProdotto) {
        Prodotto prodotto = em.find(Prodotto.class, IdProdotto);
        prodotto.setInCatalogo(false);
        em.merge(prodotto);
    }

    @Override
    public void addProductToCatalogo(Prodotto prodotto) {
        prodotto.setInCatalogo(true);
        em.merge(prodotto);
    }

    @Override
    public void addProductToCatalogo(Long IdProdotto) {
        Prodotto prodotto = em.find(Prodotto.class, IdProdotto);
        prodotto.setInCatalogo(true);
        em.merge(prodotto);
    }

    @Override
    public void addProductToMagazzino(Prodotto prodotto) {
        prodotto.setInMagazzino(true);
        em.merge(prodotto);
    }

    @Override
    public void addProductToMagazzino(Long IdProdotto) {
        Prodotto prodotto = em.find(Prodotto.class, IdProdotto);
        prodotto.setInMagazzino(true);
        em.merge(prodotto);
    }


    @Override
    public void updateProduct(Prodotto prodotto) {
        em.merge(prodotto);
    }

    @Override
    public void updateProductName(Long IdProdotto, String nome) {
        Prodotto prodotto = em.find(Prodotto.class, IdProdotto);
        prodotto.setNome(nome);
        em.merge(prodotto);
    }

    @Override
    public void updateProductDesc(Long IdProdotto, String descrizione) {
        Prodotto prodotto = em.find(Prodotto.class, IdProdotto);
        prodotto.setDescrizione(descrizione);
        em.merge(prodotto);
    }

    @Override
    public void updateProductPrice(Long IdProdotto, double price) {
        Prodotto prodotto = em.find(Prodotto.class, IdProdotto);
        prodotto.setPrezzo(price);
        em.merge(prodotto);
    }

    @Override
    public List<Prodotto> getProducts() {
        TypedQuery<Prodotto> query= em.createNamedQuery("TROVA_TUTTI", Prodotto.class);
        return query.getResultList();
    }

    @Override
    public List<Prodotto> getProductsInCatalogo() {
        TypedQuery<Prodotto> query= em.createNamedQuery("TROVA_IN_CATALOGO", Prodotto.class);
        return query.getResultList();
    }

    @Override
    public List<Prodotto> getProductsInMagazzino() {
        TypedQuery<Prodotto> query= em.createNamedQuery("TROVA_IN_MAGAZZINO", Prodotto.class);
        return query.getResultList();
    }






    @Override
    public List<Prodotto> findByName(String nome) {
        TypedQuery<Prodotto> query=  em.createNamedQuery("TROVA_PER_NOME", Prodotto.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    @Override
    public Prodotto findProductByID(int id) {
        TypedQuery<Prodotto> query=  em.createNamedQuery("TROVA_PER_IDENT", Prodotto.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }

    @Override
    public List<Prodotto> findProductByFornitore(Long id) {
        TypedQuery<Prodotto> query=  em.createNamedQuery("TROVA_PER_FORNITORE", Prodotto.class);
        query.setParameter("fornitore", id);
        return query.getResultList();
    }


    @Override
    public List<Prodotto> findByMinusPrize(Double prezzo) {
       TypedQuery<Prodotto> query=em.createNamedQuery("TROVA_PER_PREZZO_MINORE", Prodotto.class);
       query.setParameter("prezzo", prezzo);
       return query.getResultList();
    }

    @Override
    public List<Prodotto> findByMajorPrize(Double prezzo) {
        TypedQuery<Prodotto> query=em.createNamedQuery("TROVA_PER_PREZZO_MAGGIORE", Prodotto.class);
        query.setParameter("prezzo", prezzo);
        return query.getResultList();
    }

    @Override
    public List<Prodotto> findByCategory(Categoria categoria) {
        TypedQuery<Prodotto> query=em.createNamedQuery("TROVA_PER_CATEGORIA", Prodotto.class);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }

    /*
    @Override
    public List<Prodotto> findByFornitore(Fornitore fornitore) {
        TypedQuery<Prodotto> query=em.createNamedQuery("TROVA_PER_FORNITORE", Prodotto.class);
        query.setParameter("fornitore", fornitore);
        return query.getResultList();
    }*/


}
