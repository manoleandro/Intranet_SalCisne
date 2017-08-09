package br.com.sp.intranet.service.administrador.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.administrador.GrupoDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.model.administrador.CsGrupo;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.app.CsAutorizacaoApp;
import br.com.sp.intranet.model.administrador.app.CsServicoApp;
import br.com.sp.intranet.service.administrador.GrupoService;

@Service
public class GrupoServiceImpl implements GrupoService{
	
	@Autowired
	private GrupoDAO dao;

	@Override
	@Transactional("sic")
	public List<CsGrupo> findAll() throws JPAException {
		return dao.findAll();
	}

	@Override
	@Transactional("sic")
	public void delete(GenericEntity entity) throws JPAException {
		dao.delete(entity);
	}

	@Override
	@Transactional("sic")
	public void update(GenericEntity entity) throws JPAException {
		dao.update(entity);
	}

	@Override
	@Transactional("sic")
	public void save(GenericEntity entity) throws JPAException {
		dao.save(entity);
	}

	@Override
	@Transactional("sic")
	public CsGrupo findById(Long id) throws JPAException {
		return (CsGrupo) dao.findById(CsGrupo.class, id);
	}
	
	@Override
	@Transactional("sic")
	public List<CsServico> carregarServicos(CsGrupo grupo) throws JPAException {
		return dao.carregarServicos(grupo);
	}
	
	@Override
	@Transactional("sic")
	public List<CsAutorizacao> carregarAutorizacoes(CsGrupo grupo) throws JPAException{
		return dao.carregarAutorizacoes(grupo);
	}
	
	
	@Override
	@Transactional("sic")
	public List<CsServicoApp> carregarServicosApp(CsGrupo grupo) throws JPAException {
		return dao.carregarServicosApp(grupo);
	}
	
	@Override
	@Transactional("sic")
	public List<CsAutorizacaoApp> carregarAutorizacoesApp(CsGrupo grupo) throws JPAException{
		return dao.carregarAutorizacoesApp(grupo);
	}
}