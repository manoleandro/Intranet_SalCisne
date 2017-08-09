package br.com.sp.intranet.controller.arquivos;


import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.upload.UploadArquivo;
import br.com.sp.intranet.service.arquivo.UploadArquivoService;



public class ArquivoModel extends ListDataModel<UploadArquivo> implements SelectableDataModel<UploadArquivo> {

	@Autowired
	private UploadArquivoService uploadArquivoService;
	
	public ArquivoModel() {  
    }  
  
    public ArquivoModel(List<UploadArquivo> data) {  
        super(data);  
    }

	@Override
	public UploadArquivo getRowData(String key){
		
		List<UploadArquivo> arquivos = (List<UploadArquivo>) getWrappedData();  
		  
		/*for(UploadArquivo uploadArquivo : arquivos) {  
		    if(uploadArquivo.getIdArquivo().equals(key))  
		        return uploadArquivo;  
		} 
		return null;*/
		UploadArquivo arquivo = new UploadArquivo();
		try {
			System.out.println("KEY: " + key);
			arquivo = this.uploadArquivoService.findById(Long.valueOf(key));
			System.out.println("KEY: " + key);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JPAException e) {
			e.printStackTrace();
		}

		return arquivo;
		}

	@Override
	public Object getRowKey(UploadArquivo arquivo) {
		return arquivo.getPrimaryKey();
	}	

}
