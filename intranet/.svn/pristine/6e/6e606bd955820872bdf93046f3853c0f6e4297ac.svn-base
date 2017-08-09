package br.com.sp.intranet.dao.administrador;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsMenu;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.vo.PermissaoUsuarioVO;

@Repository
public class MenuDAO extends GenericDAO {
	
	@SuppressWarnings("unchecked")
	public List<PermissaoUsuarioVO> findPermissoesByUsuario(String usuario) throws JPAException{
		List<PermissaoUsuarioVO> list;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT {menu.*}, {servico.*}");
		sql.append(" FROM CS_USUARIO usuario INNER JOIN CS_USUARIO_GRUPO usuarioGrupo ON usuario.USERNAME = usuarioGrupo.USUARIO");
		sql.append(" INNER JOIN CS_GRUPO_SERVICO grupoServico ON grupoServico.ID_GRUPO = usuarioGrupo.ID_GRUPO");
		sql.append(" INNER JOIN CS_SERVICO servico ON servico.ID = grupoServico.ID_SERVICO");
		sql.append(" LEFT JOIN CS_MENU menu ON menu.ID = servico.ID_MENU");
		sql.append(" WHERE usuario.USERNAME = :usuario");
		sql.append(" AND servico.VISIVEL = 'T'");
		
		sql.append(" UNION");

		sql.append(" SELECT {menu.*}, {servico.*}");
		sql.append(" FROM CS_USUARIO usuario INNER JOIN CS_USUARIO_SERVICO usuarioServico ON usuario.USERNAME = usuarioServico.USUARIO");
		sql.append(" INNER JOIN CS_SERVICO servico ON servico.ID = usuarioServico.ID_SERVICO");
		sql.append(" LEFT JOIN CS_MENU menu ON menu.ID = servico.ID_MENU");
		sql.append(" WHERE usuario.USERNAME = :usuario");
		sql.append(" AND servico.VISIVEL = 'T'");

		Query query = getSession().createSQLQuery(sql.toString())
				.addEntity("menu", CsMenu.class)
				.addEntity("servico", CsServico.class)
				.setResultTransformer(new AliasToBeanResultTransformer(PermissaoUsuarioVO.class));
		
		query.setParameter("usuario", usuario);
		
		list = query.list();
		
		if (list == null || list.isEmpty())
		return null;

		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<CsMenu> findAll() throws JPAException{
		List<CsMenu> list;
		
		list = getSession().createQuery("SELECT model FROM CsMenu model ORDER BY model.ordem, model.descricao").list();

		if (list == null || list.isEmpty())
			return null;

		return list;
	}
}