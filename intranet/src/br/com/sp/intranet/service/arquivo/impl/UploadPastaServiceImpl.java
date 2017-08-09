package br.com.sp.intranet.service.arquivo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.arquivo.UploadPastaDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.upload.UploadPasta;
import br.com.sp.intranet.service.arquivo.UploadPastaService;

@Service
public class UploadPastaServiceImpl implements UploadPastaService {
	
	@Autowired
	private UploadPastaDAO uploadPastaDAO;

	@Override
	@Transactional("sic")
	public List<UploadPasta> listaTodos() throws JPAException {
		return uploadPastaDAO.listarTodasPastas();
	}

	@Override
	@Transactional("sic")
	public List<UploadPasta> findByProperty(String propertyName, Object value) throws JPAException {
		return uploadPastaDAO.findByProperty(propertyName, value);
	}

	@Override
	@Transactional("sic")
	public List<UploadPasta> findByColumnIsNull(String propertyName, String valueIsNull, String tableName) throws JPAException {
		return uploadPastaDAO.findByColumnIsNull(propertyName, valueIsNull, tableName);
	}

	@Override
	@Transactional("sic")
	public UploadPasta findById(Long id) throws JPAException {
		return uploadPastaDAO.findById(id);
	}

	@Override
	@Transactional("sic")
	public void incluirPasta(UploadPasta uploadPasta) throws JPAException {
		uploadPastaDAO.save(uploadPasta);
		
	}

	@Override
	@Transactional("sic")
	public void excluirPasta(UploadPasta uploadPasta) throws JPAException {
		uploadPastaDAO.delete(uploadPasta);
		
	}

	@Override
	@Transactional("sic")
	public void alterarPasta(UploadPasta uploadPasta) throws JPAException {
		uploadPastaDAO.update(uploadPasta);
		
	}

	@Override
	@Transactional("sic")
	public List<UploadPasta> findPastaBySuperior(Long pastaSuperior) throws JPAException {
		return uploadPastaDAO.findPastaBySuperior(pastaSuperior);
	}


}