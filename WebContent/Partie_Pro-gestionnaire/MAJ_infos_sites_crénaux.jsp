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
		<sql:setDataSource var = "bdd_co" driver = "com.mysql.cj.jdbc.Driver"
         	url = "jdbc:mysql://localhost:3306/bdd_ouicreches?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"
         	user = "root"  password = ""/>
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
			<h1>Mise à jour des informations lié au site suivant : <c:forEach items="${ Nom_site }" var="nomsite"><c:out value="${ nomsite }"></c:out></c:forEach></h1>
			<section id="sectionMAJ_site">
			
							<form action="/Plateforme_web_B_and_B/identification_pro/maj_infos_infos_remplies" method="post">
								<section id="infos+horaires">
								<div id="information_siteCrèche">
								
									<table>
										<tr>
											<td>
												Adresse*
											</td>
											
											<td>
												<sql:query dataSource="${bdd_co}" var="result_num_voie">
													SELECT sc.Numéro FROM `site_creche` AS sc WHERE sc.`Nom Site`='${ Nom_site }'
												</sql:query>
												
												<c:forEach items="${ result_num_voie.rows }" var="row">
													N°<input type="text" id="numéro" placeholder="" name="numéro"  value="${ row.Numéro }"/>
												</c:forEach>
													
											</td>
											<td>
												<sql:query dataSource="${bdd_co}" var="result_voie">
													SELECT sc.Voie FROM `site_creche` AS sc WHERE sc.`Nom Site`='${ Nom_site }'
												</sql:query>
												<c:forEach items="${ result_voie.rows }" var="row">
													Voie<input type="text" id="voie" placeholder="" name="voie" value="${row.Voie}"/>
												</c:forEach>
											</td>
											<td>
												<sql:query dataSource="${bdd_co}" var="result_complément">
													SELECT sc.Complément  FROM `site_creche` AS sc WHERE sc.`Nom Site`='${ Nom_site }'
												</sql:query>
												<c:forEach items="${ result_complément.rows }" var="row">
													Complément <input type="text" id="complément" placeholder="" name="complément" value="${row.Complément}"/>
												</c:forEach>
											</td>
											<td>
												<sql:query dataSource="${bdd_co}" var="result_cp">
													SELECT sc.CP  FROM `site_creche` AS sc WHERE sc.`Nom Site`='${ Nom_site }'
												</sql:query>
												
												<c:forEach items="${ result_cp.rows }" var="row">
													CP <input type="number" id="cp" placeholder="" name="CP" value="${row.CP }"/>
												</c:forEach>
											</td>
											<td>
												<sql:query dataSource="${bdd_co}" var="result_ville">
													SELECT sc.Ville  FROM `site_creche` AS sc WHERE sc.`Nom Site`='${ Nom_site }'
												</sql:query>
												<c:forEach items="${ result_ville.rows }" var="row">
													Ville <input type="text" id="ville" placeholder="" name="ville" value="${row.Ville }"/>
												</c:forEach>										
											</td>
										</tr>
										<tr>
											<td id="titre_ligne">
												Contact*
											</td>
											<td>
												<sql:query dataSource="${bdd_co}" var="result_Tel_contact">
													SELECT sc.Tel_contact  FROM `site_creche` AS sc WHERE sc.`Nom Site`='${ Nom_site }'
												</sql:query>
												
												<c:forEach items="${ result_Tel_contact.rows }" var="row">
													Tél <input type="tel" id="téléphone" placeholder="" name="telephone" value="${row.Tel_contact}"/> 
												</c:forEach>
											</td>
											<td>
												<sql:query dataSource="${bdd_co}" var="result_Mail_contact">
													SELECT sc.Mail_contact  FROM `site_creche` AS sc WHERE sc.`Nom Site`='${ Nom_site }'
												</sql:query>
												<c:forEach items="${ result_Mail_contact.rows }" var="row">
													Mail contact <input type="email" id="mail" placeholder="" name="mail" value="${row.Mail_contact }"/>
												</c:forEach>
											</td>
											<td>
												<sql:query dataSource="${bdd_co}" var="result_Nom_contact">
													SELECT sc.Nom_contact  FROM `site_creche` AS sc WHERE sc.`Nom Site`='${ Nom_site }'
												</sql:query>
												
												<c:forEach items="${ result_Nom_contact.rows }" var="row">
													Nom contact <input type="text" id="nom_contact" placeholder="" name="nom_contact" value="${row.Nom_contact }"/>
												</c:forEach>				
											</td>
										</tr>
										
										<tr>
											<td id="titre_ligne">
												Type*
											</td>
											<td>
												<sql:query dataSource="${bdd_co}" var="result_Type">
													SELECT sc.Type FROM `site_creche` AS sc WHERE sc.`Nom Site`='${ Nom_site }'
												</sql:query>
												
												<c:forEach items="${ result_Type.rows }" var="row">
												<select id="choix_type_crèche" name="type" >
													<option value="${row.Type}"></option>
													<option>Micro crèche</option>
													<option>Crèche</option>
													<option>Multi accueil</option>
												</select>
												</c:forEach>
											</td>
											<td>
												<sql:query dataSource="${bdd_co}" var="result_Capacité">
													SELECT sc.Capacité  FROM `site_creche` AS sc WHERE sc.`Nom Site`='${ Nom_site }'
												</sql:query>
												<c:forEach items="${ result_Capacité.rows }" var="row">
												Capacité* <input type="number" id="nb_places" placeholder="" name="nb_places" value="${row.Capacité }"/> places
												</c:forEach>
												
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
								<select id="jour_semaine">
									<option disabled="disabled">Choisissez un jour de la semaine</option>
									<option>Lundi</option>
									<option>Mardi</option>
									<option>Mercredi</option>
									<option>Jeudi</option>
									<option>Vendredi</option>
									<option>Samedi</option>
									<option>Dimanche</option>
								</select>
								<div id="journée_entière">
									<p>Journée entière</p>
									<p>Début</p><input id="je_debut" value="" type="text" name="je_debut"/>
									<p>Fin</p><input id="je_fin" value="" type="text" name="je_fin"/>
								</div>
								<div id="Matin">
									<p>Matin</p>
									<p>Début</p><input id="matin_debut" value="" type="text" name="matin_debut"/>
									<p>Fin</p><input id="matin_fin" value="" type="text" name="matin_fin"/>
								</div>
								<div id="Après_midi">
									<p>Après midi</p>
									<p>Début</p><input id="ap_debut" value="" type="text" name="ap_debut"/>
									<p>Fin</p><input id="ap_fin" value="" type="text" name="ap_fin"/>
								</div>
								<div id="Fin_de_journée">
									<p>Fin de journée</p>
									<p>Début</p><input id="fj_debut" value="" type="text" name="fj_debut"/>
									<p>Fin</p><input id="fj_fin" value="" type="text" name="fj_fin"/>
								</div>
								
							</section>
						
							<input type="submit" value="Mettre à jour les informations saisies" id="maj_infos" name="maj_infos_page"/> 
						</form>
						
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