package servlets.gestionnaire;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Choix;
import bean.Site;
import jdbc.BDD_Connexion;
import servlets.Identification_pro;

public class Choix_action extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Choix choix=null;
	private static String nom_choix_action="",nom_choix_site="";
	private static Site site=null;
	private static ArrayList<String> Nom_prenom;
	private static ResultSet resultat=null;
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		choix=new Choix();
		choix.setNomchoix_action(req.getParameter("choix_action"));
		choix.setNomchoix_site(req.getParameter("choix_site"));
		nom_choix_action=choix.getNomchoix_action();
		nom_choix_site=choix.getNomchoix_site();
		String reqSQL="SELECT sc.Num�ro, sc.Voie, sc.Compl�ment, sc.CP, sc.Ville, sc.Tel_contact, sc.Mail_contact, sc.Nom_contact, sc.Type, sc.Capacit� "
				+ " FROM `site_creche` AS sc "
				+ " WHERE `Nom Site`="+nom_choix_site+";";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		
		if (nom_choix_action.equals("MAJ informations sites/cr�neaux")==true) {
			
			try {
				req.setAttribute("Horaires_Lundi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),1,nom_choix_site));
				req.setAttribute("Horaires_Mardi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),2,nom_choix_site));
				req.setAttribute("Horaires_Mercredi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),3,nom_choix_site));
				req.setAttribute("Horaires_Jeudi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),4,nom_choix_site));
				req.setAttribute("Horaires_Vendredi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),5,nom_choix_site));
				req.setAttribute("Horaires_Samedi", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),6,nom_choix_site));
				req.setAttribute("Horaires_Dimanche", Identification_pro.r�cupHoraire_jour(Identification_pro.getGestionnaire(),7,nom_choix_site));
				req.setAttribute("Nom_site", nom_choix_site);
				
				ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
				resultat=ps.executeQuery();
				if (resultat.next()) {
					site.setNum(resultat.getInt(1));
					site.setVoie(resultat.getString(2));
					site.setCompl�ment(resultat.getString(3));
					site.setCp(resultat.getInt(4));
					site.setVille(resultat.getString(5));
					site.setTel_contact(resultat.getString(6));
					site.setMail_contact(resultat.getString(7));
					site.setNom_contact(resultat.getString(8));
					site.setType(resultat.getString(9));
					site.setCapacit�(resultat.getInt(10));
				}
				req.setAttribute("Num", site.getNum());
				req.setAttribute("Voie", site.getVoie());
				req.setAttribute("Compl�ment", site.getCompl�ment());
				req.setAttribute("Cp", site.getCp());
				req.setAttribute("Ville", site.getVille());
				req.setAttribute("Tel_contact", site.getTel_contact());
				req.setAttribute("Mail_contact", site.getMail_contact());
				req.setAttribute("Nom_contact", site.getNom_contact());
				req.setAttribute("Type", site.getType());
				req.setAttribute("Capacit�", site.getCapacit�());
				
			} catch (Exception e) {
				e.getMessage();
			}
			
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/MAJ_infos_sites_cr�naux.jsp").forward(req, resp);
			
		}
		
		else if(nom_choix_action.equals("Planification cr�neaux disponibles")==true) {
			req.setAttribute("Nom_site", nom_choix_site);
			
			try {
				req.setAttribute("Horaires_Lundi", Identification_pro.r�cup_Horaire_matin_apr�m_soir(Identification_pro.getGestionnaire(),1,nom_choix_site)); //a la fin des 6 ligne suivantes + celle ci je mettait .get(i) i �tant un chiffre
				req.setAttribute("Horaires_Mardi", Identification_pro.r�cup_Horaire_matin_apr�m_soir(Identification_pro.getGestionnaire(),2,nom_choix_site));
				req.setAttribute("Horaires_Mercredi", Identification_pro.r�cup_Horaire_matin_apr�m_soir(Identification_pro.getGestionnaire(),3,nom_choix_site));
				req.setAttribute("Horaires_Jeudi", Identification_pro.r�cup_Horaire_matin_apr�m_soir(Identification_pro.getGestionnaire(),4,nom_choix_site));
				req.setAttribute("Horaires_Vendredi", Identification_pro.r�cup_Horaire_matin_apr�m_soir(Identification_pro.getGestionnaire(),5,nom_choix_site));
				req.setAttribute("Horaires_Samedi", Identification_pro.r�cup_Horaire_matin_apr�m_soir(Identification_pro.getGestionnaire(),6,nom_choix_site));
				req.setAttribute("Horaires_Dimanche", Identification_pro.r�cup_Horaire_matin_apr�m_soir(Identification_pro.getGestionnaire(),7,nom_choix_site));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Planification_cr�neaux_dispo.jsp").forward(req, resp);
			
			
		}
		
		else if(nom_choix_action.equals("Habiliter un parent")==true) {
			req.setAttribute("Nom_site", nom_choix_site);
			String reqSQL2="SELECT dip.Nom,dip.Pr�nom "
					+ " FROM `demande_inscription_parent` AS dip";
			 			
			ps=null;
			resultat=null;
			ArrayList<String> Nom_et_prenom=new ArrayList<String>();
			
			try {
				ps=BDD_Connexion.getConn().prepareStatement(reqSQL2);
				resultat=ps.executeQuery();
				while (resultat.next()) {
					Nom_et_prenom.add(resultat.getString(1)+" "+resultat.getString(2));
				}
				Nom_prenom=Nom_et_prenom;
				req.setAttribute("nom_prenom", Nom_et_prenom);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Habilitation_parents.jsp").forward(req, resp);
		}
		else if(nom_choix_action.equals("Votre action *")==true) {
			String message="Veuillez choisir un site de votre enseigne et une action qui suit !";
			//|| nom_choix_site.equals("Choisissez votre site *")==true
			req.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Espace_gestionnaire.jsp").forward(req, resp);
		}
	}
	
	
	
	public static String getnom_choix_site() {
		return nom_choix_site;
	}

	public static ResultSet getResultat() {
		return Choix_action.resultat;
	}

	public static void setResultat(ResultSet resultat) {
		Choix_action.resultat = resultat;
	}
	public static ArrayList<String> getNom_prenom() {
		return Nom_prenom;
	}
}
