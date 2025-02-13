package control;

import enumerativeTypes.Ruolo;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.Ordine;
import model.UserManagement.Utente;
import remoteInterfaces.OrderServiceRemote;
import remoteInterfaces.UserServiceRemote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private UserServiceRemote userService;
    @EJB
    private OrderServiceRemote orderService;


    private static final String CORRECT_PASSWORD = "correctPassword123";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final int MIN_USERNAME_LENGTH = 5;
    private static final int MAX_USERNAME_LENGTH = 20;

    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        return CORRECT_PASSWORD.equals(password);
    }

    public boolean validateUsername(String username) {
        return username.length() >= MIN_USERNAME_LENGTH && username.length() <= MAX_USERNAME_LENGTH;
    }

    public boolean validateLogin(String username, String email, String password) {
        return validateUsername(username) && validateEmail(email) && validatePassword(password);
    }



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

        try {
            // Verifica l'esistenza dell'utente nel database
            Utente loggedUser = userService.findUserByEmail(email);

            // Se l'utente esiste e la password è corretta
            if (loggedUser != null && loggedUser.getPassword().equals(password)) {
                // Imposto l'utente loggato come attributo per la JSP
                request.getSession().setAttribute("loggedUser", loggedUser);

                // Recupera gli ordini a suo carico
                List<Ordine> orders = orderService.findOrdersByCostumer(loggedUser.getId());
                System.out.println("\n ecco che ordini ho trovato per l'utente " + loggedUser.getNome());
                for (Ordine o : orders) {
                    System.out.println(o.toString());
                }
                request.getSession().setAttribute("orders", orders);
            } else {
                // Imposto un messaggio di errore se il login fallisce
                request.setAttribute("loginError", "Login fallito. Email o password errati.");

                // Reindirizzo alla pagina JSP di login
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            //Controllo il ruolo dell'utente per il reindirizzamento
            if (loggedUser.getRuolo() == Ruolo.CLIENTE) {
                response.sendRedirect(request.getContextPath() + "/home2.jsp");
            } else {
                System.out.println(loggedUser.toString());
                response.sendRedirect(request.getContextPath() + "/Profile.jsp");
            }

        } catch (Exception e) {
            // Imposto un messaggio di errore se il login fallisce
            request.setAttribute("loginError", "Login fallito. Email o password errati.");

            // Reindirizzo alla pagina JSP di login
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}

