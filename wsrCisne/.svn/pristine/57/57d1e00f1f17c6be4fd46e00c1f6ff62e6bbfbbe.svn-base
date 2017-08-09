package wsr.service.comercial.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wsr.exception.JPAException;
import wsr.model.comercial.Cliente;
import wsr.model.comercial.EstoqueCliente;
import wsr.model.comercial.EstoqueClienteInf;
import wsr.model.comercial.HistDataVisita;
import wsr.model.comercial.HistPlanejamentoVendas;
import wsr.model.comercial.Parametro;
import wsr.model.comercial.PrevisaoVendas;
import wsr.model.comercial.RegistroVisita;
import wsr.model.comercial.RegistroVisitaPK;
import wsr.model.comercial.repository.ClienteRepository;
import wsr.model.comercial.repository.EstoqueClienteInfRepository;
import wsr.model.comercial.repository.EstoqueClienteRepository;
import wsr.model.comercial.repository.HistDataVisitaRepository;
import wsr.model.comercial.repository.HistPlanejamentoVendasRepository;
import wsr.model.comercial.repository.MvEstClRepository;
import wsr.model.comercial.repository.NotaFiscalRepository;
import wsr.model.comercial.repository.ParametroRepository;
import wsr.model.comercial.repository.PedidoVendasRepository;
import wsr.model.comercial.repository.PrazoReposicaoRepository;
import wsr.model.comercial.repository.PrevisaoVendasRepository;
import wsr.model.comercial.repository.RegistroVisitaRepository;
import wsr.model.comercial.vo.EstoqueFimDoMes;
import wsr.service.comercial.DespesaVisitaService;
import wsr.service.comercial.RegistroVisitaService;
import wsr.util.DataTypes;
import wsr.util.DateUtils;
import wsr.util.LocalDateUtils;

@Service
public class RegistroVisitaServiceImpl implements RegistroVisitaService{
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter formatterMMyyyy = DateTimeFormatter.ofPattern("MM/yyyy");
	
	@Autowired
	private DespesaVisitaService serviceDespesa;
	
	@Autowired
	private CalculoSpvServiceImpl calculo;
	
	@Autowired
	private ClienteRepository repositoryCliente;
	
	@Autowired
	private NotaFiscalRepository repositoryNotaFiscal;
	
	@Autowired
	private PedidoVendasRepository repositoryPedido;
	
	@Autowired
	private PrazoReposicaoRepository repositoryPrazo;
	
	@Autowired
	private EstoqueClienteInfRepository repositoryEstoqueInformado;
	
	@Autowired
	private EstoqueClienteRepository repositoryEstoqueCliente;
	
	@Autowired
	private MvEstClRepository repositoryMvEstCl;
	
	@Autowired
	private PrevisaoVendasRepository repositoryPrevisao;
	
	@Autowired
	private ParametroRepository repositoryParametro;
	
	@Autowired
	private RegistroVisitaRepository repository;
	
	@Autowired
	private HistDataVisitaRepository repositoryDataVisita;
	
	@Autowired
	private HistPlanejamentoVendasRepository repositoryHistPlanejamentoVendas;
	
	@Override
	@Transactional("analistaTransactionManager")
	public void incluir(RegistroVisita registroVisita) throws JPAException{
		repository.save(registroVisita);
		serviceDespesa.salvarDespesa(registroVisita);
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public void alterar(RegistroVisita registroVisita) throws JPAException{
		repository.update(registroVisita);
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public RegistroVisita montarRegistroVisita(Long idCliente, String mesAno){
		RegistroVisita registroVisita = new RegistroVisita();
		
		LocalDate dataEstoqueZero = null;
		
		Cliente cliente = repositoryCliente.findByIdCliente(idCliente);
		
		Long consumoProgressivo = new Long(0);
		Long consumoProgressivoAnterior = new Long(0);
		
		//Vendedor
		registroVisita.setZona(cliente.getCodZonaVendas());
		registroVisita.setNomeVendedor(cliente.getNomeVendedor());
		registroVisita.setIdVendedor(cliente.getIdVendedor());
	
		//Cliente
		RegistroVisitaPK pk = new RegistroVisitaPK();
		pk.setIdCliente(cliente.getIdCliente());
		pk.setDataVisitaReal(new Date(System.currentTimeMillis()));
		
		registroVisita.setPk(pk);
		registroVisita.setNomeCliente(cliente.getRazao());
		registroVisita.setPraca(cliente.getNomeMunicipio());
		
		/*registroVisita.setDataVisitaReal(formatter.format(LocalDate.now()));*/
		
		LocalDate ultimoDiaMes = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		
		// Prazo reposicao
		String filialOrigemUltimoPedido = repositoryPedido.findFilialPedidoVendaMaxMes(idCliente, LocalDateUtils.convertToDate(ultimoDiaMes));
		Long prazoReposicao = DataTypes.parseNull(repositoryPrazo.findTotalDiasPrazoReposicao(filialOrigemUltimoPedido, cliente.getUf()));
		registroVisita.setDiasReposicao(prazoReposicao);

		// Entregas Pendentes
		Long quantidadeNfSemDataCanhoto = DataTypes.parseNull(repositoryNotaFiscal.findQuantidadeNfSemCanhotoMesByCliente(idCliente, mesAno));
		registroVisita.setEntregasPendentes(quantidadeNfSemDataCanhoto);
		
		
		// Dias de cobertura
		PrevisaoVendas previsao = repositoryPrevisao.findMaxPrevisaoMesByCliente(idCliente, mesAno);

		EstoqueCliente estoqueCliente = repositoryEstoqueCliente.findEstoqueClienteAnterior(idCliente, ultimoDiaMes);
		
		consumoProgressivoAnterior = repositoryEstoqueCliente.findUltimoConsumoProgressivo(idCliente, LocalDateUtils.obterDataPrimeiroDia(mesAno));
		
		if(estoqueCliente != null){
			consumoProgressivo = DataTypes.parseNull(estoqueCliente.getConsumoProgressivo());
		}else{
			consumoProgressivo = consumoProgressivoAnterior;
		}
		
		registroVisita.setConsumoKgDia(consumoProgressivo);
		
		if (previsao != null && estoqueCliente != null) {
			registroVisita.setDiasCobertura(previsao.getDias());

			// Preco
			Double precoCompra = repositoryPrevisao.findMaxPrecoMedio(idCliente);
			registroVisita.setPrevisaoPrecoCompra(DataTypes.parseNull(precoCompra));

			// Necessidade no mes
			registroVisita.setNecessidadeMes(consumoProgressivo * (DataTypes.parseNull(previsao.getDias()) + 30));
		}
		
		if(estoqueCliente != null){
			//Ultima data pedido
			LocalDate dataMesAnterior = LocalDateUtils.obterDataPrimeiroDia(mesAno).minusMonths(1);
			LocalDate ultimoDiaMesAnterior = dataMesAnterior.with(TemporalAdjusters.lastDayOfMonth());
			
			/*estoqueClienteMesAnterior = repositoryEstoqueCliente.findMaxEstoqueClienteByMes(idCliente, formatterMMyyyy.format(ultimoDiaMesAnterior));*/
			
			//Calculo Manual
			/*estoqueFimDoMesAnterior = findEstoqueFimDoMes(idCliente, ultimoDiaMesAnterior, estoqueClienteMesAnterior);*/
			
			HistPlanejamentoVendas histPlanejamentoVendas = repositoryHistPlanejamentoVendas.findByMes(dataMesAnterior.format(formatterMMyyyy), idCliente);
			
			Long quantidadeDiasEstoque = new Long(0); 
			
			if(histPlanejamentoVendas != null && histPlanejamentoVendas.getQuantidadeEstoque() != null && consumoProgressivoAnterior != 0){
				quantidadeDiasEstoque = histPlanejamentoVendas.getQuantidadeEstoque() / consumoProgressivoAnterior;
				registroVisita.setEstoqueMesFindo(DataTypes.parseNull(histPlanejamentoVendas.getQuantidadeEstoque()).longValue());
			}
			
			dataEstoqueZero = ultimoDiaMesAnterior.plusDays(quantidadeDiasEstoque);
			
			registroVisita.setDataSpv(LocalDateUtils.convertToDate(dataEstoqueZero.minusDays(prazoReposicao)));
			
			/*//Estoque fim do mes
			EstoqueFimDoMes estoqueFimDoMes = calcularEstoqueFimDoMes(idCliente, mesAno, estoqueCliente, quantidadeNfSemDataCanhoto);
			registroVisita.setEstoqueMesFindo(estoqueFimDoMes.getQuantidadeEstoque());*/
			
			/*registroVisita.setEstoqueMesFindo(previsao.getEstoque());*/
			
			//Estoque fim do mes anterior
		}
		
		HistDataVisita dataVisita = repositoryDataVisita.findMaxDataVisitaMesByIdCliente(idCliente, mesAno);

		if (dataVisita != null && dataEstoqueZero != null) {
			registroVisita.setDataVisitaSpv(LocalDateUtils.convertToDate(dataVisita.getDataVisita()));
			// Estoque dia da visita
			Long dias = ChronoUnit.DAYS.between(LocalDateUtils.convertToLocalDate(registroVisita.getDataVisitaSpv()), dataEstoqueZero);
			
			registroVisita.setEstoqueProjetado(consumoProgressivoAnterior * dias);
		}
		
		//Compra kg
		registroVisita.setPrevisaoCompraKg(DataTypes.parseNull(registroVisita.getNecessidadeMes()) 
				- DataTypes.parseNull(registroVisita.getEstoqueMesFindo()));
		
		registroVisita.setPrevisaoCompraKg(DataTypes.parseZero(registroVisita.getPrevisaoCompraKg()));
		
		//Compra R$
		registroVisita.setPrevisaoCompraRs(registroVisita.getPrevisaoCompraKg() * DataTypes.parseNull(registroVisita.getPrevisaoPrecoCompra()));
		
		//Ultima data de compra
		LocalDate dataUltimoPedido = repositoryPedido.findUltimaDataPedido(idCliente);
		
		if(dataUltimoPedido != null){
			registroVisita.setUltimaDataCompra(LocalDateUtils.convertToDate(dataUltimoPedido));
		}
		
		//verificacoes
		registroVisita.setPermiteEstoqueZero(this.verificacaoEstoqueZero(idCliente, DataTypes.parseNull(registroVisita.getConsumoKgDia())));
		
		return registroVisita;
	}
	
	public EstoqueFimDoMes calcularEstoqueFimDoMesInformado(Long idCliente, LocalDate data, EstoqueCliente estoqueCliente, Long quantidadeNfSemDataCanhoto, Long quantidadePedidoPendente) {
		String mesAno = data.format(formatterMMyyyy);
		
		EstoqueFimDoMes estoqueFimDoMes = new EstoqueFimDoMes();
		
		Integer quantidadeEstoque = new Integer(0);
		Long quantidadeEntrada = new Long(0);
		
		Long dias = new Long(0);
		Long consumoProgressivo = new Long(0);
		
		if(estoqueCliente != null){
			consumoProgressivo = DataTypes.parseNull(estoqueCliente.getConsumoProgressivo());
		}else{
			consumoProgressivo = repositoryEstoqueCliente.findUltimoConsumoProgressivo(idCliente, data);
		}
		LocalDate dataLancamento = null;
		
		//Estoque informado
		EstoqueClienteInf estoqueInformado = repositoryEstoqueInformado.findUltimoEstoqueInf(idCliente, mesAno);
		
		LocalDate ultimoDiaMes = data.with(TemporalAdjusters.lastDayOfMonth());
		
		if(estoqueInformado != null){
			quantidadeEstoque = DataTypes.parseNull(estoqueInformado.getQuantidadeEstoque());
			quantidadeEntrada = DataTypes.parseNull(repositoryMvEstCl.findQuantidadeEntregaByDatas(idCliente, estoqueInformado.getDataInclusao(), ultimoDiaMes));
			
			dias = ChronoUnit.DAYS.between(estoqueInformado.getDataInclusao(), ultimoDiaMes);
			dataLancamento = estoqueInformado.getDataInclusao();
			
		//Estoque pedido
		}else if(estoqueCliente != null && estoqueInformado == null){
			quantidadeEstoque = DataTypes.parseNull(estoqueCliente.getQuantidadeEstoque());
			quantidadeEntrada = DataTypes.parseNull(repositoryMvEstCl.findQuantidadeEntregaByDatas(idCliente, estoqueCliente.getDataLancto(), ultimoDiaMes));
			
			dias = ChronoUnit.DAYS.between(estoqueCliente.getDataFim(), ultimoDiaMes);

			dataLancamento = estoqueCliente.getDataFim();
		}else{
			return null;
		}
		
		estoqueFimDoMes = calculo.calcularEstoqueFimDoMes(quantidadeEstoque.longValue(), quantidadeEntrada, quantidadeNfSemDataCanhoto, consumoProgressivo, dias, quantidadePedidoPendente, dataLancamento);
		
		return estoqueFimDoMes;
	}
	
	// Estoque calculado
	public EstoqueFimDoMes calcularEstoqueFimDoMes(Long idCliente, LocalDate data) {
		EstoqueFimDoMes estoqueFimDoMes = new EstoqueFimDoMes();
		
		EstoqueCliente estoqueCliente = repositoryEstoqueCliente.findEstoqueClienteAnterior(idCliente, data);
		
		if(estoqueCliente != null){
			Long consumoProgressivo = new Long(0);
			
			for (LocalDate date = estoqueCliente.getDataFim(); date.isBefore(data); date = date.plusMonths(1)) {
				LocalDate ultimoDiaMes = date.with(TemporalAdjusters.lastDayOfMonth());
				String mesAno = date.format(formatterMMyyyy);
				Long dias = new Long(0);
				
				Long quantidadeNfSemDataCanhoto = DataTypes.parseNull(repositoryNotaFiscal.findQuantidadeNfSemCanhotoMesByCliente(idCliente, mesAno));
				Long quantidadePedidoPendente = DataTypes.parseNull(repositoryPedido.findQuantidadePedidoPendentesMesByCliente(idCliente, mesAno)).longValue();
				Long quantidadeEntrada = DataTypes.parseNull(repositoryMvEstCl.findQuantidadeEntregaMes(idCliente, mesAno));
				
				consumoProgressivo = estoqueCliente.getConsumoProgressivo();
				
				YearMonth iDate = YearMonth.from(date);
				YearMonth dataEstoquePedido = YearMonth.from(estoqueCliente.getDataFim());
				
				if (iDate.equals(dataEstoquePedido)) {
					dias = ChronoUnit.DAYS.between(estoqueCliente.getDataFim(), ultimoDiaMes);
					LocalDate dataLancamento = estoqueCliente.getDataFim();
					
					estoqueFimDoMes =  calculo.calcularEstoqueFimDoMes(estoqueCliente.getQuantidadeEstoque().longValue(), quantidadeEntrada, quantidadeNfSemDataCanhoto, consumoProgressivo, dias, quantidadePedidoPendente, dataLancamento);
				
				}else{
					LocalDate dataMesPassado = date.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
					dias = ChronoUnit.DAYS.between(dataMesPassado, ultimoDiaMes);
					LocalDate dataLancamento = estoqueCliente.getDataFim();
					
					estoqueFimDoMes =  calculo.calcularEstoqueFimDoMes(estoqueFimDoMes.getQuantidadeEstoque(), quantidadeEntrada, quantidadeNfSemDataCanhoto, consumoProgressivo, dias, quantidadePedidoPendente, dataLancamento);
				}
			}
		}
		
		return estoqueFimDoMes;
	}
	
	public EstoqueFimDoMes findEstoqueFimDoMes(Long idCliente, LocalDate data, EstoqueCliente estoqueCliente) {
		String mesAno = data.format(formatterMMyyyy);
		
		Long quantidadeNfSemDataCanhoto = DataTypes.parseNull(repositoryNotaFiscal.findQuantidadeNfSemCanhotoMesByCliente(idCliente, mesAno));
		Long quantidadePedidoPendente = DataTypes.parseNull(repositoryPedido.findQuantidadePedidoPendentesMesByCliente(idCliente, mesAno)).longValue();
		
		Long quantidadeEstoque = new Long(0);
		Long quantidadeEntrada = new Long(0);
		Long dias = new Long(0);
		LocalDate dataLancamento = null;
		
		//Estoque Informado
		EstoqueFimDoMes estoqueFimDoMes = this.calcularEstoqueFimDoMesInformado(idCliente, data, estoqueCliente, quantidadeNfSemDataCanhoto, quantidadePedidoPendente);
		
		//Estoque calculado
		if(estoqueFimDoMes == null || estoqueFimDoMes.getQuantidadeEstoque() == null){
			EstoqueCliente estoqueClienteAnterior = repositoryEstoqueCliente.findMaxEstoqueClienteByMes(idCliente, formatterMMyyyy.format(data.minusMonths(1)));
			
			EstoqueFimDoMes estoqueFimDoMesAnterior = this.calcularEstoqueFimDoMesInformado(idCliente, data.minusMonths(1), estoqueClienteAnterior, quantidadeNfSemDataCanhoto, quantidadeNfSemDataCanhoto);
			
			if(estoqueFimDoMesAnterior != null){
				quantidadeEstoque = DataTypes.parseNull(estoqueFimDoMesAnterior.getQuantidadeEstoque());
				quantidadeEntrada = DataTypes.parseNull(repositoryMvEstCl.findQuantidadeEntregaMes(idCliente, mesAno));
				
				LocalDate dataMesPassado = data.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
				dias = ChronoUnit.DAYS.between(dataMesPassado, data);
				dataLancamento = estoqueFimDoMesAnterior.getDataLancamento();
				
				estoqueFimDoMes = calculo.calcularEstoqueFimDoMes(quantidadeEstoque, quantidadeEntrada, quantidadeNfSemDataCanhoto, estoqueFimDoMesAnterior.getConsumoProgressivo(), dias, quantidadePedidoPendente, dataLancamento);
			}else{
				estoqueFimDoMes = this.calcularEstoqueFimDoMes(idCliente, data);
			}
		}
		
		return estoqueFimDoMes;
	}
	
	public boolean verificacaoEstoqueZero(Long idCliente, Long consumoProgressivo){
		Parametro parametro = repositoryParametro.findByIdParametro(Long.parseLong("1"));
		
		LocalDate dataUltimoPedido = repositoryPedido.findUltimaDataPedido(idCliente);
		
		if(dataUltimoPedido != null && isDataPedidoDentroPrazoCompra(dataUltimoPedido, parametro.getQuantidadeMesSemCompra()) && !DataTypes.isNull(consumoProgressivo)){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean isDataPedidoDentroPrazoCompra(LocalDate dataUltimoPedido, Integer quantidadeMeses){
		LocalDate hoje = LocalDate.now();
		
		Period periodo = Period.between(dataUltimoPedido, hoje);
		
		Integer diferencaMeses = periodo.getMonths();
		
		if(diferencaMeses <= quantidadeMeses){
			return true;
		}else{
			return false;
		}	
	}

	@Override
	@Transactional("analistaTransactionManager")
	public RegistroVisita findRegistroVisita(Long idCliente, String mesAno, String dia) {
		RegistroVisitaPK pk = new RegistroVisitaPK(idCliente, DateUtils.criarData(dia, "dd/MM/yyyy"));
		
		RegistroVisita registroVisitaDB = null;
		
		try {
			registroVisitaDB = repository.findById(pk);
		
		} catch (JPAException e) {
			e.printStackTrace();
		}
		if(registroVisitaDB != null){
			registroVisitaDB.setAlteracao(true);
			
			return registroVisitaDB;
		}else{
			return this.montarRegistroVisita(idCliente, mesAno);
		}
	}
}