package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Utente;
import model.UtenteDao;
import model.Ordine;
import model.OrdineDAO;

/**
 * Servlet implementation class RegistrationControl
 */
public class RegistrationControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UtenteDao model = new UtenteDao();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationControl() {
        super();
        System.out.println("RegistrationControl: costruttore invocato");
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("doGet invocato con action: " + action);
        
        if (action != null && action.equalsIgnoreCase("logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("Home.jsp");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("doPost invocato con action: " + action);

        try {
            if (action != null) {
                
                if (action.equalsIgnoreCase("insert")) { 
                    String email = request.getParameter("Email");
                    String pass = request.getParameter("PasswordCliente");
                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    String IBAN = request.getParameter("Iban");
                    String dataNascitaStr = request.getParameter("data_di_nascita");
                    int IVA = Integer.parseInt(request.getParameter("IVA"));
                   // int tipo_account = Integer.parseInt(request.getParameter("Tipo_account"));
                    
                    Utente bean = new Utente();
                    bean.setEmail(email);
                    bean.setPass(pass);
                    bean.setIBAN(IBAN);
                    bean.setCognome(cognome);
                    bean.setNome(nome);
                    bean.setIVA(IVA);
                   // bean.setTipo_account(tipo_account);
                    bean.setData(java.sql.Date.valueOf(dataNascitaStr));
                    
                    model.doSave(bean);
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("IVA", bean.getIVA() );
                    session.setAttribute("Email", bean.getEmail());
                    session.setAttribute("cognome", bean.getCognome());
                    session.setAttribute("nome", bean.getNome());
                    session.setAttribute("PasswordCliente", bean.getPass());
                    session.setAttribute("Iban", bean.getIBAN());
                    session.setAttribute("Tipo_account", 0);
                    session.setAttribute("data_di_nascita", bean.getData());
                    //response.sendRedirect(request.getContextPath() + "/registrazione-conferma.jsp");

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registrazione-conferma.jsp");
                    dispatcher.forward(request, response);
                }
                
                if (action.equalsIgnoreCase("login")) {
                    String email = request.getParameter("Email");
                    String pwd = request.getParameter("PasswordCliente");
                    
                    try {
                        UtenteDao dao = new UtenteDao();
                        Utente utente = dao.doRetrieveByEmail(email);
                        
                        if (utente != null && utente.getPass().equals(pwd)) {
                            HttpSession session = request.getSession();
                            session.setAttribute("IVA", utente.getIVA() );
                            session.setAttribute("Email", utente.getEmail());
                            session.setAttribute("cognome", utente.getCognome());
                            session.setAttribute("nome", utente.getNome());
                            session.setAttribute("PasswordCliente", utente.getPass());
                            session.setAttribute("Iban", utente.getIBAN());
                            session.setAttribute("Tipo_account", utente.getTipo_account());
                            session.setAttribute("data_di_nascita", utente.getData());
                            
                            if (utente.getTipo_account() == 0) {
                                response.sendRedirect(request.getContextPath() + "/Home.jsp");
                            } else if (utente.getTipo_account() == 1) {
                                response.sendRedirect(request.getContextPath() + "/Amministratore.jsp");
                            }
                        } else {
                            request.setAttribute("errore", "Email o password non validi");
                            request.getRequestDispatcher("/pagina_accesso.jsp").forward(request, response);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        request.setAttribute("errore", "Errore del database: " + e.getMessage());
                        request.getRequestDispatcher("/pagina_accesso.jsp").forward(request, response);
                    }
                }
                
           /*    if (action.equalsIgnoreCase("searchByEmail")) { 
                    try {
                        String email = request.getParameter("Email");
                        
                        UtenteDao dao = new UtenteDao();
                        List<Utente> utenti = dao.searchByEmail(email);
                        
                        Gson gson = new Gson();
                        String json = gson.toJson(utenti);
                        
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(json);
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                        request.setAttribute("errore", "Errore del database: " + e.getMessage());
                    }
                }*/
                
              /*  if (action.equalsIgnoreCase("modifica")) {
                    String email = request.getParameter("Email");
                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    
                    String IBAN = request.getParameter("Iban");
                    String pass = request.getParameter("PasswordCliente");
                    String dataNascitaStr = request.getParameter("data_di_nascita");
                    int IVA = Integer.parseInt(request.getParameter("IVA"));
                    int tipo_account = Integer.parseInt(request.getParameter("Tipo_account"));
                    
                    Utente utente = new Utente();
                    utente.setEmail(email);
                    utente.setNome(nome);
                    utente.setCognome(cognome);
                    utente.setIVA(IVA);
                    utente.setPass(pass);
                    
                    utente.setIBAN(IBAN);
                    utente.setData(java.sql.Date.valueOf(dataNascitaStr));
                    utente.setTipo_account(tipo_account);
                    
                    OrdineDAO ordine = new OrdineDAO();
                    UtenteDao utenteDao = new UtenteDao();
                    utenteDao.doUpdate(utente);
                    ArrayList<Ordine> ordini = (ArrayList<Ordine>) ordine.searchByEmail(email);
                    HttpSession session = request.getSession();
                    session.setAttribute("Email", utente.getEmail());
                    session.setAttribute("cognome", utente.getCognome());
                    session.setAttribute("PasswordCliente", utente.getPass());
                    session.setAttribute("IVA", utente.getIVA());
                    session.setAttribute("Iban", utente.getIBAN());
                    session.setAttribute("nome", utente.getNome());
                   
                    session.setAttribute("data_di_nascita", utente.getData());
                    request.setAttribute("ordini", ordini);
                    
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Profilo.jsp");
                    dispatcher.forward(request, response);
                }*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore2", "Mail già usata");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pagina_accesso.jsp");
            dispatcher.forward(request, response);
        }
    }
}
            
