<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href=".\CSS\style_espace_gestionnaire.css" rel="stylesheet" type="text/css" />
		<title>Espace gestionnaire</title>
	</head>
	
	
	<body>
		<div id="btns_connexion">
			<img alt="" src=".\Images\OuiCr%E8chesLogo.png" id="logoBandB">
			
			<div id="libellé_espace_pro">
				<p>Espace gestionnaire pro</p>
			</div>
		</div>
		
		<div id="contenu_page">
			
				<h1>Espace Pro/gestionnaire</h1>
				<p id="phrase_accueil">Bienvenue, </p>
				
				<section id="choix_site_action">
					<p id="libellé_enseigne"></p>
					<select id="choix_site">
						
					</select>
					
					<select id="choix_action" >
						<option disabled selected>Votre action *</option>
						<option>MAJ informations sites/créneaux</option>
						<option>Planification créneaux disponibles</option>
						<option>Habiliter un parent</option>
					</select>
					
					<input type="submit" id="validation" value="Validation"/>
				</section>
		</div>
		
		<footer>
			<p> Bonjour, 
				<br>OuiCrèches est une plateforme dédiée à la réservation de places occasionnelles en crèche.
				<br><br> Notre mission ? 
	 			<br><br>Rendre la réservation plus simple, plus juste et plus sécurisée pour les parents et les gestionnaires établissements.
 			</p>
			<p>A Propos    :<br><br>
				<a href="https://www.bottinesetbottillons.fr" id="lien_site"> Qui sommes nous ?</a><br>
				<a href="https://www.bottinesetbottillons.fr" id="lien_site"> Comment ça marche ?</a><br>
				<a href="https://www.bottinesetbottillons.fr" id="lien_site"> Nous contacter</a>.
			</p><br>
		</footer>
	</body>
</html>