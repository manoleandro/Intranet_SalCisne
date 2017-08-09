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
import br.com.sp.intranet.service.administrador.MenuService;

@Controller
@Scope("view")
public class MenuController extends GenericController {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private MenuService service;
	
	private CsMenu menu;
	private CsMenu menuSelecionado;
	private CsMenu superiorSelecionado;
	
	private List<CsMenu> menus;
	
	private boolean inclusao;
	
	@PostConstruct
	public void init(){
		try {
			menus = service.findAll();
			menu = null;
			menuSelecionado = null;
			superiorSelecionado = null;
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	public void setarSuperior(){
		menu.setSuperior(superiorSelecionado);
	}
	
	public void prepararIncluir(){
		menu = new CsMenu();
		menuSelecionado = null;
		inclusao = true;
		abrirDialog();
	}
	
	public void prepararAlterar(){
		if (menuSelecionado != null) {
			menu = menuSelecionado;
			menuSelecionado = null;
			inclusao = false; 
			abrirDialog();
		}else{
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}
	
	public void alterar(CsMenu menu) {
		try {
			service.update(menu);
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
		}
	}

	public void incluir(CsMenu menu) {
		try {
			service.save(menu);
			createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "incluir.erro");
		}
	}
	
	public void salvar(){
		if(inclusao)
			incluir(menuSelecionado);
		else
			alterar(menuSelecionado);
			
		init();
	}
	
	public void excluir(){
		if(menuSelecionado != null){
			try {
				service.delete(menuSelecionado);
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
	
	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogMenu').show();");
	}
	
	public CsMenu getMenu() {
		return menu;
	}

	public void setMenu(CsMenu menu) {
		this.menu = menu;
	}

	public CsMenu getMenuSelecionado() {
		return menuSelecionado;
	}

	public void setMenuSelecionado(CsMenu menuSelecionado) {
		this.menuSelecionado = menuSelecionado;
	}

	public List<CsMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<CsMenu> menus) {
		this.menus = menus;
	}

	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}

	public CsMenu getSuperiorSelecionado() {
		return superiorSelecionado;
	}

	public void setSuperiorSelecionado(CsMenu superiorSelecionado) {
		this.superiorSelecionado = superiorSelecionado;
	}
}