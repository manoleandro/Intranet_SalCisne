package br.com.sp.intranet.controller.caixa;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.HistoricoPadrao;
import br.com.sp.intranet.service.caixa.HistoricoPadraoService;

public class HistPadraoModel extends ListDataModel<HistoricoPadrao> implements SelectableDataModel<HistoricoPadrao>{
	
	@Autowired
	private HistoricoPadraoService historicoPadraoService;
	
	public HistPadraoModel(){
		
	}
	
	 public HistPadraoModel(List<HistoricoPadrao> data) {  
	        super(data);  
	    }
	
	@Override
	public HistoricoPadrao getRowData(String key) {
		try {
			
			return historicoPadraoService.findById(Long.valueOf(key));
		
		} catch (JPAException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object getRowKey(HistoricoPadrao hist) {
		return hist.getPrimaryKey();
	}
}




