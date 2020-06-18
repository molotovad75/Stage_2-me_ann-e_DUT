/**
 * 
 */
function validation_choix_site_action(){
	
	var liste_action, texte_action,liste_site,texte_site,choice;
	
	liste_site=document.getElementById("choix_site");
	texte_site=liste_site.options[liste_site.selectedIndex].text;
	
	liste_action = document.getElementById("choix_action");
	texte_action = liste_action.options[liste_action.selectedIndex].text;
	
	choice = select.selectedIndex;
	
//	if (texte_site) {
//		
//	}
	if (texte_action==liste_action.options[0].value) {
		document.getElementById("sectionMAJ_site").style.display = "block"; 
		document.getElementById("sectionPlanification_crénaux_dispo").style.display = "none"; 
		document.getElementById("sectionHabiliter_parents").style.display = "none"; 
	}
}