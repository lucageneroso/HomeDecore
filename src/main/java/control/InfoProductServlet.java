package control;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.Cart;
import model.OrderManagement.ItemCart;
import model.OrderManagement.Prodotto;
import model.UserManagement.Utente;
import remoteInterfaces.CartServiceRemote;
import remoteInterfaces.CatalogoRemote;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/infoProduct")
public class InfoProductServlet extends HttpServlet {

    @EJB
    private CartServiceRemote cartService;

    @EJB
    private CatalogoRemote catalogo;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdStr = request.getParameter("productId");

        if (productIdStr == null) {
            response.sendRedirect("catalogo");
            return;
        }

        try {

            int productId = Integer.parseInt(productIdStr);

            // Recupera la mappa dei prodotti visualizzati dalla sessione
            HttpSession session = request.getSession();
            Map<Integer, Prodotto> prodottiSessione = (Map<Integer, Prodotto>) session.getAttribute("prodottiVisualizzati");

            if (prodottiSessione == null) {
                prodottiSessione = new HashMap<>();
            }

            // Se il prodotto non è già nella sessione, lo aggiungiamo
            if (!prodottiSessione.containsKey(productId)){
                Prodotto prodotto=catalogo.findProductByID(productId);
                prodottiSessione.put(productId, prodotto);
                session.setAttribute("prodottiVisualizzati", prodottiSessione);
                System.out.println("Prodotto aggiunto in sessiome");

                request.setAttribute("prodotto", prodotto);
                request.getRequestDispatcher("infoProduct.jsp").forward(request, response);

            }
            else{
                Prodotto prodotto=prodottiSessione.get(productId);
                System.out.println("Prodotto gia in sessione");

                // Passa il prodotto alla JSP, Forward alla JSP per visualizzare il prodotto
                request.setAttribute("prodotto", prodotto);
                request.getRequestDispatcher("infoProduct.jsp").forward(request, response);
            }


        /*
            if (prodotto == null) {
                response.sendRedirect("catalogo");
                return;
            }
            */

            /*
            if (prodotto != null) {



            } else {
                response.sendRedirect("errorPage.jsp");
            }

             */

            //request.setAttribute("prodotto", prodotto);
            //request.getRequestDispatcher("infoProduct.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("catalogo");
        }
    }
}
