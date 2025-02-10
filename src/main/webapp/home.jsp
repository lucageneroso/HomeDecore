
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>HOMEDECORE</title>

    <link rel="stylesheet" href="style.css">

</head>
<body>

<%@include file = "Navbar.jsp" %>

<section class="cover elemento">
    <div class="cover_filter"></div>
    <div class="cover_caption">
        <div class="cover_caption_copy">
            <h1>Welcome to HomeDecore</h1>


        </div>
    </div>
</section>




<section class="cards clearfix">
    <div class="card">
        <img src="image/bagno.jpg" alt="" class="image">
        <div class="card_copy">
            <h3>Articoli Bagno</h3>

        </div>

    </div>


    <div class="card">
        <img src="image/Soggiorno2.jpg" alt="" class="image">
        <div class="card_copy">
            <h3>I Nostri Soggiorni</h3>

        </div>

    </div>


    <div class="card">
        <img src="image/Cucina4.jpg" alt="" class="image">
        <div class="card_copy">
            <h3>Le Nostre Cucine</h3>

        </div>

    </div>

</section>

<section class="banner clearfix">
    <div class="banner_image"></div>
    <div class="banner_copy">
        <div class="banner_copy_txt">

            <h4>Scegli la cucina perfetta per la tua casa</h4>
            <p>Gli arredi e gli accessori sono realizzati e rifiniti a mano, garantendo qualità e stile per ogni ambiente. </p>
        </div>
    </div>
</section>

<section class="banner clearfix">
    <div class="banner_image float banner_image2"></div>
    <div class="banner_copy float">
        <div class="banner_copy_txt">

            <h4 class="right">Scegli il bagno perfetto per la tua casa</h4>
            <p class="right">Ogni elemento é progettato con cura e realizzato con materiali di alta qualità per garantire comfort e stile.</p>
        </div>
    </div>
</section>

<section class="banner clearfix">
    <div class="banner_image banner_image3"></div>
    <div class="banner_copy">
        <div class="banner_copy_txt">

            <h4>Scegli il soggiorno perfetto per la tua casa</h4>
            <p>Ogni dettaglio é pensato per offrire eleganza, comfort e funzionalità, creando un ambiente accogliente e armonioso.</p>
        </div>
    </div>
</section>



<%@include file = "footer.jsp" %>
</body>

<script>
    $(document).ready(function(){
        $(".header_icon-bar").click(function(e){
            $(".header_menu").toggleClass('is-open');
            e.preventDefault();/*mettiamo questo codice in questo modo dato che la rimozone e l inerimento della classe is open avviene su un link dobbiamo far si che la pagina non venga ricaricata cosi da poter attivare l evento*/
        });

    });
</script>
</html>

