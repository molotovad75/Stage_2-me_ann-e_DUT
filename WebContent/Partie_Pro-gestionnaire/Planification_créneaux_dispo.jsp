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
	       						<td>#</td>
	       						
	       						<td>--</td>
	       						<td>Lundi<br></td>
	       						<td>--</td>
	       						
	       						<td>--</td>
	       						<td>Mardi<br></td>
	       						<td>--</td>
	       						
	       						<td>--</td>
	       						<td>Mercredi<br></td>
	       						<td>--</td>
	       						
	       						<td>--</td>
	       						<td>Jeudi<br></td>
	       						<td>--</td>
	       						
	       						<td>--</td>
	       						<td>Vendredi<br></td>
	       						<td>--</td>
	       						
	       						<td>--</td>
	       						<td>Samedi<br></td>
	       						<td>--</td>
	   						</tr>
   						</thead>
   						
   						<tbody>
	   						<tr>
	       						<td>8h00</td>
	       							<td rowspan="1">
	       								<!-- Début bâton journée entière : Lundi -->
	       							</td> 
	       							<td rowspan="1">
	       								<!-- Début bâton Matin : Lundi -->
	       							</td>
	       							<td rowspan="1">
	       								<!-- Début bâton Après-midi : Lundi -->
	       							</td>
	       							
	   						</tr>
	   						<tr>
	       						<td>13h00</td>
		       						<td rowspan="1">
		       							26 ans
		       							<!-- Lundi -->
		       						</td>
	       							<td rowspan="1"> 
	       								Viva 
	       								<!-- Mardi je commence à 13h -->
	       							</td>	
	   						</tr>
	   						<tr>
	       						<td>17h00</td> <!-- rowspan doit être =2 ou moins -->
	       							<td rowspan="1">
	       								26 ans
	       								<!-- Lundi -->
	       							</td>
	   						</tr>
	   						<tr>
	       						<td>18h00</td>
	       						<td rowspan="1">26 ans</td><!-- Lundi -->
	       						
	   						</tr>
   						</tbody>
					</table>
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