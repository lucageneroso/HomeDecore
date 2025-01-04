package control;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import remoteInterfaces.CatalogoRemote;
import service.Catalogo;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {

    //@EJB(mappedName = "java:app/HomeDecore/Catalogo")
    @EJB
    CatalogoRemote catalogo;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (catalogo == null) {
            try {
                InitialContext ctx = new InitialContext();
                catalogo = (Catalogo) ctx.lookup("java:app/HomeDecore/Catalogo");
            } catch (NamingException e) {
                throw new ServletException("Failed to lookup Catalogo EJB", e);
            }
        }

        System.out.println("Catalogo "+catalogo);
        // Recupera i prodotti dal database
        List<Prodotto> prodotti = catalogo.getProducts();
        System.out.println(prodotti);
        request.setAttribute("prodotti", prodotti);
        request.getRequestDispatcher("tuttiProdotti.jsp").forward(request, response);
    }

}
