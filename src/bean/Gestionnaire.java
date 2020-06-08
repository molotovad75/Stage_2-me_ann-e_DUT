package bean;

public class Gestionnaire {
//	ATTRIBUTS IDENTIFICATION
	private String nom_user;
	private String mdp;

	//	ATTRIBUTS POUR GESTIONNAIRE VOULANT S'INSCRIRE
	private String Nom;
	private String Prénom;
	private String email;
	private int[] téléphone=new int [10];
	
	public String getNom_user() {
		return nom_user;
	}
	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrénom() {
		return Prénom;
	}
	public void setPrénom(String prénom) {
		Prénom = prénom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int[] getTéléphone() {
		return téléphone;
	}
	public void setTéléphone(int[] téléphone) {
		this.téléphone = téléphone;
	}
}
