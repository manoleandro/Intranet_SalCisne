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
import br.com.sp.intranet.controller.administrador.LoginController;
import br.com.sp.intranet.dao.externo.ContatoDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.externo.Contato;
import br.com.sp.intranet.model.externo.Externo;
import br.com.sp.intranet.service.externo.ContatoService;

@Service
public class ContatoServiceImpl implements ContatoService {
	
	@Autowired
	private ContatoDAO dao;
	
	@Autowired
	private LoginController login;

	
	
	public void save(Contato contatoNovo, Externo externo) throws JPAException {
		contatoNovo.setUser(login.getUsuario().getUsername());
		List<Contato> listContatos = new ArrayList<Contato>();
		listContatos = externo.getContatos();
		listContatos.add(contatoNovo);
		externo.setContatos(listContatos);
		Collections.sort(listContatos);
		
		String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		System.out.println("realPath: " + realPath);
		File f = new File(realPath + "\\contatos.json");
		
		if(f.delete()){
			System.out.println(f.getName() + "(SAVE) arquivo removido...");
		} else{
			System.out.println("(SAVE) falha ao remover arquivo...");
		}
		
		try {

			f.createNewFile();
			dao.save(f, externo);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void update(Contato contato, Externo externo) throws JPAException {
		contato.setUser(login.getUsuario().getUsername());
		List<Contato> listContatos = new ArrayList<Contato>();
		listContatos = externo.getContatos();
		listContatos.remove(contato);
		externo.setContatos(listContatos);
		listContatos.add(contato);
		
		Collections.sort(listContatos);
		
		String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		System.out.println("realPath: " + realPath);
		File f = new File(realPath + "\\contatos.json");
		
		if(f.delete()) {
			System.out.println(f.getName() + "(UPDATE) arquivo removido...");
		} else{
			System.out.println("(UPDATE) falha ao remover arquivo...");
		}
		
		try {

			f.createNewFile();
			dao.update(f, externo);
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

	public void delete(Contato contato, Externo externo) throws JPAException {
		List<Contato> listContatos = new ArrayList<Contato>();
		listContatos = externo.getContatos();
		listContatos.remove(contato);
		externo.setContatos(listContatos);
		Collections.sort(listContatos);
		
		String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		System.out.println("realPath: " + realPath);
		File f = new File(realPath + "\\contatos.json");
		
		if(f.delete()) {
			System.out.println(f.getName() + "(REMOVE) arquivo removido...");
		} else{
			System.out.println("(REMOVE) falha ao remover arquivo...");
		}
		
		try {

			f.createNewFile();
			dao.delete(f, externo);
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}
	
	public List<Contato> findAll(Externo externo) throws JPAException {
		return externo.getContatos();
	}

}