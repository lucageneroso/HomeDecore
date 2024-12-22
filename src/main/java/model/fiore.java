package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class fiore {
    @Id @GeneratedValue
    private Long id;

    private List<String> fiori;
    

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
