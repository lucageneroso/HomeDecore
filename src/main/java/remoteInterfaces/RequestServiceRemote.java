package remoteInterfaces;

import jakarta.ejb.Remote;
import model.RequestManagement.Request;
import model.ReviewManagement.Recensione;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Remote
public interface RequestServiceRemote {
    void addRequest(Request request);
    void removeRequest(Request request);
    void updateRequest(Request request);

    Request findById(int ID);
    List<Request> findAll();

    List<Request> findByDate(LocalDateTime date);
    List<Request> findByPostDate(LocalDateTime date);
    List<Request> findByPreviousDate(LocalDateTime date);
    List<Request> orderByDate();

    List<Request> findByMagazziniere(int userID);
    List<Request> findByDestinatario(int userID);
}
