package br.com.sp.intranet.dao.externo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import br.com.sp.intranet.model.externo.Contato;
import br.com.sp.intranet.model.externo.Externo;

@Repository
public class ContatoDAO {

	public void save(File f, Externo externo) {
		FileOutputStream os;
		try {
			os = new FileOutputStream(f.getAbsolutePath(), true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String temp = gson.toJson(externo);
	        bw.append(temp);
	        bw.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(File f, Externo externo) {
		FileOutputStream os;
		try {
			os = new FileOutputStream(f.getAbsolutePath(), true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String temp = gson.toJson(externo);
	        bw.append(temp);
	        bw.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(File f, Externo externo) {
		FileOutputStream os;
		try {
			os = new FileOutputStream(f.getAbsolutePath(), true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String temp = gson.toJson(externo);
	        bw.append(temp);
	        bw.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}