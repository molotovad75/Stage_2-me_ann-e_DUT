package bean;

import java.sql.Date;

public class Enfant {
	private String nom,Pr�nom1,Pr�nom2;
	private Date date_naiss;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPr�nom1() {
		return Pr�nom1;
	}
	public void setPr�nom1(String pr�nom1) {
		Pr�nom1 = pr�nom1;
	}
	public String getPr�nom2() {
		return Pr�nom2;
	}
	public void setPr�nom2(String pr�nom2) {
		Pr�nom2 = pr�nom2;
	}
	public Date getDate_naiss() {
		return date_naiss;
	}
	public void setDate_naiss(Date date_naiss) {
		this.date_naiss = date_naiss;
	}
	
}
