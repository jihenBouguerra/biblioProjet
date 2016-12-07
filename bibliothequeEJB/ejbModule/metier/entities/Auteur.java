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
public class Auteur implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAuteur;
	@Column(nullable = false, length = 30)
	private String nomAuteur;

	/*
	 * associations
	 * *************************************************************************
	 * 
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "auteur", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Set<Livre> livres;

	/*
	 * constructeurs
	 * *************************************************************************
	 * *******
	 */

	public Auteur(int idAuteur, String nomAuteurs, Set<Livre> livres) {
		super();
		this.idAuteur = idAuteur;
		this.nomAuteur = nomAuteurs;
		this.livres = livres;
	}

	public Auteur() {
		super();
	}

	public Auteur(String nomAuteur) {
		super();
		this.nomAuteur = nomAuteur;
	}

	/*
	 * getters
	 * *************************************************************************
	 * ***********************************
	 */
	public int getIdAuteur() {
		return idAuteur;
	}

	public String getNomAuteur() {
		return nomAuteur;
	}

	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}

	/*
	 * setters
	 * *************************************************************************
	 * 
	 */
	public Set<Livre> getLivres() {
		return livres;
	}

	public void setIdAuteur(int idAuteur) {
		this.idAuteur = idAuteur;
	}

	public void setNomAuteurs(String nomAuteurs) {
		this.nomAuteur = nomAuteurs;
	}

}