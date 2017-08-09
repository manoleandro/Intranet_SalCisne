package br.com.sp.intranet.service.caixa;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.TpConta;

public interface TpContaService {
	
	public TpConta findById(Long id) throws JPAException;
	public List<TpConta> findAll () throws JPAException;
	boolean salvar(TpConta tpConta) throws JPAException;
	boolean alterar(TpConta tpConta) throws JPAException;
	boolean excluir(TpConta tpConta) throws JPAException;
		
}
