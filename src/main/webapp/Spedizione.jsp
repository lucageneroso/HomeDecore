<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dettagli di Spedizione</title>
    <link rel="stylesheet" type="text/css" href="style/Spedizione.css">
</head>
<body>
<div class="container">
    <h2>Inserisci i dettagli di spedizione</h2>

    <form action="SpedizioneServlet" method="post">
        <label>Indirizzo di Spedizione</label>
        <input type="text" name="indirizzo" placeholder="Inserisci il tuo indirizzo" required>

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

