package model.OrderManagement;

public class PayPal {
    private String email;
    private String password;

    public PayPal(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /*la password non può essere recuperata per evitare problemi di vulnerabilità e sicurezza*/

    public String getEmail() {
        return email;
    }



    public boolean processPayment(String email, double amount) {
        System.out.println("Pagamento di " + amount + "€ effettuato con PayPal dall'email: " + email);
        return true;
    }
}
