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
				resultat=v�rifier_comtpe_pro(gest,req,resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (resultat==false) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(req, resp);
			}else if (resultat==true) {
				gest.setPr�nom(req.getParameter("Pr�nom"));
				
				r�cup_infos_sites_gestionnaires(gest.getNom_user());
				req.setAttribute("NomEnseigne_site", r�cup_infos_sites_gestionnaires(gest.getNom_user()));
				
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
			req.setAttribute("Prenom", gest.getPr�nom());
			return true;
		}
	}
	
	
	
	public static ArrayList<String> r�cup_infos_sites_gestionnaires(String pgNom) throws SQLException {
		String reqSQL1="SELECT e.NomEnseigne, sc.`Nom Site` "
				+ "FROM enseigne AS e,pro_gestionnaire AS pg,site_creche AS sc "
				+ "WHERE e.NumGestionnaire=pg.IdPro AND sc.Id_Enseigne=e.IdEnseigne AND pg.Nom='"+pgNom+"';";
		
		
		String lsNomEnseigne = "";String lsNomSite = ""; String ls_infos="";
		list_nom_enseigne=new ArrayList<String>();
		try {
			Statement state = BDD_Connexion.load_database().createStatement();
			 ResultSet result = state.executeQuery(reqSQL1);
			 while(  result.next()) {
				 lsNomEnseigne = result.getString(1);
		         lsNomSite=result.getString(2);
		         ls_infos=lsNomEnseigne+" - "+lsNomSite;
		         list_nom_enseigne.add(ls_infos);
	       	 }
	         
	         result.close();
	         state.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return list_nom_enseigne;
		
//		PreparedStatement ps=null;
//		ResultSet resultat=null;
//		
//		try {
//			ps=BDD_Connexion.getConn().prepareStatement(reqSQL1);
//			ps.setString(1, Identification_pro.getGestionnaire().getNom_user());
//			resultat=ps.executeQuery();
//			
//		} catch (Exception e) {
//			e.getMessage();
//		}
//		resultMeta = resultat.getMetaData();
//		list_nom_enseigne=new ArrayList<String>();
//		if (resultat.next()) {
//			for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
//				list_nom_enseigne.add(resultat.getObject(i).toString());
//			}			
//		}
//		resultat.close();
//		return list_nom_enseigne;
	}
	
	
	public static Gestionnaire getGestionnaire() {
		return gest;
	}
}
