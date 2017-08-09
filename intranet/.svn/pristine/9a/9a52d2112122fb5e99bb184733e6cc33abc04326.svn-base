package br.com.sp.intranet.controller.caixa;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.TpConta;
import br.com.sp.intranet.service.caixa.TpContaService;



@Controller
@Scope("view")
public class TipoContaCaixaController {

	@Autowired
	private TpContaService tpContaService;


	private List<TpConta> listTpContas = new ArrayList<TpConta>();
	private List<TpConta> listaTPContasFiltro;
	private TpConta tpContaSelecionada;
	private TpConta tpContas = new TpConta();
	private boolean isInclusao = false;
	
	public void init() throws JPAException {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			prepararTipoContas();
		}
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
	
	public void prepararTipoContas() throws JPAException{
		listTpContas = this.tpContaService.findAll();
	}


	public List<TpConta> getListTpContas() {
		return listTpContas;
	}


	public void setListTpContas(List<TpConta> listTpContas) {
		this.listTpContas = listTpContas;
	}


	public TpConta getTpContaSelecionada() {
		return tpContaSelecionada;
	}


	public void setTpContaSelecionada(TpConta tpContaSelecionada) {
		this.tpContaSelecionada = tpContaSelecionada;
	}


	public TpConta getTpContas() {
		return tpContas;
	}


	public void setTpContas(TpConta tpContas) {
		this.tpContas = tpContas;
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

}
