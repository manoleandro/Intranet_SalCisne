package br.com.sp.intranet.controller;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sp.intranet.controller.administrador.LoginController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.service.administrador.UsuarioService;

public class GenericController implements Serializable {
	private static final String TITULO_MSG = "ATENÇÃO:";
	@Autowired
	private LoginController login;
	
	@Autowired
	private UsuarioService serviceUsuario;
	
	public void createMessage(Severity severity, String messageKeyProperty) {
		FacesMessage msg = new FacesMessage(severity, ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString(messageKeyProperty), null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public CsUsuario findUsuarioLoggedByUsername(){
		CsUsuario usuario = new CsUsuario();
		
		try {
			usuario = serviceUsuario.findById(login.getUsuario().getUsername());
			
		} catch (JPAException e) {
			e.printStackTrace();
		}
		return usuario;
	}
}