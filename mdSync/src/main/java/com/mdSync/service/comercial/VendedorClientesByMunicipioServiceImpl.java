package com.mdSync.service.comercial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lightcouch.CouchDbClient;
import org.springframework.core.ParameterizedTypeReference;

import com.mdSync.model.comercial.VendedorClienteByMunicipio;
import com.mdSync.service.Service;
import com.mdSync.util.Constant;

public class VendedorClientesByMunicipioServiceImpl extends Service {
	
	private static final String DB_NAME ="vendedores_clientes_by_municipio";
	private static final String PARAM_VENDEDORES_CLIENTES_BY_MUNICIPIO = "?mesAno={mesAno}";
	
	private static VendedorClientesByMunicipioServiceImpl instance;
    
    public static VendedorClientesByMunicipioServiceImpl getInstance() {
        if (instance == null) {
            instance = new VendedorClientesByMunicipioServiceImpl();
        }
        return instance;
    }
    
	public List<Object> carregarVendedorClientesByMunicipio(String mesAno) throws Exception{
		List<Object> list = new ArrayList<Object>();
		
		ParameterizedTypeReference<List<VendedorClienteByMunicipio>> typeRef = new ParameterizedTypeReference<List<VendedorClienteByMunicipio>>() {};
		Map<String, String> params = new HashMap<>();
		params.put("mesAno", mesAno);
		
		list = exchangeRest(Constant.URL + DB_NAME + PARAM_VENDEDORES_CLIENTES_BY_MUNICIPIO, typeRef, params);
    	
		return list;
	}
    
    public void sincronizarVendedorClientesByMunicipio(String mesAno){
    	CouchDbClient dbClient = null;
    	
    	try {
			List<Object> list = carregarVendedorClientesByMunicipio(mesAno);
			dbClient = createCouchDbClient(DB_NAME);
			
			for (Object object : list) {
				VendedorClienteByMunicipio vendedorClienteByMunicipio = (VendedorClienteByMunicipio) object;
				syncCouch(dbClient, vendedorClienteByMunicipio, vendedorClienteByMunicipio.getIdVendedor().toString() + "#" + vendedorClienteByMunicipio.getMesAno());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shutdownCouchDbClient(dbClient);
		}
    }
}