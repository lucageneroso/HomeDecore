package remoteInterfaces;

import model.Recensione;

import java.util.Date;
import java.util.List;

public interface RecensioneServiceRemote {

    void addReview(Recensione r);
    void removeReview(Recensione r);
    void updateReview(Recensione r);

    Recensione findById(int ID);
    List<Recensione> findAll();
    List<Recensione> findByDate(Date date);
    List<Recensione> findByUser(int userID);
    List<Recensione> findByProduct(int productID);
    List<Recensione> findByRating(int rating);

}
