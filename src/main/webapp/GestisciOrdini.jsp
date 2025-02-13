<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.OrderManagement.Ordine" %>
<%@ page import="enumerativeTypes.Stato" %>
<%@ page import="enumerativeTypes.Ruolo" %>
<%@ page import="model.UserManagement.Utente" %>

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
    <title>Gestione Ordini</title>
    <link rel="stylesheet" href="style/StyleProfile.css">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .btn-confirm {
            background-color: blue;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
            cursor: pointer;
            border: none;
        }
    </style>
    <script>
        function cambiaStato(ordineId) {
            const selectElement = document.getElementById("statoOrdine_" + ordineId);
            const statoSelezionato = selectElement.value;

            const params = new URLSearchParams();
            params.append("idOrdine", ordineId);
            params.append("nuovoStato", statoSelezionato);

            fetch("cambiaStatoOrdine", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: params
            })
                .then(response => response.json()) // Legge la risposta JSON
                .then(data => {
                    if (data.status === "success") {
                        // Aggiorna visivamente lo stato attuale nella tabella
                        document.getElementById("statoAttuale_" + ordineId).innerText = statoSelezionato;
                    } else {
                        alert("Errore: " + data.message);
                    }
                })
                .catch(error => console.error("Errore:", error));
        }
    </script>
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


<h2>Gestione Ordini</h2>

<table>
    <tr>
        <th>Id Ordine</th>
        <th>Stato Attuale</th>
        <th>Cambia Stato</th>
        <th>Conferma</th>
    </tr>
    <%
        List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");

        if (ordini != null && !ordini.isEmpty()) {
            for (Ordine ordine : ordini) {
    %>
    <tr>
        <td><%= ordine.getId() %></td>
        <td id="statoAttuale_<%= ordine.getId() %>"><%= ordine.getStato().name() %></td>
        <td>
            <select id="statoOrdine_<%= ordine.getId() %>" name="statoOrdine">
                <%
                    for (Stato stato : Stato.values()) {
                        String selected = (stato == ordine.getStato()) ? "selected" : "";
                %>
                <option value="<%= stato.name() %>" <%= selected %>><%= stato.name() %></option>
                <%
                    }
                %>
            </select>
        </td>
        <td>
            <button class="btn-confirm" onclick="cambiaStato(<%= ordine.getId() %>)">Conferma</button>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">Nessun ordine disponibile.</td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
