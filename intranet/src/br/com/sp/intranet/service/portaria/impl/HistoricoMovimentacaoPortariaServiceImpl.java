package br.com.sp.intranet.service.portaria.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.portaria.HistoricoMovimentacaoPortariaDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.HistMovPortaria;
import br.com.sp.intranet.service.portaria.HistoricoMovimentacaoPortariaService;

@Service
public class HistoricoMovimentacaoPortariaServiceImpl implements HistoricoMovimentacaoPortariaService {
	
	
	@Autowired
	private HistoricoMovimentacaoPortariaDAO dao;

	@Override
	@Transactional("sic")
	public void delete(HistMovPortaria histMovPortaria) throws JPAException {
		dao.delete(histMovPortaria);
		
	}

	@Override
	@Transactional("sic")
	public void save(HistMovPortaria histMovPortaria) throws JPAException {
		dao.save(histMovPortaria);
		
	}

	@Override
	@Transactional("sic")
	public void update(HistMovPortaria histMovPortaria) throws JPAException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional("sic")
	public List<HistMovPortaria> findAll() throws JPAException {
		List<HistMovPortaria> listHistMovPortaria = dao.findAll();
		return listHistMovPortaria;
	}

	@Override
	@Transactional("sic")
	public HistMovPortaria findById(HistMovPortaria histMovPortaria) throws JPAException {
		return (HistMovPortaria) dao.findById(HistMovPortaria.class, histMovPortaria.getId());
	}

	@Override
	@Transactional("sic")
	public List<HistMovPortaria> findByData(String dataInicio, String dataFim) throws JPAException {
		List<HistMovPortaria> listHistMovPortaria = dao.findByData(dataInicio, dataFim);
		return listHistMovPortaria;
	}
	
}
