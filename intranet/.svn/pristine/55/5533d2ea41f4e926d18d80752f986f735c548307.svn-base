package br.com.sp.intranet.service.caixa;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Contas;


public interface ContasService {
	
	
	
	public List<Contas> findAll () throws JPAException;
	public Contas findById(Long id) throws JPAException;
	
	boolean salvar(Contas contas) throws JPAException;
	boolean alterar(Contas contas) throws JPAException;
	boolean excluir(Contas contas) throws JPAException;
	
	public List<Contas> findByProperty(String propertyName, Object value)throws JPAException;
	public List<Contas> findByBancosCompSaldo(Long idBanco, String compSaldo) throws JPAException;
	
}
