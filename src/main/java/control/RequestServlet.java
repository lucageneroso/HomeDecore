package control;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderManagement.Ordine;
import model.RequestManagement.Request;
import model.UserManagement.GestoreOrdini;
import remoteInterfaces.OrderServiceRemote;
import remoteInterfaces.RequestServiceRemote;

import java.io.IOException;
import java.util.List;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {

    @EJB RequestServiceRemote requestServiceRemote;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Requests: \n");


        List<Request> richieste= requestServiceRemote.findAll();
        System.out.println("Requests: "+richieste.get(0));

        requestServiceRemote.cambiaStato(richieste.get(0));
        richieste= requestServiceRemote.findAll();
        System.out.println("Requests: "+richieste.get(0));



    }
}
