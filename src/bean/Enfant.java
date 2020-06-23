package bean;

import java.sql.Date;

public class Enfant {
	private String nom,Prénom1,Prénom2;
	private Date date_naiss;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrénom1() {
		return Prénom1;
	}
	public void setPrénom1(String prénom1) {
		Prénom1 = prénom1;
	}
	public String getPrénom2() {
		return Prénom2;
	}
	public void setPrénom2(String prénom2) {
		Prénom2 = prénom2;
	}
	public Date getDate_naiss() {
		return date_naiss;
	}
	public void setDate_naiss(Date date_naiss) {
		this.date_naiss = date_naiss;
	}
	
}
