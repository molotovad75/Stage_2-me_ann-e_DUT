package servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.Parent;
import jdbc.BDD_Connexion;

public class Demande_inscription_parent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BDD_Connexion.load_database();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		Parent parent=new Parent();
		parent.setNom(req.getParameter("Nom"));
		parent.setPrénom(req.getParameter("Prénom"));
		parent.setEmail(req.getParameter("email_parent"));
		parent.setTéléphone(req.getParameter("tel_parent"));
		parent.setMessage(req.getParameter("message"));
		if (parent.getMessage().equals("")==true) {
			parent.setMessage("Bonjour, je souahite en savoir plus sur la solution OuiCrèches, merci de me recontacter.");
		}
		try {
			ajouter_demande_parent(parent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(req, resp);
	

	}
	
	public static void ajouter_demande_parent(Parent parent) throws ClassNotFoundException {
		String reqSQL="INSERT INTO demande_inscription_parent VALUES(NULL,?,?,?,?,?,CURDATE());";
		PreparedStatement ps=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			ps.setString(1, parent.getNom());
			ps.setString(2, parent.getPrénom());
			ps.setString(3, parent.getEmail());
			ps.setString(4, parent.getTéléphone());
			ps.setString(5, parent.getMessage());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
