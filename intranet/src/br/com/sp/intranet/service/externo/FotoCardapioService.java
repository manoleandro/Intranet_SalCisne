package br.com.sp.intranet.service.externo;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.externo.Cardapio;
import br.com.sp.intranet.model.externo.ExternoCardapio;
import br.com.sp.intranet.model.externo.ExternoFotosCardapio;
import br.com.sp.intranet.model.externo.FotosCardapio;

public interface FotoCardapioService {
	public void update(FotosCardapio fotoCardapio, ExternoFotosCardapio externoFotosCardapio) throws JPAException;

}