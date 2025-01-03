import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
/*
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserService ejb;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Logica di autenticazione
        if (username.equals("admin") && password.equals("admin")) {
            response.sendRedirect("catalogo.xhtml");
        } else {
            response.sendRedirect("login.xhtml?error=true");
        }
    }
}
*/