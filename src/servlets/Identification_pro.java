package servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Gestionnaire;
import jdbc.BDD_Connexion;

public class Identification_pro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BDD_Connexion.load_database();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		Gestionnaire gest = new Gestionnaire();
		gest.setNom_user(req.getParameter("Id_pro"));
		gest.setMdp(req.getParameter("mdp_parent"));
		
		try {
			vérifier_comtpe_parent(gest,req,resp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/../index.jsp").forward(req, resp);
	

	}
	
	public static void vérifier_comtpe_parent(Gestionnaire gest,HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException {
		String reqSQL="SELECT pg.Nom, pg.Mdp FROM pro_gestionnaire AS pg WHERE pg.Nom=? AND pg.Mdp=?;";
		PreparedStatement ps=null;
		ResultSet resultat=null;
		try {
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			ps.setString(1, gest.getNom_user());
			ps.setString(2, gest.getMdp());
			resultat=ps.executeQuery();
			
			if (!resultat.next()) {
				//Dire qu'il y a une erreur d'authentification
				String mess_erreur_authentification="Authentification échouée, veuillez rééssayer !";
				req.setAttribute("erreur_auth", mess_erreur_authentification);
			}else {
				//Connection authentifié
				String mess_authentification="Authentification réussie !";
				req.setAttribute("auth", mess_authentification);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
