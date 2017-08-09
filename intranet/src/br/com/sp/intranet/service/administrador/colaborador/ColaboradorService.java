package br.com.sp.intranet.service.administrador.colaborador;

import java.util.List;

import java.util.Map;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.vo.rh.Colaborador;
import br.com.sp.intranet.model.administrador.vo.rh.Ferias;
import br.com.sp.intranet.model.administrador.vo.rh.HistoricoAfastamento;
import br.com.sp.intranet.model.administrador.vo.rh.HistoricoSalario;

public interface ColaboradorService {
	
	public Colaborador findById(Long id) throws JPAException;
	public List<Colaborador> findAll () throws JPAException;
	boolean salvar(Colaborador colaborador) throws JPAException;
	boolean alterar(Colaborador colaborador) throws JPAException;
	boolean excluir(Colaborador colaborador) throws JPAException;
	public int countTotal(Map<String, String> params, int inicia, int maxPorPagina) throws JPAException;
	
	public List<Colaborador> findByColaborador() throws JPAException;
	public List<Colaborador> findColaboradorAtivo() throws JPAException;
	
	public int getRowCount(Map<String, String> params, int inicia, int maxPorPagina) throws JPAException;

	public List<HistoricoSalario> findSalario(Long idColaborador) throws JPAException;
	public List<HistoricoAfastamento> findAfastamento(Long idColaborador) throws JPAException;
	public List<Ferias> findFerias(Long idColaborador) throws JPAException;
	
}
