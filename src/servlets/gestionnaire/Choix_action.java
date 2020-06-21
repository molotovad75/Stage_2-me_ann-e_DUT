package servlets.gestionnaire;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Choix;
import servlets.Identification_pro;

public class Choix_action extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Choix choix=null;
	private static String nom_choix_action="",nom_choix_site="";
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		choix=new Choix();
		choix.setNomchoix_action(req.getParameter("choix_action"));
		choix.setNomchoix_site(req.getParameter("choix_site"));
		nom_choix_action=choix.getNomchoix_action();
		nom_choix_site=choix.getNomchoix_site();
		if (nom_choix_action.equals("MAJ informations sites/cr�neaux")==true) {
			try {
				req.setAttribute("Horaires_Lundi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),1,nom_choix_site));
				req.setAttribute("Horaires_Mardi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),2,nom_choix_site));
				req.setAttribute("Horaires_Mercredi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),3,nom_choix_site));
				req.setAttribute("Horaires_Jeudi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),4,nom_choix_site));
				req.setAttribute("Horaires_Vendredi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),5,nom_choix_site));
				req.setAttribute("Horaires_Samedi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),6,nom_choix_site));
				req.setAttribute("Horaires_Dimanche", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),7,nom_choix_site));
				
			} catch (Exception e) {
				e.getMessage();
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/MAJ_infos_sites_cr�naux.jsp").forward(req, resp);
			
		}else if(nom_choix_action.equals("Planification cr�neaux disponibles")==true) {
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Planification_cr�neaux_dispo.jsp").forward(req, resp);
		}else if(nom_choix_action.equals("Habiliter un parent")==true) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Habilitation_parents.jsp").forward(req, resp);
		}
		//R�cuper les valeurs des 2 d�rouleurs.
		
	}
	
}
