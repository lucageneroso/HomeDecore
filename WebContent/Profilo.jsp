<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Ordine" %>
<%@ page import="model.User.Utente" %>
<%@ page import="model.UtenteDao" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>

<%
OrdineDAO ordineDao = new OrdineDAO();
List<Ordine> ordini = null;
String email=null;

if (request.getAttribute("email") == null) { // Usa "email" per consistenza
    System.out.println("Sessione");
    email = (String) session.getAttribute("Email");
    ordini = ordineDao.getOrdini(email);
} else {
    System.out.println("Richiesta");
    email = (String) request.getAttribute("email"); // Usa "email" per consistenza
    ordini = (List<Ordine>) request.getAttribute("ordini");
}

%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profilo Utente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: auto;
            padding: 20px;
        }
        .header-profilo {
            background-color: orange;
            color: #fff;
            padding: 10px;
            text-align: center;
            margin-bottom: 20px;
            border-radius:10px;
        }
        .info-box {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
        }
        .info-box h2 {
            margin-top: 0;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }
        .order-list {
            margin-top: 20px;
        }
        .order-item {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 10px;
        }
        .order-item h3 {
            margin-top: 0;
            border-bottom: 1px solid #ddd;
            padding-bottom: 5px;
        }
        .order-item p {
            margin: 5px 0;
        }
        
        .button-home{
        	margin:10px;
        	background-color:white;
        	border-color:orange;
        	border-radius:5px;
        	display: inline-block;
        	padding:5px;
        	border: 1px solid #ddd;
        }
        .content {
    max-width: 1200px;
    margin: 10px auto;
    padding: 10px;
    background-color: #f9f9f9;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
#product-list {
    list-style-type: none;
    padding: 0;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
}

#product-list li {
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 20px;
    margin: 10px;
    width: 200px;
    text-align: center;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s, box-shadow 0.3s;
}

#product-list li:hover {
    transform: scale(1.05);
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

#product-list li a {
    text-decoration: none;
    color: #333;
    font-size: 1.2em;
    display: block;
    margin-top: 10px;
}

#product-list li a:hover {
    color: #007bff;
}
    </style>
</head>
<body>


<div class="container">
    <div class="header-profilo">
        <h1>Profilo Utente</h1>
    </div>
    
    
    
    <div class="info-box">
        <h2>Informazioni Utente</h2>
        <% if (request.getAttribute("email") == null) { %>
            <!-- Mostra le informazioni dalla sessione -->
            <p><strong>Nome:</strong> <%= session.getAttribute("nome") %></p>
            <p><strong>Cognome:</strong> <%= session.getAttribute("cognome") %></p>
            <p><strong>Email:</strong> <%= request.getAttribute("email") != null ? request.getAttribute("email") : session.getAttribute("Email") %></p>
            <p><strong>Data di Nascita:</strong> <%= new SimpleDateFormat("dd/MM/yyyy").format(session.getAttribute("data_di_nascita")) %></p>
            <p><strong>IBAN:</strong> <%= session.getAttribute("Iban") %></p>
            <p><strong>Tipo Account:</strong> <%= session.getAttribute("Tipo_account").equals(0) ? "Standard" : "Amministratore" %></p>
        <% } else { 
        UtenteDao uDao= new UtenteDao();
        Utente u= uDao.doRetrieveByEmail(email);
        %>
        <p><strong>Nome:</strong> <%= u.getNome() %></p>
        <p><strong>Cognome:</strong> <%= u.getCognome() %></p>
        <p><strong>Email:</strong> <%= u.getEmail() %></p>
        <p><strong>Data di Nascita:</strong> <%= u.getData() %></p>
        <p><strong>IBAN:</strong> <%= u.getIBAN() %></p>
        <p><strong>Tipo Account:</strong> <%= u.getTipo_account()==0 ? "Standard" : "Amministratore" %></p>
        <%} %>
    </div>
    <% if (request.getAttribute("email") == null && session.getAttribute("Tipo_account").equals(0)){
    %>
     	<div class="button-home">
        	<a href="../src/main/java/webapp/Home.jsp">Torna alla Home</a>
    	</div>
    <%} else{%>
    <div class="button-home">
        	<a href="Amministratore.jsp">Torna ad Amministratore</a>
    	</div>
    <%} %>
    <div class="info-box">
        <h2>Ordini Effettuati</h2>
        <div class="order-list">
            
            <% if (ordini != null && !ordini.isEmpty()) { %>
                <% for (Ordine ordine : ordini) { %>
                    <div class="order-item">
                        <h3>Ordine <%= ordine.getNumeroOrdine() %> - Data: <%= new SimpleDateFormat("dd/MM/yyyy").format(ordine.getData()) %></h3>
                        <p><strong>Totale:</strong> <%= ordine.getTotale() %></p>
                        <p><strong>Stato:</strong> <%= ordine.getStato() %></p>
                        <p><strong>Numero Prodotti:</strong> <%= ordine.getNumeroProdotti() %></p>
                        
                        <div class="content">
        					<ul id="product-list">
            				<%
            				DettagliOrdineDAO dod= new DettagliOrdineDAO();
                            List<Prodotto> prodotti=  dod.getProdotti(ordine.getNumeroOrdine());
                			if (prodotti != null && !prodotti.isEmpty()) {
                   			for (Prodotto product : prodotti) {
                    	
                    		byte[] imageBytes = product.getImg();
                        	String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                        
                        	out.println("<li>" +
                                product.getNome() + "<br>" +
                                "<img src='data:image/jpg;base64," + base64Image + "' alt='Immagine Prodotto' style='max-width:100px;'>"+
                                "</a></li>");
                    	
                    }
                } else {
                    out.println("<li>Nessun prodotto disponibile</li>");
                }
            %>
        </ul>
    </div>
                        
                       
                       
                        
                    </div>
                <% } %>
            <% } else { %>
                <p>Nessun ordine trovato.</p>
            <% } %>
        </div>
    </div>
</div>

</body>
</html>
