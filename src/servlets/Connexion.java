package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Gestionnaire;
import jdbc.Connexion_pro;

public class Connexion extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gestionnaire gestionnaire=new Gestionnaire();
		gestionnaire.setNom_user(request.getParameter("Id_pro"));
		gestionnaire.setMdp(request.getParameter("mdp_parent"));
		request.setAttribute("gestionnaire", Connexion_pro.vérification_connexion_pro(gestionnaire));
		
	}
}
