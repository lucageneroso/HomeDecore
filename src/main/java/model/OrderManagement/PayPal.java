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



    public boolean processPayment(double amount) {
        if (amount >= 0 && amount <= 1000) {
            System.out.println("Pagamento di " + amount + "€ avvenuto con successo ed effettuato con PayPal");
            return true;

        } else {
            System.out.println("Pagamento non avvenuto");
            return false;
        }
    }
}
