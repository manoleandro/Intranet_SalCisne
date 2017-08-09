package br.com.sp.intranet.service.caixa.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.sp.intranet.dao.caixa.ContasDAO;
import br.com.sp.intranet.dao.caixa.SaldoContaDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Contas;
import br.com.sp.intranet.model.caixa.SaldoConta;
import br.com.sp.intranet.service.caixa.ContasService;
import br.com.sp.intranet.service.caixa.SaldoContaService;

@Service
public class SaldoContaServiceImpl implements SaldoContaService {
		
	@Autowired
	private SaldoContaDAO dao;


	@Transactional("sic")
	public SaldoConta findById(Long id) throws JPAException {
		return dao.findById(id);
	}

	@Transactional("sic")
	public List<SaldoConta> findAll() throws JPAException {
		return dao.findAll();
	}

	@Override
	@Transactional("sic")
	public boolean salvar(SaldoConta saldoConta) throws JPAException {
		return dao.save(saldoConta);
	}

	@Override
	@Transactional("sic")
	public boolean alterar(SaldoConta saldoConta) throws JPAException {
		return dao.update(saldoConta);
	}

	@Override
	@Transactional("sic")
	public boolean excluir(SaldoConta saldoConta) throws JPAException {
		return dao.delete(saldoConta);
	}

	@Override
	@Transactional("sic")
	public Double findUltimoSaldo(Long idConta, Date dtLancamento) throws JPAException {
		return dao.findUltimoSaldo(idConta, this.formataData(dtLancamento));
	}

	@Override
	@Transactional("sic")
	public Double findSaldo(Long idConta, Date dataLancamento, boolean isSaldoDia) throws JPAException {
		return dao.findSaldo(idConta, dataLancamento, isSaldoDia);
	}

	@Override
	@Transactional("sic")
	public Double findSaldoInicial(Long idConta) throws JPAException {
		return dao.findSaldoInicial(idConta);
	}

	@Override
	@Transactional("sic")
	public void atualizaDiferencaSaldoPosterior(String dataLancamento, Double diferencaSaldo, Long idConta) throws JPAException {
		dao.atualizaDiferencaSaldoPosterior(dataLancamento, diferencaSaldo, idConta);
		
	}

	public String formataData(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR"));
		return dateFormat.format(data);
	}
}
