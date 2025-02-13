package control;

import jakarta.servlet.http.HttpServletResponse;


import enumerativeTypes.Ruolo;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.Ordine;
import model.UserManagement.Cliente;
import model.UserManagement.Indirizzo;
import model.UserManagement.Utente;
import remoteInterfaces.OrderServiceRemote;
import remoteInterfaces.UserServiceRemote;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @EJB
    private UserServiceRemote userService;



    // Regex per validazione email
    private static final String EMAIL_REGEX = "^(?=.{7,})[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$";

    // Regex per validazione password (minimo una maiuscola, un numero, tra 4 e 20 caratteri)
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{4,20}$";

    // Metodo per validare l'email
    public boolean validateEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    // Metodo per validare la password
    public boolean validatePassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recupera i dati dal form
        String nome = request.getParameter("name");
        String cognome = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        String stato = request.getParameter("stato");
        String provincia = request.getParameter("provincia");
        String citta = request.getParameter("citta");
        String via = request.getParameter("via");
        int numCivico = Integer.parseInt(request.getParameter("numCivico"));
        int cap = Integer.parseInt(request.getParameter("cap"));


        Indirizzo indirizzo = new Indirizzo(stato, provincia, citta, via, numCivico, cap);


        Cliente nuovoCliente = new Cliente(nome, cognome, email, password, indirizzo);
        userService.addUser(nuovoCliente);
        response.sendRedirect("home.jsp");




    }

}

