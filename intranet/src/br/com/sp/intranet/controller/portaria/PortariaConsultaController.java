package br.com.sp.intranet.controller.portaria;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.administrador.LoginController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.model.portaria.FaixaHorarioCarregamento;
import br.com.sp.intranet.model.portaria.FaixaHorarioPortaria;
import br.com.sp.intranet.model.portaria.HistMovPortaria;
import br.com.sp.intranet.model.portaria.MovPortaria;
import br.com.sp.intranet.model.portaria.PesoCarregadoTurno;
import br.com.sp.intranet.model.portaria.QuantidadeVeiculosDetalhado;
import br.com.sp.intranet.model.portaria.RelatorioEnum;
import br.com.sp.intranet.model.portaria.TempoCarregadoTurno;
import br.com.sp.intranet.model.portaria.Tempos;
import br.com.sp.intranet.model.portaria.TotalPeso;
import br.com.sp.intranet.service.portaria.HistoricoMovimentacaoPortariaService;
import br.com.sp.intranet.service.portaria.PortariaService;
import br.com.sp.intranet.util.DataTypes;
import br.com.sp.intranet.util.DateUtils;
import br.com.sp.intranet.util.GeracaoRelatorio;
import br.com.sp.intranet.util.PrintUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;

@Controller
@Scope("view")
public class PortariaConsultaController {

	@Autowired
	private PortariaService portariaService;
	@Autowired
	private LoginController login;
	@Autowired
	private HistoricoMovimentacaoPortariaService historicoMovimentacaoPortariaService;

	private List<String> photos = new ArrayList<String>();
	private List<FaixaHorarioCarregamento> listFaixaHorarioCarregamento = new ArrayList<FaixaHorarioCarregamento>();
	private List<TotalPeso> listTotalPeso = new ArrayList<TotalPeso>();
	private List<QuantidadeVeiculosDetalhado> listQuantidadeVeiculosDetalhados = new ArrayList<QuantidadeVeiculosDetalhado>();
	private List<TempoCarregadoTurno> listTempoCarregamentoTurno = new ArrayList<TempoCarregadoTurno>();
	private List<PesoCarregadoTurno> listPesoCarregadoTurno = new ArrayList<PesoCarregadoTurno>();
	private List<MovPortaria> listMovPortaria = new ArrayList<MovPortaria>();
	private List<MovPortaria> listMovPortariaFiltro;

	private MovPortaria movPortaria = new MovPortaria();
	private MovPortaria movPortariaSelecionado;

	private StreamedContent relatorioStream;
	
	private int status;
	private Date dtInicio;
	private Date dtFim;
	private String dataInicioString;
	private String dataFimString;
	private Long idMovPortaria;
	private Boolean isDetalhePortaria = new Boolean(true);
	private Boolean isExcluirMovPortaria = new Boolean(true);

	private final String NOME_REL_FAIXA_HORARIO = "/br/com/sp/intranet/resource/jasper/portaria/faixaHorariaVeiculos.jasper";
	private final String NOME_REL_MEDIA_TEMPO_ESPERA_CARREGAMENTO = "/br/com/sp/intranet/resource/jasper/portaria/mediaTempoEsperaCarregamento.jasper";
	private final String NOME_REL_QUANTIDADE_TIPO_CARREGAMENTO = "/br/com/sp/intranet/resource/jasper/portaria/quantidadePorTipoCarregamento.jasper";
	private final String NOME_REL_MEDIA_CARREGAMENTO_POR_TIPO = "/br/com/sp/intranet/resource/jasper/portaria/mediaTempoCarregamentoPorTipo.jasper";
	
	private RelatorioEnum [] listRelatorios;
	private RelatorioEnum relatorio;

	private static final PrintUtils printUtils = PrintUtils.getInstance();
	
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			Date hoje = new Date();
			SimpleDateFormat formatterString = new SimpleDateFormat("dd/MM/yyyy");
			try {
				listMovPortaria = portariaService.findConsultaMovPortaria(formatterString.format(hoje), formatterString.format(hoje));
			} catch (JPAException e) {
				e.printStackTrace();
			}
			
			setDtFim(hoje);
			setDtInicio(hoje);
			verificaPermissoes();
		}
	}
	
	public void abrirDialogTempoCarregamentoPorTurno(){
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogTempoCarregTurno').show();");
	}
	
	public void abrirDialogQuantidadeVeiculosTurno(){
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogQuantidadeVeiculosTurnoDetalhe').show();");
	}
	
	public void abrirDialogPesoCarregamentoPorTurno(){
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogPesoCarregadoTurno').show();");
	}
	
	
	public void abrirDialogFaixaCarregamento(){
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogFaixaCarregamento').show();");
	}
	
	public void abrirDialogTotalPeso(){
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogTotalPeso').show();");
	}
	
	public StreamedContent emitirRelatorios(){
		StreamedContent relatorioStream = null;
		try {
			switch (relatorio) {

			case TEMPO_CARREGAMENTO_TURNO_DETAL:
				this.carregarTempoCarregamentoTurno();
				this.abrirDialogTempoCarregamentoPorTurno();
				break;

			case VEICULOS_TURNO_DETAL:
				this.carregarQuantidadeVeiculosTurnoDetalhado();
				this.abrirDialogQuantidadeVeiculosTurno();
				break;

			case PESO_CARREGAMENTO_TURNO_DETAL:
				this.carregarPesoCarregado();
				this.abrirDialogPesoCarregamentoPorTurno();
				break;

			case QUANTIDADE_VEICULOS_CARREG:
				this.carregarFaixaHorarioCarregamento();
				this.abrirDialogFaixaCarregamento();
				break;

			case QUANTIDADE_TOTAL_PESO:
				this.carregarTotalPeso();
				this.abrirDialogTotalPeso();
				break;

			case QUANTIDADE_TIPO_CARREG:
				relatorioStream = this.emitirRelatorioQuantidadeTipoCarregamento();
				break;

			case FAIXA_HORARIA_APRES:
				relatorioStream = this.emitirRelatorioFaixaHorarioApres();
				break;

			case TEMPO_ESPERA_CARREG:
				relatorioStream = this.emitirRelatorioTempoEsperaCarregamento();
				break;

			case TEMPO_CARREG_TIPO:
				relatorioStream = this.emitirRelatorioTempoCarregamentoPorTipo();
				break;
			default:
				break;
			}
		} catch (JPAException e) {
			e.printStackTrace();
		}
		
		return relatorioStream;
	}
	
	public void initRelatorios(){
		dtInicio = null;
		dtFim = null;
		relatorio = null;
		relatorioStream = null;
	}

	public void initDetalhe() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

			Long idMovimentacao = Long.parseLong(request.getParameter("id"));
			
			try {
				this.movPortaria = new MovPortaria();
				this.movPortaria = this.portariaService.findById(idMovimentacao);
				RequestContext rc = RequestContext.getCurrentInstance();
				rc.update("form");
			} catch (JPAException e) {
				e.printStackTrace();
			}
		}
	}

	public void verificaPermissoes() {
		List<CsAutorizacao> listAutorizacoes = new ArrayList<CsAutorizacao>();
		try {
			listAutorizacoes = login.getUsuarioService().carregarAutorizacoes(login.getUsuario());
		} catch (JPAException e1) {
			e1.printStackTrace();
		}

		for (CsAutorizacao campo : listAutorizacoes) {
			if (campo.getAutorizacao().equalsIgnoreCase("btnDetalhePortaria"))
				isDetalhePortaria = false;
			if (campo.getAutorizacao().equalsIgnoreCase("btnExcluirMovPortaria"))
				isExcluirMovPortaria = false;
		}
	}

	public void pesquisar() throws JPAException {
		SimpleDateFormat formatterString = new SimpleDateFormat("dd/MM/yyyy");
		listMovPortaria = portariaService.findConsultaMovPortaria(formatterString.format(this.dtInicio), formatterString.format(this.dtFim));

	}

	public void excluir() throws JPAException {
		if (portariaService.delete(this.movPortariaSelecionado)) {
			createMessage(FacesMessage.SEVERITY_INFO, "excluir.sucesso");
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("location.reload();");
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "excluir.erro");
		}

	}

	public void redirecionaPagina() {

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("detalheConsultaPortaria.xhtml?id=" + idMovPortaria);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void redirecionaPaginaPrincipal() {

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("consultarPortaria.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void prepararFaixaHoraria() throws JPAException {
		movPortaria = new MovPortaria();
	}

	public void prepararDetalhe() throws JPAException {
		this.movPortaria = portariaService.findById(this.movPortariaSelecionado.getIdMov());
	}

	public JasperPrint imprimirRelatorioQuantidadeTipoCarregamento(Date dataInicio, Date dataFim) throws JPAException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("dataInicio", dataInicio);
		parameters.put("dataFim", dataFim);
		return printUtils.preencherRelatorio(NOME_REL_QUANTIDADE_TIPO_CARREGAMENTO, parameters);
	}

	public JasperPrint imprimirRelatorioTempoCarregamentoPorTipo(Date dataInicio, Date dataFim) throws JPAException {
		List<MovPortaria> listaMovPortaria = new ArrayList<MovPortaria>();
		Date hoje = new Date();
		SimpleDateFormat formatterString = new SimpleDateFormat("dd/MM/yyyy");
		listaMovPortaria = portariaService.getListMediaPortaria(formatterString.format(dataInicio), formatterString.format(dataFim));

		List<Tempos> listaTempos = new ArrayList<Tempos>();
		listaTempos = calculaMediaTemposPorTipo(listaMovPortaria);
		JRDataSource jrds = new JRBeanCollectionDataSource(listaTempos);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("listaTempos", listaTempos);
		return printUtils.preencherRelatorioList(NOME_REL_MEDIA_CARREGAMENTO_POR_TIPO, parameters, jrds);
	}

	public JasperPrint imprimirRelatorioTempoEsperaCarragamento(Date dataInicio, Date dataFim) throws JPAException {
		List<MovPortaria> listaMovPortaria = new ArrayList<MovPortaria>();
		Date hoje = new Date();
		SimpleDateFormat formatterString = new SimpleDateFormat("dd/MM/yyyy");
		listaMovPortaria = portariaService.getListMediaPortaria(formatterString.format(dataInicio), formatterString.format(dataFim));

		List<Tempos> listaTempos = new ArrayList<Tempos>();
		listaTempos = calculaMediaTempos(listaMovPortaria);
		JRDataSource jrds = new JRBeanCollectionDataSource(listaTempos);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("listaTempos", listaTempos);
		return printUtils.preencherRelatorioList(NOME_REL_MEDIA_TEMPO_ESPERA_CARREGAMENTO, parameters, jrds);
	}

	public JasperPrint imprimirRelatorioFaixaHorarioApresentacao(Date dataInicio, Date dataFim) throws JPAException {
		List<FaixaHorarioPortaria> listaFaixaHorario = new ArrayList<FaixaHorarioPortaria>();
		listaFaixaHorario = getListFaixaHorario(dataInicio, dataFim);
		Collections.sort(listaFaixaHorario);

		JRDataSource jrds = new JRBeanCollectionDataSource(listaFaixaHorario);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("listaFaixaHorario", listaFaixaHorario);
		return printUtils.preencherRelatorioList(NOME_REL_FAIXA_HORARIO, parameters, jrds);
	}

	public StreamedContent emitirRelatorioFaixaHorarioApres() {
		relatorioStream = new DefaultStreamedContent();
		try {
			HttpServletRequest request = getRequest();
			JasperPrint jasperPrint = this.imprimirRelatorioFaixaHorarioApresentacao(dtInicio, dtFim);
			request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
			relatorioStream = GeracaoRelatorio.gerarRelatorio(request, "Faixa Horaria de Apresentação");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return relatorioStream;
	}

	public StreamedContent emitirRelatorioQuantidadeTipoCarregamento() {
		relatorioStream = new DefaultStreamedContent();

		try {
			HttpServletRequest request = getRequest();
			JasperPrint jasperPrint = this.imprimirRelatorioQuantidadeTipoCarregamento(dtInicio, dtFim);
			request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
			relatorioStream = GeracaoRelatorio.gerarRelatorio(request, "Quantidade por Tipo de Carregamento");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return relatorioStream;
	}

	public StreamedContent emitirRelatorioTempoCarregamentoPorTipo() {
		relatorioStream = new DefaultStreamedContent();

		try {
			HttpServletRequest request = getRequest();
			JasperPrint jasperPrint = this.imprimirRelatorioTempoCarregamentoPorTipo(dtInicio, dtFim);
			request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
			relatorioStream = GeracaoRelatorio.gerarRelatorio(request,
					"Média de Tempo de Carregamento por Tipo");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return relatorioStream;
	}

	public StreamedContent emitirRelatorioTempoEsperaCarregamento() {
		relatorioStream = new DefaultStreamedContent();

		try {
			HttpServletRequest request = getRequest();
			JasperPrint jasperPrint = this.imprimirRelatorioTempoEsperaCarragamento(dtInicio, dtFim);
			request.getSession().setAttribute(BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
			relatorioStream = GeracaoRelatorio.gerarRelatorio(request, "Média de Tempo Geral de Espera/Carregamento");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return relatorioStream;
	}

	public List<Tempos> calculaMediaTempos(List<MovPortaria> listMovPortaria) throws JPAException {
		List<Tempos> listTempos = new ArrayList<Tempos>();

		DateUtils dateU = new DateUtils();
		Tempos tempo = new Tempos();

		int cont = 0;
		int quantidade = 1;
		int mes = 0;

		try {
			Iterator it = listMovPortaria.iterator();

			while (it.hasNext()) {
				MovPortaria movPortaria = new MovPortaria();
				movPortaria = (MovPortaria) it.next();
				// Soma tempos caso for mês igual
				if (dateU.retornaMes(movPortaria.getHoraInicioCarregamento()) == mes) {
					tempo.setTempoEsperaMilis(DataTypes.parseNull(tempo.getTempoEsperaMilis()) + new Long(
							dateU.retornaMilisegundosDaHora(DataTypes.parseZero(movPortaria.getTempoEspera()))));
					tempo.setTempoCarregamentoMilis(DataTypes.parseNull(tempo.getTempoCarregamentoMilis()) + new Long(
							dateU.retornaMilisegundosDaHora(DataTypes.parseZero(movPortaria.getTempoCarregamento()))));
					quantidade += 1;
					// Efetuar conta e incluir na lista.
				} else {
					// Apenas efetuar conta e inclui no caso de não ser a
					// primeira troca de mês(sem soma)
					if (cont > 0) {
						Double te = new Double("0");
						Double tc = new Double("0");
						tempo.setData(dateU.adicionaMeses(movPortaria.getHoraInicioCarregamento(), -1));
						te = (double) tempo.getTempoEsperaMilis() / quantidade;
						tc = (double) tempo.getTempoCarregamentoMilis() / quantidade;
						tempo.setTempoEsperaMilis(dateU.retornaMilisegundosDaHora(dateU.retornaHoraSomada(te)));
						tempo.setTempoCarregamentoMilis(dateU.retornaMilisegundosDaHora(dateU.retornaHoraSomada(tc)));
						tempo.setTempoEspera(dateU.retornaHoraSomada(te));
						tempo.setTempoCarregamento(dateU.retornaHoraSomada(tc));
						listTempos.add(tempo);

						// proximo mês
						tempo = new Tempos();
						quantidade = 1;
						tempo.setTempoEsperaMilis(
								dateU.retornaMilisegundosDaHora(DataTypes.parseZero(movPortaria.getTempoEspera())));
						tempo.setTempoCarregamentoMilis(dateU
								.retornaMilisegundosDaHora(DataTypes.parseZero(movPortaria.getTempoCarregamento())));
					} else {
						// primeira troca de mês apenas setar os valores
						// correntes
						tempo.setData(movPortaria.getHoraInicioCarregamento());
						tempo.setTempoEsperaMilis(
								dateU.retornaMilisegundosDaHora(DataTypes.parseZero(movPortaria.getTempoEspera())));
						tempo.setTempoCarregamentoMilis(dateU
								.retornaMilisegundosDaHora(DataTypes.parseZero(movPortaria.getTempoCarregamento())));
					}
				}

				if (!it.hasNext()) {
					Double te = new Double("0");
					Double tc = new Double("0");

					te = (double) tempo.getTempoEsperaMilis() / quantidade;
					tc = (double) tempo.getTempoCarregamentoMilis() / quantidade;

					tempo.setData(movPortaria.getHoraInicioCarregamento());
					tempo.setTempoEsperaMilis(dateU.retornaMilisegundosDaHora(dateU.retornaHoraSomada(te)));
					tempo.setTempoCarregamentoMilis(dateU.retornaMilisegundosDaHora(dateU.retornaHoraSomada(tc)));

					tempo.setTempoEspera(dateU.retornaHoraSomada(te));
					tempo.setTempoCarregamento(dateU.retornaHoraSomada(tc));
					listTempos.add(tempo);
				}
				cont += 1;
				mes = movPortaria.getHoraInicioCarregamento() == null ? 0
						: dateU.retornaMes(movPortaria.getHoraInicioCarregamento());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTempos;
	}

	public List<Tempos> calculaMediaTemposPorTipo(List<MovPortaria> listMovPortaria) throws JPAException {
		List<Tempos> listTempos = new ArrayList<Tempos>();

		DateUtils dateU = new DateUtils();
		Tempos tempo = new Tempos();

		int cont = 0;
		int quantidadePalet = 1;
		int quantidadeNPalet = 1;
		int mes = 0;

		try {
			Iterator it = listMovPortaria.iterator();

			while (it.hasNext()) {
				MovPortaria movPortaria = new MovPortaria();
				movPortaria = (MovPortaria) it.next();

				if (dateU.retornaMes(movPortaria.getHoraInicioCarregamento()) == mes) {
					if (movPortaria.getTipoCarga().toUpperCase().contains("PALET")) {
						tempo.setTempoMedCarregamentoPaletMilis(
								DataTypes.parseNull(tempo.getTempoMedCarregamentoPaletMilis())
										+ new Long(dateU.retornaMilisegundosDaHora(
												DataTypes.parseZero(movPortaria.getTempoCarregamento()))));
						quantidadePalet += 1;
					} else {
						tempo.setTempoMedCarregamentoNPaletMilis(
								DataTypes.parseNull(tempo.getTempoMedCarregamentoNPaletMilis())
										+ new Long(dateU.retornaMilisegundosDaHora(
												DataTypes.parseZero(movPortaria.getTempoCarregamento()))));
						quantidadeNPalet += 1;
					}
				} else {
					if (cont > 0) {
						Double tc = new Double("0");
						tempo.setData(dateU.adicionaMeses(movPortaria.getHoraInicioCarregamento(), -1));
						tc = (double) tempo.getTempoMedCarregamentoPaletMilis() / quantidadePalet;
						tempo.setTempoMedCarregamentoPaletMilis(
								dateU.retornaMilisegundosDaHora(dateU.retornaHoraSomada(tc)));
						tempo.setTempoMedCarregamentoPalet(dateU.retornaHoraSomada(tc));
						quantidadePalet = 1;

						Double tc2 = new Double("0");
						tc2 = (double) tempo.getTempoMedCarregamentoNPaletMilis() / quantidadeNPalet;
						tempo.setTempoMedCarregamentoNPaletMilis(
								dateU.retornaMilisegundosDaHora(dateU.retornaHoraSomada(tc2)));
						tempo.setTempoMedCarragamentoNPalet(dateU.retornaHoraSomada(tc2));

						listTempos.add(tempo);

						tempo = new Tempos();
						quantidadeNPalet = 1;
						tempo.setTempoMedCarregamentoNPaletMilis(dateU
								.retornaMilisegundosDaHora(DataTypes.parseZero(movPortaria.getTempoCarregamento())));
					} else {
						tempo.setData(movPortaria.getHoraInicioCarregamento());
						if (movPortaria.getTipoCarga().toUpperCase().contains("PALET")) {
							tempo.setTempoMedCarregamentoPaletMilis(dateU.retornaMilisegundosDaHora(
									DataTypes.parseZero(movPortaria.getTempoCarregamento())));
						} else {
							tempo.setTempoMedCarregamentoNPaletMilis(dateU.retornaMilisegundosDaHora(
									DataTypes.parseZero(movPortaria.getTempoCarregamento())));
						}
					}
				}
				if (!it.hasNext()) {
					if (tempo.getTempoMedCarregamentoPaletMilis() != null && quantidadePalet != 0) {
						Double tc = new Double("0");
						tc = (double) tempo.getTempoMedCarregamentoPaletMilis() / quantidadePalet;
						tempo.setData(movPortaria.getHoraInicioCarregamento());
						tempo.setTempoMedCarregamentoPaletMilis(
								dateU.retornaMilisegundosDaHora(dateU.retornaHoraSomada(tc)));
						tempo.setTempoMedCarregamentoPalet(dateU.retornaHoraSomada(tc));
					}

					if (tempo.getTempoMedCarregamentoNPaletMilis() != null && quantidadeNPalet != 0) {
						Double tc2 = new Double("0");
						tc2 = (double) tempo.getTempoMedCarregamentoNPaletMilis() / quantidadeNPalet;
						tempo.setData(movPortaria.getHoraInicioCarregamento());
						tempo.setTempoMedCarregamentoNPaletMilis(
								dateU.retornaMilisegundosDaHora(dateU.retornaHoraSomada(tc2)));
						tempo.setTempoMedCarragamentoNPalet(dateU.retornaHoraSomada(tc2));
					}

					listTempos.add(tempo);
				}
				cont += 1;
				mes = movPortaria.getHoraInicioCarregamento() == null ? 0
						: dateU.retornaMes(movPortaria.getHoraInicioCarregamento());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTempos;
	}

	public List<FaixaHorarioPortaria> getListFaixaHorario(Date dataInicio, Date dataFim) throws JPAException {
		SimpleDateFormat formatterString = new SimpleDateFormat("dd/MM/yyyy");
		List<FaixaHorarioPortaria> listHorarioPortaria = new ArrayList<FaixaHorarioPortaria>();
		List<Map> list711 = portariaService.getQtdFaixaHorario(formatterString.format(dataInicio),
				formatterString.format(dataFim), "07:00:00", "11:00:00");

		Iterator it = list711.iterator();
		while (it.hasNext()) {
			FaixaHorarioPortaria faixaHorarioPortaria = new FaixaHorarioPortaria();
			Map faixas = (Map) it.next();
			faixaHorarioPortaria.setQuantidade((Long) faixas.get("QTD"));
			faixaHorarioPortaria.setDia(DateUtils.retornaMesAno(faixas.get("MESANO").toString()));

			faixaHorarioPortaria.setHoraInicio(new Time(DateUtils.retornaMilisegundoDeHora("07:00:00")));
			faixaHorarioPortaria.setHoraFim(new Time(DateUtils.retornaMilisegundoDeHora("11:00:00")));

			listHorarioPortaria.add(faixaHorarioPortaria);
		}

		List<Map> list1115 = portariaService.getQtdFaixaHorario(formatterString.format(dataInicio),
				formatterString.format(dataFim), "11:00:01", "15:00:00");

		Iterator it2 = list1115.iterator();
		while (it2.hasNext()) {
			FaixaHorarioPortaria faixaHorarioPortaria = new FaixaHorarioPortaria();
			Map faixas = (Map) it2.next();
			faixaHorarioPortaria.setQuantidade((Long) faixas.get("QTD"));
			faixaHorarioPortaria.setDia(DateUtils.retornaMesAno(faixas.get("MESANO").toString()));
			faixaHorarioPortaria.setHoraInicio(new Time(DateUtils.retornaMilisegundoDeHora("11:00:01")));
			faixaHorarioPortaria.setHoraFim(new Time(DateUtils.retornaMilisegundoDeHora("15:00:00")));

			listHorarioPortaria.add(faixaHorarioPortaria);
		}

		List<Map> list1519 = portariaService.getQtdFaixaHorario(formatterString.format(dataInicio),
				formatterString.format(dataFim), "15:00:01", "19:00:00");

		Iterator it3 = list1519.iterator();
		while (it3.hasNext()) {
			FaixaHorarioPortaria faixaHorarioPortaria = new FaixaHorarioPortaria();
			Map faixas = (Map) it3.next();
			faixaHorarioPortaria.setQuantidade((Long) faixas.get("QTD"));
			faixaHorarioPortaria.setDia(DateUtils.retornaMesAno(faixas.get("MESANO").toString()));
			faixaHorarioPortaria.setHoraInicio(new Time(DateUtils.retornaMilisegundoDeHora("15:00:01")));
			faixaHorarioPortaria.setHoraFim(new Time(DateUtils.retornaMilisegundoDeHora("19:00:00")));

			listHorarioPortaria.add(faixaHorarioPortaria);
		}

		List<Map> list1923 = portariaService.getQtdFaixaHorario(formatterString.format(dataInicio),
				formatterString.format(dataFim), "19:00:01", "23:00:00");

		Iterator it4 = list1923.iterator();
		while (it4.hasNext()) {
			FaixaHorarioPortaria faixaHorarioPortaria = new FaixaHorarioPortaria();
			Map faixas = (Map) it4.next();
			faixaHorarioPortaria.setQuantidade((Long) faixas.get("QTD"));
			faixaHorarioPortaria.setDia(DateUtils.retornaMesAno(faixas.get("MESANO").toString()));
			faixaHorarioPortaria.setHoraInicio(new Time(DateUtils.retornaMilisegundoDeHora("19:00:01")));
			faixaHorarioPortaria.setHoraFim(new Time(DateUtils.retornaMilisegundoDeHora("23:00:00")));

			listHorarioPortaria.add(faixaHorarioPortaria);
		}

		List<Map> list2300 = new ArrayList<Map>();
		list2300 = portariaService.getQtdFaixaHorario(formatterString.format(dataInicio), formatterString.format(dataFim), "23:00:01", "23:59:59");
		List<Map> list0006 = new ArrayList<Map>();
		list0006 = portariaService.getQtdFaixaHorario(formatterString.format(dataInicio), formatterString.format(dataFim), "00:00:00", "06:59:59");

		Iterator it5 = list2300.iterator();
		while (it5.hasNext()) {
			FaixaHorarioPortaria faixaHorarioPortaria = new FaixaHorarioPortaria();
			Map faixas = (Map) it5.next();
			Iterator it6 = list0006.iterator();

			while (it6.hasNext()) {
				Map faixas2 = (Map) it6.next();
				if (faixas.get("MESANO").equals(faixas2.get("MESANO"))) {
					Long valor1 = (Long) faixas.get("QTD");
					Long valor2 = (Long) faixas2.get("QTD");
					faixaHorarioPortaria.setQuantidade(valor1 + valor2);
				} else {
					faixaHorarioPortaria.setQuantidade((Long) faixas.get("QTD"));
				}
			}
			faixaHorarioPortaria.setDia(DateUtils.retornaMesAno(faixas.get("MESANO").toString()));
			faixaHorarioPortaria.setHoraInicio(new Time(DateUtils.retornaMilisegundoDeHora("23:00:01")));
			faixaHorarioPortaria.setHoraFim(new Time(DateUtils.retornaMilisegundoDeHora("06:59:59")));

			listHorarioPortaria.add(faixaHorarioPortaria);
		}

		return listHorarioPortaria;
	}

	public List<HistMovPortaria> retornaRegistrosAlterados(MovPortaria movPortariaSelecionado, String usuario)
			throws JPAException {
		MovPortaria movPortariaDB = new MovPortaria();
		movPortariaDB = portariaService.findById(movPortariaSelecionado.getIdMov());

		List<HistMovPortaria> listHistMovPortaria = new ArrayList<HistMovPortaria>();
		Date hoje = new Date(System.currentTimeMillis());
		DateUtils dateU = new DateUtils();

		if (!DataTypes.parseNull(movPortariaDB.getPlaca()).equalsIgnoreCase(movPortariaSelecionado.getPlaca())) {
			HistMovPortaria histMovPortaria = new HistMovPortaria();
			histMovPortaria.setData(hoje);
			histMovPortaria.setUsuario(usuario);
			histMovPortaria.setDe(movPortariaDB.getPlaca());
			histMovPortaria.setPara(movPortariaSelecionado.getPlaca());
			histMovPortaria.setColuna("PLACA");
			histMovPortaria.setJustificativa(movPortariaSelecionado.getJustificativa());

			listHistMovPortaria.add(histMovPortaria);
		}

		if (!DataTypes.parseNull(movPortariaDB.getPeso()).equals(movPortariaSelecionado.getPeso())) {
			HistMovPortaria histMovPortaria = new HistMovPortaria();
			histMovPortaria.setData(hoje);
			histMovPortaria.setUsuario(usuario);
			histMovPortaria.setDe(DataTypes.parseNull(movPortariaDB.getPeso()).toString());
			histMovPortaria.setPara(DataTypes.parseNull(movPortariaSelecionado.getPeso()).toString());
			histMovPortaria.setColuna("PESO");
			histMovPortaria.setJustificativa(movPortariaSelecionado.getJustificativa());

			listHistMovPortaria.add(histMovPortaria);
		}

		if (!DataTypes.parseNull(movPortariaDB.getPeso2()).equals(movPortariaSelecionado.getPeso2())) {
			HistMovPortaria histMovPortaria = new HistMovPortaria();
			histMovPortaria.setData(hoje);
			histMovPortaria.setUsuario(usuario);
			histMovPortaria.setDe(DataTypes.parseNull(movPortariaDB.getPeso2()).toString());
			histMovPortaria.setPara(DataTypes.parseNull(movPortariaSelecionado.getPeso2()).toString());
			histMovPortaria.setColuna("PESO 2");
			histMovPortaria.setJustificativa(movPortariaSelecionado.getJustificativa());

			listHistMovPortaria.add(histMovPortaria);
		}

		if (!DataTypes.parseNull(movPortariaDB.getNumeroOrdem())
				.equalsIgnoreCase(movPortariaSelecionado.getNumeroOrdem())) {
			HistMovPortaria histMovPortaria = new HistMovPortaria();
			histMovPortaria.setData(hoje);
			histMovPortaria.setUsuario(usuario);
			histMovPortaria.setDe(movPortariaDB.getNumeroOrdem());
			histMovPortaria.setPara(movPortariaSelecionado.getNumeroOrdem());
			histMovPortaria.setColuna("ORDEM DE PAGTO");
			histMovPortaria.setJustificativa(movPortariaSelecionado.getJustificativa());

			listHistMovPortaria.add(histMovPortaria);
		}

		if (movPortariaDB.getData() != null && movPortariaSelecionado.getData() != null
				&& !dateU.compararDatasEmString(movPortariaDB.getData(), movPortariaSelecionado.getData(),
						"dd/MM/yyyy HH:mm:ss")) {
			HistMovPortaria histMovPortaria = new HistMovPortaria();
			histMovPortaria.setData(hoje);
			histMovPortaria.setUsuario(usuario);
			histMovPortaria.setDe(dateU.obtemData(movPortariaDB.getData(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setPara(dateU.obtemData(movPortariaSelecionado.getData(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setColuna("HORARIO DE APRESENTAÇÃO");
			histMovPortaria.setJustificativa(movPortariaSelecionado.getJustificativa());

			listHistMovPortaria.add(histMovPortaria);
		}

		if (movPortariaDB.getHoraOrdemCarrega() != null && movPortariaSelecionado.getHoraOrdemCarrega() != null
				&& !dateU.compararDatasEmString(movPortariaDB.getHoraOrdemCarrega(),
						movPortariaSelecionado.getHoraOrdemCarrega(), "dd/MM/yyyy HH:mm:ss")) {
			HistMovPortaria histMovPortaria = new HistMovPortaria();
			histMovPortaria.setData(hoje);
			histMovPortaria.setUsuario(usuario);
			histMovPortaria.setDe(dateU.obtemData(movPortariaDB.getHoraOrdemCarrega(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria
					.setPara(dateU.obtemData(movPortariaSelecionado.getHoraOrdemCarrega(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setColuna("HORARIO DE ORDEM DE CARREGAMENTO");
			histMovPortaria.setJustificativa(movPortariaSelecionado.getJustificativa());

			listHistMovPortaria.add(histMovPortaria);
		}

		if (movPortariaDB.getHoraEntrada() != null && movPortariaSelecionado.getHoraEntrada() != null
				&& !dateU.compararDatasEmString(movPortariaDB.getHoraEntrada(), movPortariaSelecionado.getHoraEntrada(),
						"dd/MM/yyyy HH:mm:ss")) {
			HistMovPortaria histMovPortaria = new HistMovPortaria();
			histMovPortaria.setData(hoje);
			histMovPortaria.setUsuario(usuario);
			histMovPortaria.setDe(dateU.obtemData(movPortariaDB.getHoraEntrada(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setPara(dateU.obtemData(movPortariaSelecionado.getHoraEntrada(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setColuna("HORARIO DE ENTRADA");
			histMovPortaria.setJustificativa(movPortariaSelecionado.getJustificativa());

			listHistMovPortaria.add(histMovPortaria);
		}

		if (movPortariaDB.getHoraInicioCarregamento() != null
				&& movPortariaSelecionado.getHoraInicioCarregamento() != null
				&& !dateU.compararDatasEmString(movPortariaDB.getHoraInicioCarregamento(),
						movPortariaSelecionado.getHoraInicioCarregamento(), "dd/MM/yyyy HH:mm:ss")) {
			HistMovPortaria histMovPortaria = new HistMovPortaria();
			histMovPortaria.setData(hoje);
			histMovPortaria.setUsuario(usuario);
			histMovPortaria.setDe(dateU.obtemData(movPortariaDB.getHoraInicioCarregamento(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setPara(
					dateU.obtemData(movPortariaSelecionado.getHoraInicioCarregamento(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setColuna("HORARIO DE INICIO DE CARREGAMENTO");
			histMovPortaria.setJustificativa(movPortariaSelecionado.getJustificativa());

			listHistMovPortaria.add(histMovPortaria);
		}

		if (movPortariaDB.getHoraTerminoCarregamento() != null
				&& movPortariaSelecionado.getHoraTerminoCarregamento() != null
				&& !dateU.compararDatasEmString(movPortariaDB.getHoraTerminoCarregamento(),
						movPortariaSelecionado.getHoraTerminoCarregamento(), "dd/MM/yyyy HH:mm:ss")) {
			
			HistMovPortaria histMovPortaria = new HistMovPortaria();
			histMovPortaria.setData(hoje);
			histMovPortaria.setUsuario(usuario);
			histMovPortaria.setDe(dateU.obtemData(movPortariaDB.getHoraInicioCarregamento(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setPara(dateU.obtemData(movPortariaSelecionado.getHoraInicioCarregamento(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setColuna("HORARIO DE TERMINO DE CARREGAMENTO");
			histMovPortaria.setJustificativa(movPortariaSelecionado.getJustificativa());

			listHistMovPortaria.add(histMovPortaria);
		}

		if (movPortariaDB.getHoraSaida() != null && movPortariaSelecionado.getHoraSaida() != null
				&& !dateU.compararDatasEmString(movPortariaDB.getHoraSaida(), movPortariaSelecionado.getHoraSaida(), "dd/MM/yyyy HH:mm:ss")) {
			HistMovPortaria histMovPortaria = new HistMovPortaria();
			histMovPortaria.setData(hoje);
			histMovPortaria.setUsuario(usuario);
			histMovPortaria.setDe(dateU.obtemData(movPortariaDB.getHoraSaida(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setPara(dateU.obtemData(movPortariaSelecionado.getHoraSaida(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setColuna("HORARIO DE SAIDA");
			histMovPortaria.setJustificativa(movPortariaSelecionado.getJustificativa());

			listHistMovPortaria.add(histMovPortaria);
		}

		return listHistMovPortaria;
	}

	public void salvarDetalhe() throws JPAException {
		List<HistMovPortaria> listHistMovPortaria = new ArrayList<HistMovPortaria>();
		listHistMovPortaria = this.retornaRegistrosAlterados(movPortariaSelecionado, login.getUsuario().getUsername());

		if (this.portariaService.update(this.movPortariaSelecionado)) {
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			this.redirecionaPaginaPrincipal();
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
		}

		for (HistMovPortaria histMovPortaria : listHistMovPortaria) {
			historicoMovimentacaoPortariaService.save(histMovPortaria);
		}

	}

	public void carregarPesoCarregado() throws JPAException {
		DateUtils dateU = new DateUtils();

		String dataInicio = dateU.obtemData(dtInicio, "dd/MM/yyyy");
		String dataFim = dateU.obtemData(dtFim, "dd/MM/yyyy");

		List<Map> listManual1Turno = portariaService.getQtdPesoTipoCargaTurno(dataInicio, dataFim, "07:00:00",
				"15:00:00", "MANUAL");
		List<Map> listManual2Turno = portariaService.getQtdPesoTipoCargaTurno(dataInicio, dataFim, "15:00:01",
				"23:59:59", "MANUAL");
		List<Map> listManual22Turno = portariaService.getQtdPesoTipoCargaTurno(dataInicio, dataFim, "00:00:00",
				"06:59:59", "MANUAL");
		List<Map> listPalet1Turno = portariaService.getQtdPesoTipoCargaTurno(dataInicio, dataFim, "07:00:00",
				"15:00:00", "PALET");
		List<Map> listPalet2Turno = portariaService.getQtdPesoTipoCargaTurno(dataInicio, dataFim, "15:00:01",
				"23:59:59", "PALET");
		List<Map> listPalet22Turno = portariaService.getQtdPesoTipoCargaTurno(dataInicio, dataFim, "00:00:00",
				"06:59:59", "PALET");

		setListPesoCarregadoTurno(this.montarPesoCarregadoPorTurno(listManual1Turno, listManual2Turno,
				listManual22Turno, listPalet1Turno, listPalet2Turno, listPalet22Turno));
	}

	public void carregarQuantidadeVeiculosTurnoDetalhado() throws JPAException {
		DateUtils dateU = new DateUtils();

		String dataInicio = dateU.obtemData(dtInicio, "dd/MM/yyyy");
		String dataFim = dateU.obtemData(dtFim, "dd/MM/yyyy");

		List<Map> listManual1Turno = portariaService.getQuantidadeVeiculosTurnoDetalhado(dataInicio, dataFim,
				"07:00:00", "15:00:00", "MANUAL");
		List<Map> listManual2Turno = portariaService.getQuantidadeVeiculosTurnoDetalhado(dataInicio, dataFim,
				"15:00:01", "23:59:59", "MANUAL");
		List<Map> listManual22Turno = portariaService.getQuantidadeVeiculosTurnoDetalhado(dataInicio, dataFim,
				"00:00:00", "06:59:59", "MANUAL");
		List<Map> listPalet1Turno = portariaService.getQuantidadeVeiculosTurnoDetalhado(dataInicio, dataFim, "07:00:00",
				"15:00:00", "PALET");
		List<Map> listPalet2Turno = portariaService.getQuantidadeVeiculosTurnoDetalhado(dataInicio, dataFim, "15:00:01",
				"23:59:59", "PALET");
		List<Map> listPalet22Turno = portariaService.getQuantidadeVeiculosTurnoDetalhado(dataInicio, dataFim,
				"00:00:00", "06:59:59", "PALET");

		setListQuantidadeVeiculosDetalhados(this.montarQuantidadeVeiculosDetalhado(listManual1Turno, listManual2Turno,
				listManual22Turno, listPalet1Turno, listPalet2Turno, listPalet22Turno));
	}

	public void carregarFaixaHorarioCarregamento() throws JPAException {
		List<FaixaHorarioPortaria> listHorarioPortaria1 = new ArrayList<FaixaHorarioPortaria>();
		List<FaixaHorarioPortaria> listHorarioPortaria2 = new ArrayList<FaixaHorarioPortaria>();
		Date dataInicio = dtInicio;
		Date dataFim = dtFim;
		
		SimpleDateFormat formatterString = new SimpleDateFormat("dd/MM/yyyy");

		listHorarioPortaria1 = this.getListFaixaHorarioCarregamento(dataInicio, dataFim, true);

		listHorarioPortaria2 = this.getListFaixaHorarioCarregamento(dataInicio, dataFim, false);

		List<Map> listCIF = portariaService.getQtdTotalContracao(formatterString.format(dataInicio),
				formatterString.format(dataFim), "CIF");
		List<Map> listFOB = portariaService.getQtdTotalContracao(formatterString.format(dataInicio),
				formatterString.format(dataFim), "FOB");

		List<Map> listPALET = portariaService.getQtdTotalTipoCarga(formatterString.format(dataInicio),
				formatterString.format(dataFim), "PALET");
		List<Map> listMANUAL = portariaService.getQtdTotalTipoCarga(formatterString.format(dataInicio),
				formatterString.format(dataFim), "MANUAL");

		setListFaixaHorarioCarregamento(this.montarFaixaHorarioCarregamento(listHorarioPortaria1, listHorarioPortaria2, listCIF, listFOB, listPALET, listMANUAL));
	}

	public void carregarTotalPeso() throws JPAException {
		Date dataInicio = dtInicio;
		Date dataFim = dtFim;
		
		SimpleDateFormat formatterString = new SimpleDateFormat("dd/MM/yyyy");
		List<Map> listCIF = portariaService.getQtdPesoContratacao(formatterString.format(dataInicio),
				formatterString.format(dataFim), "CIF");
		List<Map> listFOB = portariaService.getQtdPesoContratacao(formatterString.format(dataInicio),
				formatterString.format(dataFim), "FOB");

		List<Map> listPALET = portariaService.getQtdPesoTipoCarga(formatterString.format(dataInicio),
				formatterString.format(dataFim), "PALET");
		List<Map> listMANUAL = portariaService.getQtdPesoTipoCarga(formatterString.format(dataInicio),
				formatterString.format(dataFim), "MANUAL");

		setListTotalPeso(this.montarTotalPeso(listCIF, listFOB, listPALET, listMANUAL));
	}

	public void carregarTempoCarregamentoTurno() throws JPAException {
		DateUtils dateU = new DateUtils();

		String dataInicio = dateU.obtemData(dtInicio, "dd/MM/yyyy");
		String dataFim = dateU.obtemData(dtFim, "dd/MM/yyyy");

		List<Map> listManualCIF1Turno = portariaService.getTemposCarregamento(dataInicio, dataFim, "MANUAL", "CIF",
				"07:00:00", "15:00:00");
		List<Map> listManualCIF2Turno = portariaService.getTemposCarregamento(dataInicio, dataFim, "MANUAL", "CIF",
				"15:00:01", "23:59:59");
		listManualCIF2Turno.addAll(
				portariaService.getTemposCarregamento(dataInicio, dataFim, "MANUAL", "CIF", "00:00:00", "06:59:59"));

		List<Map> listManualFOB1Turno = portariaService.getTemposCarregamento(dataInicio, dataFim, "MANUAL", "FOB",
				"07:00:00", "15:00:00");
		List<Map> listManualFOB2Turno = portariaService.getTemposCarregamento(dataInicio, dataFim, "MANUAL", "FOB",
				"15:00:01", "23:59:59");
		listManualFOB2Turno.addAll(
				portariaService.getTemposCarregamento(dataInicio, dataFim, "MANUAL", "FOB", "00:00:00", "06:59:59"));

		List<Map> listPaletCIF1Turno = portariaService.getTemposCarregamento(dataInicio, dataFim, "PALET", "CIF",
				"07:00:00", "15:00:00");
		List<Map> listPaletCIF2Turno = portariaService.getTemposCarregamento(dataInicio, dataFim, "PALET", "CIF",
				"15:00:01", "23:59:59");
		listPaletCIF2Turno.addAll(
				portariaService.getTemposCarregamento(dataInicio, dataFim, "PALET", "CIF", "00:00:00", "06:59:59"));

		List<Map> listPaletFOB1Turno = portariaService.getTemposCarregamento(dataInicio, dataFim, "PALET", "FOB",
				"07:00:00", "15:00:00");
		List<Map> listPaletFOB2Turno = portariaService.getTemposCarregamento(dataInicio, dataFim, "PALET", "FOB",
				"15:00:01", "23:59:59");
		listPaletFOB2Turno.addAll(
				portariaService.getTemposCarregamento(dataInicio, dataFim, "PALET", "FOB", "00:00:00", "06:59:59"));

		setListTempoCarregamentoTurno(this.montarTempoCarregamentoTurno(listManualCIF1Turno, listManualCIF2Turno,
				listManualFOB1Turno, listManualFOB2Turno, listPaletCIF1Turno, listPaletCIF2Turno, listPaletFOB1Turno,
				listPaletFOB2Turno));
	}

	public List<TempoCarregadoTurno> montarTempoCarregamentoTurno(List<Map> listManualCIF1Turno,
			List<Map> listManualCIF2Turno, List<Map> listManualFOB1Turno, List<Map> listManualFOB2Turno,
			List<Map> listPaletCIF1Turno, List<Map> listPaletCIF2Turno, List<Map> listPaletFOB1Turno,
			List<Map> listPaletFOB2Turno) throws JPAException {
		List<TempoCarregadoTurno> listTempoCarregamentoTurno = new ArrayList<TempoCarregadoTurno>();

		for (int i = 1; i < 5; i++) {
			TempoCarregadoTurno tempoCarregadoTurno = new TempoCarregadoTurno();

			switch (i) {

			case 1:
				tempoCarregadoTurno.setTipoCarga("MANUAL");
				tempoCarregadoTurno.setTurno("1º Turno");
				tempoCarregadoTurno.setTempoCIF(calculaMediaString(listManualCIF1Turno));
				tempoCarregadoTurno.setTempoFOB(calculaMediaString(listManualFOB1Turno));

				List<Map> listMediaTurno = new ArrayList<Map>();
				listMediaTurno.addAll(listManualCIF1Turno);
				listMediaTurno.addAll(listManualFOB1Turno);
				tempoCarregadoTurno.setMediaTurno(calculaMediaString(listMediaTurno));

				List<Map> listMediaTipoCarga = new ArrayList<Map>();
				listMediaTipoCarga.addAll(listManualCIF1Turno);
				listMediaTipoCarga.addAll(listManualCIF2Turno);
				listMediaTipoCarga.addAll(listManualFOB1Turno);
				listMediaTipoCarga.addAll(listManualFOB2Turno);
				tempoCarregadoTurno.setMediaTipoCarga(calculaMediaString(listMediaTipoCarga));

				listTempoCarregamentoTurno.add(tempoCarregadoTurno);
				break;

			case 2:
				tempoCarregadoTurno.setTipoCarga("MANUAL");
				tempoCarregadoTurno.setTurno("2º Turno");
				tempoCarregadoTurno.setTempoCIF(calculaMediaString(listManualCIF2Turno));
				tempoCarregadoTurno.setTempoFOB(calculaMediaString(listManualFOB2Turno));

				List<Map> listMediaTurno2 = new ArrayList<Map>();
				listMediaTurno2.addAll(listManualCIF2Turno);
				listMediaTurno2.addAll(listManualFOB2Turno);
				tempoCarregadoTurno.setMediaTurno(calculaMediaString(listMediaTurno2));

				listTempoCarregamentoTurno.add(tempoCarregadoTurno);
				break;

			case 3:
				tempoCarregadoTurno.setTipoCarga("PALET");
				tempoCarregadoTurno.setTurno("1º Turno");
				tempoCarregadoTurno.setTempoCIF(calculaMediaString(listPaletCIF1Turno));
				tempoCarregadoTurno.setTempoFOB(calculaMediaString(listPaletFOB1Turno));

				List<Map> listMediaTurno3 = new ArrayList<Map>();
				listMediaTurno3.addAll(listPaletCIF1Turno);
				listMediaTurno3.addAll(listPaletFOB1Turno);
				tempoCarregadoTurno.setMediaTurno(calculaMediaString(listMediaTurno3));

				List<Map> listMediaTipoCarga3 = new ArrayList<Map>();
				listMediaTipoCarga3.addAll(listPaletCIF1Turno);
				listMediaTipoCarga3.addAll(listPaletCIF2Turno);
				listMediaTipoCarga3.addAll(listPaletFOB1Turno);
				listMediaTipoCarga3.addAll(listPaletFOB2Turno);
				tempoCarregadoTurno.setMediaTipoCarga(calculaMediaString(listMediaTipoCarga3));

				listTempoCarregamentoTurno.add(tempoCarregadoTurno);
				break;

			case 4:
				tempoCarregadoTurno.setTipoCarga("PALET");
				tempoCarregadoTurno.setTurno("2º Turno");
				tempoCarregadoTurno.setTempoCIF(calculaMediaString(listPaletCIF2Turno));
				tempoCarregadoTurno.setTempoFOB(calculaMediaString(listPaletFOB2Turno));

				List<Map> listMediaTurno4 = new ArrayList<Map>();
				listMediaTurno4.addAll(listPaletCIF2Turno);
				listMediaTurno4.addAll(listPaletFOB2Turno);
				tempoCarregadoTurno.setMediaTurno(calculaMediaString(listMediaTurno4));

				listTempoCarregamentoTurno.add(tempoCarregadoTurno);
				break;
			default:
				break;
			}
		}

		return listTempoCarregamentoTurno;
	}

	public List<TotalPeso> montarTotalPeso(List<Map> listCIF, List<Map> listFOB, List<Map> listPALET,
			List<Map> listMANUAL) throws JPAException {
		List<TotalPeso> listTotalPeso = new ArrayList<TotalPeso>();

		DateUtils dateU = new DateUtils();

		if (!listCIF.isEmpty()) {
			Iterator itCIF = listCIF.iterator();
			while (itCIF.hasNext()) {
				Map cif = (Map) itCIF.next();
				TotalPeso totalPeso = new TotalPeso();
				Date data = DateUtils.retornaMesAno(cif.get("MESANO").toString());
				totalPeso.setData(data);
				totalPeso.setMes(dateU.getNomeMes(dateU.retornaMes(data) + 1) + "/" + dateU.retornaAno(data));
				if (cif.get("PESO") != null)
					totalPeso.setQtdCIF((Double) cif.get("PESO"));

				Iterator itFOB = listFOB.iterator();
				while (itFOB.hasNext()) {
					Map fob = (Map) itFOB.next();
					if (cif.get("MESANO").equals(fob.get("MESANO")) && fob.get("PESO") != null) {
						totalPeso.setQtdFOB((Double) fob.get("PESO"));
						break;
					}
				}

				Iterator itPALET = listPALET.iterator();
				while (itPALET.hasNext()) {
					Map palet = (Map) itPALET.next();
					if (cif.get("MESANO").equals(palet.get("MESANO")) && palet.get("PESO") != null) {
						totalPeso.setQtdPalet((Double) palet.get("PESO"));
						break;
					}
				}

				Iterator itMANUAL = listMANUAL.iterator();
				while (itMANUAL.hasNext()) {
					Map manual = (Map) itMANUAL.next();
					if (cif.get("MESANO").equals(manual.get("MESANO")) && manual.get("PESO") != null) {
						totalPeso.setQtdManual((Double) manual.get("PESO"));
						break;
					}
				}

				totalPeso.setQtdTotal(
						DataTypes.parseNull(totalPeso.getQtdCIF()) + DataTypes.parseNull(totalPeso.getQtdFOB()));

				listTotalPeso.add(totalPeso);
			}
		} else {
			Iterator itFOB = listFOB.iterator();
			while (itFOB.hasNext()) {
				Map fob = (Map) itFOB.next();
				TotalPeso totalPeso = new TotalPeso();
				Date data = DateUtils.retornaMesAno(fob.get("MESANO").toString());
				totalPeso.setData(data);
				totalPeso.setMes(dateU.getNomeMes(dateU.retornaMes(data) + 1) + "/" + dateU.retornaAno(data));
				if (fob.get("PESO") != null)
					totalPeso.setQtdCIF((Double) fob.get("PESO"));

				Iterator itCIF = listCIF.iterator();
				while (itFOB.hasNext()) {
					Map cif = (Map) itFOB.next();
					if (cif.get("MESANO").equals(fob.get("MESANO")) && fob.get("PESO") != null) {
						totalPeso.setQtdFOB((Double) fob.get("PESO"));
						break;
					}
				}

				Iterator itPALET = listPALET.iterator();
				while (itPALET.hasNext()) {
					Map palet = (Map) itPALET.next();
					if (fob.get("MESANO").equals(palet.get("MESANO")) && palet.get("PESO") != null) {
						totalPeso.setQtdPalet((Double) palet.get("PESO"));
						break;
					}
				}

				Iterator itMANUAL = listMANUAL.iterator();
				while (itMANUAL.hasNext()) {
					Map manual = (Map) itMANUAL.next();
					if (fob.get("MESANO").equals(manual.get("MESANO")) && manual.get("PESO") != null) {
						totalPeso.setQtdManual((Double) manual.get("PESO"));
						break;
					}
				}

				totalPeso.setQtdTotal(
						DataTypes.parseNull(totalPeso.getQtdCIF()) + DataTypes.parseNull(totalPeso.getQtdFOB()));

				listTotalPeso.add(totalPeso);
			}
		}
		Collections.sort(listTotalPeso);
		return listTotalPeso;
	}

	public List<FaixaHorarioCarregamento> montarFaixaHorarioCarregamento(
			List<FaixaHorarioPortaria> listFaixaHorarioPortaria1, List<FaixaHorarioPortaria> listFaixaHorarioPortaria2,
			List<Map> listCIF, List<Map> listFOB, List<Map> listPALET, List<Map> listMANUAL) throws JPAException {

		List<FaixaHorarioCarregamento> listFaixaHorarioCarregamento = new ArrayList<FaixaHorarioCarregamento>();

		DateUtils dateU = new DateUtils();

		if (!listFaixaHorarioPortaria1.isEmpty()) {
			for (FaixaHorarioPortaria faixaHorarioPortaria : listFaixaHorarioPortaria1) {
				FaixaHorarioCarregamento faixaHorarioCarregamento = new FaixaHorarioCarregamento();

				faixaHorarioCarregamento.setMes(dateU.getNomeMes(dateU.retornaMes(faixaHorarioPortaria.getDia()) + 1)
						+ "/" + dateU.retornaAno(faixaHorarioPortaria.getDia()));
				faixaHorarioCarregamento.setQtd1Turno(faixaHorarioPortaria.getQuantidade());
				faixaHorarioCarregamento.setData(faixaHorarioPortaria.getDia());

				for (FaixaHorarioPortaria faixaHorarioPortaria2 : listFaixaHorarioPortaria2) {
					if (faixaHorarioPortaria2.getDia().equals(faixaHorarioPortaria.getDia())
							&& faixaHorarioPortaria2.getTurno() == 2) {
						faixaHorarioCarregamento.setQtd2Turno(faixaHorarioPortaria2.getQuantidade());
						break;
					}
				}

				Iterator itCIF = listCIF.iterator();
				while (itCIF.hasNext()) {
					Map cif = (Map) itCIF.next();
					if (faixaHorarioPortaria.getDia().equals(DateUtils.retornaMesAno(cif.get("MESANO").toString()))
							&& cif.get("QTD") != null) {
						faixaHorarioCarregamento.setDetalhe("CIF: " + cif.get("QTD") + "<br></br>");
						break;
					}
				}

				Iterator itFOB = listFOB.iterator();
				while (itFOB.hasNext()) {
					Map fob = (Map) itFOB.next();
					if (faixaHorarioPortaria.getDia().equals(DateUtils.retornaMesAno(fob.get("MESANO").toString()))
							&& fob.get("QTD") != null) {
						faixaHorarioCarregamento.setDetalhe(
								faixaHorarioCarregamento.getDetalhe() + "FOB: " + fob.get("QTD") + "<hr></hr>");
						break;
					}
				}

				Iterator itPALET = listPALET.iterator();
				while (itPALET.hasNext()) {
					Map palet = (Map) itPALET.next();
					if (faixaHorarioPortaria.getDia().equals(DateUtils.retornaMesAno(palet.get("MESANO").toString()))
							&& palet.get("QTD") != null) {
						faixaHorarioCarregamento.setDetalhe(faixaHorarioCarregamento.getDetalhe() + "PALETIZADO: "
								+ palet.get("QTD") + "<br></br>");
						break;
					}
				}

				Iterator itMANUAL = listMANUAL.iterator();
				while (itMANUAL.hasNext()) {
					Map manual = (Map) itMANUAL.next();
					if (faixaHorarioPortaria.getDia().equals(DateUtils.retornaMesAno(manual.get("MESANO").toString()))
							&& manual.get("QTD") != null) {
						faixaHorarioCarregamento
								.setDetalhe(faixaHorarioCarregamento.getDetalhe() + "MANUAL: " + manual.get("QTD"));
						break;
					}
				}
				String mesAno = dateU.retornaMesAno(faixaHorarioPortaria.getDia());

				List<Map> listDiasCarregamento = portariaService.getQtdDiasMesCarregamento("01/" + mesAno,
						"" + dateU.retornaUltimoDiaDoMes(faixaHorarioPortaria.getDia()) + "/" + mesAno);
				faixaHorarioCarregamento.setQtdDiasMes(listDiasCarregamento.size());
				faixaHorarioCarregamento
						.setMed1Turno(DataTypes.parseNull(faixaHorarioCarregamento.getQtd1Turno()).doubleValue()
								/ DataTypes.parseNull(faixaHorarioCarregamento.getQtdDiasMes()));
				faixaHorarioCarregamento
						.setMed2Turno(DataTypes.parseNull(faixaHorarioCarregamento.getQtd2Turno()).doubleValue()
								/ DataTypes.parseNull(faixaHorarioCarregamento.getQtdDiasMes()));

				faixaHorarioCarregamento.setQtdTotal(DataTypes.parseNull(faixaHorarioCarregamento.getQtd1Turno())
						+ DataTypes.parseNull(faixaHorarioCarregamento.getQtd2Turno()));

				faixaHorarioCarregamento.setPerc1Turno(DataTypes.returnPercent(
						DataTypes.parseNull(faixaHorarioCarregamento.getQtd1Turno()).doubleValue(),
						DataTypes.parseNull(faixaHorarioCarregamento.getQtdTotal()).doubleValue()));
				faixaHorarioCarregamento.setPerc2Turno(DataTypes.returnPercent(
						DataTypes.parseNull(faixaHorarioCarregamento.getQtd2Turno()).doubleValue(),
						DataTypes.parseNull(faixaHorarioCarregamento.getQtdTotal()).doubleValue()));

				listFaixaHorarioCarregamento.add(faixaHorarioCarregamento);
			}
		} else {
			for (FaixaHorarioPortaria faixaHorarioPortaria2 : listFaixaHorarioPortaria2) {
				FaixaHorarioCarregamento faixaHorarioCarregamento = new FaixaHorarioCarregamento();

				faixaHorarioCarregamento.setMes(dateU.getNomeMes(dateU.retornaMes(faixaHorarioPortaria2.getDia()) + 1)
						+ "/" + dateU.retornaAno(faixaHorarioPortaria2.getDia()));
				faixaHorarioCarregamento.setQtd2Turno(faixaHorarioPortaria2.getQuantidade());
				faixaHorarioCarregamento.setData(faixaHorarioPortaria2.getDia());

				for (FaixaHorarioPortaria faixaHorarioPortaria1 : listFaixaHorarioPortaria1) {
					if (faixaHorarioPortaria2.getDia().equals(faixaHorarioPortaria1.getDia())
							&& faixaHorarioPortaria1.getTurno() == 1) {
						faixaHorarioCarregamento.setQtd1Turno(faixaHorarioPortaria1.getQuantidade());
						break;
					}
				}

				Iterator itCIF = listCIF.iterator();
				while (itCIF.hasNext()) {
					Map cif = (Map) itCIF.next();
					if (faixaHorarioPortaria2.getDia().equals(DateUtils.retornaMesAno(cif.get("MESANO").toString()))
							&& cif.get("QTD") != null) {
						faixaHorarioCarregamento.setDetalhe("CIF: " + cif.get("QTD") + "<br></br>");
						break;
					}
				}

				Iterator itFOB = listFOB.iterator();
				while (itFOB.hasNext()) {
					Map fob = (Map) itFOB.next();
					if (faixaHorarioPortaria2.getDia().equals(DateUtils.retornaMesAno(fob.get("MESANO").toString()))
							&& fob.get("QTD") != null) {
						faixaHorarioCarregamento.setDetalhe(
								faixaHorarioCarregamento.getDetalhe() + "FOB: " + fob.get("QTD") + "<hr></hr>");
						break;
					}
				}

				Iterator itPALET = listPALET.iterator();
				while (itPALET.hasNext()) {
					Map palet = (Map) itPALET.next();
					if (faixaHorarioPortaria2.getDia().equals(DateUtils.retornaMesAno(palet.get("MESANO").toString()))
							&& palet.get("QTD") != null) {
						faixaHorarioCarregamento.setDetalhe(faixaHorarioCarregamento.getDetalhe() + "PALETIZADO: "
								+ palet.get("QTD") + "<br></br>");
						break;
					}
				}

				Iterator itMANUAL = listMANUAL.iterator();
				while (itMANUAL.hasNext()) {
					Map manual = (Map) itMANUAL.next();
					if (faixaHorarioPortaria2.getDia().equals(DateUtils.retornaMesAno(manual.get("MESANO").toString()))
							&& manual.get("QTD") != null) {
						faixaHorarioCarregamento
								.setDetalhe(faixaHorarioCarregamento.getDetalhe() + "MANUAL: " + manual.get("QTD"));
						break;
					}
				}
				String mesAno = dateU.retornaMesAno(faixaHorarioPortaria2.getDia());

				List<Map> listDiasCarregamento = portariaService.getQtdDiasMesCarregamento("01/" + mesAno,
						"" + dateU.retornaUltimoDiaDoMes(faixaHorarioPortaria2.getDia()) + "/" + mesAno);
				faixaHorarioCarregamento.setQtdDiasMes(listDiasCarregamento.size());
				faixaHorarioCarregamento
						.setMed1Turno(DataTypes.parseNull(faixaHorarioCarregamento.getQtd1Turno()).doubleValue()
								/ DataTypes.parseNull(faixaHorarioCarregamento.getQtdDiasMes()));
				faixaHorarioCarregamento
						.setMed2Turno(DataTypes.parseNull(faixaHorarioCarregamento.getQtd2Turno()).doubleValue()
								/ DataTypes.parseNull(faixaHorarioCarregamento.getQtdDiasMes()));

				faixaHorarioCarregamento.setQtdTotal(DataTypes.parseNull(faixaHorarioCarregamento.getQtd1Turno())
						+ DataTypes.parseNull(faixaHorarioCarregamento.getQtd2Turno()));

				faixaHorarioCarregamento.setPerc1Turno(DataTypes.returnPercent(
						DataTypes.parseNull(faixaHorarioCarregamento.getQtd1Turno()).doubleValue(),
						DataTypes.parseNull(faixaHorarioCarregamento.getQtdTotal()).doubleValue()));
				faixaHorarioCarregamento.setPerc2Turno(DataTypes.returnPercent(
						DataTypes.parseNull(faixaHorarioCarregamento.getQtd2Turno()).doubleValue(),
						DataTypes.parseNull(faixaHorarioCarregamento.getQtdTotal()).doubleValue()));

				listFaixaHorarioCarregamento.add(faixaHorarioCarregamento);
			}
		}

		Collections.sort(listFaixaHorarioCarregamento);
		return listFaixaHorarioCarregamento;

	}

	public List<FaixaHorarioPortaria> getListFaixaHorarioCarregamento(Date dataInicio, Date dataFim, boolean isTurno1)
			throws JPAException {
		List<FaixaHorarioPortaria> listHorarioPortaria = new ArrayList<FaixaHorarioPortaria>();
		if (isTurno1) {
			Date hoje = new Date();
			SimpleDateFormat formatterString = new SimpleDateFormat("dd/MM/yyyy");
			List<Map> turno1 = portariaService.getQtdFaixaHorarioCarregamento(formatterString.format(dataInicio),
					formatterString.format(dataFim), "07:00:00", "15:00:00");

			Iterator it = turno1.iterator();
			while (it.hasNext()) {
				FaixaHorarioPortaria faixaHorarioPortaria = new FaixaHorarioPortaria();
				Map faixas = (Map) it.next();
				faixaHorarioPortaria.setQuantidade((Long) faixas.get("QTD"));
				faixaHorarioPortaria.setDia(DateUtils.retornaMesAno(faixas.get("MESANO").toString()));
				faixaHorarioPortaria.setTurno(1);
				listHorarioPortaria.add(faixaHorarioPortaria);
			}
		} else {

			Date hoje = new Date();
			SimpleDateFormat formatterString = new SimpleDateFormat("dd/MM/yyyy");
			List<Map> turno2 = portariaService.getQtdFaixaHorarioCarregamento(formatterString.format(dataInicio),
					formatterString.format(dataFim), "15:00:01", "23:59:59");
			List<Map> turno22 = portariaService.getQtdFaixaHorarioCarregamento(formatterString.format(dataInicio),
					formatterString.format(dataFim), "00:00:00", "06:59:59");

			Iterator it2 = turno2.iterator();

			while (it2.hasNext()) {
				boolean incluirList = false;
				FaixaHorarioPortaria faixaHorarioPortaria = new FaixaHorarioPortaria();
				Map faixas = (Map) it2.next();
				Iterator it3 = turno22.iterator();

				while (it3.hasNext()) {
					Map faixas2 = (Map) it3.next();
					if (faixas.get("MESANO").equals(faixas2.get("MESANO"))) {
						Long quantidade = (Long) faixas.get("QTD");
						Long quantidade2 = (Long) faixas2.get("QTD");
						faixaHorarioPortaria.setQuantidade(quantidade + quantidade2);
						faixaHorarioPortaria.setDia(DateUtils.retornaMesAno(faixas.get("MESANO").toString()));
						faixaHorarioPortaria.setTurno(2);
						listHorarioPortaria.add(faixaHorarioPortaria);
						incluirList = true;
					}
				}
				if (incluirList == false) {
					faixaHorarioPortaria.setQuantidade((Long) faixas.get("QTD"));
					faixaHorarioPortaria.setDia(DateUtils.retornaMesAno(faixas.get("MESANO").toString()));
					faixaHorarioPortaria.setTurno(2);
					listHorarioPortaria.add(faixaHorarioPortaria);
				}
			}
		}

		return listHorarioPortaria;
	}

	public List<QuantidadeVeiculosDetalhado> montarQuantidadeVeiculosDetalhado(List<Map> listManual1Turno,
			List<Map> listManual2Turno, List<Map> listManual22Turno, List<Map> listPalet1Turno,
			List<Map> listPalet2Turno, List<Map> listPalet22Turno) throws JPAException {
		List<QuantidadeVeiculosDetalhado> listQuantidadeVeiculosDetalhado = new ArrayList<QuantidadeVeiculosDetalhado>();

		for (int i = 1; i < 5; i++) {
			QuantidadeVeiculosDetalhado quantidadeVeiculoDetalhado = new QuantidadeVeiculosDetalhado();

			switch (i) {
			case 1:
				quantidadeVeiculoDetalhado.setTipoCarregamento("MANUAL");
				quantidadeVeiculoDetalhado.setTurno("1º Turno");
				Iterator it = listManual1Turno.iterator();
				while (it.hasNext()) {
					Map ma1 = (Map) it.next();
					if (ma1.get("CONTRATACAO").equals("CIF"))
						quantidadeVeiculoDetalhado.setQtdCIF((Long) ma1.get("QTD"));
					else
						quantidadeVeiculoDetalhado.setQtdFOB((Long) ma1.get("QTD"));
				}
				quantidadeVeiculoDetalhado.setQtdTotal(DataTypes.parseNull(quantidadeVeiculoDetalhado.getQtdCIF())
						+ DataTypes.parseNull(quantidadeVeiculoDetalhado.getQtdFOB()));
				listQuantidadeVeiculosDetalhado.add(quantidadeVeiculoDetalhado);
				break;

			case 2:
				quantidadeVeiculoDetalhado.setTipoCarregamento("MANUAL");
				quantidadeVeiculoDetalhado.setTurno("2º Turno");
				Iterator it2 = listManual2Turno.iterator();
				while (it2.hasNext()) {
					Map ma2 = (Map) it2.next();
					boolean incluirList = false;
					Iterator it22 = listManual22Turno.iterator();

					while (it22.hasNext()) {
						Map ma22 = (Map) it22.next();
						if (ma22.get("CONTRATACAO").equals(ma2.get("CONTRATACAO"))) {
							Long qtd1 = (Long) ma2.get("QTD");
							Long qtd2 = (Long) ma22.get("QTD");
							if (ma2.get("CONTRATACAO").equals("CIF"))
								quantidadeVeiculoDetalhado.setQtdCIF(qtd1 + qtd2);
							else
								quantidadeVeiculoDetalhado.setQtdFOB(qtd1 + qtd2);
						}
					}
					if (!incluirList) {
						if (ma2.get("CONTRATACAO").equals("CIF"))
							quantidadeVeiculoDetalhado.setQtdCIF((Long) ma2.get("QTD"));
						else
							quantidadeVeiculoDetalhado.setQtdFOB((Long) ma2.get("QTD"));
					}
				}
				quantidadeVeiculoDetalhado.setQtdTotal(DataTypes.parseNull(quantidadeVeiculoDetalhado.getQtdCIF())
						+ DataTypes.parseNull(quantidadeVeiculoDetalhado.getQtdFOB()));
				listQuantidadeVeiculosDetalhado.add(quantidadeVeiculoDetalhado);
				break;

			case 3:
				quantidadeVeiculoDetalhado.setTipoCarregamento("PALETIZADA");
				quantidadeVeiculoDetalhado.setTurno("1º Turno");
				Iterator it3 = listPalet1Turno.iterator();
				while (it3.hasNext()) {
					Map pa1 = (Map) it3.next();
					if (pa1.get("CONTRATACAO").equals("CIF"))
						quantidadeVeiculoDetalhado.setQtdCIF((Long) pa1.get("QTD"));
					else
						quantidadeVeiculoDetalhado.setQtdFOB((Long) pa1.get("QTD"));
				}
				quantidadeVeiculoDetalhado.setQtdTotal(DataTypes.parseNull(quantidadeVeiculoDetalhado.getQtdCIF())
						+ DataTypes.parseNull(quantidadeVeiculoDetalhado.getQtdFOB()));
				listQuantidadeVeiculosDetalhado.add(quantidadeVeiculoDetalhado);
				break;

			case 4:
				quantidadeVeiculoDetalhado.setTipoCarregamento("PALETIZADA");
				quantidadeVeiculoDetalhado.setTurno("2º Turno");
				Iterator it4 = listPalet2Turno.iterator();
				while (it4.hasNext()) {
					Map pa2 = (Map) it4.next();
					boolean incluirList = false;
					Iterator it42 = listPalet22Turno.iterator();

					while (it42.hasNext()) {
						Map pa22 = (Map) it42.next();
						if (pa2.get("CONTRATACAO").equals(pa22.get("CONTRATACAO"))) {
							incluirList = true;
							Long qtd1 = (Long) pa2.get("QTD");
							Long qtd2 = (Long) pa22.get("QTD");
							if (pa2.get("CONTRATACAO").equals("CIF"))
								quantidadeVeiculoDetalhado.setQtdCIF(qtd1 + qtd2);
							else
								quantidadeVeiculoDetalhado.setQtdFOB(qtd1 + qtd2);
						}
					}
					if (!incluirList) {
						if (pa2.get("CONTRATACAO").equals("CIF"))
							quantidadeVeiculoDetalhado.setQtdCIF((Long) pa2.get("QTD"));
						else
							quantidadeVeiculoDetalhado.setQtdFOB((Long) pa2.get("QTD"));
					}
				}
				quantidadeVeiculoDetalhado.setQtdTotal(DataTypes.parseNull(quantidadeVeiculoDetalhado.getQtdCIF())
						+ DataTypes.parseNull(quantidadeVeiculoDetalhado.getQtdFOB()));
				listQuantidadeVeiculosDetalhado.add(quantidadeVeiculoDetalhado);
				break;
			default:
				break;
			}

		}

		return listQuantidadeVeiculosDetalhado;
	}

	public List<PesoCarregadoTurno> montarPesoCarregadoPorTurno(List<Map> listManual1Turno, List<Map> listManual2Turno,
			List<Map> listManual22Turno, List<Map> listPalet1Turno, List<Map> listPalet2Turno,
			List<Map> listPalet22Turno) throws JPAException {

		List<PesoCarregadoTurno> listPesoCarregadoTurno = new ArrayList<PesoCarregadoTurno>();
		Double totalCIF1 = new Double(0);
		Double totalFOB1 = new Double(0);
		Double totalTotal1 = new Double(0);
		Double totalCIF2 = new Double(0);
		Double totalFOB2 = new Double(0);
		Double totalTotal2 = new Double(0);
		Double totalTotalGeral = new Double(0);
		String totalPerc1Turno;
		String totalPerc2Turno;
		String totalPercTotal;

		for (int i = 1; i < 5; i++) {
			PesoCarregadoTurno pesoCarregadoTurno = new PesoCarregadoTurno();

			switch (i) {
			case 1:
				pesoCarregadoTurno.setTipoCarga("MANUAL");
				Iterator it = listManual1Turno.iterator();
				while (it.hasNext()) {
					Map ma1 = (Map) it.next();
					if (ma1.get("CONTRATACAO").equals("CIF"))
						pesoCarregadoTurno.setQtd1CIF((Double) ma1.get("PESO"));
					else
						pesoCarregadoTurno.setQtd1FOB((Double) ma1.get("PESO"));
				}

				pesoCarregadoTurno.setQtd1Total(DataTypes.parseNull(pesoCarregadoTurno.getQtd1CIF())
						+ DataTypes.parseNull(pesoCarregadoTurno.getQtd1FOB()));

				Iterator it2 = listManual2Turno.iterator();

				while (it2.hasNext()) {
					boolean incluirList = false;
					Iterator it22 = listManual22Turno.iterator();
					Map ma2 = (Map) it2.next();

					while (it22.hasNext()) {
						Map ma22 = (Map) it22.next();
						if (ma2.get("CONTRATACAO").equals(ma22.get("CONTRATACAO"))) {
							incluirList = true;
							if (ma2.get("CONTRATACAO").equals("CIF")) {
								Double peso1 = (Double) ma2.get("PESO");
								Double peso2 = (Double) ma22.get("PESO");
								pesoCarregadoTurno.setQtd2CIF(peso1 + peso2);
							} else {
								Double peso1 = (Double) ma2.get("PESO");
								Double peso2 = (Double) ma22.get("PESO");
								pesoCarregadoTurno.setQtd2FOB(peso1 + peso2);
							}
						}
					}
					if (!incluirList) {
						if (ma2.get("CONTRATACAO").equals("CIF"))
							pesoCarregadoTurno.setQtd1CIF((Double) ma2.get("PESO"));
						else
							pesoCarregadoTurno.setQtd1FOB((Double) ma2.get("PESO"));
					}
				}

				pesoCarregadoTurno.setQtd2Total(DataTypes.parseNull(pesoCarregadoTurno.getQtd2CIF())
						+ DataTypes.parseNull(pesoCarregadoTurno.getQtd2FOB()));
				pesoCarregadoTurno.setTotalGeral(DataTypes.parseNull(pesoCarregadoTurno.getQtd1Total())
						+ DataTypes.parseNull(pesoCarregadoTurno.getQtd2Total()));
				pesoCarregadoTurno.setPercTurno1(
						DataTypes.returnPercent(pesoCarregadoTurno.getQtd1Total(), pesoCarregadoTurno.getTotalGeral()));
				pesoCarregadoTurno.setPercTurno2(
						DataTypes.returnPercent(pesoCarregadoTurno.getQtd2Total(), pesoCarregadoTurno.getTotalGeral()));

				totalCIF1 = DataTypes.parseNull(pesoCarregadoTurno.getQtd1CIF());
				totalFOB1 = DataTypes.parseNull(pesoCarregadoTurno.getQtd1FOB());
				totalTotal1 = DataTypes.parseNull(pesoCarregadoTurno.getQtd1Total());
				totalCIF2 = DataTypes.parseNull(pesoCarregadoTurno.getQtd2CIF());
				totalFOB2 = DataTypes.parseNull(pesoCarregadoTurno.getQtd2FOB());
				totalTotal2 = DataTypes.parseNull(pesoCarregadoTurno.getQtd2Total());
				totalTotalGeral = DataTypes.parseNull(pesoCarregadoTurno.getTotalGeral());

				listPesoCarregadoTurno.add(pesoCarregadoTurno);

				break;

			case 2:
				pesoCarregadoTurno.setTipoCarga("PALETIZADA");

				Iterator it3 = listPalet1Turno.iterator();
				while (it3.hasNext()) {
					Map ma2 = (Map) it3.next();
					if (ma2.get("CONTRATACAO").equals("CIF"))
						pesoCarregadoTurno.setQtd1CIF((Double) ma2.get("PESO"));
					else
						pesoCarregadoTurno.setQtd1FOB((Double) ma2.get("PESO"));
				}

				pesoCarregadoTurno.setQtd1Total(DataTypes.parseNull(pesoCarregadoTurno.getQtd1CIF())
						+ DataTypes.parseNull(pesoCarregadoTurno.getQtd1FOB()));

				Iterator it4 = listPalet2Turno.iterator();
				while (it4.hasNext()) {
					Map pa1 = (Map) it4.next();
					boolean incluirList = false;
					Iterator it42 = listPalet22Turno.iterator();

					while (it42.hasNext()) {
						Map pa12 = (Map) it42.next();
						if (pa1.get("CONTRATACAO").equals(pa12.get("CONTRATACAO"))) {
							incluirList = true;

							if (pa1.get("CONTRATACAO").equals("CIF")) {
								Double peso1 = (Double) pa1.get("PESO");
								Double peso2 = (Double) pa12.get("PESO");
								pesoCarregadoTurno.setQtd2CIF(peso1 + peso2);
							} else {
								Double peso1 = (Double) pa1.get("PESO");
								Double peso2 = (Double) pa12.get("PESO");
								pesoCarregadoTurno.setQtd2FOB(peso1 + peso2);
							}
						}
					}
					if (!incluirList) {
						if (pa1.get("CONTRATACAO").equals("CIF"))
							pesoCarregadoTurno.setQtd2CIF((Double) pa1.get("PESO"));
						else
							pesoCarregadoTurno.setQtd2FOB((Double) pa1.get("PESO"));
					}
				}

				pesoCarregadoTurno.setQtd2Total(DataTypes.parseNull(pesoCarregadoTurno.getQtd2CIF())
						+ DataTypes.parseNull(pesoCarregadoTurno.getQtd2FOB()));
				pesoCarregadoTurno.setQtd2Total(DataTypes.parseNull(pesoCarregadoTurno.getQtd2CIF())
						+ DataTypes.parseNull(pesoCarregadoTurno.getQtd2FOB()));
				pesoCarregadoTurno.setTotalGeral(DataTypes.parseNull(pesoCarregadoTurno.getQtd1Total())
						+ DataTypes.parseNull(pesoCarregadoTurno.getQtd2Total()));
				pesoCarregadoTurno.setPercTurno1(
						DataTypes.returnPercent(pesoCarregadoTurno.getQtd1Total(), pesoCarregadoTurno.getTotalGeral()));
				pesoCarregadoTurno.setPercTurno2(
						DataTypes.returnPercent(pesoCarregadoTurno.getQtd2Total(), pesoCarregadoTurno.getTotalGeral()));

				totalCIF1 += DataTypes.parseNull(pesoCarregadoTurno.getQtd1CIF());
				totalFOB1 += DataTypes.parseNull(pesoCarregadoTurno.getQtd1FOB());
				totalTotal1 += DataTypes.parseNull(pesoCarregadoTurno.getQtd1Total());
				totalCIF2 += DataTypes.parseNull(pesoCarregadoTurno.getQtd2CIF());
				totalFOB2 += DataTypes.parseNull(pesoCarregadoTurno.getQtd2FOB());
				totalTotal2 += DataTypes.parseNull(pesoCarregadoTurno.getQtd2Total());
				totalTotalGeral += DataTypes.parseNull(pesoCarregadoTurno.getTotalGeral());
				totalPerc1Turno = DataTypes.returnPercent(totalTotal1, totalTotalGeral);
				totalPerc2Turno = DataTypes.returnPercent(totalTotal2, totalTotalGeral);

				listPesoCarregadoTurno.add(pesoCarregadoTurno);
				break;

			case 3:
				pesoCarregadoTurno.setTipoCarga("TOTAL");
				pesoCarregadoTurno.setQtd1CIF(totalCIF1);
				pesoCarregadoTurno.setQtd1FOB(totalFOB1);
				pesoCarregadoTurno.setQtd1Total(totalTotal1);
				pesoCarregadoTurno.setQtd2CIF(totalCIF2);
				pesoCarregadoTurno.setQtd2FOB(totalFOB2);
				pesoCarregadoTurno.setQtd2Total(totalTotal2);
				pesoCarregadoTurno.setTotalGeral(totalTotalGeral);
				pesoCarregadoTurno.setPercTurno1(
						DataTypes.returnPercent(pesoCarregadoTurno.getQtd1Total(), pesoCarregadoTurno.getTotalGeral()));
				pesoCarregadoTurno.setPercTurno2(
						DataTypes.returnPercent(pesoCarregadoTurno.getQtd2Total(), pesoCarregadoTurno.getTotalGeral()));

				listPesoCarregadoTurno.add(pesoCarregadoTurno);
				break;
			default:
				break;
			}
		}
		return listPesoCarregadoTurno;
	}

	public String calculaMediaString(List<Map> listTempos) throws JPAException {
		Long totalTempoMilis = new Long(0);
		DateUtils dateU = new DateUtils();

		Iterator it = listTempos.iterator();
		while (it.hasNext()) {
			String string = (String) it.next();
			totalTempoMilis += dateU.retornaMilisegundosDaHora(string);
		}

		Double horaMilis = new Double(0);
		horaMilis = totalTempoMilis.doubleValue() / listTempos.size();

		return dateU.retornaHoraSomada(horaMilis);
	}

	public void getHoraAtual() throws JPAException {
		Date hoje = new Date(System.currentTimeMillis());
		DateUtils dateU = new DateUtils();
		movPortaria.setDataHora(dateU.obtemData(hoje, "dd/MM/yyyy HH:mm:ss"));
		setMovPortaria(movPortaria);
	}

	public void createMessage(Severity severity, String messageKeyPropertie) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
		flash.setKeepMessages(true);
		FacesMessage msg = new FacesMessage(severity,
				ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle())
						.getString(messageKeyPropertie),
				null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	public PortariaService getPortariaService() {
		return portariaService;
	}

	public void setPortariaService(PortariaService portariaService) {
		this.portariaService = portariaService;
	}

	public LoginController getLogin() {
		return login;
	}

	public void setLogin(LoginController login) {
		this.login = login;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public List<FaixaHorarioCarregamento> getListFaixaHorarioCarregamento() {
		return listFaixaHorarioCarregamento;
	}

	public void setListFaixaHorarioCarregamento(List<FaixaHorarioCarregamento> listFaixaHorarioCarregamento) {
		this.listFaixaHorarioCarregamento = listFaixaHorarioCarregamento;
	}

	public List<TotalPeso> getListTotalPeso() {
		return listTotalPeso;
	}

	public void setListTotalPeso(List<TotalPeso> listTotalPeso) {
		this.listTotalPeso = listTotalPeso;
	}

	public List<QuantidadeVeiculosDetalhado> getListQuantidadeVeiculosDetalhados() {
		return listQuantidadeVeiculosDetalhados;
	}

	public void setListQuantidadeVeiculosDetalhados(
			List<QuantidadeVeiculosDetalhado> listQuantidadeVeiculosDetalhados) {
		this.listQuantidadeVeiculosDetalhados = listQuantidadeVeiculosDetalhados;
	}

	public List<PesoCarregadoTurno> getListPesoCarregadoTurno() {
		return listPesoCarregadoTurno;
	}

	public void setListPesoCarregadoTurno(List<PesoCarregadoTurno> listPesoCarregadoTurno) {
		this.listPesoCarregadoTurno = listPesoCarregadoTurno;
	}

	public List<MovPortaria> getListMovPortaria() {
		return listMovPortaria;
	}

	public void setListMovPortaria(List<MovPortaria> listMovPortaria) {
		this.listMovPortaria = listMovPortaria;
	}

	public List<MovPortaria> getListMovPortariaFiltro() {
		return listMovPortariaFiltro;
	}

	public void setListMovPortariaFiltro(List<MovPortaria> listMovPortariaFiltro) {
		this.listMovPortariaFiltro = listMovPortariaFiltro;
	}

	public MovPortaria getMovPortaria() {
		return movPortaria;
	}

	public void setMovPortaria(MovPortaria movPortaria) {
		this.movPortaria = movPortaria;
	}

	public MovPortaria getMovPortariaSelecionado() {
		return movPortariaSelecionado;
	}

	public void setMovPortariaSelecionado(MovPortaria movPortariaSelecionado) {
		this.movPortariaSelecionado = movPortariaSelecionado;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public String getDataInicioString() {
		return dataInicioString;
	}

	public void setDataInicioString(String dataInicioString) {
		this.dataInicioString = dataInicioString;
	}

	public String getDataFimString() {
		return dataFimString;
	}

	public void setDataFimString(String dataFimString) {
		this.dataFimString = dataFimString;
	}

	public Long getIdMovPortaria() {
		return idMovPortaria;
	}

	public void setIdMovPortaria(Long idMovPortaria) {
		this.idMovPortaria = idMovPortaria;
	}

	public Boolean getIsDetalhePortaria() {
		return isDetalhePortaria;
	}

	public void setIsDetalhePortaria(Boolean isDetalhePortaria) {
		this.isDetalhePortaria = isDetalhePortaria;
	}

	public Boolean getIsExcluirMovPortaria() {
		return isExcluirMovPortaria;
	}

	public void setIsExcluirMovPortaria(Boolean isExcluirMovPortaria) {
		this.isExcluirMovPortaria = isExcluirMovPortaria;
	}

	public List<TempoCarregadoTurno> getListTempoCarregamentoTurno() {
		return listTempoCarregamentoTurno;
	}

	public void setListTempoCarregamentoTurno(List<TempoCarregadoTurno> listTempoCarregamentoTurno) {
		this.listTempoCarregamentoTurno = listTempoCarregamentoTurno;
	}

	public RelatorioEnum[] getListRelatorios() {
		return RelatorioEnum.values();
	}

	public void setListRelatorios(RelatorioEnum[] listRelatorios) {
		this.listRelatorios = listRelatorios;
	}

	public RelatorioEnum getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(RelatorioEnum relatorio) {
		this.relatorio = relatorio;
	}

	public StreamedContent getRelatorioStream() {
		setRelatorioStream(emitirRelatorios());
		return relatorioStream;
	}

	public void setRelatorioStream(StreamedContent relatorioStream) {
		this.relatorioStream = relatorioStream;
	}
}