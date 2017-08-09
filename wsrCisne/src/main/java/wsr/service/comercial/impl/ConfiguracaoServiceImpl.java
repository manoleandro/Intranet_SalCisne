package wsr.service.comercial.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wsr.exception.JPAException;
import wsr.model.comercial.Configuracao;
import wsr.model.comercial.repository.ConfiguracaoRepository;
import wsr.service.comercial.ConfiguracaoService;

@Service
public class ConfiguracaoServiceImpl implements ConfiguracaoService{
	
	@Autowired
	private ConfiguracaoRepository repository;
	
	@Override
	@Transactional("analistaTransactionManager")
	public Configuracao find() throws JPAException{
		return repository.find();
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public void update(Configuracao configuracao) throws JPAException{
		repository.update(configuracao);
	}

}
