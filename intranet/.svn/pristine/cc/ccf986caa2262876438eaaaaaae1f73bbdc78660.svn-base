package br.com.sp.intranet.service.caixa.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.sp.intranet.dao.caixa.TpContaDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.TpConta;
import br.com.sp.intranet.service.caixa.TpContaService;

@Service
public class TpContaServiceImpl implements TpContaService {
		
	@Autowired
	private TpContaDAO dao;

	@Transactional("sic")
	public TpConta findById(Long id) throws JPAException {
		return dao.findById(id);
	}

	@Transactional("sic")
	public List<TpConta> findAll() throws JPAException {
		return dao.findAll();
	}

	@Override
	@Transactional("sic")
	public boolean salvar(TpConta tpConta) throws JPAException {
		return dao.save(tpConta);
	}

	@Override
	@Transactional("sic")
	public boolean alterar(TpConta tpConta) throws JPAException {
		return dao.update(tpConta);
	}

	@Override
	@Transactional("sic")
	public boolean excluir(TpConta tpConta) throws JPAException {
		return dao.delete(tpConta);
	}

}
