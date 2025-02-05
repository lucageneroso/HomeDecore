package service;

import model.OrderManagement.CreditCard;
import remoteInterfaces.PaymentServiceRemote;

public class CreditCardService implements PaymentServiceRemote {

    private CreditCard creditCard;

    public CreditCardService(String titolare, String numeroCarta, String dataScandenza, String cvv) {
        creditCard=new CreditCard(titolare,numeroCarta,dataScandenza,cvv);
    }
    @Override
    public boolean effettuaPagamento(double importo) {
        creditCard.payWithCard(creditCard.getTitolare(), importo);
        return true;
    }
}
