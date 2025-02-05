package service;

import model.OrderManagement.PayPal;
import remoteInterfaces.PaymentServiceRemote;

public class PayPalService implements PaymentServiceRemote {
    private PayPal payPal;



    public PayPalService(String email,String password) {
        payPal = new PayPal(email,password);
    }
    @Override
    public boolean effettuaPagamento(double importo) {
        payPal.processPayment(payPal.getEmail(),importo);
        return true;
    }
}
