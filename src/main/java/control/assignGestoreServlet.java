package control;

import enumerativeTypes.Ruolo;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.Prodotto;
import model.RequestManagement.OrderRequest;
import model.UserManagement.Utente;
import remoteInterfaces.RequestServiceRemote;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/assignGestoreServlet")
public class assignGestoreServlet extends HttpServlet {

    @EJB RequestServiceRemote requestService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Chiamata alla servlet");

        HttpSession session = request.getSession(false);

        if (session != null) {
            Utente utente = (Utente) session.getAttribute("loggedUser");

            if (utente.getRuolo().equals(Ruolo.MAGAZZINIERE)){

                // Leggi i parametri
                String ordineId = request.getParameter("ordineId");
                String gestoreId = request.getParameter("gestoreId");
                String message = request.getParameter("message");

                // Stampa per debugging
                System.out.println("ordineId: " + ordineId);
                System.out.println("gestoreId: " + gestoreId);
                System.out.println("message: " + message);

                try {
                    // Crea la richiesta di ordine
                    OrderRequest orderRequest = new OrderRequest(utente.getId(), Long.parseLong(gestoreId), LocalDateTime.now(), Long.parseLong(ordineId), message);
                    requestService.addRequest(orderRequest);
                    System.out.println("Richiesta creata");

                    // Risposta JSON
                    response.setContentType("application/json");
                    response.getWriter().write("{\"status\":\"success\"}");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setContentType("application/json");
                    response.getWriter().write("{\"status\":\"error\",\"message\":\"Errore nell'elaborazione della richiesta\"}");
                }

            } else {
                System.out.println("Ruolo non corretto");
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"error\",\"message\":\"Ruolo non corretto\"}");
            }

        } else {
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Sessione non esistente.\"}");
        }
    }
}
