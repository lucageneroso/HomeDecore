<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pagamento</title>
    <link rel="stylesheet" type="text/css" href="Pagamento.css">
    <script>
        function togglePaymentMethod(method) {
            document.getElementById("paypalForm").style.display = (method === "paypal") ? "block" : "none";
            document.getElementById("creditCardForm").style.display = (method === "creditcard") ? "block" : "none";
            document.getElementById("paymentMethod").value = method;


        }
        /*validazione dei vari campi dei metodi di pagamento prima dell'invio alla servlet apposita*/
        function validateForm() {
            let method = document.getElementById("paymentMethod").value;

            if (method === "paypal") {
                let email = document.getElementById("paypalEmail").value;
                let password = document.getElementById("paypalPassword").value;
                if (email.trim() === "" || password.trim() === "") {
                    alert("Inserisci email e password di PayPal!");
                    return false;
                }
            } else if (method === "creditcard") {
                let numeroCarta = document.getElementById("numeroCarta").value;
                let data = document.getElementById("dataScadenza").value;
                let cvv = document.getElementById("CVVCarta").value;
                if (numeroCarta.trim() === "" || data.trim() === "" || cvv.trim() === "") {
                    alert("Inserisci tutti i dati della carta di credito!");
                    return false;
                }
            }
            return true;
        }
    </script>
</head>
<body>
<h2>Conferma i dettagli di spedizione</h2>

<p><strong>Indirizzo:</strong> <%= session.getAttribute("indirizzo") %></p>
<p><strong>Metodo di Consegna:</strong> <%= session.getAttribute("metodoConsegna") %></p>
<p><strong>Spedizione Rapida:</strong> <%= session.getAttribute("spedizioneRapida") %></p>

<div class="container">
    <div class="payment-card">
        <h2>Pagamento</h2>
        <label>Seleziona il metodo di pagamento:</label>
        <div class="button-group">
            <button type="button" onclick="togglePaymentMethod('paypal')">PayPal</button>
            <button type="button" onclick="togglePaymentMethod('creditcard')">Carta di Credito</button>
        </div>


        <form action="processPayment" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="paymentMethod" id="paymentMethod" value="paypal">

            <div id="paypalForm" class="payment-form">
                <label>Email PayPal</label>
                <input type="email" name="paypalEmail" id="paypalEmail" placeholder="Inserisci la tua email PayPal" >
                <label>Password</label>
                <input type="password" name="paypalPassword" id="paypalPassword" placeholder="Inserisci la tua password" >
            </div>


            <div id="creditCardForm" class="payment-form" style="display: none;">
                <label>Numero Carta</label>
                <input type="text" name="numeroCarta" id="numeroCarta" placeholder="1234 5678 9012 3456" >
                <label>Data di Scadenza</label>
                <input type="text" name="dataScadenza" id="dataScadenza" placeholder="MM/YY" >
                <label>CVV</label>
                <input type="text" name="CVVCarta" id="CVVCarta" placeholder="123" >
            </div>

            <button type="submit" class="pay-button">Procedi al Pagamento</button>
        </form>
    </div>
</div>
</body>
</html>
