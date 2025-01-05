package control;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User.Utente;
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
        try {
            InitialContext ctx= new InitialContext();
            userService= (UserServiceRemote) ctx.lookup("java:app/HomeDecore/UserService");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        List<Utente> clienti= userService.findAllUsers();
        for(Utente u:clienti){
            System.out.println(u.toString());
        }
        request.setAttribute("clienti", clienti);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            InitialContext ctx= new InitialContext();
            userService= (UserServiceRemote) ctx.lookup("java:app/HomeDecore/UserService");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        // Recupero i parametri dalla richiesta POST
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Verifica l'esistenza dell'utente nel database
        Utente loggedUser = userService.findUserByEmail(email);

        // Se l'utente esiste e la password è corretta
        if (loggedUser != null && loggedUser.getPassword().equals(password)) {
            // Imposto l'utente loggato come attributo per la JSP
            request.setAttribute("loggedUser", loggedUser);
            request.getRequestDispatcher("/Home.jsp").forward(request, response);
        } else {
            // Imposto un messaggio di errore se il login fallisce
            request.setAttribute("loginError", "Login fallito. Email o password errati.");
        }

        // Reindirizzo alla pagina JSP di login
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
