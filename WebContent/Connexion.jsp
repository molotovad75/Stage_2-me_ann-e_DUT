<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Connexion</title>
		<link href=".\CSS\style_connexion.css" rel="stylesheet" type="text/css" />	
		<script type="text/javascript" src="JS/script.js"></script>
	</head>
	
	<body>
		
		<div id="btns_connexion">
			<label for="menu_options_co" class="Choix_Espaces">Espaces</label>
			<input type="checkbox" id="menu_options_co" role="button" />
			<img alt="" src=".\Images\OuiCr%E8chesLogo.png" id="logoBandB">
			<div id="menu_choix">
				<input type="button" id="Connexion_parents" value="Espace Parents" onclick='afficher_cacher_div()'/>
				<input type="button" id="Connexion_pro" value="Espace Pro" onclick='afficher_cacher_div2()' /> 
			</div>
		</div>
		
		<div id="contenu_page">
			<div id="Corps">
				<h2>Besoin d'une garde d'enfant occasionnelle ?  Réservez rapidement au sein de votre crèche partenaire</h2>
				<section id="form_connexion">
				
					<div id="Identification_parents">
						<form action="/Plateforme_web_B_and_B/identification_parent" method="post">
							<p id="ident">J'ai un compte parent</p>
							<p id="ident">Je m'identifie</p>
							<p>
								<input type="text" id="Id_parent" placeholder="Nom d'utilisateur" name="Id_parent"/><br><br>
								<input type="password" id="mdp_parent" placeholder="Mot de passe utilisateur" name="mdp_parent"/><br><br>
								<input type="submit" id="Se_connecter_parent" value="Se connecter"/>
								<a href="" id="mdp_oubli">Mot de passe oublié</a>
								
							</p>
							<!-- JSTL -->
							<c:forEach var="message_erreur_auth_parents" items="${message_erreur_auth_parent}" >
								<c:out value="${message_erreur_auth_parents}" />
							</c:forEach>
							
							<c:forEach items="${confirmation_changement_mdp}" var="confirmation">
								<c:out value="${confirmation}"/>
							</c:forEach>
						
							<c:forEach items="${message_erreur_ancien_mdp}" var="erreur">
								<c:out value="${erreur}"/>
							</c:forEach>
			
						</form>
					</div>	
				
					<div id="Creation_parents">
						<form action="/Plateforme_web_B_and_B/demande_inscription_parent" method="post">
							<p id="ident">Je n'ai pas de compte parents</p>
							<p id="ident">Je souhaite en savoir plus sur la solution OuiCrèches</p>
							<p>
								<input type="text" id="Nom" placeholder="Nom *" name="Nom"/><br><br>
								<input type="text" id="Prenom" placeholder="Prénom *"name="Prénom"/><br><br>
								<input type="email" id="email_parent" placeholder="email *"name="email_parent"/><br><br> <!-- Id_parent -->
								<input type="tel" id="tel_parent" placeholder="téléphone parents *"name="tel_parent"/><br><br> <!-- tel_parent -->
								<textarea rows="8" cols="45" maxlength="65525" name="message" id="message" placeholder="Bonjour, je souahite en savoir plus sur la solution OuiCrèches, merci de me recontacter."></textarea>
								<input type="submit" id="envoyer_parent" value="Envoyer au gestionnaire OuiCrèche"  name="Se_connecter"/>
							</p>
							<!-- JSTL -->
							<c:forEach var="item" items="${mess_erreur_nb_caractère}" >
									<c:out value="${item}" />
							</c:forEach>
						</form>
					</div>
					
					<div id="Identification_pro">
						<form action="/Plateforme_web_B_and_B/identification_pro" method="post">
							<p id="ident">J'ai un compte pro/gestionnaire</p>
							<p id="ident">Je m'identifie</p>
							<p>
								<input type="text" id="Id_pro" placeholder="Nom pro" name="Id_pro"/><br><br>
								<input type="password" id="mdp_pro" placeholder="Mot de passe pro" name="mdp_pro"/><br><br>
								<input type="submit" id="Se_connecter_pro" value="Se connecter" name="Se_connecter_pro"/>
								<a href="" id="mdp_oubli">Mot de passe oublié</a>
							</p>
							<!-- JSTL -->
							<c:forEach var="item" items="${auth}" >
									<c:out value="${item}" />
							</c:forEach>
							
						</form>
					</div>
					
					<div id="Creation_pro">
						<form action="/Plateforme_web_B_and_B/demande_inscription_pro" method="post">
							<p id="ident">Je n'ai pas de compte pro/gestionnaire</p>
							<p id="ident">Je souahite en savoir plus sur la solution OuiCrèches Pro</p>
							<p>
								<input type="text" id="Nom" placeholder="Nom *" name="Nom"/><br><br>
								<input type="text" id="Prenom" placeholder="Prénom" name="Prénom"/><br><br>
								<input type="email" id="email_pro" placeholder="email *" name="email_pro"/><br><br> 
								<input type="tel" id="tel_pro" placeholder="Téléphone pro *" name="tel_pro"/><br><br>
								<textarea  rows="8" cols="45" maxlength="65525" name="message" id="message" placeholder="Bonjour, je souahite en savoir plus sur la solution OuiCrèches, merci de me recontacter."></textarea>
								<input type="submit" id="envoyer_pro" value="Envoyer au gestionnaire OuiCrèche" name="Se_connecter"/>
							</p>
							<!-- JSTL -->
							<c:forEach var="item" items="${err_mail}" >
									<c:out value="${item}" />
							</c:forEach>
						</form>
					</div>
					
				</section>
				
				
			</div>
		</div>	
		<footer>
			<p> Bonjour, 
				<br>OuiCrèches est une plateforme dédiée à la réservation de places occasionnelles en crèche.
				<br><br> Notre mission ? 
	 			<br>Rendre la réservation plus simple, plus juste et plus sécurisée pour les parents et les gestionnaires d'établissements.
 			</p>
			<p>
				<a href="/Plateforme_web_B_and_B/Qui_sommes_nous" id="lien_site"> Qui sommes nous ?</a><br>
				<a href="" id="lien_site"> Comment ça marche ?</a><br>
				<a href="/Plateforme_web_B_and_B/contacter_ouiCreches" id="lien_site"> Nous contacter</a>.
			</p><br>
		</footer>
			
	</body>
</html>