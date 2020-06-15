package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.BDD_Connexion;

public class Espace_gestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ArrayList<String> list_nom_enseigne=null;
	
	//private static ResultSetMetaData resultMeta = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BDD_Connexion.load_database();
//			récup_infos_sites_gestionnaires(Identification_pro.getGestionnaire().getNom_user());
//			request.setAttribute("NomEnseigne_site", récup_infos_sites_gestionnaires(Identification_pro.getGestionnaire().getNom_user()));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Espace_gestionnaire.jsp").forward(request, response);
	}
	
//	public static String récup_infos_sites_gestionnaires(String pgNom) throws SQLException {
//		String reqSQL1="SELECT e.NomEnseigne, sc.`Nom Site` "
//				+ "FROM enseigne AS e,pro_gestionnaire AS pg,site_creche AS sc "
//				+ "WHERE e.NumGestionnaire=pg.IdPro AND sc.Id_Enseigne=e.IdEnseigne AND pg.Nom='"+pgNom+"';";
//		
//		
//
//	}
	
	public static ArrayList<String> getlist_nom_enseigne() {
		return list_nom_enseigne;
	}
}
	