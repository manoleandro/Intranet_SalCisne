package br.com.sp.intranet.service.caixa.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.sp.intranet.dao.caixa.LancamentoDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Lancamentos;
import br.com.sp.intranet.service.caixa.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {
		
	@Autowired
	private LancamentoDAO dao;

	@Override
	@Transactional("sic")
	public Lancamentos findById(Long id) throws JPAException {
		return dao.findById(id);
	}

	@Override
	@Transactional("sic")
	public List<Lancamentos> findAll() throws JPAException {
		return dao.findAll();
	}

	@Override
	@Transactional("sic")
	public boolean salvar(Lancamentos lancamentos) throws JPAException {
		return dao.save(lancamentos);
	}

	@Override
	@Transactional("sic")
	public boolean alterar(Lancamentos lancamentos) throws JPAException {
		return dao.update(lancamentos);
	}

	@Override
	@Transactional("sic")
	public boolean excluir(Lancamentos lancamentos) throws JPAException {
		return dao.delete(lancamentos);
	}

	@Override
	@Transactional("sic")
	public List<Lancamentos> findLancamentosPosteriores(String dataLancamento, Long idConta) throws JPAException {
		return dao.findLancamentosPosteriores(dataLancamento, idConta);
	}

	@Override
	@Transactional("sic")
	public List<Lancamentos> findFiltroGeral(Lancamentos lancamentos, String compSaldo) throws JPAException {
		return dao.findFiltroGeral(lancamentos, compSaldo);
	}

	@Override
	@Transactional("sic")
	public List somatorioValorPorTipoConta(String dataInicio, String dataFim, String compSaldo, Long idConta) throws JPAException {
		return dao.somatorioValorPorTipoConta(dataInicio, dataFim, compSaldo, idConta);
	}

	@Override
	@Transactional("sic")
	public void atualizaDiferencaSaldoPosterior(String dataLancamento, Double diferencaSaldo, Long idConta) throws JPAException {
		dao.atualizaDiferencaSaldoPosterior(dataLancamento, diferencaSaldo, idConta);
	}

	@Override
	@Transactional("sic")
	public Double somatorioValor(String dataInicio, String dataFim) throws JPAException {
		return dao.somatorioValor(dataInicio, dataFim);
	}

	@Override
	@Transactional("sic")
	public void atualizaFechamentoConta(String dataFim) throws JPAException {
		dao.atualizaFechamentoConta(dataFim);
		
	}


}
