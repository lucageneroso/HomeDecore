<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="enumerativeTypes.Categoria" %>
<html>
<head>
  <title>Crea Prodotto</title>
  <style>
    body, html {
      width: 100%;
      height: 100%;
      background-color: #8B4000;
      font-size: 16px;
      font-family: 'Lobster', sans-serif;
      box-sizing: border-box;
      margin: 0;
      padding: 0;
    }

    *, *:before, *:after {
      box-sizing: border-box;
    }

    /* Header con navbar */
    .header {
      position: fixed;
      top: 0;
      left: 0;
      z-index: 1000;
      width: 100%;
      height: 60px;
      display: flex;
      align-items: center;
      justify-content: center;
      background: linear-gradient(180deg, rgba(139, 64, 0, 0) 0%, rgba(139, 64, 0, 0.003) 43%);
      box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
      backdrop-filter: blur(5px);
    }

    .navbar {
      display: flex;
      gap: 30px;
      padding: 10px 20px;
    }

    .navbar_item a {
      text-decoration: none;
      color: white;
      font-size: 18px;
      padding: 10px 15px;
      border-radius: 13.5px;
      transition: background-color 0.3s ease-in-out;
      background-color: rgba(0, 0, 0, 0.5);
    }

    .navbar_item a:hover {
      background-color: white;
      color: black;
    }

    /* Contenuto principale con scorrimento */

    .content {
      padding-top: 70px; /* Evita che la navbar copra il contenuto */
      padding: 20px;
      text-align: center;
      color: white;
      overflow-y: auto;
      max-height: calc(100vh - 70px); /* Massimizza l'altezza per sfruttare tutto lo spazio disponibile */
    }
    .spazio_div{
      width: 100%;
      height: 10%;
    }

    /* Sezione Ordini */
    .orders-section {
      margin-top: 20px;
      padding: 20px;
      background-color: rgba(0, 0, 0, 0.7);
      border-radius: 15px;
      box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
      color: white;
      text-align: center;
      max-width: 800px;
      margin: 20px auto;
    }

    /* Tabella Ordini */
    .orders-section table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 10px;
      background-color: rgba(255, 255, 255, 0.1);
      border-radius: 10px;
      overflow: hidden;
      margin-left: auto;
      margin-right: auto;
    }

    .orders-section th, .orders-section td {
      padding: 12px;
      border-bottom: 1px solid rgba(255, 255, 255, 0.3);
      text-align: center;
      color: white;
    }

    .orders-section th {
      background-color: rgba(255, 165, 0, 0.6);
      font-size: 18px;
    }

    .orders-section tr:hover {
      background-color: rgba(255, 255, 255, 0.2);
      transition: background-color 0.3s ease-in-out;
    }

    .button {
      cursor: pointer;
      background-color: rgba(59, 62, 35, 0.8);
      color: white;
      padding: 10px 20px;
      border-radius: 13.5px;
      font-size: 16px;
      transition: background-color 0.3s ease-in-out;
      border: none;
    }

    .button:hover {
      background-color: rgba(255, 99, 71, 0.9);
    }

    /* Responsive Design */
    @media (max-width: 768px) {
      .navbar {
        flex-direction: column;
        gap: 15px;
      }

      .orders-section table {
        font-size: 14px;
      }

      .orders-section th, .orders-section td {
        padding: 8px;
      }
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
<header class="header">
  <nav class="navbar">


    <div class="navbar_item"><a href="Profile.jsp">Profilo</a></div>
    <div class="navbar_item"><a href="request">Richieste</a></div>
    <div class="navbar_item"><a href="CreaProdotto.jsp">Crea Prodotto</a></div>
    <div class="navbar_item"><a href="logout.jsp">Logout</a></div>
  </nav>
</header>
<div class="spazio_div">

</div>
<div class="orders-section">
<h2>Crea un Nuovo Prodotto</h2>

<form action="CreaProdottoServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
  <table>
    <tr>
      <th>Immagine</th>
      <th>Nome</th>
    </tr>
    <tr>
      <td><input type="file" name="immagine" accept="image/*"></td>
      <td><input type="text" id="nome" name="nome"></td>
    </tr>

    <tr>
      <th>Descrizione</th>
      <th>Prezzo</th>
    </tr>
    <tr>
      <td><input type="text" name="descrizione"></td>
      <td><input type="number" id="prezzo" name="prezzo" step="0.01" min="0.99" max="999.99"></td>
    </tr>


    <tr>

      <th>Categoria</th>
      <th>Azioni</th>
    </tr>
    <tr>

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
</div>

</body>
</html>
