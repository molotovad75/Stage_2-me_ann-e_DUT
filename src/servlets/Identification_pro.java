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
	private static String Nom_gestionnaire="";
	//private static String lsNomSite = "";
	
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
				req.setAttribute("NomEnseigne_site", récup_infos_sites_gestionnaires(gest.getNom_user()));
				req.setAttribute("NomEnseigne", récup_nom_enseignes(gest.getNom_user()));
				req.setAttribute("Nom", gest.getNom_user());
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
			Nom_gestionnaire=resultat.getString(1);
			return true;
		}
	}
	
	
	
	
	public static ArrayList<String> récup_infos_sites_gestionnaires(String pgNom) throws SQLException {
		String reqSQL1="SELECT sc.`Nom Site` "
				+ "FROM enseigne AS e,pro_gestionnaire AS pg,site_creche AS sc "
				+ "WHERE e.NumGestionnaire=pg.IdPro AND sc.Id_Enseigne=e.IdEnseigne AND pg.Nom='"+pgNom+"';";
		
		
		String lsNomSite = "";
		list_nom_enseigne=new ArrayList<String>();
		try {
			Statement state = BDD_Connexion.load_database().createStatement();
			 ResultSet result = state.executeQuery(reqSQL1);
			 while(result.next()) {
			//	 lsNomEnseigne = result.getString(1);
		         lsNomSite=result.getString(1);
//		         ls_site_enseigne=lsNomEnseigne+" - "+lsNomSite;
		        //ls_site_enseigne=lsNomSite;
		       //  Identification_pro.lsNomSite=lsNomSite;
		         list_nom_enseigne.add(lsNomSite);
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
	
//	LES 4 REQUETES DE TYPE SELECT SUIVANTES SONT POUR RECUPERER UNE CLE ETRANGERE ASSOCIE DE LOIN A UN GESTIONNAIRE
	public static int récupIdGestionnaire(Gestionnaire gest) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT pg.IdPro FROM pro_gestionnaire AS pg WHERE pg.Nom=? AND pg.Mdp=?;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int Id_gestionnaire=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			ps.setString(1, Nom_gestionnaire);
			ps.setString(2, gest.getMdp());
			resultat=ps.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat.next()) {
			Id_gestionnaire=resultat.getInt(1);
		}
		return Id_gestionnaire;
	}
	
	public static int récupIdEnseigne(Gestionnaire gest) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT e.IdEnseigne FROM enseigne AS e WHERE e.NumGestionnaire="+récupIdGestionnaire(gest)+";";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int Id_Enseigne=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			resultat=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat.next()) {
			Id_Enseigne=resultat.getInt(1);
		}
		return Id_Enseigne;
	}
	
	public static int récupIdCreche(Gestionnaire gest) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT sc.IdCrèche FROM site_creche AS sc WHERE sc.Id_Enseigne="+récupIdEnseigne(gest)+";";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int IdCrèche=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			resultat=ps.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat.next()) {
			IdCrèche=resultat.getInt(1);
		}
		return IdCrèche;
	}
	
	public static int récupIdSite_horaire(Gestionnaire gest) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT hcs.IdSite FROM horaires_crenaux_sites AS hcs WHERE hcs.IdSite="+récupIdCreche(gest)+";";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int IdSite=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			resultat=ps.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat.next()) {
			IdSite=resultat.getInt(1);
		}
		return IdSite;
	}
	
	
	
	public static String récupHoraire_jour(Gestionnaire gest, int num_jour,String nom_site) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT hcs.Horaires_journée, hcs.Horaires_fin_journée " + 
				"FROM horaires_crenaux_sites AS hcs, ouverture_semaine AS os " + 
				"WHERE hcs.Id_jour_semaine="+num_jour+" AND os.IdSiteCreche="+récupIdSite_horaire(gest)+" "
						+ "AND hcs.IdSite="+récupId_site(nom_site)+" ;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		String Horaires_journées="", Horaires_fin_journée="",Horaire_final="";
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			resultat=ps.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat.next()) {
			Horaires_journées=resultat.getString(1);
			Horaires_fin_journée=resultat.getString(2);
			if (Horaires_journées.equals("fermé")==true) {
				Horaire_final=Horaires_journées;
			}else {
				Horaire_final=Horaires_journées+" à "+Horaires_fin_journée;
			}
			
		}
		return Horaire_final;
	}
	
	public static int récupId_site(String nom_site) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT sc.IdCrèche "
				+ "FROM `site_creche` AS sc "
				+ "WHERE sc.`Nom Site`=\""+nom_site+"\";";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int Idcrèche=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			resultat=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat.next()) {
			Idcrèche=resultat.getInt(1);
		}
		return Idcrèche;
	}
//	FIN DES 4 REQUETES DE TYPE SELECT **************************************************************************************
	
	
	public static Gestionnaire getGestionnaire() {
		return gest;
	}

//	public static int getIdPro() {
//		return IdPro;
//	}
}
