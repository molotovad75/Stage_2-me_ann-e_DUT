package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class D�connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "../../Plateforme_web_B_and_B/index";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* R�cup�ration et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();

        /* Redirection vers la page d'indexs */
        response.sendRedirect( VUE );
    }
}
