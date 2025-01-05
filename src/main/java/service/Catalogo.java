package service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import enumerativeTypes.Categoria;
import model.Prodotto;
import remoteInterfaces.CatalogoRemote;
import jakarta.annotation.PostConstruct;

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
        em.remove(em.merge(prodotto));
    }

    @Override
    public void updateProduct(Prodotto prodotto) {
        em.merge(prodotto);
    }

    @Override
    public List<Prodotto> getProducts() {
        System.out.println(em);
    TypedQuery<Prodotto> query= em.createNamedQuery("Prodotto.TROVA_TUTTI", Prodotto.class);
    return query.getResultList();
    }

    @Override
    public List<Prodotto> findByName(String nome) {
        TypedQuery<Prodotto> query=  em.createNamedQuery("Prodotto.TROVA_PER_NOME", Prodotto.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    @Override
    public Prodotto findProductByID(int id) {
        TypedQuery<Prodotto> query=  em.createNamedQuery("Prodotto.TROVA_PER_ID", Prodotto.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }


    @Override
    public List<Prodotto> findByMinusPrize(Double prezzo) {
       TypedQuery<Prodotto> query=em.createNamedQuery("Prodotto.TROVA_PER_PREZZO_MINORE", Prodotto.class);
       query.setParameter("prezzo", prezzo);
       return query.getResultList();
    }

    @Override
    public List<Prodotto> findByMajorPrize(Double prezzo) {
        TypedQuery<Prodotto> query=em.createNamedQuery("Prodotto.TROVA_PER_PREZZO_MAGGIORE", Prodotto.class);
        query.setParameter("prezzo", prezzo);
        return query.getResultList();
    }

    @Override
    public List<Prodotto> findByCategory(Categoria categoria) {
        TypedQuery<Prodotto> query=em.createNamedQuery("Prodotto.TROVA_PER_CATEGORIA", Prodotto.class);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }


}
