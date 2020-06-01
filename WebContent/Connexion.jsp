<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Connexion</title>
		<link href=".\CSS\style_connexion.css" rel="stylesheet" type="text/css" />
		<script  type="text/javascript" >
			function afficher_cacher_div(){
					/* Connexion pro/gestionnaire qui disparait à moitié*/	
				Identification_pro.style.opacity='50%';
				Création_pro.style.opacity='50%';
				Identification_parents.style.opacity='100%';
				Création_parents.style.opacity='100%';
				Connexion_parents.disabled=true;
				Connexion_pro.disabled=false;
				/* Connexion_parents.style.font-weight='bold'; */
				
				var Identification_pro_input = document.getElementById("Identification_pro").getElementsByTagName("input").getElementById("Id_pro");
				var Création_pro_input = document.getElementById("Création_pro").getElementsByTagName("input").getElementById("Nom");
				Identification_pro_input.disabled.maxLength='0'; Création_pro_enfants.input.maxLength='0';
			}
			
			function afficher_cacher_div2() {
				/* Connexion parent qui disparait à moitié*/	
				Identification_pro.style.opacity='100%';
				Création_pro.style.opacity='100%';
				Identification_parents.style.opacity='50%';
				Création_parents.style.opacity='50%';
				Connexion_parents.disabled=false;	
				Connexion_pro.disabled=true;
				/* Connexion_pro.style.font-weight='bold'; */
				var Identification_parents_input = document.getElementById("Identification_parents").getElementsByTagName("input").getElementById("Id_parent");
				var Création_parents_input = document.getElementById("Création_parents").getElementsByTagName("input").getElementById("Nom");
				Identification_parents_input.maxLength='0';Création_parents_input.maxLength='0';
				
			}
			
		</script>
	</head>
	
	<body>
		
		<div id="btns_connexion">
			<label for="menu_options_co" class="Choix_Espaces">Espaces</label>
			<input type="checkbox" id="menu_options_co" role="button" />
			<img alt="" src=".\Images\OuiCr%E8chesLogo.png" id="logoBandB">
			<div id="menu_choix">
				<input type="button" id="Connexion_parents" value="Espace Parents" onclick="afficher_cacher_div();"/>
				<input type="button" id="Connexion_pro" value="Espace Pro" onclick="afficher_cacher_div2();" /> 
			</div>
		</div>
		
		<div id="contenu_page">
			<div id="Corps">
				<h1>Espace de Connexion</h1>
				<p id="phrase_accueil">Besoin d’une garde d’enfant occasionnelle ?  Réservez rapidement au sein de votre crèche partenaire</p>
				
				<section id="form_connexion">
					<div id="Identification_parents">
						<p>J'ai un compte parent</p>
						<p id="ident">Je m'identifie</p>
						<p>
							<input type="text" id="Id_parent" placeholder="Nom d'utilisateur"/><br><br>
							<input type="password" id="mdp_parent" placeholder="Mot de passe utilisateur"/><br><br>
							<input type="submit" id="Se_connecter" placeholder="Se connecter"/>
							<a href="" id="mdp_oubli">Mot de passe oublié</a>
						</p>
					</div>	
				
					<div id="Création_parents">
						<p>Je n'ai pas de compte parents</p>
						<p id="ident">Je souhaite en savoir plus sur la solution OuiCrèches</p>
						<p>
							<input type="text" id="Nom" placeholder="Nom *" name="Nom"/><br><br>
							<input type="text" id="Prénom" placeholder="Prénom *"name="Prénom"/><br><br>
							<input type="email" id="email_parent" placeholder="email *"name="email_parent"/><br><br> <!-- Id_parent -->
							<input type="tel" id="tel_parent" placeholder="téléphone parents *"name="tel_parent"/><br><br> <!-- tel_parent -->
							<textarea rows="8" cols="45" maxlength="65525" name="message" id="message" placeholder="Message"></textarea>
							<input type="submit" id="Se_connecter" placeholder="S'inscrire" name="Se_connecter"/>
						</p>
					</div>
					
					<div id="Identification_pro">
						<p>J'ai un compte pro/gestionnaire</p>
						<p id="ident">Je m'identifie</p>
						<p>
							<input type="text" id="Id_pro" placeholder="Nom pro"/><br><br>
							<input type="password" id="mdp_parent" placeholder="Mot de passe pro"/><br><br>
							<input type="submit" id="Se_connecter" placeholder="Se connecter"/>
							<a href="" id="mdp_oubli">Mot de passe oublié</a>
						</p>
					</div>
					
					<div id="Création_pro">
						<p>Je n'ai pas de compte pro/gestionnaire</p>
						<p id="ident">Je souahite en savoir plus sur la solution OuiCrèche Pro</p>
						<p>
							<input type="text" id="Nom" placeholder="Nom *" name="Nom"/><br><br>
							<input type="text" id="Prénom" placeholder="Prénom"name="Prénom"/><br><br>
							<input type="email" id="email_pro" placeholder="email *"name="email_pro"/><br><br> 
							<input type="tel" id="tel_pro" placeholder="Téléphone pro *"name="tel_pro"/><br><br>
							
							<textarea  rows="8" cols="45" maxlength="65525" name="message" id="message" placeholder="Message *"></textarea>
							<input type="submit" id="Se_connecter" placeholder="S'inscrire" name="Se_connecter"/>
						</p>
					</div>
				</section>
			</div>
		</div>	
		<footer>
			<p> Bonjour, 
				<br>OuiCrèches est une plateforme dédiée à la réservation de places occasionnelles en crèche.
				<br><br> Notre mission ? 
	 			<br><br>Rendre la réservation plus simple, plus juste et plus sécurisée pour les parents et les gestionnaires établissements.
 			</p>
			<p>A Propos    :<br><br>
				<a href="https://www.bottinesetbottillons.fr" id="lien_site"> Qui sommes nous ?</a><br>
				<a href="https://www.bottinesetbottillons.fr" id="lien_site"> Comment ça marche ?</a><br>
				<a href="https://www.bottinesetbottillons.fr" id="lien_site"> Nous contacter</a>.
			</p><br>
		</footer>
			
	</body>
</html>