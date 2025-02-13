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
import model.UserManagement.Utente;
import remoteInterfaces.CatalogoRemote;

import java.io.IOException;
import java.util.List;



@WebServlet("/fornitore")
public class FornitoreServlet extends HttpServlet {

    @EJB CatalogoRemote catalogo;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {

            Utente utente = (Utente) session.getAttribute("loggedUser");
            Long userID= utente.getId();

            if (utente.getRuolo().equals(Ruolo.FORNITORE)){

                List<Prodotto> prodotti= catalogo.findProductByFornitore(userID);
                System.out.println(prodotti);

            }

            if (utente.getRuolo().equals(Ruolo.MAGAZZINIERE)){

                String fornitoreIDParam = request.getParameter("fornitoreID");

                if (fornitoreIDParam != null) {
                    Long fornitoreID = Long.parseLong(fornitoreIDParam);
                    List<Prodotto> prodotti = catalogo.findProductByFornitore(fornitoreID);
                    System.out.println(prodotti);
                } else {
                    System.out.println("Nessun fornitore specificato.");
                }


            }

            else{
                System.out.println("Ruolo errato");
            }


        }else{
            response.getWriter().println("Sessione non esistente.");
        }


    }
}
