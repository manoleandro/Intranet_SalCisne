package br.com.sp.intranet.service.caixa.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.caixa.BancosDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Bancos;
import br.com.sp.intranet.service.caixa.BancosService;

@Service
public class BancosServiceImpl implements BancosService {
		
	@Autowired
	private BancosDAO dao;

	@Override
	@Transactional("sic")
	public Bancos findById(Long id) throws JPAException {
		return dao.findById(id);
	}

	@Override
	@Transactional("sic")
	public List<Bancos> findAll() throws JPAException {
		return dao.findAll();
	}

	@Override
	@Transactional("sic")
	public boolean salvar(Bancos bancos) throws JPAException {
		return dao.save(bancos);
	}

	@Override
	@Transactional("sic")
	public boolean alterar(Bancos bancos) throws JPAException {
		return dao.update(bancos);
	}

	@Override
	@Transactional("sic")
	public boolean excluir(Bancos bancos) throws JPAException {
		return dao.delete(bancos);
	}

}
