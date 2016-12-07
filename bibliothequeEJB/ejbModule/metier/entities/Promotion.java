package metier.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

 

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
public class Promotion implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPromotion;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	private double val;
	
	

	/*
	 * associations
	 * *************************************************************************
	 * *****************************************
	 */
	@JsonIgnore
	@ManyToMany
	private Set<Livre> livres;

	/*
	 * constructeur
	 * *************************************************************************
	 * *****************************************
	 */

	public Promotion() {
		super();
	}

	public Promotion(int idPromotion, Date dateDebut, Date dateFin, double val, Set<Livre> livres) {
		super();
		this.idPromotion = idPromotion;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.val = val;
		this.livres = livres;
	}

	/*
	 * getters
	 * *************************************************************************
	 * *****************************************
	 */
	public int getIdPromotion() {
		return idPromotion;
	}

	

	public Date getDateFin() {
		return dateFin;
	}

	public Date getDateDebut() {
		return dateDebut;
	}
	

	public double getVal() {
		return val;
	}
	@JsonIgnore
	public Set<Livre> getLivres() {
		return livres;
	}

	public void setVal(double val) {
		this.val = val;
	}

	
	/*
	 * setters
	 * *************************************************************************
	 * *****************************************
	 */
	public void setIdPromotion(int idPromotion) {
		this.idPromotion = idPromotion;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	@JsonIgnore
	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}

	/*
	 * autres
	 * *************************************************************************
	 * *****************************************
	 */

}
