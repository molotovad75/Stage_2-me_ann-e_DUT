package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class BDD_Connexion {
	private static String nomHote="localhost";
	private static String nomBDD="bdd_ouicreches";
	private static String nomUtilisateur="root";
	private static String mot_de_passe="";
	
//	public static Connection connexionBDD() throws ClassNotFoundException,SQLException{
//		String nomHote="localhost";
//		String nomBDD="bdd_ouicreches";
//		String nomUtilisateur="root";
//		String mot_de_passe="";
//		return connexionBDD(nomHote,nomBDD,nomUtilisateur,mot_de_passe);
//	}
//	
//	public static Connection connexionBDD(String nomHote,String nomBDD, String nomUtilisateur,String mdp) throws SQLException,ClassNotFoundException {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		String connectionURL="jdbc:mysql://"+nomHote+":3306/"+nomBDD+"?autoReconnect=true&useSSL=false";
//		Connection conn=DriverManager.getConnection(connectionURL,nomUtilisateur,mdp);
//		return conn;
//	}
//	
//
//		
//	public static Connection getConnexion()throws ClassNotFoundException,SQLException{
//		return connexionBDD();
//	}
//	
	public static void fermer(Connection conn) {
		try {
			conn.close();
		}catch (Exception e) {
			e.getMessage();
		}
	}
	

	
	public static Connection getConn() throws SQLException, ClassNotFoundException {
		return load_database();
	}

	
	public static Connection load_database() throws SQLException, ClassNotFoundException {
		Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
	    conn= DriverManager.getConnection("jdbc:mysql://"+nomHote+":3306/"+nomBDD+"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",nomUtilisateur,mot_de_passe);
//		try {
//	        Class.forName("com.mysql.cj.jdbc.Driver");
//	        conn= DriverManager.getConnection("jdbc:mysql://"+nomHote+":3306/"+nomBDD+"?autoReconnect=true&useSSL=false",nomUtilisateur,mot_de_passe);
//	    } catch (ClassNotFoundException | SQLException e) {
//	        e.printStackTrace();
//	    }
		return conn;
	}
}
