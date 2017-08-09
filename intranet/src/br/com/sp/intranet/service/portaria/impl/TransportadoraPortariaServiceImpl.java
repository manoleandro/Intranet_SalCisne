package br.com.sp.intranet.service.portaria.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.portaria.TransportadoraPortariaDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.TransportadoraPortaria;
import br.com.sp.intranet.model.portaria.Veiculo;
import br.com.sp.intranet.service.portaria.TransportadoraPortariaService;

@Service
public class TransportadoraPortariaServiceImpl implements TransportadoraPortariaService {
	
	
	@Autowired
	private TransportadoraPortariaDAO dao;

	@Override
	@Transactional("sic")
	public List<TransportadoraPortaria> findAll() throws JPAException {
		List<TransportadoraPortaria> listTransportadorasPortaria = dao.findAll();
		return listTransportadorasPortaria;
	}

	@Override
	@Transactional("sic")
	public void delete(TransportadoraPortaria transportadoraPortaria) throws JPAException {
		dao.delete(transportadoraPortaria);	
	}

	@Override
	@Transactional("sic")
	public void save(TransportadoraPortaria transportadoraPortaria) throws JPAException {
		dao.save(transportadoraPortaria);
	}
	
	
	@Override
	@Transactional("sic")
	public void update(TransportadoraPortaria transportadoraPortaria) throws JPAException {
		dao.update(transportadoraPortaria);
	}
	
	@Override
	@Transactional("sic")
	public TransportadoraPortaria findById(TransportadoraPortaria transportadoraPortaria) throws JPAException {
		return (TransportadoraPortaria) dao.findById(TransportadoraPortaria.class, transportadoraPortaria.getIdTransportadora());
	}
	
}
