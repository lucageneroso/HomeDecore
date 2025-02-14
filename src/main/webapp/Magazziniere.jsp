<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.OrderManagement.Prodotto" %>
<html>
<head>
    <title>Magazzino</title>
    <style>
        body{
            background: #8B4000;
            color:white;
        }
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
            background-color:#8B4000;
        }
        .spazio_div{
            width: 100%;
            height: 10%;
        }
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
        function enableEdit(button, field, id) {
            console.log("enableEdit -> field:", field, "| id:", id);

            if (!id || id === "null" || id === "undefined") {
                console.error("Errore: ID non valido", id);
                return;
            }

            // Trova la riga corrente
            var row = button.closest("tr"); // Trova la riga più vicina (più sicuro)
            var cell = row.querySelector("." + field);
            var currentValue = cell.innerText.trim();

            // Crea input manualmente e aggiorna il DOM senza usare innerHTML
            var input = document.createElement("input");
            input.type = "text";
            input.className = "input-edit";
            input.value = currentValue;
            input.id = `edit-${field}-${id}`;

            // Sostituisci il contenuto della cella con l'input
            cell.innerText = ""; // Pulisce il contenuto della cella
            cell.appendChild(input);

            // Crea il bottone di conferma manualmente
            var confirmButton = document.createElement("button");
            confirmButton.className = "btn-confirm";
            confirmButton.innerText = "Conferma";
            confirmButton.onclick = function () {
                confirmEdit(field, id);
            };

            // Sostituisci il bottone di modifica con il bottone di conferma
            button.replaceWith(confirmButton);
        }



        function confirmEdit(field, id) {
            console.log("confirmEdit -> field:", field, "| id:", id);

            if (!id || id === "null" || id === "undefined") {
                console.error("Errore: ID non valido", id);
                return;
            }

            var input = document.getElementById(`edit-${field}-${id}`);
            var newValue = input.value.trim();  // Rimuove gli spazi vuoti

            console.log("Valore nuovo:", newValue);
            console.log("Trim: ", String(id).trim());

            // Validazione per il campo "nome" (non vuoto)
            if (field === "nome" && newValue === "") {
                alert("Il nome non può essere vuoto.");
                return;  // Interrompe l'esecuzione se il nome è vuoto
            }

            // Validazione per il campo "prezzo" (deve essere maggiore di 0.99)
            if (field === "prezzo") {
                var prezzo = parseFloat(newValue);
                if (isNaN(prezzo) || prezzo < 0.99) {
                    alert("Il prezzo deve essere maggiore di 0.99.");
                    return;  // Interrompe l'esecuzione se il prezzo non è valido
                }
            }

            const params = new URLSearchParams();
            params.append("id", String(id).trim());
            params.append("field", field);
            params.append("value", newValue);

            fetch("ModificaProdottoServlet", {
                method: "POST",
                body: params
            })
                .then(response => response.text())
                .then(data => {
                    console.log("Risposta servlet:", data);
                    alert("Modifica salvata: " + data);
                    location.reload();
                })
                .catch(error => console.error("Errore:", error));
        }


        // Funzione per aprire il form della richiesta
        function openRequestForm(id, name, availability, idFornitore, idProd) {
            // Imposta i valori nel form
            document.getElementById('product-name').value = name;
            document.getElementById('product-availability').value = availability;

            // Apre il pop-up
            document.getElementById('request-popup').style.display = 'block';

            // Gestisci l'invio del form
            document.getElementById('request-form').onsubmit = function(event) {
                event.preventDefault();

                // Ottieni la quantità
                var quantity = document.getElementById('quantity').value;
                var note = document.getElementById('note').value; // Ottieni la nota

                // Validazione della quantità
                if (quantity <= 0) {
                    alert("La quantità deve essere maggiore di 0.");
                    return;
                }

                // Invio della richiesta al server
                const params = new URLSearchParams();
                params.append("id", id);
                params.append("idFornitore", idFornitore);
                params.append("idProd", idProd);
                params.append("quantity", quantity);

                fetch("RichiestaProdottoServlet", {
                    method: "POST",
                    body: params
                })
                    .then(response => response.text())
                    .then(data => {
                        alert("Richiesta inviata: " + data);
                        closeRequestForm();  // Chiudi il form
                    })
                    .catch(error => console.error("Errore:", error));
            };
        }

        // Funzione per chiudere il form della richiesta
        function closeRequestForm() {
            document.getElementById('request-popup').style.display = 'none';
        }



        function toggleCatalogStatus(id) {
            fetch("ToggleCatalogStatusServlet", {
                method: "POST",
                body: new URLSearchParams({
                    "id": id
                })
            })
                .then(response => response.text())
                .then(data => {
                    console.log("Risposta dal server:", data);
                    alert("Stato aggiornato: " + data);
                    location.reload();  // Ricarica la pagina per aggiornare lo stato
                })
                .catch(error => console.error("Errore:", error));
        }







    </script>
</head>
<body>
<header class="header">
    <nav class="navbar">


        <!-- Magazziniere-->
        <div class="navbar_item"><a href="Profile.jsp">Profilo</a></div>
        <div class="navbar_item"><a href="magazzinoProdotti">Magazzino</a></div>
        <div class="navbar_item"><a href="ProductNotInMagazzino">Aggiungi prodotti in Magazzino</a></div>
        <div class="navbar_item"><a href="ordini">Gestisci ordini</a></div>
        <div class="navbar_item"><a href="logout.jsp">Logout</a></div>
    </nav>
</header>

<div class="spazio_div">

</div>


<h2>Magazzino</h2>

<table>
    <tr>
        <th>Nome</th>
        <th>Descrizione</th>
        <th>Prezzo</th>
        <th>In Catalogo</th>
        <th>Disponibilità</th>
        <th>Azioni</th>
    </tr>
    <%
        List<Prodotto> prodottoMagazzino = (List<Prodotto>) request.getAttribute("prodotti");
        if (prodottoMagazzino != null && !prodottoMagazzino.isEmpty()) {
            for (Prodotto p : prodottoMagazzino) {
    %>
    <tr>
        <td class="nome"><%= p.getNome() %></td>
        <td class="descrizione"><%= p.getDescrizione() %></td>
        <td class="prezzo"><%= p.getPrezzo() %></td>

        <!-- Icona e pulsante "In Catalogo" -->
        <td>
            <% boolean inCatalogo = p.isInCatalogo(); %>
            <span class="<%= inCatalogo ? "green" : "red" %>">
                <%= inCatalogo ? "✔" : "✘" %>
            </span>
            <button class="btn-edit" onclick="toggleCatalogStatus(<%= p.getId() %>)">
                <%= inCatalogo ? "Rimuovi dal catalogo" : "Aggiungi al catalogo" %>
            </button>
        </td>

        <td><%= p.getDisponibilita() %></td>
        <td>
            <% Integer id = p.getId(); %>
            <button class="btn-edit" onclick="enableEdit(this, 'nome', <%= (id != null ? id : "'undefined'") %>)">Modifica Nome</button>
            <button class="btn-edit" onclick="enableEdit(this, 'descrizione', <%= (id != null ? id : "'undefined'") %>)">Modifica Descrizione</button>
            <button class="btn-edit" onclick="enableEdit(this, 'prezzo', <%= (id != null ? id : "'undefined'") %>)">Modifica Prezzo</button>
            <button class="btn-edit" onclick="openRequestForm(<%= (id != null ? id : "'undefined'") %>, '<%= p.getNome() %>', <%= p.getDisponibilita() %>, <%= p.getFornitore()%>, <%= p.getId()%>)">Richiesta</button>
        </td>
    </tr>
    <% }
    } %>
</table>



<!-- Popup per la richiesta -->
<div id="request-popup" style="display:none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background-color: white; padding: 20px; border: 1px solid #ccc; z-index: 1000;">
    <h3>Richiesta Prodotto</h3>
    <form id="request-form">
        <label for="product-name">Nome Prodotto</label>
        <input type="text" id="product-name" disabled><br><br>

        <label for="product-availability">Disponibilità</label>
        <input type="text" id="product-availability" disabled><br><br>

        <label for="quantity">Quantità da richiedere</label>
        <input type="number" id="quantity" min="1" required><br><br>

        <label for="note">Nota (opzionale)</label>
        <textarea id="note" rows="3" cols="30"></textarea><br><br>

        <button type="submit">Invia Richiesta</button>
        <button type="button" onclick="closeRequestForm()">Annulla</button>
    </form>
</div>



</body>
</html>