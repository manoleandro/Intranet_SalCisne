package br.com.sp.intranet.controller.administrador.app;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.app.CsAutorizacaoApp;
import br.com.sp.intranet.service.administrador.app.AutorizacaoAppService;

@Controller
@Scope("view")
public class AutorizacaoAppController extends GenericController{
	
	@Autowired
	private AutorizacaoAppService service;
	
	private List<CsAutorizacaoApp> autorizacoes;
	
	private CsAutorizacaoApp autorizacao;
	private CsAutorizacaoApp autorizacaoSelecionada;
	
	private boolean inclusao;

	@PostConstruct
	public void init(){
		try {
			autorizacoes = service.findAll();
			autorizacao = null;
			autorizacaoSelecionada = null;
		} catch (JPAException e) {			
			e.printStackTrace();
		}
	}
	
	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogAutorizacao').show();");
	}
	
	public void prepararIncluir(){
		autorizacao = new CsAutorizacaoApp();
		inclusao = true;
		abrirDialog();
	}
	
	public void prepararAlterar(){
		if (autorizacaoSelecionada != null) {
			autorizacao = autorizacaoSelecionada;
			autorizacaoSelecionada = null;
			inclusao = false;
			abrirDialog();
		}else{
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}
	
	public void salvar(){
		if(inclusao)
			incluir(autorizacaoSelecionada);
		else
			alterar(autorizacaoSelecionada);
			
		init();
	}
	
	public void alterar(CsAutorizacaoApp autorizacao) {
		try {
			service.update(autorizacao);
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
		}
	}

	public void incluir(CsAutorizacaoApp autorizacao) {
		try {
			service.save(autorizacao);
			createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "incluir.erro");
		}
	}
	
	public void excluir(){
		if(autorizacaoSelecionada != null){
			try {
				service.delete(autorizacaoSelecionada);
				createMessage(FacesMessage.SEVERITY_ERROR, "excluir.sucesso");
				init();
			} catch (JPAException e) {
				e.printStackTrace();
				createMessage(FacesMessage.SEVERITY_ERROR, "excluir.erro");
			}
		}else{
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}	
	}
	
	public List<CsAutorizacaoApp> getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(List<CsAutorizacaoApp> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

	public CsAutorizacaoApp getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(CsAutorizacaoApp autorizacao) {
		this.autorizacao = autorizacao;
	}

	public CsAutorizacaoApp getAutorizacaoSelecionada() {
		return autorizacaoSelecionada;
	}

	public void setAutorizacaoSelecionada(CsAutorizacaoApp autorizacaoSelecionada) {
		this.autorizacaoSelecionada = autorizacaoSelecionada;
	}

	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}
}