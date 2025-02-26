<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.OrderManagement.Prodotto" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>

<head>
    <title>Catalogo Prodotti</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="style/style.css">

</head>
<body >
<%@include file = "NewNavbar1.jsp" %>
<%
    List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
    String[] categorie = {"BAGNO", "CUCINA", "SOGGIORNO", "GIARDINO"};

    if (prodotti != null) {
%>
<div class="spazio"></div>
<div class="container">
    <%
        for (String categoria : categorie) {
    %>
    <div class="card-header my-3">ARTICOLI <%= categoria %></div>

    <div class="row g-3">
        <%
            for (Prodotto p : prodotti) {
                if (p.getCategoria().toString().equals(categoria)) {
        %>

        <div class="col-12 col-md-6 col-lg-4 gy-3">
            <div class="w-100 carta">
                <a href="#"></a>
                <a href="infoProduct?productId=<%= p.getId() %>">
                <div class="card-body">

                    <% if (p.getImageBytes() != null) { %>
                    <img src="data:image/png;base64,<%= java.util.Base64.getEncoder().encodeToString(p.getImageBytes()) %>"
                         alt="Immagine prodotto" width="150" height="150">
                    <% } else { %>
                    <p>Immagine non disponibile</p>
                    <% } %>


                    <h5 class="card-title"><%= p.getNome() %></h5>
                    <h6 class="stock">

                        <b>Stock:</b>
                        <% if (p.getDisponibilita() != 0) { %>
                        <%= p.getDisponibilita() %>

                        <% } else { %>
                        <p class="card-text"><b><font color="red">ACQUISTO NON DISPONIBILE</font></b></p>
                        <% } %>
                    </h6>
                </a>



                </div>
            </div>
        </div>

        <%
                }
            }
        %>
    </div>
    <% } %>
</div>
<%
    }
%>
<%@include file = "footer.jsp" %>
<script>
    $(document).ready(function(){
        $(".header_icon-bar").click(function(e){
            $(".header_menu").toggleClass('is-open');
            e.preventDefault();/*mettiamo questo in questo modo dato che la rimozione e l inerimento della classe is open avviene su un link dobbiamo far si che la pagina non venga ricaricata cosi da poter attivare l evento*/
        });

    });
</script>

</body>
</html>
