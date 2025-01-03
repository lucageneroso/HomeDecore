package control;


import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.*;

import model.Cart;
import model.Prodotto;
import model.ProductDao;

/**
 * Servlet implementation class ProductControl
 */
@MultipartConfig
public class ProductControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ProductDao model = new ProductDao();

    public ProductControl() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        System.out.println(action);

        try {
            if (action != null) {
            	
                if (action.equalsIgnoreCase("addC")) {
                	
                	
                    int id = Integer.parseInt(request.getParameter("id"));
                    cart.addProduct(model.doRetrieveByKey(id));
                    request.setAttribute("cart", cart);

                    // Ottieni il riferimento alla pagina precedente
                    String referer = request.getHeader("Referer");

                    // Reindirizza la richiesta alla pagina precedente
                    response.sendRedirect(referer);
                    
                    
                    
                } 
                
                else if (action.equalsIgnoreCase("svuotaC")) {
                    cart.deleteAllProduct();
                    request.setAttribute("cart", cart);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/carrello.jsp");
                    dispatcher.forward(request, response);
                } 
                
                else if (action.equalsIgnoreCase("deleteC")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    cart.deleteProduct(model.doRetrieveByKey(id));
                    request.setAttribute("cart", cart);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/carrello.jsp");
                    dispatcher.forward(request, response);
                } 
                
                else if (action.equalsIgnoreCase("read")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.removeAttribute("product");
                    request.setAttribute("product", model.doRetrieveByKey(id));
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductDetails.jsp");
                    dispatcher.forward(request, response);
                } 
                
                else if (action.equalsIgnoreCase("delete")) {
                    int id = Integer.valueOf(request.getParameter("id"));
                    model.doDelete(id);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Amministratore.jsp");
                    dispatcher.forward(request, response);
                } 
                
                else if (action.equalsIgnoreCase("search")) {
                    String nome = request.getParameter("nome");
                    try {
                        request.removeAttribute("products");
                        request.setAttribute("products", model.searchProducts(nome));
                    } catch (SQLException e) {
                        System.out.println("Error:" + e.getMessage());
                    }

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
                    dispatcher.forward(request, response);
                } 
                
                else if (action.equalsIgnoreCase("updateq")) {
                	 String id = request.getParameter("id");
                     String quantita = request.getParameter("quantita");
                    
                    model.doupdateq(id,quantita);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Amministratore.jsp");
                    dispatcher.forward(request, response);
                } 
                
                else if (action.equalsIgnoreCase("change")) {
                    int idfoto = Integer.parseInt(request.getParameter("id"));
                    int idprod = Integer.parseInt(request.getParameter("productid"));
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductDetails.jsp");
                    dispatcher.forward(request, response);
                } 
                
                else if (action.equalsIgnoreCase("dettaglio")) {
                    String categoria = request.getParameter("Categoria");
                    try {
                        request.removeAttribute("products");
                        if (categoria != null) {
                            request.setAttribute("products", model.doRetrieveByCategoria(categoria));
                        }
                    } catch (SQLException e) {
                        System.out.println("Error:" + e.getMessage());
                    }

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
                    dispatcher.forward(request, response);
                } 
                
                else if (action.equalsIgnoreCase("viewC")) {
                    request.setAttribute("cart", cart);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/carrello.jsp");
                    dispatcher.forward(request, response);
                } 
                
                else if (action.equalsIgnoreCase("edit")) {
                    String id = request.getParameter("id");
                    String prezzo = request.getParameter("prezzo");
                    if (prezzo != null && !prezzo.isEmpty()) {
                        model.cambiaprezzo(id, prezzo);
                    }
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Amministratore.jsp");
                    dispatcher.forward(request, response);

                } 
                
                else if (action.equalsIgnoreCase("insert")) {
                	
                	int id = Integer.parseInt(request.getParameter("id"));
                	String descrizione = request.getParameter("descrizione");
                    double prezzo = Double.parseDouble(request.getParameter("prezzo"));
                    int quantita = Integer.parseInt(request.getParameter("quantita"));
                    String nome = request.getParameter("nome");
                    InputStream inputStream = request.getPart("foto").getInputStream();
                    String categoria = request.getParameter("categoria");
                    byte[] bytes = null;
                    try {
                        bytes = IOUtils.toByteArray(inputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    inputStream.close();

                    Prodotto prodotto = new Prodotto();
                    prodotto.setDescrizione(descrizione);
                    prodotto.setPrezzo(prezzo);
                    prodotto.setQuantita(quantita);
                    prodotto.setImg(bytes);
                    prodotto.setNome(nome);
                    prodotto.setCategoria(categoria);

                    try {
                        model.doSave(prodotto);
                        response.sendRedirect(request.getContextPath() + "/Amministratore.jsp");
                        System.out.println("Salvato nel database");
                    } catch (SQLException e) {
                        // Errore del database, gestisci l'errore
                        e.printStackTrace();
                        request.setAttribute("errore", "Errore del database: " + e.getMessage());
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Errore.jsp");
                        dispatcher.forward(request, response);
                    }
                } else if (action.equalsIgnoreCase("all")) {
                    try {
                        request.removeAttribute("products");
                        request.setAttribute("products", model.doRetrieveAll());
                    } catch (SQLException e) {
                        System.out.println("Error:" + e.getMessage());
                    }

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }

        request.getSession().setAttribute("cart", cart);
        request.setAttribute("cart", cart);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String action = request.getParameter("action");
    	System.out.println(action);
    	
    	if (action.equalsIgnoreCase("insert")) {
        	
        	int id = Integer.parseInt(request.getParameter("id"));
        	String descrizione = request.getParameter("descrizione");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int quantita = Integer.parseInt(request.getParameter("quantita"));
            String nome = request.getParameter("nome");
            InputStream inputStream = request.getPart("foto").getInputStream();
            String categoria = request.getParameter("categoria");
            byte[] bytes = null;
            try {
                bytes = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream.close();

            Prodotto prodotto = new Prodotto();
            prodotto.setID(id);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo(prezzo);
            prodotto.setQuantita(quantita);
            prodotto.setImg(bytes);
            prodotto.setNome(nome);
            prodotto.setCategoria(categoria);
            prodotto.setSconto(0);
            System.out.println(prodotto);

            try {
                model.doSaveAmministratore(prodotto);
                response.sendRedirect(request.getContextPath() + "/Amministratore.jsp");
                System.out.println("Salvato nel database");
            } catch (SQLException e) {
                // Errore del database, gestisci l'errore
                e.printStackTrace();
                request.setAttribute("errore", "Errore del database: " + e.getMessage());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Errore.jsp");
                dispatcher.forward(request, response);
            }
        } else if (action.equalsIgnoreCase("all")) {
            try {
                request.removeAttribute("products");
                request.setAttribute("products", model.doRetrieveAll());
            } catch (SQLException e) {
                System.out.println("Error:" + e.getMessage());
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
            dispatcher.forward(request, response);
        }
        //doGet(request, response);
    }
}
