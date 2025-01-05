<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
    <!--<link rel="stylesheet" href="css/header.css">-->
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        /* Stile del body con sfondo personalizzato */
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: radial-gradient(circle, rgba(255,215, 0, 0.8), rgba(255, 165, 0, 0.5), rgba(255, 255, 255, 0));
            background-size: cover;
            background-attachment: fixed;
        }

        /* Logo in alto a sinistra */
        .logo {
            position: absolute;
            top: 10px;
            left: 20px;
            max-height: 50px;
            z-index: 1;
        }


        /* Stile per la barra di ricerca
        .cerca-form {
            display: inline-block;
        }

        .cerca-form input {
            padding: 5px;
            margin-right: 5px;
        }

        .cerca-form button {
            padding: 5px;
        }
        */

        /* Stile per i riquadri delle categorie */
        .category-container {
            text-align: center; /* Allinea il testo al centro */
            padding: 20px; /* Padding intorno al contenitore delle categorie */
            display: flex; /* Usa un layout flessibile */
            flex-wrap: wrap; /* Avvolgi i contenuti su pi� righe se necessario */
            justify-content: space-around; /* Distribuisci uniformemente i box con spazio attorno */
        }

        .category-container a{
            text-decoration: none;
        }

        .category-box {
            width: 22%; /* Larghezza del box delle categorie */
            height: 300px; /* Altezza fissa per uniformare tutti i box */
            margin: 10px; /* Margine tra i box */
            background-color: #fff; /* Colore di sfondo */
            border: 1px solid #ccc; /* Bordo */
            border-radius: 5px; /* Angoli arrotondati */
            padding: 20px; /* Spazio interno */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Ombra */
            text-align: center; /* Allinea il testo al centro */
            display: flex; /* Usa un layout flessibile */
            flex-direction: column; /* Direzione dei contenuti: verticale */
            justify-content: space-between; /* Distribuisci uniformemente i contenuti all'interno */
            box-sizing: border-box; /* Assicura che il padding e il bordo non aumentino la dimensione del box */
        }

        .category-box img {
            max-width: 100%; /* Larghezza massima dell'immagine */
            height: 65%; /* Mantieni le proporzioni dell'immagine */
            border-radius: 5px; /* Angoli arrotondati dell'immagine */
            margin-bottom: 10px; /* Margine inferiore */
        }

        .category-box h2 {
            font-size: 20px;
            color: #FFA500;
            margin-bottom: 10px;
        }


        @media screen and (max-width: 1024px) {
            .category-box {
                width: 45%;
            }
        }

        @media screen and (max-width: 600px) {
            .category-box {
                width: 90%;
            }
        }

        /* Contenitore principale */
        .container-home {
            max-width: 85%;
            margin: 0 auto;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.9);
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            text-align: center;
            margin-top: 20px;
            position: relative;
        }

        /* Stili per il carosello */
        .ekw_carousel_container {
            position: relative;
            max-width: 100%;
            overflow: hidden;
            margin-top: 20px;
            border-radius: 15px;
            width: 100%; /* Imposta la larghezza al 100% per adattarsi al contenitore padre */
            margin-left: auto;
            margin-right: auto;
            z-index: 3;
        }

        .ekw_content_container {
            display: flex;
            transition: transform 0.5s ease;
            width: 400%; /* Imposta la larghezza al 100% per adattarsi al contenitore */
            overflow: hidden; /* Nasconde eventuali overflow orizzontali */
        }

        .ekw_content_container div {
            flex: 0 0 25%; /* Ogni slide occupa il 33.33% della larghezza quando la larghezza del dispositivo � inferiore a 768px */
            max-width: 100%; /* Massima larghezza di ogni slide */
        }

        .ekw_content_container img {
            width: 100%; /* Assicurati che tutte le immagini abbiano lo stesso width */
            height: auto;
            object-fit: contain;
            max-height: 500px;
        }

        .ekw_control_container {
            position: absolute;
            top: 50%;
            width: 100%;
            display: flex;
            justify-content: space-between;
            transform: translateY(-50%);
        }

        .ekw_control_container .material-icons {
            cursor: pointer;
            font-size: 30px;
            color: #FF8C00;
        }




        .header-Home {
            padding: 20px;
            display: flex;
            justify-content: center; /* Allinea i due elementi ai bordi della container */
            align-items: center; /* Centra verticalmente i contenuti */
            text-align:center;
            background-color: rgba(255, 255, 255, 0); /* Sfondo trasparente con opacit� */
        }

        .header-Home h1 {
            font-size: 32px;
            color: #333;
            margin: 0; /* Rimuove i margini esterni per il testo */
            background-color: rgba(255, 255, 255, 0); /* Sfondo trasparente con opacit� */
            padding: 10px 20px; /* Padding per spaziatura interna */
            border-radius: 5px; /* Bordi arrotondati */
            text-align:center;
        }

    </style>
</head>
<body>

<%@ include file="header.jsp" %>



<div class="container-home">
    <div class="header-Home">
        <h1>Benvenuti su HomeDecor</h1>
    </div>

    <!-- Carosello -->
    <div class="ekw_carousel_container">
        <div class="ekw_content_container">
            <div><img src="images/slide1.jpeg" alt="Image 1"></div>
            <div><img src="images/slide2.jpeg" alt="Image 2"></div>
            <div><img src="images/slide3.jpeg" alt="Image 3"></div>
            <div><img src="images/slide4.jpeg" alt="Image 4"></div>
        </div>
    </div>

    <div class="category-container">
        <div class="category-box">
            <a href="Product.jsp?categoria=Bagno">
                <img src="images/bagno.jpeg" alt="Categoria Bagno">
                <h2>Bagno</h2>
            </a>
        </div>
        <div class="category-box">
            <a href="Product.jsp?categoria=Cucina">
                <img src="images/cucina.jpeg" alt="Categoria Cucina">
                <h2>Cucina</h2>
            </a>
        </div>
        <div class="category-box">
            <a href="Product.jsp?categoria=Soggiorno">
                <img src="images/soggiorno.jpg" alt="Categoria Soggiorno">
                <h2>Soggiorno</h2>
            </a>
        </div>
        <div class="category-box">
            <a href="tuttiProdotti.jsp">
                <img src="images/tutti.jpeg" alt="Tutti i prodotti">
                <h2>Tutti i prodotti</h2>
            </a>
        </div>
    </div>



</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.imagesloaded/4.1.4/imagesloaded.pkgd.min.js"></script>
<script>
    // Funzione per gestire il menu hamburger
    $(document).ready(function() {
        $('.hamburger').click(function() {
            $('.dropdown-content').slideToggle();
        });
    });








    // Funzione per scorrere automaticamente il carosello
    $(document).ready(function() {
        var currentPosition = 0;
        var slides = $('.ekw_content_container div');
        var numberOfSlides = slides.length;
        var slideWidth = slides.eq(0).outerWidth();

        // Impostazione della larghezza del contenitore del carosello
        $('.ekw_content_container').css('width', slideWidth * numberOfSlides);

        // Funzione per avanzare automaticamente il carosello
        function nextSlide() {
            if (currentPosition < numberOfSlides - 1) {
                currentPosition++;
            } else {
                currentPosition = 0;
            }
            $('.ekw_content_container').css({
                'transform': 'translateX(' + (-currentPosition * slideWidth) + 'px)',
                'transition': 'transform 0.5s ease'
            });
        }

        // Imposta l'intervallo per lo scorrimento automatico
        var slideInterval = setInterval(nextSlide, 3000);

        // Pausa lo scorrimento automatico quando il mouse entra nel carosello
        $('.ekw_carousel_container').mouseenter(function() {
            clearInterval(slideInterval);
        });

        // Riavvia lo scorrimento automatico quando il mouse esce dal carosello
        $('.ekw_carousel_container').mouseleave(function() {
            slideInterval = setInterval(nextSlide, 3000);
        });

        // Gestisce il reset della transizione dopo il completamento dell'animazione
        $('.ekw_content_container').on('transitionend', function() {
            if (currentPosition === numberOfSlides - 1) {
                $('.ekw_content_container').css('transition', 'none');
            }
        });
    });

    // Funzione per la ricerca
    function submitSearch(event) {
        event.preventDefault(); // Previeni il comportamento predefinito del link

        var searchInput = document.getElementById("searchInput");
        var nome = searchInput.value.trim();

        if (nome !== "") {
            var url = "product?action=search&nome=" + encodeURIComponent(nome);
            window.location.href = url;
        }
    }

    $(document).ready(function() {
        var $slider = $('.ekw_content_container');
        var slideCount = $slider.children().length;
        var slideWidth = $slider.children().first().width();
        var currentIndex = 0;

        function goToSlide(index) {
            $slider.css('transform', 'translateX(' + (-index * slideWidth) + 'px)');
        }

        $('.left').click(function() {
            currentIndex = (currentIndex > 0) ? currentIndex - 1 : slideCount - 1;
            goToSlide(currentIndex);
        });

        $('.right').click(function() {
            currentIndex = (currentIndex < slideCount - 1) ? currentIndex + 1 : 0;
            goToSlide(currentIndex);
        });

        setInterval(function() {
            currentIndex = (currentIndex < slideCount - 1) ? currentIndex + 1 : 0;
            goToSlide(currentIndex);
        }, 3000);
    });










</script>


</body>
</html>