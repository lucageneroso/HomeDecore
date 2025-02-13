
<%@ page import="model.UserManagement.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="model.OrderManagement.Ordine" %>
<%@ page import="enumerativeTypes.Ruolo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Recupera l'oggetto utente dalla sessione
    Utente utente = (Utente) session.getAttribute("loggedUser");

    if (utente == null) {
        response.sendRedirect("/login.jsp"); // Reindirizza alla pagina di login se l'utente non è autenticato.
        return;
    }


%>
<html>
<head>
    <title>Profilo</title>
    <link rel="stylesheet" href="style/StyleProfile.css">
</head>
<body>

<header class="header">
    <nav class="navbar">


        <div class="navbar_item"><a href="Profile.jsp">Profilo</a></div>
        <%
            if(utente.getRuolo() == Ruolo.MAGAZZINIERE){
        %>


        <!-- Magazziniere-->
        <div class="navbar_item"><a href="magazzinoProdotti">Magazzino</a></div>
        <div class="navbar_item"><a href="ProductNotInMagazzino">Aggiungi prodotti in Magazzino</a></div>
        <div class="navbar_item"><a href="ordini">Gestisci ordini</a></div>
        <%}%>
        <%
            if(utente.getRuolo() == Ruolo.FORNITORE){
        %>
        <!-- Fornitore-->
        <div class="navbar_item"><a href="request">Richieste</a></div>
        <div class="navbar_item"><a href="CreaProdotto.jsp">Crea Prodotto</a></div>
        <%}%>
        <%
            if(utente.getRuolo() == Ruolo.GESTOREORDINI){
        %>
        <!-- Geestore Ordini-->
        <div class="navbar_item"><a href="request">Richieste</a></div>
        <div class="navbar_item"><a href="ordini">Gestisci ordini</a></div>
        <%}%>
        <div class="navbar_item"><a href="logout.jsp">Logout</a></div>
    </nav>
</header>

<div class="profile-container">
    <h1>Benvenuto nella tua area personale</h1>
    <button class="button" onclick="toggleProfileInfo()">Mostra Informazioni Profilo</button>
</div>

<div class="profile-info hidden" id="profile-info">
    <h2>Informazioni Account</h2>
    <p><strong>Nome:</strong> <%= utente.getNome() %> <%= utente.getCognome() %></p>
    <p><strong>Email:</strong> <%= utente.getEmail() %></p>
    <p><strong>Ruolo:</strong> <%= utente.getRuolo() %></p>
    <p><strong>Password:</strong> <%= utente.getPassword() %></p>
</div>


<script>
    function toggleProfileInfo() {
        const profileInfo = document.getElementById("profile-info");
        profileInfo.classList.toggle("hidden");
    }
</script>
</body>
</html>
```
