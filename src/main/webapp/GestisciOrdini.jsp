<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.OrderManagement.Ordine" %>
<%@ page import="enumerativeTypes.Stato" %>

<html>
<head>
    <title>Gestione Ordini</title>
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
