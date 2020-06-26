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
					<%-- <a href="${pageContext.request.contextPath}/Plateforme_web_B_and_B/identification_parent/choix_compte_parent?name=pour_partie_moi" onclick="" id="moi"><img name="" alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_partie_moi"/></a> --%>
					<a href="#partie_moi_choisie" onclick="" id="moi"><img name="pour_partie_moi" alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_partie_moi"/></a>
				
				</section>
				<div id="partie_moi">
					<p>Moi</p>
				</div>
				
			
				<section id="partie_conjoint_sec">
					<%-- <a href="${pageContext.request.contextPath}/Plateforme_web_B_and_B/identification_parent/choix_compte_parent?name=pour_conjoint" onclick="" id="mon_conjoint"><img name="" alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_conjoint" /></a> --%>			
 					<a href="#partie_conjoint_choisie" onclick="" id="mon_conjoint"><img name="pour_conjoint" alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_conjoint" /></a>
 					
 				</section>
				<div id="partie_conjoint">
					<p>Mon conjoint</p>
				</div>
				
			
				<section id="partie_enfants_sec">
					<%-- <a href="${pageContext.request.contextPath}/Plateforme_web_B_and_B/identification_parent/choix_compte_parent?name=pour_enfants_connus" onclick="" id="mes_enfants"><img name="" alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_enfants_connus" /></a> --%>
					<a href="#partie_enfants_choisie" onclick="" id="mes_enfants"><img name="pour_enfants_connus" alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_enfants_connus" /></a>
					
				</section>
				<div id="partie_enfants">
					<p>Mes enfants (Connus par OuiCrèches)</p>
				</div>
				
				
				<section id="partie_modifié_mdp_sec">
					<%-- <a href="${pageContext.request.contextPath}/Plateforme_web_B_and_B/identification_parent/choix_compte_parent?name=pour_mdp"  onclick="" id="modifier_mdp" ><img name="" alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_mdp"/></a> --%>
					<a href="#partie_modifié_mdp_choisie"  onclick="" id="modifier_mdp" ><img name="pour_mdp" alt="" src=".\Images\transparent2.png" id="gestionnaire_picture" class="pour_mdp"/></a>
					
				</section>
				<div id="partie_modifié_mdp">
					<p>Modifier mon mot de passe</p>
				</div>
				
			</section>
		
			<section id="Informations_choix">
					<div id="partie_moi_choisie">
						<h2>Moi</h2>
						<p>Mon Mail </p>
						
						<p>Mon téléphone </p>
						
						<p>Civilité </p>
					
						<p>Nom </p>
					
						<p>Prénom</p>
						
						<p>Adresse </p>
						<p>Voie </p>
						
						<p>Code postal </p>
						
						<p>Ville </p>
						
						<input type="submit" name="validation" id="validation">
					</div>
					
					
					<div id="partie_conjoint_choisie">
					
					</div>
					
					<div id="partie_enfants_choisie"> 
					
					</div>
					
					<div id="partie_modifié_mdp_choisie">
					
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