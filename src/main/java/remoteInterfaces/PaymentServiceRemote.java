package remoteInterfaces;

import jakarta.ejb.Remote;

@Remote
public interface PaymentServiceRemote {
    int effettuaPagamento(double importo);
}
