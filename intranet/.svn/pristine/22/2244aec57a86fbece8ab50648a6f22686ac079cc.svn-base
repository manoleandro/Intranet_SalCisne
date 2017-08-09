package br.com.sp.intranet.service.caixa.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.sp.intranet.dao.caixa.HistoricoPadraoDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.HistoricoPadrao;
import br.com.sp.intranet.service.caixa.HistoricoPadraoService;

@Service
public class HistoricoPadraoServiceImpl implements HistoricoPadraoService {
		
	@Autowired
	private HistoricoPadraoDAO dao;

	@Override
	@Transactional("sic")
	public HistoricoPadrao findById(Long id) throws JPAException {
		return dao.findById(id);
	}

	@Override
	@Transactional("sic")
	public List<HistoricoPadrao> findAll() throws JPAException {
		return dao.findAll();
	}

	@Override
	@Transactional("sic")
	public boolean salvar(HistoricoPadrao historicoPadrao) throws JPAException {
		return dao.save(historicoPadrao);
	}

	@Override
	@Transactional("sic")
	public boolean alterar(HistoricoPadrao historicoPadrao) throws JPAException {
		return dao.update(historicoPadrao);
	}

	@Override
	@Transactional("sic")
	public boolean excluir(HistoricoPadrao historicoPadrao) throws JPAException {
		return dao.delete(historicoPadrao);
	}

	

}
