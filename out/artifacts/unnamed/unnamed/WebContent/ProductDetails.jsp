<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.*, model.Prodotto, model.Cart, model.immagine, model.ProductDao" %>

<%
    // Recupera l'ID del prodotto dalla query string
    String productIdParam = request.getParameter("id");
    int productId = 0;
    if (productIdParam != null) {
        try {
            productId = Integer.parseInt(productIdParam);
        } catch (NumberFormatException e) {
            // Gestione dell'errore se l'ID non è un numero valido
        }
    }

    // Recupera il prodotto dal database usando l'ID
    ProductDao productDao = new ProductDao();
    Prodotto product = productDao.doRetrieveByKey(productId);

    // Recupera il carrello dalla sessione
    Cart cart = (Cart) session.getAttribute("cart");

    // Recupera la media delle recensioni (questo può essere caricato dal database come necessario)
    Float mediaAttribute = (Float) request.getAttribute("media");
    float media = (mediaAttribute != null) ? mediaAttribute : 0.0f;

    if (product == null) {
        // Se il prodotto non viene trovato, reindirizza alla pagina di errore o mostra un messaggio di errore
        response.sendRedirect("error.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Dettagli Prodotto: <%= product.getNome() %></title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/zooming/build/zooming.min.css">
    <link rel="stylesheet" href="css/ProductDetails.css">
     <link rel="stylesheet" href="css/header.css">
    <style>
        /* Stili aggiuntivi specifici per la pagina */
    </style>
</head>
<body>

<jsp:include page="../src/main/java/webapp/header.jsp"/>
<br><br><br>

<div class="cart">
    <div class="row">
        <div class="col-md-4">
            <div class="img1" id="zoomableImageContainer">
                <img src="data:image/jpg;base64, <%= Base64.getEncoder().encodeToString(product.getImg()) %>" alt="Immagine prodotto principale" width="400px" id="ProductImg" data-zoomable>
            </div>
            <br>
            <div class="small-imgs">
                <% if (product.getAllimg() != null) {
                    ArrayList<immagine> immaginiAggiuntive = product.getAllimg();
                    for (immagine immagine : immaginiAggiuntive) {
                        byte[] imgBytes = immagine.getImg();
                        String base64img = (imgBytes != null) ? Base64.getEncoder().encodeToString(imgBytes) : "";
                %>
                <a href="#" onclick="switchImage('<%= base64img %>')">
                    <img src="data:image/jpg;base64, <%= base64img %>" class="small-img" width="80px" alt="Immagine aggiuntiva">
                </a>
                <% }
                   }
                %>
            </div>
        </div>
        <div class="col-md-6">
            <h1 class="product-title"><%= product.getNome() %></h1>
            
           
            
            
            <div class="price">
                € <%= product.getPrezzo() %>
            </div>
            <% if (product.getQuantita() == 0) { %>
            <p>Prodotto non disponibile</p>
            <% } else { %>
            <div class="qty">
                Quantità disponibile: <%= product.getQuantita() %>
            </div>
            <div id="product" class="product-inf">
                <ul>
                    <li class="active">Descrizione</li>
                </ul>
                <br>
                <div id="Description">
                    <p><%= product.getDescrizione() %></p>
                </div>
            </div>
            <div class="buttons">
                <% if (cart == null || !cart.presente(product.getID())) { %>
                <a href="product?action=addC&id=<%= product.getID() %>" class="custom-btn">Aggiungi al carrello <i class="fas fa-angle-right"></i></a>
                <% } else { %>
                <p>Prodotto già nel carrello</p>
                <% } %>
            </div>
            <% } %>
        </div>
    </div>
</div>

<br>
<jsp:include page="fragments/footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/zooming/build/zooming.min.js"></script>
<script>
    // Inizializza il plugin di zoom sull'immagine
    new Zooming({
        onOpen: function() {
            document.body.style.overflow = 'hidden'; // Impedisce lo scrolling della pagina quando è attivo il zoom
        },
        onClose: function() {
            document.body.style.overflow = ''; // Ripristina lo scrolling della pagina quando il zoom viene chiuso
        }
    }).listen('#zoomableImageContainer img[data-zoomable]');

    // Funzione per cambiare l'immagine principale
    function switchImage(base64Image) {
        var productImg = document.getElementById('ProductImg');
        productImg.src = "data:image/jpg;base64," + base64Image;
    }
</script>

</body>
</html>
