package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ProductDao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DettagliOrdineDAO {

    private static DataSource ds;

    static {
        try {
            InitialContext initialContext = new InitialContext();
            ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/ingrosso");
        } catch (NamingException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
    

    public void doSave(int idOrdine, int idProdotto) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = ds.getConnection();
            String insertSQL = "INSERT INTO DettagliOrdine (id_ordine, idProdotto, quantita) VALUES (?, ?, ?)";

            preparedStatement = conn.prepareStatement(insertSQL);
            preparedStatement.setInt(1, idOrdine);
            preparedStatement.setInt(2, idProdotto);
            preparedStatement.setInt(3,1);

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public List<Prodotto> getProdotti(int idOrdine){
    	Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Prodotto> prodotti = new ArrayList<>();
    	String selectSql = "SELECT idProdotto FROM DettagliOrdine WHERE id_ordine ="+idOrdine;
    	
    	
    	try {
    		
    		ProductDao pDAO= new ProductDao();
    		
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	int id = rs.getInt("idProdotto");           	
            	prodotti.add(pDAO.doRetrieveByKey(id));
            	
            }

        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return prodotti;
    }
    	
    	
    	
    
}