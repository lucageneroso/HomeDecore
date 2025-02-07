package model.UserManagement;

import java.io.Serializable;

public class FornitoreDTO implements Serializable {

    private Long fornitoreID;
    private String email;

    public FornitoreDTO() {}
    public FornitoreDTO(Long fornitoreID, String email) {
        this.fornitoreID = fornitoreID;
        this.email = email;
    }

    public Long getFornitoreID() { return fornitoreID; }
    public void setFornitoreID(Long fornitoreID) { this.fornitoreID = fornitoreID; }

    public String getFornitoreEmail() { return email; }
    public void setFornitoreEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "{ ID: " + fornitoreID + ", Email: " + email+" }";
    }
}
