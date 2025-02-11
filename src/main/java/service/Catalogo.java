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
        if (prodotto.isInCatalogo()){
            prodotto.setInCatalogo(false);
            em.merge(prodotto);
        }
        else{
            System.out.println("Prodotto gia non in Catalogo");
        }

    }

    @Override
    public void removeProductFromCatalogo(Long IdProdotto) {
        Prodotto prodotto = em.find(Prodotto.class, IdProdotto);
        if (prodotto.isInCatalogo()){
            prodotto.setInCatalogo(false);
            em.merge(prodotto);
        }
        else{
            System.out.println("Prodotto gia non in Catalogo");
        }
    }

    @Override
    public void removeProductFromMagazzino(Prodotto prodotto) {
        if (prodotto.isInMagazzino()){
            prodotto.setInMagazzino(false);
            prodotto.setInCatalogo(false);
            em.merge(prodotto);
        }
        else{
            System.out.println("Prodotto gia non in Magazzino");
        }
    }

    @Override
    public void removeProductFromMagazzino(Long IdProdotto) {
        Prodotto prodotto = em.find(Prodotto.class, IdProdotto);
        if (prodotto.isInMagazzino()){
            prodotto.setInMagazzino(false);
            prodotto.setInCatalogo(false);
            em.merge(prodotto);
        }
        else{
            System.out.println("Prodotto gia non in Magazzino");
        }
    }

    @Override
    public void addProductToCatalogo(Prodotto prodotto) {
        if (prodotto.isInCatalogo()){
            System.out.println("Prodotto gia in Catalogo");
        }
        else{
            prodotto.setInCatalogo(true);
            em.merge(prodotto);
        }

    }

    @Override
    public void addProductToCatalogo(Long IdProdotto) {
        Prodotto prodotto = em.find(Prodotto.class, IdProdotto);
        if (prodotto.isInCatalogo()){
            System.out.println("Prodotto gia in Catalogo");
        }
        else{
            prodotto.setInCatalogo(true);
            em.merge(prodotto);
        }
    }

    @Override
    public void addProductToMagazzino(Prodotto prodotto) {
        if (prodotto.isInMagazzino()){
            System.out.println("Prodotto gia in Magazzino");
        }
        else{
            prodotto.setInMagazzino(true);
            em.merge(prodotto);
        }

    }

    @Override
    public void addProductToMagazzino(Long IdProdotto) {
        Prodotto prodotto = em.find(Prodotto.class, IdProdotto);
        if (prodotto.isInMagazzino()){
            System.out.println("Prodotto gia in Magazzino");
        }
        else{
            prodotto.setInMagazzino(true);
            em.merge(prodotto);
        }
    }


    @Override
    public void updateProduct(Prodotto prodotto) {
        em.merge(prodotto);
    }

    @Override
    public void updateProductName(Long IdProdotto, String nome) {
        if (nome.isEmpty()){
            System.out.println("Nome non valido");
            return;
        }
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
        if (price <= 0){
            System.out.println("Prezzo non valido");
            return;
        }
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


    public boolean validateNameChange(String nome){
        if (nome.isEmpty()) return false;
        else return true;
    }

    public boolean validatePriceChange(Double price){
        if (price<=0) return false;
        else return true;
    }

    public boolean validateAddToMagazzino(Prodotto prodotto){
        if (prodotto.isInMagazzino()) return false;
        else return true;
    }

    public boolean validateRemoveFromMagazzino(Prodotto prodotto){
        if (prodotto.isInMagazzino()) return true;
        else return false;
    }

    public boolean validateAddToCatalogo(Prodotto prodotto){
        if (prodotto.isInCatalogo()) return false;
        else return true;
    }

    public boolean validateRemoveFromCatalogo(Prodotto prodotto){
        if (prodotto.isInCatalogo()) return true;
        else return false;
    }


}
