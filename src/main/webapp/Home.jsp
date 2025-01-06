
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
        <img src="image/bagno.jpeg" alt="" class="image">
        <div class="card_copy">
            <h3>Articoli Bagno</h3>

        </div>

    </div>


    <div class="card">
        <img src="image/soggiorno.jpg" alt="" class="image">
        <div class="card_copy">
            <h3>I Nostri Soggiorni</h3>

        </div>

    </div>


    <div class="card">
        <img src="image/cucina.jpeg" alt="" class="image">
        <div class="card_copy">
            <h3>Le Nostre Cucine</h3>

        </div>

    </div>

</section>

<section class="banner clearfix">
    <div class="banner_image"></div>
    <div class="banner_copy">
        <div class="banner_copy_txt">

            <h4>Scegli la bomboniera perfetta per il tuo evento</h4>
            <p>Gli orci sono realizzati e decorati a mano su tornio, immerso poi in smalto ceramico alimentare certificato. </p>
        </div>
    </div>
</section>

<section class="banner clearfix">
    <div class="banner_image float banner_image2"></div>
    <div class="banner_copy float">
        <div class="banner_copy_txt">

            <h4 class="right">Il nostro olio extravergine</h4>
            <p class="right">Olio extravergine di oliva ottenuto direttamente dalle olive e unicamente mediante processi meccanici.</p>
        </div>
    </div>
</section>

<section class="banner clearfix">
    <div class="banner_image banner_image3"></div>
    <div class="banner_copy">
        <div class="banner_copy_txt">

            <h4>I Nostri Ceci</h4>
            <p>La produzione dei ceci include semina, crescita e raccolta con cure colturali per garantire qualità.</p>
        </div>
    </div>
</section>



<%@include file = "footer.jsp" %>
</body>

<script>
    $(document).ready(function(){
        $(".header_icon-bar").click(function(e){
            $(".header_menu").toggleClass('is-open');
            e.preventDefault();/*mettiamo questo codice in questo modo dato che la rimozione e l inerimento della classe is open avviene su un link dobbiamo far si che la pagina non venga ricaricata cosi da poter attivare l evento*/
        });

    });
</script>
</html>

