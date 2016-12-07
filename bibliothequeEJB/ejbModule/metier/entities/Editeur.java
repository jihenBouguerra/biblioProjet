package metier.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Editeur implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEditeur;
	@Column(nullable = false, length = 30)
	private String nomEditeur;

	/*
	 * associations
	 * *************************************************************************
	 * *****
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "editeur", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Livre> livres;

	/*
	 * constructeurs
	 * *************************************************************************
	 * *****************************************
	 */

	public Editeur() {
		super();
	}

	public Editeur(String nomEditeur) {
		super();
		this.nomEditeur = nomEditeur;
	}

	/*
	 * getters
	 * *************************************************************************
	 * *****************************************
	 */
	public int getIdEditeur() {
		return idEditeur;
	}

	public String getNomEditeur() {
		return nomEditeur;
	}

	@JsonIgnore
	public Set<Livre> getLivres() {
		return livres;
	}

	/*
	 * setters
	 * *************************************************************************
	 * *****************************************
	 */
	public void setIdEditeur(int idEditeur) {
		this.idEditeur = idEditeur;
	}

	public void setNomEditeur(String nomEditeur) {
		this.nomEditeur = nomEditeur;
	}

	@JsonIgnore
	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}

}