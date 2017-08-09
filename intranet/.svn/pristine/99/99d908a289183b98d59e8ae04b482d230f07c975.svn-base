package br.com.sp.intranet.service.externo;

import java.util.List;


import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.externo.Contato;
import br.com.sp.intranet.model.externo.Externo;

public interface ContatoService {

	public void delete(Contato contato, Externo externo) throws JPAException;
	public void update(Contato contato, Externo externo) throws JPAException;
	public void save(Contato contatoNovo, Externo externo) throws JPAException;
	public List<Contato> findAll(Externo externo) throws JPAException;

}