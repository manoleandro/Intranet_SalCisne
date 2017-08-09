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
import br.com.sp.intranet.dao.externo.CardapioDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.externo.Cardapio;
import br.com.sp.intranet.model.externo.ExternoCardapio;
import br.com.sp.intranet.service.externo.CardapioService;

@Service
public class CardapioServiceImpl implements CardapioService {

	@Autowired
	private CardapioDAO dao;

	@Autowired
	private LoginController login;

	public void update(Cardapio cardapio, ExternoCardapio externoCardapio) throws JPAException {
		cardapio.setUser(login.getUsuario().getUsername());
		List<Cardapio> listCardapios = new ArrayList<Cardapio>();
		listCardapios = externoCardapio.getCardapios();
		listCardapios.remove(cardapio);
		externoCardapio.setCardapios(listCardapios);
		listCardapios.add(cardapio);
		System.out.println("NOME FOTO" + cardapio.getNomeFoto());
		Collections.sort(listCardapios);

		String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		System.out.println("realPath: " + realPath);
		File f = new File(realPath + "\\cardapio.json");

		if (f.delete()) {
			System.out.println(f.getName() + "(UPDATE CARDÁIO) arquivo removido...");
		} else {
			System.out.println("(UPDATE CARDÁIO) falha ao remover arquivo...");
		}

		try {

			f.createNewFile();
			dao.update(f, externoCardapio);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}