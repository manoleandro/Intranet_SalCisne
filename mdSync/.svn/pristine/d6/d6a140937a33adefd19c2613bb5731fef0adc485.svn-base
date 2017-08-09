package com.mdSync.service.comercial;

import java.util.ArrayList;
import java.util.List;

import org.lightcouch.CouchDbClient;
import org.springframework.core.ParameterizedTypeReference;

import com.mdSync.model.comercial.Vendedor;
import com.mdSync.service.Service;
import com.mdSync.util.Constant;

public class VendedoresServiceImpl extends Service{
	
	private static final String DB_NAME ="vendedores";
	
	private static VendedoresServiceImpl instance;
    
    public static VendedoresServiceImpl getInstance() {
        if (instance == null) {
            instance = new VendedoresServiceImpl();
        }
        return instance;
    }
    
    public List<Object> carregarVendedores() throws Exception{
		List<Object> vendedores = new ArrayList<>();
		
		ParameterizedTypeReference<List<Vendedor>> typeRef = new ParameterizedTypeReference<List<Vendedor>>() {};
		
		vendedores = exchangeRest(Constant.URL + DB_NAME, typeRef);
    	
		return vendedores;
	}
    
    public void sincronizarVendedores(){
    	CouchDbClient dbClient = null;
    	
    	try {
			List<Object> vendedores = carregarVendedores();
			dbClient = createCouchDbClient(DB_NAME);
			
			for (Object object : vendedores) {
				Vendedor vendedor = (Vendedor) object;
				syncCouch(dbClient, vendedor, vendedor.getIdVendedor().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			shutdownCouchDbClient(dbClient);
		}
    }
}
