package br.com.sp.intranet.controller.caixa;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Bancos;
import br.com.sp.intranet.model.caixa.Contas;
import br.com.sp.intranet.model.caixa.Fechamento;
import br.com.sp.intranet.model.caixa.HistoricoPadrao;
import br.com.sp.intranet.model.caixa.Lancamentos;
import br.com.sp.intranet.model.caixa.ParametrosRelatorio;
import br.com.sp.intranet.model.caixa.SaldoConta;
import br.com.sp.intranet.model.caixa.SaldoContaPK;
import br.com.sp.intranet.model.caixa.TpConta;
import br.com.sp.intranet.service.caixa.BancosService;
import br.com.sp.intranet.service.caixa.ContasService;
import br.com.sp.intranet.service.caixa.HistoricoPadraoService;
import br.com.sp.intranet.service.caixa.LancamentoService;
import br.com.sp.intranet.service.caixa.SaldoContaService;
import br.com.sp.intranet.service.caixa.TpContaService;
import br.com.sp.intranet.util.DataTypes;
import br.com.sp.intranet.util.DateUtils;
import br.com.sp.intranet.util.GeracaoRelatorio;
import br.com.sp.intranet.util.PrintUtils;

@Controller
@Scope("view")
public class CaixaController extends GenericController implements Serializable {
	
	private static final long serialVersionUID = -2753499588829971671L;

	@Autowired
	private ContasService contasService;
	
	@Autowired
	private BancosService bancosService;
	
	@Autowired
	private TpContaService tpContaService;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private HistoricoPadraoService historicoPadraoService;
	
	@Autowired
	private SaldoContaService saldoContaService;
	
	
	private static final PrintUtils printUtils = PrintUtils.getInstance();
	private final String NOME_REL_SALDO_CAIXA = "/br/com/sp/intranet/resource/jasper/caixa/caixaSaldo3.jasper";
	private final String NOME_REL_SALDO_CAIXA_TOTAL = "/br/com/sp/intranet/resource/jasper/caixa/caixaCompSaldo2.jasper";
	private final String NOME_REL_CAIXA_SALDO_CONTA ="/br/com/sp/intranet/resource/jasper/caixa/caixaSaldoConta.jasper";
	
	private Contas contas = new Contas();
	private TpConta tpContas = new TpConta();
	private Bancos bancos = new Bancos();
	private Lancamentos lancamentos = new Lancamentos();
	private Lancamentos lancamentoFiltro = new Lancamentos();
	private Fechamento fechamento = new Fechamento();
	private HistoricoPadrao histPadrao = new HistoricoPadrao();
	private ParametrosRelatorio paramRelat = new ParametrosRelatorio();
	private SaldoConta saldo = new SaldoConta();
	
	private List<Contas> listContas = new ArrayList<Contas>();
	private List<TpConta> listTpContas = new ArrayList<TpConta>();
	private List<Bancos> listBancos = new ArrayList<Bancos>();
	private List<Lancamentos> listLancamentos = new ArrayList<Lancamentos>();
	private List<Fechamento> listFechamento = new ArrayList<Fechamento>();
	private List<HistoricoPadrao> listHistPadrao = new ArrayList<HistoricoPadrao>();
	
	private ContasModel listaContas;
	private LancamentosModel listaLancamentos;
	private HistPadraoModel listaHistPadrao;
	
	private List<Contas> listaContasFiltro;
	private List<Bancos> listaBancosFiltro;
	private List<Lancamentos> listaLancamentosFiltro;
	private List<TpConta> listaTPContasFiltro;
	private List<Fechamento> listaFechamentoFiltro;
	private List<HistoricoPadrao> listaHistPadraoFiltro;
	private Lancamentos lancamentoSelected;
		
	private Contas contaSelecionada;
	private Bancos bancoSelecionado;
	private TpConta tpContaSelecionada;
	private Lancamentos lancamentoSelecionado;
	private HistoricoPadrao histPadraoSelecionado;
	
	private boolean isCompSaldo;
	private boolean isInclusao = false;
	
	private Double total;
	private String strTotal;
	
	private StreamedContent relatorioSaldoCaixa;
	private StreamedContent relatorioSaldoCaixaTotal;
	private StreamedContent relatorioSaldoConta;
	
	private String compFluxo;
	
	private Long codLancamento;
	
	public void init() throws JPAException {
		if (!FacesContext.getCurrentInstance().isPostback()) {
		this.atualizaFechamentoConta();
		listarLancamentos("1", newLancamentosFiltro(false));
		setLancamentos(lancamentos);
		}
	}
	
	public void mudaData() {
		System.out.println("data: " + lancamentoFiltro.getDataInicio());
	}

	public void consultarSaldo() throws JPAException{
		Double saldo = DataTypes.parseNull(this.saldoContaService.findSaldo(fechamento.getIdConta(), fechamento.getCompetencia(), true));
		
		//Incluindo Saldo Inicial
		saldo += this.saldoContaService.findSaldoInicial(fechamento.getIdConta());
		fechamento.setSaldo(saldo);
	}	
	
	
	 public void onRowSelect(SelectEvent event) {
		 lancamentos = new Lancamentos();
		 lancamentos = lancamentoSelected;
		 System.out.println("desc " + lancamentos.getDescricao());
    }
	
	public void prepararRelatorioSaldoTot() throws JPAException{
		DateUtils dateU = new DateUtils();
		paramRelat = new ParametrosRelatorio();
		paramRelat.setDataInicio(new Date(System.currentTimeMillis()));
		setParamRelat(paramRelat);
		if(listBancos==null || listBancos.isEmpty()){
			prepararBancos();
		}
	}
	
	public void prepararConsultaSaldo() throws JPAException{
		fechamento.setCompetencia(new Date(System.currentTimeMillis()));
	}
	
	public void prepararRelatorio() throws JPAException{
		newLancamentos(false);
		prepararContas();
	}
	
	
	
	public StreamedContent emitirRelatorioSaldoConta(){
		relatorioSaldoConta = new DefaultStreamedContent();
		try {
			List<Contas> listContas = new ArrayList<Contas>();
			listContas = this.contasService.findByBancosCompSaldo(paramRelat.getIdBanco(), paramRelat.getCompFluxo());
			HttpServletRequest request = getRequest();
			JasperPrint jasperPrint = this.imprimirRelatorioCaixaSaldoConta(this.getSaldoContas(listContas, paramRelat.getDataInicio()), paramRelat.getDataInicio());
			request.getSession().setAttribute( BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint );
			relatorioSaldoConta = GeracaoRelatorio.gerarRelatorio( request, "Saldo Caixa por Conta");
			
			setRelatorioSaldoConta(relatorioSaldoConta);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return relatorioSaldoConta;
	}
	
	public StreamedContent emitirRelatorioSaldoTotCaixa(){
		relatorioSaldoCaixaTotal = new DefaultStreamedContent();
		try {
			HttpServletRequest request = getRequest();
			JasperPrint jasperPrint = this.imprimirRelatorioSaldoCaixaTotal(paramRelat);
			request.getSession().setAttribute( BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint );
			relatorioSaldoCaixaTotal = GeracaoRelatorio.gerarRelatorio( request, "Saldo Caixa Total");
			
			setRelatorioSaldoCaixaTotal(relatorioSaldoCaixaTotal);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return relatorioSaldoCaixaTotal;
	}
	
	public StreamedContent emitirRelatorio(){
		relatorioSaldoCaixa = new DefaultStreamedContent();
		try {
			Lancamentos lancamento = lancamentoSelecionado;
			contas = new Contas();
			contas = this.contasService.findById(lancamentoSelecionado.getContas().getIdConta());
			lancamento.setContas(contas);
			Double saldoAnterior = new Double("0");
			/*saldoAnterior = DataTypes.parseNull(implSaldo.findUltimoSaldo(lancamento.getContas().getIdConta(), lancamento.getDataInicio()));*/
			saldoAnterior = DataTypes.parseNull(this.saldoContaService.findSaldo(lancamento.getContas().getIdConta(), lancamento.getDataInicio(), false));
			//Incluindo saldo inicial
			saldoAnterior += DataTypes.parseNull(this.saldoContaService.findSaldoInicial(lancamento.getContas().getIdConta()));
			
			HttpServletRequest request = getRequest();
			JasperPrint jasperPrint = this.imprimirRelatorioSaldoCaixa(lancamento, saldoAnterior);
			request.getSession().setAttribute( BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint );
			relatorioSaldoCaixa = GeracaoRelatorio.gerarRelatorio( request, "Saldo Caixa ");
			
			setRelatorioSaldoCaixa(relatorioSaldoCaixa);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return relatorioSaldoCaixa;
	}
	
	public void excluirHistPadrao() throws JPAException {
		if(this.historicoPadraoService.excluir(histPadraoSelecionado)){
			prepararHistoricoPadrao();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O histórico foi excluído com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível excluir o registro! pois o mesmo esta vinculado a outras tabelas" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		listHistPadrao = this.historicoPadraoService.findAll();
	}
	
	public void prepararInclusaoHistPadrao() throws JPAException{
		histPadrao = new HistoricoPadrao();
		setHistPadrao(histPadrao);
		isInclusao = true;
	}
	
	public void prepararAlteracaoHistPadrao() throws JPAException{
		isInclusao = false;
		setHistPadrao(histPadraoSelecionado);
	}
		
	public void salvarHistPadrao() throws JPAException{
		if(isInclusao){
			if(this.historicoPadraoService.salvar(histPadraoSelecionado)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O histórico salvo com Sucesso!" , "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}	
		}else{
			if(this.historicoPadraoService.alterar(histPadraoSelecionado)){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O histórico alterado com Sucesso!" , "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}	
		}
		prepararHistoricoPadrao();
	}
	
	public void prepararHistoricoPadrao() throws JPAException{
		listHistPadrao = this.historicoPadraoService.findAll();
		HistPadraoModel lista = new HistPadraoModel(listHistPadrao);
		setListaHistPadrao( lista );
		setListaHistPadraoFiltro( null );
		setHistPadraoSelecionado(null);
	}
	
	public void listarLancamentos() throws JPAException{
		Date hoje = new Date(System.currentTimeMillis());
		lancamentos = new Lancamentos();
		lancamentos.setDataFim(hoje);
		lancamentos.setDataInicio(hoje);
		listarLancamentos("1",lancamentos); 
	}
	
	public void prepararAlterarLancamento() throws JPAException{
		isInclusao = false;
		lancamentoSelecionado.setStrIdConta(lancamentoSelecionado.getContas().getIdConta().toString());
		lancamentoSelecionado.setStrIdHistPadrao("");
		if(lancamentoSelecionado.getValor() < 0){
			lancamentoSelecionado.setValor(lancamentoSelecionado.getValor() * -1);
		}
				
		if(lancamentoSelecionado.getContas() != null){
			Contas contas = new Contas();
			contas.setIdConta(lancamentoSelecionado.getContas().getIdConta());
			contas.setDescricao(lancamentoSelecionado.getContas().getDescricao());
			contas.setConta(lancamentoSelecionado.getContas().getConta());
			lancamentoSelecionado.setContas(contas);
		}
		setLancamentos(lancamentoSelecionado);
	}
	
	public void prepararAlterarLancamentoRetroativo() throws JPAException{
		lancamentoSelecionado = this.lancamentoService.findById(codLancamento);
		prepararAlterarLancamento();
	}
	
	public void prepararAlterar() {
		lancamentos = new Lancamentos();
		lancamentos = lancamentoSelecionado;
	}
	
/*	public void salvarFechamento() throws JPAException{
		if(implFecha.salvarFechamento(fechamento) && implLanc.atualizaFechamentoConta(fechamento)){
			listarLancamentos("1", newLancamentos());
			setLancamentos(lancamentos);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O Fechamento foi incluído com sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O fechamento nÃ£o foi salvo, verifique as informaÃ§Ãµes!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void prepararSaldoFechar() throws JPAException{
		if(fechamento.getCompetencia() != null)
			fechamento = implLanc.getFechamento(fechamento);
	}
	
	public void prepararFechar() throws JPAException{
		fechamento = new Fechamento();
		fechamento.setSaldo(new Double("0"));
	}
	
	public void prepararFechamento() throws JPAException{
		listFechamento = implFecha.findAll();
		fechamento = new Fechamento();
	}*/
	
	public Contas newContas(){
		contas = new Contas();
		contas.setTpConta(new TpConta());
		contas.setBanco(new Bancos());
		setCompSaldo(false);
		return contas;
	}
	
	public Lancamentos newLancamentosFiltro(boolean isRetroativo){
		lancamentoFiltro = new Lancamentos();
		lancamentoFiltro = setDatas(isRetroativo);
		contas = new Contas();
		contas.setBanco(new Bancos());
		contas.setTpConta(new TpConta());
		lancamentoFiltro.setContas(contas);
		return lancamentoFiltro;
	}
	
	public Lancamentos newLancamentos(boolean isRetroativo){
		lancamentos = new Lancamentos();
		lancamentos = setDatas(isRetroativo);
		contas = new Contas();
		contas.setBanco(new Bancos());
		contas.setTpConta(new TpConta());
		lancamentos.setContas(contas);
		return lancamentos;
	}
	
	public Lancamentos setDatas(boolean isReatroativo){
		Date hoje = new Date(System.currentTimeMillis());
		DateUtils dateU = new DateUtils();
		lancamentos.setDataFim(hoje);
		lancamentos.setDataInicio(hoje);
		lancamentos.setDtLancamento(hoje);
		if(!isReatroativo){
			lancamentos.setDataInicioLancPermitido(dateU.adicionaDias(hoje, -5));
			lancamentos.setDataFimLancPermitido(hoje);
		}	
		return lancamentos;
	}
	
	//caixaController.compFluxo, caixaController.lancamentoFiltro
	public void listarLancamentos(String compFluxo, Lancamentos lancamento) throws JPAException{
		prepararComboBancosTpContas();
		contas = newContas();
		prepararContas();
		if(lancamento.getDataInicio()== null || lancamento.getDataFim()==null){
			lancamento = setDatas(false);
		}
		listLancamentos = this.lancamentoService.findFiltroGeral(lancamento, compFluxo);
		LancamentosModel lista = new LancamentosModel(listLancamentos);
		setListaLancamentos( lista );
		setListaLancamentosFiltro( null );
		
		Double total = this.calculaTotal(listLancamentos);
		setTotal(total);
		setStrTotal(this.formataBigDecimal(total));
		/*setLancamentos(lancamentos);*/
		setCompFluxo(compFluxo);
		
	}
	
	public void alterar() throws JPAException{
		if(this.lancamentoService.salvar(lancamentos)){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O lançamento foi alterado com Sucesso!", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O lançamento não foi alterado, verifique as informações!", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		listarLancamentos(compFluxo, lancamentoFiltro);
	}
	
	public void salvarLancamento() throws JPAException{
		if(lancamentoSelecionado.getValor().equals(new Double(0))){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Valor não está preenchido", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
		
			contas = new Contas();
			contas = this.contasService.findById(new Long(lancamentoSelecionado.getStrIdConta()));
			SaldoConta saldoConta = new SaldoConta();
		
			if(contas==null || histPadrao == null){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "A conta ou o histórico não está cadastrado(a)!" , "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				if (this.gravarInclusao(lancamentoSelecionado, contas, saldoConta)) {
					listarLancamentos(new Integer(lancamentos.getContas().getCompSaldo()).toString(), lancamentos);
					prepararLancamento();
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O lançamento foi incluído com Sucesso!", "");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O lançamento não foi incluído, verifique as informações!", "");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
		}
	}
	
	public SaldoConta getSaldoConta(Lancamentos lancamento, boolean isAlteracaoInclusao) throws JPAException{
		if(lancamento.getDebCred().equalsIgnoreCase("D") && lancamento.getValor() > 0){
			lancamento.setValor(lancamento.getValor()*-1);
		}
		if(isAlteracaoInclusao){
			lancamento.setValor(lancamento.getValor() * -1);
		}
		return this.getSaldoConta(lancamento);
	}
	
	public SaldoConta getSaldoContaAlteracao(Lancamentos lancamento) throws JPAException {
		SaldoConta saldoConta = new SaldoConta();
		if (lancamento.getDebCred().equalsIgnoreCase("D")) {
			lancamento.setValor(lancamento.getValor() * -1);
		}
		Lancamentos lancamentoDB = new Lancamentos();
		lancamentoDB = this.lancamentoService.findById(lancamento.getIdLancamento());
		boolean isMesmoValor = true;
		boolean isMesmaConta = true;
		if (lancamento.getValor() != lancamentoDB.getValor()) {
			isMesmoValor = false;
		}
		if (lancamento.getContas().getIdConta() != lancamentoDB.getContas().getIdConta()) {
			isMesmaConta = false;
		}
		if (!isMesmaConta || !isMesmoValor) {
			saldoConta = this.getSaldoContaAlteracao(lancamento, lancamentoDB, isMesmaConta, isMesmoValor);
		}
		return saldoConta;
	}
	
	public void selecionarHistPadrao() throws JPAException{
		if(histPadraoSelecionado != null){
			lancamentos.setDescricao(histPadraoSelecionado.getDescricao());
			lancamentos.setStrIdHistPadrao(histPadraoSelecionado.getId().toString());
			setLancamentos(lancamentos);
		}
	}
	
	public void selecionarHistPadraoId() throws JPAException{
		if(lancamentos.getStrIdHistPadrao() != null && lancamentos.getStrIdHistPadrao() != ""){
			HistoricoPadrao hist = this.historicoPadraoService.findById(new Long(lancamentos.getStrIdHistPadrao()));
			if(hist != null){
				lancamentos.setDescricao(hist.getDescricao());
				setLancamentos(lancamentos);
			}else{
				lancamentos.setDescricao("");
			}
		}
	}
	
	public void selecionarContaId() throws JPAException{
		if(lancamentos.getStrIdConta() != null && lancamentos.getStrIdConta() != ""){
			Contas conta = this.contasService.findById(new Long(lancamentos.getStrIdConta()));
			if(conta!=null){
				lancamentos.getContas().setDescricao(conta.getDescricao());
				lancamentos.getContas().setConta(conta.getConta());
			}else{
				lancamentos.getContas().setDescricao("");
				lancamentos.getContas().setConta("");
			}	
		}else{
			lancamentos.getContas().setDescricao("");
			lancamentos.getContas().setConta("");
		}
	}
	
	public void selecionarConta() throws JPAException{
		if(contaSelecionada != null){
			lancamentos.getContas().setDescricao(contaSelecionada.getDescricao());
			lancamentos.getContas().setConta(contaSelecionada.getConta());
			lancamentos.setStrIdConta(contaSelecionada.getIdConta().toString());
			setLancamentos(lancamentos);
		}	
	}
	
	public void prepararLancamento() throws JPAException{
		isInclusao = true;
		newLancamentos(false);
		lancamentos.setDebCred("D");
		lancamentos.setStrIdConta("");
		lancamentos.setStrIdHistPadrao("");
	}
	
	/*public void prepararLancamentoRetroativo() throws JPAException{
		isInclusao = true;
		newLancamentos(true);
		lancamentos.setDebCred("D");
		lancamentos.setStrIdConta("");
		lancamentos.setStrIdHistPadrao("");
	}*/
	
	public void prepararBancos() throws JPAException{
		listBancos = this.bancosService.findAll();
	}
	
	public void prepararInclusaoBanco() throws JPAException{
		isInclusao = true;
		bancos = new Bancos();
		setBancos(bancos);
	}
	
	public void prepararAlteracaoBanco() throws JPAException{
		isInclusao = false;
		setBancos(bancoSelecionado);
	}
	
	public void salvarBanco() throws JPAException{
		if(isInclusao){
			this.bancosService.salvar(bancoSelecionado);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O Banco foi incluído com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			this.bancosService.alterar(bancoSelecionado);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O Banco foi alterado com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		prepararBancos();
	}
	
	public void excluirBanco() throws JPAException{
		if(this.bancosService.excluir(bancoSelecionado)){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O Banco foi excluído com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nãoo foi possível excluir o registro! pois o mesmo esta vinculado a outras tabelas" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		listBancos = this.bancosService.findAll();
	}
	
	public void prepararTipoContas() throws JPAException{
		listTpContas = this.tpContaService.findAll();
	}
	
	public void prepararInclusaoTpConta(){
		tpContas = new TpConta();
		setTpContas(tpContas);
		isInclusao = true;
	}
	public void prepararAlteracaoTpConta() throws JPAException{
		isInclusao = false;
		setTpContas(tpContaSelecionada);
	}
	
	public void salvarTpConta() throws JPAException{
		if(isInclusao){
			this.tpContaService.salvar(tpContaSelecionada);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O Tipo de Conta foi incluído com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			this.tpContaService.alterar(tpContaSelecionada);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O Tipo de Conta foi alterado com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		prepararTipoContas();
	}
	
	public void excluirTpConta() throws JPAException{
		if(this.tpContaService.excluir(tpContaSelecionada)){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O Tipo de Conta foi excluído com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível excluir o registro! pois o mesmo esta vinculado a outras tabelas" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		listTpContas = this.tpContaService.findAll();
	}
	
	public void prepararContas() throws JPAException{
		listContas = this.contasService.findAll();
		for(Iterator<Contas> it = listContas.iterator();it.hasNext();){
			Contas contaIt = it.next();
			contaIt.getBanco().getId();
			contaIt.getBanco().getDescricao();
			contaIt.getTpConta().getId();
			contaIt.getTpConta().getDescricao();
		}
		ContasModel lista = new ContasModel(listContas);
		setListaContas( lista );
		setListaContasFiltro( null );
		setContaSelecionada(null);
	}
	
	public void prepararInclusaoContas() throws JPAException{
		isInclusao = true;
		isCompSaldo = false;
		prepararComboBancosTpContas();
		contas = newContas();
	}
	
	public void prepararComboBancosTpContas() throws JPAException{
		if(listBancos == null || listBancos.isEmpty()){
			listBancos = this.bancosService.findAll();
		}
		if(listTpContas == null || listTpContas.isEmpty()){
			listTpContas = this.tpContaService.findAll();
		}	
	}
	
	public void prepararAlteracaoContas() throws JPAException{
		isInclusao = false;
		if(contaSelecionada.getCompSaldo()==1){
			setCompSaldo(true);
		}else{
			setCompSaldo(false);
		}
		prepararComboBancosTpContas();
		
		if(contaSelecionada.getTpConta() != null){
			TpConta tpConta = new TpConta();
			tpConta.setId(contaSelecionada.getTpConta().getId());
			contaSelecionada.setTpConta(tpConta);
		}
		
		if(contaSelecionada.getBanco() != null){
			Bancos banco = new Bancos();
			banco.setId(contaSelecionada.getBanco().getId());
			contaSelecionada.setBanco(banco);
		}
		contas = contaSelecionada;
	}
	
	public void salvarConta() throws JPAException{
		Bancos banco= this.bancosService.findById(contaSelecionada.getBanco().getId());
		TpConta tpConta = this.tpContaService.findById(contaSelecionada.getTpConta().getId());
		
		if(banco != null && tpConta != null){
			if(isInclusao){
				this.gravarInclusao(isCompSaldo, contaSelecionada, banco, tpConta);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "A Conta foi incluída com Sucesso!" , "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			
			}else{
				this.gravarAlteracao(isCompSaldo, contaSelecionada, banco, tpConta);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "A Conta foi alterada com Sucesso!" , "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O registro não foi salvo! Selecione o Tipo de Conta e o Banco!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		prepararContas();
	}
	
	public void excluirConta() throws JPAException{
		if(this.contasService.excluir(contaSelecionada)){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "A Conta foi excluída com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível excluir o registro! pois o mesmo esta vinculado a outras tabelas!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		prepararContas();
	}
	
	
	public void gravarAlteracao(boolean isCompSaldo, Contas contaSelecionada, Bancos banco, TpConta tpConta) throws JPAException{
		if(isCompSaldo){
			contaSelecionada.setCompSaldo(1);
		}else{	
			contaSelecionada.setCompSaldo(0);
		}
		contaSelecionada.setBanco(banco);
		contaSelecionada.setTpConta(tpConta);

		if(this.contasService.alterar(contaSelecionada)) {
			
		} else {
			
		}

	}
	
	public void gravarInclusao(boolean isCompSaldo, Contas contaSelecionada, Bancos banco, TpConta tpConta) throws JPAException{
		if(isCompSaldo){
			contaSelecionada.setCompSaldo(1);
		}else{	
			contaSelecionada.setCompSaldo(0);
		}
		Contas contas = new Contas(); 
		contas = contaSelecionada;
		contas.setBanco(banco);
		contas.setTpConta(tpConta);
		
		if(this.contasService.salvar(contaSelecionada)) {
			
		} else {
			
		}
	}
	
	public Double calculaTotal(List<Lancamentos> lista){
		Double total = new Double("0");
		NumberFormat nf = NumberFormat.getInstance();    
		
		for (Lancamentos lancamentos : lista) {
			total = total + lancamentos.getValor();
		}
		if(total.intValue() == 0){
			total = new Double("0");
		}
		return total;
	}
	
	public String formataBigDecimal(Double valor) {
		DecimalFormat df = new java.text.DecimalFormat( "#,##0.00" );
		return df.format(valor);
	}
	
	
	public JasperPrint imprimirRelatorioSaldoCaixaTotal( ParametrosRelatorio paramRelat) throws JPAException{
		
		DateUtils dateU           = new DateUtils();
		Double valeDiretoriaTotal = new Double("0");
		Double numerarioTotal     = new Double("0");
		Double chequeTotal        = new Double("0");
		
		Double valeDiretoriaTotalAnterior = new Double("0");
		Double numerarioTotalAnterior     = new Double("0");
		Double chequeTotalAnterior        = new Double("0"); 
		
		Integer mesGeracao = new Integer(paramRelat.getMesGeracao());
		
		String dataInicio         = "01/"+paramRelat.getMesGeracao()+"/"+paramRelat.getAnoGeracao();
		String dataFim            = dateU.retornaUltimoDiaMes(dateU.obtemData(dataInicio)) + dataInicio.substring(2);
		String dataInicioAnterior = "01/" + (mesGeracao==1 ? 12 + "/" +(paramRelat.getAnoGeracao()-1) :(mesGeracao-1) + "/" + paramRelat.getAnoGeracao());
		String dataFimAnterior    = dateU.retornaUltimoDiaMes(dateU.obtemData(dataInicioAnterior)) + dataInicioAnterior.substring(2);
		String compSaldo          = paramRelat.getCompFluxo().equalsIgnoreCase("T") ? "'1','0'" : paramRelat.getCompFluxo();
		List<Map> listTotal       = this.lancamentoService.somatorioValorPorTipoConta(formataDataOracle(dateU.criarData(dataInicio,"dd/MM/yyyy")), formataDataOracle(dateU.criarData(dataFim,"dd/MM/yyyy")), compSaldo, paramRelat.getIdConta());
		
		Iterator it = listTotal.iterator();
		while(it.hasNext()){
			Map somatorios = (Map) it.next();
			int tpConta = new Long((Long) somatorios.get("tpConta")).intValue();
			switch(tpConta){
				case 1:
					valeDiretoriaTotal = (Double) somatorios.get("total");
					break;
					
				case 2:
					numerarioTotal = (Double) somatorios.get("total");
					break;
				
				case 3:
					chequeTotal = (Double) somatorios.get("total");
					break;
			}
		}
		
		List<Map> listTotalAnterior = this.lancamentoService.somatorioValorPorTipoConta(formataDataOracle(dateU.criarData(dataInicioAnterior,"dd/MM/yyyy")),  formataDataOracle(dateU.criarData(dataFimAnterior,"dd/MM/yyyy")), compSaldo, paramRelat.getIdConta());
		
		Iterator itAnt = listTotalAnterior.iterator();
		while(itAnt.hasNext()){
			Map somatoriosAnterior = (Map) itAnt.next();
			int tpConta = new Long((Long) somatoriosAnterior.get("tpConta")).intValue();
			switch(tpConta){
				case 1:
					valeDiretoriaTotalAnterior = (Double) somatoriosAnterior.get("total");
					break;
					
				case 2:
					numerarioTotalAnterior = (Double) somatoriosAnterior.get("total");
					break;
				
				case 3:
					chequeTotalAnterior = (Double) somatoriosAnterior.get("total");
					break;
			}
		}
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("dataInicio", dataInicio);
		parameters.put("dataFim", dataFim);
		parameters.put("dataInicioAnterior", dataInicioAnterior);
		parameters.put("dataFimAnterior", dataFimAnterior);
		parameters.put("valeDiretoriaTotal", valeDiretoriaTotal);
		parameters.put("numerarioTotal", numerarioTotal);
		parameters.put("chequeTotal", chequeTotal);
		parameters.put("valeDiretoriaTotalAnterior", valeDiretoriaTotalAnterior);
		parameters.put("numerarioTotalAnterior", numerarioTotalAnterior);
		parameters.put("chequeTotalAnterior", chequeTotalAnterior);
		
		return printUtils.preencherRelatorio(NOME_REL_SALDO_CAIXA_TOTAL, parameters );
	}

	public JasperPrint imprimirRelatorioCaixaSaldoConta(List<SaldoConta> listSaldoConta, Date data) throws JPAException{
		JRDataSource jrds = new JRBeanCollectionDataSource(listSaldoConta);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put( "listSaldoConta", listSaldoConta);
		parameters.put("data", data);
		return printUtils.preencherRelatorioList( NOME_REL_CAIXA_SALDO_CONTA, parameters, jrds );
	}
	
	public JasperPrint imprimirRelatorioSaldoCaixa( Lancamentos lancamentos, Double saldoAnterior ) throws JPAException{
		DateUtils dateU = new DateUtils();
		String dataInicioAnterior = "01/"+(new Integer(dateU.retornaMesPorData(lancamentos.getDataInicio()))-1)+"/"+dateU.retornaAno(lancamentos.getDataInicio());
		String dataFimAnterior = dateU.retornaUltimoDiaMes(dateU.obtemData(dataInicioAnterior)) + dataInicioAnterior.substring(2);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put( "dataInicioApres", formataDataOracle(lancamentos.getDataInicio()));
		parameters.put( "dataFimApres", formataDataOracle(lancamentos.getDataFim()));
		parameters.put( "dataInicio", formataDataOracle(lancamentos.getDataInicio()));
		parameters.put( "dataFim", formataDataOracle(lancamentos.getDataFim()));
		parameters.put( "idConta", lancamentos.getContas().getIdConta().toString());
		parameters.put( "descricaoConta", lancamentos.getContas().getDescricao());
		parameters.put( "saldoAnterior", saldoAnterior);
		return printUtils.preencherRelatorio( NOME_REL_SALDO_CAIXA, parameters );
	}
	
	public List<Lancamentos> getLancamentosPosteriores(Lancamentos lancamentoSelecionado) throws JPAException{
		List<Lancamentos> listLancamentosPosteriores = new ArrayList<Lancamentos>();
		listLancamentosPosteriores = this.lancamentoService.findLancamentosPosteriores(formataDataOracle(lancamentoSelecionado.getDtLancamento()), lancamentoSelecionado.getContas().getIdConta());
		return listLancamentosPosteriores;
	}
	
	
	public boolean gravarInclusao(Lancamentos lancamentoSelecionado, Contas conta, SaldoConta saldoConta) throws JPAException{
		Lancamentos lancamento = new Lancamentos();
		lancamento = lancamentoSelecionado;
		lancamento.setContas(conta);
		DateUtils dateU = new DateUtils();
		lancamento.setDtLancamento(dateU.criarData(formataDataOracle(lancamentoSelecionado.getDtLancamento()),"dd/MM/yyyy"));
		lancamento.setValor(retornaValor(lancamento));
		try{
			this.lancamentoService.salvar(lancamento);
			return true;
		}catch(JPAException e){
			e.printStackTrace();
			return false;
		}		
	}
	
	public Double retornaValor(Lancamentos lancamento){
		Double retorno = lancamento.getValor();
		if(lancamento.getDebCred().equalsIgnoreCase("D") && lancamento.getValor() >= 0){
			retorno = lancamento.getValor() * -1;
		}
		return retorno;
	}
	
	
	public boolean gravarAlteracaoInclusao(Lancamentos lancamentoSelecionado,Lancamentos lancamentoDB, Contas conta, Contas contaDB, SaldoConta saldoConta, SaldoConta saldoContaDB) throws JPAException{
		String dataHoje = formataDataOracle(new Date(System.currentTimeMillis()));
		DateUtils dateU = new DateUtils();
		//lancamento da conta antiga
		String dataLancDB = formataDataOracle(lancamentoDB.getDtLancamento());
		Lancamentos lancamentoDBP = new Lancamentos();
		lancamentoDBP = lancamentoDB;
		lancamentoDBP.setContas(contaDB);
		lancamentoDBP.setDtLancamento(dateU.criarData(formataDataOracle(lancamentoDB.getDtLancamento()),"dd/MM/yyyy"));
		lancamentoDBP.setValor(lancamentoDBP.getValor());
		lancamentoDBP.setDescricao("EXTORNO REF. LANCAMENTO INDEVIDO Nº "+lancamentoDB.getIdLancamento());
		lancamentoDBP.setIdLancamento(null);
		
		//lancamento da Conta Nova
		String dataLanc = formataDataOracle(lancamentoSelecionado.getDtLancamento());
		Lancamentos lancamento = new Lancamentos();
		lancamento = new Lancamentos();
		lancamento = lancamentoSelecionado;
		lancamento.setContas(conta);
		lancamento.setDtLancamento(dateU.criarData(formataDataOracle(lancamentoSelecionado.getDtLancamento()),"dd/MM/yyyy"));
		lancamento.setIdLancamento(null);
		
		try{
			this.lancamentoService.salvar(lancamentoDBP);
			if(saldoConta != null){
				this.saldoContaService.alterar(saldoContaDB);
				if(!dataLanc.equals(dataHoje)){
					this.lancamentoService.atualizaDiferencaSaldoPosterior(formataDataOracle(lancamentoDBP.getDtLancamento()), saldoContaDB.getDiferenca(), lancamentoDBP.getContas().getIdConta());
				}
			}
			
			this.lancamentoService.salvar(lancamento);
			if(saldoConta != null){
				this.saldoContaService.alterar(saldoConta);
				if(!dataLanc.equals(dataHoje)){
					this.lancamentoService.atualizaDiferencaSaldoPosterior(formataDataOracle(lancamento.getDtLancamento()), saldoConta.getDiferenca(), lancamento.getContas().getIdConta());
				} 			
			}
			return true;
			
		}catch(JPAException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean gravarAlteracaoSimples(Lancamentos lancamentoSelecionado, Contas contas) throws JPAException{
		try{
			Lancamentos lancamento = this.lancamentoService.findById(lancamentoSelecionado.getIdLancamento());
			lancamento = lancamentoSelecionado;
			lancamento.setContas(contas);
			if(lancamento.getDebCred().equalsIgnoreCase("D")){
				lancamento.setValor(lancamento.getValor() * -1);
			}			
			this.lancamentoService.alterar(lancamento);
			return true;
		}catch(JPAException e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean gravarAlteracao(Lancamentos lancamentoSelecionado, Contas conta, SaldoConta saldoConta) throws JPAException{
		String dataHoje = formataDataOracle(new Date(System.currentTimeMillis()));
		String dataLanc = formataDataOracle(lancamentoSelecionado.getDtLancamento());
		
		try{
			Lancamentos lancamento = this.lancamentoService.findById(lancamentoSelecionado.getIdLancamento());
			lancamento.setValor(lancamentoSelecionado.getValor());
			lancamento.setContas(conta);
			lancamento.setDtLancamento(lancamentoSelecionado.getDtLancamento());
			lancamento.setDescricao(lancamentoSelecionado.getDescricao());
			lancamento.setDocumento(lancamentoSelecionado.getDocumento());
			lancamento.setDebCred(lancamentoSelecionado.getDebCred());
			
			this.lancamentoService.alterar(lancamento);
			if(saldoConta.getDiaDB() != null && saldoConta.getIdContaDB() != null){
				SaldoConta saldoContaDB = new SaldoConta();
				SaldoContaPK saldoContaPKDB = new SaldoContaPK();
				saldoContaPKDB.setDia(saldoConta.getDiaDB());
				saldoContaPKDB.setIdConta(saldoConta.getIdContaDB());
				/*saldoContaDB = SaldoContaServiceImpl.findById(saldoContaPKDB);*/
				if(saldoConta.getValorLancDB() == null){
					saldoContaDB.setSaldo(saldoContaDB.getSaldo() - saldoConta.getDiferenca());
					saldoContaDB.setDiferenca(saldoConta.getDiferenca());
				}else{
					saldoContaDB.setSaldo(saldoContaDB.getSaldo() - saldoConta.getValorLancDB());
					saldoContaDB.setDiferenca(saldoConta.getValorLancDB());
				}	
				this.saldoContaService.alterar(saldoContaDB);
				if(!dataLanc.equals(dataHoje)){
					this.lancamentoService.atualizaDiferencaSaldoPosterior(formataDataOracle(lancamento.getDtLancamento()), saldoContaDB.getDiferenca() *-1, saldoConta.getIdContaDB());
				}
			}
			this.saldoContaService.alterar(saldoConta);
			if(!dataLanc.equals(dataHoje)){
				this.lancamentoService.atualizaDiferencaSaldoPosterior(formataDataOracle(lancamento.getDtLancamento()), saldoConta.getDiferenca(), lancamento.getContas().getIdConta());
			}
			return true;
		}catch(JPAException e){
			e.printStackTrace();
			return false;
		}	
		
	}
	
	
	public String formataData(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR"));
		return dateFormat.format(data);
	}
	
	public String formataDataOracle(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR"));
		return dateFormat.format(data);
	}
	
	public Fechamento getFechamento(Fechamento fechamento) throws JPAException{
		DateUtils dateU = new DateUtils();
		
		String dataInicio = formataDataOracle(dateU.criarData("01/"+dateU.retornaMesPorData(fechamento.getCompetencia())+"/"+dateU.retornaAno(fechamento.getCompetencia()), "dd/MM/yyyy"));
		String dataFim = dateU.retornaUltimoDiaMes(fechamento.getCompetencia()) + dataInicio.substring(2);	
		String compSaldo ="";
		/*if(fechamento.getCompSaldo().equalsIgnoreCase("T")){
			compSaldo = "'1','0'";
		}else{
			compSaldo = fechamento.getCompSaldo();
		}*/
		fechamento.setPeriodo(dataInicio +" a " + dataFim);
		fechamento.setSaldo(DataTypes.parseNull(this.lancamentoService.somatorioValor(formataDataOracle(dateU.criarData(dataInicio, "dd/MM/yyyy")), formataDataOracle(dateU.criarData(dataFim, "dd/MM/yyyy")))));
		

		return fechamento;
		
	}
	
	public boolean atualizaFechamentoConta() throws JPAException {
		try{
			DateUtils dateU = new DateUtils();
			Date dataFim = new Date(System.currentTimeMillis()); 
			dataFim = dateU.adicionaDias(dataFim, -5);
			this.lancamentoService.atualizaFechamentoConta(formataDataOracle(dataFim));
			return true;
			
		}catch(JPAException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public Double atualizarSaldoDia(Lancamentos lancamento, Double saldo) throws JPAException{
		Double diferencaSaldo = new Double("0");
		try{
			SaldoConta saldoContaDb = new SaldoConta();
			SaldoContaPK saldoContaPK = new SaldoContaPK();
			
			saldoContaPK.setDia(lancamento.getDtLancamento());
			saldoContaPK.setIdConta(lancamento.getContas().getIdConta());
			
			/*saldoContaDb = findById(saldoContaPK);*/
			
			Double saldoDia = new Double("0");
			saldoDia = saldoContaDb.getSaldo() + saldo;
			diferencaSaldo = saldoDia - saldoContaDb.getSaldo();
			
			saldoContaDb.setSaldo(saldoDia);
			this.saldoContaService.alterar(saldoContaDb);
				
		}catch(JPAException e){
			e.printStackTrace();
		}
		return diferencaSaldo;
		
	}
	
	@SuppressWarnings("unused")
	public SaldoConta getSaldoConta(Lancamentos lancamento) throws JPAException{
		SaldoContaPK saldoContaPK = new SaldoContaPK();
		saldoContaPK.setDia(lancamento.getDtLancamento());
		saldoContaPK.setIdConta(new Long(lancamento.getStrIdConta() == null ? lancamento.getContas().getIdConta(): new Long(lancamento.getStrIdConta())));
		SaldoConta saldoConta = new SaldoConta();
		/*saldoConta = findById(saldoContaPK);*/
		Double saldo     = new Double("0");
		
		if(saldoConta != null){
			saldo = saldoConta.getSaldo() + lancamento.getValor();
			/*diferenca =  saldo - saldoConta.getSaldo();*/
			saldoConta.setSaldo(saldo);
			saldoConta.setDiferenca(lancamento.getValor());
			
		}else{
			saldoConta = new SaldoConta();
			saldoConta.setPk(saldoContaPK);
			saldo = DataTypes.parseNull(this.saldoContaService.findUltimoSaldo(new Long(lancamento.getStrIdConta() == null ? lancamento.getContas().getIdConta() : new Long(lancamento.getStrIdConta())), lancamento.getDtLancamento()));
			saldo = saldo + DataTypes.parseNull(lancamento.getValor());
			saldoConta.setSaldo(saldo);
			saldoConta.setDiferenca(lancamento.getValor());
		}
		return saldoConta;
	}
	
	@SuppressWarnings("unused")
	public SaldoConta getSaldoContaAlteracao(Lancamentos lancamento, Lancamentos lancamentoDB, boolean isMesmaConta, boolean isMesmoValor) throws JPAException{
		SaldoConta saldoConta = new SaldoConta();
		SaldoContaPK saldoContaPK = new SaldoContaPK();
		saldoContaPK.setDia(lancamento.getDtLancamento());
		saldoContaPK.setIdConta(lancamento.getContas().getIdConta());
		Double diferenca = new Double("0");
		Double saldo     = new Double("0");
		
		if(isMesmaConta){
			saldoContaPK.setIdConta(lancamento.getContas().getIdConta());
			/*saldoConta = findById(saldoContaPK);*/
			if(!isMesmoValor){
				diferenca = lancamento.getValor()-lancamentoDB.getValor();
				if(diferenca==0){
					saldoConta.setDiferenca(lancamento.getValor() * 2);
				}else{
					saldoConta.setDiferenca(diferenca);
				}	
				saldoConta.setSaldo(saldoConta.getSaldo() + diferenca);
			}/*else{
				diferenca = lancamento.getValor();
				saldoConta.setDiferenca(diferenca);
				saldoConta.setSaldo(saldoConta.getSaldo() + diferenca);
			}*/	
		
		}else{
			/*saldoConta = findById(saldoContaPK);*/
			if(saldoConta != null) {
				saldoConta.setSaldo(saldoConta.getSaldo() + lancamento.getValor());
				saldoConta.setDiferenca(lancamento.getValor());
			}else{
				saldoConta = new SaldoConta();
				saldoConta.setPk(saldoContaPK);
				saldo = DataTypes.parseNull(this.saldoContaService.findUltimoSaldo(lancamento.getContas().getIdConta(), lancamento.getDtLancamento()));
				saldo = saldo + DataTypes.parseNull(lancamento.getValor());
				saldoConta.setSaldo(saldo);
				saldoConta.setDiferenca(lancamento.getValor());
			}
			if(!isMesmoValor){
				saldoConta.setValorLancDB(lancamentoDB.getValor());
			}
			saldoConta.setIdContaDB(lancamentoDB.getContas().getIdConta());
			saldoConta.setDiaDB(lancamentoDB.getDtLancamento());
			
		}
		return saldoConta;
	}
	
	public List<SaldoConta> getSaldoContas(List<Contas> listContas, Date competencia) throws JPAException{
		List<SaldoConta> listSaldoConta = new ArrayList<SaldoConta>();
		for(Contas contas: listContas) {
			SaldoConta saldoConta = new SaldoConta();
			Double saldo = DataTypes.parseNull(this.saldoContaService.findSaldo(contas.getIdConta(), competencia, true));
			//Incluindo saldo inicial
			saldo += DataTypes.parseNull(this.saldoContaService.findSaldoInicial(contas.getIdConta()));
			saldoConta.setSaldo(saldo);
			
			saldoConta.setDescricaoConta(contas.getDescricao());
			listSaldoConta.add(saldoConta);
		}
		return listSaldoConta;
	}
	
	
	
	public ContasModel getListaContas() {
		return listaContas;
	}

	public void setListaContas(ContasModel listaContas) {
		this.listaContas = listaContas;
	}

	public List<Contas> getListaContasFiltro() {
		return listaContasFiltro;
	}

	public void setListaContasFiltro(List<Contas> listaContasFiltro) {
		this.listaContasFiltro = listaContasFiltro;
	}

	public Contas getContas() {
		return contas;
	}

	public void setContas(Contas contas) {
		this.contas = contas;
	}

	public boolean isCompSaldo() {
		return isCompSaldo;
	}

	public void setCompSaldo(boolean isCompSaldo) {
		this.isCompSaldo = isCompSaldo;
	}

	public Contas getContaSelecionada() {
		return contaSelecionada;
	}

	public void setContaSelecionada(Contas contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}

	public boolean isInclusao() {
		return isInclusao;
	}
	public void setInclusao(boolean isInclusao) {
		this.isInclusao = isInclusao;
	}
	public List<TpConta> getListaTPContasFiltro() {
		return listaTPContasFiltro;
	}

	public void setListaTPContasFiltro(List<TpConta> listaTPContasFiltro) {
		this.listaTPContasFiltro = listaTPContasFiltro;
	}
	
	public List<TpConta> getListTpContas() {
		return listTpContas;
	}

	public void setListTpContas(List<TpConta> listTpContas) {
		this.listTpContas = listTpContas;
	}

	public TpConta getTpContas() {
		return tpContas;
	}

	public void setTpContas(TpConta tpContas) {
		this.tpContas = tpContas;
	}

	public TpConta getTpContaSelecionada() {
		return tpContaSelecionada;
	}

	public void setTpContaSelecionada(TpConta tpContaSelecionada) {
		this.tpContaSelecionada = tpContaSelecionada;
	}


	public Bancos getBancos() {
		return bancos;
	}


	public void setBancos(Bancos bancos) {
		this.bancos = bancos;
	}


	public List<Bancos> getListBancos() {
		return listBancos;
	}


	public void setListBancos(List<Bancos> listBancos) {
		this.listBancos = listBancos;
	}


	public Bancos getBancoSelecionado() {
		return bancoSelecionado;
	}


	public void setBancoSelecionado(Bancos bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}

	public List<Bancos> getListaBancosFiltro() {
		return listaBancosFiltro;
	}

	public void setListaBancosFiltro(List<Bancos> listaBancosFiltro) {
		this.listaBancosFiltro = listaBancosFiltro;
	}

	public Lancamentos getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}

	public LancamentosModel getListaLancamentos() {
		return listaLancamentos;
	}

	public void setListaLancamentos(LancamentosModel listaLancamentos) {
		this.listaLancamentos = listaLancamentos;
	}

	public List<Lancamentos> getListaLancamentosFiltro() {
		return listaLancamentosFiltro;
	}

	public void setListaLancamentosFiltro(List<Lancamentos> listaLancamentosFiltro) {
		this.listaLancamentosFiltro = listaLancamentosFiltro;
	}

	public List<Lancamentos> getListLancamentos() {
		return listLancamentos;
	}

	public void setListLancamentos(List<Lancamentos> listLancamentos) {
		this.listLancamentos = listLancamentos;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Lancamentos getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamentos lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	public String getStrTotal() {
		return strTotal;
	}

	public void setStrTotal(String strTotal) {
		this.strTotal = strTotal;
	}

	public List<Contas> getListContas() {
		return listContas;
	}

	public void setListContas(List<Contas> listContas) {
		this.listContas = listContas;
	}

	public Fechamento getFechamento() {
		return fechamento;
	}

	public void setFechamento(Fechamento fechamento) {
		this.fechamento = fechamento;
	}

	public List<Fechamento> getListFechamento() {
		return listFechamento;
	}

	public void setListFechamento(List<Fechamento> listFechamento) {
		this.listFechamento = listFechamento;
	}

	public List<Fechamento> getListaFechamentoFiltro() {
		return listaFechamentoFiltro;
	}

	public void setListaFechamentoFiltro(List<Fechamento> listaFechamentoFiltro) {
		this.listaFechamentoFiltro = listaFechamentoFiltro;
	}

	public List<HistoricoPadrao> getListHistPadrao() {
		return listHistPadrao;
	}

	public void setListHistPadrao(List<HistoricoPadrao> listHistPadrao) {
		this.listHistPadrao = listHistPadrao;
	}

	public List<HistoricoPadrao> getListaHistPadraoFiltro() {
		return listaHistPadraoFiltro;
	}

	public void setListaHistPadraoFiltro(List<HistoricoPadrao> listaHistPadraoFiltro) {
		this.listaHistPadraoFiltro = listaHistPadraoFiltro;
	}

	public HistoricoPadrao getHistPadraoSelecionado() {
		return histPadraoSelecionado;
	}

	public void setHistPadraoSelecionado(HistoricoPadrao histPadraoSelecionado) {
		this.histPadraoSelecionado = histPadraoSelecionado;
	}

	public HistoricoPadrao getHistPadrao() {
		return histPadrao;
	}

	public void setHistPadrao(HistoricoPadrao histPadrao) {
		this.histPadrao = histPadrao;
	}

	public HistPadraoModel getListaHistPadrao() {
		return listaHistPadrao;
	}

	public void setListaHistPadrao(HistPadraoModel listaHistPadrao) {
		this.listaHistPadrao = listaHistPadrao;
	}
	
	public StreamedContent getRelatorioSaldoConta() {
		setRelatorioSaldoConta(emitirRelatorioSaldoConta());
		return relatorioSaldoConta;
	}
	
	public void setRelatorioSaldoConta(StreamedContent relatorioSaldoConta) {
		this.relatorioSaldoConta = relatorioSaldoConta;
	}
	
	public StreamedContent getRelatorioSaldoCaixa() {
		setRelatorioSaldoCaixa(emitirRelatorio());
		return relatorioSaldoCaixa;
	}

	public void setRelatorioSaldoCaixa(StreamedContent relatorioSaldoCaixa) {
		this.relatorioSaldoCaixa = relatorioSaldoCaixa;
	}

	public ParametrosRelatorio getParamRelat() {
		return paramRelat;
	}

	public void setParamRelat(ParametrosRelatorio paramRelat) {
		this.paramRelat = paramRelat;
	}

	public StreamedContent getRelatorioSaldoCaixaTotal() {
		setRelatorioSaldoCaixaTotal(emitirRelatorioSaldoTotCaixa());
		return relatorioSaldoCaixaTotal;
	}

	public void setRelatorioSaldoCaixaTotal(StreamedContent relatorioSaldoCaixaTotal) {
		this.relatorioSaldoCaixaTotal = relatorioSaldoCaixaTotal;
	}

	public String getCompFluxo() {
		return compFluxo;
	}

	public void setCompFluxo(String compFluxo) {
		this.compFluxo = compFluxo;
	}

	public SaldoConta getSaldo() {
		return saldo;
	}

	public void setSaldo(SaldoConta saldo) {
		this.saldo = saldo;
	}

	public Long getCodLancamento() {
		return codLancamento;
	}

	public void setCodLancamento(Long codLancamento) {
		this.codLancamento = codLancamento;
	}
	
	public Lancamentos getLancamentoFiltro() {
		return lancamentoFiltro;
	}
	
	public void setLancamentoFiltro(Lancamentos lancamentoFiltro) {
		this.lancamentoFiltro = lancamentoFiltro;
	}
	
	public HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	public Lancamentos getLancamentoSelected() {
		return lancamentoSelected;
	}

	public void setLancamentoSelected(Lancamentos lancamentoSelected) {
		this.lancamentoSelected = lancamentoSelected;
	}
	
	
}
		