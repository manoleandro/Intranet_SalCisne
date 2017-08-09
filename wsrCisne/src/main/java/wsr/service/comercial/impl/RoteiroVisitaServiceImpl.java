package wsr.service.comercial.impl;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wsr.model.comercial.RoteiroVisita;
import wsr.model.comercial.repository.RoteiroVisitaRepository;
import wsr.service.comercial.RoteiroVisitaService;
import wsr.util.LocalDateUtils;

@Service
public class RoteiroVisitaServiceImpl implements RoteiroVisitaService{

	@Autowired
	private RoteiroVisitaRepository repository;
	
	@Override
	@Transactional("analistaTransactionManager")
	public List<RoteiroVisita> findRoteiroVisitaSugeridoByZonaVendas(String mesAno, Long zonaVendas) {
		LocalDate ultimoDiaMes = LocalDateUtils.obterDataPrimeiroDia(mesAno).with(TemporalAdjusters.lastDayOfMonth());
		String data = LocalDateUtils.formatarData(ultimoDiaMes, "dd/MM/yyyy");
		String mesAnoAnterior = LocalDateUtils.formatarData(ultimoDiaMes.minusMonths(1), "MM/yyyy");
		
		return repository.findRoteiroVisitaSugeridoByZonaVendas(data, mesAno, mesAnoAnterior, zonaVendas);
	}
	
	
	@Override
	@Transactional("analistaTransactionManager")
	public RoteiroVisita findRoteiroVisitaSugeridoByCliente(String mesAno, Long idCliente) {
		LocalDate ultimoDiaMes = LocalDateUtils.obterDataPrimeiroDia(mesAno).with(TemporalAdjusters.lastDayOfMonth());
		String data = LocalDateUtils.formatarData(ultimoDiaMes, "dd/MM/yyyy");
		String mesAnoAnterior = LocalDateUtils.formatarData(ultimoDiaMes.minusMonths(1), "MM/yyyy");
		
		return repository.findRoteiroVisitaSugeridoByCliente(data, mesAno, mesAnoAnterior, idCliente);
	}
}