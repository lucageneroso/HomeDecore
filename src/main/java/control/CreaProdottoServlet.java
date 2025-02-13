package control;

import java.io.IOException;
import java.io.InputStream;

import enumerativeTypes.Categoria;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.OrderManagement.Prodotto;
import remoteInterfaces.CatalogoRemote;

@WebServlet("/CreaProdottoServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // Max 5MB per l'immagine
public class CreaProdottoServlet extends HttpServlet {

    @EJB CatalogoRemote catalogo;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String categoriaStr = request.getParameter("categoria");
        String prezzoStr = request.getParameter("prezzo");
        Part filePart = request.getPart("immagine");

        if (nome == null || nome.trim().isEmpty() || prezzoStr == null || categoriaStr == null) {
            response.getWriter().write("{\"status\":\"Errore\",\"message\":\"}");
            //response.sendRedirect("paginaCreazione.jsp?error=InvalidInput");
            return;
        }

        double prezzo;
        try {
            prezzo = Double.parseDouble(prezzoStr);
            if (prezzo < 0.99 || prezzo > 999.99) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            response.getWriter().write("{\"status\":\"Errore\",\"message\":\"}");
            //response.sendRedirect("paginaCreazione.jsp?error=InvalidPrice");
            return;
        }

        // Validazione categoria
        Categoria categoria;
        try {
            categoria = Categoria.valueOf(categoriaStr);
        } catch (IllegalArgumentException e) {
            response.getWriter().write("{\"status\":\"Errore\",\"message\":\"}");
            //response.sendRedirect("paginaCreazione.jsp?error=InvalidCategory");
            return;
        }

        InputStream fileContent = filePart != null ? filePart.getInputStream() : null;

        Prodotto prodotto= new Prodotto(nome, descrizione, prezzo, null, categoria, 0, false, false);
        catalogo.addProduct(prodotto);
        System.out.println("Prodotto creato con successo");
        response.getWriter().write("{\"status\":\"success\"}");

        // Qui puoi inserire il prodotto nel database

        response.sendRedirect("Profile.jsp");
    }
}

