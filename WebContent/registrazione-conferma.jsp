<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione Confermata</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #fff; /* Colore di sfondo generale */
            color: #333; /* Colore testo generale */
        }
        
        .container {
            width: 100%;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        
        .success-icon {
            width: 100px;
            height: 100px;
            background-color: orange;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 20px;
            animation: bounce-in 1s ease-out;
        }
        
        .success-icon i {
            font-size: 3em;
            color: #fff;
        }
        
        @keyframes bounce-in {
            0% {
                transform: scale(0);
            }
            70% {
                transform: scale(1.2);
            }
            100% {
                transform: scale(1);
            }
        }
        
        h1 {
            font-size: 2em;
            text-align: center;
            margin-bottom: 20px;
            color: orange;
        }
        
        p {
            font-size: 1.2em;
            text-align: center;
            margin-bottom: 20px;
        }
        
        .back-to-home {
            text-align: center;
            margin-top: 20px;
        }
        
        .back-to-home a {
            text-decoration: none;
            background-color: orange;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 1.2em;
            transition: background-color 0.3s ease;
        }
        
        .back-to-home a:hover {
            background-color: #f90;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="success-icon">
        <i class="fas fa-check"></i>
    </div>
    <h1>Registrazione completata!</h1>
    <p>Grazie per esserti registrato!</p>
    <div class="back-to-home">
        <a href="../src/main/java/webapp/Home.jsp">Torna alla Home</a>
    </div>
</div>

</body>
</html>
