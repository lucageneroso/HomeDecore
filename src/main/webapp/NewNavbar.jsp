<%@ page import="model.UserManagement.Utente" %>

<a href="" class=" face "></a>
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
            String homeLink;

            // Determino la home da mostrare in base allo stato dell'utente
            if(user!=null)
                homeLink="home2.jsp";
            else
                homeLink="home.jsp";





        %>

        <li class="header_menu_item"><a href="<%= homeLink %>">Home</a></li>
        <li class="header_menu_item"><a href="catalogo">Catalogo</a></li>


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
