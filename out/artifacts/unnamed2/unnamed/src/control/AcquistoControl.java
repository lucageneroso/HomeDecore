package control;
import model.AcquistoDao;
import model.Cart;
import model.DettagliOrdine;
import model.Ordine;
import model.OrdineDAO;
import model.Prodotto;
import model.ProductDao;
import model.DettagliOrdineDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class AcquistoControl
 */
@WebServlet("/AcquistoControl")
public class AcquistoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	AcquistoDao	model = new AcquistoDao();
    public AcquistoControl() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		String action = request.getParameter("action");
		
		System.out.println(action);
		
		if (action != null) {
			
			if (action.equalsIgnoreCase("visualizza")) {
				if(cart.getProducts().size()==0) {
					request.setAttribute("errore", "Il carrello è vuoto. Si prega di inserire almeno un prodotto prima di procedere all'acquisto.\n");
					System.out.println("Carrello vuoto");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/product?action=viewC");
					dispatcher.forward(request, response);
				}
				
				
				if(request.getSession().getAttribute("Email")!= null) {
					
				String pagamento = request.getParameter("Pagamento");
				String numeroCarta = request.getParameter("card-number");
				String titolareCarta = request.getParameter("card-holder");
				String scadenzaCarta = request.getParameter("expiration-date");
				String cvv = request.getParameter("cvv");
				String TipoC = request.getParameter("delivery-option");
				String indirizzo = request.getParameter("address");
				String citta = request.getParameter("citta");
				String cap = request.getParameter("cap");
				String provincia = request.getParameter("provincia");
				request.setAttribute("provincia", provincia);
				request.setAttribute("citta", citta);
				request.setAttribute("cap", cap);
				request.setAttribute("indirizzo", indirizzo);
				request.setAttribute("TipoC", TipoC);
				request.setAttribute("cart",cart);
				request.setAttribute("Pagamento", pagamento);
				request.setAttribute("numeroCarta", numeroCarta);
				request.setAttribute("card-holder", titolareCarta);
				request.setAttribute("scadenzaCarta",scadenzaCarta);
				request.setAttribute("cvv", cvv);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ConfermaOrdine.jsp");
				dispatcher.forward(request, response);
				}
				else {
					request.setAttribute("errore3", "Per completare la procedura devi accedere");
                    request.getRequestDispatcher("/pagina_accesso.jsp").forward(request, response);
				}
			}
			if (action.equalsIgnoreCase("completa")) {
				
				/*
				String pagamento = request.getParameter("Pagamento");
				String numeroCarta = request.getParameter("NumeroC");
				String titolareCarta = request.getParameter("TitolareC");
				String scadenzaCarta = request.getParameter("ScadenzaC");
				String cvv = request.getParameter("cvv");
				String TipoC = request.getParameter("Tipocons");
				*/
				
				
				
				String indirizzo = request.getParameter("indirizzo");
				String citta = request.getParameter("citta");
				Integer cap = Integer.parseInt(request.getParameter("cap"));
				String provincia = request.getParameter("provincia");
				int numProdotti = Integer.parseInt(request.getParameter("numProdotti"));
				Double totale = Double.parseDouble(request.getParameter("totale"));
				String stato= "In preparazione";
				
				int IVA=0;
				String email=null;
				
				Enumeration<String> attributeNames = request.getSession().getAttributeNames();
				while (attributeNames.hasMoreElements()) {
		            String attributeName = attributeNames.nextElement();
		            if(attributeName=="IVA") {
		            	Object attributeValue = request.getSession().getAttribute(attributeName);
		            	System.out.print(attributeName+" "+attributeValue);
		            	IVA= (Integer)attributeValue;
		            }
		            if(attributeName=="Email") {
		            	Object attributeValue = request.getSession().getAttribute(attributeName);
		            	System.out.print(attributeName+" "+attributeValue);
		            	email= (String)attributeValue;
		            }
		        }
				
				
				
				System.out.println(indirizzo+" "+citta+" "+cap+" "+provincia+" "+numProdotti+" "+totale+" "+IVA+" "+email+" "+stato);
				
				OrdineDAO ordinedao= new OrdineDAO();
				int id=-1;
				try {
					id = ordinedao.doSave(indirizzo, totale, stato, numProdotti, IVA, email, citta, cap, provincia);
					System.out.println("ordine salvato");
					
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				ProductDao pDAO= new ProductDao();
				DettagliOrdineDAO dettagliordinedao= new DettagliOrdineDAO();
				for(Prodotto p: cart.getProducts()){
					try {
						dettagliordinedao.doSave(id, p.getID());
						pDAO.reduce(p.getID());
					} catch (SQLException e) {
						e.printStackTrace(); 
					}
				}
				
				
				
				
				
				request.getSession().removeAttribute("cart");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.jsp");
				dispatcher.forward(request, response);
				
			}
	}

}}
