/**
 * 
 */


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