package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class BDD_Connexion {
	private static Connection conn;
	
	public static Connection getConn() {
		return conn;
	}

	public static void load_database() throws SQLException {
		
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    try {
	    	conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_ouicrèches","root","");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
