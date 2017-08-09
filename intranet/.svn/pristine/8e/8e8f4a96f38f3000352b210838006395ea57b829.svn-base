package br.com.sp.intranet.controller.comercial;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.RegistroVisita;
import br.com.sp.intranet.model.comercial.Vendedor;
import br.com.sp.intranet.service.comercial.RelatorioVisitaService;
import br.com.sp.intranet.service.comercial.VendedorService;
import br.com.sp.intranet.util.MesAnoUtil;

@SuppressWarnings("serial")
@Controller
@Scope("view")
public class RelatorioVisitaController extends GenericController{
	private List<Vendedor> vendedores;
	private List<Vendedor> vendedoresFiltro;
	private Vendedor vendedorSelecionado;
	
	private List<RegistroVisita> registrosVisita;
	
	private CsUsuario usuario;
	
	private String filtro;
	private Date data;
	private MesAnoUtil mesAnoUtil;
	
	private String totalQuantidadePrevisao;
	private String totalQuantidadeReal;
	private String totalQuantidadeEspecial;
	private String totalValorTotal;
	
	@Autowired
	private RelatorioVisitaService service;
	
	@Autowired
	private VendedorService serviceVendedor;
	
	public void init() {
		mesAnoUtil = new MesAnoUtil(LocalDate.now());
		usuario = findUsuarioLoggedByUsername();
		try {
			if(usuario.getZonaVendas() != null){
				vendedorSelecionado = serviceVendedor.findByZonaVendas(usuario, usuario.getZonaVendas());
				this.carregarRelatorioVisita();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void filtrar(){
		if(filtro != null && !filtro.isEmpty()){
			registrosVisita = service.filtrar(registrosVisita, filtro);
		}else{
			this.carregarRelatorioVisita();
		}
	}
	
	public void filtrarByData(){
		try {
			if(vendedorSelecionado != null){
				registrosVisita = service.carregarRelatorioVisita(vendedorSelecionado.getCodZonaVendas(), mesAnoUtil.getMesAno(), usuario);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		registrosVisita = service.filtrarByData(registrosVisita, data);
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
	
	public void carregarRelatorioVisita(){
		filtro = null;
		data = null;
		
		try {
			if(vendedorSelecionado != null){
				registrosVisita = service.carregarRelatorioVisita(vendedorSelecionado.getCodZonaVendas(), mesAnoUtil.getMesAno(), usuario);
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

	public List<RegistroVisita> getRegistrosVisita() {
		if(registrosVisita != null && !registrosVisita.isEmpty()){
			Collections.sort(registrosVisita);
		}	
		return registrosVisita;
	}

	public void setRegistrosVisita(List<RegistroVisita> registrosVisita) {
		this.registrosVisita = registrosVisita;
	}

	public String getTotalQuantidadePrevisao() {
		Long x = new Long(0);
		if(registrosVisita != null && !registrosVisita.isEmpty()){
			x = registrosVisita.stream()
					.filter(r -> r.getPrevisaoCompraKg() != null)
					.mapToLong(RegistroVisita::getPrevisaoCompraKg).sum();
		}
		totalQuantidadePrevisao = new DecimalFormat("#,###").format(x);
		
		return totalQuantidadePrevisao;
	}

	public void setTotalQuantidadePrevisao(String totalQuantidadePrevisao) {
		this.totalQuantidadePrevisao = totalQuantidadePrevisao;
	}

	public String getTotalQuantidadeReal() {
		
		Long x = new Long(0);
		if(registrosVisita != null && !registrosVisita.isEmpty()){
			x = registrosVisita.stream()
					.filter(r -> r.getVendaCompraKg() != null)
					.mapToLong(RegistroVisita::getVendaCompraKg).sum();
		}
		totalQuantidadeReal = new DecimalFormat("#,###").format(x);
		
		return totalQuantidadeReal;
	}

	public void setTotalQuantidadeReal(String totalQuantidadeReal) {
		this.totalQuantidadeReal = totalQuantidadeReal;
	}

	public String getTotalQuantidadeEspecial() {
		
		Double x = new Double(0);
		if(registrosVisita != null && !registrosVisita.isEmpty()){
			x = registrosVisita.stream()
					.filter(r -> r.getCompraRsEspecial() != null)
					.mapToDouble(RegistroVisita::getCompraRsEspecial).sum();
		}
		totalQuantidadeEspecial = new DecimalFormat("#,##0.00").format(x);
		
		return totalQuantidadeEspecial;
	}

	public void setTotalQuantidadeEspecial(String totalQuantidadeEspecial) {
		this.totalQuantidadeEspecial = totalQuantidadeEspecial;
	}

	public String getTotalValorTotal() {
		Double x = new Double(0);
		if(registrosVisita != null && !registrosVisita.isEmpty()){
			x = registrosVisita.stream()
					.filter(r -> r.getValorTotalPedido() != null)
					.mapToDouble(RegistroVisita::getValorTotalPedido).sum();
		}
		totalValorTotal = new DecimalFormat("#,##0.00").format(x);
		
		return totalValorTotal;
	}

	public void setTotalValorTotal(String totalValorTotal) {
		this.totalValorTotal = totalValorTotal;
	}

	public List<Vendedor> getVendedoresFiltro() {
		return vendedoresFiltro;
	}

	public void setVendedoresFiltro(List<Vendedor> vendedoresFiltro) {
		this.vendedoresFiltro = vendedoresFiltro;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
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
}