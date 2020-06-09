package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class BDD_Connexion {
	
	public static Connection connexionBDD() throws ClassNotFoundException,SQLException{
		String nomHote="localhost";
		String nomBDD="bdd_ouicreches";
		String nomUtilisateur="root";
		String mot_de_passe="";
		return connexionBDD(nomHote,nomBDD,nomUtilisateur,mot_de_passe);
	}
	
	public static Connection connexionBDD(String nomHote,String nomBDD, String nomUtilisateur,String mdp) throws SQLException,ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String connectionURL="jdbc:mysql://"+nomHote+":3306/"+nomBDD+"?autoReconnect=true&useSSL=false";
		Connection conn=DriverManager.getConnection(connectionURL,nomUtilisateur,mdp);
		return conn;
	}
	

		
	public static Connection getConnexion()throws ClassNotFoundException,SQLException{
		return connexionBDD();
	}
	
	public static void fermer(Connection conn) {
		try {
			conn.close();
		}catch (Exception e) {
			e.getMessage();
		}
	}
	
	
}
