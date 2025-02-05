package remoteInterfaces;

import enumerativeTypes.Categoria;
import model.OrderManagement.Prodotto;

//import javax.ejb.Remote;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface CatalogoRemote {

    // CRUD OPERATIONS
    void addProduct(Prodotto prodotto);
    void removeProduct(Prodotto prodotto);

    void removeProductFromCatalogo(Prodotto prodotto);
    void removeProductFromCatalogo(Long IdProdotto);

    void removeProductFromMagazzino(Prodotto prodotto);
    void removeProductFromMagazzino(Long IdProdotto);

    void addProductToCatalogo(Prodotto prodotto);
    void addProductToCatalogo(Long IdProdotto);

    void addProductToMagazzino(Prodotto prodotto);
    void addProductToMagazzino(Long IdProdotto);

    void updateProduct(Prodotto prodotto);
    void updateProductName(Long IdProdotto, String nome );
    void updateProductDesc(Long IdProdotto, String descrizione );
    void updateProductPrice(Long IdProdotto, double price );

    List<Prodotto> getProducts();
    List<Prodotto> getProductsInCatalogo();
    List<Prodotto> getProductsInMagazzino();

    List<Prodotto> findByName(String nome);
    Prodotto findProductByID(int id);
    List<Prodotto> findProductByFornitore(Long id);

    // RETRIEVE
    List<Prodotto> findByMinusPrize(Double prezzo);
    List<Prodotto> findByMajorPrize(Double prezzo);
    List<Prodotto> findByCategory(Categoria categoria);
    //List<Prodotto> findByFornitore(Fornitore fornitore);
}