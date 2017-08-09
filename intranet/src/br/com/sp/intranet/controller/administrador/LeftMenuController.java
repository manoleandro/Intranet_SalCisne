package br.com.sp.intranet.controller.administrador;

import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.service.administrador.MenuService;

@Controller
@Scope("session")
public class LeftMenuController extends GenericController{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MenuService service;
	
	@Autowired
	private LoginController login;
	
	private MenuModel menuModel;
	
	public MenuModel montarMenu(){
		try {
			if(menuModel == null || menuModel.getElements().isEmpty())
				menuModel = service.montarMenu(login.getUsuario().getUsername());
		} catch (JPAException e) {
			e.printStackTrace();
		}
		return menuModel;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}
}