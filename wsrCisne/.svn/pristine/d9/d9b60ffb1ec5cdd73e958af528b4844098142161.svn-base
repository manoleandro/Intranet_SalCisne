package wsr.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wsr.model.security.CsServicoApp;
import wsr.model.security.repository.CsServicoAppRepository;
import wsr.service.security.ServicoAppService;

@Service
public class ServicoAppServiceImpl implements ServicoAppService {

	@Autowired
	private CsServicoAppRepository repository;
	
	@Override
	@Transactional
	public List<CsServicoApp> findAll(){
		return repository.findAll();
	}
	
	@Override
	@Transactional
	public CsServicoApp findById(Long id){
		return repository.findById(id);
	}
}