package control;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ReviewManagement.Recensione;
import model.UserManagement.Utente;
import remoteInterfaces.RecensioneServiceRemote;
import model.ReviewManagement.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/reviews")
public class ReviewServlet extends HttpServlet {
    private RecensioneServiceRemote recensioneService;

    @Override
    public void init() throws ServletException {
        try {
            javax.naming.InitialContext ctx= new javax.naming.InitialContext();
            recensioneService = (RecensioneServiceRemote) ctx.lookup("java:app/HomeDecore/Review");
        } catch (Exception e) {
            throw new ServletException("Errore durante l'inizializzazione della servlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni il productID dalla query string
        String productIdStr = request.getParameter("productID");
        if (productIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID mancante");
            return;
        }

        try {
            int productID = Integer.parseInt(productIdStr);
            List<Recensione> reviews = recensioneService.findByProduct(productID);

            request.setAttribute("reviews", reviews);
            request.setAttribute("productID", productID);
            request.getRequestDispatcher("/productReviews.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID non valido");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Aggiungi una nuova recensione
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            Utente user= (Utente) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            Long userID=user.getId();
            String content = request.getParameter("content");
            double rating = Double.parseDouble(request.getParameter("rating"));

            Recensione nuovaRecensione = new Recensione(rating,userID, productID, content, LocalDateTime.now());
            recensioneService.addReview(nuovaRecensione);

            // Ricarica la pagina delle recensioni del prodotto
            response.sendRedirect(request.getContextPath() + "/reviews?productID=" + productID);
        } catch (Exception e) {
            throw new ServletException("Errore durante l'aggiunta della recensione", e);
        }
    }
}

