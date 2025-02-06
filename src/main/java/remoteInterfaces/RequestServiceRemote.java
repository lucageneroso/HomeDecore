package remoteInterfaces;

import jakarta.ejb.Remote;
import model.RequestManagement.Request;

import java.time.LocalDateTime;
import java.util.List;

@Remote
public interface RequestServiceRemote {

    void addRequest(Request request);
    void removeRequest(Request request);
    void updateRequest(Request request);

    Request findById(Long ID);
    List<Request> findByMagazziniere(Long magazziniereID);
    List<Request> findByDestinatario(Long destinatarioID);
    List<Request> findAll();

    public void cambiaStato(Request request);

    public List<Request> findByDate(LocalDateTime dataStart, LocalDateTime dataEnd);
    List<Request> findByPostDate(LocalDateTime date);
    List<Request> findByPreviousDate(LocalDateTime date);
    List<Request> orderByDate();


}
