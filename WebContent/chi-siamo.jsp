
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi Siamo</title>
    <link rel="stylesheet" href="css/header.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <style>
    	body {
            background-color: #f8f9fa; /* Colore di sfondo generale */
        }
        
        .container_chi {
            font-family: 'Arial', sans-serif; /* Tipo di carattere predefinito */
            line-height: 1.6; /* Altezza della riga */
            background-color: #fff; /* Colore di sfondo */
            color: #333; /* Colore del testo principale */
            padding: 20px; /* Spaziatura interna */
            margin-top: 80px; /* Assicura che il contenuto non venga sovrapposto all'header */
            max-width: 1200px; /* Limita la larghezza del contenitore per una migliore leggibilit� */
            margin-left: auto; /* Centra il contenitore rispetto alla larghezza massima */
            margin-right: auto;
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1); /* Ombra leggera */
            border-radius:10px;
            width:90%;
        }
        
        .container_chi h1 {
            font-size: 36px; /* Dimensione del font per il titolo principale */
            text-align: center; /* Allinea il titolo al centro */
            margin-bottom: 20px; /* Spaziatura inferiore per il titolo */
            color: #333; /* Colore del testo del titolo */
        }
        
        .container_chi h2 {
            font-size: 28px; /* Dimensione del font per i sottotitoli */
            margin-top: 30px; /* Spaziatura superiore per i sottotitoli */
            color: #555; /* Colore del testo dei sottotitoli */
            border-bottom: 2px solid #f58220; /* Linea sottile arancione sotto i sottotitoli */
            padding-bottom: 10px; /* Spaziatura inferiore */
        }
        
        .container_chi p {
            font-size: 16px; /* Dimensione del font per i paragrafi */
            text-align: justify; /* Allinea il testo giustificato */
            margin-bottom: 15px; /* Spaziatura inferiore per i paragrafi */
            color: #666; /* Colore del testo dei paragrafi */
        }
        
        .container_chi a {
            display: block; /* Mostra il link come blocco */
            text-align: center; /* Allinea il testo del link al centro */
            margin: 30px auto 0; /* Spaziatura superiore (30px), nessuna spaziatura inferiore, e margine laterale automatico per centrare */
            color: #f58220; /* Colore del link arancione */
            text-decoration: none; /* Rimuove la sottolineatura */
            font-weight: bold; /* Testo in grassetto */
            transition: color 0.3s; /* Transizione del colore al passaggio del mouse */
        }
        
        .container_chi a:hover {
            color: #333; /* Cambia colore al passaggio del mouse a nero */
        }
    </style>
    
</head>
<body>

	<%@ include file="../src/main/java/webapp/header.jsp" %>

	<div class="container_chi">
    <h1>Chi Siamo</h1>
    <p>Benvenuti nella pagina Chi Siamo. Qui troverete informazioni sulla nostra azienda, la nostra missione e i nostri valori.</p>
    <h2>La nostra Storia</h2>
    <p>
    La nostra azienda � stata fondata nel 2018 da tre amici: Luca, Pietro e Riccardo, con una passione comune per l'innovazione nel settore dei prodotti per la casa. 
    Il loro viaggio � iniziato con una semplice idea: rendere pi� accessibili e convenienti i prodotti di qualit� per la casa, fornendo un servizio diretto e personalizzato ai clienti.
    </p>
    <h2>Il nostro Team</h2>
    <p>
    Il nostro team � composto da professionisti con una vasta esperienza nel settore dei prodotti per la casa, uniti dalla passione per l'eccellenza e il servizio clienti. 
    Ognuno di noi porta competenze uniche che contribuiscono al successo e alla crescita continua dell'azienda.
    </p>
    <h2>Contattaci</h2>
    <p>Per ulteriori informazioni, potete contattarci sui social</p>
    <a href="../src/main/java/webapp/Home.jsp">Torna alla Home</a>
    </div>
    
    <%@ include file="fragments/footer.jsp" %>
</body>
</html>