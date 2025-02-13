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
import model.OrderManagement.Prodotto;
import model.RequestManagement.ProductRequest;
import model.UserManagement.Utente;
import remoteInterfaces.CatalogoRemote;
import remoteInterfaces.RequestServiceRemote;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/RichiestaProdottoServlet")
public class RichiestaProdottoServlet extends HttpServlet {

    @EJB RequestServiceRemote requestService;
    @EJB CatalogoRemote catalogo;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);




        if (session != null) {

            Utente utente = (Utente) session.getAttribute("loggedUser");
            Long userID= utente.getId();

            if (utente.getRuolo().equals(Ruolo.MAGAZZINIERE)){


                String idParam = request.getParameter("id");
                String idParamFornitore = request.getParameter("idFornitore");
                String idParamProd = request.getParameter("id");
                String quantityParam = request.getParameter("quantity");
                String note = request.getParameter("note"); // Leggi la nota

                if (idParam == null || quantityParam == null) {
                    response.getWriter().write("Errore: Dati mancanti.");
                    return;
                }

                long id = Long.parseLong(idParam);
                long idFornitore = Long.parseLong(idParamFornitore);
                int idProd = Integer.parseInt(idParamProd);
                int quantity = Integer.parseInt(quantityParam);

                // Logica per gestire la richiesta, ad esempio aggiorna la disponibilità o invia una notifica
                ProductRequest productRequest= new ProductRequest(userID, idFornitore, LocalDateTime.now(),  idProd,quantity, note);
                requestService.addRequest(productRequest);

                /*
                Prodotto prodotto= catalogo.findProductByID(idProd);
                if (prodotto != null) {

                    if( !prodotto.isInMagazzino() ){
                        prodotto.setInMagazzino(true);
                        // Salva lo stato nel database
                        catalogo.updateProduct(prodotto);
                        System.out.println("Prodotto aggiunto in magazzino");
                    }

                    } else {
                    response.getWriter().write("Prodotto non trovato");
                }

                 */






                System.out.println("Richiesta aggiunto");




            }

            else{
                System.out.println("Ruolo non accettato");
            }


        }else{
            response.getWriter().println("Sessione non esistente.");
        }
    }






}
