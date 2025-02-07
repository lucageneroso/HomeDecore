package model.OrderManagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import enumerativeTypes.Stato;
import jakarta.persistence.*;
import model.UserManagement.GestoreOrdini;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NamedQueries({
        @NamedQuery(name="Ordine.TROVA_TUTTI", query="SELECT o FROM Ordine o"),
        @NamedQuery(name="Ordine.TROVA_PER_ID", query="SELECT o FROM Ordine o WHERE o.id = :id "),
        @NamedQuery(name="Ordine.TROVA_PER_ID_GESTORE", query="SELECT o FROM Ordine o WHERE o.idGestore = :idGestore "),
        @NamedQuery(name="Ordine.TROVA_PER_UTENTE", query="SELECT o FROM Ordine o WHERE o.userId = :userId"),
        @NamedQuery(name="Ordine.TROVA_PER_DATA", query="SELECT o FROM Ordine o WHERE o.date =:date "),
        @NamedQuery(name="Ordine.TROVA_PER_STATO", query="SELECT o FROM Ordine o WHERE o.stato =:stato "),
        @NamedQuery(name="Ordine.TROVA_PER_TOTALE", query="SELECT o FROM Ordine o WHERE o.totale = :totale")
})
@Entity
public class Ordine implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private double totale=0.0;
    private Long userId;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private Stato stato;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> items;

    private Long idGestore;

    public Ordine() {}
    public Ordine(Long userId, Double totale, List<ItemCartDTO> items) {
        this.totale = totale;
        this.userId = userId;
        this.date = LocalDateTime.now();
        this.stato = Stato.PREPARATION;
        this.items = serializeItems(items);
        this.idGestore = null;
    }

    // Metodo per serializzare i DTO in JSON
    public List<String> serializeItems(List<ItemCartDTO> items) {
        List<String> serializedItems = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();  // Usa Jackson per la serializzazione JSON
        try {
            for (ItemCartDTO item : items) {
                String json = objectMapper.writeValueAsString(item);
                serializedItems.add(json);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return serializedItems;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getTotale() {
        return totale;
    }
    public void setTotale(double totale) {
        this.totale = totale;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public List<String> getItems(){
        return items;
    }
    public void setItems(List<String> items){
        this.items = items;
    }
    public Stato getStato() {
        return stato;
    }
    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public Long getIdGestore() { return idGestore; }
    public void setIdGestore(Long idGestore) { this.idGestore = idGestore; }
    public void setIdGestore(GestoreOrdini gestore) { this.idGestore = gestore.getId(); }

}
