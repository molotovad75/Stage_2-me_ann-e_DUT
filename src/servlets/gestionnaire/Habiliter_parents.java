package servlets.gestionnaire;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Conjoint;
import bean.Enfant;
import bean.Parent;
import jdbc.BDD_Connexion;
import servlets.Identification_pro;

public class Habiliter_parents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Parent parent;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BDD_Connexion.load_database();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Parent par=new Parent();
		Conjoint conj=new Conjoint();
		Enfant e=new Enfant();
		par.setNom(req.getParameter("Nom_référent"));
		par.setPrénom(req.getParameter("Prénom_référent"));
		par.setEmail(req.getParameter("mail_référent"));
		
		par.setTéléphone(req.getParameter("Téléphone_référent"));
		par.setCivilité(req.getParameter("Civilité_parent_ref"));
		if (par.getNom().equals("")==true 
			||par.getEmail().equals("")==true 
			||par.getTéléphone().equals("")==true 
			||par.getCivilité().equals("")==true ) {
			
			String message_erreur_parent="Au moins un champ obligatoire n'a pas été rempli !";
			req.setAttribute("message_erreur_parent", message_erreur_parent);
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Habilitation_parents.jsp").forward(req, resp);
			
		}else if(par.getNom().equals("")==false 
				||par.getEmail().equals("")==false 
				||par.getTéléphone().equals("")==false 
				||par.getCivilité().equals("")==false ) {
			
			enregister_parent_classique_BDD(par);
			enregistrer_parent_habilité_BDD();
			
			if (req.getParameter("mail_conjoint").equals("")==false) {//On vérifie oui ou non si l'email a été renseigné.
				conj.setNom(req.getParameter("Nom_conjoint"));
				conj.setTéléphone(req.getParameter("Téléphone_conjoint"));
				conj.setEmail(req.getParameter("mail_conjoint"));
				conj.setCivilité(req.getParameter("Civilité_conjoint"));
				if (conj.getCivilité().equals("")==true || conj.getNom().equals("")==true || conj.getTéléphone().equals("")==true) {
					String message_erreur_conjoint="Au moins un champ obligatoire n'a pas été rempli !";
					req.setAttribute("message_erreur_conjoint", message_erreur_conjoint);
					this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Habilitation_parents.jsp").forward(req, resp);
				}else if(conj.getCivilité().equals("")==false || conj.getNom().equals("")==false || conj.getTéléphone().equals("")==false) {
					enregistrer_conjoint(conj,req);
				}
				
			}
			
			
			e.setDate_naiss(req.getParameter("date_naiss1"));
			e.setNom(req.getParameter("nom_enfant1"));
			e.setPrénom1(req.getParameter("prénom_enfant1"));
			e.setPrénom2(req.getParameter("prénom2_enfant1"));
			if (e.getDate_naiss().equals("")==true 
				|| e.getNom().equals("")==true 
				|| e.getPrénom1().equals("")==true) {
				String message_erreur_enfant1="Au moins un champ obligatoire n'a pas été rempli !";
				req.setAttribute("message_erreur_enfant1", message_erreur_enfant1);
				this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Habilitation_parents.jsp").forward(req, resp);
			}else if(e.getDate_naiss().equals("")==false 
					|| e.getNom().equals("")==false 
					|| e.getPrénom1().equals("")==false) {
				enregistrer_enfant1(e,req);
			}
		}
		
		
	}//FIN de la méthode doPost()
	
	public static void enregister_parent_classique_BDD(Parent parent) {
		String reqSQL1="INSERT INTO parents VALUES (?,?,?,?,?,?)";
		PreparedStatement ps=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL1);
			ps.setString(1, parent.getNom());
			ps.setString(2, parent.getPrénom());
			ps.setString(3, parent.getEmail());
			ps.setString(4, parent.getMdp());
			ps.setString(5, parent.getTéléphone());
			ps.setString(6, parent.getCivilité());
			ps.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		}
		Habiliter_parents.parent=parent;
	}
	
	/*Trouver parent habilités****************************************************************************************/
	public static int trouver_phr_idParent_habilité() {
		String reqSQLtrouver_idParent_habilité="SELECT phr.IdParent "
				+ "FROM parents_habilite_reservation AS phr "
				+ "WHERE phr.IdParent="+trouver_parent_IdParent()+" ;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int Idparent_habilité=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_idParent_habilité);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				Idparent_habilité=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return Idparent_habilité;
	}
	
	public static int trouver_parent_IdParent() {
		String reqSQLtrouver_p_IdParent="SELECT p.IdParent "
				+ "FROM parents AS p "
				+ "WHERE p.NomParent_référence="+Habiliter_parents.parent.getNom()+" "
				+ "AND p.PrénomParent_référence="+Habiliter_parents.parent.getPrénom()+" "
				+ "AND p.Email="+Habiliter_parents.parent.getEmail()+" ;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int Idparents=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_p_IdParent);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				Idparents=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return Idparents;
	}
	/*FIN Trouver parent habilités****************************************************************************************/
	
	
	/*Trouver crèches habilités****************************************************************************************/
	public static int trouver_phr_idCrèche() throws SQLException {
		String reqSQL_trouver_phr_idCrèche="SELECT phr.IdCrèche "
				+ "FROM parents_habilite_reservation AS phr "
				+ "WHERE phr.IdCrèche="+trouver_crèche_idCrèche()+"; ";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int IdCrèche=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL_trouver_phr_idCrèche);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				IdCrèche=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return IdCrèche;
	}
	
	public static int trouver_crèche_idCrèche() throws SQLException {
		String reqSQL_trouver_crèche_idCrèche="SELECT sc.IdCrèche "
				+ "FROM `site_creche` AS sc, parents_habilite_reservation AS phr "
				+ "WHERE sc.`Nom Site`="+Choix_action.getnom_choix_site()+" "
				+ "AND sc.Id_Enseigne="+trouver_enseigne_idEnseigne()+" ;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int IdCrèche=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL_trouver_crèche_idCrèche);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				IdCrèche=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}	
		return IdCrèche;
	}
			//C'est pour trouver l'enseigne de la crèche en question
	public static int trouver_enseigne_idEnseigne() throws SQLException {
		String reqSQLtrouver_enseigne_idEnseigne="SELECT e.IdEnseigne "
				+ "FROM enseigne AS e "
				+ "WHERE e.NomEnseigne="+Identification_pro.récup_nom_enseignes(Identification_pro.getGestionnaire().getNom_user())+" ";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int IdEnseigne=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_enseigne_idEnseigne);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				IdEnseigne=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return IdEnseigne;
	}
	/*FIN Trouver crèches habilités****************************************************************************************/
	
	
	public static void enregistrer_parent_habilité_BDD() {
		String reqSQL2="INSERT INTO parents_habilite_reservation VALUES (?,?)";
		PreparedStatement ps=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL2);
			ps.setInt(1,trouver_phr_idParent_habilité());//IdParent
			ps.setInt(2, trouver_phr_idCrèche());//IdCrèche
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	
	
	/*** - CONJOINT - *********************************************************************************************************************/
	
	public static void enregistrer_conjoint(Conjoint conjoint,HttpServletRequest req) {
		String reqSQL1="INSERT INTO conjoint VALUES (?,?,?,?,?)";
		PreparedStatement ps=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL1);
			ps.setInt(1, trouver_conjoint_Idparent_référent(req));//IdParent_référent
			ps.setString(2, conjoint.getCivilité());
			ps.setString(3, conjoint.getNom());
			ps.setString(4, conjoint.getTéléphone());
			ps.setString(5, conjoint.getEmail());
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public static int trouver_conjoint_Idparent_référent(HttpServletRequest req) {
		String reqSQLtrouver_conjoint_Idparent_référent="SELECT p.IdParent "
				+ "FROM parents AS p "
				+ "WHERE p.NomParent_référence="+req.getParameter("Nom_référent")+"; ";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int Idparent=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_conjoint_Idparent_référent);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				Idparent=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return Idparent;
	}
	/*** - FIN CONJOINT - *********************************************************************************************************************/
	
	/*** - Enfant 1 - *********************************************************************************************************************/
	public static void enregistrer_enfant1(Enfant enfant,HttpServletRequest req) {
		String reqSQLenregistrer_enfant1="INSERT INTO `enfants` "
				+ "VALUES  (?,?,?,?,?)";
		PreparedStatement ps=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLenregistrer_enfant1);
			ps.setString(1, enfant.getNom());
			ps.setString(2, enfant.getPrénom1());
			ps.setString(3, enfant.getPrénom2());
			ps.setString(4,	enfant.getDate_naiss());
			ps.setInt(5, trouver_Idparents_enfants());
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public static int trouver_Idparents_enfants() {
		String reqSQLtrouver_Idparents_enfants=
				  "SELECT p.IdParent "
				+ "FROM parents AS p "
				+ "WHERE p.NomParent_référence="+parent.getNom()+" "
				+ "AND p.Email="+parent.getEmail()+" "
				+ "AND p.Téléphone="+parent.getTéléphone()+"  ;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int Idparent_trouvé=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_Idparents_enfants);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				Idparent_trouvé=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return Idparent_trouvé;
	}
	
	/*** - Fin Enfant 1 - *********************************************************************************************************************/
	
}
