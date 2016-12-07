package clientJSF;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import metier.sessions.IBiblioLocal;
import metier.entities.*;

@ManagedBean(name = "GestionLivreMB")
@RequestScoped
public class GestionLivreMB {
	@EJB

	private IBiblioLocal metier;
	private String l;

	public List<Livre> getLivres() {

		System.out.println(metier.consulterLivres().size());
		return metier.consulterLivres();
	}

	@PostConstruct
	void init()

	{

	}

	public IBiblioLocal getMetier() {
		return metier;
	}

	public String getL() {
		l = "aaaa";
		return l;
	}

	public void setMetier(IBiblioLocal metier) {
		this.metier = metier;
	}

	public void setL(String l) {
		this.l = l;
	}

}
