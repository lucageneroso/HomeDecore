<%--
  Created by IntelliJ IDEA.
  User: miche
  Date: 08/02/2025
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.OrderManagement.Prodotto" %>
<!DOCTYPE html>
<html>
<head>
    <title>Info Prodotto</title>
     <link rel="stylesheet" href="style.css">
</head>
<body>



<%
    Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");
    if (prodotto != null) {
%><%@include file="Navbar.jsp"%>
<div style="width: 100%; height: 100%; display: flex; justify-content: center ; align-items: center;">

<section class="banner clearfix " style="width: 100%" >
    <div class="banner_image"></div>
    <div class="banner_copy">
        <div class="banner_copy_txt">

            <h4><%=prodotto.getNome()%></h4>
            <h2>Prezzo: <%=prodotto.getPrezzo()%></h2>
            <p><%=prodotto.getDescrizione()%> </p>
            <form action="addToCart" method="get">
                <input type="hidden" name="productId" value="<%= prodotto.getId() %>">
                <input type="number" name="quantity" value="1" min="1" max="<%= prodotto.getDisponibilita() %>">
                <button type="submit">Aggiungi al Carrello</button>
            </form>
        </div>
    </div>
</section></div>
<%}%>

<%@include file="footer.jsp"%>
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
