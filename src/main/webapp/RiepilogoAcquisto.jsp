<%--
  Created by IntelliJ IDEA.
  User: Pietro
  Date: 07/02/2025
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.OrderManagement.ItemCart, model.OrderManagement.Prodotto, java.util.List" %>

<html>
<head>
    <title>Riepilogo Acquisto</title>
    <link rel="stylesheet" type="text/css" href="style/Riepilogo.css">
</head>
<body>
<div class="container">
    <h2>Riepilogo Acquisto</h2>

    <h3>Dettagli Prodotti</h3>
    <table>
        <thead>
        <tr>
            <th>Prodotto</th>
            <th>Quantità</th>
            <th>Prezzo Unitario</th>
            <th>Totale</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Recupera la lista degli articoli dal checkout
            List<ItemCart> itemsCart = (List<ItemCart>) session.getAttribute("items");
            double totale = 0;

            if (itemsCart != null && !itemsCart.isEmpty()) {
                for (ItemCart item : itemsCart) {
                    Prodotto prodotto = item.getProdotto();
                    double prezzoTotale = prodotto.getPrezzo() * item.getQuantity();
                    totale += prezzoTotale;
        %>
        <tr>
            <td><%= prodotto.getNome() %></td>
            <td><%= item.getQuantity() %></td>
            <td>€<%= String.format("%.2f", prodotto.getPrezzo()) %></td>
            <td>€<%= String.format("%.2f", prezzoTotale) %></td>
        </tr>
        <%
            }
        }
            else {
        %>
        <tr>
            <td colspan="4">Nessun prodotto nel riepilogo.</td>
        </tr>
        <% }
            session.removeAttribute("items");%>
        </tbody>
    </table>

    <h3>Dettagli di Spedizione</h3>
    <p><strong>Indirizzo:</strong> <%= session.getAttribute("indirizzo") %></p>
    <p><strong>Metodo di Consegna:</strong> <%= session.getAttribute("metodoConsegna") %></p>
    <p><strong>Spedizione Rapida:</strong> <%= session.getAttribute("spedizioneRapida") %></p>

    <%
        // Aggiunge il costo della spedizione express se selezionata
        String metodoConsegna = (String) session.getAttribute("metodoConsegna");
        if ("express".equals(metodoConsegna)) {
            totale += 5.99; // Costo spedizione express
        }
    %>


    <p><strong>Totale:</strong> €<%= String.format("%.2f", totale) %></p>

    <div class="button-container">
        <a href="home2.jsp"><button class="payment-button">Ritorna alla Home</button></a>
    </div>
</div>
</body>
</html>

