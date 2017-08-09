package br.com.sp.intranet.service.portaria.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.portaria.VeiculosPortariaDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.evento.Evento;
import br.com.sp.intranet.model.portaria.Veiculo;
import br.com.sp.intranet.service.portaria.VeiculosTransportadoraService;

@Service
public class VeiculosTransportadoraServiceImpl implements VeiculosTransportadoraService {
	
	
	@Autowired
	private VeiculosPortariaDAO dao;

	@Override
	@Transactional("sic")
	public List<Veiculo> findAll() throws JPAException {
		List<Veiculo> listVeiculos = dao.findAll();
		return listVeiculos;
	}

	@Override
	@Transactional("sic")
	public void delete(Veiculo veiculo) throws JPAException {
		dao.delete(veiculo);	
	}

	@Override
	@Transactional("sic")
	public void save(Veiculo veiculo) throws JPAException {
		dao.save(veiculo);
	}
	
	
	@Override
	@Transactional("sic")
	public void update(Veiculo veiculo) throws JPAException {
		dao.update(veiculo);
	}
	
	@Override
	@Transactional("sic")
	public Veiculo findById(Veiculo veiculo) throws JPAException {
		return (Veiculo) dao.findById(Veiculo.class, veiculo.getIdVeiculo());
	}

}
