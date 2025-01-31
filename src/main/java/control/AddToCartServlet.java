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

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    @EJB
    private CartServiceRemote cartService;

    @EJB
    private CatalogoRemote catalogo;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdStr = request.getParameter("productId");
        String quantityStr = request.getParameter("quantity");

        if (productIdStr == null || quantityStr == null) {
            response.sendRedirect("catalogo");
            return;
        }

        try {
            int productId = Integer.parseInt(productIdStr);
            int quantity = Integer.parseInt(quantityStr);
            Prodotto prodotto = catalogo.findProductByID(productId);

            if (prodotto == null) {
                response.sendRedirect("catalogo");
                return;
            }

            HttpSession session = request.getSession();
            Utente utente = (Utente) session.getAttribute("loggedUser");
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }

            ItemCart item = new ItemCart(prodotto, quantity);
            cart.addItem(item);

            session.setAttribute("cartTotal", cart.calculateTotal());
            response.sendRedirect("cart.jsp");
        } catch (NumberFormatException e) {
            response.sendRedirect("catalogo");
        }
    }
}