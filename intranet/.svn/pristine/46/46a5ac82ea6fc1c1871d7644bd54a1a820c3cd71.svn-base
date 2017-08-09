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
import br.com.sp.intranet.model.administrador.CsGrupo;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.app.CsAutorizacaoApp;
import br.com.sp.intranet.model.administrador.app.CsServicoApp;
import br.com.sp.intranet.service.administrador.AutorizacaoService;
import br.com.sp.intranet.service.administrador.GrupoService;
import br.com.sp.intranet.service.administrador.ServicoService;
import br.com.sp.intranet.service.administrador.app.AutorizacaoAppService;
import br.com.sp.intranet.service.administrador.app.ServicoAppService;

@Controller
@Scope("view")
public class GrupoController extends GenericController{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private GrupoService service;
	
	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private ServicoAppService servicoAppService;
	
	@Autowired
	private AutorizacaoService autorizacaoService;
	
	@Autowired
	private AutorizacaoAppService autorizacaoAppService;
	
	private List<CsGrupo> grupos;
	
	private List<CsServico> servicos;
	private List<CsServico> servicosSelecionados;
	
	private List<CsAutorizacao> autorizacoes;
	private List<CsAutorizacao> autorizacoesSelecionadas;
	
	private List<CsServicoApp> servicosApp;
	private List<CsServicoApp> servicosAppSelecionados;
	
	private List<CsAutorizacaoApp> autorizacoesApp;
	private List<CsAutorizacaoApp> autorizacoesAppSelecionadas;
	
	private CsGrupo grupoSelecionado;
	private CsGrupo grupo;
	
	private boolean inclusao;
	
	@PostConstruct
	public void init(){
		try {
			grupos = service.findAll();
			grupo = null;
			grupoSelecionado = null;
			
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	public void prepararAutorizacoes(){
		try{
			grupo = grupoSelecionado;
			autorizacoesSelecionadas = service.carregarAutorizacoes(grupoSelecionado);
			
			if(autorizacoes == null || autorizacoes.isEmpty()){
				autorizacoes = autorizacaoService.findAll();
			}
		}catch(JPAException e){
			e.printStackTrace();
		}
	}
	
	public void prepararServicos(){
		try {
			grupo = grupoSelecionado;
			servicosSelecionados = service.carregarServicos(grupoSelecionado);
			
			if(servicos == null || servicos.isEmpty()){
				servicos = servicoService.findAll();
			}
		} catch (JPAException e) {
			e.printStackTrace();
		}	
	}
	
	public void prepararAutorizacoesApp(){
		try{
			grupo = grupoSelecionado;
			autorizacoesAppSelecionadas = service.carregarAutorizacoesApp(grupoSelecionado);
			
			if(autorizacoesApp == null || autorizacoesApp.isEmpty()){
				autorizacoesApp = autorizacaoAppService.findAll();
			}
		}catch(JPAException e){
			e.printStackTrace();
		}
	}
	
	public void prepararServicosApp(){
		try {
			grupo = grupoSelecionado;
			servicosAppSelecionados = service.carregarServicosApp(grupoSelecionado);
			
			if(servicosApp == null || servicosApp.isEmpty()){
				servicosApp = servicoAppService.findAll();
			}
		} catch (JPAException e) {
			e.printStackTrace();
		}	
	}
	
	public void excluir(){
		if (grupoSelecionado != null) {
			try {
				service.delete(grupoSelecionado);
				createMessage(FacesMessage.SEVERITY_INFO, "excluir.sucesso");
				init();
			} catch (JPAException e) {
				e.printStackTrace();
				createMessage(FacesMessage.SEVERITY_ERROR, "excluir.erro");
			}
		}else{
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}
	
	public void salvar() {
		if (inclusao)
			incluir(grupoSelecionado);
		else
			alterar(grupoSelecionado);
		init();
	}
	
	public void salvarServicos(){
		grupoSelecionado.setServicos(servicosSelecionados);
		alterar(grupoSelecionado);
		init();
	}
	
	public void salvarAutorizacoes(){
		grupoSelecionado.setAutorizacoes(autorizacoesSelecionadas);
		alterar(grupoSelecionado);
		init();
	}
	
	public void salvarAutorizacoesApp(){
		grupoSelecionado.setAutorizacoesApp(autorizacoesAppSelecionadas);
		alterar(grupoSelecionado);
		init();
	}
	
	public void salvarServicosApp(){
		grupoSelecionado.setServicosApp(servicosAppSelecionados);
		alterar(grupoSelecionado);
		init();
	}
	
	public void alterar(CsGrupo grupo) {
		try {
			service.update(grupo);
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
		}
	}

	public void incluir(CsGrupo grupo) {
		try {
			service.save(grupo);
			createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "incluir.erro");
		}
	}
	
	public void prepararIncluir(){
		grupo = new CsGrupo();
		inclusao = true;
		grupoSelecionado = null;
		abrirDialog();
	}
	
	public void prepararAlterar(){
		if (grupoSelecionado != null) {
			grupo = grupoSelecionado;
			inclusao = false;
			grupoSelecionado = null;
			abrirDialog();
		}else{
			createMessage(FacesMessage.SEVERITY_ERROR, "sem.registro");
		}
	}
	
	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogGrupo').show();");
	}
	
	public List<CsGrupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<CsGrupo> grupos) {
		this.grupos = grupos;
	}

	public CsGrupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(CsGrupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public CsGrupo getGrupo() {
		return grupo;
	}

	public void setGrupo(CsGrupo grupo) {
		this.grupo = grupo;
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

	public List<CsServico> getServicosSelecionados() {
		return servicosSelecionados;
	}

	public void setServicosSelecionados(List<CsServico> servicosSelecionados) {
		this.servicosSelecionados = servicosSelecionados;
	}

	public List<CsAutorizacao> getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(List<CsAutorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

	public List<CsAutorizacao> getAutorizacoesSelecionadas() {
		return autorizacoesSelecionadas;
	}

	public void setAutorizacoesSelecionadas(List<CsAutorizacao> autorizacoesSelecionadas) {
		this.autorizacoesSelecionadas = autorizacoesSelecionadas;
	}

	public List<CsServicoApp> getServicosApp() {
		return servicosApp;
	}

	public void setServicosApp(List<CsServicoApp> servicosApp) {
		this.servicosApp = servicosApp;
	}

	public List<CsServicoApp> getServicosAppSelecionados() {
		return servicosAppSelecionados;
	}

	public void setServicosAppSelecionados(List<CsServicoApp> servicosAppSelecionados) {
		this.servicosAppSelecionados = servicosAppSelecionados;
	}

	public List<CsAutorizacaoApp> getAutorizacoesApp() {
		return autorizacoesApp;
	}

	public void setAutorizacoesApp(List<CsAutorizacaoApp> autorizacoesApp) {
		this.autorizacoesApp = autorizacoesApp;
	}

	public List<CsAutorizacaoApp> getAutorizacoesAppSelecionadas() {
		return autorizacoesAppSelecionadas;
	}

	public void setAutorizacoesAppSelecionadas(List<CsAutorizacaoApp> autorizacoesAppSelecionadas) {
		this.autorizacoesAppSelecionadas = autorizacoesAppSelecionadas;
	}
}