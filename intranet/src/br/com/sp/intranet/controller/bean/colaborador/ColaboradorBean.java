package br.com.sp.intranet.controller.bean.colaborador;

import org.primefaces.model.StreamedContent;

import br.com.sp.intranet.controller.bean.GenericBean;
import br.com.sp.intranet.model.administrador.vo.rh.Cargo;
import br.com.sp.intranet.model.administrador.vo.rh.Colaborador;
import br.com.sp.intranet.model.administrador.vo.rh.Departamento;
import br.com.sp.intranet.model.administrador.vo.rh.Diretoria;
import br.com.sp.intranet.model.administrador.vo.rh.Secao;
import br.com.sp.intranet.model.administrador.vo.rh.Setor;

public class ColaboradorBean extends GenericBean {

	private Colaborador colaborador;
	private Cargo cargo;
	private Diretoria diretoria;
	private Departamento dept;
	private Setor setor;
	private Secao secao;
	private StreamedContent foto;
	
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public Departamento getDept() {
		return dept;
	}
	public void setDept(Departamento dept) {
		this.dept = dept;
	}
	public StreamedContent getFoto() {
		return foto;
	}
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
	public Diretoria getDiretoria() {
		return diretoria;
	}
	public void setDiretoria(Diretoria diretoria) {
		this.diretoria = diretoria;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public Secao getSecao() {
		return secao;
	}
	public void setSecao(Secao secao) {
		this.secao = secao;
	}
}