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
		par.setNom(req.getParameter("Nom_r�f�rent"));
		par.setPr�nom(req.getParameter("Pr�nom_r�f�rent"));
		par.setEmail(req.getParameter("mail_r�f�rent"));
		
		par.setT�l�phone(req.getParameter("T�l�phone_r�f�rent"));
		par.setCivilit�(req.getParameter("Civilit�_parent_ref"));
		if (par.getNom().equals("")==true 
			||par.getEmail().equals("")==true 
			||par.getT�l�phone().equals("")==true 
			||par.getCivilit�().equals("")==true ) {
			
			String message_erreur_parent="Au moins un champ obligatoire n'a pas �t� rempli !";
			req.setAttribute("message_erreur_parent", message_erreur_parent);
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Habilitation_parents.jsp").forward(req, resp);
			
		}else if(par.getNom().equals("")==false 
				||par.getEmail().equals("")==false 
				||par.getT�l�phone().equals("")==false 
				||par.getCivilit�().equals("")==false ) {
			
			enregister_parent_classique_BDD(par);
			enregistrer_parent_habilit�_BDD();
			
			if (req.getParameter("mail_conjoint").equals("")==false) {//On v�rifie oui ou non si l'email a �t� renseign�.
				conj.setNom(req.getParameter("Nom_conjoint"));
				conj.setT�l�phone(req.getParameter("T�l�phone_conjoint"));
				conj.setEmail(req.getParameter("mail_conjoint"));
				conj.setCivilit�(req.getParameter("Civilit�_conjoint"));
				if (conj.getCivilit�().equals("")==true || conj.getNom().equals("")==true || conj.getT�l�phone().equals("")==true) {
					String message_erreur_conjoint="Au moins un champ obligatoire n'a pas �t� rempli !";
					req.setAttribute("message_erreur_conjoint", message_erreur_conjoint);
					this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Habilitation_parents.jsp").forward(req, resp);
				}else if(conj.getCivilit�().equals("")==false || conj.getNom().equals("")==false || conj.getT�l�phone().equals("")==false) {
					enregistrer_conjoint(conj,req);
				}
				
			}
			
			
			e.setDate_naiss(req.getParameter("date_naiss1"));
			e.setNom(req.getParameter("nom_enfant1"));
			e.setPr�nom1(req.getParameter("pr�nom_enfant1"));
			e.setPr�nom2(req.getParameter("pr�nom2_enfant1"));
			if (e.getDate_naiss().equals("")==true 
				|| e.getNom().equals("")==true 
				|| e.getPr�nom1().equals("")==true) {
				String message_erreur_enfant1="Au moins un champ obligatoire n'a pas �t� rempli !";
				req.setAttribute("message_erreur_enfant1", message_erreur_enfant1);
				this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Habilitation_parents.jsp").forward(req, resp);
			}else if(e.getDate_naiss().equals("")==false 
					|| e.getNom().equals("")==false 
					|| e.getPr�nom1().equals("")==false) {
				enregistrer_enfant1(e,req);
			}
		}
		
		
	}//FIN de la m�thode doPost()
	
	public static void enregister_parent_classique_BDD(Parent parent) {
		String reqSQL1="INSERT INTO parents VALUES (?,?,?,?,?,?)";
		PreparedStatement ps=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL1);
			ps.setString(1, parent.getNom());
			ps.setString(2, parent.getPr�nom());
			ps.setString(3, parent.getEmail());
			ps.setString(4, parent.getMdp());
			ps.setString(5, parent.getT�l�phone());
			ps.setString(6, parent.getCivilit�());
			ps.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		}
		Habiliter_parents.parent=parent;
	}
	
	/*Trouver parent habilit�s****************************************************************************************/
	public static int trouver_phr_idParent_habilit�() {
		String reqSQLtrouver_idParent_habilit�="SELECT phr.IdParent "
				+ "FROM parents_habilite_reservation AS phr "
				+ "WHERE phr.IdParent="+trouver_parent_IdParent()+" ;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int Idparent_habilit�=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_idParent_habilit�);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				Idparent_habilit�=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return Idparent_habilit�;
	}
	
	public static int trouver_parent_IdParent() {
		String reqSQLtrouver_p_IdParent="SELECT p.IdParent "
				+ "FROM parents AS p "
				+ "WHERE p.NomParent_r�f�rence="+Habiliter_parents.parent.getNom()+" "
				+ "AND p.Pr�nomParent_r�f�rence="+Habiliter_parents.parent.getPr�nom()+" "
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
	/*FIN Trouver parent habilit�s****************************************************************************************/
	
	
	/*Trouver cr�ches habilit�s****************************************************************************************/
	public static int trouver_phr_idCr�che() throws SQLException {
		String reqSQL_trouver_phr_idCr�che="SELECT phr.IdCr�che "
				+ "FROM parents_habilite_reservation AS phr "
				+ "WHERE phr.IdCr�che="+trouver_cr�che_idCr�che()+"; ";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int IdCr�che=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL_trouver_phr_idCr�che);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				IdCr�che=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return IdCr�che;
	}
	
	public static int trouver_cr�che_idCr�che() throws SQLException {
		String reqSQL_trouver_cr�che_idCr�che="SELECT sc.IdCr�che "
				+ "FROM `site_creche` AS sc, parents_habilite_reservation AS phr "
				+ "WHERE sc.`Nom Site`="+Choix_action.getnom_choix_site()+" "
				+ "AND sc.Id_Enseigne="+trouver_enseigne_idEnseigne()+" ;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int IdCr�che=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL_trouver_cr�che_idCr�che);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				IdCr�che=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}	
		return IdCr�che;
	}
			//C'est pour trouver l'enseigne de la cr�che en question
	public static int trouver_enseigne_idEnseigne() throws SQLException {
		String reqSQLtrouver_enseigne_idEnseigne="SELECT e.IdEnseigne "
				+ "FROM enseigne AS e "
				+ "WHERE e.NomEnseigne="+Identification_pro.r�cup_nom_enseignes(Identification_pro.getGestionnaire().getNom_user())+" ";
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
	/*FIN Trouver cr�ches habilit�s****************************************************************************************/
	
	
	public static void enregistrer_parent_habilit�_BDD() {
		String reqSQL2="INSERT INTO parents_habilite_reservation VALUES (?,?)";
		PreparedStatement ps=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL2);
			ps.setInt(1,trouver_phr_idParent_habilit�());//IdParent
			ps.setInt(2, trouver_phr_idCr�che());//IdCr�che
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
			ps.setInt(1, trouver_conjoint_Idparent_r�f�rent(req));//IdParent_r�f�rent
			ps.setString(2, conjoint.getCivilit�());
			ps.setString(3, conjoint.getNom());
			ps.setString(4, conjoint.getT�l�phone());
			ps.setString(5, conjoint.getEmail());
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public static int trouver_conjoint_Idparent_r�f�rent(HttpServletRequest req) {
		String reqSQLtrouver_conjoint_Idparent_r�f�rent="SELECT p.IdParent "
				+ "FROM parents AS p "
				+ "WHERE p.NomParent_r�f�rence="+req.getParameter("Nom_r�f�rent")+"; ";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int Idparent=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_conjoint_Idparent_r�f�rent);
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
			ps.setString(2, enfant.getPr�nom1());
			ps.setString(3, enfant.getPr�nom2());
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
				+ "WHERE p.NomParent_r�f�rence="+parent.getNom()+" "
				+ "AND p.Email="+parent.getEmail()+" "
				+ "AND p.T�l�phone="+parent.getT�l�phone()+"  ;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int Idparent_trouv�=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_Idparents_enfants);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				Idparent_trouv�=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return Idparent_trouv�;
	}
	
	/*** - Fin Enfant 1 - *********************************************************************************************************************/
	
}
