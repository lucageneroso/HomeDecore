<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.OrderManagement.Prodotto" %>
<%@ page import="model.OrderManagement.Ordine" %>
<%@ page import="model.UserManagement.Utente" %>
<%@ page import="model.UserManagement.GestoreOrdini" %>
<html>
<head>
  <title>Magazzino</title>
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
    .btn-edit {
      background-color: #4CAF50;
      color: white;
      padding: 5px 10px;
      text-decoration: none;
      border-radius: 5px;
      cursor: pointer;
    }
    .btn-confirm {
      background-color: #008CBA;
      color: white;
      padding: 5px 10px;
      text-decoration: none;
      border-radius: 5px;
      cursor: pointer;
    }
    .input-edit {
      width: 100%;
      padding: 5px;
    }

    .green {
      color: green;
    }

    .red {
      color: red;
    }


  </style>
  <script>

  </script>
</head>



<body>
<h2>Ordini</h2>

<table>
  <tr>
    <th>Cliente</th>
    <th>Totale</th>
    <th>Stato</th>
    <th>Azioni</th>
  </tr>

  <%
    List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
    List<Utente> clienti = (List<Utente>) request.getAttribute("clienti");
    List<GestoreOrdini> gestori = (List<GestoreOrdini>) request.getAttribute("gestori");

    if (ordini != null && !ordini.isEmpty() && clienti != null && ordini.size() == clienti.size()) {
      for (int i = 0; i < ordini.size(); i++) {
        Ordine ordine = ordini.get(i);
        Utente cliente = clienti.get(i);
  %>
  <tr id="ordineRow<%= ordine.getId() %>">
    <!-- Visualizza il nome del cliente -->
    <td class="cliente"><%= cliente.getNome() %> <%= cliente.getCognome() %></td>
    <td class="prezzo"><%= ordine.getTotale() %></td>
    <td class="stato"><%= ordine.getStato() %></td>

    <td class="Affida">
      <%
        if (ordine.getIdGestore() == null) {
      %>
      <!-- Simbolo rosso e pulsante "Affida" quando l'idGestore è null -->
      <span style="color: red;">&#10060;</span> <!-- simbolo rosso (croce) -->
      <button id="affidaBtn<%= ordine.getId() %>" class="btn-affida" onclick="openGestorePopup(<%= ordine.getId() %>)">Affida</button>
      <%
      } else {
      %>
      <!-- Simbolo verde (icona di spunta) quando l'idGestore non è null -->
      <span id="spunta<%= ordine.getId() %>" style="color: green; display: inline;">&#9989;</span> <!-- simbolo verde (spunta) -->
      <%
        }
      %>
    </td>
  </tr>
  <%
      }
    }
  %>
</table>

<!-- Popup per selezionare un gestore e inserire un messaggio -->
<div id="gestorePopup" style="display:none;">
  <div style="background-color: white; padding: 20px; border: 1px solid black;">
    <h3>Seleziona un Gestore</h3>
    <select id="gestoreSelect">
      <%
        for (GestoreOrdini gestore : gestori) {
      %>
      <option value="<%= gestore.getId() %>"><%= gestore.getNome() %> <%= gestore.getCognome() %></option>
      <%
        }
      %>
    </select>
    <br><br>
    <label for="message">Messaggio:</label>
    <textarea id="message" rows="4" cols="50"></textarea>
    <br><br>
    <button onclick="assignGestore()">Assegna Gestore</button>
    <button onclick="closePopup()">Annulla</button>
  </div>
</div>



<script>
  let selectedOrderId = null;
  let selectedGestoreId = null;

  // Funzione per aprire il popup per la selezione del gestore
  function openGestorePopup(ordineId) {
    console.log("Popup aperto per ordine:", ordineId); // Aggiungi questo per il debug
    selectedOrderId = ordineId; // Memorizza l'ID dell'ordine
    document.getElementById("gestorePopup").style.display = "block";
  }

  function assignGestore() {
    selectedGestoreId = document.getElementById("gestoreSelect").value; // Ottieni l'ID del gestore selezionato
    const message = document.getElementById("message").value; // Ottieni il messaggio inserito

    // Invia la richiesta alla servlet usando POST
    const params = new URLSearchParams();
    params.append("ordineId", selectedOrderId);
    params.append("gestoreId", selectedGestoreId);
    params.append("message", message);

    fetch("assignGestoreServlet", {
      method: "POST",
      body: params
    })
            .then(response => response.text())
            .then(data => {
              console.log("Risposta servlet:", data);
              alert("Richiesta effettuata: " + data);

              // Modifica la riga corrispondente dell'ordine
              const ordineRow = document.getElementById("ordineRow" + selectedOrderId); // Seleziona la riga dell'ordine
              if (ordineRow) {
                const affidaColumn = ordineRow.querySelector(".Affida");

                // Cambia la X rossa in un simbolo verde
                affidaColumn.innerHTML = '<span style="color: green;">&#9989;</span>'; // Spunta verde
              }

              // Chiudi il popup
              closePopup();
            })
            .catch(error => console.error("Errore:", error));
  }


  // Funzione per chiudere il popup
  function closePopup() {
    document.getElementById("gestorePopup").style.display = "none";
  }
</script>

</body>







</html>