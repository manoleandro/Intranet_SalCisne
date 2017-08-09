package br.com.sp.intranet.service.externo;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.externo.ExternoGaleria;
import br.com.sp.intranet.model.externo.Galeria;

public interface GaleriaService {

	public void delete(Galeria galeria, ExternoGaleria externo) throws JPAException;
	public void update(Galeria galeria, ExternoGaleria externo) throws JPAException;
	public void save(Galeria galeria, ExternoGaleria externo) throws JPAException;

}