<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.RequestManagement.ProductRequest" %>
<%@ page import="service.Catalogo" %>
<%@ page import="jakarta.ejb.EJB" %>
<%@ page import="remoteInterfaces.CatalogoRemote" %>
<%@ page import="model.OrderManagement.Prodotto" %>
<%@ page import="model.RequestManagement.OrderRequest" %>
<html>
<head>
    <title></title>
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
        .btn-accept {
            background-color: green;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
    <script>
        function accettaRichiesta(richiestaId, ordineID) {
            // Invia una richiesta AJAX per accettare la richiesta
            const params = new URLSearchParams();
            params.append("idRequest", richiestaId);
            params.append("idOrdine", ordineID);

            fetch("accettaRichiestaOrdineServlet", {
                method: "POST",
                body: params
            })
                .then(response => response.text())
                .then(data => {
                    console.log(data);
                    // Se la richiesta è stata accettata, rimuovi la riga dalla tabella
                    const row = document.getElementById("richiesta_" + richiestaId);
                    row.remove(); // Rimuove la riga
                })
                .catch(error => console.error("Errore:", error));
        }
    </script>
</head>
<body>

<h2>Richieste di Prodotti dai Magazzinieri</h2>

<table>
    <tr>
        <th>Id Ordine</th>
        <th>Messaggio</th>
        <th>Azioni</th>
    </tr>
    <%
        List<OrderRequest> richiesteOrdini = (List<OrderRequest>) request.getAttribute("richiesteOrdini");

        if (richiesteOrdini != null && !richiesteOrdini.isEmpty() ) {
            for (int i = 0; i < richiesteOrdini.size(); i++) {
                OrderRequest richiesta = richiesteOrdini.get(i);
    %>
    <tr id="richiesta_<%= richiesta.getId() %>">
        <td><%= richiesta.getOrdineID()%></td>
        <td><%= richiesta.getMessage() %></td>
        <td>
            <button class="btn-accept" onclick="accettaRichiesta(<%= richiesta.getId() %>, <%= richiesta.getOrdineID() %>)">Accetta</button>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">Nessuna richiesta disponibile.</td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
