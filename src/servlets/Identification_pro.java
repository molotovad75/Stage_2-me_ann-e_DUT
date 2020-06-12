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
		boolean resultat = false;
		try {
			BDD_Connexion.load_database();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		Gestionnaire gest = new Gestionnaire();
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
				gest.setPr�nom(req.getParameter(""));
				this.getServletContext().getRequestDispatcher("/WEB-INF/../Partie_Pro-gestionnaire/Espace_gestionnaire.jsp").forward(req, resp);
			}
		} catch (ClassNotFoundException e) {
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
	
//	public static boolean RecupPr�nomPro() {
//		String reqSQL="SELECT pg.Pr�nom FROM pro_gestionnaire AS pg WHERE pg.";
//		
//	}
}
