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
				resultat=v�rifier_comtpe_pro(gest,req,resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (resultat==false) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(req, resp);
			}else if (resultat==true) {
				r�cup_infos_sites_gestionnaires(gest.getNom_user());
				r�cup_nom_enseignes(gest.getNom_user());
				req.setAttribute("NomEnseigne_site", r�cup_infos_sites_gestionnaires(gest.getNom_user()));
				req.setAttribute("NomEnseigne", r�cup_nom_enseignes(gest.getNom_user()));
				req.setAttribute("Nom", gest.getNom_user());
				this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Espace_gestionnaire.jsp").forward(req, resp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean v�rifier_comtpe_pro(Gestionnaire gest,HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, SQLException {
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
			String mess_erreur_authentification="Authentification �chou�e, veuillez r��ssayer !";
			req.setAttribute("auth", mess_erreur_authentification);
			return false;
		}else {
			//Connection authentifi�
			String mess_authentification="Authentification r�ussie !";
			req.setAttribute("auth", mess_authentification);
			req.setAttribute("Nom", gest.getNom_user());
			Nom_gestionnaire=resultat.getString(1);
			return true;
		}
	}
	
	
	
	
	public static ArrayList<String> r�cup_infos_sites_gestionnaires(String pgNom) throws SQLException {
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
	public static ArrayList<String> r�cup_nom_enseignes(String pgNom) throws SQLException {
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
	
	public static ArrayList<String> r�cupPr�nom(String pgNom,String pgMdp) {
		String reqSQL=
				  "SELECT pg.Pr�nom"
				+ "FROM pro_gestionnaire AS pg"
				+ "WHERE pg.Nom="+pgNom+" AND pg.Mdp="+pgMdp+";";
		String lsPr�nom="";
		ArrayList<String> list_prenom=new ArrayList<String>();
		try {
			Statement state = BDD_Connexion.load_database().createStatement();
			ResultSet result = state.executeQuery(reqSQL);
			if(result.next()) {
				lsPr�nom=result.getString(1);
				list_prenom.add(lsPr�nom);
			}
			result.close();
			state.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return list_prenom;
	}
	
//	LES 4 REQUETES DE TYPE SELECT SUIVANTES SONT POUR RECUPERER UNE CLE ETRANGERE ASSOCIE DE LOIN A UN GESTIONNAIRE
	public static int r�cupIdGestionnaire(Gestionnaire gest) throws ClassNotFoundException, SQLException {
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
	
	public static int r�cupIdEnseigne(Gestionnaire gest) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT e.IdEnseigne FROM enseigne AS e WHERE e.NumGestionnaire="+r�cupIdGestionnaire(gest)+";";
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
	
	public static int r�cupIdCreche(Gestionnaire gest) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT sc.IdCr�che FROM site_creche AS sc WHERE sc.Id_Enseigne="+r�cupIdEnseigne(gest)+";";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int IdCr�che=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			resultat=ps.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat.next()) {
			IdCr�che=resultat.getInt(1);
		}
		return IdCr�che;
	}
	
	public static int r�cupIdSite_horaire(Gestionnaire gest) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT hcs.IdSite FROM horaires_crenaux_sites AS hcs WHERE hcs.IdSite="+r�cupIdCreche(gest)+";";
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
	
	
	
	public static String r�cupHoraire_jour(Gestionnaire gest, int num_jour,String nom_site) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT hcs.Horaires_journ�e, hcs.Horaires_fin_journ�e " + 
				"FROM horaires_crenaux_sites AS hcs, ouverture_semaine AS os " + 
				"WHERE hcs.Id_jour_semaine="+num_jour+" AND os.IdSiteCreche="+r�cupIdSite_horaire(gest)+" "
						+ "AND hcs.IdSite="+r�cupId_site(nom_site)+" ;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		String Horaires_journ�es="", Horaires_fin_journ�e="",Horaire_final="";
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			resultat=ps.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat.next()) {
			Horaires_journ�es=resultat.getString(1);
			Horaires_fin_journ�e=resultat.getString(2);
			if (Horaires_journ�es.equals("ferm�")==true) {
				Horaire_final=Horaires_journ�es;
			}else {
				Horaire_final=Horaires_journ�es+" � "+Horaires_fin_journ�e;
			}
			
		}
		return Horaire_final;
	}
	
	public static int r�cupId_site(String nom_site) throws ClassNotFoundException, SQLException {
		String reqSQL="SELECT sc.IdCr�che "
				+ "FROM `site_creche` AS sc "
				+ "WHERE sc.`Nom Site`=\""+nom_site+"\";";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int Idcr�che=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			resultat=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat.next()) {
			Idcr�che=resultat.getInt(1);
		}
		return Idcr�che;
	}
//	FIN DES 4 REQUETES DE TYPE SELECT **************************************************************************************
	
	
	public static Gestionnaire getGestionnaire() {
		return gest;
	}

//	public static int getIdPro() {
//		return IdPro;
//	}
}
