package com.mdSync.service.security;

import java.util.ArrayList;
import java.util.List;

import org.lightcouch.CouchDbClient;
import org.springframework.core.ParameterizedTypeReference;

import com.mdSync.model.security.CsUsuario;
import com.mdSync.service.Service;
import com.mdSync.util.Constant;

public class UsuarioServiceImpl extends Service{
	
	private static final String DB_NAME ="usuarios";
	
	private static UsuarioServiceImpl instance;
    
    public static UsuarioServiceImpl getInstance() {
        if (instance == null) {
            instance = new UsuarioServiceImpl();
        }
        return instance;
    }
    
    public List<Object> carregarUsuarios() throws Exception{
		List<Object> usuarios = new ArrayList<>();
		
		ParameterizedTypeReference<List<CsUsuario>> typeRef = new ParameterizedTypeReference<List<CsUsuario>>() {};
		
		usuarios = exchangeRest(Constant.URL + DB_NAME, typeRef);
    	
		return usuarios;
	}
    
    public void sincronizarUsuarios(){
    	CouchDbClient dbClient = null;
    	
    	try {
    		List<Object> usuarios = carregarUsuarios();
			dbClient = createCouchDbClient(DB_NAME);
			
			for (Object object : usuarios) {
				CsUsuario usuario = (CsUsuario) object;
				syncCouch(dbClient, usuario, usuario.getUsername());
			}
			shutdownCouchDbClient(dbClient);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shutdownCouchDbClient(dbClient);
		}
    }
}