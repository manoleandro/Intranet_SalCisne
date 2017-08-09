package br.com.sp.intranet.service.administrador.colaborador.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.administrador.colaborador.ColaboradorDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.vo.rh.Colaborador;
import br.com.sp.intranet.model.administrador.vo.rh.Ferias;
import br.com.sp.intranet.model.administrador.vo.rh.HistoricoAfastamento;
import br.com.sp.intranet.model.administrador.vo.rh.HistoricoSalario;
import br.com.sp.intranet.service.administrador.colaborador.ColaboradorService;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {
		
	@Autowired
	private ColaboradorDAO dao;
	
	@Override
	@Transactional("sic")
	public Colaborador findById(Long id) throws JPAException {
		return this.dao.findById(id);
	}

	@Override
	@Transactional("sic")
	public boolean salvar(Colaborador colaborador) throws JPAException {
		return this.dao.save(colaborador);
	}

	@Override
	@Transactional("sic")
	public boolean alterar(Colaborador colaborador) throws JPAException {
		return this.dao.update(colaborador);
	}

	@Override
	@Transactional("sic")
	public boolean excluir(Colaborador colaborador) throws JPAException {
		return this.dao.delete(colaborador);
	}

	@Override
	@Transactional("sic")
	public List<Colaborador> findAll() throws JPAException {
		return dao.findAll();
	}


	@Override
	@Transactional("sic")
	public int countTotal(Map<String, String> params, int inicia, int maxPorPagina) throws JPAException {
		return dao.countTotal(params, inicia, maxPorPagina);
	}

	

	@Override
	public List<HistoricoAfastamento> findAfastamento(Long idColaborador) throws JPAException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional("sic")
	public List<Ferias> findFerias(Long idColaborador) throws JPAException {
		return dao.findByHistFerias(idColaborador);
	}

	@Override
	@Transactional("sic")
	public List<HistoricoSalario> findSalario(Long idColaborador) throws JPAException {
		return dao.findByHistSalario(idColaborador);
	}

	

	@Override
	@Transactional("sic")
	public int getRowCount(Map<String, String> params, int inicia, int maxPorPagina) throws JPAException {
		return dao.countTotal(params, inicia, maxPorPagina);
	}

	@Override
	@Transactional("sic")
	public List<Colaborador> findByColaborador() throws JPAException {
		return dao.findByColaborador();
	}

	@Override
	@Transactional("sic")
	public List<Colaborador> findColaboradorAtivo() throws JPAException {
		return dao.findColaboradorAtivo();
	}
	
	



}
