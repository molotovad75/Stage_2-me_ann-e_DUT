package bean;

public class Gestionnaire {
//	ATTRIBUTS IDENTIFICATION
	private String nom_user;
	private String mdp;

	//	ATTRIBUTS POUR GESTIONNAIRE VOULANT S'INSCRIRE
	private String Nom;
	private String Pr�nom;
	private String email;
	private int[] t�l�phone=new int [10];
	
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
	public String getPr�nom() {
		return Pr�nom;
	}
	public void setPr�nom(String pr�nom) {
		Pr�nom = pr�nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int[] getT�l�phone() {
		return t�l�phone;
	}
	public void setT�l�phone(int[] t�l�phone) {
		this.t�l�phone = t�l�phone;
	}
}
