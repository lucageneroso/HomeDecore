package control;

import enumerativeTypes.Ruolo;
import enumerativeTypes.Stato;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.Ordine;
import model.UserManagement.Utente;
import remoteInterfaces.OrderServiceRemote;
import remoteInterfaces.RequestServiceRemote;

import java.io.IOException;


@WebServlet("/cambiaStatoOrdine")
public class cambiaStatoOrdine extends HttpServlet {

    @EJB OrderServiceRemote orderService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(false);

        if (session != null) {
            Utente utente = (Utente) session.getAttribute("loggedUser");

            if (utente.getRuolo().equals(Ruolo.GESTOREORDINI)){


                // Leggi i parametri
                String stato = request.getParameter("nuovoStato");
                String idOrdine = request.getParameter("idOrdine");

                try {

                    Ordine ordine = orderService.findOrderById( Integer.parseInt(idOrdine) );
                    ordine.setStato(Stato.valueOf(stato) );
                    orderService.updateOrder(ordine);

                    System.out.println("Ordine modificato");

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
