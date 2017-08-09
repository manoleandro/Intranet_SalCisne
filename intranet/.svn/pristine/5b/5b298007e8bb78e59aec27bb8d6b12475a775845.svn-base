package br.com.sp.intranet.controller.comercial;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.DespesaVisita;
import br.com.sp.intranet.model.comercial.Vendedor;
import br.com.sp.intranet.service.comercial.DespesaVisitaService;
import br.com.sp.intranet.service.comercial.VendedorService;
import br.com.sp.intranet.util.MesAnoUtil;

@Controller
@Scope("view")
public class DespesaVisitaController extends GenericController{
	private static final String URL_DESPESA_VISITA_ALTERACAO ="despesaVisitaAlteracao.xhtml?";
	private static final String URL_DESPESA_VISITA ="/pages/comercial/despesaVisita.xhtml?faces-redirect=true";
	
	private List<Vendedor> vendedores;
	private List<Vendedor> vendedoresFiltro;
	private Vendedor vendedorSelecionado;
	
	private List<DespesaVisita> despesas;
	private List<DespesaVisita> despesasFiltro;
	private DespesaVisita despesaSelecionada;
	private DespesaVisita despesa;
	
	private CsUsuario usuario;
	
	private String filtro;
	private Date data;
	private MesAnoUtil mesAnoUtil;
	
	private String totalKm;
	private String totalDiarias;
	private String totalRefeicoes;
	private String totalOutrosGastos;
	private String totalDespesa;
	
	@Autowired
	private DespesaVisitaService service;
	
	@Autowired
	private VendedorService serviceVendedor;
		
	public void init(){
		mesAnoUtil = new MesAnoUtil(LocalDate.now());
		usuario = findUsuarioLoggedByUsername();
		
		try {
			if(usuario.getZonaVendas() != null){
				vendedorSelecionado = serviceVendedor.findByZonaVendas(usuario, usuario.getZonaVendas());
				this.carregarDespesas();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initAlteracao(){
		usuario = findUsuarioLoggedByUsername();
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		
		if(request.getParameter("idDespesa") != null){
			try {
				despesa = service.findById(usuario, Long.parseLong(request.getParameter("idDespesa"))) ;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JPAException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void filtrar(){
		if(filtro != null && !filtro.isEmpty()){
			despesas = service.filtrar(despesas, filtro);
		}else{
			this.carregarDespesas();
		}
	}
	
	public void filtrarByData(){
		try {
			if(vendedorSelecionado != null){
				despesas = service.findDespesaVisitaByMes(usuario, mesAnoUtil.getMesAno(), vendedorSelecionado.getCodZonaVendas());
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		despesas = service.filtrarByData(despesas, data);
	}
	
	public String salvar(){
		try {
			service.salvar(usuario, despesaSelecionada);
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			despesaSelecionada = null;
			despesa = null;
			return this.voltar();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
			return null;
		}
	}
	
	public void prepararDespesa(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(URL_DESPESA_VISITA_ALTERACAO + "idDespesa="  + despesaSelecionada.getId().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String voltar(){
		return URL_DESPESA_VISITA;
	}
	
	public void prepararVendedores(){
		try {
			vendedorSelecionado = null;
			vendedores = serviceVendedor.carregarVendedores(usuario);
			abrirDialog();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void carregarDespesas(){
		filtro = null;
		data = null;
		despesaSelecionada = null;
		
		try {
			if(vendedorSelecionado != null){
				despesas = service.findDespesaVisitaByMes(usuario, mesAnoUtil.getMesAno(), vendedorSelecionado.getCodZonaVendas());
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogAssistenteVendedores').show(); PF('tableVendedores').clearFilters()");
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public Vendedor getVendedorSelecionado() {
		return vendedorSelecionado;
	}

	public void setVendedorSelecionado(Vendedor vendedorSelecionado) {
		this.vendedorSelecionado = vendedorSelecionado;
	}

	public List<DespesaVisita> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<DespesaVisita> despesas) {
		this.despesas = despesas;
	}

	public DespesaVisita getDespesaSelecionada() {
		return despesaSelecionada;
	}

	public void setDespesaSelecionada(DespesaVisita despesaSelecionada) {
		this.despesaSelecionada = despesaSelecionada;
	}

	public CsUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(CsUsuario usuario) {
		this.usuario = usuario;
	}

	public DespesaVisita getDespesa() {
		return despesa;
	}

	public void setDespesa(DespesaVisita despesa) {
		this.despesa = despesa;
	}

	public String getTotalKm() {
		Long x = new Long(0);
		if(despesas != null && !despesas.isEmpty()){
			x = despesas.stream()
					.filter(d -> d.getQuantidadeKm() != null)
					.mapToLong(DespesaVisita::getQuantidadeKm).sum();
		}
		totalKm = new DecimalFormat("#,###").format(x);
		return totalKm;
	}

	public void setTotalKm(String totalKm) {
		this.totalKm = totalKm;
	}

	public String getTotalDiarias() {
		Long x = new Long(0);
		if(despesas != null && !despesas.isEmpty()){
			x = despesas.stream()
					.filter(d -> d.getQuantidadeDiarias() != null)
					.mapToLong(DespesaVisita::getQuantidadeDiarias).sum();
		}
		totalDiarias = new DecimalFormat("#,###").format(x);
		
		return totalDiarias;
	}

	public void setTotalDiarias(String totalDiarias) {
		this.totalDiarias = totalDiarias;
	}

	public String getTotalRefeicoes() {
		Long x = new Long(0);
		if(despesas != null && !despesas.isEmpty()){
			x = despesas.stream()
					.filter(d -> d.getQuantidadeRefeicoes() != null)
					.mapToLong(DespesaVisita::getQuantidadeRefeicoes).sum();
		}
		totalRefeicoes = new DecimalFormat("#,###").format(x);
		return totalRefeicoes;
	}

	public void setTotalRefeicoes(String totalRefeicoes) {
		this.totalRefeicoes = totalRefeicoes;
	}

	public String getTotalOutrosGastos() {
		Double x = new Double(0);
		if(despesas != null && !despesas.isEmpty()){
			x = despesas.stream()
					.filter(d -> d.getOutrosGastos() != null)
					.mapToDouble(DespesaVisita::getOutrosGastos).sum();
		}	
		
		totalOutrosGastos = new DecimalFormat("#,##0.00").format(x);
		return totalOutrosGastos;
	}

	public void setTotalOutrosGastos(String totalOutrosGastos) {
		this.totalOutrosGastos = totalOutrosGastos;
	}

	public String getTotalDespesa() {
		Double x = new Double(0);
		if(despesas != null && !despesas.isEmpty()){
			x = despesas.stream()
					.filter(d -> d.getTotalDespesa() != null)
					.mapToDouble(DespesaVisita::getTotalDespesa).sum();
		}
		totalDespesa = new DecimalFormat("#,##0.00").format(x);
		return totalDespesa;
	}

	public void setTotalDespesa(String totalDespesa) {
		this.totalDespesa = totalDespesa;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Vendedor> getVendedoresFiltro() {
		return vendedoresFiltro;
	}

	public void setVendedoresFiltro(List<Vendedor> vendedoresFiltro) {
		this.vendedoresFiltro = vendedoresFiltro;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public MesAnoUtil getMesAnoUtil() {
		return mesAnoUtil;
	}

	public void setMesAnoUtil(MesAnoUtil mesAnoUtil) {
		this.mesAnoUtil = mesAnoUtil;
	}

	public List<DespesaVisita> getDespesasFiltro() {
		return despesasFiltro;
	}

	public void setDespesasFiltro(List<DespesaVisita> despesasFiltro) {
		this.despesasFiltro = despesasFiltro;
	}
}