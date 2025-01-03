package control;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import service.Catalogo;

import java.io.IOException;
import java.util.List;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {

    @Inject Catalogo catalogo;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera i prodotti dal database
        List<Prodotto> prodotti = catalogo.getProducts();
        System.out.println(prodotti);
        request.setAttribute("prodotti", prodotti);
        request.getRequestDispatcher("catalogo.xhtml").forward(request, response);
    }

}
