<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	Cart cart = (Cart) request.getAttribute("cart");
	double totale=0;
	int numProdotti=0;
%>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,model.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Conferma</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f6f5f7;
            text-align:center;
        }

        h2 {
            color: #333;
        }

		#conf {
		            display: flex;
		            align-items: center;
					text-align: center;
					justify-content: center;
		            width: 60vw;
		            margin: 0 auto;
		            padding: 20px;
		            background-color: #ff6848;
		            border: 1px solid #ddd;
		            border-radius: 5px;
					font-family: Arial, Sans-Serif;
					
					font-size:100%;
		        }
		
		#tab{
			width: 400px;
		}
		#tab th, td {
		    padding: 5px;
			vertical-align: top;
			
		  }
		  
		  #tab td:first-child {
		    text-align: left;
		   
			
		  }
		  
		  #tab td:last-child {
		    text-align: right;
		  }

		table.cart-table {
		    width: 100%;
		    text-align:center;
		    vertical-align: middle;
		    border-color: white 3px; 
		    border-radius:5px;
		    margin-top: 20px;
		    transition: transform 0.3s ease-in-out;
		}
		table.cart-table:hover {
		    transform: scale(1.05);
		}
		
		#table1 td{ 
		align-items: center; 
		justify-content: center; 
		vertical-align:middle;
		border:none;
		}
		
		input[type="submit"]:hover {
		  background-color: #FF6848;
		  border: 1px solid #f6f5f7;
		  border-radius: 14px;
		}
		
		input[type="submit"] {
		  background-color: #333;
		  color: #fff;
		  border: none;
		  margin: 27px;
		  padding: 10px 20px;
		  cursor: pointer;
		  transition: all 0.3s ease-in-out;
		}
	       #image{
	       width: 10vw;
	       height: 10vw;
	       min-width:150px;
	       min-height: 150px;
	       }
	       
	    
		@media (max-width: 600px) {
			#conf{
				width:85%;
				font-size:2.5vw;
			}
}
	    
    </style>
</head>
 
<body>
<br><br><br>
<div id="conf">

	<form action="acquista" method="post">
		<input type="hidden" name="action" value="completa">
		<input type="hidden" name="email" value="<%= session.getAttribute("email") %>">

		<h2>Conferma ordine</h2>
		<table id="tab">
		
		<tr>
		<input type="hidden" name="Tipocons" value="<%= request.getAttribute("TipoC") %>">
		<td align="left">Tipo di consegna</td>
		<td><%= request.getAttribute("TipoC") %></td>
		<% String tipoc = (String) request.getAttribute("TipoC");
		            if (tipoc.equals("Consegna a casa")) { %>
		</tr>
		<tr>
		<td align="left">Indirizzo</td>
		<td><input type="hidden" name="indirizzo" value="<%= session.getAttribute("indirizzo") %>">
   					<%= session.getAttribute("indirizzo") %>
  					<input type="hidden" name="citta" value="<%= session.getAttribute("citta") %>">
                    <%= session.getAttribute("citta") %>
                    <input type="hidden" name="provincia" value="<%= session.getAttribute("provincia") %>">
                    <%= session.getAttribute("provincia") %>
                    <input type="hidden" name="cap" value="<%= session.getAttribute("cap") %>">
                    <%= session.getAttribute("cap") %></td>
                    </tr>
    <%}else if(tipoc.equals("Altro indirizzo")){ %>
    <tr>
    <td align="left">Indirizzo</td>
    				<td><input type="hidden" name="indirizzo" value="<%= request.getAttribute("indirizzo") %>">
   				    <%= request.getAttribute("indirizzo") %>
   				    <input type="hidden" name="citta" value="<%= request.getAttribute("citta") %>">
                    <%= request.getAttribute("citta") %>
                    <input type="hidden" name="provincia" value="<%= request.getAttribute("provincia") %>">
                    <%= request.getAttribute("provincia") %>
                    <input type="hidden" name="cap" value="<%= request.getAttribute("cap") %>">
                    <%= request.getAttribute("cap") %>    </td>	
    <% }
    %></tr>
		<tr>
		<td></td>
		<td></td>
		</tr>
		<tr>
		<td></td>
		<td></td>
		</tr>
		<tr>
		<td></td>
		<td></td>
		</tr>
		<tr>
		 <input type="hidden" name="Pagamento" value="<%= request.getAttribute("Pagamento") %>">
		<td align="left">Pagamento</td>
		<td><%= request.getAttribute("Pagamento") %>
		<% String pagamento = (String) request.getAttribute("Pagamento");
		            if (pagamento.equals("Carta di credito")) { %> </td>
		</tr>
		<tr>
		 <input type="hidden" name="NumeroC" value="<%= request.getAttribute("numeroCarta") %>">
		<td align="left">Numero Carta</td>
		<td><%= request.getAttribute("numeroCarta") %></td>
		</tr>
		<tr>
		<input type="hidden" name="TitolareC" value="<%= request.getAttribute("card-holder") %>">
		<td align="left">Titolare Carta</td>
		<td><%= request.getAttribute("card-holder") %></td>
		</tr>
		<tr>
		 <input type="hidden" name="ScadenzaC" value="<%= request.getAttribute("scadenzaCarta") %>">
		<td align="left">Scadenza Carta</td>
		<td><%= request.getAttribute("scadenzaCarta") %></td>
		</tr>
		<tr>
		 <input type="hidden" name="cvv" value="<%= request.getAttribute("cvv") %>">
		<td align="left">CVV</td>
		<td><%= request.getAttribute("cvv") %></td>
		</tr>
		<% } %>
		</table>
		<% if (cart != null) { %>
			<table class="cart-table" border="1" id="table1">
				<% for (Prodotto p : cart.getProducts()) {
					
					totale+=p.getPrezzo();
					numProdotti++;
					
					byte[] imageB = p.getImg();
					String base64img = "";
					if (imageB != null) {
						base64img = Base64.getEncoder().encodeToString(imageB);
					}
				%>
					<tr data-product-id="<%= p.getID() %>">
						<td><img src="data:image/jpg;base64, <%= base64img %>" width="100" height="100" alt="#"></td>
						<td><%= p.getNome() %></td>
						<td><span id="totalPrice_<%= p.getID() %>"><%= p.getPrezzo() %>€</span></td>
					</tr>
				<% } %>
			</table>
		<% } %>
						<p> Totale: <%= Math.round(totale * 100.0) / 100.0 %> € </p>
						<input type="hidden" name="totale" id="totalHidden" value="<%= Math.round(totale * 100.0) / 100.0 %>">
						<input type="hidden" name="numProdotti" id="totalHidden" value="<%= numProdotti %>">
						<input type="submit" value="Acquista Ora">
			</form>
			
</div>
</body>
</html>
