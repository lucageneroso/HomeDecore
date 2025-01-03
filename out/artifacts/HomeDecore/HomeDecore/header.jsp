<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
    body {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    * {
        box-sizing: inherit;
    }

    .navbar {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        background: rgba(255, 255, 255, 0.6);
        backdrop-filter: blur(10px);
        z-index: 1000;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        padding: 10px 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .content {
        padding: 20px;
    }

    .navbar ul {
        list-style: none;
        margin: 0;
        padding: 0;
        display: flex;
        flex-wrap: wrap;
    }

    .navbar li {
        margin-right: 20px;
    }

    .navbar a {
        text-decoration: none;
        color: #000;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        background-color: white;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        z-index: 1;
    }

    .dropdown:hover .dropdown-content {
        display: flex;
        flex-direction: column;
        padding:5px;
        gap:10px;
    }

    .cerca-form {
        display: inline-block;
    }

    .cerca-form input {
        padding: 5px;
        margin-right: 5px;
    }

    .cerca-form button {
        padding: 5px;
    }

    .search-results {
        display: none;
        position: absolute;
        background-color: #fff;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        max-height: 200px;
        overflow-y: auto;
        z-index: 1001;
        width: 100%;
    }

    .search-results div {
        padding: 5px;
        cursor: pointer;
    }

    .search-results div:hover {
        background-color: #f0f0f0;
    }
    .search-results .no-results {
        padding: 20px;
        text-align: center;
        color: #666; /* Aggiunto per colore testo meno invasivo */
    }

    .login {
        display: inline-block;
        margin-left: 20px;
    }

    .login-button {
        text-decoration: none;
        color: #000;
        padding: 5px 10px;
        border: 1px solid #000;
        border-radius: 5px;
    }

    .login-button:hover {
        background-color: #f0f0f0;
    }

    .hamburger {
        display: none;
        flex-direction: column;
        cursor: pointer;
    }

    .hamburger div {
        width: 25px;
        height: 2px;
        background-color: #000;
        margin: 4px 0;
    }

    @media (max-width: 480px) {
        /* Nascondi il menu principale nell'header */
        .navbar .nav-list {
            display: none;
        }
    }

    @media (max-width: 1200px) {
        .navbar.responsive {
            display: flex;
            flex-direction: column;
            position: sticky;
            background-color: rgba(255, 255, 255, 0.9);
            width: 100%;
            top: 60px; /* aggiustare in base al design */
            left: 0;
            padding: 20px;
            z-index: 1000;
        }

        .navbar.responsive ul {
            flex-direction: column;
        }

        .navbar.responsive div {
            margin-right: 0;
            margin-bottom: 20px;
            line-height: 1.5;
        }

        .navbar.responsive li {
            margin-right: 0;
            margin-bottom: 20px;
            line-height: 1.5;
        }

        .hamburger {
            display: flex;
        }

        .cerca-form {
            display: none; /* Nascondi la barra di ricerca */
        }

        .login {
            display: none; /* Nascondi il login */
        }

        .carrello {
            display: none; /* Nascondi il carrello */
        }

        .navbar.responsive .cerca-form,
        .navbar.responsive .login,
        .navbar.responsive .carrello {
            display: block; /* Mostra barra di ricerca, login e carrello quando la navbar è aperta */
        }

        .navbar .logo-link {
            text-align: center; /* Centra il logo */
        }

        .navbar.responsive .dropdown-content {
            display:none;
        }

        .navbar.responsive .nav-list:hover {
            display: flex;
            flex-direction: column;
            margin-top: 20px; /* Spazio superiore per separare dal logo */
        }

        .navbar.responsive .nav-list li {
            margin-bottom: 10px; /* Spaziatura tra le voci di menu */
        }
    }

    .search-results {
        display: none;
        position: absolute;
        background-color: #fff;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        max-height: 200px;
        overflow-y: auto;
        z-index: 1001;
        width: 300px; /* Modifica la larghezza per adattarla alla tua barra di ricerca */
        border: 1px solid #ccc; /* Aggiungi un bordo per visibilit */
    }

    .search-results div {
        padding: 10px;
        cursor: pointer;
        border-bottom: 1px solid #ddd;
    }

    .search-results div:hover {
        background-color: #f0f0f0;
    }

    .search-results .no-results {
        padding: 10px;
        text-align: center;
        color: #666; /* Colore testo meno invasivo */
    }

    #searchInput:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px white inset; /* Esempio di nascondere con un box-shadow bianco */
    }


</style>

<div class="navbar">

    <div class="hamburger" id="hamburger">
        <div></div>
        <div></div>
        <div></div>
    </div>

    <a href="../../src/main/java/webapp/Home.jsp" class="logo-link">HomeDecor</a>

    <div >
        <nav>
            <ul class="nav-list">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="chi-siamo.jsp">Chi Siamo</a></li>
                <li class="dropdown">
                    <a href="tuttiProdotti.jsp" class="dropbtn">Prodotti</a>
                    <div class="dropdown-content">
                        <a href="Product.jsp?categoria=Cucina">Cucina</a>
                        <a href="Product.jsp?categoria=Bagno">Bagno</a>
                        <a href="Product.jsp?categoria=Soggiorno">Soggiorno</a>
                        <a href="../../src/main/java/webapp/tuttiProdotti.jsp">All</a>
                    </div>
                </li>
            </ul>
        </nav>
    </div>

    <div class="dx">


        <div class="cerca-form">
            <form action="product" method="GET">
                <input type="text" name="nome" id="searchInput" placeholder="Cerca prodotto" autocomplete="off">
                <button type="submit" onclick="submitSearch(event)">Cerca</button>
            </form>
            <div id="searchResults" class="search-results"></div>
        </div>



    </div>

  <%-- <div class="login">
        <% if (session.getAttribute("Email") == null) { %>
        <a class="login-button" href="pagina_accesso.jsp">Login</a>
        <% } else { %>
        <a class="login-button" href="logout">LogOut</a>
        <a class="login-button" href="Profilo.jsp">Profilo</a>
        <% } %>
    </div>  --%>

    <div class="carrello">
        <a class="login-button" href="carrello.jsp">Carrello</a>
    </div>


</div>





<script>
    document.addEventListener("DOMContentLoaded", function() {
        var searchInput = document.getElementById("searchInput");
        var searchResults = document.createElement("div");
        searchResults.setAttribute("id", "searchResults");
        searchResults.classList.add("search-results");
        document.body.appendChild(searchResults);

        var navbar = document.querySelector(".navbar");
        var hamburger = document.getElementById("hamburger");

        hamburger.addEventListener("click", function() {
            navbar.classList.toggle("responsive");

            // Aggiungi o rimuovi una classe per gestire la visibilità degli elementi
            document.body.classList.toggle("navbar-open");

            // Chiudi i risultati della ricerca quando si apre il menu a tendina
            clearSearchResults();
        });

        searchInput.addEventListener("input", function() {
            var query = searchInput.value.trim();

            if (query !== "") {
                var url = "suggestProducts?query=" + encodeURIComponent(query);

                fetch(url)
                    .then(response => response.json())
                    .then(data => {
                        displaySearchResults(data);
                    })
                    .catch(error => {
                        console.error('Errore durante la richiesta:', error);
                    });
            } else {
                clearSearchResults();
            }
        });

        function displaySearchResults(results) {
            searchResults.innerHTML = '';

            if (results.length > 0) {
                results.forEach(function(result) {
                    var resultDiv = document.createElement("div");
                    resultDiv.textContent = result;
                    resultDiv.addEventListener("click", function() {
                        // Gestisci il click su risultato (es. reindirizza alla pagina del prodotto)
                        window.location.href = 'Product.jsp?nome=' + encodeURIComponent(result);
                    });
                    searchResults.appendChild(resultDiv);
                });

                searchResults.style.display = 'block';
            } else {
                showNoResultsMessage();
            }
        }

        function showNoResultsMessage() {
            searchResults.innerHTML = '<div class="no-results">Nessun prodotto trovato</div>';
            searchResults.style.display = 'block';
        }

        function clearSearchResults() {
            searchResults.innerHTML = '';
            searchResults.style.display = 'none';
        }

        window.addEventListener('click', function(e) {
            if (!searchResults.contains(e.target) && e.target !== searchInput) {
                clearSearchResults();
            }
        });
    });


    document.addEventListener("DOMContentLoaded", function() {
        var searchInput = document.getElementById("searchInput");
        var searchResults = document.getElementById("searchResults");

        searchInput.addEventListener("input", function() {
            var query = searchInput.value.trim();

            if (query !== "") {
                var url = "suggestProducts?query=" + encodeURIComponent(query);

                fetch(url)
                    .then(response => response.json())
                    .then(data => {
                        displaySearchResults(data);
                    })
                    .catch(error => {
                        console.error('Errore durante la richiesta:', error);
                    });
            } else {
                clearSearchResults();
            }
        });

        function displaySearchResults(results) {
            searchResults.innerHTML = '';

            if (results.length > 0) {
                results.forEach(function(result) {
                    var resultDiv = document.createElement("div");

                    // Crea un link <a> all'interno del div
                    var resultLink = document.createElement("a");
                    resultLink.textContent = result;
                    resultLink.setAttribute("href", 'product?action=search&nome=' + encodeURIComponent(result));
                    resultLink.style.textDecoration = "none"; // Rimuovi la sottolineatura se desiderato

                    // Aggiungi l'evento click al link
                    resultLink.addEventListener("click", function(event) {
                        // Impedisci il comportamento predefinito del link (facoltativo, a seconda delle necessità)
                        // event.preventDefault();

                        // Reindirizza alla pagina del prodotto
                        window.location.href = 'Product.jsp?nome=' + encodeURIComponent(result);
                    });

                    // Aggiungi il link come figlio del div
                    resultDiv.appendChild(resultLink);

                    // Aggiungi il div al contenitore dei risultati di ricerca
                    searchResults.appendChild(resultDiv);
                });

                searchResults.style.display = 'block';
            } else {
                showNoResultsMessage();
            }
        }

        function showNoResultsMessage() {
            searchResults.innerHTML = '<div class="no-results">Nessun prodotto trovato</div>';
            searchResults.style.display = 'block';
        }

        function clearSearchResults() {
            searchResults.innerHTML = '';
            searchResults.style.display = 'none';
        }

        window.addEventListener('click', function(e) {
            if (!searchResults.contains(e.target) && e.target !== searchInput) {
                clearSearchResults();
            }
        });
    });

    // Funzione per la ricerca
    function submitSearch(event) {
        event.preventDefault(); // Previeni il comportamento predefinito del link

        var searchInput = document.getElementById("searchInput");
        var nome = searchInput.value.trim();

        if (nome !== "") {
            var url = "product?action=search&nome=" + encodeURIComponent(nome);
            window.location.href = url;
        }
    }



</script>


<br><br><br>
</body>
</html>