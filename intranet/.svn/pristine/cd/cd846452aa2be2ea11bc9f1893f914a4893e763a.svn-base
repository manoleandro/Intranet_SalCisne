package br.com.sp.intranet.service.administrador.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.administrador.MenuDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.CsGrupo;
import br.com.sp.intranet.model.administrador.CsMenu;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.vo.PermissaoUsuarioVO;
import br.com.sp.intranet.service.administrador.MenuService;
import br.com.sp.intranet.util.DataTypes;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDAO dao;
	
	@Override
	@Transactional("sic")
	public MenuModel montarMenu(String usuario) throws JPAException{
		MenuModel menuModel = new DefaultMenuModel();
		
		List<PermissaoUsuarioVO> permissoes = dao.findPermissoesByUsuario(usuario);
		
		Map<CsMenu, List<CsServico>> permissoesAgrupadasByMenu = agruparPermissoesByMenu(permissoes);
		
		//Incluindo links sem menu
		List<PermissaoUsuarioVO> permissoesSemMenu = filtrarPermissaoSemMenu(permissoes);
		
		for (PermissaoUsuarioVO permissaoSemMenu : permissoesSemMenu) {
			menuModel.addElement(criarItemMenu(permissaoSemMenu.getServico()));
		}
		
		//Ordenando Menus
		Map<CsMenu, List<CsServico>> permissoesAgrupadasByMenuOrdered = DataTypes.sortMapByKey(permissoesAgrupadasByMenu);

		//Incluindo links com menu
		Iterator<Entry<CsMenu, List<CsServico>>> iterator = permissoesAgrupadasByMenuOrdered.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry<CsMenu, List<CsServico>> entry = (Map.Entry<CsMenu, List<CsServico>>) iterator.next();
			
			DefaultSubMenu menu = criarMenu(entry.getKey());
			
			//Incluindo subMenus
			Map<CsMenu, List<CsServico>> permissoesAgrupadasBySubMenu = agruparPermissoesBySubMenu(permissoes, entry.getKey());
			
			if(!permissoesAgrupadasBySubMenu.isEmpty()){
				Iterator<Entry<CsMenu, List<CsServico>>> it = permissoesAgrupadasBySubMenu.entrySet().iterator();
				
				while(it.hasNext()){
					Map.Entry<CsMenu, List<CsServico>> entrySub = (Map.Entry<CsMenu, List<CsServico>>) it.next();
					
					DefaultSubMenu subMenu = criarMenu(entrySub.getKey());
					
					for (CsServico servico : entrySub.getValue()) {
						DefaultMenuItem subItem = criarItemMenu(servico);
						subMenu.addElement(subItem);
					}
					menu.addElement(subMenu);
				}
			}
			
			for (CsServico servico : entry.getValue()) {
				DefaultMenuItem item = criarItemMenu(servico);
				menu.addElement(item);
			}
			
			menuModel.addElement(menu);
		}
		
		return menuModel;
	}
	
	public DefaultSubMenu criarMenu(CsMenu menu){
		DefaultSubMenu subMenu = new DefaultSubMenu(menu.getDescricao());
		subMenu.setIcon(menu.getIcone());
		
		return subMenu;
	}
	
	public DefaultMenuItem criarItemMenu(CsServico servico){
		DefaultMenuItem item = new DefaultMenuItem(servico.getDescricao());
		item.setIcon(servico.getIcone());
		item.setOutcome(servico.getLink());
		
		return item;
	}
	
	public List<PermissaoUsuarioVO> filtrarPermissaoSemMenu(List<PermissaoUsuarioVO> permissoes){
		List<PermissaoUsuarioVO> permissoesSemMenu = permissoes.stream()
				.filter(p -> p.getMenu() == null)
				.collect(Collectors.toList());
		
		return permissoesSemMenu;
	}
	
	public Map<CsMenu, List<CsServico>> agruparPermissoesByMenu(List<PermissaoUsuarioVO> permissoes) {
		Map<CsMenu, List<CsServico>> permissoesAgrupadasByMenu = permissoes.stream()
				.filter(m -> m.getMenu() != null)
				.filter(m -> m.getMenu().getSuperior() == null)
				.sorted()
				.collect(Collectors.groupingBy(PermissaoUsuarioVO::getMenu, Collectors.mapping(PermissaoUsuarioVO::getServico, Collectors.toList())));
		
		return permissoesAgrupadasByMenu;
	}
	
	public Map<CsMenu, List<CsServico>> agruparPermissoesBySubMenu(List<PermissaoUsuarioVO> permissoes, CsMenu menu){
		Map<CsMenu, List<CsServico>> permissoesAgrupadasBySubMenu = permissoes.stream()
				.filter(s -> s.getMenu() != null)
				.filter(s -> s.getMenu().getSuperior() != null)
				.filter(s -> s.getMenu().getSuperior().equals(menu))
				.sorted()
				.collect(Collectors.groupingBy(PermissaoUsuarioVO::getMenu, Collectors.mapping(PermissaoUsuarioVO::getServico, Collectors.toList())));
				
		return permissoesAgrupadasBySubMenu;	
	}

	@Override
	@Transactional("sic")
	public List<CsMenu> findAll() throws JPAException {
		return dao.findAll();
	}
	
	@Override
	@Transactional("sic")
	public void delete(GenericEntity entity) throws JPAException {
		dao.delete(entity);
	}

	@Override
	@Transactional("sic")
	public void update(GenericEntity entity) throws JPAException {
		dao.update(entity);
	}

	@Override
	@Transactional("sic")
	public void save(GenericEntity entity) throws JPAException {
		dao.save(entity);
	}

	@Override
	@Transactional("sic")
	public CsMenu findById(Long id) throws JPAException {
		return (CsMenu) dao.findById(CsGrupo.class, id);
	}
}