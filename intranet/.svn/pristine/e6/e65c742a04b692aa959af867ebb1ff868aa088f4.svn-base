package br.com.sp.intranet.service.arquivo;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.upload.UploadArquivo;

public interface UploadArquivoService {
	List<Long> findByLikeIdPasta(String propertyName,String propertyName2, String value, String value2) throws JPAException;
	UploadArquivo findById(Long id) throws JPAException;
	
	List<Long> findByLike(String propertyName, String propertyName2, String value, String value2) throws JPAException;
	List<Object[]> findByPropertyObject(String propertyName, Object value) throws JPAException;
	List<UploadArquivo> findByDateIN(String propertyDate, String dataInicio, String dataFim, String propertyName, String where) throws JPAException;
		
	
	List<UploadArquivo> findByProperty(String propertyName, Object value)throws JPAException;
	void salvarArquivo(UploadArquivo uploadArquivo) throws JPAException;
	void alterarArquivo(UploadArquivo uploadArquivo) throws JPAException;
	void excluirArquivo(UploadArquivo uploadArquivo) throws JPAException;
	
	
	

}
