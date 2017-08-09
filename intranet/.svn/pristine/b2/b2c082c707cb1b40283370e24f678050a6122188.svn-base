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
import br.com.sp.intranet.model.externo.ExternoFotosCardapio;

@Repository
public class FotoCardapioDAO {

	public void update(File f, ExternoFotosCardapio externoFotoCardapio) {
		FileOutputStream os;
		try {
			os = new FileOutputStream(f.getAbsolutePath(), true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String temp = gson.toJson(externoFotoCardapio);
	        bw.append(temp);
	        bw.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}