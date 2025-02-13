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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ProductNotInMagazzino")
public class ProductNotInMagazzino extends HttpServlet {

    @EJB
    CatalogoRemote catalogo;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {

            Utente utente = (Utente) session.getAttribute("loggedUser");

            if (utente.getRuolo().equals(Ruolo.MAGAZZINIERE)){

                List<Prodotto> tuttiProdotti= catalogo.getProducts();
                List<Prodotto> prodottiNonInMagazzino = new ArrayList<Prodotto>();

                for (Prodotto p: tuttiProdotti){
                    if (!p.isInMagazzino()){
                        prodottiNonInMagazzino.add(p);
                    }
                }

                request.setAttribute("prodotti", prodottiNonInMagazzino);
                request.getRequestDispatcher("/prodottiFornitori.jsp").forward(request, response);



            }else{
                System.out.println("Ruolo non corretto");
            }

        }else{
            response.getWriter().println("Sessione non esistente.");
        }


    }

}
