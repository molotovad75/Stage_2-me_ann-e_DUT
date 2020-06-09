package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Gestionnaire;

public class Connexion_pro extends BDD_Connexion{
	
	private List<Gestionnaire>gestionnaire;
	private static String nom_user="";
	private static String mdp="";
	
	public List<Gestionnaire> recupererGestionnaire(String nom_user,String mdp){
		List<Gestionnaire> user=new ArrayList<Gestionnaire>();
		Connection conn = null;
		java.sql.ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			conn = BDD_Connexion.getConnexion();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			ps=conn.prepareStatement("SELECT pg.Nom,pg.Mdp FROM pro_gestionnaire AS pg WHERE pg.Nom=? AND pg.Mdp=?");
			ps.setString(1, nom_user);
			ps.setString(2, mdp);
			rs=ps.executeQuery();
			while (rs.next()) {
				Gestionnaire gestionnaire=new Gestionnaire();
				gestionnaire.setNom_user(rs.getString("pg.Nom"));
				gestionnaire.setMdp(rs.getString("pg.Mdp"));
				user.add(gestionnaire);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs!=null) {
					rs.close();
				}
				if (ps!=null) {
					ps.close();
				}
				if (conn!=null) {
					conn.close();
				}
			} catch (SQLException ignore) {
				ignore.getMessage();
			}
			
		}
//		setnom_user(nom_user);
//		setMdp(mdp);
		return user;
	}
	
	public List<Gestionnaire> getUtilisateurs() {
		return gestionnaire;
	}


	public void setUtilisateurs(List<Gestionnaire> gestionnaire) {
		this.gestionnaire = gestionnaire;
	}


	public static String getnom_user() {
		return nom_user;
	}


	public static void setnom_user(String nom_user) {
		Connexion_pro.nom_user = nom_user;
	}


	public static String getMdp() {
		return mdp;
	}


	public static void setMdp(String mdp) {
		Connexion_pro.mdp = mdp;
	}
}
