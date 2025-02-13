<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.RequestManagement.ProductRequest" %>
<%@ page import="service.Catalogo" %>
<%@ page import="jakarta.ejb.EJB" %>
<%@ page import="remoteInterfaces.CatalogoRemote" %>
<html>
<head>
  <title>Richieste Magazzinieri</title>
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
    <th>Immagine</th>
    <th>Nome</th>
    <th>Descrizione</th>
    <th>Prezzo</th>
    <th>Categoria</th>
    <th>Azioni</th>
  </tr>

  <tr>
    <td> <input type="image"></td>
    <td><input type="text"></td>
    <td><input type="text"></td>
    <td><input type="number" min="0.99" max="999.999"></td>
    <td><input type="text"></td>
    <td>
      <a class="btn-accept" href="">Crea</a>
    </td>
  </tr>

</table>

</body>
</html>