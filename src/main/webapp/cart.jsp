<%@ page import="model.OrderManagement.Prodotto" %>
<%@ page import="model.OrderManagement.Cart" %>
<%@ page import="model.OrderManagement.ItemCart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>carrello</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="style/carrello.css">
    <link rel="stylesheet" href="style/style.css">


</head>
<body>
<%@include file="NewNavbar.jsp"%>
<br><br><br>
<div class="container">




    <div class="total_price"><p>Totale complessivo: <span id="cartTotal"><%= session.getAttribute("cartTotal") != null ? session.getAttribute("cartTotal") : "0.00" %></span></p></div>

    <table class=" table-light tabella_intera" >

        <thead>
        <tr class="tabella" >
            <th scope="col" >Nome</th>
            <th scope="col">Categoria</th>
            <th scope="col">Prezzo</th>
            <th scope="col">Quantità</th>


        </tr>
        </thead>

        <tbody>
            <%
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart != null && !cart.getItems().isEmpty()) {
				for (ItemCart item : cart.getItems()) {
                    Prodotto prodotto = item.getProdotto();
                    double totalPrice = prodotto.getPrezzo() * item.getQuantity();

		%>
        <tr>

            <td><%= prodotto.getNome()%></td>
            <td><%= prodotto.getCategoria()%></td>
            <td>€<%= prodotto.getPrezzo()%></td>
            <td><%= item.getQuantity()%></td>
        </tr>
        <tr>
            <td colspan="4"  style="display: flex; gap: 10px;align-items: center; width: 100%">

                <form style="display: flex; gap: 10px;align-items: center;" action="updateQuantity" method="post" onsubmit="setTimeout(updateTotal, 500)" >
                    <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" max="<%= prodotto.getDisponibilita()%>">
                    <input type="hidden" name="id" value="<%= prodotto.getId() %>" >

                        <input type="hidden" name="cartId" value="<%= cart.getId() %>">
                        <input type="hidden" name="productId" value="<%= prodotto.getId() %>">

                        <button class= "link_bottone_rimuovi">Aggiorna</button>

                </form >
                        <form action="removeProduct" method="post" onsubmit="setTimeout(updateTotal, 500)">
                            <input type="hidden" name="cartId" value="<%= cart.getId() %>">
                            <input type="hidden" name="productId" value="<%= prodotto.getId() %>">
                        <button class= "link_bottone_rimuovi" type="submit">Rimuovi</button>
                        </form>


            </td>
        </tr>
            <%}%>
        </tbody>


    </table>
    <form action="checkout" method="post">
        <button type = "submit" class="link_bottone">Acquista</button>
    </form>
</div>




    <%
        } else {
    %>
    <tr>
        <td colspan="6">Il carrello è vuoto.</td>
    </tr>
    <% } %>












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

