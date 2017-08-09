package br.com.sp.intranet.service.administrador.app.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.administrador.app.AutorizacaoAppDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.app.CsAutorizacaoApp;
import br.com.sp.intranet.service.administrador.app.AutorizacaoAppService;

@Service
public class AutorizacaoAppServiceImpl implements AutorizacaoAppService{
	
	@Autowired
	private AutorizacaoAppDAO dao;
	
	@Override
	@Transactional("sic")
	public List<CsAutorizacaoApp> findAll() throws JPAException{
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
	public CsAutorizacaoApp findById(Long id) throws JPAException {
		return (CsAutorizacaoApp) dao.findById(CsAutorizacaoApp.class, id);
	}
}