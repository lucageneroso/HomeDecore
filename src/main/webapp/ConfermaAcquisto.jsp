<%--
  Created by IntelliJ IDEA.
  User: Pietro
  Date: 04/02/2025
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Conferma Acquisto</title>
    <meta http-equiv="refresh" content="5;url=CompleteOrderServlet">
    <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 50px;
        }
        .message-box {
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            display: inline-block;
            max-width: 500px;
        }
        h1 {
            color: #28a745;
            font-size: 24px;
        }
        p {
            color: #333;
            font-size: 18px;
        }
        .redirect-message {
            margin-top: 20px;
            font-size: 14px;
            color: #777;
        }
    </style>
</head>
<body>
<%
    String paymentMessage = (String) session.getAttribute("message");
    if (paymentMessage != null) {
        session.removeAttribute("message"); // Rimuove il messaggio dalla sessione dopo averlo mostrato
    }
%>
<div class="message-box">
    <h1><%= (paymentMessage != null) ? paymentMessage : "Errore nel recupero del messaggio di pagamento!" %></h1>
    <p class="redirect-message">Verrai reindirizzato al riepilogo acquisto in 5 secondi...</p>
</div>
</body>
</html>
