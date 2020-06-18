<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Espace gestionnaire</title>
		<link href=".\..\CSS\style_espace_gestionnaire.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="btns_connexion">
			<img alt="" src=".\..\Images\OuiCr%E8chesLogo.png" id="logoBandB">
			
			<div id="libellé_espace_pro">
				<p>Pro
					<c:forEach var="item" items="${Nom}" >
						<c:out value="${item}" />
					</c:forEach>
				</p>
			</div>
		</div>
		
		<section id="sectionHabiliter_parents">
					<div id="parent_référence">
						<p>Le parent de référence *</p>
						<p>Mail*</p><input type="email" id="mail" placeholder="" name="mail_référent"/>
						<select id="Civilité">
							<option disabled="disabled">Civilité *</option>
							<option>Mr.</option>
							<option>Mme</option>
							<option>Mlle</option>
						</select>
						<p>Nom*</p><input type="text" id="Nom" placeholder="" name="Nom_référent"/>
						<p>Téléphone *</p><input type="text" id="Téléphone" placeholder="" name="Téléphone_référent"/>
					</div>
					<div id="conjoint">
						<p>Son conjoint</p>
						<p>Mail</p><input type="email" id="mail" placeholder="" name="mail_conjoint"/>
						<select id="Civilité">
							<option disabled="disabled">Civilité *</option>
							<option>Mr.</option>
							<option>Mme</option>
							<option>Mlle</option>
						</select>
						<p>Nom *</p><input type="text" id="Nom" placeholder="" name="Nom_conjoint"/>
						<p>Téléphone *</p><input type="text" id="Téléphone" placeholder="" name="Téléphone_conjoint"/>
					</div>
					
					<div id="enfant1">
						<p>Enfant 1*</p>
						
						<p>Date de naissance *</p><input type="datetime-local" id="date_naiss" name="date_naiss1" min="2017-04-01" required>
						<p>Nom *</p><input type="text" id="Nom" placeholder="" name="nom_enfant1"/>
						<p>Prénom 1 *</p><input type="text" id="Nom" placeholder="" name="prénom_enfant1"/>
						<p>Prénom 2</p><input type="text" id="Nom" placeholder="" name="prénom2_enfant1"/>
					</div>
					<div id="enfant2">
						<p>Enfant 2</p>
						<p>Date de naissance *</p><input type="datetime-local" id="date_naiss" name="date_naiss2" min="2017-04-01" required>
						<p>Nom *</p><input type="text" id="Nom" placeholder="" name="nom_enfant2"/>
						<p>Prénom 1 *</p><input type="text" id="Nom" placeholder="" name="prénom_enfant2"/>
						<p>Prénom 2</p><input type="text" id="Nom" placeholder="" name="prénom2_enfant2"/>
					</div>
					<div id="enfant3">
						<p>Enfant 3</p>
						<p>Date de naissance *</p><input type="datetime-local" id="date_naiss" name="date_naiss3" min="2017-04-01" required>
						<p>Nom *</p><input type="text" id="Nom" placeholder="" name="nom_enfant3"/>
						<p>Prénom 1 *</p><input type="text" id="Nom" placeholder="" name="prénom_enfant3"/>
						<p>Prénom 2</p><input type="text" id="Nom" placeholder="" name="prénom2_enfant3"/>
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