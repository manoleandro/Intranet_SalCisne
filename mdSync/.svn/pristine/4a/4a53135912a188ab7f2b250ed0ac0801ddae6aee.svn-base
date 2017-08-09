package com.mdSync.service.comercial;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lightcouch.CouchDbClient;
import org.springframework.core.ParameterizedTypeReference;

import com.mdSync.model.comercial.RegistroVisita;
import com.mdSync.service.Service;
import com.mdSync.util.Constant;
import com.mdSync.util.LocalDateUtils;

public class RegistroVisitaServiceImpl extends Service{
	
	private static final String DB_NAME = "registro_visita_atual";
	private static final String CLIENTES = "clientes";
	
	private static final String PARAM_REGISTRO_VISITA = "?idCliente={idCliente}&mesAno={mesAno}"; 
	
	private static RegistroVisitaServiceImpl instance;
    
    public static RegistroVisitaServiceImpl getInstance() {
        if (instance == null) {
            instance = new RegistroVisitaServiceImpl();
        }
        return instance;
    }
    
    public List<Object> carregarClientes() throws Exception{
		List<Object> clientes = new ArrayList<>();
		
		ParameterizedTypeReference<List<Long>> typeRef = new ParameterizedTypeReference<List<Long>>() {};
		
		clientes = exchangeRest(Constant.URL + CLIENTES, typeRef);
    	
		return clientes;
	}
    
    public Object carregarRegistroVisita(Long idCliente){
    	ParameterizedTypeReference<RegistroVisita> typeRef = new ParameterizedTypeReference<RegistroVisita>() {};
		
    	Map<String, String> params = new HashMap<>();
		params.put("idCliente", idCliente.toString());
		params.put("mesAno", LocalDateUtils.formatarData(LocalDate.now(), "MM/yyyy"));
		
    	return exchangeRestObject(Constant.URL + DB_NAME + PARAM_REGISTRO_VISITA, typeRef, params);
    }
    
    public void sincronizarRegistrosVisita(){
    	CouchDbClient dbClient = null;
    	
    	try {
			List<Object> clientes = carregarClientes();
			dbClient = createCouchDbClient(DB_NAME);
			
			for (Object object : clientes) {
				Long idCliente = (Long) object;
				RegistroVisita registroVisita = (RegistroVisita) this.carregarRegistroVisita(idCliente);
				syncCouch(dbClient, registroVisita, idCliente.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shutdownCouchDbClient(dbClient);
		}
    }
}
