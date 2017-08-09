package wsr.service.comercial.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wsr.exception.JPAException;
import wsr.model.GenericEntity;
import wsr.model.comercial.Cliente;
import wsr.model.comercial.Configuracao;
import wsr.model.comercial.DespesaVisita;
import wsr.model.comercial.DespesaVisitaCliente;
import wsr.model.comercial.RegistroVisita;
import wsr.model.comercial.repository.DespesaVisitaRepository;
import wsr.service.comercial.ClienteService;
import wsr.service.comercial.ConfiguracaoService;
import wsr.service.comercial.DespesaVisitaService;
import wsr.util.DataTypes;

@Service
public class DespesaVisitaServiceImpl implements DespesaVisitaService{
	
	@Autowired
	private DespesaVisitaRepository repository;
	
	@Autowired
	private ClienteService serviceCliente;
	
	@Autowired
	private ConfiguracaoService serviceConfiguracao;
	
	@Override
	@Transactional("analistaTransactionManager")
	public List<DespesaVisita> findDespesaVisitaByMes(String mesAno, Long zonaVendas) throws JPAException{
		return repository.findDespesaVisitaByMes(mesAno, zonaVendas);
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public DespesaVisita findByDiaAndZona(Date dia, Long zonaVendas) throws JPAException{
		return repository.findByDiaAndZona(dia, zonaVendas);
	}
	
	@Transactional("analistaTransactionManager")
	public void alterar(DespesaVisita despesaVisita) throws JPAException{
		repository.update(despesaVisita);
	}
	
	@Transactional("analistaTransactionManager")
	public void incluir(GenericEntity entity) throws JPAException{
		repository.save(entity);
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public void salvar(DespesaVisita despesaVisita) throws JPAException{
		despesaVisita = this.calcularTotalDespesa(despesaVisita);
		this.alterar(despesaVisita);	
	}
	
	public DespesaVisita calcularTotalDespesa(DespesaVisita despesaVisita) throws JPAException {
		Configuracao configuracao = serviceConfiguracao.find();
		Double total = new Double(0);
		
		if(despesaVisita != null && configuracao != null){
			total += DataTypes.parseNull(despesaVisita.getQuantidadeKm()) *  DataTypes.parseNull(configuracao.getValorKm());
			total += DataTypes.parseNull(despesaVisita.getQuantidadeDiarias()) * DataTypes.parseNull(configuracao.getValorDiaria());
			total += DataTypes.parseNull(despesaVisita.getQuantidadeRefeicoes()) * DataTypes.parseNull(configuracao.getValorRefeicao());
			total += DataTypes.parseNull(despesaVisita.getOutrosGastos());
		
			despesaVisita.setTotalDespesa(total);
		}
		return despesaVisita;
	}
	
	@SuppressWarnings("unused")
	@Override
	public void salvarDespesa(RegistroVisita registroVisita) throws JPAException{
		DespesaVisita despesaVisita = this.findByDiaAndZona(registroVisita.getPk().getDataVisitaReal(), registroVisita.getZona());
		
		Cliente cliente = serviceCliente.findById(registroVisita.getPk().getIdCliente());
		
		if(despesaVisita != null){
			Integer ordem = new Integer(despesaVisita.getVisitaClientes().size() + 1);
			DespesaVisitaCliente despesaCliente = new DespesaVisitaCliente(despesaVisita.getId(), cliente, ordem);
			despesaVisita.getVisitaClientes().add(despesaCliente);
			this.alterar(despesaVisita);
		
		}else{
			despesaVisita = new DespesaVisita(registroVisita.getPk().getDataVisitaReal(), null, registroVisita.getZona());
			this.incluir(despesaVisita);
			
			Integer ordem = new Integer(1);
			
			DespesaVisitaCliente despesaCliente = new DespesaVisitaCliente(despesaVisita.getId(), cliente, ordem);
			this.incluir(despesaCliente);
		}
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public DespesaVisita findById(Long idDespesa) throws JPAException{
		return repository.findById(idDespesa);
	}
}
