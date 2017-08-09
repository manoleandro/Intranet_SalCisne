package br.com.sp.intranet.service.administrador.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.administrador.ServicoDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.CsGrupo;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.service.administrador.ServicoService;

@Service
public class ServicoServiceImpl implements ServicoService {
	
	@Autowired
	private ServicoDAO dao;
	
	
	@Override
	@Transactional("sic")
	public List<CsServico> findAll() throws JPAException {
		List<CsServico> servicos = dao.findAll();
		Collections.sort(servicos);
		return servicos;
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
	public CsServico findById(Long id) throws JPAException {
		return (CsServico) dao.findById(CsGrupo.class, id);
	}
}