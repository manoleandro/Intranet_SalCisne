package wsr.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wsr.model.security.CsUsuario;
import wsr.model.security.repository.CsUsuarioRepository;
import wsr.service.security.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private CsUsuarioRepository repository;
	
	@Override
	@Transactional
	public CsUsuario findByUsername(String username){
		return repository.findByUsername(username);
	}
	
	@Override
	@Transactional
	public List<CsUsuario> findUsuariosApp(){
		return repository.findUsuariosApp();
	}
}
