package control;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import remoteInterfaces.CatalogoRemote;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/ModificaProdottoServlet")
public class ModificaProdottoServlet extends HttpServlet {

    @EJB CatalogoRemote catalogo;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verifica tutti i parametri ricevuti nella richiesta
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            System.out.println("Parametro ricevuto: " + paramName + " = " + request.getParameter(paramName));
        }

        // Recupera i parametri specifici
        String idParam = request.getParameter("id");
        String field = request.getParameter("field");
        String value = request.getParameter("value");

        System.out.println("Servlet: id = " + idParam + ", field = " + field + ", value = " + value);

        // Controlla se l'ID è valido
        if (idParam == null || idParam.isEmpty()) {
            response.getWriter().write("Errore: ID mancante.");
            return;
        }

        // Logica di aggiornamento
        if (field.equals("nome")) {
            catalogo.updateProductName(Long.parseLong(idParam), value);
        }
        if (field.equals("descrizione")) {
            catalogo.updateProductDesc(Long.parseLong(idParam), value);
        }
        if (field.equals("prezzo")) {
            catalogo.updateProductPrice(Long.parseLong(idParam), Long.parseLong(value));
        }
    }
}
