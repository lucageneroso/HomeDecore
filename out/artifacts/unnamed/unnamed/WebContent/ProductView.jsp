<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64" %>
<%@ page import="model.*" %>
<%@ page import="model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<%
// Recupera il parametro "nome" dalla query string e sanifica il valore
String nomeProdotto = request.getParameter("nome");
System.out.println(nomeProdotto);
if (nomeProdotto != null) {
    nomeProdotto = nomeProdotto.trim();
}
ProductDao productDAO = new ProductDao();
// Inizializza una lista vuota di prodotti
List<Prodotto> products = null;

// Se il nome del prodotto è fornito e non è vuoto, cerca i prodotti corrispondenti nel database
if (nomeProdotto != null && !nomeProdotto.isEmpty()) {
    // Esempio di chiamata a un metodo del DAO per cercare prodotti per nome
    products = productDAO.searchProducts(nomeProdotto); 
}
System.out.println("Nome del prodotto cercato: " + nomeProdotto);
// Recupera il carrello dalla sessione
Cart cart = (Cart) session.getAttribute("cart");
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="css/header.css">
    <title>Prodotto Cercato</title>
    
    <style>
    .container-view {
        max-width: 85%;
        margin: 100px auto;
        padding: 20px;
        background-color: #f9f9f9;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .title {
        text-align: center;
        margin-bottom: 20px; /* Spazio inferiore per separare il titolo dalla lista */
    }

    .product-container {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        margin-top: 20px;
    }

    .product {
        width: 250px;
        padding: 10px;
        margin: 10px;
        background-color: #f5f5f7;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    }

    .product img {
        width: 100%;
        height: auto;
        border-radius: 5px;
    }

    .product h3 {
        text-align: center;
        margin-top: 10px;
        margin-bottom: 5px;
        font-size: 20px;
        color: #333;
    }

    .product p {
        text-align: center;
        margin-bottom: 10px;
        font-size: 18px;
        color: #666;
    }

    .product button {
        display: block;
        margin: 0 auto;
        background-color: #ffa500;
        color: white;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .product button:hover {
        background-color: #ff8c00;
    }
</style>
    
    
    
</head>



<body>
    <jsp:include page="../src/main/java/webapp/header.jsp" />
    <div class="container-view">
        <div class="title">
            <h2>Visualizzazione Prodotto</h2>
        </div>
        <br>

        <%-- Se la lista dei prodotti non è vuota, mostra i prodotti --%>
        <div class="product-container">
            <% if (products != null && !products.isEmpty()) {
                Iterator<Prodotto> it = products.iterator();
                while (it.hasNext()) {
                    Prodotto prodotto = it.next();
                    byte[] imageBytes = prodotto.getImg();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            %>
            <div class="product">
            
            <a href='ProductDetails.jsp?id=<%= prodotto.getID() %>' style="text-decoration: none;">
            
            
                <img src="data:image/jpg;base64, <%= base64Image %>" alt="Immagine Prodotto">
                <h3><%= prodotto.getNome() %></h3>
                <p>Prezzo: <%= prodotto.getPrezzo() %>€</p>
                <% if (prodotto.getQuantita() <= 0) { %>
                    <p style="color: red;">Non disponibile</p>
                <% } %>
               </a>
            </div>
            <% } %>
        </div>
        <% } else { %>
            <p>Nessun prodotto trovato con il nome "<%= nomeProdotto %>".</p>
        <% } %>
        
    </div>
    <jsp:include page="fragments/footer.jsp" />
</body>







</html>
