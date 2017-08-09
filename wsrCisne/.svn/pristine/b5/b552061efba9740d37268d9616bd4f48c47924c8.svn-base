package wsr.service.comercial.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wsr.exception.JPAException;
import wsr.model.comercial.RegistroVisita;
import wsr.model.comercial.repository.RegistroVisitaRepository;
import wsr.service.comercial.RelatorioVisitaService;

@Service
public class RelatorioVisitaServiceImpl implements RelatorioVisitaService{
	
	@Autowired
	private RegistroVisitaRepository repository;
	
	@Override
	@Transactional("analistaTransactionManager")
	public List<RegistroVisita> findRegistroVisitaByMes(Long zonaVendas, String mesAno){
		try {
			return repository.findRegistroVisitaByMes(zonaVendas, mesAno);
		} catch (JPAException e) {
			e.printStackTrace();
		}
		return null;
	}
}