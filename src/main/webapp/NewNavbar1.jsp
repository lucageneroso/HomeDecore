<%@ page import="model.UserManagement.Utente" %>
<%@ page import="model.OrderManagement.Ordine" %>
<%@ page import="model.OrderManagement.Cart" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<a href="" class="face"></a>
<a href="" class="insta"></a>

<header class="header clearfix">
    <a href="home2.jsp" class="header_logo"></a>

    <a href="" class="header_icon-bar">
        <span></span>
        <span></span>
        <span></span>
    </a>

    <ul class="header_menu animate">
        <%
            // Recupero l'utente dalla sessione
            Utente user = (Utente) session.getAttribute("loggedUser");
            String homeLink,cartLink="";
            // Controllo se esiste un carrello associato all'utente
           Cart cart=(Cart)session.getAttribute("cart");

            // Determino la home da mostrare in base allo stato dell'utente
            if(user!=null)
                homeLink="home2.jsp";
            else
                homeLink="home.jsp";


            // Determino il link del carrello

            if (cart!= null) {
                session.setAttribute("cart",cart);
                // Utente loggato con carrello attivo

            }

        %>

        <li class="header_menu_item"><a href="<%= homeLink %>">Home</a></li>
        <li class="header_menu_item"><a href="cart.jsp">Carrello</a></li>
    </ul>

    <script>
        $(document).ready(function(){
            $(".header_icon-bar").click(function(e){
                $(".header_menu").toggleClass('is-open');
                e.preventDefault();
            });
        });
    </script>
</header>
