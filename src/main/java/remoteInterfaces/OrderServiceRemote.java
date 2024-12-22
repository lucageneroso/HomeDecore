package remoteInterfaces;

import model.Ordine;
import model.Prodotto;
import enumerativeTypes.Stato;

import javax.ejb.Remote;
import java.sql.Date;
import java.util.List;

@Remote
public interface OrderServiceRemote {
     void addOrder(Ordine order);
     Ordine findOrderById(int id);
     List<Prodotto> findAllOrders();
     void updateOrder(Ordine order);
     void removeOrder(int id);
     List<Prodotto> findOrdersByCostumer(int userId);
     List<Prodotto> findByPrize(Double prezzo);
     List<Prodotto> findByDate(Date date);
     List<Prodotto> findByState(Stato stato);
}
