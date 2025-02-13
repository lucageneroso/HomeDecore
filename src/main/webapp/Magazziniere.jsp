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
</head>
<body>

<h2>Magazzino</h2>

<table>
    <tr>
        <th>Nome</th>
        <th>Descrizione</th>
        <th>Prezzo</th>
        <th>Disponibilita</th>
        <th>Azione</th>
    </tr>
    <%


        List<Prodotto> prodottoMagazzino = (List<Prodotto>) request.getAttribute("prodotti");

        if (prodottoMagazzino != null && !prodottoMagazzino.isEmpty()) {
            for (Prodotto p : prodottoMagazzino) {
    %>
    <tr>
        <td><%= p.getNome() %></td>
        <td><%= p.getDescrizione() %></td>
        <td><%= p.getPrezzo() %></td>
        <td><%= p.getDisponibilita() %></td>
        <td>Richiesta</td>


    </tr>
    <%}
    }
    %>
</table>

</body>
</html>