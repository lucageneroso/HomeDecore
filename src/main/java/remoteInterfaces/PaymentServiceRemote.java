package remoteInterfaces;

import jakarta.ejb.Remote;

@Remote
public interface PaymentServiceRemote {
    boolean effettuaPagamento(double importo);
}
