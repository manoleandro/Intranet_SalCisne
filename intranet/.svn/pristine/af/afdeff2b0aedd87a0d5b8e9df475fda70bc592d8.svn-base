package br.com.sp.intranet.controller.administrador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.service.administrador.AutorizacaoService;
import br.com.sp.intranet.service.administrador.ServicoService;

@Controller
@Scope("view")
public class AutorizacaoController extends GenericController{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private AutorizacaoService service;
	
	@Autowired
	private ServicoService servicoService;
	
	private List<CsAutorizacao> autorizacoes;
	private List<CsServico> servicos;
	
	private CsServico servicoSelecionado;
	
	private CsAutorizacao autorizacao;
	private CsAutorizacao autorizacaoSelecionada;

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
	
	public void setarServico(){
		autorizacao.setServico(servicoSelecionado);
	}
	
	public void carregarServicos(){
		servicoSelecionado = null;
		if(servicos == null || servicos.isEmpty()){
			try {
				servicos = servicoService.findAll();
			} catch (JPAException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogAutorizacao').show();");
	}
	
	public void prepararIncluir(){
		autorizacao = new CsAutorizacao();
		autorizacao.setServico(new CsServico());
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
	
	public void alterar(CsAutorizacao autorizacao) {
		try {
			service.update(autorizacao);
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
		}
	}

	public void incluir(CsAutorizacao autorizacao) {
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

	public List<CsAutorizacao> getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(List<CsAutorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

	public CsAutorizacao getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(CsAutorizacao autorizacao) {
		this.autorizacao = autorizacao;
	}

	public CsAutorizacao getAutorizacaoSelecionada() {
		return autorizacaoSelecionada;
	}

	public void setAutorizacaoSelecionada(CsAutorizacao autorizacaoSelecionada) {
		this.autorizacaoSelecionada = autorizacaoSelecionada;
	}

	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}

	public List<CsServico> getServicos() {
		return servicos;
	}

	public void setServicos(List<CsServico> servicos) {
		this.servicos = servicos;
	}

	public CsServico getServicoSelecionado() {
		return servicoSelecionado;
	}

	public void setServicoSelecionado(CsServico servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}
}