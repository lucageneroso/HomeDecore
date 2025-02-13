<%@ page import="model.UserManagement.Utente" %><%--
  Created by IntelliJ IDEA.
  User: Pietro
  Date: 21/01/2025
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title></title>
    <link rel="stylesheet" href="style/style.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


</head>
<body>
<header class="header clearfix">


    <a href="home.jsp" class="header_logo"></a>



    <a href="" class="header_icon-bar">
        <span></span>
        <span></span>
        <span></span>

    </a>


    <ul class="header_menu animate">


        <%
            // Recupera l'utente dalla sessione
            Utente loggedUser = (Utente) session.getAttribute("loggedUser");
            String nome = (loggedUser != null) ? loggedUser.getNome() : "";
        %>
        <li style="color:white" class="header_menu_item">Benvenuto <strong><%= nome %></strong></li>
        <li class="header_menu_item">
            <a href="ProfileClient.jsp" title="Profilo">
                <img src="image/profilo-icona.png" alt="Profilo" style="width: 24px; height: 24px;"/>
            </a>

        <li class="header_menu_item"><a href="cart.jsp">Carrello<span class="badge badge-success px-1"></span></a>
        <li class="header_menu_item"><a href="catalogo">Catalogo</a>
        </li>
        <li class="header_menu_item">
            <a href="logout.jsp" title="Logout">Logout</a>
        </li>

    </ul>



    <script>
        $(document).ready(function(){
            $(".header_icon-bar").click(function(e){
                $(".header_menu").toggleClass('is-open');
                e.preventDefault();/*mettiamo questo in questo modo dato che la rimozione e l inerimento della classe is open avviene su un link dobbiamo far si che la pagina non venga ricaricata cosi da poter attivare l evento*/
            });

        });
    </script>
</header>
</body>
</html>
