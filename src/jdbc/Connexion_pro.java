package jdbc;

import java.sql.PreparedStatement;

public class Connexion_pro {
	public static void vérification_connexion_pro(){
		String requeteSQL="SELECT pg.Nom,pg.Mdp FROM pro_gestionnaire AS pg WHERE "++";";
		try {
			BDD_Connexion.load_database();
			PreparedStatement preparedStatement=BDD_Connexion.getConn().prepareStatement(requeteSQL);
//			String paramEmail = request.getParameter( "email" );
//			String paramMotDePasse = request.getParameter( "motdepasse" );
//			String paramNom = request.getParameter( "nom" );
		} catch (Exception e) {
			
		}
		
		
	}
}
