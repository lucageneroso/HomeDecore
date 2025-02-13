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

@WebServlet("/ToggleCatalogStatusServlet")
public class ToggleCatalogStatusServlet extends HttpServlet {

    @EJB
    CatalogoRemote catalogo;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("id"));
        Prodotto prodotto= catalogo.findProductByID(productId);


        if (prodotto != null) {
            boolean currentStatus = prodotto.isInCatalogo();
            prodotto.setInCatalogo(!currentStatus);  // Cambia lo stato

            // Salva lo stato nel database
            catalogo.updateProduct(prodotto);

            response.getWriter().write(currentStatus ? "Rimosso dal catalogo" : "Aggiunto al catalogo");
        } else {
            response.getWriter().write("Prodotto non trovato");
        }
    }
}
