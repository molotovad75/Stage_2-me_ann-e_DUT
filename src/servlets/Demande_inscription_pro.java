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

public class Demande_inscription_pro extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			BDD_Connexion.load_database();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		Gestionnaire gest=new Gestionnaire();
		gest.setNom(req.getParameter("Nom"));
		gest.setPrénom(req.getParameter("Prénom"));
		gest.setEmail(req.getParameter("email_pro"));
		gest.setTéléphone(req.getParameter("tel_pro"));
		gest.setMessage(req.getParameter("message"));
		
		try {
			ajouter_demande_gestionnaire(gest,req,resp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(req, resp);
	}
	
	public static void ajouter_demande_gestionnaire(Gestionnaire gestionnaire,HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException {
		boolean res=false;
		String reqSQL="INSERT INTO demande_inscription_gestionnaire VALUES(NULL,?,?,?,?,?,CURDATE());";
		PreparedStatement ps=null;
		
//		PreparedStatement ps2=null;
//		String reqSQL2="SELECT pg.Email FROM pro_gestionnaire AS pg;";
//		ResultSet resultat=null;
		try {
			
			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
			ps.setString(1, gestionnaire.getNom());
			ps.setString(2, gestionnaire.getPrénom());
			ps.setString(3, gestionnaire.getEmail());
			ps.setString(4, gestionnaire.getTéléphone());
			ps.setString(5, gestionnaire.getMessage());
//			res=vérif_pas_meme_adresse_mail(gestionnaire);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
//	public static boolean vérif_pas_meme_adresse_mail(Gestionnaire gest) throws ClassNotFoundException, SQLException {
//		boolean res=false;
//		String reqSQL="SELECT pg.Email FROM pro_gestionnaire AS pg;";
//		PreparedStatement ps=null;
//		ResultSet resultat=null;
//		
//		try {
//			ps=BDD_Connexion.getConn().prepareStatement(reqSQL);
//			resultat=ps.executeQuery();			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		while(resultat.next()) {
//			String email_gestionnaire=resultat.getString("pg.Email");
//			if (email_gestionnaire) {
//				
//			}
//		}
//		return res;
//	}
	
	
	
}
