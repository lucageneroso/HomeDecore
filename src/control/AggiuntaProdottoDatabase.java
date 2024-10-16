package control;

import java.io.*;
import java.sql.*;

public class AggiuntaProdottoDatabase {

    public static void main(String[] args) {

        String jdbcURL = "jdbc:mysql://localhost:3306/ingrosso";
        String dbUser = "root";
        String dbPassword = "root";

        Connection connection = null;
        PreparedStatement statement = null;
        FileInputStream fis = null;
        File imageFile=null;

        try {
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "INSERT INTO Prodotto (idProdotto, Quantita, Prezzo, Nome, Descrizione, Categoria, Sconto, Foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, 1);
            statement.setInt(2, 100);
            statement.setDouble(3, 2.99);
            statement.setString(4, "Rotolone");
            statement.setString(5, "Un versatile rotolo di carta ad alta assorbenza, perfetto per pulizie domestiche e asciugature rapide. Indispensabile per ogni casa.");
            statement.setString(6, "Cucina");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/rotolone.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();



            statement = connection.prepareStatement(sql);
            statement.setInt(1, 2);
            statement.setInt(2, 200);
            statement.setDouble(3, 49.99);
            statement.setString(4, "Set di Coltelli");
            statement.setString(5, "Un set completo di coltelli affilati, ideale per ogni cucina. Perfetto per tagliare, affettare e tritare con precisione.");
            statement.setString(6, "Cucina");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/setColtelli.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();


            statement = connection.prepareStatement(sql);
            statement.setInt(1, 3);
            statement.setInt(2, 100);
            statement.setDouble(3, 4.99);
            statement.setString(4, "Macina Pepe");
            statement.setString(5, "Elegante macina pepe manuale, ideale per aggiungere pepe fresco ai tuoi piatti. Facile da usare e ricaricare.");
            statement.setString(6, "Cucina");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/macinaPepe.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();


            statement = connection.prepareStatement(sql);
            statement.setInt(1, 4);
            statement.setInt(2, 300);
            statement.setDouble(3, 3.99);
            statement.setString(4, "Bicchiere Cocktail");
            statement.setString(5, "Elegante bicchiere nero per cocktail, perfetto per servire bevande con stile e raffinatezza.");
            statement.setString(6, "Cucina");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/cocktail.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();


            statement = connection.prepareStatement(sql);
            statement.setInt(1, 5);
            statement.setInt(2, 150);
            statement.setDouble(3, 7.99);
            statement.setString(4, "Mensola");
            statement.setString(5, "Una robusta mensola in legno, perfetta per esporre oggetti decorativi o per organizzare libri e altri articoli. Aggiunge stile e funzionalità a qualsiasi ambiente domestico.");
            statement.setString(6, "Bagno");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/mensola.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();


            statement = connection.prepareStatement(sql);
            statement.setInt(1, 6);
            statement.setInt(2, 250);
            statement.setDouble(3, 3.99);
            statement.setString(4, "Cestino");
            statement.setString(5, "Pratico cestino da bagno in metallo, ideale per smaltire rifiuti con stile e resistenza.");
            statement.setString(6, "Bagno");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/cestino.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();


            statement = connection.prepareStatement(sql);
            statement.setInt(1, 7);
            statement.setInt(2, 200);
            statement.setDouble(3, 8.99);
            statement.setString(4, "Saponette");
            statement.setString(5, "Saponette artigianali, disponibili in una varietà di fragranze rilassanti e vivificanti. Delicatamente profumate, ideali per una pulizia delicata e una pelle morbida e idratata.");
            statement.setString(6, "Bagno");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/saponette.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();

            statement = connection.prepareStatement(sql);
            statement.setInt(1, 8);
            statement.setInt(2, 400);
            statement.setDouble(3, 2.99);
            statement.setString(4, "Asciugamani");
            statement.setString(5, "Asciugamani di alta qualità, morbidi e assorbenti, disponibili in vari colori e dimensioni per soddisfare ogni esigenza in bagno. Perfetti per un comfort quotidiano e un'asciugatura rapida.");
            statement.setString(6, "Bagno");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/asciugamani.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();


            statement = connection.prepareStatement(sql);
            statement.setInt(1, 9);
            statement.setInt(2, 50);
            statement.setDouble(3, 14.99);
            statement.setString(4, "Orologio");
            statement.setString(5, "Moderno orologio digitale con display luminoso, ideale per tenere traccia del tempo con precisione e stile contemporaneo.");
            statement.setString(6, "Soggiorno");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/orologio.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();

            statement = connection.prepareStatement(sql);
            statement.setInt(1, 10);
            statement.setInt(2, 350);
            statement.setDouble(3, 9.99);
            statement.setString(4, "Tappeto");
            statement.setString(5, "Elegante tappeto grigio, tessuto con materiali di alta qualità per aggiungere comfort e stile a qualsiasi ambiente domestico.");
            statement.setString(6, "Soggiorno");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/tappeto.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();

            statement = connection.prepareStatement(sql);
            statement.setInt(1, 11);
            statement.setInt(2, 175);
            statement.setDouble(3, 29.99);
            statement.setString(4, "Tavolino");
            statement.setString(5, "Tavolino elegante e funzionale, perfetto per complementare lo spazio con stile e praticità. Realizzato con materiali di alta qualità per durabilità e comfort.");
            statement.setString(6, "Soggiorno");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/tavolino.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();

            statement = connection.prepareStatement(sql);
            statement.setInt(1, 12);
            statement.setInt(2, 200);
            statement.setDouble(3, 13.99);
            statement.setString(4, "Lampada");
            statement.setString(5, "Lampada moderna e versatile, ideale per illuminare con stile e funzionalità. Progettata con materiali di qualità per un'illuminazione efficace e piacevole.");
            statement.setString(6, "Soggiorno");
            statement.setDouble(7, 0);
            imageFile = new File("src/control/images/lampada.jpg");
            fis = new FileInputStream(imageFile);
            statement.setBinaryStream(8, fis, (int) imageFile.length());
            statement.executeUpdate();
            fis.close();



            connection.close();
            System.out.println("Prodotti aggiunti");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
