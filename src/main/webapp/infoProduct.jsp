<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.OrderManagement.Prodotto, java.util.Map, jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>Info Prodotto</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>

<%@include file="Navbar.jsp"%>

<%
    // Ottieni la sessione
    HttpSession sessione = request.getSession();

    // Recupera la lista dei prodotti visualizzati dalla sessione
    Map<Integer, Prodotto> prodottiSessione = (Map<Integer, Prodotto>) sessione.getAttribute("prodottiVisualizzati");
    if (prodottiSessione == null) {
        prodottiSessione = new java.util.HashMap<>();
    }

    // Recupera il prodotto dall'attributo della richiesta
    Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");


    if (prodotto != null) {
        // Se il prodotto non è già in sessione, lo aggiungiamo
        if (!prodottiSessione.containsKey(prodotto.getId())) {
            prodottiSessione.put(prodotto.getId(), prodotto);
            sessione.setAttribute("prodottiVisualizzati", prodottiSessione);
        }
    } else {
        // Se il prodotto è nullo, proviamo a recuperarlo dalla sessione
        String idProdottoParam = request.getParameter("id");
        if (idProdottoParam != null) {
            try {
                int idProdotto = Integer.parseInt(idProdottoParam);
                prodotto = prodottiSessione.get(idProdotto);
            } catch (NumberFormatException e) {
                prodotto = null;
            }
            System.out.println("Prodotto in sessione");
        }
    }

    if (prodotto != null) {
%>
<div style="width: 100%; height: 100%; display: flex; justify-content: center ; align-items: center;">
    <section class="banner clearfix" style="width: 100%">
        <div class="banner_image">
            <% if (prodotto.getImageBytes() != null) { %>
            <img src="data:image/png;base64,<%= java.util.Base64.getEncoder().encodeToString(prodotto.getImageBytes()) %>"
                  alt="Immagine prodotto" width="150" height="150">
            <% } else { %>
            <p>Immagine non disponibile</p>
            <% } %>
        </div>
        <p></p>
        <div class="banner_copy">
            <div class="banner_copy_txt">
                <h4><%= prodotto.getNome() %></h4>
                <h2>Prezzo: <%= prodotto.getPrezzo() %></h2>
                <p><%= prodotto.getDescrizione() %></p>
                <form action="addToCart" method="get">
                    <input type="hidden" name="productId" value="<%= prodotto.getId() %>">
                    <input type="number" name="quantity" value="1" min="1" max="<%= prodotto.getDisponibilita() %>">
                    <button type="submit">Aggiungi al Carrello</button>
                </form>
            </div>
        </div>
    </section>
</div>
<%
} else {
%>
<p style="text-align: center; font-size: 18px; color: red;">Prodotto non trovato.</p>
<%
    }
%>

<%@include file="footer.jsp"%>

<script>
    $(document).ready(function(){
        $(".header_icon-bar").click(function(e){
            $(".header_menu").toggleClass('is-open');
            e.preventDefault();
        });
    });
</script>

</body>
</html>
