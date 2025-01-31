package control;

import enumerativeTypes.Ruolo;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserManagement.Utente;
import remoteInterfaces.UserServiceRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private UserServiceRemote userService;




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Utente> clienti= userService.findAllUsers();
        for(Utente u:clienti){
            System.out.println(u.toString());
        }
        request.setAttribute("clienti", clienti);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // Recupero i parametri dalla richiesta POST
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Verifica l'esistenza dell'utente nel database
        Utente loggedUser = userService.findUserByEmail(email);

        // Se l'utente esiste e la password è corretta
        if (loggedUser != null && loggedUser.getPassword().equals(password)) {
            // Imposto l'utente loggato come attributo per la JSP
            request.getSession().setAttribute("loggedUser", loggedUser);
            // Controllo il ruolo dell'utente per il reindirizzamento
            if (loggedUser.getRuolo() == Ruolo.FORNITORE) {
                response.sendRedirect(request.getContextPath()+"/Profile.jsp");

            } else if (loggedUser.getRuolo() == Ruolo.MAGAZZINIERE) {
                response.sendRedirect(request.getContextPath()+"/Profile.jsp");
            } else if (loggedUser.getRuolo() == Ruolo.GESTOREORDINI) {
                response.sendRedirect(request.getContextPath()+"/Profile.jsp");

            } else if (loggedUser.getRuolo() == Ruolo.CLIENTE) {
                response.sendRedirect(request.getContextPath()+"/home2.jsp");
            }
        } else {
            // Imposto un messaggio di errore se il login fallisce
            request.setAttribute("loginError", "Login fallito. Email o password errati.");


            // Reindirizzo alla pagina JSP di login
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        }
}
