package control;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderManagement.ItemCart;
import model.OrderManagement.Prodotto;
import model.UserManagement.Magazziniere;
import remoteInterfaces.CatalogoRemote;
import service.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet("/CompleteOrderServlet")
public class CompleteOrderServlet extends HttpServlet {


    @EJB
    CatalogoRemote catalogo;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Reindirizza le richieste GET a POST
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<ItemCart> elementi= (List<ItemCart>) session.getAttribute("items");

        List<Prodotto> prodotti = catalogo.getProductsInMagazzino();

        // Aggiorna il magazzino scalando le quantità acquistate
        for (ItemCart item : elementi) {
            for (Prodotto prodotto : prodotti) {
                if (prodotto.getId() == item.getProdotto().getId()) {
                    int nuovaQuantita = prodotto.getDisponibilita() - item.getQuantity();
                    if (nuovaQuantita >= 0) {
                        prodotto.setDisponibilita(nuovaQuantita);
                        catalogo.updateProduct(prodotto); // aggiorniamo la scorta nel DB
                    }
                }
            }
        }
         //Reindirizza al riepilogo acquisto
        response.sendRedirect(request.getContextPath() + "/RiepilogoAcquisto.jsp");





    }
    }

