package model.OrderManagement;

public class CreditCard {


    private String titolare;
    private String numeroCarta;
    private String dataScandenza;
    private String cvv;

    public CreditCard(String titolare, String numeroCarta, String dataScandenza, String cvv) {
        this.titolare=titolare;
        this.numeroCarta=numeroCarta;
        this.dataScandenza=dataScandenza;
        this.cvv=cvv;
    }
    public String getTitolare() {
        return titolare;
    }
   /*le varie informazioni non possono essere recuperate per garantire sicurezza dei dati

    */
   public boolean payWithCard(double amount) {
       if (amount >= 0 && amount <= 3000) {
           System.out.println("Pagamento di " + amount + "€ effettuato con la carta di credito è andato a buon fine");
           return true;
       }
       else{
           System.out.println("Pagamento non avvenuto");
           return false;
       }
   }
}
