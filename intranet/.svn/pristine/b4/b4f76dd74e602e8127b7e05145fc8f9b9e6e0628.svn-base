package br.com.sp.intranet.service.administrador.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.administrador.AutorizacaoDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.service.administrador.AutorizacaoService;

@Service
public class AutorizacaoServiceImpl implements AutorizacaoService{

	@Autowired
	private AutorizacaoDAO dao;
	
	@Override
	@Transactional("sic")
	public List<CsAutorizacao> findAll() throws JPAException {
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
	public CsAutorizacao findById(Long id) throws JPAException {
		return (CsAutorizacao) dao.findById(CsAutorizacao.class, id);
	}
}