package br.com.sp.intranet.service.portaria;
import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.HistMovPortaria;

public interface HistoricoMovimentacaoPortariaService {
	
	void delete(HistMovPortaria histMovPortaria) throws JPAException;
	void save(HistMovPortaria histMovPortaria) throws JPAException;
	void update(HistMovPortaria histMovPortaria) throws JPAException;
	List<HistMovPortaria> findAll() throws JPAException;
	List<HistMovPortaria> findByData(String dataInicio, String dataFim) throws JPAException;
	HistMovPortaria findById(HistMovPortaria histMovPortaria) throws JPAException;

}
