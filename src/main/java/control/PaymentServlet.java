package control;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.CreditCard;
import model.OrderManagement.Ordine;
import service.CreditCardService;
import service.PayPalService;

import java.io.IOException;

@WebServlet("/processPayment")
public class PaymentServlet extends HttpServlet {
    @Inject
    PayPalService PayPalservice;

    @Inject
    CreditCardService cardService;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Ordine order = (Ordine) session.getAttribute("order");

        //recupero totale carrello aggiunto in sessione per effettuare il pagamento a seconda della modalità scelta
        Double importo= (Double) session.getAttribute("cartTotal");
        int result;
        String message;

        if (order == null) {
            response.sendRedirect("cart.jsp?error=noorder");
            return;
        }

        // Recupera il metodo di pagamento selezionato
        String paymentMethod = request.getParameter("paymentMethod");
        boolean paymentSuccess = false;

        //Controlli sui metodi di pagamento
        if ("paypal".equals(paymentMethod)) {
            String paypalEmail = request.getParameter("paypalEmail");
            String paypalPassword = request.getParameter("paypalPassword");



            // Simulazione del pagamento con PayPal
            if (paypalEmail != null && !paypalEmail.isEmpty() &&
                    paypalPassword != null && !paypalPassword.isEmpty()) {
                PayPalservice.init(paypalEmail, paypalPassword);
                result = PayPalservice.effettuaPagamento(importo);
                if (result == 1) {
                    message="Il pagamento con PayPal è avvenuto con successo";
                    session.setAttribute("message", message);
                    paymentSuccess = true;
                }
                else{
                    message="Il pagamento non è andato a buon fine";
                    session.setAttribute("message", message);

                }
            }
            else
            {
               message="dati della carta non validi";
               session.setAttribute("message", message);
            }
        } else if ("creditcard".equals(paymentMethod)) {
            String titolare=request.getParameter("titolare");
            String numeroCarta = request.getParameter("numeroCarta");
            String dataScadenza= request.getParameter("dataScadenza");
            String CVVCarta = request.getParameter("CVVCarta");


            // Simulazione del pagamento con Carta di Credito
            if (numeroCarta != null && dataScadenza!= null && CVVCarta!= null) {
                cardService.init(titolare,numeroCarta,dataScadenza,CVVCarta);
                result=cardService.effettuaPagamento(importo);
                if(result==1){
                    message="Il pagamento con la carta di credito è avvenuto con successo";
                    session.setAttribute("message",message);
                    paymentSuccess = true;
                }
                else{
                    message="Il pagamento non è andato a buon fine";
                    session.setAttribute("message",message);
                }

            }
            else
            {
                message="dati della carta non validi";
                session.setAttribute("message",message);
            }
        }

        if (paymentSuccess) {
            session.removeAttribute("order"); // Rimuove l'ordine dalla sessione
            session.removeAttribute("cartTotal");
            response.sendRedirect("ConfermaAcquisto.jsp");  //convalida acquisto
        } else {
            response.sendRedirect("ErrorePagamento.jsp");
        }
    }
}
