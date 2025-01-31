<%@ page import="model.OrderManagement.Prodotto" %>
<%@ page import="model.OrderManagement.Cart" %>
<%@ page import="model.OrderManagement.ItemCart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carrello</title>
    <script>
        function updateTotal() {
            let total = 0;
            document.querySelectorAll(".item-total").forEach(el => {
                total += parseFloat(el.innerText);
            });
            document.getElementById("cartTotal").innerText = total.toFixed(2);
        }
    </script>
</head>
<body>
<h1>Carrello</h1>
<table>
    <thead>
    <tr>
        <th>Prodotto</th>
        <th>Descrizione</th>
        <th>Prezzo</th>
        <th>Quantità</th>
        <th>Totale</th>
        <th>Azioni</th>
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
        <td><%= prodotto.getNome() %></td>
        <td><%= prodotto.getDescrizione() %></td>
        <td><%= prodotto.getPrezzo() %></td>
        <td>
            <form action="updateQuantity" method="post" onsubmit="setTimeout(updateTotal, 500)">
                <input type="hidden" name="cartId" value="<%= cart.getId() %>">
                <input type="hidden" name="productId" value="<%= prodotto.getId() %>">
                <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1">
                <button type="submit">Aggiorna</button>
            </form>
        </td>
        <td class="item-total"><%= totalPrice %></td>
        <td>
            <form action="removeProduct" method="post" onsubmit="setTimeout(updateTotal, 500)">
                <input type="hidden" name="cartId" value="<%= cart.getId() %>">
                <input type="hidden" name="productId" value="<%= prodotto.getId() %>">
                <button type="submit">Rimuovi</button>
            </form>
            <form action="checkout" method="post">
                <button type="submit">Procedi all'acquisto</button>
            </form>
        </td>
    </tr>
    <%  }
    } else {
    %>
    <tr>
        <td colspan="6">Il carrello è vuoto.</td>
    </tr>
    <% } %>
    </tbody>
</table>
<%
    if (cart != null && !cart.getItems().isEmpty()) {
%>
<p>Totale complessivo: <span id="cartTotal"><%= session.getAttribute("cartTotal") != null ? session.getAttribute("cartTotal") : "0.00" %></span></p>
<% } %>
</body>
</html>