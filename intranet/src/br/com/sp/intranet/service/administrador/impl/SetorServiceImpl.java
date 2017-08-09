package br.com.sp.intranet.service.administrador.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.administrador.SetorDAO;
import br.com.sp.intranet.exception.JPAException;

import br.com.sp.intranet.model.administrador.CsSetor;
import br.com.sp.intranet.service.administrador.SetorService;

@Service
public class SetorServiceImpl implements SetorService {

	@Autowired
	private SetorDAO dao;

	@Override
	@Transactional("sic")
	public List<CsSetor> findAll() throws JPAException {
		return dao.findAll();
	}

	@Override
	@Transactional("sic")
	public CsSetor findByDescricao(String descricao) throws JPAException {
		return dao.findByDescricao(descricao);
	}

}