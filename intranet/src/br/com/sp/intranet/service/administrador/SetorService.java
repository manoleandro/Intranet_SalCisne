package br.com.sp.intranet.service.administrador;

import java.util.List;
import br.com.sp.intranet.exception.JPAException;

import br.com.sp.intranet.model.administrador.CsSetor;


public interface SetorService {

	
	public List<CsSetor> findAll() throws JPAException;

	CsSetor findByDescricao(String descricao) throws JPAException;

	
}