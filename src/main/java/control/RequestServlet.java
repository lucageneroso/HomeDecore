package control;

import enumerativeTypes.Ruolo;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.Ordine;
import model.RequestManagement.OrderRequest;
import model.RequestManagement.ProductRequest;
import model.RequestManagement.Request;
import model.UserManagement.GestoreOrdini;
import model.UserManagement.Utente;
import remoteInterfaces.OrderServiceRemote;
import remoteInterfaces.RequestServiceRemote;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {

    @EJB RequestServiceRemote requestServiceRemote;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // Ottenere la sessione senza crearne una nuova se non esiste
        HttpSession session = request.getSession(false);

        if (session != null) {

            Utente utente = (Utente) session.getAttribute("loggedUser");
            Long userID= utente.getId();

            if (utente.getRuolo().equals(Ruolo.MAGAZZINIERE)) {
                List<Request> richieste= requestServiceRemote.findByMagazziniere(userID);
                System.out.println(richieste);
            }

            if (utente.getRuolo() != null && utente.getRuolo().equals(Ruolo.FORNITORE)){
                //Selezionamo tutti i tipi di Richiesta che siano ProductRequest
                List<ProductRequest> richieste = requestServiceRemote.findByDestinatario(userID)
                        .stream()
                        .filter(r -> r instanceof ProductRequest)
                        .map(r -> (ProductRequest) r)
                        .collect(Collectors.toList());
                request.setAttribute("richiesteFornitore", richieste);
                request.getRequestDispatcher("/Fornitore.jsp").forward(request, response);

                System.out.println(richieste);
            }

            if (utente.getRuolo().equals(Ruolo.GESTOREORDINI)){
                //Selezionamo tutti i tipi di Richiesta che siano OrderRequest
                List<OrderRequest> richieste = requestServiceRemote.findByDestinatario(userID)
                        .stream()
                        .filter(r -> r instanceof OrderRequest)
                        .map(r -> (OrderRequest) r)
                        .collect(Collectors.toList());

                System.out.println(richieste);

            }



            if (
                    utente.getRuolo().equals(Ruolo.CLIENTE)
            ){
                System.out.println("Nah, you aint right on here nigga");
            }





            /*
            System.out.println("Requests: \n");


            List<Request> richieste= requestServiceRemote.findAll();
            System.out.println("Requests: "+richieste.get(0));

            requestServiceRemote.cambiaStato(richieste.get(0));
            richieste= requestServiceRemote.findAll();
            System.out.println("Requests: "+richieste.get(0));
            */


        } else {
            response.getWriter().println("Sessione non esistente.");
        }




    }
}
