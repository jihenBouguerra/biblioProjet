package metier.sessions;

import java.util.List;

import javax.ejb.Local;

import metier.entities.*;

@Local
public interface IBiblioLocal {
	// livre
	public List<Livre> consulterLivres(Auteur a);

	public List<Livre> consulterLivres(Theme t);

	public List<Livre> consulterLivres(Editeur e);

	public Livre consulterLivre(long id);

	public List<Livre> consulterLivres();

	// cmd
	public List<Commande> consulterCmds(Compte c);

	public List<Commande> consulterCmds(Livre l);

	public Commande consulterCmd(Compte c, Livre l);

	public Commande consulterCmd(long id);

	// promotion
	public List<Promotion> consulterPromotions();

	public List<Promotion> consulterPromotions(Livre l);
	// compte

	public Compte connect(Compte c);
}
