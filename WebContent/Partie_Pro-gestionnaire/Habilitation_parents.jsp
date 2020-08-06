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
			
			<nav id="libellé_espace_pro">
				<ul>
					<li><a href="#" id="dérouleur_pro">Pro <c:forEach var="item" items="${Nom}" ><c:out value="${item}" /></c:forEach></a>
						<ul class="sous">
							<li><a href="" id="mon_compte">Mon compte</a></li>
							<li><a href="/Plateforme_web_B_and_B/deconnexion" id="se_déconnecter">Se déconnecter</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
		
		<div id="corps">
			<h1>Habiliter un parent pour le site suivant :<c:forEach items="${ Nom_site }" var="nomsite"><c:out value="${ nomsite }"></c:out></c:forEach></h1>
			<section id="sectionHabiliter_parents">
					<form action="/Plateforme_web_B_and_B/identification_pro/habiliter_parents" method="post">
						<div id="parent_référence">
							<p>Le parent de référence *</p>
							<p>Mail*</p><input type="email" id="mail" placeholder="" name="mail_référent"/>
							<select id="Civilité" name="Civilité_parent_ref">
								<option disabled="disabled">Civilité *</option>
								<option>Mr.</option>
								<option>Mme</option>
								<option>Mlle</option>
							</select>
							<p>Nom*</p><input type="text" id="Nom" placeholder="" name="Nom_référent"/>
							<p>Prénom*</p><input type="text" id="Prénom" placeholder="" name="Prénom_référent"/>
							<p>Téléphone *</p><input type="text" id="Téléphone" placeholder="" name="Téléphone_référent"/>
							<c:forEach var="message_erreur_parent_var" items="${ message_erreur_parent }"><c:out value="${ message_erreur_parent_var }"></c:out></c:forEach>
						</div>
						
						<div id="conjoint">
							<p>Son conjoint</p>
							<p>Mail</p><input type="email" id="mail" placeholder="" name="mail_conjoint"/>
							<select id="Civilité" name="Civilité_conjoint">
								<option disabled="disabled">Civilité *</option>
								<option>Mr.</option>
								<option>Mme</option>
								<option>Mlle</option>
							</select>
							<p>Nom *</p><input type="text" id="Nom" placeholder="" name="Nom_conjoint"/>
							<p>Téléphone *</p><input type="text" id="Téléphone" placeholder="" name="Téléphone_conjoint"/>
							<c:forEach var="message_erreur_conjoint_var" items="${ message_erreur_conjoint }"><c:out value="${ message_erreur_conjoint_var }"></c:out></c:forEach>
						</div>
						
						<div id="enfant1">
							<p>Enfant 1*</p>
							
							<p>Date de naissance *</p><input type="text" id="date_naiss" name="date_naiss1" min="2017-04-01" required>
							<p>Nom *</p><input type="text" id="Nom" placeholder="" name="nom_enfant1"/>
							<p>Prénom 1 *</p><input type="text" id="Nom" placeholder="" name="prénom_enfant1"/>
							<p>Prénom 2</p><input type="text" id="Nom" placeholder="" name="prénom2_enfant1"/>
							<c:forEach var="message_erreur_enfant1_var" items="${ message_erreur_enfant1 }"><c:out value="${ message_erreur_enfant1_var }"></c:out></c:forEach>
						</div>
						<div id="enfant2">
							<p>Enfant 2</p>
							<p>Date de naissance *</p><input type="text" id="date_naiss" name="date_naiss2" >
							<p>Nom *</p><input type="text" id="Nom" placeholder="" name="nom_enfant2"/>
							<p>Prénom 1 *</p><input type="text" id="Nom" placeholder="" name="prénom_enfant2"/>
							<p>Prénom 2</p><input type="text" id="Nom" placeholder="" name="prénom2_enfant2"/>
						</div>
						<div id="enfant3">
							<p>Enfant 3</p>
							<p>Date de naissance *</p><input type="text" id="date_naiss" name="date_naiss3" >
							<p>Nom *</p><input type="text" id="Nom" placeholder="" name="nom_enfant3"/>
							<p>Prénom 1 *</p><input type="text" id="Nom" placeholder="" name="prénom_enfant3"/>
							<p>Prénom 2</p><input type="text" id="Nom" placeholder="" name="prénom2_enfant3"/>
						</div>
					
						<input type="submit" id="habiliter_parent" value="Confirmation" name="habiliter_parent"/>
					</form>
		</section>
		
		<section id="voir_candidatures_parents">
			<form action="/Plateforme_web_B_and_B/identification_pro/messages_parents" method="post">
				<select id="choix_parent" name="choix_parent" >
					<option disabled="disabled">Demandes d'habilitations parents</option>
					<option disabled="disabled">Nom parent + prénom parent (dans l'ordre)</option>
					<c:forEach items="${ nom_prenom }" var="noms_prenoms">
						<option><c:out value="${ noms_prenoms }"></c:out> </option>
					</c:forEach>
				</select>
				<input type="submit" id="regarder_message" value="Afficher message des parents sélectionnés" name="regarder_message" onclick=""/>
			</form>
			
			
			
			<div id="bloc_message">
				<p>
					Message de <c:forEach items="${ Nom_prénom_parent_selectionné }" var="nom_prénom_parent_selectionné">
					<c:out value="${ nom_prénom_parent_selectionné }"></c:out> 
					</c:forEach>:<br><br> 
					<c:forEach items="${ Message }" var="message">
						<c:out value="${ message }"></c:out>
					</c:forEach>
				</p>
			</div>
		</section>
		</div>
		
		
		<div id="message_erreur">
			<p><c:forEach items="${ message_erreur }" var="Message_erreur"><c:out value="${ Message_erreur }"></c:out></c:forEach></p>
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