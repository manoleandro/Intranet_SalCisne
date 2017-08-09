package br.com.sp.intranet.service.externo.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.sp.intranet.dao.externo.GaleriaDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.externo.ExternoGaleria;
import br.com.sp.intranet.model.externo.Galeria;
import br.com.sp.intranet.service.externo.GaleriaService;

@Service
public class GaleriaServiceImpl implements GaleriaService {
	
	@Autowired
	private GaleriaDAO dao;
	
	public void save(Galeria galeriaNovo, ExternoGaleria externoGaleria) throws JPAException {
		List<Galeria> listGalerias = new ArrayList<Galeria>();
		listGalerias = externoGaleria.getGalerias();
		listGalerias.add(galeriaNovo);
		externoGaleria.setGalerias(listGalerias);
		Collections.sort(listGalerias);
		
		String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		System.out.println("realPath: " + realPath);
		File f = new File(realPath + "\\galeria.json");
		
		if(f.delete()){
			System.out.println(f.getName() + "(SAVE) arquivo removido...");
		} else{
			System.out.println("(SAVE) falha ao remover arquivo...");
		}
		
		try {

			f.createNewFile();
			dao.save(f, externoGaleria);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void update(Galeria galeria, ExternoGaleria externoGaleria) throws JPAException {
		
		List<Galeria> listGalerias = new ArrayList<Galeria>();
		listGalerias = externoGaleria.getGalerias();
		listGalerias.remove(galeria);
		externoGaleria.setGalerias(listGalerias);
		listGalerias.add(galeria);
		
		Collections.sort(listGalerias);
		
		String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		System.out.println("realPath: " + realPath);
		File f = new File(realPath + "\\galeria.json");
		
		if(f.delete()) {
			System.out.println(f.getName() + "(UPDATE) arquivo removido...");
		} else{
			System.out.println("(UPDATE) falha ao remover arquivo...");
		}
		
		try {

			f.createNewFile();
			dao.update(f, externoGaleria);
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

	public void delete(Galeria Galeria, ExternoGaleria externoGaleria) throws JPAException {
		List<Galeria> listGalerias = new ArrayList<Galeria>();
		listGalerias = externoGaleria.getGalerias();
		listGalerias.remove(Galeria);
		externoGaleria.setGalerias(listGalerias);
		Collections.sort(listGalerias);
		
		String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		System.out.println("realPath: " + realPath);
		File f = new File(realPath + "\\galeria.json");
		
		if(f.delete()) {
			System.out.println(f.getName() + "(REMOVE) arquivo removido...");
		} else{
			System.out.println("(REMOVE) falha ao remover arquivo...");
		}
		
		try {

			f.createNewFile();
			dao.delete(f, externoGaleria);
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}
	
}