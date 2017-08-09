package br.com.sp.intranet.service.caixa;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Bancos;


public interface BancosService {
	
	public Bancos findById(Long id) throws JPAException;
	public List<Bancos> findAll () throws JPAException;
	boolean salvar(Bancos bancos) throws JPAException;
	boolean alterar(Bancos bancos) throws JPAException;
	boolean excluir(Bancos bancos) throws JPAException;
}
