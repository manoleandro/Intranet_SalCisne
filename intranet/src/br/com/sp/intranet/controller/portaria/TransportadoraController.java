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
import br.com.sp.intranet.model.portaria.MovPortaria;
import br.com.sp.intranet.model.portaria.TransportadoraPortaria;
import br.com.sp.intranet.service.portaria.TransportadoraPortariaService;

@Controller
@Scope("view")
public class TransportadoraController {
	
	@Autowired
	private TransportadoraPortariaService transportadoraPortariaService;
	private TransportadoraPortaria transportadoraPortaria, transportadoraPortariaSelecionada;
	private MovPortaria movPortaria;
	private List<TransportadoraPortaria> listTransportadora = new ArrayList<TransportadoraPortaria>();
	private boolean inclusao;
	
	/**
	 * M�todo utilizado na p�gina transportadora.xhtml, executado ao carregar a p�gina.
	 */
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) { 
			listarTransportadoras();
		}
	}
	
	/**
	 * M�todo utilizado para listar as transportadoras da tabela TRANSPORTADORA_PORTARIA.
	 */
	public void listarTransportadoras() {
		try {
			listTransportadora = transportadoraPortariaService.findAll();
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * M�todo executado ao pressionar o bot�o para INCLUIR.
	 */
	public void prepararInclusao() {
		inclusao = true;
		transportadoraPortaria = new TransportadoraPortaria();
		abrirDialog();
	}
	
	/**
	 * M�todo executado ao pressionar o bot�o para EDITAR.
	 */
	public void prepararAlteracao() {
		inclusao = false;
		this.transportadoraPortaria = this.transportadoraPortariaSelecionada;
		if(transportadoraPortaria != null) {
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
				transportadoraPortariaService.save(transportadoraPortaria);
				createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			} else {
				transportadoraPortariaService.update(transportadoraPortaria);
				createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			}

			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dialog-transportadora').hide();");
		}
		listarTransportadoras();
	}
	
	/**
	 * M�todo executado ao pressionar o bot�o SIM do dialog de remo��o do objeto.
	 */
	public void remover() {
		if (transportadoraPortariaSelecionada != null) {
			try {
				transportadoraPortariaService.delete(transportadoraPortariaSelecionada);
				createMessage(FacesMessage.SEVERITY_INFO, "excluir.sucesso");
				listarTransportadoras();
				transportadoraPortariaSelecionada = new TransportadoraPortaria();
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
		if (this.transportadoraPortaria.getDescricao() == null || this.transportadoraPortaria.getDescricao().length() == 0) {
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

	
	public MovPortaria getMovPortaria() {
		return movPortaria;
	}

	public void setMovPortaria(MovPortaria movPortaria) {
		this.movPortaria = movPortaria;
	}

	public List<TransportadoraPortaria> getListTransportadora() {
		return listTransportadora;
	}

	public void setListTransportadora(List<TransportadoraPortaria> listTransportadora) {
		this.listTransportadora = listTransportadora;
	}

	public TransportadoraPortaria getTransportadoraPortariaSelecionada() {
		return transportadoraPortariaSelecionada;
	}

	public void setTransportadoraPortariaSelecionada(TransportadoraPortaria transportadoraPortariaSelecionada) {
		this.transportadoraPortariaSelecionada = transportadoraPortariaSelecionada;
	}

	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}

	public TransportadoraPortaria getTransportadoraPortaria() {
		return transportadoraPortaria;
	}

	public void setTransportadoraPortaria(TransportadoraPortaria transportadoraPortaria) {
		this.transportadoraPortaria = transportadoraPortaria;
	}
}
