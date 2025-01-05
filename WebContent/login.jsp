<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
String mess = (String) request.getAttribute("errore"); 
String mess2 = (String) request.getAttribute("errore2");
String mess3 = (String) request.getAttribute("errore3");

%>
<!DOCTYPE html>
<html lang="it">
	<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, control.*,java.sql.Connection, java.sql.*, model.*"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Accedi</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    	<link href="https://fonts.googleapis.com/css?family=Aleo|Slabo+27px" rel="stylesheet">
    	<style>
    	.container1 {
			background-color: white;
			box-shadow: 0px 2px 10px rgba(255, 255, 255, 0.2);
			border-radius: 10px;text-align: left;
			display: none;
			margin-top: 0;
			}
			
			#count
			{
			   display:none;
			   position:relative ;
			   margin:4%; margin-bottom:2%;
			   color:#000; font-size:18px;
			   text-indent:40px;
			   font-weight:bolder;
			   
			}
			#see
			{
				display:inline-block;
				color:gray;
				margin:auto; 
				float: right;
				margin-right:40px;
				margin-top:20px;
				cursor:pointer; 
				transition-duration:0.2s;
			}
			
			i
			{
				font-size:20px;
				transition-duration:600ms;    
			}
			#check0,#check1,#check2,#check3,#check4
			{
			   display:block;
			   position:relative;
				padding: 3px;
			   color:rgb(214, 47, 12); 
			   font-size:12px;  
			}
			span
			{
				margin:10px;
				font-weight: bold;
				font-size:12px;
			}
		@media (max-width: 710px) {
		  .container1 span{
			font-size: 10px;
		  }
		  #check0,#check1,#check2,#check3,#check4{
			padding: 0;
		  }
		  
		  .container1 i {
			font-size: 13px;
		  }
		}
		#err{
			color: red;
			display: none;
			font-size: 12px;
		}
    	</style>
	</head>
<body>

<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form action="registration" method="post" >
		<input type="hidden" name="action" value="insert">
			<h1>Crea Account</h1>
			<div class="form">
				<div class="form-row">
				  <input type="text" name="nome" placeholder="Nome" />
				  <input type="text" name="cognome" required placeholder="Cognome">
				</div>
				<div>
				  <input type="email" name="Email" required placeholder="Email">
				  <input type="text" name="IBAN" required placeholder="IBAN">
				  <input type="number" name="Tipo_account" required placeholder="TipoAccount">
				</div>
				<div>
				  <input type="password" name="PasswordCliente" id="pass" required placeholder="Password" onInput="check()" />
					<div id="err">Password non valida</div>
				</div>
				<div class="container1">
			<div id="check4">
                <i class="far fa-check-circle"></i>  <span>Deve contenere almeno 8 caratteri</span>
           </div>
		 <div id="check1">
                <i class="far fa-check-circle"></i>  <span> Contiene una lettera maiuscola</span>
           </div>
           <div id="check2">
                <i class="far fa-check-circle"></i>  <span> Contiene un carattere numerico</span>
           </div>
           <div id="check3">
                <i class="far fa-check-circle"></i>   <span>Contiene un carattere speciale</span>
           </div>
           
    </div>
	<br>
				<div>
				<%
				if (mess2 != null) { %>
					<p style="color: red;"><%=mess2%></p>
					<% } %>
				 <button  type="submit" value="Crea" onclick="validatePassword()">Crea </button>
				</div>
			  </div>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="registration" method="post">
		<input type="hidden" name="action" value="login">
			<h1>Accedi</h1>
			<input type="email" name="username" required placeholder="Username">
			<input type="password" name="pass" required placeholder="Password">
			<%
	if (mess != null) { %>
	<p style="color: red;"><%=mess%></p>
	
<% } %>
<%
				if (mess3 != null) { %>
					<p style="color: red;"><%=mess3%></p>
					<% } %>
			<button>Accedi</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Bentornato!</h1>
				<p>Se sei già registrato inserisci le tue credenziali</p>
				<button class="ghost" id="signIn">Accedi</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Benvenuto!</h1>
				<p>Crea un account per conoscerci meglio</p>
				<%
				if (mess2 != null) { %>
					<p style="color: red;"><%=mess2%></p>
					<% } %>
				<button class="ghost" id="signUp">Registrati</button>
			</div>
		</div>
	</div>
</div>


<script>
function showContainer() {
  var passwordInput = document.getElementById('pass');
  var container = document.querySelector('.container1');
  
  passwordInput.addEventListener('click', function() {
    container.style.display = 'block';
  });
}

showContainer();

const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});
</script>
	<script>
	var is_visible = false;


function check()
{
    var input = document.getElementById("pass").value;
    
    input=input.trim();
    document.getElementById("pass").value=input;
    
	var passwordInput = document.getElementById('pass');
  var countMessage = document.getElementById('check4');
  
  var password = passwordInput.value;
  var passwordLength = password.length;
  
  if (passwordLength < 8) {
    countMessage.querySelector("i").className = "far fa-check-circle invalid";
    countMessage.style.color = "red";
  } else {
    countMessage.querySelector("i").className = "far fa-check-circle valid";
    countMessage.style.color = "green";
  }
  if (/[A-Z]/.test(input)) {
	    document.getElementById("check1").style.color = "green";
	} else {
	    document.getElementById("check1").style.color = "red";
	}
	
    if(input.match(/[0-9]/i))
    {
        document.getElementById("check2").style.color="green";
    }
    else
    {
       document.getElementById("check2").style.color="red"; 
    }

    if(input.match(/[^A-Za-z0-9-' ']/i))
    {
        document.getElementById("check3").style.color="green";
    }
    else
    {
       document.getElementById("check3").style.color="red"; 
    }
    
    
}
	</script>
<script type="text/javascript" src="script.js"></script>
<script>
    function validatePassword() {
      var isPasswordValid = true; // Inizialmente, considera la password come valida

      // Verifica i colori dei check e controlla se tutti i requisiti sono soddisfatti
      var check1Color = document.getElementById("check1").style.color;
      var check2Color = document.getElementById("check2").style.color;
      var check3Color = document.getElementById("check3").style.color;
      var check4Color = document.getElementById("check4").style.color;

      if (check1Color !== "green" || check2Color !== "green" || check3Color !== "green" || check4Color !== "green") {
        isPasswordValid = false; // La password non è valida se uno dei requisiti non è soddisfatto
        document.getElementById("pass").style.border = "2px solid red"; // Colora il bordo di rosso
		document.getElementById("pass").style.borderRadius = "5px";
	  } else {
        document.getElementById("pass").style.border = "2px solid red"; // Ripristina lo stile del bordo predefinito
		document.getElementById("pass").style.borderRadius = "5px";
	  }

      if (!isPasswordValid) {
       document.getElementById("err").style.display = "block";
        event.preventDefault(); // Blocca l'invio del form
      }
    }
  </script>
</body>
</html>