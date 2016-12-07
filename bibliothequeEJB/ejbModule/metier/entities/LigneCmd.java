package metier.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class LigneCmd implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idLigneCmd;
	private int qte;

	/*
	 * associations
	 * *************************************************************************
	 * *****************************************
	 */
	@ManyToOne
	private Livre livre;
	@ManyToOne
	private Commande commande;

	/*
	 * constructeur
	 * *************************************************************************
	 * *****************************************
	 */ public LigneCmd() {
		super();
	}

	public LigneCmd(long idLigneCmd, int qte, Livre livre, Commande commande) {
		super();
		this.idLigneCmd = idLigneCmd;
		this.qte = qte;
		this.livre = livre;
		this.commande = commande;
	}

	/*
	 * getters
	 * *************************************************************************
	 * *****************************************
	 */
	public float getPrix() {
		return livre.getPrix() * qte;
	}

	public long getIdLigneCmd() {
		return idLigneCmd;
	}

	public int getQte() {
		return qte;
	}

	public Livre getLivre() {
		return livre;
	}

	public Commande getCommande() {
		return commande;
	}

	/*
	 * setters
	 * *************************************************************************
	 * *****************************************
	 */
	public void setIdLigneCmd(long idLigneCmd) {
		this.idLigneCmd = idLigneCmd;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
}