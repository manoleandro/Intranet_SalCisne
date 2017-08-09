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
import br.com.sp.intranet.model.administrador.app.CsServicoApp;
import br.com.sp.intranet.model.administrador.app.TipoPermissaoEnum;
import br.com.sp.intranet.service.administrador.app.ServicoAppService;

@Controller
@Scope("view")
public class ServicoAppController extends GenericController {
	
	@Autowired
	private ServicoAppService service;
	
	private List<CsServicoApp> servicos;
	
	private CsServicoApp servico;
	private CsServicoApp servicoSelecionado;
	
	private boolean inclusao = false;
	
	private TipoPermissaoEnum[] permissoes;
	
	@PostConstruct
	public void init(){
		try {
			servicos = service.findAll();
			servico = new CsServicoApp();
			servicoSelecionado = null;
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	public void prepararIncluir(){
		servico = new CsServicoApp();
		servicoSelecionado = null;
		inclusao = true;
		
		abrirDialog();
	}
	
	public void prepararAlterar(){
		if (servicoSelecionado != null) {
			servico = servicoSelecionado;
			servicoSelecionado = null;
			inclusao = false;
			abrirDialog();
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}
	
	public void salvar(){
		if(inclusao)
			incluir(servicoSelecionado);
		else
			alterar(servicoSelecionado);
			
		init();
	}
	
	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogServico').show();");
	}
	
	public void alterar(CsServicoApp servico) {
		try {
			service.update(servico);
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
		}
	}

	public void incluir(CsServicoApp servico) {
		try {
			service.save(servico);
			createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "incluir.erro");
		}
	}
	
	public void excluir(){
		if(servicoSelecionado != null){
			try {
				service.delete(servicoSelecionado);
				createMessage(FacesMessage.SEVERITY_INFO, "excluir.sucesso");
				init();
			} catch (JPAException e) {
				createMessage(FacesMessage.SEVERITY_ERROR, "excluir.erro");
				e.printStackTrace();
			}
		}else{
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}
	
	public List<CsServicoApp> getServicos() {
		return servicos;
	}
	
	public void setServicos(List<CsServicoApp> servicos) {
		this.servicos = servicos;
	}
	
	public CsServicoApp getServico() {
		return servico;
	}
	
	public void setServico(CsServicoApp servico) {
		this.servico = servico;
	}
	
	public CsServicoApp getServicoSelecionado() {
		return servicoSelecionado;
	}
	
	public void setServicoSelecionado(CsServicoApp servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}

	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}

	public TipoPermissaoEnum[] getPermissoes() {
		return TipoPermissaoEnum.values();
	}

	public void setPermissoes(TipoPermissaoEnum[] permissoes) {
		this.permissoes = permissoes;
	}
}