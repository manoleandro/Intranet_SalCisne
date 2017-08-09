package br.com.sp.intranet.controller.portaria;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.HistMovPortaria;
import br.com.sp.intranet.service.portaria.HistoricoMovimentacaoPortariaService;

@Controller
@Scope("view")
public class HistoricoPortariaController {
	
	@Autowired
	private HistoricoMovimentacaoPortariaService historicoMovimentacaoPortariaService;
	private HistMovPortaria histMovPortaria, histMovPortariaSelecionado;
	private List<HistMovPortaria> listHistMovPortaria = new ArrayList<HistMovPortaria>();
	private List<HistMovPortaria> listHistoricoFiltro;
	private boolean inclusao;
	private Date dataInicio, dataFim;
	
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			this.dataInicio = new Date(System.currentTimeMillis());
			this.dataFim = new Date(System.currentTimeMillis());
			listarHistoricoInicio();
		}
	}
	
	public void listarHistoricoInicio() {
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataHoje = hoje.format(formatador);
		try {
			listHistMovPortaria = historicoMovimentacaoPortariaService.findByData(dataHoje, dataHoje);
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	public void listarHistorico() {
		SimpleDateFormat formatterString = new SimpleDateFormat("dd/MM/yyyy");
		try {
			listHistMovPortaria = historicoMovimentacaoPortariaService.findByData(formatterString.format(dataInicio), formatterString.format(dataFim));
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}

	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}



	public Date getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}


	public Date getDataFim() {
		return dataFim;
	}


	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}


	public HistMovPortaria getHistMovPortaria() {
		return histMovPortaria;
	}


	public void setHistMovPortaria(HistMovPortaria histMovPortaria) {
		this.histMovPortaria = histMovPortaria;
	}


	


	public HistMovPortaria getHistMovPortariaSelecionado() {
		return histMovPortariaSelecionado;
	}


	public void setHistMovPortariaSelecionado(HistMovPortaria histMovPortariaSelecionado) {
		this.histMovPortariaSelecionado = histMovPortariaSelecionado;
	}


	public List<HistMovPortaria> getListHistMovPortaria() {
		return listHistMovPortaria;
	}


	public void setListHistMovPortaria(List<HistMovPortaria> listHistMovPortaria) {
		this.listHistMovPortaria = listHistMovPortaria;
	}


	public List<HistMovPortaria> getListHistoricoFiltro() {
		return listHistoricoFiltro;
	}


	public void setListHistoricoFiltro(List<HistMovPortaria> listHistoricoFiltro) {
		this.listHistoricoFiltro = listHistoricoFiltro;
	}

	
}
