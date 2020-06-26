package servlets.parents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.scene.control.Hyperlink;

public class Choix_compte_parent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nom_lien_partie_moi=req.getParameter("moi");
		String nom_lien_pour_conjoint=req.getParameter("mon_conjoint");
		String nom_lien_pour_enfants_connus=req.getParameter("mes_enfants");
		String nom_lien_pour_mdp=req.getParameter("modifier_mdp");
		
		
	}
}
