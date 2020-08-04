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
	private static ArrayList<String> list_nom_cr�ches=null;
	private static ArrayList<Integer> list_phr_idCr�che=null;
	
	public static Parent getParent_connect�() {
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
			resultat=v�rifier_comtpe_parent(parent,req,resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat==false) {
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(req, resp);
		}else if (resultat==true) {
			Nom_site_cr�che_parent_habilit�s(req,parent.getNom(),parent.getMdp());
//			for (int i = 0; i < Nom_site_cr�che_parent_habilit�s(req).size(); i++) {
//				req.setAttribute("Noms_site_cr�che_parent_habilit�s"+i, Nom_site_cr�che_parent_habilit�s(req).get(i));
//			}
			
			req.setAttribute("Noms_site_cr�che_parent_habilit�s", Nom_site_cr�che_parent_habilit�s(req,parent.getNom(),parent.getMdp()));
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_parents/Esapce_parent.jsp").forward(req, resp);
		}
	}


	public static boolean v�rifier_comtpe_parent(Parent parent, HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		String reqSQLv�rifier_comtpe_parent=
				  "SELECT p.NomParent_r�f�rence, p.Mdp "
				+ "FROM parents AS p "
				+ "WHERE p.NomParent_r�f�rence=? "
				+ "AND p.Mdp=?; ";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLv�rifier_comtpe_parent);
			ps.setString(1, parent.getNom());
			ps.setString(2, parent.getMdp());
			resultat=ps.executeQuery();
			
			
		} catch (Exception e) {
			e.getMessage();
		}
		session=req.getSession();
		session.setAttribute("Nom", parent.getNom());
		session.setAttribute("Mdp", parent.getMdp()); // A changer
		session.setAttribute("NomsEnseigne", trouver_Cr�che_NomCr�che());
		if (!resultat.next()) {
			String message_erreur_auth_parent="Authentification �chou�e, veuillez r��ssayer !";
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
				+ "WHERE p.NomParent_r�f�rence="+parent.getNom()+"  "
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
	
	public static int trouver_phr_IdCr�che() {
		String reqSQLtrouver_phr_IdCr�che=
				  "SELECT phr.IdCr�che "
				+ "FROM `parents_habilite_reservation` AS phr "
				+ "WHERE phr.IdParent="+trouver_parent_IdParent()+"; ";
		
		PreparedStatement ps=null;
		ResultSet resultat=null;
		int IdCr�che=0;
		
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_phr_IdCr�che);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				IdCr�che=resultat.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return IdCr�che;
	}
	/****************************************************************************/
	
	public static ArrayList<String> trouver_Cr�che_NomCr�che() {
		String reqSQLtrouver_NomCr�che=
				  "SELECT sc.`Nom Site` "
				+ "FROM `site_creche` AS sc  "
				+ "WHERE sc.IdCr�che="+trouver_phr_IdCr�che()+"; ";
		
		PreparedStatement ps=null;
		ResultSet resultat=null;
		String NomCr�che="";
		ArrayList<String> liste_Noms_Cr�ches_trouv�s_pour_parent=new ArrayList<String>();
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLtrouver_NomCr�che);
			resultat=ps.executeQuery();
			while (resultat.next()) {
				NomCr�che=resultat.getString(1);
				liste_Noms_Cr�ches_trouv�s_pour_parent.add(NomCr�che);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return liste_Noms_Cr�ches_trouv�s_pour_parent;
	}
	
	
	
	/****************************Pour r�cup�rer le nom du site auquel le parent est habilit�*************************************/
	public static ArrayList<String> Nom_site_cr�che_parent_habilit�s(HttpServletRequest req,String nomParent,String mdpParent) {
		String reqSQLNom_site_cr�che=
				  "SELECT sc.`Nom Site` "
				+ "FROM site_creche AS sc "
				+ "WHERE sc.IdCr�che='"+r�cup�rer_Id_Cr�che(req,nomParent, mdpParent)+"'; ";
		list_nom_cr�ches=new ArrayList<String>();
		String nom_site_parent_habilit�s="";
		try {
			Statement state =BDD_Connexion.load_database().createStatement();
			ResultSet rs=state.executeQuery(reqSQLNom_site_cr�che);
			
			
			while(rs.next()) {
				nom_site_parent_habilit�s=rs.getString(1);
				list_nom_cr�ches.add(nom_site_parent_habilit�s);
			}
			rs.close();
		    state.close();
		} catch (Exception e) {
			e.getMessage();
		}
		
		
		
		return list_nom_cr�ches;
	}
	
	public static ArrayList<Integer> r�cup�rer_Id_Cr�che(HttpServletRequest req,String nomParent,String mdpParent) {
		String reqSQLr�cup�rer_Id_Cr�che=
				  "SELECT phr.IdCr�che "
				+ "FROM parents_habilite_reservation AS phr "
				+ "WHERE phr.IdParent='"+r�cup�rer_Id_parent_habilit�(req,nomParent,mdpParent)+"'; ";
		ResultSet rs=null;
		list_phr_idCr�che=new ArrayList<Integer>();
		int phr_idCr�che=0;
		try {
//			ps=BDD_Connexion.getConn().prepareStatement(reqSQLr�cup�rer_Id_Cr�che);
//			ps.setInt(1, r�cup�rer_Id_parent_habilit�(req));
//			rs=ps.executeQuery();
			Statement state =BDD_Connexion.load_database().createStatement();
			rs=state.executeQuery(reqSQLr�cup�rer_Id_Cr�che);
			while (rs.next()) {
				phr_idCr�che=rs.getInt(1);
				list_phr_idCr�che.add(phr_idCr�che);
			}
			state.close();
			rs.close();
		} catch (Exception e) {
			e.getMessage();
		}
		return list_phr_idCr�che;
	}
	
	public static int r�cup�rer_Id_parent_habilit�(HttpServletRequest req,String nomParent,String mdpParent) {
		String reqSQLr�cup�rer_Id_parent_habilit�=
				"SELECT phr.IdParent "
			  + "FROM  parents_habilite_reservation AS phr, parents AS p "
			  + "WHERE phr.IdParent="+trouverIdParent(nomParent,mdpParent) +"; ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		int phr_idparent_habilit�s=0;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLr�cup�rer_Id_parent_habilit�);
//			ps.setString(1, (String) req.getAttribute("Nom"));
//			ps.setString(2, (String) req.getAttribute("Mdp"));
			rs=ps.executeQuery();
			if (rs.next()) {
				phr_idparent_habilit�s=rs.getInt(1);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return phr_idparent_habilit�s;
	}
	
	public static int trouverIdParent(String nomParent,String mdpParent) {
		String reqSQLtrouverIdParent=
				  "SELECT p.IdParent  "
				+ "FROM  parents AS p "
				+ "WHERE  p.NomParent_r�f�rence="+nomParent+" "
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
