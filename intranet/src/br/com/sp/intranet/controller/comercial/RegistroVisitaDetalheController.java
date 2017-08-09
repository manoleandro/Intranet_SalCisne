package br.com.sp.intranet.controller.comercial;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.RegistroVisita;
import br.com.sp.intranet.service.comercial.RegistroVisitaService;
import br.com.sp.intranet.util.DataTypes;
import br.com.sp.intranet.util.DateUtils;
import br.com.sp.intranet.util.LocalDateUtils;

@Controller
@Scope("view")
public class RegistroVisitaDetalheController extends GenericController {

	private static final long serialVersionUID = 1L;

	private static final String URL_REGISTRO_VISITA_CLIENTE = "/pages/comercial/registroVisita.xhtml?faces-redirect=true";

	@Autowired
	private RegistroVisitaService service;

	private RegistroVisita vo;

	private CsUsuario usuario;
	
	private String mesAno;
	
	private Date dia;
	
	public void init() {
		usuario = findUsuarioLoggedByUsername();

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

		String idCliente = request.getParameter("idCliente");
		mesAno = request.getParameter("mesAno");
		dia = DateUtils.obtemData(request.getParameter("dia"), "dd/MM/yyyy");
		
		if (idCliente != null && !idCliente.isEmpty()) {
			try {
				vo = new RegistroVisita();
				vo = service.carregarDetalheRegistroVisita(idCliente, mesAno, usuario, dia);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void salvar() {
		try {
			if(this.verificaEstoqueZero()){
				if(vo.isAlteracao()){
					service.alterar(vo, usuario);
				}else{
					service.incluir(vo, usuario);
				}
				vo = service.carregarDetalheRegistroVisita(vo.getPk().getIdCliente().toString(), mesAno, usuario, vo.getPk().getDataVisitaReal());
				createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "incluir.erro");
		}
	}

	public void calcularPrecoCompra() {
		if (vo.getVendaCompraRs() != null && vo.getVendaCompraKg() != null && vo.getVendaCompraRs() != 0 && vo.getVendaCompraKg() != 0) {
			vo.setVendaPrecoCompra(vo.getVendaCompraRs() / vo.getVendaCompraKg());
		}
	}
	
	public void calcularPrecoCompraEspecial() {
		if (vo.getCompraRsEspecial() != null && vo.getCompraKgEspecial() != null && vo.getCompraRsEspecial() != 0 && vo.getCompraKgEspecial() != 0) {
			vo.setPrecoCompraEspecial(vo.getCompraRsEspecial() / vo.getCompraKgEspecial());
		}
	}
	
	public boolean verificaEstoqueZero(){
		if (vo.getEstoqueDiaVisita() != null && vo.getEstoqueDiaVisita() != 0) {
			return true;
		}else{
			if(vo.isPermiteEstoqueZero()){
				return true;
			}else{
				createMessage(FacesMessage.SEVERITY_ERROR, "validacao.estoque.zero");
				return false;
			}	
		}
	}

	public void calcularConsumoKgDia() {
		if(verificaEstoqueZero()){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			Long consumoKgDia = new Long(0); 
		
			if(vo.getPk().getDataVisitaReal() != null){
				LocalDate dataVisitaReal = LocalDateUtils.convertToLocalDate(vo.getPk().getDataVisitaReal());
			
				consumoKgDia = (DataTypes.parseNull(vo.getEstoqueMesFindo())
						- DataTypes.parseNull(vo.getEstoqueDiaVisita())) / dataVisitaReal.getDayOfMonth();
			}
			vo.setVendasConsumoKgDia(consumoKgDia);

			calcularDataProximaVisita(formatter);
			calcularVariacaoConsumo();
			calcularVariacaoDiasEstoque();
		}
	}

	public void calcularDataProximaVisita(DateTimeFormatter formatter) {

		Long dias = new Long(0); 
		
		if(vo.getVendasConsumoKgDia() != null && vo.getVendasConsumoKgDia() != 0){
			dias = (DataTypes.parseNull(vo.getEstoqueDiaVisita()) + DataTypes.parseNull(vo.getVendaCompraKg())) / vo.getVendasConsumoKgDia()
					- vo.getDiasReposicao();
		}	
		
		if(vo.getPk().getDataVisitaReal() != null){
			LocalDate dataProximaVisita = LocalDateUtils.convertToLocalDate(vo.getPk().getDataVisitaReal());

			vo.setDataProximaVisita(LocalDateUtils.convertToDate(dataProximaVisita.plusDays(dias)));
		}	
	}

	public void calcularVariacaoDiasEstoque() {
		/*
		 * Long variacao = ((vo.getVendaCompraKg() + vo.getEstoqueDiaVisita()) /
		 * vo.getVendasConsumoKgDia()) - vo.getDiasCobertura() + 30;
		 * vo.setVariacaoDiasEmEstoque(variacao);
		 */

		Long valor1 = new Long(0); 
		
		if(vo.getVendasConsumoKgDia() != null && vo.getVendasConsumoKgDia() != 0){
			valor1 = (DataTypes.parseNull(vo.getEstoqueDiaVisita()) + DataTypes.parseNull(vo.getVendaCompraKg())) / vo.getVendasConsumoKgDia();
		}	
		
		Long valor2 = new Long(0); 
		
		if(vo.getConsumoKgDia() != null && vo.getConsumoKgDia() != 0){
			valor2 = vo.getEstoqueMesFindo() / vo.getConsumoKgDia();
		}	

		vo.setVariacaoDiasEmEstoque(valor1 - valor2);
	}

	public void calcularVariacaoConsumo() {
		/*
		 * Double variacaoConsumo = (vo.getVendasConsumoKgDia().doubleValue() -
		 * vo.getConsumoKgDia()) / vo.getConsumoKgDia();
		 * 
		 * vo.setVariacaoConsumo(Math.round(variacaoConsumo * 100));
		 */

		vo.setVariacaoConsumo(DataTypes.parseNull(vo.getVendasConsumoKgDia()) - DataTypes.parseNull(vo.getConsumoKgDia()));
	}

	public String voltar() {
		return URL_REGISTRO_VISITA_CLIENTE + "&zonaVendas=" + vo.getZona() + "&mesAno=" + mesAno;
	}

	public RegistroVisita getVo() {
		return vo;
	}

	public void setVo(RegistroVisita vo) {
		this.vo = vo;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}
}