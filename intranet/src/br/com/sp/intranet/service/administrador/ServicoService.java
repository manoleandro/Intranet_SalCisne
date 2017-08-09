package br.com.sp.intranet.service.administrador;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.CsServico;

public interface ServicoService {
	
	public List<CsServico> findAll() throws JPAException;
	
	public void delete(GenericEntity entity) throws JPAException;
	
	public void update(GenericEntity entity) throws JPAException;
	
	public void save(GenericEntity entity) throws JPAException;
	
	public CsServico findById(Long id) throws JPAException;

}