package servlets.parents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MAJ_conjoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
	public static void MAJconjoint() {
		String reqSQL="UPDATE `conjoint` AS c "
				+ "SET c. "
				+ "WHERE ";
	}
}
