package control;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.Cart;
import model.UserManagement.Cliente;
import model.UserManagement.Utente;
import remoteInterfaces.CartServiceRemote;
import remoteInterfaces.CatalogoRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @EJB
    CartServiceRemote cartService;
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera i prodotti dal database
        HttpSession session = request.getSession();
        Utente loggedUser = (Utente) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Cart sessionCart = (Cart) session.getAttribute("cart");
        if (sessionCart == null) {
            sessionCart= cartService.findCartByCostumer(loggedUser.getId());
            if (sessionCart == null) {
                sessionCart = new Cart();
            }
            session.setAttribute("cart", sessionCart);
        }

       try {
            InitialContext ctx = new InitialContext();
            cartService = (CartServiceRemote) ctx.lookup("java:app/HomeDecore/CartService!remoteInterfaces.CartServiceRemote");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
