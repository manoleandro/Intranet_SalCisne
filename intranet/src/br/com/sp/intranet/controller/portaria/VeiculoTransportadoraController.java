package br.com.sp.intranet.controller.portaria;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.Veiculo;
import br.com.sp.intranet.service.portaria.VeiculosTransportadoraService;

@Controller
@Scope("view")
public class VeiculoTransportadoraController {
	
	@Autowired
	private VeiculosTransportadoraService transportadoraPortariaService;
	private Veiculo veiculoPortaria, veiculoPortariaSelecionada;
	private List<Veiculo> listVeiculo = new ArrayList<Veiculo>();
	private boolean inclusao;
	
	/**
	 * M�todo utilizado na p�gina transportadora.xhtml, executado ao carregar a p�gina.
	 */
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) { 
			listarVeiculos();
		}
	}
	
	/**
	 * M�todo utilizado para listar as transportadoras da tabela VEICULO.
	 */
	public void listarVeiculos() {
		try {
			listVeiculo = transportadoraPortariaService.findAll();
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * M�todo executado ao pressionar o bot�o para INCLUIR.
	 */
	public void prepararInclusao() {
		inclusao = true;
		veiculoPortaria = new Veiculo();
		abrirDialog();
	}
	
	/**
	 * M�todo executado ao pressionar o bot�o para EDITAR.
	 */
	public void prepararAlteracao() {
		inclusao = false;
		this.veiculoPortaria = this.veiculoPortariaSelecionada;
		if(veiculoPortaria != null) {
			abrirDialog();
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}
	
	/**
	 * M�todo utilizado para persistir a transportadora no banco de dados.
	 * @throws JPAException
	 */
	public void salvar() throws JPAException {
		if (verificaCamposPreenchidos()) {
			createMessage(FacesMessage.SEVERITY_WARN, "incluir.alerta");
		} else {

			if (inclusao) {
				transportadoraPortariaService.save(veiculoPortaria);
				createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			} else {
				transportadoraPortariaService.update(veiculoPortaria);
				createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			}

			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dialog-transportadora').hide();");
		}
		listarVeiculos();
	}
	
	/**
	 * M�todo executado ao pressionar o bot�o SIM do dialog de remo��o do objeto.
	 */
	public void remover() {
		if (veiculoPortariaSelecionada != null) {
			try {
				transportadoraPortariaService.delete(veiculoPortariaSelecionada);
				createMessage(FacesMessage.SEVERITY_INFO, "excluir.sucesso");
				listarVeiculos();
				veiculoPortariaSelecionada = new Veiculo();
				RequestContext rc = RequestContext.getCurrentInstance();
				rc.execute("location.reload();");
			} catch (JPAException e) {
				createMessage(FacesMessage.SEVERITY_ERROR, "excluir.erro");
				e.printStackTrace();
			}
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}
	
	/**
	 * M�todo utilizado para verificar se os campos obrigat�rios foram preenchidos na tela.
	 * @return
	 */
	public boolean verificaCamposPreenchidos() {
		boolean camposVazios = false;
		if (this.veiculoPortaria.getDescricao() == null || this.veiculoPortaria.getDescricao().length() == 0) {
			camposVazios = true;
		}
		return camposVazios;
	}
	
	/**
	 * M�todo utilizado para abrir a tela de INCLUS�O/EDI��O.
	 */
	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialog-transportadora').show();");
	}
	
	/**
	 * M�todo utilizado para exibir mensagens na tela SUCESSO/ALERTA/ERRO.
	 * @param severity
	 * @param messageKeyPropertie
	 */
	public void createMessage(Severity severity, String messageKeyPropertie) {
		FacesMessage msg = new FacesMessage(severity, ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString(messageKeyPropertie), null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public VeiculosTransportadoraService getTransportadoraPortariaService() {
		return transportadoraPortariaService;
	}

	public void setTransportadoraPortariaService(VeiculosTransportadoraService transportadoraPortariaService) {
		this.transportadoraPortariaService = transportadoraPortariaService;
	}

	public Veiculo getVeiculoPortaria() {
		return veiculoPortaria;
	}

	public void setVeiculoPortaria(Veiculo veiculoPortaria) {
		this.veiculoPortaria = veiculoPortaria;
	}

	public Veiculo getVeiculoPortariaSelecionada() {
		return veiculoPortariaSelecionada;
	}

	public void setVeiculoPortariaSelecionada(Veiculo veiculoPortariaSelecionada) {
		this.veiculoPortariaSelecionada = veiculoPortariaSelecionada;
	}

	public List<Veiculo> getListVeiculo() {
		return listVeiculo;
	}

	public void setListVeiculo(List<Veiculo> listVeiculo) {
		this.listVeiculo = listVeiculo;
	}

	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}

}
