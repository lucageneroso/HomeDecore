<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String errorMessage = (String) session.getAttribute("message");
  if (errorMessage == null) {
    errorMessage = "Si è verificato un errore durante il pagamento.";
  }
  session.removeAttribute("message"); // Rimuove il messaggio dalla sessione
%>
<html>
<head>
  <title>Errore Pagamento</title>
  <meta http-equiv="refresh" content="5;url=cart.jsp">
  <style>
    body {
      text-align: center;
      font-family: Arial, sans-serif;
      background-color: #f8f9fa;
      padding: 50px;
    }
    .error-box {
      background: #ffffff;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      display: inline-block;
      border-left: 5px solid #dc3545;
    }
    h1 {
      color: #dc3545;
    }
    p {
      color: #333;
    }
  </style>
</head>
<body>
<div class="error-box">
  <h1>Errore durante il pagamento</h1>
  <p><%= errorMessage %></p>
  <p>Verrai reindirizzato al carrello in pochi secondi...</p>
</div>
</body>
</html>
