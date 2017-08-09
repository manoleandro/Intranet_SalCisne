package br.com.sp.intranet.dao.arquivo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.upload.UploadArquivo;
import br.com.sp.intranet.util.DataTypes;

@Repository
public class UploadArquivoDAO extends GenericDAO {


	@SuppressWarnings("unchecked")
	public List<Long> findByLike(String propertyName, String propertyName2, String value, String value2) throws JPAException {
		
		StringBuffer sbSQL = new StringBuffer( "SELECT model.idArquivo from UploadArquivo model" );
		
		sbSQL.append( " where "+ propertyName + " like '%"+ value +"%'" );
		if(!DataTypes.isNull(value2)){
			sbSQL.append( " or " + propertyName2 + " like '%"+value2+";%'" );
		}
		sbSQL.append( " order by model.idArquivo" );

		List<Long> list;
		list = getSession().createQuery(sbSQL.toString()).list();	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByPropertyObject(String propertyName, Object value) throws JPAException {
		
		
		StringBuffer sbSQL = new StringBuffer( "SELECT model.idArquivo, model.descricao, model.uploadPasta, model.dtInclusao, model.nomeArquivo, model.tipoArquivo,");
		sbSQL.append(" model.usuarioComp, model.setorComp");
		sbSQL.append(" from UploadArquivo model" );
		sbSQL.append( " where "+ propertyName + "= '"+value+"'" );
		sbSQL.append( " order by model.idArquivo" );
		
		System.out.println("QUERY ARQUIVOS: " + sbSQL);
		
		List<Object[]> list = new ArrayList<Object[]>();
		list = getSession().createQuery(sbSQL.toString()).list();	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<UploadArquivo> findByProperty(String propertyName, Object value) throws JPAException {
		
		StringBuffer sbSQL = new StringBuffer( "from UploadArquivo model" );
		sbSQL.append( " where "+ propertyName + "= :propertyValue" );
		sbSQL.append( " order by model.idArquivo" );

		List<UploadArquivo> list;
		list = getSession().createQuery(sbSQL.toString()).setParameter( "propertyValue", value ).list();	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> findByLikeIdPasta(String propertyName, String propertyName2, String value, String value2) throws JPAException {
		
		StringBuffer sbSQL = new StringBuffer( "SELECT model.uploadPasta.idPasta from UploadArquivo model" );
		
		sbSQL.append( " where "+ propertyName + " like '%"+ value +"%'" );
		if(!DataTypes.isNull(value2)){
			sbSQL.append( " or " + propertyName2 + " like '%"+value2+";%'" );
		}
		sbSQL.append( " order by model.idArquivo" );
		
		System.out.println("QUERY COMPLETA: " + sbSQL);

		List<Long> list;
		list = getSession().createQuery(sbSQL.toString()).list();	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}

	@Transactional("sic")
	public UploadArquivo findById(Long id) throws JPAException {
		return (UploadArquivo) findById(UploadArquivo.class, id);
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<UploadArquivo> findByDateIN(String propertyNameDate, String dataInicio, String dataFim, String propertyNameIN, String where) throws JPAException{
		
		StringBuffer sbSQL = new StringBuffer( "from UploadArquivo model" );
		
		sbSQL.append( " where " + propertyNameDate +" between to_date('"+dataInicio+"','dd/MM/yyyy') and to_date('" + dataFim+"','dd/MM/yyyy')"); //Oracle Command
		/*sbSQL.append( " where date_format("+ propertyNameDate+", '%d%m%Y') between '"+dataInicio+"' and  '"+dataFim+"'"); //MYSQL Command*/
		/*sbSQL.append(" where "+propertyNameDate+" >='"+dataInicio+"' and "+ propertyNameDate +"<= '"+dataFim+"'");*/
		if(propertyNameIN !=null){
			sbSQL.append(" AND (" + propertyNameIN + " IN "+ where +")");
		}	
		sbSQL.append( " order by model.idArquivo" );

		List<UploadArquivo> list;
		list = getSession().createQuery(sbSQL.toString()).list();	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	


}