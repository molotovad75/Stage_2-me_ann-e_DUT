package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Gestionnaire;
import jdbc.Enregistrer_demande_gestionnaire;

public class Demande_inscription_pro extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gestionnaire gest=new Gestionnaire();
		gest.setNom(req.getParameter("Nom"));
		gest.setPrénom(req.getParameter("Prénom"));
		gest.setEmail(req.getParameter("email_pro"));
		gest.setTéléphone(req.getParameter("tel_pro"));
		gest.setMessage(req.getParameter("message"));
		
		Enregistrer_demande_gestionnaire.ajouter_demande_gestionnaire(gest);
//		req.setAttribute("", );
		this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(req, resp);
	}
}
