<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href=".\CSS\style_index.css" rel="stylesheet" type="text/css" />
		<title>OuiCrèches</title>
		<!-- on inclut jQuery via CDN -->
        <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
		<script  type="text/javascript">
		function include(file) {
		    var oScript =  document.createElement("script");
		    oScript.src = file;
		    oScript.type = "text/javascript";
		    document.body.appendChild(oScript);
		}
		
		include("script.js");
		

		</script>
	</head>
	<body>
		<div id="btns_connexion">
			<img alt="" src=".\Images\OuiCr%E8chesLogo.png" id="logoBandB">
		</div>
		
		
		<h2>Besoin d'une garde d'enfant occasionnelle ?  Réservez rapidement au sein de votre crèche partenaire</h2>
		<div id="rectangle1"></div>
		
		<section id="parent">
			<a href="/Plateforme_web_B_and_B/connexion" onclick=""><img alt="" src=".\Images\p%E8re%20et%20b%E9b%E9%20page%20de%20garde.jpg" id="père_enfant_picture" /></a>
		</section>
		<div id="libellé_je_suis_parent">
			<h1>Je suis parent</h1>
			<p id="sous-titre_parent">Je réserve via mon compte</p>
		</div>
		
		<div id="rectangle2"></div>
		<section id="pro">
			<a href="/Plateforme_web_B_and_B/connexion" onclick=""><img alt="" src=".\Images\Gestionnaire%20page%20de%20garde.jpg" id="gestionnaire_picture" /></a>
		</section>
		<div id="libellé_je_suis_pro">
			<h1>Je suis gestionnaire de crèche</h1>
			<p id="sous-titre_pro">J'accède à mon compte</p>
		</div>
		
		<footer>
			<p> Bonjour, 
				<br>OuiCrèches est une plateforme dédiée à la réservation de places occasionnelles en crèche.
				<br><br> Notre mission ? 
	 			<br>Rendre la réservation plus simple, plus juste et plus sécurisée pour les parents et les gestionnaires d'établissements.
 			</p>
			<p>
				<a href="/Plateforme_web_B_and_B/Qui_sommes_nous" id="lien_site"> Qui sommes nous ?</a><br>
				<a href="/Plateforme_web_B_and_B" id="lien_site"> Comment ça marche ?</a><br>
				<a href="/Plateforme_web_B_and_B/contacter_ouiCreches" id="lien_site"> Nous contacter</a>.
			</p><br>
		</footer>
	</body>
</html>