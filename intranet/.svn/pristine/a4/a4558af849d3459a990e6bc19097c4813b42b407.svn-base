package br.com.sp.intranet.service.caixa;

import java.util.Date;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.SaldoConta;

public interface SaldoContaService {
	
	public Double findUltimoSaldo(Long idConta, Date dtLancamento) throws JPAException;
	public Double findSaldo(Long idConta, Date dataLancamento, boolean isSaldoDia) throws JPAException;
	public Double findSaldoInicial (Long idConta) throws JPAException;
	
	public void atualizaDiferencaSaldoPosterior(String dataLancamento, Double diferencaSaldo, Long idConta) throws JPAException;
	
	boolean salvar(SaldoConta saldoConta) throws JPAException;
	boolean excluir(SaldoConta saldoConta) throws JPAException;
	boolean alterar(SaldoConta saldoConta) throws JPAException;

}
