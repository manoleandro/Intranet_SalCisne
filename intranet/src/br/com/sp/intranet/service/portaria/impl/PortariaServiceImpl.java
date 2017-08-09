package br.com.sp.intranet.service.portaria.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.sp.intranet.dao.portaria.PortariaDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.MovPortaria;
import br.com.sp.intranet.service.portaria.PortariaService;

@Service
public class PortariaServiceImpl implements PortariaService {
	
	
	@Autowired
	private PortariaDAO dao;
	
	@Override
	@Transactional("sic")
	public List<MovPortaria> findByStatus(int status) throws JPAException {
		List<MovPortaria> listMovPortaria = dao.findByStatus(status);
		return listMovPortaria;
	}

	@Override
	@Transactional("sic")
	public boolean delete(MovPortaria movPortaria) throws JPAException {
		return dao.delete(movPortaria);	
	}

	@Override
	@Transactional("sic")
	public boolean save(MovPortaria movPortaria) throws JPAException {
		return dao.save(movPortaria);
	}
	
	
	@Override
	@Transactional("sic")
	public boolean update(MovPortaria movPortaria) throws JPAException {
		return dao.update(movPortaria);
	}
	
	@Override
	@Transactional("sic")
	public List<MovPortaria> findAll() throws JPAException {
		return dao.findAll();
	}

	@Override
	@Transactional("sic")
	public MovPortaria findById(Long id) throws JPAException {
		return (MovPortaria) dao.findById(MovPortaria.class, id);
	}

	@Override
	@Transactional("sic")
	public List<MovPortaria> findConsultaMovPortaria(String dataInicio, String dataFim) throws JPAException {
		List<MovPortaria> listMovPortaria = dao.findConsultaMovPortaria(dataInicio, dataFim);
		return listMovPortaria;
	}
	
	@Override
	@Transactional("sic")
	public List<MovPortaria> getListMediaPortaria(String dataInicio, String dataFim) throws JPAException {
		List<MovPortaria> listMovPortaria = dao.getListMediaPortaria(dataInicio, dataFim);
		return listMovPortaria;
	}

	@Override
	@Transactional("sic")
	public List<Map> getQtdPesoTipoCargaTurno(String dataInicio, String dataFim, String horarioInicio, String horarioFim, String tipoCarga) throws JPAException {
		List<Map> listQtdPesoTipoCargaTurno = dao.getQtdPesoTipoCargaTurno(dataInicio, dataFim, horarioInicio, horarioFim, tipoCarga);
		return listQtdPesoTipoCargaTurno;
	}

	@Override
	@Transactional("sic")
	public List<Map> getQuantidadeVeiculosTurnoDetalhado(String dataInicio, String dataFim, String horarioInicio, String horarioFim, String tipoCarga) throws JPAException {
		List<Map> listQuantidadeVeiculosTurnoDetalhado = dao.getQuantidadeVeiculosTurnoDetalhado(dataInicio, dataFim, horarioInicio, horarioFim, tipoCarga);
		return listQuantidadeVeiculosTurnoDetalhado;
	}

	@Override
	@Transactional("sic")
	public List<Map> getQtdTotalContracao(String dataInicio, String dataFim, String contratacao) throws JPAException {
		List<Map> listQtdTotalContracao = dao.getQtdTotalContracao(dataInicio, dataFim, contratacao);
		return listQtdTotalContracao;
	}

	@Override
	@Transactional("sic")
	public List<Map> getQtdTotalTipoCarga(String dataInicio, String dataFim, String tipoCarga) throws JPAException {
		List<Map> listQtdTotalTipoCarga = dao.getQtdTotalTipoCarga(dataInicio, dataFim, tipoCarga);
		return listQtdTotalTipoCarga;
	}

	@Override
	@Transactional("sic")
	public List<Map> getQtdPesoContratacao(String dataInicio, String dataFim, String contratacao) throws JPAException {
		List<Map> listQtdPesoContrataca = dao.getQtdPesoContratacao(dataInicio, dataFim, contratacao);
		return listQtdPesoContrataca;
	}

	@Override
	@Transactional("sic")
	public List<Map> getQtdPesoTipoCarga(String dataInicio, String dataFim, String tipoCarga) throws JPAException {
		List<Map> listgetQtdPesoTipoCarga = dao.getQtdPesoTipoCarga(dataInicio, dataFim, tipoCarga);
		return listgetQtdPesoTipoCarga;
	}

	@Override
	@Transactional("sic")
	public List<Map> getQtdDiasMesCarregamento(String dataInicio, String dataFim) throws JPAException {
		List<Map> listQtdDiasMesCarregamento = dao.getQtdDiasMesCarregamento(dataInicio, dataFim);
		return listQtdDiasMesCarregamento;
	}

	@Transactional("sic")
	public List<Map> getQtdFaixaHorarioCarregamento(String dataInicio, String dataFim, String horarioInicio, String horarioFim) throws JPAException {
		List<Map> listQtdFaixaHorarioCarregamento = dao.getQtdFaixaHorarioCarregamento(dataInicio, dataFim, horarioInicio, horarioFim);
		return listQtdFaixaHorarioCarregamento;
	}

	@Override
	@Transactional("sic")
	public List<Map> getTemposCarregamento(String dataInicio, String dataFim, String tipoCarga, String contratacao, String horaInicio, String horaFim) throws JPAException {
		List<Map> listTemposCarregamento = dao.getTemposCarregamento(dataInicio, dataFim, tipoCarga, contratacao, horaInicio, horaFim);
		return listTemposCarregamento;
	}

	@Transactional("sic")
	public List<Map> getQtdFaixaHorario(String dataInicio, String dataFim, String horarioInicio, String horarioFim) throws JPAException {
		List<Map> listQtdFaixaHorario = dao.getQtdFaixaHorario(dataInicio, dataFim, horarioInicio, horarioFim);
		return listQtdFaixaHorario;
	}

	

	

	
	
	

}
