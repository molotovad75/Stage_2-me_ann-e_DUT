<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Connexion</title>
		<link href=".\CSS\style.css" rel="stylesheet" type="text/css" />
	</head>
	
	<body>
		
		<div id="btns_connexion">
			<label for="menu_options_co" class="Choix_Espaces">Espaces</label>
			<input type="checkbox" id="menu_options_co" role="button" />
			<img alt="" src=".\Images\BOTTINES-BOTTILLONS-LOGO.png" id="logoBandB">
			<div id="menu_choix">
				<input type="button" id="Connexion_parents" value="Espace Parents"/>
				<input type="button" id="Connexion_pro" value="Espace Pro" />
			</div>
		</div>
		
		<div id="contenu_page">
			<div id="Corps">
				<h1>Espace de Connexion</h1>
				<section id="form_connexion">
					<div id="Identification_parents">
						<p>J'ai un compte parent</p>
						<p id="ident">Je m'identifie</p>
						<p><input type="text" id="Id_parent" placeholder="Nom d'utilisateur"/><br><br>
						<input type="password" id="mdp_parent" placeholder="Mot de passe utilisateur"/><br><br>
						<input type="submit" id="Se_connecter" placeholder="Se connecter"/>
						<a href="" id="mdp_oubli">Mot de passe oublié</a></p>
					</div>	
				
					<div id="Identification_pro">
						<p>J'ai un compte pro</p>
						<p id="ident">Je m'identifie</p>
						<p><input type="text" id="Id_pro" placeholder="Nom d'utilisateur pro"/><br><br>
						<input type="password" id="mdp_pro" placeholder="Mot de passe utilisateur pro"/><br><br>
						<input type="submit" id="Se_connecter" placeholder="Se connecter"/>
						<a href="" id="mdp_oubli">Mot de passe pro oublié</a></p>
					</div>
				</section>
			</div>
		</div>	
		<footer>
			<p>Bottines et botillons Services  © </p>
			<p>Pour en savoir plus sur nous visitez notre site<a href="https://www.bottinesetbottillons.fr" id="lien_site"> Bottines & Bottilons</a>.</p>
		</footer>
			
	</body>
</html>