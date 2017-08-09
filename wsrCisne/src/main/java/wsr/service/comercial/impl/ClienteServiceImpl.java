package wsr.service.comercial.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wsr.exception.JPAException;
import wsr.model.comercial.Cliente;
import wsr.model.comercial.repository.ClienteRepository;
import wsr.service.comercial.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	@Transactional("analistaTransactionManager")
	public List<Cliente> findAll() throws JPAException{
		return repository.findAll();
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public List<Long> findAllIdCliente() throws JPAException{
		return repository.findAllIdCliente();
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public List<Cliente> findByZonaVendas(Long zonaVendas) throws JPAException{
		return repository.findByZonaVendas(zonaVendas);
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public Cliente findById(Long id) throws JPAException{
		return repository.findByIdCliente(id);
	}
}