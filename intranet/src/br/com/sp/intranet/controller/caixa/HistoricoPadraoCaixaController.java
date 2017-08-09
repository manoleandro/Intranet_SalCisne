package br.com.sp.intranet.controller.caixa;



import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Bancos;
import br.com.sp.intranet.model.caixa.HistoricoPadrao;
import br.com.sp.intranet.service.caixa.BancosService;
import br.com.sp.intranet.service.caixa.HistoricoPadraoService;



@Controller
@Scope("view")
public class HistoricoPadraoCaixaController {

	@Autowired
	private HistoricoPadraoService historicoPadraoService;
	
	private HistoricoPadrao histPadrao = new HistoricoPadrao();
	private boolean isInclusao = false;
	private HistoricoPadrao histPadraoSelecionado;
	private List<HistoricoPadrao> listHistPadrao = new ArrayList<HistoricoPadrao>();
	private HistPadraoModel listaHistPadrao;
	private List<HistoricoPadrao> listaHistPadraoFiltro;
	
	public void init() throws JPAException {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			prepararHistoricoPadrao();
		}
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

	public HistoricoPadraoService getHistoricoPadraoService() {
		return historicoPadraoService;
	}


	public void setHistoricoPadraoService(HistoricoPadraoService historicoPadraoService) {
		this.historicoPadraoService = historicoPadraoService;
	}


	public HistoricoPadrao getHistPadrao() {
		return histPadrao;
	}


	public void setHistPadrao(HistoricoPadrao histPadrao) {
		this.histPadrao = histPadrao;
	}


	public boolean isInclusao() {
		return isInclusao;
	}


	public void setInclusao(boolean isInclusao) {
		this.isInclusao = isInclusao;
	}


	public HistoricoPadrao getHistPadraoSelecionado() {
		return histPadraoSelecionado;
	}


	public void setHistPadraoSelecionado(HistoricoPadrao histPadraoSelecionado) {
		this.histPadraoSelecionado = histPadraoSelecionado;
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


	public HistPadraoModel getListaHistPadrao() {
		return listaHistPadrao;
	}


	public void setListaHistPadrao(HistPadraoModel listaHistPadrao) {
		this.listaHistPadrao = listaHistPadrao;
	}

}
