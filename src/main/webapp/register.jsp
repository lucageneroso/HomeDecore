<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/log.css">
    <title>Registration</title>

</head>
<body>

<div class="container">
    <h1>Registrazione</h1>

    <!-- Login Form -->
    <form action="/HomeDecore/register" method="post">
        <div class="form-group">
            <label for="name">Nome*</label>
            <input type="text" id="name" name="name" placeholder="Es. Mario Rossi" required>
        </div>
        <div class="form-group">
            <label for="surname">Cognome*</label>
            <input type="text" id="surname" name="surname" placeholder="Es. Rossi" required>
        </div>

        <div class="form-group">
            <label for="email">Email*</label>
            <input type="email" id="email" name="email" required
                   pattern="^[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)+$"
                   minlength="7" maxlength="50"
                   title="Inserisci un'email valida tra 4 e 50 caratteri (es: nome.cognome@email.com), con almeno un punto dopo la chiocciola.">
        </div>



        <div class="form-group">
            <label for="password">Password*</label>
            <input type="password" id="password" name="password" required
                   pattern="^(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{4,20}$"
                   title="La password deve contenere almeno una maiuscola, un numero e avere tra 4 e 20 caratteri.">
        </div>

        <div class="form-group">
            <label for="provincia">Provincia*</label>
            <input type="text" id="provincia" name="provincia" placeholder="Es. Milano" required>
        </div>
        <div class="form-group">
            <label for="citta">Città*</label>
            <input type="text" id="citta" name="citta" placeholder="Es. Milano" required>
        </div>
        <div class="form-group">
            <label for="via">Via*</label>
            <input type="text" id="via" name="via" placeholder="Es. Via Roma" required>
        </div>
        <div class="form-group">
            <label for="numCivico">Numero Civico*</label>
            <input type="number" id="numCivico" name="numCivico" placeholder="Es. 12" required>
        </div>
        <div class="form-group">
            <label for="cap">CAP*</label>
            <input type="number" id="cap" name="cap" placeholder="Es. 20100" required>
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
