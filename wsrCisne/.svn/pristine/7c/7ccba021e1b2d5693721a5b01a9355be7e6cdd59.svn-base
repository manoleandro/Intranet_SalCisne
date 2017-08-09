package wsr.model.security.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOSecurity;
import wsr.model.security.CsServicoApp;

@Repository
public class CsServicoAppRepository extends GenericDAOSecurity{
	
	public CsServicoApp findById(Long id){
		CsServicoApp servicoApp = null;
		
		servicoApp = (CsServicoApp) getSession().get(CsServicoApp.class, id);
		
		return servicoApp;
	}
	
	@SuppressWarnings("unchecked")
	public List<CsServicoApp> findAll(){
		List<CsServicoApp> servicosApp = null;
		
		servicosApp = getSession().createQuery("Select model From CsServicoApp model").list();
		
		return servicosApp;
	}
}