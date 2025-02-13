<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dettagli di Spedizione</title>
    <link rel="stylesheet" type="text/css" href="style/Spedizione.css">
    <script>
        function validaForm() {
            let indirizzo = document.getElementById("indirizzo").value.trim();
            // Espressione regolare per verificare la presenza del numero civico
            let regexNumeroCivico = /^[A-Za-zÀ-ÿ\s]+[^\d\s]\s\d+[A-Za-z0-9]*$/;



            if (indirizzo === "") {
                alert("L'indirizzo non può essere vuoto.");
                return false;
            }

            if (!regexNumeroCivico.test(indirizzo)) {
                alert("L'indirizzo deve contenere il nome della via e un numero civico (es: Via Roma 10).");
                return false;
            }

            return true; // Se tutto è corretto, il form viene inviato
        }
    </script>
</head>

<body>
<div class="container">
    <h2>Inserisci i dettagli di spedizione</h2>

    <% String error = (String) session.getAttribute("spedizioneError");
        if (error != null) { %>
    <p style="color: red;"><%= error %></p>
    <% session.removeAttribute("spedizioneError"); } %>

    <form action="SpedizioneServlet" method="post" onsubmit="return validaForm();">
        <label>Indirizzo di Spedizione</label>
        <input id="indirizzo" type="text" name="indirizzo" placeholder="Inserisci il tuo indirizzo" required>

        <label>Metodo di Consegna</label>
        <select name="metodoConsegna" required>
            <option value="standard">Standard (3-5 giorni)</option>
            <option value="express">Express (1-2 giorni)</option>
        </select>

        <label>Spedizione Rapida?</label>
        <input type="checkbox" name="spedizioneRapida" value="si"> Sì

        <button type="submit">Vai al Pagamento</button>
    </form>
</div>
</body>
</html>

