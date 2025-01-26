<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="log.css">
    <title>Registration</title>

</head>
<body>

<div class="container">
    <h1>Registrazione</h1>

    <!-- Login Form -->
    <form action="/HomeDecore/login" method="POST">
        <div class="form-group">
            <label for="name">Nome*</label>
            <input type="text" id="name" name="name" placeholder="Es. Mario Rossi" required>
        </div>
        <div class="form-group">
            <label for="surname">Cognome*</label>
            <input type="text" id="surname" name="surname" placeholder="Es. Rossi" required>
        </div>
        <div class="form-group">
            <label for="birthdate">Data di nascita*</label>
            <input type="date" id="birthdate" name="birthdate" required>
        </div>
        <div class="form-group">
            <label for="email">Email*</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Password*</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="vat">p.IVA*</label>
            <input type="text" id="vat" name="vat" pattern="^[0-9]{11}$" placeholder="Es. 12345678901" required>
            <small>Inserisci un numero di 11 cifre</small>
        </div>
        <div class="form-group">
            <label for="iban">IBAN*</label>
            <input type="text" id="iban" name="iban" pattern="^[A-Za-z0-9]{27}$" placeholder="Es. IT60X0542811101000000123456" required>
            <small>Inserisci un IBAN di 27 caratteri</small>
        </div>
        <div class="form-group">
            <button type="submit">Registrati</button>
        </div>
        <small>*: campi obbligatori</small>
    </form>

    <!-- Success/Error message -->
    <!-- <div class="message">
        <c:if test="${not empty loggedUser}">
            <p class="success">Benvenuto, ${loggedUser.nome}!</p>
        </c:if>
        <c:if test="${empty loggedUser}">
            <p class="error">Login fallito. Email o password errati.</p>
        </c:if>
        <c:if test="${not empty loginError}">
            <p class="error">${loginError}</p>
        </c:if>
    </div>-->

</div>

</body>
</html>
