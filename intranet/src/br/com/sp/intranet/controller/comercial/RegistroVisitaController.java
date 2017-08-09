package br.com.sp.intranet.controller.comercial;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.Cliente;
import br.com.sp.intranet.model.comercial.Vendedor;
import br.com.sp.intranet.model.comercial.VendedorClienteByMunicipio;
import br.com.sp.intranet.service.comercial.ClienteService;
import br.com.sp.intranet.service.comercial.RegistroVisitaService;
import br.com.sp.intranet.service.comercial.VendedorService;
import br.com.sp.intranet.util.DateUtils;
import br.com.sp.intranet.util.LocalDateUtils;
import br.com.sp.intranet.util.MesAnoUtil;

@Controller
@Scope("view")
public class RegistroVisitaController extends GenericController {
	
	private static final long serialVersionUID = 1L;
	
	private static final String URL_DETALHE_REGISTRO_VISITA_CLIENTE = "detalheRegistroVisita.xhtml?";

	@Autowired
	private RegistroVisitaService service;
	
	@Autowired
	private VendedorService serviceVendedor;
	
	@Autowired
	private ClienteService serviceCliente;
	
	private VendedorClienteByMunicipio vendedor;
	
	private List<Cliente> clientes;
	private List<Cliente> clientesFiltro;
	private Cliente clienteSelecionado;

	private List<Vendedor> vendedores;
	private List<Vendedor> vendedoresFiltro;
	private Vendedor vendedorSelecionado;
	
	private CsUsuario usuario;
	
	private String filtro;
	private Long zonaVendas;
	
	private String mesAno;
	private Date data;
	
	private MesAnoUtil mesAnoUtil;
	
	public void init() {
		usuario = findUsuarioLoggedByUsername();
		//Carregar vendedor do usuario
		if (!FacesContext.getCurrentInstance().isPostback()) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			
			if(request.getParameter("zonaVendas") != null){
				zonaVendas = Long.parseLong(request.getParameter("zonaVendas"));	
			}else{
				zonaVendas = usuario.getZonaVendas();
			}
			
			if(request.getParameter("mesAno") != null){
				mesAno = request.getParameter("mesAno");
				mesAnoUtil = new MesAnoUtil(LocalDateUtils.obterDataPrimeiroDia(mesAno));
			}else{
				mesAnoUtil = new MesAnoUtil(LocalDate.now());
				mesAno = mesAnoUtil.getMesAno();
			}
			
			if(zonaVendas != null) {
				try {
					vendedor = service.carregarVendedorClienteByMunicipio(mesAno, zonaVendas, usuario, data);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				vendedorSelecionado = new Vendedor(vendedor);
			}
		}	
	}
	
	public void filtrar(){
		try {
			if((filtro == null || filtro.isEmpty())){
				this.carregarRegistroVisita();
			}else{
				vendedor = service.filtarCliente(filtro, vendedor);
			}	
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void filtrarByData(){
		try {
			vendedor = service.carregarVendedorClienteByMunicipio(mesAnoUtil.getMesAno(), vendedorSelecionado.getCodZonaVendas(), usuario, data);
			vendedor = service.filtrarByData(vendedor, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prepararDetalhe(){
		 
		if(clienteSelecionado != null){
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(URL_DETALHE_REGISTRO_VISITA_CLIENTE + "&idCliente=" + clienteSelecionado.getIdCliente() + 
						"&mesAno=" + mesAnoUtil.getMesAno() + "&dia=" + DateUtils.obtemData(clienteSelecionado.getDataVisita(), "dd/MM/yyyy"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void prepararClientes(){
		clienteSelecionado = null;
		
		if((clientes == null || clientes.isEmpty()) && vendedorSelecionado != null){
			clientes = serviceCliente.carregarClientesByZonaVendas(vendedorSelecionado.getCodZonaVendas(), usuario);
		}
		abrirDialogClientes();
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
	
	public void carregarRegistroVisita() {
		filtro = null;
		data = null;
		
		if(vendedorSelecionado != null && mesAno != null){
			try {
				vendedor = service.carregarVendedorClienteByMunicipio(mesAnoUtil.getMesAno(), vendedorSelecionado.getCodZonaVendas(), usuario, data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogAssistenteVendedores').show(); PF('tableVendedores').clearFilters()");
	}
	
	public void abrirDialogClientes() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogAssistenteClientes').show(); PF('tableCliente').clearFilters()");
	}
	
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public VendedorClienteByMunicipio getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorClienteByMunicipio vendedor) {
		this.vendedor = vendedor;
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

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Long getZonaVendas() {
		return zonaVendas;
	}

	public void setZonaVendas(Long zonaVendas) {
		this.zonaVendas = zonaVendas;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Vendedor> getVendedoresFiltro() {
		return vendedoresFiltro;
	}

	public void setVendedoresFiltro(List<Vendedor> vendedoresFiltro) {
		this.vendedoresFiltro = vendedoresFiltro;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getClientesFiltro() {
		return clientesFiltro;
	}

	public void setClientesFiltro(List<Cliente> clientesFiltro) {
		this.clientesFiltro = clientesFiltro;
	}

	public MesAnoUtil getMesAnoUtil() {
		return mesAnoUtil;
	}

	public void setMesAnoUtil(MesAnoUtil mesAnoUtil) {
		this.mesAnoUtil = mesAnoUtil;
	}
}