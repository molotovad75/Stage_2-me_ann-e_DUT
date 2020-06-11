package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class BDD_Connexion {
	private static String nomHote="localhost";
	private static String nomBDD="bdd_ouicreches";
	private static String nomUtilisateur="root";
	private static String mot_de_passe="";
	
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
		return conn;
	}
}
