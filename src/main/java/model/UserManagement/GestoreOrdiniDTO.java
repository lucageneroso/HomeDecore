package model.UserManagement;

import java.io.Serializable;

public class GestoreOrdiniDTO implements Serializable {

    private Long gestoreOrdiniID;
    private String email;

    public GestoreOrdiniDTO() {}
    public GestoreOrdiniDTO(Long gestoreOrdiniID, String email) {
        this.gestoreOrdiniID = gestoreOrdiniID;
        this.email = email;
    }

    public Long getGestoreOrdiniID() { return gestoreOrdiniID; }
    public void setGestoreOrdiniID(Long gestoreOrdiniID) { this.gestoreOrdiniID = gestoreOrdiniID; }

    public String getGestoreOrdiniEmail() { return email; }
    public void setGestoreOrdiniEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "{ ID: " + gestoreOrdiniID + ", Email: " + email+" }";
    }
}
