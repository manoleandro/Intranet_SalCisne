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
import br.com.sp.intranet.service.caixa.BancosService;



@Controller
@Scope("view")
public class BancoCaixaController {

	@Autowired
	private BancosService bancosService;
	
	private List<Bancos> listBancos = new ArrayList<Bancos>();
	private List<Bancos> listaBancosFiltro;
	private Bancos bancoSelecionado;
	private boolean isInclusao = false;
	private Bancos bancos = new Bancos();

	public void init() throws JPAException {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			prepararBancos();
		}
	}
	
	
	public void prepararBancos() throws JPAException{
		listBancos = this.bancosService.findAll();
	}
	
	public void prepararInclusaoBanco() throws JPAException{
		isInclusao = true;
		bancos = new Bancos();
		setBancos(bancos);
	}
	
	public void prepararAlteracaoBanco() throws JPAException{
		isInclusao = false;
		setBancos(bancoSelecionado);
	}
	
	
	public void salvarBanco() throws JPAException{
		if(isInclusao){
			this.bancosService.salvar(bancoSelecionado);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O Banco foi incluído com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			this.bancosService.alterar(bancoSelecionado);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O Banco foi alterado com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		prepararBancos();
	}
	
	public void excluirBanco() throws JPAException{
		if(this.bancosService.excluir(bancoSelecionado)){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O Banco foi excluído com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nãoo foi possível excluir o registro! pois o mesmo esta vinculado a outras tabelas" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		listBancos = this.bancosService.findAll();
	}


	public List<Bancos> getListBancos() {
		return listBancos;
	}


	public void setListBancos(List<Bancos> listBancos) {
		this.listBancos = listBancos;
	}


	public List<Bancos> getListaBancosFiltro() {
		return listaBancosFiltro;
	}


	public void setListaBancosFiltro(List<Bancos> listaBancosFiltro) {
		this.listaBancosFiltro = listaBancosFiltro;
	}


	public Bancos getBancoSelecionado() {
		return bancoSelecionado;
	}


	public void setBancoSelecionado(Bancos bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}


	public boolean isInclusao() {
		return isInclusao;
	}


	public void setInclusao(boolean isInclusao) {
		this.isInclusao = isInclusao;
	}


	public Bancos getBancos() {
		return bancos;
	}


	public void setBancos(Bancos bancos) {
		this.bancos = bancos;
	}
	
	

}
