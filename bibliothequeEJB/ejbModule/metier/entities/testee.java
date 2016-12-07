package metier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class testee implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 30)
	private String nomAuteurs;

	public int getIdAuteur() {
		return id;
	}

	public String getNomAuteurs() {
		return nomAuteurs;
	}

	public void setIdAuteur(int idAuteur) {
		this.id = idAuteur;
	}

	public void setNomAuteurs(String nomAuteurs) {
		this.nomAuteurs = nomAuteurs;
	}

	public testee(int idAuteur, String nomAuteurs) {
		super();
		this.id = idAuteur;
		this.nomAuteurs = nomAuteurs;
	}

	public testee() {
		super();
	}

}
