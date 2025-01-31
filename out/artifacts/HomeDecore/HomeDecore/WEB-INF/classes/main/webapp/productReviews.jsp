<%@ page import="model.ReviewManagement.*" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Recensioni del prodotto</title>
</head>
<body>
<h1>Recensioni del Prodotto</h1>

<%
  List<Recensione> reviews = (List<Recensione>) request.getAttribute("reviews");
  Integer productID = (Integer) request.getAttribute("productID");

  if (reviews != null && !reviews.isEmpty()) {
%>
<ul>
  <%
    for (Recensione review : reviews) {
  %>
  <li>
    <strong>Rating:</strong> <%= review.getRating() %>/5<br>
    <strong>Utente:</strong> <%= review.getUserID() %><br>
    <strong>Commento:</strong> <%= review.getCommento() %><br>
  </li>
  <%
    }
  %>
</ul>
<%
} else {
%>
<p>Non ci sono recensioni per questo prodotto.</p>
<%
  }
%>

<h2>Aggiungi una Recensione</h2>
<form action="<%= request.getContextPath() %>/reviews" method="post">
  <input type="hidden" name="productID" value="<%= productID %>" />
  <label for="rating">Valutazione (1-5):</label>
  <input type="number" id="rating" name="rating" min="1" max="5" required /><br>
  <label for="content">Commento:</label>
  <textarea id="content" name="content" rows="4" cols="50" required></textarea><br>
  <button type="submit">Aggiungi Recensione</button>
</form>
</body>
</html>
