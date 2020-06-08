package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.xdevapi.Result;

import bean.Gestionnaire;

public class Connexion_pro {
	
	public static Gestionnaire vérification_connexion_pro(Gestionnaire gestionnaire){
		ResultSet result=null;
		try {
			BDD_Connexion.load_database();
			String requeteSQL="SELECT pg.Nom,pg.Mdp FROM pro_gestionnaire AS pg WHERE pg.Nom=? AND pg.Mdp=?;";
			PreparedStatement preparedStatement=BDD_Connexion.getConn().prepareStatement(requeteSQL);
			preparedStatement.setString(1, gestionnaire.getNom_user());
			preparedStatement.setString(2, gestionnaire.getMdp());
			result=preparedStatement.executeQuery();
			
			while (result.next()) {
				String nom_user=result.getString("pg.Nom");
				String mdp=result.getString("pg.Mdp");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		
		return gestionnaire;
	}
}
