<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="enumerativeTypes.Categoria" %>
<html>
<head>
  <title>Crea Prodotto</title>
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
      border: none;
      cursor: pointer;
      border-radius: 5px;
    }
  </style>
  <script>
    function validateForm() {
      let nome = document.getElementById("nome").value.trim();
      let prezzo = parseFloat(document.getElementById("prezzo").value);

      if (nome === "") {
        alert("Il nome del prodotto non può essere vuoto.");
        return false;
      }

      if (isNaN(prezzo) || prezzo < 0.99 || prezzo > 999.99) {
        alert("Il prezzo deve essere compreso tra 0.99 e 999.99.");
        return false;
      }

      return true;
    }
  </script>
</head>
<body>

<h2>Crea un Nuovo Prodotto</h2>

<form action="CreaProdottoServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
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
      <td><input type="file" name="immagine" accept="image/*"></td>
      <td><input type="text" id="nome" name="nome"></td>
      <td><input type="text" name="descrizione"></td>
      <td><input type="number" id="prezzo" name="prezzo" step="0.01" min="0.99" max="999.99"></td>
      <td>
        <select name="categoria">
          <%
            for (enumerativeTypes.Categoria cat : enumerativeTypes.Categoria.values()) {
          %>
          <option value="<%= cat.name() %>"><%= cat.name() %></option>
          <%
            }
          %>
        </select>
      </td>
      <td>
        <button type="submit" class="btn-accept">Crea</button>
      </td>
    </tr>
  </table>
</form>

</body>
</html>
