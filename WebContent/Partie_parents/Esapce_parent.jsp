<%@page import="servlets.Identification_parent"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Espace parents</title>
		<meta charset="UTF-8">
		<link href=".\CSS\style_espace_parents.css" rel="stylesheet" type="text/css" />
	</head>
	
	<body>
		<div id="btns_connexion">
			<img alt="" src=".\Images\OuiCr%E8chesLogo.png" id="logoBandB">
			
			<nav id="libellé_espace_pro">
				<ul>
					<li><a href="#" id="dérouleur_pro">Parent <c:forEach var="item" items="${Nom}" ><c:out value="${item}" /></c:forEach></a>
						<ul class="sous">
							<li><a href="/Plateforme_web_B_and_B/identification_parent/mon_compte" id="mon_compte">Mon compte</a></li>
							<li><a href="/Plateforme_web_B_and_B/deconnexion" id="se_déconnecter">Se déconnecter</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
		
		<section id="établissements_habilités">
			<h2>Je suis habilité à réserver dans le/les établissement(s)</h2>
			
			<%-- <c:forEach begin="0" end="${Nom_crèches_taille}" step="1" var="Nom_crèches_taille_var">
				<c:forEach items="${Noms_site_crèche_parent_habilités}${Nom_crèches_taille_var}" var="row">
			 		<!-- <input type="radio" id="" name="" value="" />  -->
					<c:out value="${row}"/>
				</c:forEach>
			 </c:forEach> 
			 --%>
			<c:forEach items="${ Noms_site_crèche_parent_habilités }" var="Nom_site_crèche_parent_habilité">
			 		<!-- <input type="radio" id="" name="" value="" />  -->
					<c:out value="${Nom_site_crèche_parent_habilité}"/>
			</c:forEach>
			
		</section>
		
		<section id="souhait_réservation">
			<h2>Je souahite réserver</h2>
			<p>Créneaux disponibles</p>
			<p>Du </p><input type="date" id="date_départ" name="date_départ"  /><p> Au </p><input type="date" id="date_arrivée" name="date_arrivée"  />
			
			<div id="choix_dans_journée">
				<p>Journée entière</p><input type="radio" id="journée_entière" name="journée_entière" />
				<p>Matin uniquement</p><input type="radio" id="matin_uniquement" name="matin_uniquement" />
				<p>Après midi uniquement</p><input type="radio" id="aprèm_uniquement" name="aprèm_uniquement" />
			</div>
			
			
			<div id="">
				<p>Nombre d'enfants à faire garder (9 max)</p>
				<script type="text/javascript">
					var i=0;
					function ajouter_input_text(){
						var ul=document.getElementById("dynamique_list_input");
						 var input = document.createElement("input");
						input.setAttribute('name','nb_enfants_garder'+i);
						input.setAttribute('type','date');
						/* input.setAttribute('placeholder','enfant '+(i+1)+' jj/mm/aaaa'); */
						input.setAttribute('id','menu'+i);
						input.innerHTML = "Enfant "+i;
					
						
						ul.appendChild(input);
						i++;
						if (i==10) {
							enlever_input_text();
						}
					}
					
					function enlever_input_text(){
						var ul = document.getElementById("dynamique_list_input");
						ul.removeChild(ul.lastChild);
					    i--;
					}
					
				</script>
				<input type="button" id="plus" value="+" onclick='ajouter_input_text()' /> 
				<input type="button" id="moins" value="-" onclick='enlever_input_text()'/>
				<p>Date de naissance</p>
				
				<ul id="dynamique_list_input">
					
				</ul>
				
				</div>
			
			<input type="submit" id="rechercher" name="rechercher" value="Rechercher"/>
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