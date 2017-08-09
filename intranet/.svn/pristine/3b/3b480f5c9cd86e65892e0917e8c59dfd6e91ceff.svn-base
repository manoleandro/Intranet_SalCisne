package br.com.sp.intranet.model.evento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "FOTO")
@SequenceGenerator(name = "SEQ_FOTO", sequenceName = "SEQ_FOTO")
public class Foto extends GenericEntity {

	private Long idFoto;
	private Evento evento;
	private String descricao;
	private Long ordem;
	private byte[] imagem;
	private Boolean exibe;

	public Foto() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_FOTO")
	@Column(name = "ID_FOTO", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getIdFoto() {
		return this.idFoto;
	}

	public void setIdFoto(Long idFoto) {
		this.idFoto = idFoto;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EVENTO", unique = false, nullable = true, insertable = true, updatable = true)
	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Column(name = "DESCRICAO", unique = false, nullable = true, insertable = true, updatable = true)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "ORDEM", unique = false, nullable = true, insertable = true, updatable = true, scale = 0)
	public Long getOrdem() {
		return this.ordem;
	}

	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}

	@Lob
	@Column(name = "IMAGEM", unique = false, nullable = true, insertable = true, updatable = true)
	public byte[] getImagem() {
		return this.imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@Column(name = "EXIBE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public Boolean getExibe() {
		return this.exibe;
	}

	public void setExibe(Boolean exibe) {
		this.exibe = exibe;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return getIdFoto();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFoto == null) ? 0 : idFoto.hashCode());
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
		Foto other = (Foto) obj;
		if (idFoto == null) {
			if (other.idFoto != null)
				return false;
		} else if (!idFoto.equals(other.idFoto))
			return false;
		return true;
	}
}