<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<p id="libellé_enseigne"><c:forEach items="${ NomEnseigne }" var="NomEnseignes"><c:out value="${ NomEnseignes }"/></c:forEach></p>
				<section id="choix_site_action">					
					<select id="choix_site">
						<option disabled selected>Choisissez votre site *</option>
						<c:forEach items="${ NomEnseigne_site }" var="NomEnseigne_sites">
							<option><c:out value="${ NomEnseigne_sites }" /></option>
						</c:forEach>
					</select>
					
					<select id="choix_action" >
						<option disabled selected>Votre action *</option>
						<option>MAJ informations sites/créneaux</option>
						<option>Planification créneaux disponibles</option>
						<option>Habiliter un parent</option>
					</select>
					
					<input type="button" id="validation" value="Validation"/>
				</section>
				
				
				<section id="sectionMAJ_site">
					<section id="">
						<div id="information_siteCrèche">
						</div>
						<div id="horaires_ouvertures">
							<table>
								<caption>Horaires d'ouverture</caption>
								<tr>
									<td>Lundi</td>
									
								</tr>
								<tr>
									<td>Mardi</td>
								</tr>
								<tr>
									<td>Mercredi</td>
								</tr>
								<tr>
									<td>Jeudi</td>
								</tr>
								<tr>
									<td>Vendredi</td>
								</tr>
								<tr>
									<td>Samedi</td>
								</tr>
								<tr>
									<td>Dimanche</td>
								</tr>
							</table>
						</div>
					</section>
					
					<section id="">
					
					</section>
				</section>
				
				<section id="sectionPlanification_crénaux_dispo">
					<select id="choix_du_mois" >
						<option disabled selected>Mois ?*</option>
						<option>Janvier</option>
						<option>Février</option>
						<option>Mars</option>
						<option>Avril</option>
						<option>Mai</option>
						<option>Juin</option>
						<option>Juillet</option>
						<option>Août</option>
						<option>Septembre</option>
						<option>Octobre</option>
						<option>Novembre</option>
						<option>Décembre</option>
					</select>
					<select id="choix_de_l_année">
						<option disabled selected>Année ?*</option>
						<c:forEach begin="1900" end="2020" step="1" var="année">
							<option><c:out value="${ année }"/></option>
						</c:forEach>
					</select>
					<input type="button" id="validationPlanif" value="Validation"/>
					<input type="submit" id="confirmation" value="confirmation"/>
					
					<!-- CARTE SVG DOIT SUIVANTE -->
					<table id="grille_saisie">
						<caption>Grille de saisie des créneaux disponibles à proposer par le gestionnaire</caption>
						<thead>
	   						<tr> <!-- AJOUTER LA LES DATES EN DESSOUS AVEC LA JSTL -->
	       						<td><a></a></td>
	       						<td>Lundi<br></td>
	       						<td>Mardi<br></td>
	       						<td>Mercredi<br></td>
	       						<td>Jeudi<br></td>
	       						<td>Vendredi<br></td>
	       						<td>Samedi<br></td>
	   						</tr>
   						</thead>
   						
   						<tbody>
	   						<tr>
	       						<td>8h00</td><!-- rowspan doit être =4 ou moins -->
	       						<td rowspan="4">26 ans</td>
	       						<td rowspan="4">26 ans</td>
	       						<td rowspan="4">26 ans</td>
	   						</tr>
	   						<tr>
	       						<td>13h00</td><!-- rowspan doit être =3 ou moins -->
	       						<td rowspan="3">26 ans</td>
	   						</tr>
	   						<tr>
	       						<td>17h00</td> <!-- rowspan doit être =2 ou moins -->
	       						<td rowspan="2">26 ans</td>
	   						</tr>
	   						<tr>
	       						<td>18h00</td>
	       						
	       						
	   						</tr>
   						</tbody>
					</table>
				</section>
				
				<section id="sectionHabiliter_parents">
					
				</section>
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