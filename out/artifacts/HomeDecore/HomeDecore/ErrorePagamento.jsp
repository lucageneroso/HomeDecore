<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Errore di Pagamento</title>
  <link rel="stylesheet" type="text/css" href="error.css">
  <meta http-equiv="refresh" content="5;url=cart.jsp"> <!-- Reindirizzamento automatico -->
</head>
<body>
<div class="error-container">
  <h2>Errore di Pagamento</h2>
  <p><%= session.getAttribute("message") != null ? session.getAttribute("message") : "Si è verificato un errore durante il pagamento." %></p>
  <p>Verrai reindirizzato al carrello </p>
</div>
</body>