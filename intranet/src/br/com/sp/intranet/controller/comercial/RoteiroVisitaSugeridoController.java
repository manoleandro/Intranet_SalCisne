package br.com.sp.intranet.controller.comercial;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.RoteiroVisita;
import br.com.sp.intranet.model.comercial.Vendedor;
import br.com.sp.intranet.service.comercial.RoteiroVisitaService;
import br.com.sp.intranet.service.comercial.VendedorService;
import br.com.sp.intranet.util.MesAnoUtil;

@Controller
@Scope("view")
public class RoteiroVisitaSugeridoController extends GenericController{

	private List<RoteiroVisita> listRoteiroVisitaSugerido;
	
	private CsUsuario usuario;
	
	private List<Vendedor> vendedores;
	private List<Vendedor> vendedoresFiltro;
	private Vendedor vendedorSelecionado;
	
	private String filtro;
	private Date data;
	private MesAnoUtil mesAnoUtil;
	
	private String totalConsumoProgressivo;
	private String totalEstoqueMesAnterior;
	private String totalQuantidadePrevista;
	private String totalPrecoPrevisao;
	private String totalValorPrevisao;
	private String totalValorPrevisaoEspecial;
	private String totalValorTotalPrevisao;
	
	@Autowired
	private RoteiroVisitaService service;
	
	@Autowired
	private VendedorService serviceVendedor;
	
	public void init(){
		usuario = findUsuarioLoggedByUsername();
		mesAnoUtil = new MesAnoUtil(LocalDate.now());
		
		try {
			if(usuario.getZonaVendas() != null){
				vendedorSelecionado = serviceVendedor.findByZonaVendas(usuario, usuario.getZonaVendas());
				this.carregarRoteiroSugerido();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void filtrar(){
		if(filtro != null && !filtro.isEmpty()){
			listRoteiroVisitaSugerido = service.filtrar(listRoteiroVisitaSugerido, filtro);
		}else{
			this.carregarRoteiroSugerido();
		}
	}
	
	public void filtrarByData(){
		if(vendedorSelecionado != null){
			try {
				listRoteiroVisitaSugerido = service.carregarRoteiroVisitaSugeridoByZonaVendas(mesAnoUtil.getMesAno(), vendedorSelecionado.getCodZonaVendas(), usuario);
			} catch (JPAException e) {
				e.printStackTrace();
			}
		}
		listRoteiroVisitaSugerido = service.filtrarByData(listRoteiroVisitaSugerido, data);
	}
	
	/*public void efetivar(){
		try {
			if(listRoteiroVisitaSugerido != null && !listRoteiroVisitaSugerido.isEmpty()){
				if(!service.verificarRoteiroVisitaEfetivoByMes(mesAno, vendedorSelecionado.getCodZonaVendas())){
					service.efetivarRoteiroSugerido(listRoteiroVisitaSugerido);
					createMessage(FacesMessage.SEVERITY_INFO, "efetivar.sucesso");
				}else{
					createMessage(FacesMessage.SEVERITY_ERROR, "roteiro.efetivar.existe");
				}	
			}else{
				createMessage(FacesMessage.SEVERITY_ERROR, "selecionar.vendedor");
			}	
		} catch (JPAException e) {
			createMessage(FacesMessage.SEVERITY_ERROR, "efetivar.erro");
			e.printStackTrace();
		}
	}*/
	
	public void carregarRoteiroSugerido(){
		filtro = null;
		data = null;
		
		if(vendedorSelecionado != null){
			try {
				listRoteiroVisitaSugerido = service.carregarRoteiroVisitaSugeridoByZonaVendas(mesAnoUtil.getMesAno(), vendedorSelecionado.getCodZonaVendas(), usuario);
			} catch (JPAException e) {
				e.printStackTrace();
			}
		}	
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
	
	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogAssistenteVendedores').show(); PF('tableVendedores').clearFilters()");
	}
	
	public List<RoteiroVisita> getListRoteiroVisitaSugerido() {
		return listRoteiroVisitaSugerido;
	}

	public void setListRoteiroVisitaSugerido(List<RoteiroVisita> listRoteiroVisitaSugerido) {
		this.listRoteiroVisitaSugerido = listRoteiroVisitaSugerido;
	}

	public CsUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(CsUsuario usuario) {
		this.usuario = usuario;
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
	
	public String getTotalConsumoProgressivo() {
		Long x = new Long(0);
		if(listRoteiroVisitaSugerido != null && !listRoteiroVisitaSugerido.isEmpty())
			x = listRoteiroVisitaSugerido.stream().mapToLong(RoteiroVisita::getConsumoProgressivo).sum();
		
		totalConsumoProgressivo = new DecimalFormat("#,###").format(x);
		
		return totalConsumoProgressivo;
	}

	public void setTotalConsumoProgressivo(String totalConsumoProgressivo) {
		this.totalConsumoProgressivo = totalConsumoProgressivo;
	}

	public String getTotalEstoqueMesAnterior() {
		Long x = new Long(0);
		if(listRoteiroVisitaSugerido != null && !listRoteiroVisitaSugerido.isEmpty())
			x = listRoteiroVisitaSugerido.stream().mapToLong(RoteiroVisita::getQuantidadeEstoque).sum();
		
		totalEstoqueMesAnterior = new DecimalFormat("#,###").format(x);
		
		return totalEstoqueMesAnterior;
	}

	public void setTotalEstoqueMesAnterior(String totalEstoqueMesAnterior) {
		this.totalEstoqueMesAnterior = totalEstoqueMesAnterior;
	}

	public String getTotalQuantidadePrevista() {
		Long x = new Long(0);
		if(listRoteiroVisitaSugerido != null && !listRoteiroVisitaSugerido.isEmpty())
			x = listRoteiroVisitaSugerido.stream().mapToLong(RoteiroVisita::getQuantidadePrevisao).sum();
		
		totalQuantidadePrevista = new DecimalFormat("#,###").format(x);
		
		return totalQuantidadePrevista;
	}

	public void setTotalQuantidadePrevista(String totalQuantidadePrevista) {
		this.totalQuantidadePrevista = totalQuantidadePrevista;
	}

	public String getTotalPrecoPrevisao() {
		Double x = new Double(0);
		if(listRoteiroVisitaSugerido != null && !listRoteiroVisitaSugerido.isEmpty())
			x = listRoteiroVisitaSugerido.stream().mapToDouble(RoteiroVisita::getPrecoMedio).sum();
		
		totalPrecoPrevisao = new DecimalFormat("#,##0.00").format(x);
		
		return totalPrecoPrevisao;
	}

	public void setTotalPrecoPrevisao(String totalPrecoPrevisao) {
		this.totalPrecoPrevisao = totalPrecoPrevisao;
	}

	public String getTotalValorPrevisao() {
		Double x = new Double(0);
		if(listRoteiroVisitaSugerido != null && !listRoteiroVisitaSugerido.isEmpty())
			x = listRoteiroVisitaSugerido.stream().mapToDouble(RoteiroVisita::getValorPrevisao).sum();
		
		totalValorPrevisao = new DecimalFormat("#,##0.00").format(x);
		
		return totalValorPrevisao;
	}

	public void setTotalValorPrevisao(String totalValorPrevisao) {
		this.totalValorPrevisao = totalValorPrevisao;
	}

	public String getTotalValorPrevisaoEspecial() {
		Double x = new Double(0);
		if(listRoteiroVisitaSugerido != null && !listRoteiroVisitaSugerido.isEmpty())
			x = listRoteiroVisitaSugerido.stream().mapToDouble(RoteiroVisita::getValorPrevisaoEspecial).sum();
		
		totalValorPrevisaoEspecial = new DecimalFormat("#,##0.00").format(x);
		
		return totalValorPrevisaoEspecial;
	}

	public void setTotalValorPrevisaoEspecial(String totalValorPrevisaoEspecial) {
		this.totalValorPrevisaoEspecial = totalValorPrevisaoEspecial;
	}

	public String getTotalValorTotalPrevisao() {
		Double x = new Double(0);
		if(listRoteiroVisitaSugerido != null && !listRoteiroVisitaSugerido.isEmpty())
			x = listRoteiroVisitaSugerido.stream().mapToDouble(RoteiroVisita::getValorPrevisaoTotal).sum();
		
		totalValorTotalPrevisao = new DecimalFormat("#,##0.00").format(x);
		
		return totalValorTotalPrevisao;
	}

	public void setTotalValorTotalPrevisao(String totalValorTotalPrevisao) {
		this.totalValorTotalPrevisao = totalValorTotalPrevisao;
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
}