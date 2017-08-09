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
import br.com.sp.intranet.dao.externo.FotoCardapioDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.externo.ExternoFotosCardapio;
import br.com.sp.intranet.model.externo.FotosCardapio;
import br.com.sp.intranet.service.externo.FotoCardapioService;

@Service
public class FotoCardapioServiceImpl implements FotoCardapioService {

	@Autowired
	private FotoCardapioDAO dao;

	public void update(FotosCardapio fotoCardapio, ExternoFotosCardapio externoFotosCardapio) throws JPAException {
		List<FotosCardapio> listFotosCardapio = new ArrayList<FotosCardapio>();
		listFotosCardapio = externoFotosCardapio.getFotos();
		listFotosCardapio.remove(fotoCardapio);
		externoFotosCardapio.setFotos(listFotosCardapio);
		listFotosCardapio.add(fotoCardapio);
		Collections.sort(listFotosCardapio);

		String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		System.out.println("realPath: " + realPath);
		File f = new File(realPath + "\\fotoscardapio.json");

		if (f.delete()) {
			System.out.println(f.getName() + "(UPDATE FOTO) arquivo removido...");
		} else {
			System.out.println("(UPDATE FOTO) falha ao remover arquivo...");
		}

		try {

			f.createNewFile();
			dao.update(f, externoFotosCardapio);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}