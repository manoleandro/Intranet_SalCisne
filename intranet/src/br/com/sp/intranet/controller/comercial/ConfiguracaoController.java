package br.com.sp.intranet.controller.comercial;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.Configuracao;
import br.com.sp.intranet.service.comercial.ConfiguracaoService;

@Controller
@Scope("view")
public class ConfiguracaoController extends GenericController {
	
	private Configuracao configuracao;
	private CsUsuario usuario;
	
	@Autowired
	private ConfiguracaoService service;

	@PostConstruct
	public void init(){
		try {
			usuario = findUsuarioLoggedByUsername();
			configuracao = service.find(usuario);
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	public void salvar(){
		try {
			service.salvar(usuario, configuracao);
			init();
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
		} catch (JPAException e) {			
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
		}
	}
	
	public Configuracao getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
}