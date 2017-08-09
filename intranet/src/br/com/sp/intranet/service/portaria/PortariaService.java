package br.com.sp.intranet.service.portaria;

import java.util.List;
import java.util.Map;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.MovPortaria;

public interface PortariaService {
	
	boolean delete(MovPortaria movPortaria) throws JPAException;
	boolean save(MovPortaria movPortaria) throws JPAException;
	boolean update(MovPortaria movPortaria) throws JPAException;
	List<MovPortaria> findByStatus(int status) throws JPAException;
	List<MovPortaria> findConsultaMovPortaria(String dataInicio, String dataFim) throws JPAException;
	List<MovPortaria> findAll() throws JPAException;
	List<MovPortaria> getListMediaPortaria(String dataInicio, String dataFim) throws JPAException;
	@SuppressWarnings("rawtypes")
	List<Map> getQtdPesoTipoCargaTurno(String dataInicio, String dataFim, String horarioInicio, String horarioFim, String tipoCarga) throws JPAException;
	@SuppressWarnings("rawtypes")
	List<Map> getQuantidadeVeiculosTurnoDetalhado(String dataInicio, String dataFim, String horarioInicio, String horarioFim, String tipoCarga) throws JPAException;
	@SuppressWarnings("rawtypes")
	List<Map> getQtdTotalContracao(String dataInicio, String dataFim, String contratacao) throws JPAException;
	@SuppressWarnings("rawtypes")
	List<Map> getQtdTotalTipoCarga(String dataInicio, String dataFim, String tipoCarga) throws JPAException;
	@SuppressWarnings("rawtypes")
	List<Map> getQtdPesoContratacao(String dataInicio, String dataFim, String contratacao) throws JPAException;
	@SuppressWarnings("rawtypes")
	List<Map> getQtdPesoTipoCarga(String dataInicio, String dataFim, String tipoCarga) throws JPAException;
	@SuppressWarnings("rawtypes")
	List<Map> getQtdDiasMesCarregamento(String dataInicio, String dataFim) throws JPAException;
	@SuppressWarnings("rawtypes")
	List<Map> getQtdFaixaHorarioCarregamento(String dataInicio, String dataFim, String horarioInicio, String horarioFim) throws JPAException;
	@SuppressWarnings("rawtypes")
	List<Map> getTemposCarregamento(String dataInicio, String dataFim, String tipoCarga, String contratacao, String horaInicio, String horaFim) throws JPAException;
	@SuppressWarnings("rawtypes")
	List<Map> getQtdFaixaHorario(String dataInicio, String dataFim, String horarioInicio, String horarioFim) throws JPAException;
	
	
    MovPortaria findById(Long id) throws JPAException;
}
