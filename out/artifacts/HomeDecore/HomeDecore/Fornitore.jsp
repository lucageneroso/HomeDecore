<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.RequestManagement.ProductRequest" %>
<%@ page import="service.Catalogo" %>
<%@ page import="jakarta.ejb.EJB" %>
<%@ page import="remoteInterfaces.CatalogoRemote" %>
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
</head>
<body>

<h2>Richieste di Prodotti dai Magazzinieri</h2>

<table>
    <tr>
        <th>Prodotto</th>
        <th>Quantità</th>
        <th>Magazziniere</th>
        <th>Azioni</th>
    </tr>
    <%


        List<ProductRequest> richiesteFornitore = (List<ProductRequest>) request.getAttribute("richiesteFornitore");

        if (richiesteFornitore != null && !richiesteFornitore.isEmpty()) {
            for (ProductRequest richiesta : richiesteFornitore) {
    %>
    <tr>
        <td><%= richiesta.getProdottoRichiestoID() %></td>
        <td><%= richiesta.getQuantita() %></td>
        <td><%= richiesta.getMagazziniereID() %></td>
        <td>
            <a class="btn-accept" href="accettaRichiesta?id=<%= richiesta.getId() %>">Accetta</a>
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