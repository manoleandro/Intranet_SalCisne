package br.com.sp.intranet.controller.administrador;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.tabview.TabView;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.controller.bean.colaborador.ColaboradorModel;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.vo.rh.Colaborador;
import br.com.sp.intranet.model.administrador.vo.rh.Ferias;
import br.com.sp.intranet.model.administrador.vo.rh.HistoricoAfastamento;
import br.com.sp.intranet.model.administrador.vo.rh.HistoricoSalario;
import br.com.sp.intranet.service.administrador.colaborador.ColaboradorService;
import br.com.sp.intranet.util.ConstantUtils;
import br.com.sp.intranet.util.DataTypes;
import br.com.sp.intranet.model.xml.Rss;


@Controller
@Scope("view")
public class ColaboradorController extends GenericController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ColaboradorService colaboradorService;
	
	@Autowired
	private LoginController login;
	
	private List<HistoricoSalario> listaHistSal;
	private List<HistoricoAfastamento> listaHistAfas;
	private List<Ferias> listaFerias;
	private List<Colaborador> listaColaborador;
	private Colaborador colaborador;
	private ColaboradorModel listColaboradorModel;
	private List<Colaborador> filteredColaboradores = new ArrayList<Colaborador>();
	private int rowCount;
	private DataTable table;
	private Boolean exibeSalario = false;
	private String ufRel;
	
	private SelectItem[] filial   = {new SelectItem("", "Selecione"),new SelectItem("240", "SP"),new SelectItem("140", "CF"), new SelectItem("141", "Horista - CF")};
	private SelectItem[] filiais  = {new SelectItem("", "Selecione"),new SelectItem("240", "SP"),new SelectItem("140", "CF"), new SelectItem("141", "Horista - CF")};
	private SelectItem[] filialSp = {new SelectItem("", "Selecione"),new SelectItem("240", "SP")};
	private SelectItem[] filialCf = {new SelectItem("", "Selecione"),new SelectItem("140", "CF"), new SelectItem("141", "Horista - CF")};
	private SelectItem[] ativos   = {new SelectItem("", "Todos"), new SelectItem("A", "Ativos"), new SelectItem("D", "Demitidos")};
	
	private LazyDataModel<Colaborador> lazyModel;
	
	public void init() throws JPAException {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			this.listaColaborador = this.colaboradorService.findAll();
			lazyModel = new ColaboradorModel(this.listaColaborador);
		}
	}
	
	public void initHistorico() throws JPAException {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			this.visualizar();
		}
	}
	
	
	
	
	private TabView tabView;
	
	public void setFilial(SelectItem[] filial) {  
        this.filial = filial;  
    }
	
	public SelectItem[] getFilial() {  
        return filial;  
    }
	
	public SelectItem[] getFiliais() {  
        return filiais;  
    }
	
	public SelectItem[] getFilialSp() {  
        return filialSp;  
    }
	
	public SelectItem[] getFilialCf() {  
        return filialCf;  
    } 
	
	public String redirecionaHistoricoColaborador(Long id) {
		System.out.println("redirecionaHistoricoColaborador..." + id);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("historicoColaborador.xhtml?id=" + id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public String redirecionaPaginaPrincipal() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("gerenciarColaborador.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String responsavel() {
    	try {
			JAXBContext context = JAXBContext.newInstance(ConstantUtils.PACOTE_XML);

			String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/avisos.xml");
			File arquivo = new File(caminho);
			if(arquivo.exists()){ 
				@SuppressWarnings("unchecked")
				JAXBElement<Rss> element = (JAXBElement<Rss>) context.createUnmarshaller().unmarshal(arquivo);
				return element.getValue().getChannel().getCategory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 

	public int getRowCount(Map<String, String> params, int inicia, int maxPorPagina) throws JPAException {
		return this.colaboradorService.countTotal(params, inicia, maxPorPagina);
	}
	
	public List<HistoricoSalario> findSalario(Long idColaborador, Boolean valido) throws JPAException {
		if (valido) {
			List<HistoricoSalario> lista = colaboradorService.findSalario(idColaborador);
			List<HistoricoSalario> listaNova = null;
			if (lista != null && !lista.isEmpty()) {
				listaNova = new ArrayList<HistoricoSalario>();
				for (HistoricoSalario hs : lista) {
					HistoricoSalario histSal = new HistoricoSalario();
					histSal.setIdHistSalario(hs.getIdHistSalario());
					histSal.setColaborador(hs.getColaborador());
					histSal.setDataAumento(hs.getDataAumento());
					histSal.setMotivo(hs.getMotivo());
					if(hs.getSalario() != null)
						histSal.setSalario(new String(Base64.decodeBase64(Base64.decodeBase64(hs.getSalario().getBytes()))));
					histSal.setPercentual(hs.getPercentual());
					listaNova.add(histSal);
				}
			}
			return listaNova;
		} else {
			return colaboradorService.findSalario(idColaborador);
		}
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
    
	
	public DataTable getTable() {
		return table;
	}

	public void setTable(DataTable table) {
		this.table = table;
	}
	

	public void visualizar() throws JPAException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

		Long idColaborador = Long.parseLong(request.getParameter("id"));
        //colaborador = (Colaborador)(table.getRowData());
		colaborador = this.colaboradorService.findById(idColaborador);
        listaHistSal = new ArrayList<HistoricoSalario>();
    	listaHistAfas = new ArrayList<HistoricoAfastamento>();
    	listaFerias = new ArrayList<Ferias>();
    	exibeSalario = allowed(login.getUsuario().getNome());
        
    	setListaHistSal(this.findSalario(idColaborador, exibeSalario));
        setListaHistAfas(this.colaboradorService.findAfastamento(idColaborador));
        
        System.out.println("ID::: " + idColaborador);
        setListaFerias(this.colaboradorService.findFerias(idColaborador));
        //colaborador.setFoto(retornaFoto(colaborador.getId()));
        tabView.setActiveIndex(0);
	}
	
	public List<Ferias> findFerias(Long idColaborador) throws JPAException {
		return colaboradorService.findFerias(idColaborador);
	}
	
	public StreamedContent retornaFoto(Long id){
		StreamedContent returnFoto = new DefaultStreamedContent(DataTypes.retornaFotoInputStream(id));
		return returnFoto;
	}

	public boolean allowed(String logado){
		String[] usuarios = this.responsavel().split(";");
		for (int i = 0; i < usuarios.length; i++) {
			String[] infoUsuario = usuarios[i].split(",");
			if(infoUsuario[0].equals(logado)){
				return true;
			}
		}
		return false;
	}
	
	public String allowedUf(String logado){
		String[] usuarios = this.responsavel().split(";");
		for (int i = 0; i < usuarios.length; i++) {
			String[] infoUsuario = usuarios[i].split(",");
			if(infoUsuario[0].equals(logado)){
				if(infoUsuario.length == 1){
					filial = this.filiais;
				}else{
					if(infoUsuario[1].equals("SP")){
						filial = this.filialSp;
						return "SP";
					}else{
						filial = this.filialCf;
						return "CF";
					}
				}
			}
		}
		return null;
	}

	public List<HistoricoSalario> getListaHistSal() {
		return listaHistSal;
	}

	public void setListaHistSal(List<HistoricoSalario> listaHistSal) {
		this.listaHistSal = listaHistSal;
	}

	public List<HistoricoAfastamento> getListaHistAfas() {
		return listaHistAfas;
	}

	public void setListaHistAfas(List<HistoricoAfastamento> listaHistAfas) {
		this.listaHistAfas = listaHistAfas;
	}

	public List<Ferias> getListaFerias() {
		return listaFerias;
	}

	public void setListaFerias(List<Ferias> listaFerias) {
		this.listaFerias = listaFerias;
	}

	public Boolean getExibeSalario() {
		return exibeSalario;
	}

	public void setExibeSalario(Boolean exibeSalario) {
		this.exibeSalario = exibeSalario;
	}

	public String getUfRel() {
		return ufRel;
	}

	public void setUfRel(String ufRel) {
		this.ufRel = ufRel;
	}

	public TabView getTabView() {
		return tabView;
	}

	public void setTabView(TabView tabView) {
		this.tabView = tabView;
	}

	public SelectItem[] getAtivos() {
		return ativos;
	}

	public void setAtivos(SelectItem[] ativos) {
		this.ativos = ativos;
	}


	public ColaboradorModel getListColaboradorModel() {
		return listColaboradorModel;
	}


	public void setListColaboradorModel(ColaboradorModel listColaboradorModel) {
		this.listColaboradorModel = listColaboradorModel;
	}


	public List<Colaborador> getListaColaborador() {
		return listaColaborador;
	}


	public void setListaColaborador(List<Colaborador> listaColaborador) {
		this.listaColaborador = listaColaborador;
	}


	public List<Colaborador> getFilteredColaboradores() {
		return filteredColaboradores;
	}


	public void setFilteredColaboradores(List<Colaborador> filteredColaboradores) {
		this.filteredColaboradores = filteredColaboradores;
	}


	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}


	public LazyDataModel<Colaborador> getLazyModel() {
		return lazyModel;
	}


	public void setLazyModel(LazyDataModel<Colaborador> lazyModel) {
		this.lazyModel = lazyModel;
	}

}