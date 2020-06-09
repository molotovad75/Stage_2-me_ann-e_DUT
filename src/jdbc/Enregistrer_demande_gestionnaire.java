package jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.Gestionnaire;

public class Enregistrer_demande_gestionnaire extends BDD_Connexion {
	
	public static void ajouter_demande_gestionnaire(Gestionnaire gestionnaire) {
		String reqSQL="INSERT INTO demande_inscription_gestionnaire VALUES(null,?,?,?,?,?);";
		try {
			connexionBDD();
			PreparedStatement ps=BDD_Connexion.getConnexion().prepareStatement(reqSQL);
			ps.setString(1, gestionnaire.getNom());
			ps.setString(2, gestionnaire.getPrénom());
			ps.setString(3, gestionnaire.getEmail());
			ps.setString(4, gestionnaire.getTéléphone());
			ps.setString(5, gestionnaire.getMessage());
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
