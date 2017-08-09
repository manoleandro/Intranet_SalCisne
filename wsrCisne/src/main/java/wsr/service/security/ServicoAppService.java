package wsr.service.security;

import java.util.List;

import wsr.model.security.CsServicoApp;

public interface ServicoAppService {

	public List<CsServicoApp> findAll();

	public CsServicoApp findById(Long id);

}