package br.com.sp.intranet.controller.caixa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Bancos;
import br.com.sp.intranet.model.caixa.Contas;
import br.com.sp.intranet.model.caixa.TpConta;
import br.com.sp.intranet.service.caixa.BancosService;
import br.com.sp.intranet.service.caixa.ContasService;
import br.com.sp.intranet.service.caixa.TpContaService;



@Controller
@Scope("view")
public class ContaCorrenteCaixaController {
	
	@Autowired
	private ContasService contasService;
	
	@Autowired
	private BancosService bancosService;
	
	@Autowired
	private TpContaService tpContaService;

	private Contas contaSelecionada;
	private ContasModel listaContas;
	private Contas contas = new Contas();

	private List<Bancos> listBancos = new ArrayList<Bancos>();
	private List<Contas> listContas = new ArrayList<Contas>();
	private List<TpConta> listTpContas = new ArrayList<TpConta>();
	private List<Contas> listaContasFiltro;

	
	private boolean isInclusao = false;
	private boolean isCompSaldo;
	
	public void init() throws JPAException {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			this.prepararContas();
		}
	}
	
	
	public void prepararContas() throws JPAException {
		listContas = this.contasService.findAll();
		for(Iterator<Contas> it = listContas.iterator(); it.hasNext();){
			Contas contaIt = it.next();
			contaIt.getBanco().getId();
			contaIt.getBanco().getDescricao();
			contaIt.getTpConta().getId();
			contaIt.getTpConta().getDescricao();
		}
		ContasModel lista = new ContasModel(listContas);
		setListaContas(lista);
		setListaContasFiltro(null);
		setContaSelecionada(null);
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
	
	public void prepararComboBancosTpContas() throws JPAException{
		if(listBancos == null || listBancos.isEmpty()){
			listBancos = this.bancosService.findAll();
		}
		if(listTpContas == null || listTpContas.isEmpty()){
			listTpContas = this.tpContaService.findAll();
		}	
	}
	
	
	public void prepararInclusaoContas() throws JPAException{
		isInclusao = true;
		isCompSaldo = false;
		prepararComboBancosTpContas();
		contas = newContas();
	}
	
	public Contas newContas() {
		contas = new Contas();
		contas.setTpConta(new TpConta());
		contas.setBanco(new Bancos());
		setCompSaldo(false);
		return contas;
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
	

	public Contas getContaSelecionada() {
		return contaSelecionada;
	}


	public void setContaSelecionada(Contas contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}


	public List<Contas> getListContas() {
		return listContas;
	}


	public void setListContas(List<Contas> listContas) {
		this.listContas = listContas;
	}


	public List<Contas> getListaContasFiltro() {
		return listaContasFiltro;
	}


	public void setListaContasFiltro(List<Contas> listaContasFiltro) {
		this.listaContasFiltro = listaContasFiltro;
	}


	public ContasModel getListaContas() {
		return listaContas;
	}


	public void setListaContas(ContasModel listaContas) {
		this.listaContas = listaContas;
	}


	public List<TpConta> getListTpContas() {
		return listTpContas;
	}


	public void setListTpContas(List<TpConta> listTpContas) {
		this.listTpContas = listTpContas;
	}


	public Contas getContas() {
		return contas;
	}


	public void setContas(Contas contas) {
		this.contas = contas;
	}


	public List<Bancos> getListBancos() {
		return listBancos;
	}


	public void setListBancos(List<Bancos> listBancos) {
		this.listBancos = listBancos;
	}


	public boolean isInclusao() {
		return isInclusao;
	}


	public void setInclusao(boolean isInclusao) {
		this.isInclusao = isInclusao;
	}


	public boolean isCompSaldo() {
		return isCompSaldo;
	}


	public void setCompSaldo(boolean isCompSaldo) {
		this.isCompSaldo = isCompSaldo;
	}



	
	
}
