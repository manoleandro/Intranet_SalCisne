package br.com.sp.intranet.service.comercial;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.Configuracao;

public interface ConfiguracaoService {

	public Configuracao find(CsUsuario usuario) throws JPAException;

	public boolean salvar(CsUsuario usuario, Configuracao configuracao) throws JPAException;

}