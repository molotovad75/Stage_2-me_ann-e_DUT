<%@page import="servlets.Identification_parent"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mon compte parent</title>
		<link href=".\CSS\style_espace_parents.css" rel="stylesheet" type="text/css" />
	</head>
	
	<body>
			<sql:setDataSource var = "bdd_co" driver = "com.mysql.cj.jdbc.Driver"
         	url = "jdbc:mysql://localhost:3306/bdd_ouicreches?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"
         	user = "root"  password = ""/>
         	
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
					<form id="partie_moi_choisie" method="post" action="">
						<h2>Moi</h2>
						<p>Mon Mail </p>
						
						
						<c:forEach items="${ result_mail.rows }" var="row">
							<c:out value="${row.Email}"/>
						</c:forEach>
						
						<p>Mon téléphone </p>
						
						
						<c:forEach  items="${ result_téléphone.rows }" var="row">
							<input type="text" name="num_téléphone" id="num_téléphone" value="${ row.Téléphone }"/>
						</c:forEach>
					
						
						<p>Civilité </p>
						
						<c:forEach  items="${ result_Civilité.rows }" var="row">
							<input type="text" name="civilité" id="civilité" value="${ row.Civilité }"/>
						</c:forEach>
						
						<p>Nom </p>
						
						<c:forEach  items="${ result_nom.rows }" var="row">
							<input type="text" name="nom_parent" id="nom_parent"  value="${ row.NomParent_référence }"/>
						</c:forEach>
						<p>Prénom</p>
						
						<c:forEach  items="${ result_prénom.rows }" var="row">
							<input type="text" name="prénom_parent" id="prénom_parent" value="${ row.PrénomParent_référence }"/>
						</c:forEach>
						
						
						<p>Adresse </p>
						<p>Voie </p>
						
						<c:forEach  items="${ result_voie.rows }" var="row">
							<input type="text" name="voie_parent" id="voie_parent" value="${ row.Voie }"/>
						</c:forEach>
						
						<p>Code postal </p>
						
						<c:forEach  items="${ result_cp.rows }" var="row">
							<input type="text" name="cp_parent" id="cp_parent" value="${ row.Code_postal }"/>
						</c:forEach>
						
						<p>Ville </p>
						 
						<c:forEach  items="${ result_ville.rows }" var="row">
							<input type="text" name="ville_parent" id="ville_parent" value="${ row.Ville }"/>
						</c:forEach>
						
						<input type="submit" name="validation" id="validation" value="Validation">
						<sql:query dataSource="${bdd_co}" var="result_mail">
							SELECT p.Email FROM parents AS p WHERE p.NomParent_référence='${Nom}' AND p.Mdp='${Mdp}'
						</sql:query>
						
						<sql:query dataSource="${bdd_co}" var="result_téléphone">
							SELECT p.Téléphone FROM parents AS p WHERE p.NomParent_référence='${Nom}' AND p.Mdp='${Mdp}'
						</sql:query>
									
						<sql:query dataSource="${bdd_co}" var="result_Civilité">
							SELECT p.Civilité  FROM parents AS p WHERE p.NomParent_référence='${Nom}' AND p.Mdp='${Mdp}'
						</sql:query>
							
						<sql:query dataSource="${bdd_co}" var="result_nom">
							SELECT p.NomParent_référence  FROM parents AS p WHERE p.NomParent_référence='${Nom}' AND p.Mdp='${Mdp}'
						</sql:query>	
								
						<sql:query dataSource="${bdd_co}" var="result_prénom">
							SELECT p.PrénomParent_référence  FROM parents AS p WHERE p.NomParent_référence='${Nom}' AND p.Mdp='${Mdp}'
						</sql:query>	
										
						<sql:query dataSource="${bdd_co}" var="result_voie">
							SELECT p.Voie  FROM parents AS p WHERE p.NomParent_référence='${Nom}' AND p.Mdp='${Mdp}'
						</sql:query>
									
						<sql:query dataSource="${bdd_co}" var="result_cp">
							SELECT p.Code_postal  FROM parents AS p WHERE p.NomParent_référence='${Nom}' AND p.Mdp='${Mdp}'
						</sql:query>
									
						<sql:query dataSource="${bdd_co}" var="result_ville">
							SELECT p.Ville  FROM parents AS p WHERE p.NomParent_référence='${Nom}' AND p.Mdp='${Mdp}'
					    </sql:query>
					</form>
					
					
					
					<form id="partie_conjoint_choisie" method="post" action="">
						<h2>Mon(a) conjoint(e)</h2>
						<p>Son Mail </p>
						
						
						<c:forEach items="${ result_mail_conjoint.rows }" var="row">
							<c:out value="${row.Email}"/>
						</c:forEach>
						
						<p>Son téléphone </p>
						
						
						<c:forEach  items="${ result_téléphone_conjoint.rows }" var="row">
							<input type="text" name="num_téléphone" id="num_téléphone" value="${ row.Téléphone }"/>
						</c:forEach>
					
						
						<p>Civilité </p>
						
						<c:forEach  items="${ result_Civilité_conjoint.rows }" var="row">
							<input type="text" name="civilité" id="civilité" value="${ row.Civilité }"/>
						</c:forEach>
						
						<p>Nom </p>
						
						<c:forEach  items="${ result_nom_conjoint.rows }" var="row">
							<input type="text" name="nom_parent" id="nom_parent"  value="${ row.Nom }"/>
						</c:forEach>
						<p>Prénom</p>
						
						<c:forEach  items="${ result_prénom_conjoint.rows }" var="row">
							<input type="text" name="prénom_parent" id="prénom_parent" value="${ row.Prénom }"/>
						</c:forEach>
						
						
						<p>Adresse </p>
						<p>Voie </p>
						
						<c:forEach  items="${ result_voie_conjoint.rows }" var="row">
							<input type="text" name="voie_parent" id="voie_parent" value="${ row.Voie }"/>
						</c:forEach>
						
						<p>Code postal </p>
						
						<c:forEach  items="${ result_cp_conjoint.rows }" var="row">
							<input type="text" name="cp_parent" id="cp_parent" value="${ row.Code_postal }"/>
						</c:forEach>
						
						<p>Ville </p>
						 
						<c:forEach  items="${ result_ville_conjoint.rows }" var="row">
							<input type="text" name="ville_parent" id="ville_parent" value="${ row.Ville }"/>
						</c:forEach>
						
						<input type="submit" name="validation" id="validation" value="Validation">
						
						<sql:query dataSource="${bdd_co}" var="result_IdParent">
							SELECT p.IdParent FROM parents AS p WHERE p.NomParent_référence='${Nom}' AND p.Mdp='${Mdp}'
						</sql:query>
						
						<sql:query dataSource="${bdd_co}" var="result_mail_conjoint">
							SELECT c.Email FROM conjoint AS c WHERE c.IdParent_référent='${result_IdParent}'
						</sql:query>
						
						<sql:query dataSource="${bdd_co}" var="result_téléphone_conjoint">
							SELECT c.Téléphone FROM conjoint AS c WHERE c.IdParent_référent='${result_IdParent}'
						</sql:query>
									
						<sql:query dataSource="${bdd_co}" var="result_Civilité_conjoint">
							SELECT c.Civilité FROM conjoint AS c WHERE c.IdParent_référent='${result_IdParent}'
						</sql:query>
							
						<sql:query dataSource="${bdd_co}" var="result_nom_conjoint">
							SELECT c.Nom FROM conjoint AS c WHERE c.IdParent_référent='${result_IdParent}'
						</sql:query>	
								
						<sql:query dataSource="${bdd_co}" var="result_prénom_conjoint">
							SELECT c.Prénom FROM conjoint AS c WHERE c.IdParent_référent='${result_IdParent}'
						</sql:query>	
							
										
						<sql:query dataSource="${bdd_co}" var="result_voie_conjoint">
							SELECT c.Voie FROM conjoint AS c WHERE c.IdParent_référent='${result_IdParent}'
						</sql:query>
									
						<sql:query dataSource="${bdd_co}" var="result_cp_conjoint">
							SELECT c.Code_postal FROM conjoint AS c WHERE c.IdParent_référent='${result_IdParent}'
						</sql:query>
									
						<sql:query dataSource="${bdd_co}" var="result_ville_conjoint">
							SELECT c.Ville FROM conjoint AS c WHERE c.IdParent_référent='${result_IdParent}'
					    </sql:query>
					</form>
					
					<form id="partie_enfants_choisie" method="post" action=""> 
						
					
						<input type="submit" name="validation" id="validation" value="Validation">
						
						<sql:query dataSource="${bdd_co}" var="result_mail_conjoint">
							SELECT e.Sexe FROM enfants AS e WHERE e.IdParent='${result_IdParent}'
						</sql:query>
						
						<sql:query dataSource="${bdd_co}" var="result_téléphone_conjoint">
							SELECT e.Date_Naissance FROM enfants AS e WHERE e.IdParent='${result_IdParent}'
						</sql:query>
									
						<sql:query dataSource="${bdd_co}" var="result_Civilité_conjoint">
							SELECT e.Nom FROM enfants AS e WHERE e.IdParent='${result_IdParent}'
						</sql:query>
								
						<sql:query dataSource="${bdd_co}" var="result_prénom_conjoint">
							SELECT e.Prénom1 FROM enfants AS e WHERE e.IdParent='${result_IdParent}'
						</sql:query>	
							
										
						<sql:query dataSource="${bdd_co}" var="result_voie_conjoint">
							SELECT e.Prénom2  FROM enfants AS e WHERE e.IdParent='${result_IdParent}'
						</sql:query>
					</form>
					
					<form id="partie_modifié_mdp_choisie" method="post" action="">
					
					</form>
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