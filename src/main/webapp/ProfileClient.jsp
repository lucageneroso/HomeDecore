<%@ page import="model.UserManagement.Utente" %>
<%@ page import="model.OrderManagement.Ordine" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Recupera l'oggetto utente dalla sessione
    Utente utente = (Utente) session.getAttribute("loggedUser");
    // Recupera gli ordini dalla sessione
    List<Ordine> orders = (List<Ordine>) session.getAttribute("orders");

    if (utente == null) {
        response.sendRedirect("/login.jsp"); // Reindirizza alla pagina di login se l'utente non è autenticato.
        return;
    }
%>
<html>
<head>
    <title>Profilo cliente</title>
    <link rel="stylesheet" href="style/StyleProfile.css">
</head>
<body>

<header class="header">
    <nav class="navbar">
        <div class="navbar_item"><a href="home2.jsp">Home</a></div>
        <div class="navbar_item"><a href="#" onclick="toggleOrders()">I tuoi Ordini</a></div>
    </nav>
</header>

<div class="profile-container">
    <h1>Benvenuto nella tua area personale</h1>
    <button class="button" onclick="toggleProfileInfo()">Mostra Informazioni Profilo</button>
</div>


<div class="profile-info hidden" id="profile-info">
    <h2>Informazioni Account</h2>
    <p><strong>Nome:</strong> <%= utente.getNome() %></p>
    <p><strong>Email:</strong> <%= utente.getEmail() %></p>
    <p><strong>Password:</strong> <%= utente.getPassword() %></p>
</div>

<!--Sezione ordini aggiunta!-->
<div class="orders-section hidden" id="orders-section">
    <h2>I tuoi ordini</h2>
    <%
        if (orders == null || orders.isEmpty()) {
    %>
    <p>Non hai effettuato ancora un ordine.</p>
    <%
    } else {
    %>
    <table border="1" style="margin:0px auto;">
        <thead>
        <tr>
            <th>ID Ordine</th>
            <th>Data</th>
            <th>Totale</th>
            <th>Stato</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Ordine ordine : orders) {
        %>
        <tr>
            <td><%= ordine.getId() %></td>
            <td><%= ordine.getDate() %></td>
            <td><%= ordine.getTotale() %> €</td>
            <td><%= ordine.getStato() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
        }
    %>
</div>

</body>
<script>
    function toggleProfileInfo() {
        document.getElementById("profile-info").classList.toggle("hidden");
    }

    function toggleOrders() {
        document.getElementById("orders-section").classList.toggle("hidden");
    }
</script>
</html>
