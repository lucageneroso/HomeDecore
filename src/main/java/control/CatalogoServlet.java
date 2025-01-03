package control;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera i prodotti dal database
        List<Prodotto> prodotti = ProdottoDAO.getAll();
        request.setAttribute("prodotti", prodotti);
        request.getRequestDispatcher("catalogo.xhtml").forward(request, response);
    }
}
