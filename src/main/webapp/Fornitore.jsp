<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.RequestManagement.ProductRequest" %>
<%@ page import="service.Catalogo" %>
<%@ page import="jakarta.ejb.EJB" %>
<%@ page import="remoteInterfaces.CatalogoRemote" %>
<%@ page import="model.OrderManagement.Prodotto" %>
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
        function accettaRichiesta(richiestaId, idProdotto, quantita) {
            // Invia una richiesta AJAX per accettare la richiesta
            const params = new URLSearchParams();
            params.append("idRequest", richiestaId);
            params.append("idProdotto", idProdotto);
            params.append("quantita", quantita);

            fetch("accettaRichiestaServlet", {
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
        <th>Prodotto</th>
        <th>Quantità</th>
        <th>Messaggio</th>
        <th>Azioni</th>
    </tr>
    <%
        List<ProductRequest> richiesteFornitore = (List<ProductRequest>) request.getAttribute("richiesteFornitore");
        List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");

        if (richiesteFornitore != null && !richiesteFornitore.isEmpty() && prodotti != null && prodotti.size() == richiesteFornitore.size()) {
            for (int i = 0; i < richiesteFornitore.size(); i++) {
                ProductRequest richiesta = richiesteFornitore.get(i);
                Prodotto prodotto = prodotti.get(i); // prendi l'elemento corrispondente nella lista dei prodotti
    %>
    <tr id="richiesta_<%= richiesta.getId() %>">
        <td><%= prodotto.getNome() %></td>
        <td><%= richiesta.getQuantita() %></td>
        <td><%= richiesta.getMessage() %></td>
        <td>
            <button class="btn-accept" onclick="accettaRichiesta(<%= richiesta.getId() %>, <%= prodotto.getId() %>, <%= richiesta.getQuantita() %>)">Accetta</button>
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
