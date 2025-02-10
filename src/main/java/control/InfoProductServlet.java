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
            Prodotto prodotto = catalogo.findProductByID(productId);

            if (prodotto == null) {
                response.sendRedirect("catalogo");
                return;
            }


            request.setAttribute("prodotto", prodotto);
            request.getRequestDispatcher("infoProduct.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("catalogo");
        }
    }
}
