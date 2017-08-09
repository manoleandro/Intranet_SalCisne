package br.com.sp.intranet.service.arquivo;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.upload.UploadPasta;

public interface UploadPastaService {
	List<UploadPasta> findByColumnIsNull(String propertyName, String valueIsNull, String tableName) throws JPAException;
	
	//List<UploadPasta> getHierarquiaPastaSetor(CsUsuario usuario);
	List<UploadPasta> listaTodos() throws JPAException;
	List<UploadPasta> findByProperty(String propertyName, Object value) throws JPAException;
	List<UploadPasta> findPastaBySuperior(Long pastaSuperior) throws JPAException;
	
    UploadPasta findById(Long id) throws JPAException;
    
	void incluirPasta(UploadPasta uploadPasta) throws JPAException;
	void excluirPasta(UploadPasta uploadPasta) throws JPAException;
	void alterarPasta(UploadPasta uploadPasta) throws JPAException;

}
