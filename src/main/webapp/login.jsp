<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Login</title>

</head>
<body>

<div class="container">
    <h1>Login</h1>

    <!-- Login Form -->
    <form action="/HomeDecore/login" method="POST">
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <button type="submit">Login</button>
        </div>
        <a class="link_register" href="register.jsp"><i>Non sei ancora registrato?</i></a>
    </form>

    <!-- Success/Error message -->
    <div class="message">
        <c:if test="${not empty loggedUser}">
            <p class="success">Benvenuto, ${loggedUser.nome}!</p>
        </c:if>
        <c:if test="${empty loggedUser}">
            <p class="error">Login fallito. Email o password errati.</p>
        </c:if>
        <c:if test="${not empty loginError}">
            <p class="error">${loginError}</p>
        </c:if>
    </div>
</div>

</body>
</html>
