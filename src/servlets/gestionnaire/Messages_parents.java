package servlets.gestionnaire;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.BDD_Connexion;
import servlets.Identification_pro;

public class Messages_parents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PreparedStatement ps=null;
		ResultSet resultat=null;
		ArrayList<String> message=new ArrayList<String>();
		try {
				
			String reqSQL3="SELECT dip.Message "
					+ "FROM `demande_inscription_parent` AS dip "
					+ "WHERE CONCAT(dip.Nom,' ', dip.Prénom) LIKE '"+req.getParameter("choix_parent")+"';";
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL3);
			resultat=ps.executeQuery();
			if (resultat.next()) {
				message.add(resultat.getString(1));
			}
			req.setAttribute("Message", message);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("Nom_site", Choix_action.getnom_choix_site());
		req.setAttribute("nom_prenom", Choix_action.getNom_prenom());
		req.setAttribute("Nom_prénom_parent_selectionné", req.getParameter("choix_parent"));
		this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Habilitation_parents.jsp").forward(req, resp);
	}

}
