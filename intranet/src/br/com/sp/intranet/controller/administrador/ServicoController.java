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
import br.com.sp.intranet.model.administrador.CsMenu;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.service.administrador.MenuService;
import br.com.sp.intranet.service.administrador.ServicoService;

@Controller
@Scope("view")
public class ServicoController extends GenericController {
	
	@Autowired
	private ServicoService service;
	
	@Autowired
	private MenuService menuService;
	
	private List<CsServico> servicos;
	
	private CsServico servico;
	private CsServico servicoSelecionado;
	
	private List<CsMenu> menus;
	
	private boolean inclusao;
	
	@PostConstruct
	public void init(){
		try {
			servicos = service.findAll();
			servico = null;
			servicoSelecionado = null;
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	public void prepararIncluir(){
		servico = new CsServico();
		servico.setMenu(new CsMenu());
		
		servicoSelecionado = null;
		inclusao = true;
		carregarMenus();
		abrirDialog();
	}
	
	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogServico').show();");
	}
	
	public void prepararAlterar(){
		if (servicoSelecionado != null) {
			servico = servicoSelecionado;
			servicoSelecionado = null;
			inclusao = false;
			carregarMenus();
			abrirDialog();
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}
	
	public void carregarMenus(){
		if (menus == null || menus.isEmpty()) {
			try {
				menus = menuService.findAll();
			} catch (JPAException e) {
				e.printStackTrace();
			}
		}
	}
		
	public void salvar(){
		if(inclusao)
			incluir(servicoSelecionado);
		else
			alterar(servicoSelecionado);
			
		init();
	}
	
	public void alterar(CsServico servico) {
		try {
			service.update(servico);
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
		}
	}

	public void incluir(CsServico servico) {
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
	
	public List<CsServico> getServicos() {
		return servicos;
	}
	
	public void setServicos(List<CsServico> servicos) {
		this.servicos = servicos;
	}
	
	public CsServico getServico() {
		return servico;
	}
	
	public void setServico(CsServico servico) {
		this.servico = servico;
	}
	
	public CsServico getServicoSelecionado() {
		return servicoSelecionado;
	}
	
	public void setServicoSelecionado(CsServico servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}

	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}

	public List<CsMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<CsMenu> menus) {
		this.menus = menus;
	}
}