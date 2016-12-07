package metier.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

//import jackson.annotation.JsonIgnore;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Livre implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idLivre;
	@Column(nullable = true, length = 30)
	private String titre;
	@Temporal(TemporalType.DATE)
	private Date dateApparition;
	@Column(nullable = true)
	private float prix;

	/*
	 * associations
	 * *************************************************************************
	 * *****************************************
	 */

	@JsonIgnore
	@OneToMany(mappedBy = "livre", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private Set<LigneCmd> ligneCmds;
	@JsonIgnore
	@ManyToMany(mappedBy = "livres", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private Set<Promotion> promotions;
	@ManyToOne()
	private Editeur editeur;
	@ManyToOne()
	private Auteur auteur;
	@ManyToOne()
	private Theme theme;

	/*
	 * constructeur
	 * *************************************************************************
	 * *****************************************
	 */

	public Livre() {
		super();

	}
	/*
	 * getters
	 * *************************************************************************
	 * *****************************************
	 */

	public float getPrix() {
		return prix;
	}

	public long getIdLivre() {
		return idLivre;
	}

	public String getTitre() {
		return titre;
	}

	 

	public Date getDateApparition() {
		return dateApparition;
	}

	

	public Editeur getEditeur() {
		return editeur;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public Theme getTheme() {
		return theme;
	}
	public Set<LigneCmd> getLigneCmds() {
		return ligneCmds;
	}

	public Set<Promotion> getPromotions() {
		return promotions;
	}

	/*
	 * setters
	 * *************************************************************************
	 * *****************************************
	 */
	public void setPrix(float prix) {
		this.prix = prix;
	}

	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	 

	public void setDateApparition(Date dateApparition) {
		this.dateApparition = dateApparition;
	}

	public void setIdLivre(long idLivre) {
		this.idLivre = idLivre;
	}

	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	

	public void setLigneCmds(Set<LigneCmd> ligneCmds) {
		this.ligneCmds = ligneCmds;
	}

	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}
	

}