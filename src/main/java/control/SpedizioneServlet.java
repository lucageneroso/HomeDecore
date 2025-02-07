package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SpedizioneServlet")
public class SpedizioneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Recupero i dati dal form
        String indirizzo = request.getParameter("indirizzo");
        String metodoConsegna = request.getParameter("metodoConsegna");
        String spedizioneRapida = request.getParameter("spedizioneRapida") != null ? "Sì" : "No";

        // Salvo i dati nella sessione
        session.setAttribute("indirizzo", indirizzo);
        session.setAttribute("metodoConsegna", metodoConsegna);
        session.setAttribute("spedizioneRapida", spedizioneRapida);

        // Reindirizza alla pagina di pagamento
        response.sendRedirect("RiepilogoAcquisto.jsp");
    }
}