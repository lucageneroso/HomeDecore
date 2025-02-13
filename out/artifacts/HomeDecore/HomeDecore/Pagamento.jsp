<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pagamento</title>
    <link rel="stylesheet" type="text/css" href="style/Pagamento.css">
    <script>
        function togglePaymentMethod(method) {
            document.getElementById("paypalForm").style.display = (method === "paypal") ? "block" : "none";
            document.getElementById("creditCardForm").style.display = (method === "creditcard") ? "block" : "none";
            document.getElementById("paymentMethod").value = method;
        }

        function formatCreditCardNumber(input) {
            let value = input.value.replace(/\D/g, ''); // Rimuove tutto tranne i numeri
            value = value.substring(0, 16); // Limita a 16 cifre effettive

            // Aggiunge spazi ogni 4 cifre
            input.value = value.replace(/(\d{4})/g, '$1 ').trim();
        }

        function validateForm() {
            let method = document.getElementById("paymentMethod").value;

            if (method === "paypal") {
                let email = document.getElementById("paypalEmail").value.trim();
                let password = document.getElementById("paypalPassword").value.trim();
                let regexEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

                if (email === "" || password === "") {
                    alert("Inserisci email e password di PayPal!");
                    return false;
                }

                if (!regexEmail.test(email)) {
                    alert("Inserisci un'email valida!");
                    return false;
                }
            } else if (method === "creditcard") {
                //rimuove gli spazi per effettuare il controllo del numero di cifre
                let numeroCarta = document.getElementById("numeroCarta").value.replace(/\s+/g, '');
                let dataScadenza = document.getElementById("dataScadenza").value.trim();
                let cvv = document.getElementById("CVVCarta").value.trim();

                let regexNumeroCarta = /^\d{16}$/;
                let regexDataScadenza = /^(0[1-9]|1[0-2])\/\d{2}$/;
                let regexCVV = /^\d{3}$/;

                // Controllo se tutti i campi sono compilati
                if (numeroCarta === "" || dataScadenza === "" || cvv === "") {
                    alert("Tutti i campi della carta di credito sono obbligatori!");
                    return false;
                }

                if (!regexNumeroCarta.test(numeroCarta)) {
                    alert("Il numero della carta deve contenere esattamente 16 cifre.");
                    return false;
                }

                if (!regexDataScadenza.test(dataScadenza)) {
                    alert("La data di scadenza deve essere nel formato MM/YY.");
                    return false;
                }

                if (!regexCVV.test(cvv)) {
                    alert("Il CVV deve essere composto da esattamente 3 cifre.");
                    return false;
                }
            }

            return true;
        }
    </script>
</head>
<body>


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
                <input type="text" name="numeroCarta" id="numeroCarta" placeholder="1234 5678 9012 3456" oninput="formatCreditCardNumber(this)" >
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
