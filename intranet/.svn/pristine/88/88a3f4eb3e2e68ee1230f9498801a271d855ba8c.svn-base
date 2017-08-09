package br.com.sp.intranet.service.arquivo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.arquivo.UploadArquivoDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.upload.UploadArquivo;
import br.com.sp.intranet.service.arquivo.UploadArquivoService;

@Service
public class UploadArquivoServiceImpl implements UploadArquivoService {

	@Autowired
	private UploadArquivoDAO uploadArquivoDAO;

	@Override
	@Transactional("sic")
	public List<Long> findByLikeIdPasta(String propertyName, String propertyName2, String value, String value2) throws JPAException {
		return uploadArquivoDAO.findByLikeIdPasta(propertyName, propertyName2, value, value2);
	}

	@Override
	@Transactional("sic")
	public UploadArquivo findById(Long id) throws JPAException {
		return uploadArquivoDAO.findById(id);
	}

	@Override
	@Transactional("sic")
	public List<Long> findByLike(String propertyName, String propertyName2, String value, String value2) throws JPAException {
		return uploadArquivoDAO.findByLike(propertyName, propertyName2, value, value2);
	}

	@Override
	@Transactional("sic")
	public List<Object[]> findByPropertyObject(String propertyName, Object value) throws JPAException {
		return uploadArquivoDAO.findByPropertyObject(propertyName, value);
	}

	@Override
	@Transactional("sic")
	public List<UploadArquivo> findByProperty(String propertyName, Object value) throws JPAException {
		return uploadArquivoDAO.findByProperty(propertyName, value);
	}

	@Override
	@Transactional("sic")
	public void salvarArquivo(UploadArquivo uploadArquivo) throws JPAException {
		uploadArquivoDAO.save(uploadArquivo);

	}

	@Override
	@Transactional("sic")
	public void alterarArquivo(UploadArquivo uploadArquivo) throws JPAException {
		uploadArquivoDAO.update(uploadArquivo);

	}

	@Override
	@Transactional("sic")
	public void excluirArquivo(UploadArquivo uploadArquivo) throws JPAException {
		uploadArquivoDAO.delete(uploadArquivo);

	}

	@Override
	@Transactional("sic")
	public List<UploadArquivo> findByDateIN(String propertyDate, String dataInicio, String dataFim, String propertyName, String where) throws JPAException {
		return uploadArquivoDAO.findByDateIN(propertyDate, dataInicio, dataFim, propertyName, where);
	}

}