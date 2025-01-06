<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }

        .container {
            width: 100%;
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .form-group button:hover {
            background-color: #45a049;
        }

        .message {
            text-align: center;
            margin-top: 20px;
        }

        .error {
            color: red;
        }

        .success {
            color: green;
        }

        .link_register
        {
            font-size: small;
            color: #ff1d00;
            text-decoration:none;
        }

        .link_register:hover
        {
            color: #2c1402;
            text-decoration:none;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Login</h1>

    <!-- Login Form -->
    <form action="/HomeDecore/login" method="POST">
        <div class="form-group">
            <label for="email">Email:</label>
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
    </div>
</div>

</body>
</html>
