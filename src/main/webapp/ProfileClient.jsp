<%@ page import="model.UserManagement.Utente" %>
<%--
  Created by IntelliJ IDEA.
  User: Pietro
  Date: 31/01/2025
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Profilo cliente</title>
    <link rel="stylesheet" href="style/StyleProfile.css">
</head>
<body>


<header class="header">
    <nav class="navbar">
        <div class="navbar_item"><a href="home2.jsp">Home</a></div>


    </nav>
</header>

<div class="profile-container">
    <h1>Benvenuto nella tua area personale</h1>
    <button class="button" onclick="toggleProfileInfo()">Mostra Informazioni Profilo</button>
</div>

<div class="profile-info hidden" id="profile-info">
    <h2>Informazioni Account</h2>
    <p><strong>Username:</strong> <%= utente.getUsername() %></p>
    <p><strong>Email:</strong> <%= utente.getEmail() %></p>
    <p><strong>Password:</strong> <%= utente.getPassword() %></p>
</div>

</body>
<script>
    function toggleProfileInfo() {
        const profileInfo = document.getElementById("profile-info");
        profileInfo.classList.toggle("hidden");
    }
</script>
</html>
