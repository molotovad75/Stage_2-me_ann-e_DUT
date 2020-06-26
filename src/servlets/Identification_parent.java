package servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public static Parent getParent_connecté() {
		return parent;
	}
	public static HttpSession getSession() {
		return session;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (resultat==false) {
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(req, resp);
		}else if (resultat==true) {
			
			session=req.getSession();
			session.setAttribute("Nom", parent.getNom());
			session.setAttribute("Mdp", parent.getMdp()); // A changer
			session.setAttribute("NomsEnseigne", trouver_Crèche_NomCrèche());
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_parents/Compte_parent.jsp").forward(req, resp);
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
}
