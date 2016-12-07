package metier.sessions;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.*;
 
@Remote
public interface IBiblioRemote {
	// livre
	public void addLivre(Livre l);

	public void removeLivre(Livre l);

	public void updateLivre(Livre l);

	public List<Livre> consulterLivres();
	public List<Livre> consulterLivres(String titre);

	public List<Livre> consulterLivres(Auteur a);

	public List<Livre> consulterLivres(Theme t);

	public List<Livre> consulterLivres(Editeur e);


	public Livre consulterLivre(long id);

	// Promotionn
	public void addPromotion(Promotion p, Livre l);

	public void removePromotion(Promotion p);

	public void updatePromotion(Promotion p);

	public List<Promotion> consulterPromotions();

	public List<Promotion> consulterPromotions(Livre l);

	public Promotion consulterPromotion(long id);

	// editeur
	public void addEditeur(Editeur p);

	public void removeEditeur(Editeur p);

	public void updateEditeur(Editeur p);

	public List<Editeur> consulterEditeurs();

	public Editeur consulterEditeur(long id);

	// theme
	public void addTheme(Theme t);

	public void removeTheme(Theme t);

	public void updateThemer(Theme t);

	public List<Theme> consulterThemes();

	public Theme consulterTheme(long id);

	// Auteur
	public void addAuteur(Auteur a);

	public void removeAuteur(Auteur a);

	public void updateAuteur(Auteur a);

	public List<Auteur> consulterAuteurs();

	public Auteur consulterAuteur(long id);
	public boolean existeAuteur(Auteur a);
	//tetse
	public void test(testee t);
	public List<testee> consulterTestes();

}
