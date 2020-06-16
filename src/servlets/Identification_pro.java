package servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Gestionnaire;
import jdbc.BDD_Connexion;

public class Identification_pro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Gestionnaire gest=null;
	private static ArrayList<String> list_nom_enseigne=null;
	
	public static ArrayList<String> getlist_nom_enseigne() {
		return list_nom_enseigne;
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean resultat = false;
		try {
			BDD_Connexion.load_database();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		gest = new Gestionnaire();
		gest.setNom_user(req.getParameter("Id_pro"));
		gest.setMdp(req.getParameter("mdp_parent"));
		
		
		try {
			try {
				resultat=vérifier_comtpe_pro(gest,req,resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (resultat==false) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(req, resp);
			}else if (resultat==true) {
				récup_infos_sites_gestionnaires(gest.getNom_user());
				récup_nom_enseignes(gest.getNom_user());
				récupPrénom(gest.getNom_user(),gest.getMdp());
				req.setAttribute("NomEnseigne_site", récup_infos_sites_gestionnaires(gest.getNom_user()));
				req.setAttribute("NomEnseigne", récup_nom_enseignes(gest.getNom_user()));
				req.setAttribute("Prenom", récupPrénom(gest.getNom_user(),gest.getMdp()));
				this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Espace_gestionnaire.jsp").forward(req, resp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean vérifier_comtpe_pro(Gestionnaire gest,HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT pg.Nom, pg.Mdp FROM pro_gestionnaire AS pg WHERE pg.Nom=? AND pg.Mdp=?;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			ps.setString(1, gest.getNom_user());
			ps.setString(2, gest.getMdp());
			resultat=ps.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!resultat.next()) {
			//Dire qu'il y a une erreur d'authentification
			String mess_erreur_authentification="Authentification échouée, veuillez rééssayer !";
			req.setAttribute("auth", mess_erreur_authentification);
			return false;
		}else {
			//Connection authentifié
			String mess_authentification="Authentification réussie !";
			req.setAttribute("auth", mess_authentification);
			req.setAttribute("Nom", gest.getNom_user());
			
			return true;
		}
	}
	
	
	
	public static ArrayList<String> récup_infos_sites_gestionnaires(String pgNom) throws SQLException {
		String reqSQL1="SELECT e.NomEnseigne, sc.`Nom Site` "
				+ "FROM enseigne AS e,pro_gestionnaire AS pg,site_creche AS sc "
				+ "WHERE e.NumGestionnaire=pg.IdPro AND sc.Id_Enseigne=e.IdEnseigne AND pg.Nom='"+pgNom+"';";
		
		
		String lsNomEnseigne = "";String lsNomSite = ""; String ls_site_enseigne="";
		list_nom_enseigne=new ArrayList<String>();
		try {
			Statement state = BDD_Connexion.load_database().createStatement();
			 ResultSet result = state.executeQuery(reqSQL1);
			 while(result.next()) {
				 lsNomEnseigne = result.getString(1);
		         lsNomSite=result.getString(2);
		         ls_site_enseigne=lsNomEnseigne+" - "+lsNomSite;
		         list_nom_enseigne.add(ls_site_enseigne);
	       	 }
	         
	         result.close();
	         state.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return list_nom_enseigne;
	}
	public static ArrayList<String> récup_nom_enseignes(String pgNom) throws SQLException {
		String reqSQL1="SELECT e.NomEnseigne "
				+ "FROM enseigne AS e,pro_gestionnaire AS pg,site_creche AS sc "
				+ "WHERE e.NumGestionnaire=pg.IdPro AND sc.Id_Enseigne=e.IdEnseigne AND pg.Nom='"+pgNom+"';";
		
		
		String lsNomEnseigne = "";
		list_nom_enseigne=new ArrayList<String>();
		try {
			Statement state = BDD_Connexion.load_database().createStatement();
			 ResultSet result = state.executeQuery(reqSQL1);
			 if(result.next()) {
				 lsNomEnseigne = result.getString(1);
		         list_nom_enseigne.add(lsNomEnseigne);
	       	 }
	         
	         result.close();
	         state.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return list_nom_enseigne;
	}
	
	public static ArrayList<String> récupPrénom(String pgNom,String pgMdp) {
		String reqSQL=
				  "SELECT pg.Prénom"
				+ "FROM pro_gestionnaire AS pg"
				+ "WHERE pg.Nom="+pgNom+" AND pg.Mdp="+pgMdp+";";
		String lsPrénom="";
		ArrayList<String> list_prenom=new ArrayList<String>();
		try {
			Statement state = BDD_Connexion.load_database().createStatement();
			ResultSet result = state.executeQuery(reqSQL);
			if(result.next()) {
				lsPrénom=result.getString(1);
				list_prenom.add(lsPrénom);
			}
			result.close();
			state.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return list_prenom;
	}
	
	public static Gestionnaire getGestionnaire() {
		return gest;
	}
}
