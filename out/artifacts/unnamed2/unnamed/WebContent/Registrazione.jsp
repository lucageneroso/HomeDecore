<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
String messaggio = (String) request.getAttribute("messaggio");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Aleo', serif;
            background-color: #f9f9f9; /* Colore di sfondo generale */
        }
        
        .container {
            position: relative;
            background-color: #fff; /* Sfondo del form */
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
            max-width: 600px;
            margin: 80px auto;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 40px;
            box-sizing: border-box;
        }
        
        h1 {
            margin: 0 0 20px;
            font-size: 28px;
            text-align: center;
        }
        
        form {
            width: 100%;
            max-width: 400px;
            display: flex;
            flex-direction: column;
        }
        
        input[type="text"],
        input[type="Email"],
        input[type="IBAN"],
        input[type="Cognome"],
        input[type="Nome"]
        input[type="password"] {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            box-sizing: border-box;
        }
        
        input[type="submit"],
        button {
            background-color: orange;
            color: #fff;
            border: none;
            padding: 14px;
            margin-top: 10px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        
        .login-link {
            margin-top: 20px;
            text-align: center;
        }
        
        .login-link a {
            color: orange;
            text-decoration: none;
        }
        
        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Crea un Account</h1>
    <form action="registration" method="post">
        <input type="hidden" name="action" value="insert">
        <% if (messaggio != null) { %>
            <p style="color: red;"><%= messaggio %></p>
        <% } %>
        <input type="text" name="nome" placeholder="Nome" required>
        <input type="text" name="cognome" placeholder="Cognome" required>
        <input type="email" name="Email" placeholder="Email" required>
        <input type="text" name="Iban" placeholder="IBAN" required>
        <input type="number" name="IVA" placeholder="Iva" required>
        
        <!--  <input type="number" name="Tipo_account" placeholder="Tipo" required> -->
        
        
        <input type="date" name="data_di_nascita" placeholder="Data di Nascita" required>
        <input type="password" name="PasswordCliente" placeholder="Password" required>
        <button type="submit">Crea Account</button>
    </form>
    <div class="login-link">
        <p>Già registrato?</p>
        <a href="login.jsp">Accedi</a>
    </div>
</div>

</body>
</html>


