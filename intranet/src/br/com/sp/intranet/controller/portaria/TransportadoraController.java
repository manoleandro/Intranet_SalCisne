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
	 * Método utilizado na página transportadora.xhtml, executado ao carregar a página.
	 */
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) { 
			listarTransportadoras();
		}
	}
	
	/**
	 * Método utilizado para listar as transportadoras da tabela TRANSPORTADORA_PORTARIA.
	 */
	public void listarTransportadoras() {
		try {
			listTransportadora = transportadoraPortariaService.findAll();
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método executado ao pressionar o botão para INCLUIR.
	 */
	public void prepararInclusao() {
		inclusao = true;
		transportadoraPortaria = new TransportadoraPortaria();
		abrirDialog();
	}
	
	/**
	 * Método executado ao pressionar o botão para EDITAR.
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
	 * Método utilizado para persistir a transportadora no banco de dados.
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
	 * Método executado ao pressionar o botão SIM do dialog de remoção do objeto.
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
	 * Método utilizado para verificar se os campos obrigatórios foram preenchidos na tela.
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
	 * Método utilizado para abrir a tela de INCLUSÃO/EDIÇÃO.
	 */
	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialog-transportadora').show();");
	}
	
	/**
	 * Método utilizado para exibir mensagens na tela SUCESSO/ALERTA/ERRO.
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
