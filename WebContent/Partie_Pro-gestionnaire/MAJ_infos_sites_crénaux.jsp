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
		<h1>Site : <c:forEach items="${ Nom_site }" var="nomsite"><c:out value="${ nomsite }"></c:out></c:forEach></h1>
		<section id="sectionMAJ_site">
					<sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
  					url="jdbc:mysql://localhost:3306/bdd_ouicreches" user="root" password=""/>
					<section id="infos+horaires">
						<div id="information_siteCrèche">
						
							<table>
								<tr>
									<td>
										Adresse*
									</td>
									
									<td>
										N°<input type="text" id="numéro" placeholder="" name="numéro"  value=""/>
											<c:forEach items="${ Num }" var="nums">
													<p><c:out value="${ nums }" /></p>
											</c:forEach>
											
									</td>
									<td>
										Voie<input type="text" id="voie" placeholder="" name="voie" value=""/>
									</td>
									<td>
										Complément
										<input type="text" id="complément" placeholder="" name="complément" value=""/>
									</td>
									<td>
										CP
										<input type="number" id="cp" placeholder="" name="CP" value=""/>
									</td>
									<td>
										Ville
										<input type="text" id="ville" placeholder="" name="ville" value=""/>										
									</td>
								</tr>
								<tr>
									<td id="titre_ligne">
										Contact*
									</td>
									<td>
										Tél
										<input type="tel" id="téléphone" placeholder="" name="telephone" value=""/> 
									</td>
									<td>
										Mail contact
										<input type="email" id="mail" placeholder="" name="mail" value=""/>
									</td>
									<td>
										Nom contact
										<input type="text" id="nom_contact" placeholder="" name="nom_contact" value=""/>
									</td>
								</tr>
								
								<tr>
									<td id="titre_ligne">
										Type*
									</td>
									<td>
										<select id="choix_type_crèche">
											<option>Micro crèche</option>
											<option>Crèche</option>
											<option>Multi accueil</option>
										</select>
									</td>
									<td>
										Capacité*
										<input type="number" id="nb_places" placeholder="" name="nb_places" value=""/> 
										places
									</td>
								</tr>
							</table>
						</div>
						
						<div id="horaires_ouvertures">
							<table id="horaires">
								<caption>Horaires d'ouverture</caption>
								
								<tr>
									<td>
									Lundi									
									</td>
									<td>
										<c:forEach items="${ Horaires_Lundi }" var="Horaires_Lundis">
											<option><c:out value="${ Horaires_Lundis }" /></option>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<td>
									Mardi									
									</td>
									<td>
										<c:forEach items="${ Horaires_Mardi }" var="Horaires_Mardis">
											<option><c:out value="${ Horaires_Mardis }" /></option>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<td>
									Mercredi									
									</td>
									<td>
										<c:forEach items="${ Horaires_Mercredi }" var="Horaires_Mercredis">
											<option><c:out value="${ Horaires_Mercredis }" /></option>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<td>
									Jeudi									
									</td>
									<td>
										<c:forEach items="${ Horaires_Jeudi }" var="Horaires_Jeudis">
											<option><c:out value="${ Horaires_Jeudis }" /></option>
										</c:forEach>
									</td>
									
								</tr>
								<tr>
									<td>
									Vendredi									
									</td>
									<td>
										<c:forEach items="${ Horaires_Vendredi }" var="Horaires_Vendredis">
											<option><c:out value="${ Horaires_Vendredis }" /></option>
										</c:forEach>
									</td>
									
								</tr>
								<tr>
									<td>
									Samedi									
									</td>
									<td>
										<c:forEach items="${ Horaires_Samedi }" var="Horaires_Samedis">
											<option><c:out value="${ Horaires_Samedis }" /></option>
										</c:forEach>
									</td>
									
								</tr>
								<tr>
									<td>
									Dimanche									
									</td>
									<td>
										<c:forEach items="${ Horaires_Dimanche }" var="Horaires_Dimanches">
											<option><c:out value="${ Horaires_Dimanches }" /></option>
										</c:forEach>
									</td>
								</tr>
							</table>
						</div>
					</section>
					
					<section id="paramétrage_créneaux_occasionnels">
						<div id="journée_entière">
							<p>Journée entière</p>
							<p>Début</p><input id="" value="" type="text" name=""/>
							<p>Fin</p><input id="" value="" type="text" name=""/>
						</div>
						<div id="Matin">
							<p>Matin</p>
							<p>Début</p><input id="" value="" type="text" name=""/>
							<p>Fin</p><input id="" value="" type="text" name=""/>
						</div>
						<div id="Après_midi">
							<p>Après midi</p>
							<p>Début</p><input id="" value="" type="text" name=""/>
							<p>Fin</p><input id="" value="" type="text" name=""/>
						</div>
						<div id="Fin_de_journée">
							<p>Fin de journée</p>
							<p>Début</p><input id="" value="" type="text" name=""/>
							<p>Fin</p><input id="" value="" type="text" name=""/>
						</div>
					</section>
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