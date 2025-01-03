package test;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDao;
import model.Prodotto;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductDao productDao = new ProductDao();

    public TestServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Collection<Prodotto> products = productDao.doRetrieveAll();
            for (Prodotto product : products) {
                System.out.println("Product ID: " + product.getID());
                System.out.println("Product Name: " + product.getNome());
                System.out.println("Product Description: " + product.getDescrizione());
                System.out.println("Product Price: " + product.getPrezzo());
                System.out.println("Product Quantity: " + product.getQuantita());
                System.out.println("Product Category: " + product.getCategoria());
                System.out.println("Product Discount: " + product.getSconto());
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

