package metier.entities;

import java.io.Serializable;

import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Commande implements Serializable {
	public enum MODEPAIEMENT {
		carteBancaires, chèques, espèces;
	}

	public enum ETATCMD {
		validé, enAttente, enCours;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idCommande;
	@Temporal(TemporalType.DATE)
	private Date dateCommande;
	@Column(nullable = false, length = 30)
	private MODEPAIEMENT modePaiement;
	@Column(nullable = false, length = 30)
	private ETATCMD etatcmd;

	/*
	 * associations
	 * *************************************************************************
	 * *****************************************
	 */

	@ManyToOne
	private Compte compte;
	@JsonIgnore
	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<LigneCmd> ligneCmd;

	/*
	 * constructeurs
	 * *************************************************************************
	 * *****************************************
	 */

	public Commande() {
		super();
	}

	/*
	 * getters
	 * *************************************************************************
	 * *****************************************
	 */

	public long getIdCommande() {
		return idCommande;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public MODEPAIEMENT getModePaiement() {
		return modePaiement;
	}

	public ETATCMD getEtatcmd() {
		return etatcmd;
	}

	public Compte getCompte() {
		return compte;
	}

	public List<LigneCmd> getLigneCmd() {
		return ligneCmd;
	}

	/*
	 * setters
	 * *************************************************************************
	 * *****************************************
	 */
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public void setIdCommande(long idCommande) {
		this.idCommande = idCommande;
	}

	public void setModePaiement(MODEPAIEMENT modePaiement) {
		this.modePaiement = modePaiement;
	}

	public void setEtatcmd(ETATCMD etatcmd) {
		this.etatcmd = etatcmd;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public void setLigneCmd(List<LigneCmd> ligneCmd) {
		this.ligneCmd = ligneCmd;
	}

}