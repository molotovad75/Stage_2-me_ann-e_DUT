<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href=".\CSS\style_contact.css" rel="stylesheet" type="text/css" />
		<title>Contact - OuiCrèches</title>
	</head>
	
	<body>
		<div id="btns_connexion">
			<img alt="" src=".\Images\OuiCr%E8chesLogo.png" id="logoBandB">
		</div>
		<h2>Besoin de nous contacter, envoyer un message ci-dessous !</h2>
		
		<section id="Corps">
			<input type="text" id="nom" name="nom" placeholder="Nom"/>
			<input type="text" id="prénom" name="prénom" placeholder="Prénom"/><br><br>
			<input type="email" id="email" name="email" placeholder="Email *"/>
			<input type="text" id="téléphone" name="téléphone" placeholder="Téléphone "/><br><br>
			<textarea rows="8" cols="45" maxlength="65525" placeholder="Votre message *" id="message"></textarea><br><br>
			<input type="submit" id="btn_envoyer_message" name="btn_envoyer_message" title="Envoyer votre message" placeholder="Envoyer votre message"/>
		</section>
		
		<footer>
			<p> Bonjour, 
				<br>OuiCrèches est une plateforme dédiée à la réservation de places occasionnelles en crèche.
				<br><br> Notre mission ? 
	 			<br>Rendre la réservation plus simple, plus juste et plus sécurisée pour les parents et les gestionnaires d'établissements.
 			</p>
			<p>
				<a href="/Plateforme_web_B_and_B/Qui_sommes_nous" id="lien_site"> Qui sommes nous ?</a><br>
				<a href="/Plateforme_web_B_and_B/comment_%C3%A7a_marche" id="lien_site"> Comment ça marche ?</a><br>
				<a href="#" id="lien_site"> Nous contacter</a>.
			</p><br>
		</footer>
	</body>
</html>