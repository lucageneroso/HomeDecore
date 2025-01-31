package control;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.Cart;
import remoteInterfaces.CartServiceRemote;
import service.CartService;

import java.io.IOException;

@WebServlet("/updateQuantity")
public class UpdateQuantityServlet extends HttpServlet {

    @EJB
    CartServiceRemote cartService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        Cart sessionCart = (Cart) session.getAttribute("cart");

        if (sessionCart != null) {
            sessionCart.updateProductQuantity(productId, quantity);
            session.setAttribute("cartTotal", sessionCart.calculateTotal());
            session.setAttribute("cart", sessionCart);
        }
        response.sendRedirect("cart.jsp");
    }
}


