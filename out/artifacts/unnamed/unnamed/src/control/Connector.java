package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

//Classe per controllare la connessione al database
public class Connector {

    public static void main(String[] args) {
        // Parametri di connessione al database
        String url = "jdbc:mysql://localhost:3306/ingrosso";
        String username = "root";
        String password = "root";

        Connection connection = null;

        try {
            // Carica il driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Stabilisce la connessione
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connessione al database riuscita!");

        } catch (ClassNotFoundException e) {
            System.out.println("Errore: Driver JDBC non trovato.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Errore: Connessione al database fallita.");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
