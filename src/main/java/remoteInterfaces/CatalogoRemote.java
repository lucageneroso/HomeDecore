package remoteInterfaces;

import enumerativeTypes.Categoria;
import model.Prodotto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface CatalogoRemote {

    // CRUD OPERATIONS
    void addProduct(Prodotto prodotto);
    void removeProduct(Prodotto prodotto);
    void updateProduct(Prodotto prodotto);

    List<Prodotto> getProducts();
    List<Prodotto> findByName(String nome);
    Prodotto findProductByID(int id);

    // RETRIEVE
    List<Prodotto> findByMinusPrize(Double prezzo);
    List<Prodotto> findByMajorPrize(Double prezzo);
    List<Prodotto> findByCategory(Categoria categoria);
}
