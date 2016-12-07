package metier.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import metier.sessions.IBiblioLocal;
import metier.entities.*;

@Stateless
@WebService

public class biblioServSOAP {
	@EJB
	private IBiblioLocal met;

	@WebMethod
	public List<Livre> consulterLivre() {
		return met.consulterLivres();
	}

}
