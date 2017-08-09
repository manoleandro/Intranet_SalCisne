package br.com.sp.intranet.controller.caixa;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Lancamentos;
import br.com.sp.intranet.service.caixa.LancamentoService;

public class LancamentosModel extends ListDataModel<Lancamentos> implements SelectableDataModel<Lancamentos>{

	@Autowired
	private LancamentoService lancamentoService;
	
	public LancamentosModel() {  
    }  
  
    public LancamentosModel(List<Lancamentos> data) {  
        super(data);  
    }

	@Override
	public Lancamentos getRowData(String key) {
		
		try {
			
			return lancamentoService.findById(Long.valueOf(key));
		
		} catch (JPAException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Object getRowKey(Lancamentos lancamentos) {
		return lancamentos.getPrimaryKey();
	}

}