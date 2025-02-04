package remoteInterfaces;

import model.OrderManagement.ItemCartDTO;
import model.OrderManagement.Ordine;
import enumerativeTypes.Stato;

//import javax.ejb.Remote;
import java.sql.Date;
import java.util.List;
import jakarta.ejb.Remote;
import model.UserManagement.GestoreOrdini;

@Remote
public interface OrderServiceRemote {
     Ordine addOrder(Ordine order);
     Ordine findOrderById(int id);
     List<Ordine> findAllOrders();
     void updateOrder(Ordine order);
     void removeOrder(int id);
     List<Ordine> findOrdersByCostumer(long userId);

     List<Ordine> findOrdersByGestore(long userId);
     List<GestoreOrdini> findAllGestoreOrdini();

     List<Ordine> findByPrize(Double prezzo);
     List<Ordine> findByDate(Date date);
     List<Ordine> findByState(Stato stato);

     List<ItemCartDTO> deserializeItems(List<String> serializedItems);
}
