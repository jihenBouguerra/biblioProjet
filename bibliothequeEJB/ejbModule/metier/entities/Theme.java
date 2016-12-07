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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Theme implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTheme;
	@Column(nullable = false, length = 30)
	private String titre;

	/*
	 * associations
	 * *************************************************************************
	 * *****************************************
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "theme", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Set<Livre> livres;
	@JsonIgnore
	@ManyToMany
	private Set<Compte> comptes;

	/*
	 * constructeurs
	 * *************************************************************************
	 * *****************************************
	 */

	public Theme() {
		super();
	}

	public Theme(int idTheme, String titre) {
		super();
		this.idTheme = idTheme;
		this.titre = titre;
	}

	public Theme(String titre) {
		super();
		this.titre = titre;
	}
	/*
	 * getters
	 * *************************************************************************
	 */

	public int getIdTheme() {
		return idTheme;
	}

	public String getTitre() {
		return titre;
	}
	@JsonIgnore
	public Set<Livre> getLivres() {
		return livres;
	}
	@JsonIgnore
	public Set<Compte> getComptes() {
		return comptes;
	}

	/*
	 * setters
	 * *************************************************************************
	 * ***********
	 */
	@JsonIgnore
	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}
	@JsonIgnore
	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}

	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

}
