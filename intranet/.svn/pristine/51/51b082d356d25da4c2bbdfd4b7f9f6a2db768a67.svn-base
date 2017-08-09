package br.com.sp.intranet.model.administrador.vo;

import br.com.sp.intranet.model.administrador.CsMenu;
import br.com.sp.intranet.model.administrador.CsServico;

public class PermissaoUsuarioVO implements Comparable<PermissaoUsuarioVO>{
	
	private CsMenu menu;
	private CsServico servico;
	
	public CsMenu getMenu() {
		return menu;
	}

	public void setMenu(CsMenu menu) {
		this.menu = menu;
	}

	public CsServico getServico() {
		return servico;
	}

	public void setServico(CsServico servico) {
		this.servico = servico;
	}


	@Override
	public int compareTo(PermissaoUsuarioVO o) {
		if (this.getMenu().getOrdem().compareTo(o.getMenu().getOrdem()) != 0) {
			return this.getMenu().getOrdem().compareTo(o.getMenu().getOrdem());
		
		}else{
			if (this.getServico().getOrdem().compareTo(o.getServico().getOrdem()) != 0) {
				return this.getServico().getOrdem().compareTo(o.getServico().getOrdem());
			} else {
				return this.getServico().getDescricao().compareTo(o.getServico().getDescricao());
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermissaoUsuarioVO other = (PermissaoUsuarioVO) obj;
		if (menu == null) {
			if (other.menu != null)
				return false;
		} else if (!menu.equals(other.menu))
			return false;
		return true;
	}
}