package metier.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Compte implements Serializable {
	@Id
	private String login;
	@Column(nullable = false, length = 30)
	private String mdp;

	/*
	 * associations
	 * *************************************************************************
	 * *****************************************
	 */
	@OneToOne
	private Client client;
	@JsonIgnore
	@OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Commande> cammandes;
	@JsonIgnore
	@ManyToMany(mappedBy = "comptes", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Theme> themes;

	/*
	 * constructeurs
	 * *************************************************************************
	 * *****************************************
	 */
	public Compte() {
		super();
	}

	public Compte(String login, String mdp, Client client) {
		super();
		this.login = login;
		this.mdp = mdp;
		this.client = client;
	}

	public Compte(String login, String mdp) {
		super();
		this.login = login;
		this.mdp = mdp;

	}

	/*
	 * getters
	 * *************************************************************************
	 * *****************************************
	 */
	public String getLogin() {
		return login;
	}

	public Client getClient() {
		return client;
	}

	public String getMdp() {
		return mdp;
	}

	public Set<Commande> getCammandes() {
		return cammandes;
	}

	public Set<Theme> getThemes() {
		return themes;
	}

	/*
	 * setters
	 * *************************************************************************
	 * *****************************************
	 */

	public void setClient(Client client) {
		this.client = client;
	}

	public void setCammandes(Set<Commande> cammandes) {
		this.cammandes = cammandes;
	}

	public void setThemes(Set<Theme> themes) {
		this.themes = themes;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
