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
import remoteInterfaces.CatalogoRemote;
import remoteInterfaces.RequestServiceRemote;

import java.io.IOException;
import java.time.LocalDateTime;



@WebServlet("/accettaRichiestaServlet")
public class accettaRichiestaServlet extends HttpServlet {

    @EJB RequestServiceRemote requestService;
    @EJB CatalogoRemote catalogo;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(false);

        if (session != null) {
            Utente utente = (Utente) session.getAttribute("loggedUser");

            if (utente.getRuolo().equals(Ruolo.FORNITORE)){

                // Leggi i parametri
                String idRequest = request.getParameter("idRequest");
                String idProdotto = request.getParameter("idProdotto");
                String quantita = request.getParameter("quantita");


                try {

                    requestService.removeRequest( requestService.findById(Long.parseLong(idRequest)) );

                    Prodotto prodotto= catalogo.findProductByID(Integer.parseInt(idProdotto));
                    prodotto.setDisponibilita( prodotto.getDisponibilita() + Integer.parseInt(quantita));
                    prodotto.setInMagazzino(true);
                    catalogo.updateProduct(prodotto);

                    System.out.println("Richiesta di prodotto gestita");

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
