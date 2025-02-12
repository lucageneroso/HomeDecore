package service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import model.OrderManagement.CreditCard;
import remoteInterfaces.PaymentServiceRemote;


//utilizziamo tale annotazione,per far si che CDI istanzi il servizio solo quando richiesto e al termine delle operazioni verrà deallocato
//In modo che ogni utente quando avvia un pagamento,sia un processo non condiviso e quindi soggetto a problemi di sicurezza dei dati
@RequestScoped
public class CreditCardService implements PaymentServiceRemote {

    private CreditCard creditCard;


    //alternativa al costruttore per aggiungere i parametri utilizzati per il pagamento con carta
    public void init(String titolare, String numeroCarta, String dataScadenza, String cvv) {
        this.creditCard = new CreditCard(titolare, numeroCarta, dataScadenza, cvv);
    }
    @Override
    public int effettuaPagamento(double importo) {
        if(creditCard.payWithCard(importo))
            return 1;
        else
            return 0;

    }
}
