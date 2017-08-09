package br.com.sp.intranet.controller.portaria;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.administrador.LoginController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.model.portaria.MovPortaria;
import br.com.sp.intranet.model.portaria.TransportadoraPortaria;
import br.com.sp.intranet.model.portaria.Veiculo;
import br.com.sp.intranet.service.portaria.HistoricoMovimentacaoPortariaService;
import br.com.sp.intranet.service.portaria.PortariaService;
import br.com.sp.intranet.service.portaria.TransportadoraPortariaService;
import br.com.sp.intranet.service.portaria.VeiculosTransportadoraService;
import br.com.sp.intranet.util.DataTypes;
import br.com.sp.intranet.util.DateUtils;
import br.com.sp.intranet.model.portaria.HistMovPortaria;

@Controller
@Scope("view")
public class PortariaController {

	@Autowired
	private TransportadoraPortariaService transportadoraPortariaService;
	@Autowired
	private VeiculosTransportadoraService veiculosTransportadoraService;
	@Autowired
	private PortariaService portariaService;
	@Autowired
	private LoginController login;
	@Autowired
	private HistoricoMovimentacaoPortariaService historicoMovimentacaoPortariaService;

	private static final String URL_PRINCIPAL = "/pages/portaria/gerenciarPortaria.jsf?faces-redirect=true";
	private static final String URL_DETALHE_PORTARIA = "/pages/portaria/detalhePortaria.jsf?faces-redirect=true";
	private static final String URL_APRESENTACAO_PORTARIA = "/pages/portaria/apresentacao.jsf?faces-redirect=true";

	private MovPortaria movPortaria, movPortariaApresentacao;
	private MovPortaria movPortariaSelecionado;
	private Veiculo tpVeiculo = new Veiculo();
	private TransportadoraPortaria transportadoraPortaria = new TransportadoraPortaria();
	private List<TransportadoraPortaria> listTransportadora = new ArrayList<TransportadoraPortaria>();
	private List<Veiculo> listVeiculo = new ArrayList<Veiculo>();
	private List<MovPortaria> listMovimentacoes, filteredMovimentacoes;
	private int status, tabIndex, tabIndexDetalhe;
	private Long idMovimentacao;
	private boolean inclusao;
	private Boolean isSetorPortaria = new Boolean(false);
	private Boolean isDetalhePortaria = new Boolean(true);
	private Boolean isExcluirMovPortaria = new Boolean(true);
	private Date horaRelogioAtual;

	/**
	 * M�todo utilizado na p�gina transportadora.xhtml, executado ao carregar a
	 * p�gina.
	 */
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			this.movPortariaApresentacao = new MovPortaria();
			verificaPermissoes();
			
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

			if(!(request.getParameter("tab") == null) && request.getParameter("tab") != "") {
					this.tabIndex = Integer.parseInt(request.getParameter("tab"));
					System.out.println("index atual " + tabIndex);
					
					if (tabIndex == 0) {
						setStatus(0);
					} else if (tabIndex == 1) {
						setStatus(1);
						this.tabIndex = 1;
					} else if (tabIndex == 2) {
						setStatus(1);
						this.tabIndex = 2;
					} else if (tabIndex == 3) {
						setStatus(2);
						this.tabIndex = 3;
					} else if (tabIndex == 4) {
						setStatus(3);
					}
					
					RequestContext rc = RequestContext.getCurrentInstance();
					rc.update("form");
					rc.update("form:tab");			
				} else {
					this.status = 0;
					this.tabIndex = 0;
				}
			
			listarMovimentacao();

			try {
				setHoraRelogioAtual(horaRelogioAtual());
			} catch (JPAException e) {
				e.printStackTrace();
			}
		}
	}

	public void initDetalhe() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

			idMovimentacao = Long.parseLong(request.getParameter("id"));
			tabIndexDetalhe = Integer.parseInt(request.getParameter("tab"));
			System.out.println("ESSE � O ID: " + idMovimentacao);
			System.out.println("ESSE � O TAB: " + tabIndexDetalhe);
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

	public Date horaRelogioAtual() throws JPAException {
		horaRelogioAtual = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
		Date hoje = new Date(System.currentTimeMillis());
		DateUtils dateU = new DateUtils();

		return horaRelogioAtual = dateU.obtemDataHora(format.format(hoje));
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

	public void listarMovimentacao() {
		System.out.println("EXECUTADO STATUS " + status);
		try {
			listMovimentacoes = new ArrayList<MovPortaria>();
			listMovimentacoes.clear();
			listMovimentacoes = portariaService.findByStatus(status);
		} catch (JPAException e) {
			e.printStackTrace();
		}

	}

	public void listarTransportadoras() {
		try {
			this.listTransportadora = transportadoraPortariaService.findAll();
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}

	public void listarVeiculos() {
		try {
			this.listVeiculo = veiculosTransportadoraService.findAll();
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}

	public String redirecionaPagina() {		
		return URL_DETALHE_PORTARIA +"&id=" + idMovimentacao + "&tab=" + tabIndex;
	}
	
	public String redirecionaApresentacao() {
		return URL_APRESENTACAO_PORTARIA;
	}
	
	public String atualizaPaginaPrincipal() {
		return URL_PRINCIPAL;
	}
	
	
	public String atualizaPaginaDetalhe() {
		return URL_DETALHE_PORTARIA;
	}

	public String redirecionaPaginaPrincipal() {
		return URL_PRINCIPAL + "&tab="+tabIndexDetalhe;
	}

	public void preparaDetalhes() {

		listarTransportadoras();
		this.movPortaria = new MovPortaria();
		this.movPortaria = this.movPortariaSelecionado;

		if (movPortaria.getTransportadoraPortaria() == null) {
			movPortaria.setTransportadoraPortaria(new TransportadoraPortaria());
			movPortaria.setVeiculo(new Veiculo());
		}
	}

	public void liberarOrdem() throws JPAException {
		if (!this.isPreenchimentoIncompleto(0, movPortariaSelecionado)) {

			movPortaria = new MovPortaria();
			movPortaria = movPortariaSelecionado;
			movPortaria.setStatus(1);
			movPortaria.setNumeroOrdem(movPortariaSelecionado.getNumeroOrdem());
			movPortaria.setHoraOrdemCarrega(retornaDataHoraHoje());

			if(portariaService.save(movPortaria)) {
				try {
					
					createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
					FacesContext.getCurrentInstance().getExternalContext().redirect("gerenciarPortaria.xhtml?tab=0");
					listarMovimentacao();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				createMessage(FacesMessage.SEVERITY_ERROR, "incluir.erro");
			}
			
			
		} else {
			createMessage(FacesMessage.SEVERITY_WARN, "incluir.alerta");
		}
	}

	public Date retornaDataHoraHoje() throws JPAException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
		Date hoje = new Date(System.currentTimeMillis());
		DateUtils dateU = new DateUtils();
		return dateU.obtemDataHora(format.format(hoje));
	}

	public boolean isPreenchimentoIncompleto(int aba, MovPortaria movPortaria) {
		boolean retorno = false;

		switch (aba) {
		case 0:
			if (movPortaria.getNumeroOrdem().trim().isEmpty() || movPortaria.getNumeroOrdem().trim().startsWith("0")
					|| !movPortaria.getNumeroOrdem().trim().matches("[A-Za-z0-9]+"))
				retorno = true;
			System.out.println("caiu aqui");
			break;

		case 2:
			if ((movPortaria.getVeiculoLimpo() == "" || movPortaria.getVeiculoLimpo() == null) || (movPortaria.getVestimentaAdequada() == "" || movPortaria.getVestimentaAdequada() == null))
				retorno = true;
			break;
		case 3:
			if (movPortaria.getHoraInicioCarregamento() == null || movPortaria.getHoraTerminoCarregamento() == null
					|| movPortaria.getContratacao().trim().isEmpty() || movPortaria.getTipoCarga().trim().isEmpty()
					|| movPortaria.getPeso() == null || movPortaria.getPeso() == 0 || movPortaria.getPeso2() == null
					|| movPortaria.getPeso2() == 0)
				retorno = true;
			break;
		case 4:
			System.out.println("movPortaria.getNumeroNf() " + movPortaria.getNumeroNf());
			System.out.println("movPortaria.getNumeroOrdem() " + movPortaria.getNumeroOrdem());
			if (movPortaria.getNumeroNf() == null || movPortaria.getNumeroNf() == Long.parseLong("0")
					|| !movPortaria.getNumeroOrdem().trim().matches("[A-Za-z0-9]+"))
				retorno = true;
			break;
		default:
			break;
		}

		return retorno;
	}

	public void salvar() throws JPAException {
		if (isPreenchimentoIncompleto(this.tabIndex, movPortariaSelecionado)) {
			createMessage(FacesMessage.SEVERITY_WARN, "incluir.alerta");
		} else {
			if(this.portariaService.update(this.movPortariaSelecionado)) {
				createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			} else {
				createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
			}
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("gerenciarPortaria.xhtml?tab="+tabIndex);
			listarMovimentacao();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void alterar() throws JPAException {

		List<HistMovPortaria> listHistMovPortaria = new ArrayList<HistMovPortaria>();
		listHistMovPortaria = this.retornaRegistrosAlterados(movPortariaSelecionado, login.getUsuario().getUsername());

		if(this.portariaService.update(this.movPortariaSelecionado)) {
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
		}
		
		this.listarMovimentacao();
		createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");

		for (HistMovPortaria histMovPortaria : listHistMovPortaria) {
			historicoMovimentacaoPortariaService.save(histMovPortaria);
		}

		this.redirecionaPaginaPrincipal();
	}

	public List<HistMovPortaria> retornaRegistrosAlterados(MovPortaria movPortariaSelecionado, String usuario) throws JPAException {
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
			histMovPortaria.setPara(
					dateU.obtemData(movPortariaSelecionado.getHoraInicioCarregamento(), "dd/MM/yyyy HH:mm:ss"));
			histMovPortaria.setColuna("HORARIO DE TERMINO DE CARREGAMENTO");
			histMovPortaria.setJustificativa(movPortariaSelecionado.getJustificativa());

			listHistMovPortaria.add(histMovPortaria);
		}

		if (movPortariaDB.getHoraSaida() != null && movPortariaSelecionado.getHoraSaida() != null
				&& !dateU.compararDatasEmString(movPortariaDB.getHoraSaida(), movPortariaSelecionado.getHoraSaida(),
						"dd/MM/yyyy HH:mm:ss")) {
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

	public void salvarApresentacao() throws JPAException {
		if (verificaCamposPreenchidosApresentacao()) {
			createMessage(FacesMessage.SEVERITY_WARN, "incluir.alerta");
		} else {
			tpVeiculo = new Veiculo();
			tpVeiculo = veiculosTransportadoraService.findById(movPortariaApresentacao.getVeiculo());
			transportadoraPortaria = new TransportadoraPortaria();
			transportadoraPortaria = transportadoraPortariaService.findById(movPortariaApresentacao.getTransportadoraPortaria());
			movPortariaApresentacao.setTransportadoraPortaria(transportadoraPortaria);
			movPortariaApresentacao.setVeiculo(tpVeiculo);

			if(this.portariaService.save(movPortariaApresentacao)) {
				createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			} else {
				createMessage(FacesMessage.SEVERITY_ERROR, "incluir.erro");
			}
			
			listarMovimentacao();
			movPortariaApresentacao = new MovPortaria();

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
			Date hoje = new Date(System.currentTimeMillis());
			DateUtils dateU = new DateUtils();
			movPortariaApresentacao = new MovPortaria();
			movPortariaApresentacao.setData(dateU.obtemDataHora(format.format(hoje)));
			movPortariaApresentacao.setTransportadoraPortaria(new TransportadoraPortaria());
			movPortariaApresentacao.setVeiculo(new Veiculo());
			this.status = 0;
			listarTransportadoras();
			listarVeiculos();
		}

	}

	public void remover() throws JPAException {
		if(portariaService.delete(this.movPortariaSelecionado)) {
			createMessage(FacesMessage.SEVERITY_INFO, "excluir.sucesso");
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "excluir.erro");
		}
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("portaria.xhtml?tab="+tabIndex);
			listarMovimentacao();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public void autorizarEntrada() throws JPAException {

		if (!this.isPreenchimentoIncompleto(2, movPortariaSelecionado)) {
			movPortaria = new MovPortaria();
			movPortaria = movPortariaSelecionado;
			movPortaria.setStatus(2);
			movPortaria.setHoraEntrada(this.retornaDataHoraHoje());
			movPortaria.setTempoEspera(this.retornaDiferencaHora(movPortaria.getHoraEntrada(),
			movPortaria.getData()));
			
			if(this.portariaService.save(movPortaria)) {
				createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			} else {
				createMessage(FacesMessage.SEVERITY_ERROR, "incluir.erro");
			}
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("portaria.xhtml?tab=2");
				listarMovimentacao();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			createMessage(FacesMessage.SEVERITY_WARN, "incluir.alerta");
		}

	}

	public boolean verificaCamposPreenchidosApresentacao() {
		boolean camposVazios = false;

		if (this.movPortariaApresentacao.getPlaca() == "" || this.movPortariaApresentacao.getPlaca() == null
				|| this.movPortariaApresentacao.getPlaca().length() == 0) {
			camposVazios = true;
		}

		if (this.movPortariaApresentacao.getNomeMotorista() == ""
				|| this.movPortariaApresentacao.getNomeMotorista() == null
				|| this.movPortariaApresentacao.getNomeMotorista().length() == 0) {
			camposVazios = true;
		}

		if (this.movPortariaApresentacao.getRgMotorista() == "" || this.movPortariaApresentacao.getRgMotorista() == null
				|| this.movPortariaApresentacao.getRgMotorista().length() == 0) {
			camposVazios = true;
		}
		
		if (this.movPortariaApresentacao.getTransportadoraPortaria().getIdTransportadora() == null) {
			camposVazios = true;
		}
		
		if (this.movPortariaApresentacao.getVeiculo().getIdVeiculo() == null) {
			camposVazios = true;
		}

		return camposVazios;

	}

	public void createMessage(Severity severity, String messageKeyPropertie) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
		flash.setKeepMessages(true);
		FacesMessage msg = new FacesMessage(severity, ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString(messageKeyPropertie), null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void initApresentacao() {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
		Date hoje = new Date(System.currentTimeMillis());
		DateUtils dateU = new DateUtils();

		movPortariaApresentacao = new MovPortaria();
		movPortariaApresentacao.setData(dateU.obtemDataHora(format.format(hoje)));
		movPortariaApresentacao.setTransportadoraPortaria(new TransportadoraPortaria());
		movPortariaApresentacao.setVeiculo(new Veiculo());

		listarTransportadoras();
		listarVeiculos();
		
	}

	public void onTabChange(TabChangeEvent event) {
		TabView tabView = (TabView) event.getComponent();
		tabIndex = tabView.getChildren().indexOf(event.getTab());

		if (event.getTab().getId().equals("ordem")) {
			setStatus(0);
			this.tabIndex = 0;
		} else if (event.getTab().getId().equals("espera")) {
			setStatus(1);
			this.tabIndex = 1;
		} else if (event.getTab().getId().equals("entrada")) {
			setStatus(1);
			this.tabIndex = 2;
		} else if (event.getTab().getId().equals("carregamento")) {
			setStatus(2);
			this.tabIndex = 3;
		} else if (event.getTab().getId().equals("saida")) {
			setStatus(3);
			this.tabIndex = 4;
		}
		listarMovimentacao();

	}

	public static String retornaDiferencaHora(Date data1, Date data2) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", new Locale("pt", "BR"));

		Calendar dataUm = Calendar.getInstance();
		dataUm.setTime(data1);

		Calendar dataDois = Calendar.getInstance();
		dataDois.setTime(data2);

		long diferenca = dataUm.getTimeInMillis() - dataDois.getTimeInMillis();
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		Integer qtdDias = (int) (diferenca / 86400000);
		String horas = dateFormat.format(diferenca);

		if (qtdDias.intValue() > 0) {
			qtdDias = qtdDias.intValue() * 24;
			String s1[] = null;
			for (int i = 0; i <= horas.length(); i++) {
				s1 = horas.split(":");
			}
			Integer horasDia = new Integer(s1[0]);
			Integer resultado = new Integer("0");
			resultado = horasDia + qtdDias;
			horas = resultado.toString() + ":" + s1[1] + ":" + s1[2];
		}
		return horas;
	}

	@SuppressWarnings("static-access")
	public void liberarNotaFiscal() throws JPAException {
		System.out.println("movPortariaSelecionado.getNumeroOrdem() 111111" + movPortariaSelecionado.getNumeroOrdem());
		if (!this.isPreenchimentoIncompleto(4, movPortariaSelecionado)) {
			movPortaria = new MovPortaria();
			
			movPortaria = movPortariaSelecionado;
			movPortaria.setNumeroNf(movPortariaSelecionado.getNumeroNf());
			movPortaria.setStatus(4);
			movPortaria.setHoraSaida(this.retornaDataHoraHoje());
			movPortaria.setTempoLonagem(this.retornaDiferencaHora(movPortaria.getHoraSaida(), movPortaria.getHoraTerminoCarregamento()));
			movPortaria.setTempoPermanencia(this.retornaDiferencaHora(movPortaria.getHoraSaida(), movPortaria.getHoraEntrada()));
			
			if(this.portariaService.save(movPortaria)) {
				createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			} else {
				createMessage(FacesMessage.SEVERITY_ERROR, "incluir.erro");
			}
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("portaria.xhtml?tab=4");
				listarMovimentacao();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			createMessage(FacesMessage.SEVERITY_WARN, "incluir.alerta");
		}
	}

	@SuppressWarnings("static-access")
	public void liberarCarregamento() throws JPAException {
		if (!this.isPreenchimentoIncompleto(3, movPortariaSelecionado)) {
			if (movPortariaSelecionado.getHoraInicioCarregamento().compareTo(this.retornaDataHoraHoje()) == -1) {
				movPortaria = new MovPortaria();

				movPortaria = movPortariaSelecionado;
				movPortaria.setStatus(3);
				movPortaria.setTempoCarregamento(this.retornaDiferencaHora(movPortaria.getHoraTerminoCarregamento(),
						movPortaria.getHoraInicioCarregamento()));
				// if (
				if(this.portariaService.save(movPortaria)) {
					createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
				} else {
					createMessage(FacesMessage.SEVERITY_ERROR, "incluir.erro");
				}
				// )) {
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect("portaria.xhtml?tab=3");
					listarMovimentacao();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "O hor�rio de inicio do carregamento deve ser menor do que o t�rmino!", "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} else {
			createMessage(FacesMessage.SEVERITY_WARN, "incluir.alerta");
		}
	}

	/**
	 * M�todo utilizado para abrir a tela de INCLUS�O/EDI��O.
	 */
	public void abrirDialogApresentacao() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialog-apresentacao').show();");
	}

	public void abrirDialogDetalhes() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialog-detalhes-espera-ord-carreg').show();");
	}

	public MovPortaria getMovPortaria() {
		return movPortaria;
	}

	public void setMovPortaria(MovPortaria movPortaria) {
		this.movPortaria = movPortaria;
	}

	public List<TransportadoraPortaria> getListTransportadora() {
		return listTransportadora;
	}

	public void setListTransportadora(List<TransportadoraPortaria> listTransportadora) {
		this.listTransportadora = listTransportadora;
	}

	public List<Veiculo> getListVeiculo() {
		return listVeiculo;
	}

	public void setListVeiculo(List<Veiculo> listVeiculo) {
		this.listVeiculo = listVeiculo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public TransportadoraPortariaService getTransportadoraPortariaService() {
		return transportadoraPortariaService;
	}

	public void setTransportadoraPortariaService(TransportadoraPortariaService transportadoraPortariaService) {
		this.transportadoraPortariaService = transportadoraPortariaService;
	}

	public VeiculosTransportadoraService getVeiculosTransportadoraService() {
		return veiculosTransportadoraService;
	}

	public void setVeiculosTransportadoraService(VeiculosTransportadoraService veiculosTransportadoraService) {
		this.veiculosTransportadoraService = veiculosTransportadoraService;
	}

	public PortariaService getPortariaService() {
		return portariaService;
	}

	public void setPortariaService(PortariaService portariaService) {
		this.portariaService = portariaService;
	}

	public List<MovPortaria> getListMovimentacoes() {
		return listMovimentacoes;
	}

	public void setListMovimentacoes(List<MovPortaria> listMovimentacoes) {
		this.listMovimentacoes = listMovimentacoes;
	}

	public List<MovPortaria> getFilteredMovimentacoes() {
		return filteredMovimentacoes;
	}

	public void setFilteredMovimentacoes(List<MovPortaria> filteredMovimentacoes) {
		this.filteredMovimentacoes = filteredMovimentacoes;
	}

	public MovPortaria getMovPortariaApresentacao() {
		return movPortariaApresentacao;
	}

	public void setMovPortariaApresentacao(MovPortaria movPortariaApresentacao) {
		this.movPortariaApresentacao = movPortariaApresentacao;
	}

	public Veiculo getTpVeiculo() {
		return tpVeiculo;
	}

	public void setTpVeiculo(Veiculo tpVeiculo) {
		this.tpVeiculo = tpVeiculo;
	}

	public TransportadoraPortaria getTransportadoraPortaria() {
		return transportadoraPortaria;
	}

	public void setTransportadoraPortaria(TransportadoraPortaria transportadoraPortaria) {
		this.transportadoraPortaria = transportadoraPortaria;
	}

	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public MovPortaria getMovPortariaSelecionado() {
		return movPortariaSelecionado;
	}

	public void setMovPortariaSelecionado(MovPortaria movPortariaSelecionado) {
		this.movPortariaSelecionado = movPortariaSelecionado;
	}

	public Boolean getIsSetorPortaria() {
		return isSetorPortaria;
	}

	public void setIsSetorPortaria(Boolean isSetorPortaria) {
		this.isSetorPortaria = isSetorPortaria;
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

	public Date getHoraRelogioAtual() {
		return horaRelogioAtual;
	}

	public void setHoraRelogioAtual(Date horaRelogioAtual) {
		this.horaRelogioAtual = horaRelogioAtual;
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}
}