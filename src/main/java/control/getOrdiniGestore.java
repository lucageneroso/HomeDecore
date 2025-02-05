package control;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.Cart;
import model.OrderManagement.Ordine;
import model.OrderManagement.Prodotto;
import model.UserManagement.GestoreOrdini;
import model.UserManagement.GestoreOrdiniDTO;
import model.UserManagement.Utente;
import remoteInterfaces.CartServiceRemote;
import remoteInterfaces.OrderServiceRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;


@WebServlet("/ordini")
public class getOrdiniGestore extends HttpServlet {

    @EJB OrderServiceRemote orderService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Ordini id prova: \n");
        List<GestoreOrdini> gestori= orderService.findAllGestoreOrdini();
        System.out.println("Gestori: "+gestori);
        List<Ordine> ordini=orderService.findOrdersByGestore( gestori.get(0).getId() );

        System.out.println("Ordini: "+ordini);



        //request.setAttribute("prodotti", prodotti);
        //request.getRequestDispatcher("tuttiProdotti.jsp").forward(request, response);
    }
}
