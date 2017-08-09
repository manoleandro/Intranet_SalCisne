package br.com.sp.intranet.dao.externo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.sp.intranet.model.externo.ExternoCardapio;

@Repository
public class CardapioDAO {

	public void update(File f, ExternoCardapio externoCardapio) {
		FileOutputStream os;
		try {
			os = new FileOutputStream(f.getAbsolutePath(), true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String temp = gson.toJson(externoCardapio);
	        bw.append(temp);
	        bw.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}