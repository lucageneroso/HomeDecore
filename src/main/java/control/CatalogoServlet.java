package control;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderManagement.Prodotto;
import remoteInterfaces.CatalogoRemote;

import java.io.IOException;
import java.util.List;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {

    @EJB
    CatalogoRemote catalogo;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Catalogo: \n");
        List<Prodotto> prodotti=catalogo.getProductsInCatalogo();
        System.out.println(prodotti);

        for(Prodotto p:prodotti){
            System.out.println(p);
        }

        request.setAttribute("prodotti", prodotti);
        request.getRequestDispatcher("tuttiProdotti.jsp").forward(request, response);


    }
}
