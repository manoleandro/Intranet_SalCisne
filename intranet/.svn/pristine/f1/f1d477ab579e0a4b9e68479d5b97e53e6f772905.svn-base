package br.com.sp.intranet.controller.arquivos;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.upload.UploadPasta;
import br.com.sp.intranet.service.arquivo.UploadPastaService;


public class PastaModel extends ListDataModel<UploadPasta> implements SelectableDataModel<UploadPasta> {
	
	@Autowired
	private UploadPastaService uploadPastaService;
	
	
	public PastaModel() {  
    }  
  
    public PastaModel(List<UploadPasta> data) {  
        super(data);  
    }

	@Override
	public UploadPasta getRowData(String key) {
		try {
			return this.uploadPastaService.findById(Long.valueOf(key));
		} catch (JPAException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object getRowKey(UploadPasta object) {
		return null;
	}

}
