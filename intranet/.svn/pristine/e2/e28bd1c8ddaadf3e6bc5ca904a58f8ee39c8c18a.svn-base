package br.com.sp.intranet.service.administrador;

import java.util.List;

import org.primefaces.model.menu.MenuModel;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.CsMenu;

public interface MenuService {

	public MenuModel montarMenu(String usuario) throws JPAException;
	
	public List<CsMenu> findAll() throws JPAException;
	
	public CsMenu findById(Long id) throws JPAException;
	
	public void save(GenericEntity entity) throws JPAException;
	
	public void update(GenericEntity entity) throws JPAException;
	
	public void delete(GenericEntity entity) throws JPAException;
}
