package metier.sessions;

import java.util.List;
import java.util.Set;
import java.util.Collection;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.*;

@Stateless(name = "bibEJB")
public class biblioEJBImpl implements IBiblioLocal, IBiblioRemote {

	@PersistenceContext(unitName = "biblioEJB")
	private EntityManager em;

	/*
	 * 
	 * livre
	 * *************************************************************************
	 *
	 */
	@Override
	public void addLivre(Livre l) {
		if (l.getIdLivre() == 0)
			em.persist(l);
		else
			throw new RuntimeException("ce livre a deja un identifiant");

	}

	@Override
	public void removeLivre(Livre l) {

		if (em.find(Livre.class, l.getIdLivre()) != null) {

			em.createQuery("delete  from Livre l where l.idLivre = ?1 ").setParameter(1, l.getIdLivre())
					.executeUpdate();

		} else
			throw new RuntimeException("livre introuvable");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Livre> consulterLivres(String titre) {
		return em.createQuery("select l from Livre l where l.titre like ?1").setParameter(1, "%" + titre + "%")
				.getResultList();

	}

	@Override
	public void updateLivre(Livre l) {
		if (em.find(Livre.class, l.getIdLivre()) != null) {
			em.merge(l);
		} else
			throw new RuntimeException("livre introuvable");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Livre> consulterLivres(Auteur a) {

		return em.createQuery("select l from Livre l where l.auteur.nomAuteur like ?1")
				.setParameter(1, "%" + a.getNomAuteur() + "%").getResultList();

	}

	@Override
	public List<Livre> consulterLivres(Theme t) {

		return (List<Livre>) em.createQuery("select l from Livre l where l.theme.titre like ?1")
				.setParameter(1, "%" + t.getTitre() + "%").getResultList();

	}

	@Override
	public List<Livre> consulterLivres(Editeur e) {

		return (List<Livre>) em.createQuery("select l from Livre l where l.editeur.nomEditeur like ?1")
				.setParameter(1, "%" + e.getNomEditeur() + "%").getResultList();

	}

	@Override
	public List<Livre> consulterLivres() {
		return (List<Livre>) em.createQuery("select l from Livre l ").getResultList();
	}

	@Override
	public Livre consulterLivre(long id) {

		return em.find(Livre.class, id);
	}

	/*
	 * Promotion
	 ********************************************************************************************************
	 */

	@Override
	public void addPromotion(Promotion p, Livre l) {
		if (p.getIdPromotion() == 0) {
			Set<Livre> livres = p.getLivres();
			livres.add(l);
			p.setLivres(livres);
			em.persist(p);
		}

	}

	@Override
	public void removePromotion(Promotion p) {
		// TODO Auto-generated method stub
		if (em.find(Promotion.class, p.getIdPromotion()) != null)
			em.remove(p);

	}

	@Override
	public void updatePromotion(Promotion p) {
		if (em.find(Promotion.class, p.getIdPromotion()) != null)
			em.merge(p);
		else
			throw new RuntimeException("Promotion introuvable");
	}

	@Override
	public List<Promotion> consulterPromotions() {
		return (List<Promotion>) em.createQuery("select p from Promotion p").getResultList();

	}

	@Override
	public List<Promotion> consulterPromotions(Livre l) {
		return (List<Promotion>) em.createQuery("select l.promotions from Livre l").getResultList();
	}

	@Override
	public Promotion consulterPromotion(long id) {
		Promotion p = em.find(Promotion.class, id);
		if (p == null)
			throw new RuntimeException("Promotion introuvable");
		return p;
	}

	/*
	 * Editeur
	 * *************************************************************************
	 */
	@Override
	public void addEditeur(Editeur e) {

		if (e.getIdEditeur() == 0)
			em.persist(e);
		else
			throw new RuntimeException("editeur a un id");
	}

	@Override
	public void removeEditeur(Editeur e) {
		if (em.find(Editeur.class, e.getIdEditeur()) != null) {

			em.createQuery("delete from Editeur e where e.idEditeur =?1").setParameter(1, e.getIdEditeur())
					.executeUpdate();

		} else
			throw new RuntimeException("editeur introuvable");

	}

	@Override
	public void updateEditeur(Editeur e) {
		if (em.find(Editeur.class, e.getIdEditeur()) != null) {
			em.merge(e);
		} else
			throw new RuntimeException("editeur introuvable");

	}

	@Override
	public List<Editeur> consulterEditeurs() {
		return (List<Editeur>) em.createQuery("select e from Editeur e ").getResultList();
	}

	@Override
	public Editeur consulterEditeur(long id) {
		return em.find(Editeur.class, id);
	}
	/*
	 * Commande
	 * *************************************************************************
	 */

	@Override
	public List<Commande> consulterCmds(Compte c) {
		if (em.find(Compte.class, c.getLogin()) != null)
			return (List<Commande>) em.createQuery("select c.Commandes from Compte c ").getResultList();
		else
			return null;
	}

	@Override
	public List<Commande> consulterCmds(Livre l) {
		if (em.find(Livre.class, l.getIdLivre()) != null)
			return (List<Commande>) em.createQuery("select l.Commandes from Livre l ").getResultList();
		else
			return null;
	}

	@Override
	public Commande consulterCmd(Compte c, Livre l) {
		if (em.find(Compte.class, c.getLogin()) != null && em.find(Livre.class, l.getIdLivre()) != null) {
			Query q = (Query) em
					.createQuery("select c.Commandes from Compte c " + " where c.Commandes.ligneCmd.Livre := lv ");
			q.setParameter("lv", l);
			return (Commande) q.getSingleResult();
		}

		else
			return null;
	}

	@Override
	public Commande consulterCmd(long id) {
		return em.find(Commande.class, id);
	}

	/*
	 * 
	 * Theme
	 * *************************************************************************
	 *
	 */

	@Override
	public void addTheme(Theme t) {
		if (t.getIdTheme() == 0)
			em.persist(t);
		else
			throw new RuntimeException("ce theme a deja un identifiant");

	}

	@Override
	public void removeTheme(Theme t) {
		if (em.find(Theme.class, t.getIdTheme()) != null) {
			em.remove(t);
		} else
			throw new RuntimeException("editeur introuvable");

	}

	@Override
	public void updateThemer(Theme t) {
		if (em.find(Theme.class, t.getIdTheme()) != null) {
			em.merge(t);
		} else
			throw new RuntimeException("editeur introuvable");

	}

	@Override
	public List<Theme> consulterThemes() {
		return (List<Theme>) em.createQuery("select t from Theme t ").getResultList();
	}

	@Override
	public Theme consulterTheme(long id) {
		return em.find(Theme.class, id);
	}

	/*
	 * 
	 * Auteur
	 * *************************************************************************
	 *
	 */
	@Override
	public void addAuteur(Auteur a) {
		if (a.getIdAuteur() == 0)
			em.persist(a);
		else
			throw new RuntimeException("cet auteur a deja un identifiant");

	}

	@Override
	public boolean existeAuteur(Auteur a) {
		int i = em.createQuery("select a from Auteur a where a.nomAuteur = ?1 ").setParameter(1, a.getNomAuteur())
				.getResultList().size();

		return (i != 0);
	}

	@Override
	public List<Auteur> consulterAuteurs() {
		return (List<Auteur>) em.createQuery("select a from Auteur a ").getResultList();
	}

	@Override
	public Auteur consulterAuteur(long id) {
		return em.find(Auteur.class, id);
	}

	@Override
	public void removeAuteur(Auteur a) {
		if (em.find(Auteur.class, a.getIdAuteur()) != null) {
			em.createQuery("delete from Auteur e where e.idAuteur =?1").setParameter(1, a.getIdAuteur())
					.executeUpdate();
		} else
			throw new RuntimeException("auteur introuvable");

	}

	@Override
	public void updateAuteur(Auteur a) {
		if (em.find(Auteur.class, a.getIdAuteur()) != null) {
			em.merge(a);
		} else
			throw new RuntimeException("auteur introuvable");

	}

	@Override
	public void test(testee t) {
		if (em.find(testee.class, t.getIdAuteur()) != null) {
			em.createQuery("delete from testee t where t.id = 1").executeUpdate();
		} else
			throw new RuntimeException("auteur introuvable");

	}

	@Override
	public List<testee> consulterTestes() {

		return (List<testee>) em.createQuery("select t from testee t ").getResultList();
	}

	/*
	 * 
	 * Compte
	 * *************************************************************************
	 *
	 */
	@Override
	public Compte connect(Compte c) {

		List<Compte> k = em.createQuery("select l from Compte l where l.login = ?1")
				.setParameter(1, c.getLogin().toLowerCase().trim()).getResultList();
		if (k.size() != 0 && k.get(0).getMdp().equals(c.getMdp()))
			return k.get(0);
		else
			return null;
	}
}
