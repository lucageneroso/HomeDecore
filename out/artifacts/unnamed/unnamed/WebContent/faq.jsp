<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
  <title>FAQ Page</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 20px;
      background-color: #f5f5f7;
      color: #1b1b1b;
    }
    
    h1 {
      text-align: center;
    }
    
    .question {
      margin-bottom: 10px;
      font-weight: bold;
    }
    
    .answer {
      margin-bottom: 20px;
    }
    
    .answer p {
      margin-bottom: 5px;
    }
    a:link { 
    font-style:italic;
    color: #ff6848;
    text-decoration: none;;
    }   
  </style>
</head>
<body>
  <h1>FAQ - Domande Frequenti</h1>
  
  <div class="question">
    Qual è lo scopo di questo sito?
  </div>
  <div class="answer">
    <p> L'obiettivo principale del progetto è quello di offrire una selezione unica e di alta qualità di capi d'abbigliamento vintage agli acquirenti, consentendo loro di trovare pezzi unici che non sono disponibili nei negozi tradizionali. 
        <br><br>
        Il sito che vogliamo realizzare vuole proporre un'esperienza d’acquisto facile e intuitiva per i clienti, fornendo foto di alta qualità dei prodotti, descrizioni dettagliate e informazioni sulle condizioni del capo. 
        <br>
        Infine, il nostro focus è quello di promuovere lo stile di vita sostenibile, incoraggiando i clienti a scegliere l'abbigliamento vintage come un'alternativa alla moda veloce e alla produzione di massa, contribuendo così a ridurre l'impatto ambientale della moda.
    </p>
  </div>
  
  <div class="question">
    Come posso contattare il servizio clienti?
  </div>
  <div class="answer">
    <p>Puoi contattare il nostro Servizio Clienti via <a href="tel:+393895045451">telefono</a> o <a href="mailto:support@newagain.com">email</a> per avere maggiori dettagli.</p>
  </div>
  
  <div class="question">
    Posso effettuare un reso?
  </div>
  <div class="answer">
    <p>Sì, abbiamo una politica di restituzione. 
        <br>Per ulteriori informazioni, consultare la pagina "Resi e rimborsi".</p>
  </div>

	<br><br><br>
	<jsp:include page="fragments/footer.jsp"/>
</body>
</html>