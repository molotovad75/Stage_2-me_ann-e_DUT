package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Connexion extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/../Connexion.jsp").forward(request, response);
	}
	
}
//CREATE TABLE Horaires_crénaux_sites(
//		IdEnseigne INT(11)UNSIGNED NOT NULL,
//	    IdSite INT(11) NOT NULL,
//	    Horaires_journée VARCHAR(20) NOT NULL,
//	    Horaires_Matin VARCHAR(20) NOT NULL,
//	    Horaires_Aprem_midi VARCHAR(20) NOT NULL,
//	    Horaires_fin_journée VARCHAR(20)NOT NULL,
//	    FOREIGN KEY (IdEnseigne)
//	    REFERENCES enseigne(IdEnseigne),
//	    FOREIGN KEY (IdSite)
//	    REFERENCES site_creche(IdCrèche)
//	    
//	)

//CREATE TABLE parents_habilité_réservation(
//	    IdParent_habilité INT(11) NOT NULL AUTO_INCREMENT,
//		IdParent INT(11) NOT NULL,
//	    Genre VARCHAR(1) NOT NULL,
//	    NomParentHabilité VARCHAR(150) NOT NULL,
//	    PrénomParentHabilité VARCHAR(150) NOT NULL,
//	    TelParentHabilité VARCHAR(10) NOT NULL,
//	    Email_conjoint VARCHAR(150) NOT NULL,
//	    GenreConjoint VARCHAR(1) NOT NULL,
//	    NomConjoint VARCHAR(150) NOT NULL,
//	    PrénomConjoint VARCHAR(150) NOT NULL,
//	    TelConjoint VARCHAR(10) NOT NULL,
//	    FOREIGN KEY (IdParent)
//	    REFERENCES parents(IdParent)
//	)


//CREATE TABLE enfants_parents_habilité(
//	    
//		Id_enfant INT(11) NOT NULL,
//		Email_parent_habilité VARCHAR(150),
//	    FOREIGN KEY (Id_enfant)
//	    REFERENCES enfants(Id_enfant)
//	)

//ALTER TABLE enfants_parents_habilité
//ADD CONSTRAINT FOREIGN KEY(Email_parent_habilité)
//REFERENCES parents(Email);

//ALTER TABLE enfants_parents_habilité
//ADD CONSTRAINT FOREIGN KEY(Id_parent_habilité)
//REFERENCES parents_habilité_réservation(Id_parent_habilité);