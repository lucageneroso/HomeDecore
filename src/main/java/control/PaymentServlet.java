package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.Ordine;

import java.io.IOException;

@WebServlet("/processPayment")
public class PaymentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Ordine order = (Ordine) session.getAttribute("order");

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
                paymentSuccess = true;
            }
        } else if ("creditcard".equals(paymentMethod)) {
            String numeroCarta = request.getParameter("numeroCarta");
            String dataScadenza= request.getParameter("dataScadenza");
            String CVVCarta = request.getParameter("CVVCarta");

            // Simulazione del pagamento con Carta di Credito
            if (numeroCarta != null && dataScadenza!= null && CVVCarta!= null) {
                paymentSuccess = true;
            }
        }

        if (paymentSuccess) {
            session.removeAttribute("order"); // Rimuove l'ordine dalla sessione
            response.sendRedirect("ConfermaAcquisto.jsp");  //convalida acquisto
        } else {
            response.sendRedirect("payment.jsp?error=failed");
        }
    }
}
