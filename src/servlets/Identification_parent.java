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
import javax.servlet.http.HttpSession;

import bean.Parent;
import jdbc.BDD_Connexion;

public class Identification_parent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Parent parent=null;
	private static HttpSession session=null;
	private static HttpServletRequest req=null;
	private static ArrayList<String> list_nom_crèches=null;
	private static ArrayList<Integer> list_phr_idCrèche=null;
	
	public static Parent getParent_connecté() {
		return parent;
	}
	public static HttpSession getSession() {
		return session;
	}
	public static HttpServletRequest getReq() {
		return req;
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean resultat = false;
		try {
			BDD_Connexion.load_database();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		parent=new Parent();
		parent.setNom(req.getParameter("Id_parent"));
		parent.setMdp(req.getParameter("mdp_parent"));
		
		try {
			resultat=vérifier_comtpe_parent(parent,req,resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat==false) {
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(req, resp);
		}else if (resultat==true) {
			Nom_site_crèche_parent_habilités(req,parent.getNom(),parent.getMdp());
//			for (int i = 0; i < Nom_site_crèche_parent_habilités(req).size(); i++) {
//				req.setAttribute("Noms_site_crèche_parent_habilités"+i, Nom_site_crèche_parent_habilités(req).get(i));
//			}
			
			req.setAttribute("Noms_site_crèche_parent_habilités", Nom_site_crèche_parent_habilités(req,parent.getNom(),parent.getMdp()));
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_parents/Esapce_parent.jsp").forward(req, resp);
		}
	}


	public static boolean vérifier_comtpe_parent(Parent parent, HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		String reqSQLvérifier_comtpe_parent=
				  "SELECT p.NomParent_référence, p.Mdp "
				+ "FROM parents AS p "
				+ "WHERE p.NomParent_référence=? "
				+ "AND p.Mdp=?; ";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLvérifier_comtpe_parent);
			ps.setString(1, parent.getNom());
			ps.setString(2, parent.getMdp());
			resultat=ps.executeQuery();
			
			
		} catch (Exception e) {
			e.getMessage();
		}
		session=req.getSession();
		session.setAttribute("Nom", parent.getNom());
		session.setAttribute("Mdp", parent.getMdp()); // A changer
		session.setAttribute("NomsEnseigne", trouver_Crèche_NomCrèche());
		if (!resultat.next()) {
			String message_erreur_auth_parent="Authentification échouée, veuillez rééssayer !";
			req.setAttribute("message_erreur_auth_parent", message_erreur_auth_parent);
			return false;
		}else {
			return true;
		}
	}
	
	
	/****************************************************************************/
	public static int trouver_parent_IdParent() {
		String reqSQLtrouver_parent_IdParent=
				  "SELECT p.IdParent  "
				+ "FROM parents AS p "
				+ "WHERE p.NomParent_référence="+parent.getNom()+"  "
				+ "AND p.Mdp="+parent.getMdp()+"; ";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int IdParent=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_parent_IdParent);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				IdParent=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return IdParent;
	}
	
	public static int trouver_phr_IdCrèche() {
		String reqSQLtrouver_phr_IdCrèche=
				  "SELECT phr.IdCrèche "
				+ "FROM `parents_habilite_reservation` AS phr "
				+ "WHERE phr.IdParent="+trouver_parent_IdParent()+"; ";
		
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int IdCrèche=0;
		
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_phr_IdCrèche);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				IdCrèche=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return IdCrèche;
	}
	/****************************************************************************/
	
	public static ArrayList<String> trouver_Crèche_NomCrèche() {
		String reqSQLtrouver_NomCrèche=
				  "SELECT sc.`Nom Site` "
				+ "FROM `site_creche` AS sc  "
				+ "WHERE sc.IdCrèche="+trouver_phr_IdCrèche()+"; ";
		
		PreparedStatement ps=null;
		ResultSet resultat=null;
		String NomCrèche="";
		ArrayList<String> liste_Noms_Crèches_trouvés_pour_parent=new ArrayList<String>();
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_NomCrèche);
			resultat=ps.executeQuery();
			while (resultat.next()) {
				NomCrèche=resultat.getString(1);
				liste_Noms_Crèches_trouvés_pour_parent.add(NomCrèche);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return liste_Noms_Crèches_trouvés_pour_parent;
	}
	
	
	
	/****************************Pour récupérer le nom du site auquel le parent est habilité*************************************/
	public static ArrayList<String> Nom_site_crèche_parent_habilités(HttpServletRequest req,String nomParent,String mdpParent) {
		String reqSQLNom_site_crèche=
				  "SELECT sc.`Nom Site` "
				+ "FROM site_creche AS sc "
				+ "WHERE sc.IdCrèche='"+récupérer_Id_Crèche(req,nomParent, mdpParent)+"'; ";
		list_nom_crèches=new ArrayList<String>();
		String nom_site_parent_habilités="";
		try {
			Statement state =BDD_Connexion.load_database().createStatement();
			ResultSet rs=state.executeQuery(reqSQLNom_site_crèche);
			
			
			while(rs.next()) {
				nom_site_parent_habilités=rs.getString(1);
				list_nom_crèches.add(nom_site_parent_habilités);
			}
			rs.close();
		    state.close();
		} catch (Exception e) {
			e.getMessage();
		}
		
		
		
		return list_nom_crèches;
	}
	
	public static ArrayList<Integer> récupérer_Id_Crèche(HttpServletRequest req,String nomParent,String mdpParent) {
		String reqSQLrécupérer_Id_Crèche=
				  "SELECT phr.IdCrèche "
				+ "FROM parents_habilite_reservation AS phr "
				+ "WHERE phr.IdParent='"+récupérer_Id_parent_habilité(req,nomParent,mdpParent)+"'; ";
		ResultSet rs=null;
		list_phr_idCrèche=new ArrayList<Integer>();
		int phr_idCrèche=0;
		try {
//			ps=BDD_Connexion.getConn().prepareStatement(reqSQLrécupérer_Id_Crèche);
//			ps.setInt(1, récupérer_Id_parent_habilité(req));
//			rs=ps.executeQuery();
			Statement state =BDD_Connexion.load_database().createStatement();
			rs=state.executeQuery(reqSQLrécupérer_Id_Crèche);
			while (rs.next()) {
				phr_idCrèche=rs.getInt(1);
				list_phr_idCrèche.add(phr_idCrèche);
			}
			state.close();
			rs.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return list_phr_idCrèche;
	}
	
	public static int récupérer_Id_parent_habilité(HttpServletRequest req,String nomParent,String mdpParent) {
		String reqSQLrécupérer_Id_parent_habilité=
				"SELECT phr.IdParent "
			  + "FROM  parents_habilite_reservation AS phr, parents AS p "
			  + "WHERE phr.IdParent="+trouverIdParent(nomParent,mdpParent) +"; ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		int phr_idparent_habilités=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLrécupérer_Id_parent_habilité);
//			ps.setString(1, (String) req.getAttribute("Nom"));
//			ps.setString(2, (String) req.getAttribute("Mdp"));
			rs=ps.executeQuery();
			if (rs.next()) {
				phr_idparent_habilités=rs.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return phr_idparent_habilités;
	}
	
	public static int trouverIdParent(String nomParent,String mdpParent) {
		String reqSQLtrouverIdParent=
				  "SELECT p.IdParent  "
				+ "FROM  parents AS p "
				+ "WHERE  p.NomParent_référence="+nomParent+" "
				+ "AND p.Mdp="+mdpParent+"; ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		int IdParent=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouverIdParent);
			rs=ps.executeQuery();
			if (rs.next()) {
				IdParent=rs.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return IdParent;
	}
	
	/******** FIN ****************************************************************************************************/
}
