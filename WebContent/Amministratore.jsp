<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,control.*,model.*"%>
<%@ page import="model.User.Utente" %>

<%
	
    ProductDao productDao = new ProductDao();
    Collection<Prodotto> products = productDao.doRetrieveAll();
    request.setAttribute("products", products);
    
    OrdineDAO ordineDao = new OrdineDAO();
    List<Ordine> ordini = ordineDao.getAllOrdini();
    request.setAttribute("ordini", ordini);
    
    UtenteDao utenteDao = new UtenteDao();
    List<Utente> utenti = utenteDao.doRetrieveAll();
    request.setAttribute("utenti", utenti);
    if((int) session.getAttribute("Tipo_account")== 1){
%>

<!DOCTYPE html>
<html lang="it">
<head>
<title>Pagina Amministratore</title>
<style>
body{
overflow-x:hidden;
}
.intestazione {
    background: linear-gradient(90deg, rgba(255,104,72,1) 0%, rgba(255,150,100,1) 50%, rgba(255,104,72,1) 100%);
    color: white;
    text-align: center;
    padding: 20px 0;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    margin-bottom: 30px;
    border-radius: 0 0 10px 10px;
}

.intestazione h1 {
    font-family: 'Arial', sans-serif;
    font-size: 2.5em;
    margin: 0;
}
    /* Stili CSS */
    .s-layout {
    display: flex;
    flex-wrap: nowrap;
}
    
 
.s-sidebar__nav {
    position: sticky;
    top: 0px;
    left: 0;
    width: 200%; /* Larghezza di default */
    height: calc(100vh - 60px);
    background: #ff6848;
    color: rgba(255, 255, 255, 0.9);
    transition: all .3s ease-in-out;
    box-shadow: 2px 0 5px rgba(0,0,0,0.1);
    font-size: 1.2em; /* Dimensione di base del testo */
    border-radius:10px;
}

@media (max-width: 1200px) { 
    .s-sidebar__nav {
        width: 150%; /* Larghezza per schermi medi */
        font-size: 1.1em; /* Dimensione del testo per schermi medi */
    }
}

@media (max-width: 1050px) {
    .s-sidebar__nav {
        width: 100%; /* Larghezza per tablet */
        font-size: 1em; /* Dimensione del testo per schermi medi */
    }
}
/*
@media (max-width: 925px) {
    .s-sidebar__nav {
        width: 50%;
        font-size: 0.7em; 
    }
}
*/
.s-sidebar__nav ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.s-sidebar__nav-link {
    display: flex;
    align-items: center;
    padding: 20px;
    color: white;
    text-decoration: none;
    transition: background 0.3s ease-in-out;
    height: 60px; /* Altezza fissa */
}
.s-sidebar__nav-link:hover {
    background: rgba(0, 0, 0, 0.2);
}

.s-sidebar__nav-link i {
    margin-right: 10px;
}

.s-sidebar__nav-link em {
    font-size: 1.2em;
   } /* Banner */
    .banner {
        background-color: rgba(235, 235, 240, 0.66);
        position: relative;
        height: 90px;
        width: auto;
    }
    
    
  .s-layout__content {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: flex-start;
    padding: 20px;
    overflow: auto;
}

    #image {
        position: absolute;
        top: -18px;
        left: 10px;
        z-index: 1;
        width: 125px;
        height: auto;
    }

    .dx {
        position: absolute;
        top: 20px;
        right: 5px;
        z-index: 1;
    }

    .dx img {
        width: 40px;
        height: 40px;
        margin-left: 15px;
        margin-right: 15px;
    }

.profile-form, .order-form {
    background: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
    display: none; /* Inizialmente nascondi tutte le sezioni */
}

.profile-form h2, .order-form h2 {
    text-align: center;
    color: #343a40;
}


table {
    border-collapse: collapse;
    width: 100%;
    max-width: 800px;
    margin: auto !important;
    margin-top: 20px !important;
}

th, td {
    padding: 12px;
    text-align: center;
    border: 1px solid #ddd;
}

th {
    background-color: #ff6848;
    color: white;
    font-weight: bold;
    border-bottom: 2px solid #e63946;
}

td {
    background-color: #f9f9f9;
    transition: background-color 0.3s ease-in-out;
}

tr:hover td {
    background-color: #ffccd5;
}

tr:nth-child(even) td {
    background-color: #f1f1f1;
}

td a {
    color: #ff6848;
    text-decoration: none;
}

td a:hover {
    text-decoration: underline;
}

input[type="submit"], input[type="reset"] {
    background-color: #333;
    color: #fff;
    border: none;
    margin: 10px;
    padding: 10px 20px;
    cursor: pointer;
    transition: all 0.3s ease-in-out;
}

input[type="submit"]:hover, input[type="reset"]:hover {
    background-color: #ff6848;
    border-radius: 14px;
}

@media screen and (max-width: 900px) {
    table {
        display: block;
        margin-left: 40px;
    }
    th, td {
        display: block;
        width: 100%;
    }
    th {
        text-align: center;
    }
    td {
        text-align: center;
    }
    tr:nth-child(even) {
        background-color: transparent;
    }
    tr {
        margin-bottom: 10px;
        border: 1px solid #ddd;
    }
    td:before {
        content: attr(data-label);
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
        text-align: center;
    }
}
  .sidebar {
                width: 100%;
                position: fixed;
                z-index: 1;
                left: -100%;
                top: 60px;
                height: calc(100vh - 60px);
                overflow-y: auto;
                transition: all 0.3s ease;
            }
            
            .sidebar.show {
                left: 0;
            }
            
            .main-content {
                padding-left: 0;
                padding-top: 20px;
            }
            
            .form-container {
                padding: 15px;
            }
            
            .form-container h2 {
                font-size: 1.5em;
            }
        }

input[type="text"], input[type="file"] {
    width: 80px;
} 

input[type="file"] {
    color: transparent;
}



</style>
<link href="styleAreautente.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--<jsp:include page="../src/main/java/webapp/header.jsp"/>-->

<div class="intestazione">
  <h1>Pagina Amministratore</h1>
  </div>

<div class="s-layout">
    <div class="s-layout__sidebar">
        <a class="s-sidebar__trigger" href="#0">
            <i class="fa fa-bars"></i>
        </a>

        <nav class="s-sidebar__nav">
            <ul>
                <li>
                    <a class="s-sidebar__nav-link" href="#0" id="catalogoLink">
                        <i class="fa fa-home"></i><em>Prodotti</em>
                    </a>
                </li> 
                <li>
                    <a class="s-sidebar__nav-link" href="#0" id="utentiLink">
                        <i class="fa fa-user"></i><em>Utenti</em>
                    </a>
                </li>
                <li>
                    <a class="s-sidebar__nav-link" href="#0" id="ordiniLink">
                        <i class="fa fa-camera"></i><em>Ordini</em>
                    </a>
                </li>
                 <li>
                    <a class="s-sidebar__nav-link" href="Profilo.jsp" id="ordiniLink">
                        <i class="fa fa-camera"></i><em>Profilo</em>
                    </a>
                </li>
                <li>
                    <a class="s-sidebar__nav-link" href="logout" id="ordiniLink">
                        <i class="fa fa-camera"></i><em>LogOut</em>
                    </a>
                </li>
            </ul>
        </nav>
    </div>

    <main class="s-layout__content" >
        <div class="profile-form">
          <h2>Catalogo </h2>
			<table class="product-table" id="productTable">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Descrizione</th>
        <th>Prezzo</th>
        <th>Categoria</th>
        <th>Quantità</th>
        <th>Foto</th>
        <th>Azioni</th>
    </tr>
    <% 
    if (products != null && !products.isEmpty()) {
        Iterator<?> it = products.iterator();
        int count = 0;
        while (it.hasNext()) {
            if (count % 4 == 0) { %>
                <tr>
            <% }
            Prodotto bean = (Prodotto) it.next();
            String base64img = null;
            if (bean.getImg() != null) {
                byte[] imageB = bean.getImg();
                base64img = Base64.getEncoder().encodeToString(imageB);
            }
            %>
            <td><%= bean.getID() %></td>
            <td><%= bean.getNome() %></td>
            <td><%= bean.getDescrizione() %></td>
            <td>
                <form action="product" method="GET">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="id" value="<%= bean.getID() %>">
                    <span id="spanPrezzo<%= bean.getID() %>"><%= bean.getPrezzo() %>€</span>
                    <br>
                    <input type="submit" value="Salva" style="display: none;" id="salvaButton<%= bean.getID() %>">
                </form>
            </td>
            <td><%= bean.getCategoria() %></td>
            <td>
                <form action="product" method="GET">
                    <input type="hidden" name="action" value="updateq">
                    <input type="hidden" name="id" value="<%= bean.getID() %>">
                    <span id="spanQuantita<%= bean.getID() %>"><%= bean.getQuantita() %></span>
                    <br>
                    <input type="submit" value="Salva" style="display: none;" id="salvaButtonQuantita<%= bean.getID() %>">
                </form>
            </td>
            <!-- 
            <td>
                <%if(bean.getQuantita()==0){ %><p>SI</p>
                <%}else{ %> <p>NO</p>
                <%} %>
            </td>
             -->
            <% if (base64img != null) { %>
                <td><img src="data:image/jpg;base64, <%= base64img %>" width="100" height="100" alt="#"></td>
            <% } %>
            
            <td>
                <a href="#" onclick="mostraInputPrezzo('<%= bean.getID() %>'); return false;">
                    <input type="submit" value="Modifica">
                </a>
                
                <br>
                    <input type="hidden" id="deleteId" value="<%= bean.getID() %>">
                    <a href="product?action=delete&id=<%=bean.getID()%>">
                        <input type="submit" value="Cancella" id="deleteButton">
                   	</a>
                  
                 <br>
                    <a href="#" onclick="mostraInputQuantita('<%= bean.getID() %>'); return false;">
                    <input type="submit" value="Aggiungi">
                	</a>
                
            </td>
            </tr>
            <% 
            count++;
        }
        if (count % 4 != 0) { %>
            <% for (int i = 0; i < 8 - (count % 8); i++) { %>
            <% } %>
      
        <% }
    } else { %>
        <tr>
            <td colspan="8">Nessun prodotto disponibile</td>
        </tr>
    <% } %>
    
    
    <!-- 
    <tr id="addProductRow">
    <td colspan="8">
        <button onclick="aggiungiProdotto()">+</button>
    </td>
     -->
</tr>
    
</table>
<br>
<table>
	<tr><td>Aggiungi Prodotto</td></tr>
	
	<tr>
		<td>
    	<form action="product" method="post" enctype="multipart/form-data">
    	<input type="hidden" name="action" value="insert">
        <input type="text" name="id" placeholder="ID">
        <input type="text" name="nome" placeholder="Nome">
        <input type="text" name="descrizione" placeholder="Descrizione">
        <input type="text" name="prezzo" placeholder="Prezzo">
        <input type="text" name="categoria" placeholder="Categoria">
        <input type="text" name="quantita" placeholder="Quantità">
        <input type="file" name="foto" accept="image/*">
        <button type="submit">Salva</button>
    	</form>
    	</td>
	</tr>
</table>

<br>
</div>

        <div class="order-form">
            <!-- Seconda sottopagina: Lista Utenti -->
			 <h2>Lista Utenti</h2>
			<div>
			    <h3>Ricerca Cliente</h3>
			<div class="search-form">
			    <form id="search-form" action="registration" method="GET">
			        <input type="text" id="user-email-input" name="email" placeholder="Inserisci l'email del cliente">
			        <button type="submit" id="search-button">Cerca</button>
			        <button type="button" id="reset-button">Reset</button>
			    </form>
			</div>
			</div>
			<table class="user-table" id="user-table">
			    <tr>
			        <th>Email</th>
			        <th>Nome</th>
			        <th>Cognome</th>
			        <th>Data di Nascita</th>
			        <th>Iban</th>
			        <th>IVA</th>
			    </tr>
			    <% 
			    if (utenti != null && !utenti.isEmpty()) {
			        for (Utente utente : utenti) { %>
			            <tr data-email="<%= utente.getEmail() %>">
			                <td><%= utente.getEmail() %></td>
			                <td><%= utente.getNome() %></td>
			                <td><%= utente.getCognome() %></td>
			                <td><%= utente.getData() %></td>
			                <td><%= utente.getIBAN() %></td>
			                <td><%= utente.getIVA() %></td>
			            </tr>
			        <% }
			    }
			    %>
			</table>
			</div>
 
        <div class="order-form">
		<!-- Terza sottopagina: Lista Ordini -->
		<h2>Lista Ordini</h2>
		<div>
		    <button onclick="sortOrdiniPerData()">Ordina per Data</button>
		</div>
		<div>
		    <h3>Ricerca Cliente</h3>
			<div class="search">
			    <form id="search" action="ordine" method="GET">
			    	<input type="hidden" name="action" value="ViewOrdini">
			        <input type="text" id="user-email-input-orders" name="email" placeholder="Inserisci l'email del cliente">
			        <button type="submit" id="search-button">Cerca</button>
			    </form>
			</div>
		</div>
		<div id="ordiniSection">
		<table class="order-table" id="orderTable">
		    <tr>
		    	<th>Numero Ordine</th>
		        <th>Email</th>
		        <th id="dataOrdineHeader" data-ordine="asc" onclick="sortOrdiniPerData()">Data</th>
		        <th>Totale</th>
		        <th>Stato Ordine</th>
		        <th>Indirizzo</th>
		        <th>Città</th>
		        <th>Provincia</th>
		        <th>CAP</th>
		    </tr>
		    <% 
		    if (ordini != null && !ordini.isEmpty()) {
		        for (Ordine ordine : ordini) { %>
		            <tr data-email="<%= ordine.getIndirizzo() %>">
		                <td><%= ordine.getNumeroOrdine() %></td>
		                <td><%= ordine.getEmailCliente() %></td>
		                <td><%= ordine.getData() %></td>
		                <td><%= ordine.getTotale() %></td>
		                <td><%= ordine.getStato() %></td>
		                <td><%= ordine.getIndirizzo() %></td>
		                <td><%= ordine.getCitta() %></td>
		                <td><%= ordine.getProvincia() %></td>
		                <td><%= ordine.getCAP() %></td>
		            </tr>
		        <% }
		    }
		    %>
		</table>
		</div>
        </div>
    </main>
</div>

<jsp:include page="fragments/footer.jsp"/>

<script>
var searchForm = document.getElementById("search-form");
if (searchForm) {
  searchForm.addEventListener("submit", function(e) {
    e.preventDefault();

    var email = document.getElementById("user-email-input").value;
    var table = document.getElementById("user-table");

    var rows = table.getElementsByTagName("tr");
    for (var i = 0; i < rows.length; i++) {
      var row = rows[i];
      if (i === 0 || row.getAttribute("data-email") === email) {
        row.style.display = "table-row";
      } else {
        row.style.display = "none";
      }
    }

    document.getElementById("user-email-input").value = "";
  });
}

var resetButton = document.getElementById("reset-button");
if (resetButton) {
  resetButton.addEventListener("click", function() {
    var table = document.getElementById("user-table");
    var rows = table.getElementsByTagName("tr");
    for (var i = 0; i < rows.length; i++) {
      rows[i].style.display = "table-row";
    }
  });
}

  
var searchFormOrders = document.getElementById("search");
if (searchFormOrders) {
	System.out.println("Qui");
  searchFormOrders.addEventListener("submit", function(e) {
    e.preventDefault();

    var email = document.getElementById("user-email-input-orders").value;
    
    var table = document.getElementById("orderTable");

    var rows = table.getElementsByTagName("tr");
    for (var i = 0; i < rows.length; i++) {
      var row = rows[i];
      if (i === 0 || row.getAttribute("data-email") === email) {
        row.style.display = "table-row";
      } else {
        row.style.display = "none";
      }
    }

    document.getElementById("user-email-input-orders").value = "";
  });
  
}

  
  var resetButtonOrders = document.getElementById("reset-button-orders");
  if (resetButtonOrders) {
    resetButtonOrders.addEventListener("click", function() {
      var table = document.getElementById("orderTable");
      var rows = table.getElementsByTagName("tr");
      for (var i = 0; i < rows.length; i++) {
        rows[i].style.display = "table-row"; // Mostra tutte le righe
      }
    });
  }
</script>


<script>
    // Gestione degli eventi per le sottopagine

    // Prima sottopagina: Catalogo Prodotti
    var catalogoLink = document.getElementById("catalogoLink");
    var catalogoSection = document.querySelector(".profile-form");

    catalogoLink.addEventListener("click", function(event) {
        event.preventDefault();
        catalogoSection.style.display = "block";
        utentiSection.style.display = "none";
        ordiniSection.style.display = "none";
    });

    // Seconda sottopagina: Lista Utenti
    var utentiLink = document.getElementById("utentiLink");
    var utentiSection = document.querySelector(".order-form:nth-of-type(2)");

    utentiLink.addEventListener("click", function(event) {
        event.preventDefault();
        catalogoSection.style.display = "none";
        utentiSection.style.display = "block";
        ordiniSection.style.display = "none";
    });

	 // Terza sottopagina: Lista Ordini
	    var ordiniLink = document.getElementById("ordiniLink");
	    var ordiniSection = document.querySelector(".order-form:nth-of-type(3)");
	
	    ordiniLink.addEventListener("click", function(event) {
	        event.preventDefault();
	        catalogoSection.style.display = "none";
	        utentiSection.style.display = "none";
	        ordiniSection.style.display = "block";
	    });
	
	    // Ordina gli ordini per data
	    function sortOrdiniPerData() {
	        var ordiniTable = document.querySelector(".order-table");
	        var dataOrdineHeader = document.getElementById("dataOrdineHeader");
	        var rows = Array.from(ordiniTable.getElementsByTagName("tr")).slice(1);
	
	        var ordine = dataOrdineHeader.getAttribute("data-ordine");
	        rows.sort(function(a, b) {
	            var dataA = new Date(a.cells[2].innerText);
	            var dataB = new Date(b.cells[2].innerText);
	
	            if (ordine === "asc") {
	                return dataA - dataB;
	            } else {
	                return dataB - dataA;
	            }
	        });
	
	        while (ordiniTable.rows.length > 1) {
	            ordiniTable.deleteRow(1);
	        }
	
	        rows.forEach(function(row) {
	            ordiniTable.appendChild(row);
	        });
	
	        dataOrdineHeader.setAttribute("data-ordine", ordine === "asc" ? "desc" : "asc");
	    }
	    
	    function showModificaForm() {
	    	  var formSection = document.getElementById("modifica-form");
	    	  formSection.style.display = "block";
	    	  
	    	  formSection.scrollIntoView({ behavior: 'smooth' });
	    	}
	    function noneForm() {
	    	  var formSection = document.getElementById("modifica-form");
	    	  formSection.style.display = "none";

	    	}

			function deleteItem() {
			    var id = document.getElementById('deleteId').value;
			    window.location.href = 'doDelete?id=' + encodeURIComponent(id);

			    System.out.println("da eliminare");
			  }
		
</script>
<script>
function mostraInputPrezzo(id) {
    var spanPrezzo = document.getElementById('spanPrezzo' + id);
    var inputPrezzo = document.createElement('input');
    inputPrezzo.type = 'text';
    inputPrezzo.name = 'prezzo';
    inputPrezzo.value = spanPrezzo.textContent.trim();
    spanPrezzo.textContent = '';
    spanPrezzo.appendChild(inputPrezzo);
    
    var salvaButton = document.getElementById('salvaButton' + id);
    salvaButton.style.display = 'block';
}

function mostraInputQuantita(id) {
    var spanQuantita = document.getElementById('spanQuantita' + id);
    var inputQuantita = document.createElement('input');
    inputQuantita.type = 'text';
    inputQuantita.name = 'quantita';
    inputQuantita.value = spanQuantita.textContent.trim();
    spanQuantita.textContent = '';
    spanQuantita.appendChild(inputQuantita);
    
    var salvaButton = document.getElementById('salvaButtonQuantita' + id);
    salvaButton.style.display = 'block';
}

function aggiungiProdotto() {
    var newRow = document.createElement("tr");
    
    

    newRow.innerHTML = `
    <td>
    	<form action="product" method="post" enctype="multipart/form-data">
        <input type="text" name="nuovoProdottoID" placeholder="ID">
        <input type="text" name="nuovoProdottoNome" placeholder="Nome">
        <input type="text" name="nuovoProdottoDescrizione" placeholder="Descrizione">
        <input type="text" name="nuovoProdottoPrezzo" placeholder="Prezzo">
        <input type="text" name="nuovoProdottoCategoria" placeholder="Categoria">
        <input type="text" name="nuovoProdottoDisponibilita" placeholder="Quantità">
        <input type="file" name="nuovoProdottoFoto" accept="image/*">
        <button type="submit" name="action" value="insert">Salva</button>
    	</form>
    </td>
    `;

    var table = document.getElementById("productTable"); 
    table.appendChild(newRow);
    System.out.println("Qui1");
}

/*
 <td>
    	<input type="text" name="nuovoProdottoID" placeholder="ID"></td>
        <td><input type="text" name="nuovoProdottoNome" placeholder="Nome"></td>
        <td><input type="text" name="nuovoProdottoDescrizione" placeholder="Descrizione"></td>
        <td><input type="text" name="nuovoProdottoPrezzo" placeholder="Prezzo"></td>
        <td><input type="text" name="nuovoProdottoCategoria" placeholder="Categoria"></td>
        <td><input type="text" name="nuovoProdottoDisponibilita" placeholder="Quantità"></td>
        <td><input type="file" name="nuovoProdottoFoto" accept="image/*" ></td>
        <td>
        	<form action="product" method="post" enctype="multipart/form-data">
        		<input type="hidden" name="action" value="insert">
        		<input type="submit" onclick="salvaNuovoProdotto()" value="Salva" id="salva">
    		</form>
        </td>
 */

function salvaNuovoProdotto() {
	System.out.println("Qui1");
	var id = document.querySelector('input[name="nuovoProdottoID"]').value;
    var nome = document.querySelector('input[name="nuovoProdottoNome"]').value;
    var quantita = document.querySelector('input[name="nuovoProdottoDisponibilita"]').value;
    var descrizione = document.querySelector('input[name="nuovoProdottoDescrizione"]').value;
    var categoria = document.querySelector('input[name="nuovoProdottoCategoria"]').value;
    var prezzo = document.querySelector('input[name="nuovoProdottoPrezzo"]').value;
    var foto = document.querySelector('input[name="nuovoProdottoFoto"]').files[0];

    var disponibilitaInt = disponibilita === "1" ? 1 : 0;
    System.out.println("Qui2");
    var formData = new FormData();
    formData.append("action", "insert");
    formData.append("id", id);
    formData.append("nome", nome);
    formData.append("quantita", quantita);
    formData.append("descrizione", descrizione);
    formData.append("prezzo", prezzo);
    formData.append("foto", foto);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "product", true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Successo: il prodotto è stato salvato nel database
                alert("Prodotto salvato con successo");
                // Esegui altre azioni di successo o aggiorna la pagina
            } else {
                // Errore: gestisci l'errore
                alert("Si è verificato un errore durante il salvataggio del prodotto");
            }
        }
    };
    xhr.send(formData);
}
</script>



</body>
</html>
<%} //ciao
    else {
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Erorre500.jsp");
		dispatcher.forward(request, response);
    }
%>