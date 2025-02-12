package service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import model.OrderManagement.PayPal;
import remoteInterfaces.PaymentServiceRemote;


//utilizziamo tale annotazione,per far si che CDI istanzi il servizio solo quando richiesto e al termine delle operazioni verrà deallocato
//In modo che ogni utente quando avvia un pagamento,sia un processo non condiviso e quindi soggetto a problemi di sicurezza dei dati
@RequestScoped
public class PayPalService implements PaymentServiceRemote {
    private PayPal payPal;

  //alternativa con metodo init,che aggiunge i parametri dell account Paypal
  public void init(String email, String password) {
      this.payPal = new PayPal(email, password);
  }


    @Override
    public int effettuaPagamento(double importo) {
        if(payPal.processPayment(importo))
            return 1;
        else
            return 0;

    }
}
