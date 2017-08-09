package br.com.sp.intranet.service.administrador;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.model.administrador.CsGrupo;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.app.CsAutorizacaoApp;
import br.com.sp.intranet.model.administrador.app.CsServicoApp;

public interface GrupoService {
	
	public List<CsGrupo> findAll() throws JPAException;
	
	public void delete(GenericEntity entity) throws JPAException;

	public void update(GenericEntity entity) throws JPAException;

	public void save(GenericEntity entity) throws JPAException;

	public CsGrupo findById(Long id) throws JPAException;
	
	public List<CsServico> carregarServicos(CsGrupo grupo) throws JPAException;
	
	public List<CsAutorizacao> carregarAutorizacoes(CsGrupo grupo) throws JPAException;

	public List<CsServicoApp> carregarServicosApp(CsGrupo grupo) throws JPAException;

	public List<CsAutorizacaoApp> carregarAutorizacoesApp(CsGrupo grupo) throws JPAException;
}