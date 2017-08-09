package br.com.sp.intranet.controller.caixa;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Contas;
import br.com.sp.intranet.service.caixa.ContasService;


public class ContasModel extends ListDataModel<Contas> implements SelectableDataModel<Contas>{

	@Autowired
	private ContasService contasService;
	
	public ContasModel() {  
    }  
  
    public ContasModel(List<Contas> data) {  
        super(data);  
    }

	@Override
	public Contas getRowData(String key) {
		
		try {
			
			return contasService.findById(Long.valueOf(key));
		
		} catch (JPAException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object getRowKey(Contas contas) {
		return contas.getPrimaryKey();
	}

}
