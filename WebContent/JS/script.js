/**
 * 
 */

var Connexion_parents=document.getElementById('Connexion_parents');
var Connexion_pro=document.getElementById('Connexion_pro');


var Identification_parents=document.getElementByID('Identification_parents');
var Création_parents=document.getElementByID('Création_parents');
var Identification_pro=document.getElementByID('Identification_pro');
var Création_pro=document.getElementByID('Création_pro');



function afficher_cacher_div_parent(){
	if (getComputedStyle(Identification_parents).visibility==='visible' && getComputedStyle(Création_parents).visibility==='visible') {
			
			Identification_parents.style.visibility='hidden';
			Création_parents.style.visibility='hidden';
	} else {
			Identification_parents.style.visibility='visible';
			Création_parents.style.visibility='visible';
	}
}
Connexion_parents.addEventListener('click', afficher_cacher_div_parent);

