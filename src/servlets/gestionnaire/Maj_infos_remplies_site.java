package servlets.gestionnaire;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.BDD_Connexion;

public class Maj_infos_remplies_site extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		String reqSQLMAJINFOS_PAGE=
				  "UPDATE site_creche AS sc "
				+ "SET sc.Numéro=? , "
				+ "sc.Voie=? , "
				+ "sc.Complément=? ,  "
				+ "sc.CP=? , "
				+ "sc.Ville=?, "
				+ "sc.Tel_contact=? , "
				+ "sc.Mail_contact=? , "
				+ "sc.Nom_contact=? , "
				+ "sc.Type=? , "
				+ "sc.Capacité=?  "
				+ "WHERE sc.`Nom Site`=? ;";
		PreparedStatement ps=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQLMAJINFOS_PAGE);
			ps.setString(1, req.getParameter("numéro"));
			ps.setString(2, req.getParameter("voie"));
			ps.setString(3, req.getParameter("complément"));
			ps.setString(4, req.getParameter("CP"));
			ps.setString(5, req.getParameter("ville"));
			ps.setString(6, req.getParameter("telephone"));
			ps.setString(7, req.getParameter("mail"));
			ps.setString(8, req.getParameter("nom_contact"));
			ps.setString(9, req.getParameter("type"));
			ps.setString(10, req.getParameter("nb_places"));
			ps.setString(11, Choix_action.getnom_choix_site());
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/MAJ_infos_sites_crénaux.jsp").forward(req, resp);
	}
}
