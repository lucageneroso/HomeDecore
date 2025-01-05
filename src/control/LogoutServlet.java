package control;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // Invalida la sessione eliminando tutti gli attributi
        
        // Puoi aggiungere un messaggio di logout se necessario
        //request.setAttribute("logoutMessage", "Logout effettuato con successo.");

        // Reindirizza alla pagina di login o ad un'altra pagina di destinazione
        response.sendRedirect(request.getContextPath() + "/Home.jsp");
    }
}