package clientJSF;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.sun.xml.internal.ws.client.RequestContext;

import metier.sessions.IBiblioLocal;
import metier.entities.*;

@ManagedBean(name = "GestionLivreMB")

@ViewScoped
public class SignUpMB {
	@EJB

	private IBiblioLocal metier;
	private Compte con;
	private String msg;
	private Client clt;
	/*
	 * private String login; private String mdp;
	 */

	@PostConstruct
	void init()

	{
		con = new Compte("", "");

		msg = "";
	}

	public String connect() {
		if (con.getLogin().equals("") || con.getMdp().equals("")) {

			msg = "remplir tous les champs";
			return "SignUp.xhtml";
		} else {
			con = metier.connect(con);

			if (con != null) {
				msg = "";
				return "Gestionlivre.xhtml?faces-redirect=true";
			}

			else {
				msg = "Verifiez votre mdp ";
				return "SignUp.xhtml";
			}

		}

	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Compte getCon() {
		return con;
	}

	public void setCon(Compte con) {
		this.con = con;
	}

	public void inscrire(){
		
	}

	public Client getClt() {
		return clt;
	}

	public void setClt(Client clt) {
		this.clt = clt;
	}

}
