<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.OrderManagement.Ordine" %>
<%@ page import="model.OrderManagement.ItemCartDTO" %>
<%@ page import="model.OrderManagement.Prodotto" %>
<%@ page import="model.OrderManagement.ItemCart" %>


<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Conferma Ordine</title>
  <link rel="stylesheet" href="styles.css">  <!-- Aggiungi il tuo stile -->
</head>
<body>

<h1>Conferma Ordine</h1>

<%
  Ordine ordine = (Ordine) session.getAttribute("order");
  List<String> serializedItems= (List<String>) request.getAttribute("itemsCart");
  if (ordine != null) {
%>

<h2>Dettagli dell'Ordine</h2>
<table>
  <tr>
    <th>ID Ordine</th>
    <td><%= ordine.getId() %></td>
  </tr>
  <tr>
    <th>Totale</th>
    <td><%= ordine.getTotale() %> €</td>
  </tr>
  <tr>
    <th>Data Ordine</th>
    <td><%= ordine.getDate() %></td>
  </tr>
  <tr>
    <th>Stato</th>
    <td><%= ordine.getStato() %></td>
  </tr>
</table>

<h3>Articoli Ordinati:</h3>
<table border="1">
  <thead>
  <tr>
    <th>Prodotto</th>
    <th>Quantità</th>
    <th>Prezzo</th>
  </tr>
  </thead>
  <tbody>
  <%


    List<ItemCart> items= (List<ItemCart>) request.getAttribute("itemsCart");
    if (items != null) {
      for (ItemCart item : items) {
  %>
  <tr>
    <td><%= item.getProdotto().getNome() %></td> <!-- O usa il nome del prodotto, se disponibile -->
    <td><%= item.getQuantity() %></td>
    <td><%= item.getProdotto().getPrezzo() %> €</td> <!-- Assicurati di avere il prezzo per item -->
  </tr>
  <%
      }
    }
  %>
  </tbody>
</table>

<p><a href="home.jsp">Torna alla home</a></p>

<%
} else {
%>
<p>Ordine non trovato o errore nella conferma. Riprova più tardi.</p>
<%
  }
%>

</body>
</html>
