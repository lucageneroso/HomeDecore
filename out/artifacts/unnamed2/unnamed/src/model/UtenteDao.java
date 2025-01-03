package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UtenteDao {
    private static DataSource ds;
    private static final String TABLE_NAME = "Cliente";

    static {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/ingrosso");
        } catch (NamingException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    private static final String INSERT_UTENTE_SQL = "INSERT INTO Cliente (Email, nome, cognome, data_di_nascita, PasswordCliente, IVA, Tipo_account, Iban) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_UTENTE_BY_EMAIL = "SELECT * FROM Cliente WHERE Email = ?";
    private static final String UPDATE_UTENTE_SQL = "UPDATE Cliente SET nome = ?, cognome = ?, data_di_nascita = ?, PasswordCliente = ?, IVA = ?, Tipo_account = ?, Iban = ? WHERE Email = ?";
    private static final String DELETE_UTENTE_SQL = "DELETE FROM Cliente WHERE Email = ?";
    private static final String SEARCH_BY_EMAIL = "SELECT * FROM Cliente WHERE Email LIKE ?";

    public void doSave(Utente utente) throws SQLException {
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_UTENTE_SQL)) {
            preparedStatement.setString(1, utente.getEmail());
            preparedStatement.setString(2, utente.getNome());
            preparedStatement.setString(3, utente.getCognome());
            preparedStatement.setDate(4, utente.getData());
            preparedStatement.setString(5, utente.getPass());
            preparedStatement.setInt(6, utente.getIVA());
            preparedStatement.setInt(7, utente.getTipo_account());
            preparedStatement.setString(8, utente.getIBAN());

            preparedStatement.executeUpdate();
        }
    }

    public Utente doRetrieveByEmail(String email) throws SQLException {
        Utente utente = null;
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UTENTE_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                utente = new Utente();
                utente.setEmail(rs.getString("Email"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setData(rs.getDate("data_di_nascita"));
                utente.setPass(rs.getString("PasswordCliente"));
                utente.setIVA(rs.getInt("IVA"));
                utente.setTipo_account(rs.getInt("Tipo_account"));
                utente.setIBAN(rs.getString("Iban"));
            }
        }
        return utente;
    }

    public void doUpdate(Utente utente) throws SQLException {
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_UTENTE_SQL)) {
            preparedStatement.setString(1, utente.getNome());
            preparedStatement.setString(2, utente.getCognome());
            preparedStatement.setDate(3, utente.getData());
            preparedStatement.setString(4, utente.getPass());
            preparedStatement.setInt(5, utente.getIVA());
            preparedStatement.setInt(6, utente.getTipo_account());
            preparedStatement.setString(7, utente.getIBAN());
            preparedStatement.setString(8, utente.getEmail());

            preparedStatement.executeUpdate();
        }
    }

    public void doDelete(String email) throws SQLException {
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_UTENTE_SQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        }
    }

    public List<Utente> searchByEmail(String emailPattern) throws SQLException {
        List<Utente> utenti = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_EMAIL)) {
            preparedStatement.setString(1, "%" + emailPattern + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Utente utente = new Utente();
                utente.setEmail(rs.getString("Email"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setData(rs.getDate("data_di_nascita"));
                utente.setPass(rs.getString("PasswordCliente"));
                utente.setIVA(rs.getInt("IVA"));
                utente.setTipo_account(rs.getInt("Tipo_account"));
                utente.setIBAN(rs.getString("Iban"));
                utenti.add(utente);
            }
        }
        return utenti;
    }



    public List<Utente> doRetrieveAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Utente> utenti = new ArrayList<>();
        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        try {
            connection = ds.getConnection(); // Assicurati che ds sia inizializzato correttamente
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Utente utente = new Utente();
                utente.setEmail(rs.getString("Email"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setPass(rs.getString("PasswordCliente"));
                utente.setIBAN(rs.getString("Iban")); // Assumendo che ci sia una colonna "IBAN" nel database
                utente.setIVA(rs.getInt("IVA")); // Assumendo che ci sia una colonna "IVA" nel database
                utente.setTipo_account(rs.getInt("Tipo_account")); // Assumendo che ci sia una colonna "tipo_account" nel database
                utente.setData(rs.getDate("data_di_nascita")); // Assumendo che ci sia una colonna "data_di_nascita" nel database

                utenti.add(utente);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return utenti;
    }
}


