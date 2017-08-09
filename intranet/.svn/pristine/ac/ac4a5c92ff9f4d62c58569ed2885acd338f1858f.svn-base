package br.com.sp.intranet.service.caixa.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.sp.intranet.dao.caixa.ContasDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Contas;
import br.com.sp.intranet.service.caixa.ContasService;

@Service
public class ContasServiceImpl implements ContasService {
		
	@Autowired
	private ContasDAO dao;

	@Override
	@Transactional("sic")
	public Contas findById(Long id) throws JPAException {
		return dao.findById(id);
	}

	@Override
	@Transactional("sic")
	public List<Contas> findAll() throws JPAException {
		return dao.findAll();
	}

	@Override
	@Transactional("sic")
	public boolean salvar(Contas contas) throws JPAException {
		return dao.save(contas);
	}

	@Override
	@Transactional("sic")
	public boolean alterar(Contas contas) throws JPAException {
		return dao.update(contas);
	}

	@Override
	@Transactional("sic")
	public boolean excluir(Contas contas) throws JPAException {
		return dao.delete(contas);
	}

	@Override
	@Transactional("sic")
	public List<Contas> findByProperty(String propertyName, Object value) throws JPAException {
		return dao.findByProperty(propertyName, value);
	}

	@Override
	@Transactional("sic")
	public List<Contas> findByBancosCompSaldo(Long idBanco, String compSaldo) throws JPAException {
		return dao.findByBancosCompSaldo(idBanco, compSaldo);
	}

}
