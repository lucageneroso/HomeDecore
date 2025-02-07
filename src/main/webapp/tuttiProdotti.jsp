<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.OrderManagement.Prodotto" %>
<!DOCTYPE html>
<html>

<head>
    <title>Catalogo Prodotti</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">

</head>
<body >
<%@include file = "Navbar.jsp" %>
<%
    List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
    String[] categorie = {"BAGNO", "CUCINA", "SOGGIORNO"};

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

                <div class="card-body">

                    <h5 class="card-title"><%= p.getNome() %></h5>
                    <h6 class="stock">
                        <b>Stock:</b>
                        <% if (p.getDisponibilita() != 0) { %>
                        <%= p.getDisponibilita() %>
                        <% } else { %>
                        <b><font color="red">Out of Stock</font></b>
                        <% } %>
                    </h6>

                    <div class="mt-3 justify-content-between bordo">
                        <% if (p.getDisponibilita() != 0) { %>

                        <% } else { %>
                        <p class="card-text"><b><font color="red">ACQUISTO NON DISPONIBILE</font></b></p>
                        <% } %>
                    </div>


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
