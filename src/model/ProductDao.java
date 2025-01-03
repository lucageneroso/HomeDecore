package model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ProductDao {

    private static DataSource ds;

    static {
        try {
            InitialContext initialContext = new InitialContext();
            ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/ingrosso");
        } catch (NamingException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

	private static final String TABLE_NAME = "Prodotto";
	
	public synchronized void doSave(Prodotto product) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    String insertSQL = "INSERT INTO " + ProductDao.TABLE_NAME + " (descrizione, prezzo, quantita,foto, categoria, sconto) VALUES (?, ?, ?,?, ?, ?, ?,?)";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(insertSQL);
	        preparedStatement.setString(1, product.getDescrizione());
	        preparedStatement.setDouble(2, product.getPrezzo());
	        preparedStatement.setInt(3, product.getQuantita());
	        InputStream inputStream = new ByteArrayInputStream(product.getImg());
	        preparedStatement.setBinaryStream(4, inputStream, product.getImg().length);
	        preparedStatement.setString(6, product.getNome());
	        preparedStatement.setString(7, product.getCategoria());
	        preparedStatement.setDouble(8, product.getSconto());
	        preparedStatement.executeUpdate();
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }
	}
	
	public synchronized void reduce(int idProdotto) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    String update = "UPDATE " + ProductDao.TABLE_NAME + " SET Quantita = Quantita - 1 WHERE idProdotto = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(update);
	        preparedStatement.setInt(1, idProdotto);
	        preparedStatement.executeUpdate();
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }
	}
	public synchronized void doSaveAmministratore(Prodotto product) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    String insertSQL = "INSERT INTO " + ProductDao.TABLE_NAME + " (idProdotto, Quantita, Prezzo, Nome ,Descrizione, Categoria, Sconto, Foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(insertSQL);
	        preparedStatement.setInt(1, product.getID());
	        preparedStatement.setInt(2, product.getQuantita());
	        preparedStatement.setDouble(3, product.getPrezzo());
	        
	        preparedStatement.setString(4, product.getNome());
	        preparedStatement.setString(5, product.getDescrizione());
	        preparedStatement.setString(6, product.getCategoria());
	        preparedStatement.setDouble(7, product.getSconto());
	        
	        InputStream inputStream = new ByteArrayInputStream(product.getImg());
	        preparedStatement.setBinaryStream(8, inputStream, product.getImg().length);
	        
	        preparedStatement.executeUpdate();
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }
	}
	
	/*public synchronized Prodotto Cambiafoto(int codef, int codp) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Prodotto bean = new Prodotto();

		String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME + " WHERE id = ?";
		String selectsql2 = "SELECT img FROM immagini WHERE cod = ?";
		String selectsql3 = "SELECT cod,img FROM immagini WHERE codprodotto = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codp);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setID(rs.getInt("id"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setCategoria(rs.getString("categoria"));
				bean.setImg(rs.getBytes("foto"));
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
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectsql2);
			preparedStatement.setInt(1, codef);

			ResultSet rs = preparedStatement.executeQuery();						
			while (rs.next()) {
			    immagine i = new immagine();
			    i.setImg(rs.getBytes("img"));
			    bean.setImg(i.getImg());								
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
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectsql3);
			preparedStatement.setInt(1, codp);

			ResultSet rs = preparedStatement.executeQuery();
			ArrayList<immagine> a = new ArrayList<immagine>();						
			while (rs.next()) {
			    immagine i = new immagine();
			    i.setId(rs.getInt("cod"));
			    i.setImg(rs.getBytes("img"));
			    a.add(i);								
			}
			bean.setAllimg(a);
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}*/
		

	public synchronized Prodotto doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Prodotto bean = new Prodotto();

		String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME + " WHERE idProdotto= ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setID(rs.getInt("idProdotto"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setQuantita(rs.getInt("Quantita"));
				bean.setCategoria(rs.getString("Categoria"));
				bean.setImg(rs.getBytes("Foto"));
				bean.setSconto(rs.getDouble("Sconto"));

			}

		}  finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	
	public synchronized Prodotto doRetrieveByName(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Prodotto bean = new Prodotto();

		String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME + " WHERE Nome= ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setID(rs.getInt("idProdotto"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setQuantita(rs.getInt("Quantita"));
				bean.setCategoria(rs.getString("Categoria"));
				bean.setImg(rs.getBytes("Foto"));
				bean.setSconto(rs.getDouble("Sconto"));

			}

		}  finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductDao.TABLE_NAME + " WHERE idProdotto = ?";


		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	public synchronized boolean doupdateq(String code, String quantita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		int result = 0;

		String deleteSQL = "UPDATE " + ProductDao.TABLE_NAME + " SET Quantita = ? WHERE idProdotto = ?";
		
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			int quantitaInt=Integer.parseInt(quantita);
			int codeInt=Integer.parseInt(code);
			
			preparedStatement.setInt(1, quantitaInt);
			preparedStatement.setInt(2, codeInt);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}
	
	
	public synchronized List<Prodotto> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<Prodotto> products = new LinkedList<Prodotto>();

		String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Prodotto bean = new Prodotto();

				bean.setID(rs.getInt("idProdotto"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setQuantita(rs.getInt("Quantita"));
				bean.setCategoria(rs.getString("Categoria"));
				bean.setImg(rs.getBytes("Foto"));
				bean.setSconto(rs.getDouble("Sconto"));	
	            
	            products.add(bean);
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
		return products;
	}
	
	public void doUpdate(Prodotto prodotto) throws SQLException {
	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        connection = ds.getConnection();

	        // Query di aggiornamento
	        String query = "UPDATE Prodotto SET Descrizione = ?, Prezzo = ?, Quantita = ?, Nome = ? WHERE idProdotto = ?";

	        statement = connection.prepareStatement(query);
	        statement.setString(1, prodotto.getDescrizione());
	        statement.setDouble(2, prodotto.getPrezzo());
	        statement.setInt(3, prodotto.getQuantita());
	        statement.setString(5, prodotto.getNome());
	        statement.setInt(6, prodotto.getID());

	        statement.executeUpdate();
	    } finally {
	        // Chiudi le risorse
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}
	
	public synchronized List<Prodotto> searchProducts(String nome) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    List<Prodotto> products = new LinkedList<Prodotto>();


	    String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME + " WHERE Nome LIKE ?";
	   
	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	     
	            preparedStatement.setString(1, "%" + nome + "%");
	        
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            Prodotto bean = new Prodotto();

	            bean.setID(rs.getInt("idProdotto"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setQuantita(rs.getInt("Quantita"));
				bean.setCategoria(rs.getString("Categoria"));
				bean.setImg(rs.getBytes("Foto"));
				bean.setSconto(rs.getDouble("Sconto"));
	            
	           
	            
	            products.add(bean);
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
	    return products;
	}
	
	public synchronized String getProductSearchResults(String nome) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;

	    try {
	        connection = ds.getConnection();
	        String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME + " WHERE Nome LIKE ?";
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setString(1, nome + "%");
	        rs = preparedStatement.executeQuery();

	        Collection<Prodotto> products = new LinkedList<>();

	        while (rs.next()) {
	            Prodotto bean = new Prodotto();

	            bean.setID(rs.getInt("idProdotto"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setQuantita(rs.getInt("Quantita"));
				bean.setCategoria(rs.getString("Categoria"));
				bean.setImg(rs.getBytes("Foto"));
				bean.setSconto(rs.getDouble("Sconto"));
	            
	           

	            products.add(bean);
	        }

	        // Creazione dell'oggetto Gson
	        Gson gson = new GsonBuilder().create();

	        // Conversione dei prodotti in una stringa JSON
	        String jsonResult = gson.toJson(products);

	        return jsonResult;
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	        return null; // o gestisci l'errore in modo appropriato nel tuo codice
	    } finally {
	        try {
	            if (rs != null)
	                rs.close();
	            if (preparedStatement != null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        } catch (SQLException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }
	}
	
	public synchronized List<Prodotto> doRetrieveByCategoria(String s1) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<Prodotto> products = new LinkedList<Prodotto>();
		

		String selectSQL = "SELECT * FROM " + ProductDao.TABLE_NAME + " WHERE Categoria = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1,s1);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Prodotto bean = new Prodotto();

				bean.setID(rs.getInt("idProdotto"));
				bean.setNome(rs.getString("Nome"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setQuantita(rs.getInt("Quantita"));
				bean.setCategoria(rs.getString("Categoria"));
				bean.setImg(rs.getBytes("Foto"));
				bean.setSconto(rs.getDouble("Sconto"));
				
				products.add(bean);
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
		return products;
	}
	

	/*public synchronized Collection<Prodotto> doRetrieveByOrdine(int numeroOrdine) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<Prodotto> products = new LinkedList<Prodotto>();

	    String selectSQL = "SELECT * FROM Prodotto ON product.id = composizione.codP WHERE composizione.numeroO = ?";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setInt(1, numeroOrdine);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            Prodotto bean = new Prodotto();

	            bean.setID(rs.getInt("id"));
	            bean.setDescrizione(rs.getString("descrizione"));
	            bean.setPrezzo(rs.getInt("prezzo"));
	            bean.setQuantita(rs.getInt("quantita"));
	            bean.setNome(rs.getString("nome"));
	            bean.setCategoria(rs.getString("categoria"));
	            bean.setSconto(rs.getDouble("sconto"));
	            Blob blob = rs.getBlob("foto");
	            byte[] imageByte = blob.getBytes(1,(int) blob.length());
	            bean.setImg(imageByte);

	            if (bean.getImg() == null) {
	                byte[] emptyImage = new byte[0];
	                bean.setImg(emptyImage);
	            }

	            products.add(bean);
	        }
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } finally {
	            if (connection != null) {
	                connection.close();
	            }
	        }
	    }

	    return products;
	}*/
	
	public synchronized void cambiaprezzo(String id, String prezzo) {
		 Connection conn = null;
		    PreparedStatement stmt = null;
		    
		    try {
		        conn = ds.getConnection(); // Ottieni la connessione al tuo database
		        
		        // Query per aggiornare il prezzo del prodotto
		        String sql = "UPDATE Prodotto SET Prezzo = ? WHERE idProdotto = ?";
		        stmt = conn.prepareStatement(sql);
		        
		        prezzo = prezzo.replaceAll("[€]", "");
		        double prezzoDouble = Double.parseDouble(prezzo);

		        
		        // Imposta i parametri nella query
		        stmt.setDouble(1, prezzoDouble);
		        stmt.setInt(2, Integer.parseInt(id)); // Converte l'id da stringa a int
		        
		        // Esegui la query di aggiornamento
		        stmt.executeUpdate();
		        
		        // Eventuali altre operazioni o logica dopo l'aggiornamento del prezzo
		        
		    } catch (SQLException e) {
		        // Gestisci l'eccezione SQL
		        e.printStackTrace();
		    } finally {
		        // Chiudi la connessione e gli statement
		        if (stmt != null) {
		            try {
		                stmt.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}
	}
