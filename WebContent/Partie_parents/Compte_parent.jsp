<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mon compte parent</title>
		<link href=".\CSS\style_espace_parents.css" rel="stylesheet" type="text/css" />
	</head>
	
	<body>
		<div id="btns_connexion">
			<img alt="" src=".\Images\OuiCr%E8chesLogo.png" id="logoBandB">
			
			<nav id="libellé_espace_pro">
				<ul>
					<li><a href="#" id="dérouleur_pro">Parent <c:forEach var="item" items="${Nom}" ><c:out value="${item}" /></c:forEach></a>
						<ul class="sous">
							<li><a href="" id="mon_compte">Mon compte</a></li>
							<li><a href="/Plateforme_web_B_and_B/deconnexion" id="se_déconnecter">Se déconnecter</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
		
		<section id="page_générale">
				<section id="partie_moi_sec">
					<a href="/Plateforme_web_B_and_B/" onclick="" id="moi"><img alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_partie_moi"/></a>
				</section>
				<div id="partie_moi">
					<p>Moi</p>
				</div>
				
			
				<section id="partie_conjoint_sec">
					<a href="/Plateforme_web_B_and_B/" onclick="" id="mon_conjoint"><img alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_conjoint" /></a>
				</section>
				<div id="partie_conjoint">
					<p>Mon conjoint</p>
				</div>
				
			
				<section id="partie_enfants_sec">
					<a href="/Plateforme_web_B_and_B/" onclick="" id="mes_enfants"><img alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_enfants_connus" /></a>
				</section>
				<div id="partie_enfants">
					<p>Mes enfants (Connus par OuiCrèches)</p>
				</div>
				
				
				<section id="partie_modifié_mdp_sec">
					<a href="/Plateforme_web_B_and_B/" onclick="" id="modifier_mdp"><img alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_mdp"/></a>
				</section>
				<div id="partie_modifié_mdp">
					<p>Modifier mon mot de passe</p>
				</div>
				
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
				<a href="/Plateforme_web_B_and_B/contacter_ouiCreches" id="lien_site"> Nous contacter</a>.
			</p><br>
		</footer>
	</body>
</html>