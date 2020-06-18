<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<link href=".\CSS\style_espace_gestionnaire.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" >
		function validation_choix_site_action(){
			
			var liste_action, texte_action,liste_site,texte_site,choice;
			
			liste_site=document.getElementById("choix_site");
			texte_site=liste_site.options[liste_site.selectedIndex].text;
			
			liste_action = document.getElementById("choix_action");
			texte_action = liste_action.options[liste_action.selectedIndex].text;
			
			choice = select.selectedIndex;
			
//			if (texte_site) {
//				src="../JS/btn_validation_gestio.js"
//			}
			if (texte_action=="MAJ informations sites/créneaux") {
				document.getElementById("sectionMAJ_site").style.display = "block"; 
				document.getElementById("sectionPlanification_crénaux_dispo").style.display = "none"; 
				document.getElementById("sectionHabiliter_parents").style.display = "none"; 
			}
		}
		</script>
		<title>Espace gestionnaire</title>
	</head>
	
	
	<body>
		<div id="btns_connexion">
			<img alt="" src=".\Images\OuiCr%E8chesLogo.png" id="logoBandB">
			
			<div id="libellé_espace_pro">
				<p>Pro
					<c:forEach var="item" items="${Nom}" >
						<c:out value="${item}" />
					</c:forEach>
				</p>
			</div>
		</div>
		
		<div id="contenu_page">
				<h1>Espace Pro/gestionnaire</h1>
				<!-- <c:forEach items="${ Prenom }" var="item">
					<p>Bienvenue, <c:out value="${ item }" /></p>
				</c:forEach> -->
				<form action="/Plateforme_web_B_and_B/identification_pro/Choix_action" method="post">
					<p id="libellé_enseigne"><c:forEach items="${ NomEnseigne }" var="NomEnseignes"><c:out value="${ NomEnseignes }"/></c:forEach></p>
					<section id="choix_site_action">
										
						<select id="choix_site" name="choix_site">
							<option disabled selected>Choisissez votre site *</option>
							<c:forEach items="${ NomEnseigne_site }" var="NomEnseigne_sites">
								<option><c:out value="${ NomEnseigne_sites }" /></option>
							</c:forEach>
						</select>
						
						<select id="choix_action" name="choix_action">
							<option disabled selected>Votre action *</option>
							<option>MAJ informations sites/créneaux</option>
							<option>Planification créneaux disponibles</option>
							<option>Habiliter un parent</option>
						</select>
						
						<input type="submit" id="validation" value="Validation"/>
					</section>
				</form>
				
		</div>
		
		<footer>
			<p> Bonjour, 
				<br>OuiCrèches est une plateforme dédiée à la réservation de places occasionnelles en crèche.
				<br><br> Notre mission ? 
	 			<br>Rendre la réservation plus simple, plus juste et plus sécurisée pour les parents et les gestionnaires d'établissements.
 			</p>
			<p>
				<a href="/Plateforme_web_B_and_B/Qui_sommes_nous" id="lien_site"> Qui sommes nous ?</a><br>
				<a href="/Plateforme_web_B_and_B/comment_%C3%A7a_marche" id="lien_site"> Comment ça marche ?</a><br>
				<a href="/Plateforme_web_B_and_B/contacter_ouiCreches" id="lien_site"> Nous contacter</a>.
			</p><br>
		</footer>
	</body>
</html>