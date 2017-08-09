package br.com.sp.intranet.service.caixa;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Lancamentos;

public interface LancamentoService {
		

	public Lancamentos findById(Long id) throws JPAException;
	public List<Lancamentos> findAll () throws JPAException;
	boolean salvar(Lancamentos lancamentos) throws JPAException;
	boolean alterar(Lancamentos lancamentos) throws JPAException;
	boolean excluir(Lancamentos lancamentos) throws JPAException;
	
	public List<Lancamentos> findLancamentosPosteriores(String dataLancamento, Long idConta) throws JPAException;
	public List<Lancamentos> findFiltroGeral(Lancamentos lancamentos, String compSaldo) throws JPAException;
	public List somatorioValorPorTipoConta(String dataInicio, String dataFim, String compSaldo, Long idConta)throws JPAException;
	public void atualizaDiferencaSaldoPosterior(String dataLancamento, Double diferencaSaldo, Long idConta) throws JPAException;
	public Double somatorioValor(String dataInicio, String dataFim) throws JPAException;
	public void atualizaFechamentoConta(String dataFim) throws JPAException;
}
