package metier.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idClient;
	@Column(nullable = false, length = 30)
	private String nomClient;
	@Column(nullable = false, length = 30)
	private String prenomClient;
	@Column(nullable = false, length = 30)
	private String email;

	/*
	 * associations
	 * *************************************************************************
	 * *****************************************
	 */

	@OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Compte compte;

	/*
	 * constructeurs
	 * *************************************************************************
	 * *****************************************
	 */
	public Client() {
		super();
	}

	public Client(long numClient, String nomClient, String prenomClient, String email, Compte compte) {
		super();
		this.idClient = numClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.email = email;

		this.compte = compte;
	}

	/*
	 * getters
	 * *************************************************************************
	 * *****************************************
	 */

	public Compte getCompte() {
		return compte;
	}

	public long getNumClient() {
		return idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public String getEmail() {
		return email;
	}

	public long getIdClient() {
		return idClient;
	}

	

	/*
	 * setters
	 * *************************************************************************
	 * *****************************************
	 */
	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public void setNumClient(long numClient) {
		this.idClient = numClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
