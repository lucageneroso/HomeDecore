package remoteInterfaces;

import model.Ordine;
import model.Prodotto;
import enumerativeTypes.Stato;

//import javax.ejb.Remote;
import java.sql.Date;
import java.util.List;
import jakarta.ejb.Remote;

@Remote
public interface OrderServiceRemote {
     void addOrder(Ordine order);
     Ordine findOrderById(int id);
     List<Ordine> findAllOrders();
     void updateOrder(Ordine order);
     void removeOrder(int id);
     List<Ordine> findOrdersByCostumer(int userId);
     List<Ordine> findByPrize(Double prezzo);
     List<Ordine> findByDate(Date date);
     List<Ordine> findByState(Stato stato);
}
