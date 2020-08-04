package servlets.parents;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.BDD_Connexion;
import servlets.Identification_parent;

public class Mettre_à_jour_mdp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean resultat = false; 
		String ancien_mdp=req.getParameter("ancien_mdp");
		String nouveau_mdp=req.getParameter("nouveau_mdp");
		
	
			try {
				BDD_Connexion.load_database();
				resultat=vérification_ancien_mdp(ancien_mdp);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		// RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp");
		if (resultat==true) {
					
			String confirmation_changement_mdp="Votre nouveau mot de passe à été mis à jour !";
			req.setAttribute("confirmation_changement_mdp", confirmation_changement_mdp);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_parents/Compte_parent.jsp").forward(req, resp);
//			this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(req, resp);
//			resp.sendRedirect(req.getContextPath() + "/identification_parent");
			mettre_àjour_mdp(nouveau_mdp);
			
		}else if (resultat==false){
			String message_erreur_ancien_mdp="L'ancien mot de passe ne correspond pas à celui que vous avez entré !";
			req.setAttribute("message_erreur_ancien_mdp", message_erreur_ancien_mdp);
			this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_parents/Compte_parent.jsp").forward(req, resp);
//			this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(req, resp);
//			resp.sendRedirect(req.getContextPath() + "/identification_parent");
		}
	//	/mettre_a_jour_mdp_parents
		
	}
	
	public static boolean vérification_ancien_mdp(String ancien_mdp) throws SQLException {
		
		String reqSQLvérification_ancien_mdp=
				"SELECT p.Mdp "
			  + "FROM parents AS p "
			  + "WHERE p.Mdp=? "
			  + "AND p.NomParent_référence=? ";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLvérification_ancien_mdp);
			ps.setString(1, ancien_mdp);
			ps.setString(2, (String) Identification_parent.getSession().getAttribute("Nom"));
			resultat=ps.executeQuery();
			
		} catch (Exception e) {
			e.getMessage();
		}
		if (!resultat.next()) {
			 return false;
		}else{
			return true;
		}
		
		
	}
	
	public static void mettre_àjour_mdp(String nouveau_mdp) {
		String reqSQL_mettre_àjour_mdp=
				  "UPDATE parents AS p "
				+ "SET p.Mdp=? "
				+ "WHERE p.NomParent_référence=? "
				+ "AND p.Mdp=? ;";
		PreparedStatement ps=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL_mettre_àjour_mdp);
			ps.setString(1, nouveau_mdp);
			ps.setString(2, (String) Identification_parent.getSession().getAttribute("Nom"));
			ps.setString(3, (String) Identification_parent.getSession().getAttribute("Mdp"));
			ps.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
