package br.com.sp.intranet.service.administrador.app;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.app.CsServicoApp;

public interface ServicoAppService {
	
	public List<CsServicoApp> findAll() throws JPAException;
	
	public void delete(GenericEntity entity) throws JPAException;
	
	public void update(GenericEntity entity) throws JPAException;
	
	public void save(GenericEntity entity) throws JPAException;
	
	public CsServico findById(Long id) throws JPAException;
}