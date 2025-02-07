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
import model.OrderManagement.Prodotto;
import model.UserManagement.GestoreOrdini;
import model.UserManagement.Utente;
import remoteInterfaces.OrderServiceRemote;

import java.io.IOException;
import java.util.List;


@WebServlet("/ordini")
public class gestioneOrdini extends HttpServlet {

    @EJB OrderServiceRemote orderService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        System.out.println("Ordini id prova: \n");
        List<GestoreOrdini> gestori= orderService.findAllGestoreOrdini();
        System.out.println("Gestori: "+gestori);

        List<Ordine> ordini=orderService.findOrdersByGestore( gestori.get(0).getId() );
        System.out.println("Ordini: "+ordini);
        */




        HttpSession session = request.getSession(false);

        if (session != null) {

            Utente utente = (Utente) session.getAttribute("loggedUser");
            Long userID= utente.getId();

            if (utente.getRuolo().equals(Ruolo.GESTOREORDINI)){

                List<Ordine> ordini= orderService.findOrdersByGestore(userID);
                System.out.println(ordini);

            }

            if (utente.getRuolo().equals(Ruolo.MAGAZZINIERE)){

                String gestoreIDParam = request.getParameter("gestoreID");

                if (gestoreIDParam != null) {
                    Long gestoreID = Long.parseLong(gestoreIDParam);
                    List<Ordine> ordini = orderService.findOrdersByGestore(gestoreID);
                    System.out.println(ordini);
                } else {
                    System.out.println("Nessun gestore di ordini specificato.");
                }


            }

            else{
                System.out.println("Stupido idiota che ci fai qui");
            }


        }else{
            response.getWriter().println("Sessione non esistente.");
        }
    }
}
