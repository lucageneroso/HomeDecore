

<a href="" class=" face "></a>
<a href="" class="insta"></a>
<header class="header clearfix">


    <a href="home.jsp" class="header_logo"></a>



    <a href="" class="header_icon-bar">
        <span></span>
        <span></span>
        <span></span>

    </a>


    <ul class="header_menu animate">



        <li class="header_menu_item"><a href="home.jsp">Home</a></li>
        <li class="header_menu_item"><a target="_blank" href="">Dove ci Trovi</a></li>

        <li class="header_menu_item"><a href="catalogo">Negozio</a></li>

        <li class="header_menu_item"><a href="login.jsp">Login</a></li>




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
