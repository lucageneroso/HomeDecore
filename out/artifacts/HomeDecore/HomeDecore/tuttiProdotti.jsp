<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.OrderManagement.Prodotto" %>
<!DOCTYPE html>
<html>
<head>
    <title>Catalogo Prodotti</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Collegamento al file CSS -->
</head>
<body>
<h1>Catalogo Prodotti</h1>
<table border="1">
    <thead>
    <tr>
        <th>Nome</th>
        <th>Descrizione</th>
        <th>Prezzo</th>
        <th>Categoria</th>
        <th>Disponibilità</th>
        <th>In Catalogo</th>
        <th>Quantità</th>
        <th>Azione</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
        if (prodotti != null) {
            for (Prodotto prodotto : prodotti) {
    %>
    <tr>
        <td><%= prodotto.getNome() %></td>
        <td><%= prodotto.getDescrizione() %></td>
        <td><%= prodotto.getPrezzo() %></td>
        <td><%= prodotto.getCategoria() %></td>
        <td><%= prodotto.getDisponibilita() %></td>
        <td><%= prodotto.isInCatalogo() ? "Sì" : "No" %></td>
        <td>
            <form action="addToCart" method="get">
                <input type="hidden" name="productId" value="<%= prodotto.getId() %>">
                <input type="number" name="quantity" value="1" min="1" max="<%= prodotto.getDisponibilita() %>">
                <button type="submit">Aggiungi al Carrello</button>
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
</body>
</html>