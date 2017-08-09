package wsr.model.comercial;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WSR_PRAZO_REPOSICAO")
public class PrazoReposicao implements Serializable {
	private Long id;
	private String origem;
	private String destino;
	private Long dias;
	private Long totalDiasEntrega;
	
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ORIGEM")
	public String getOrigem() {
		return origem;
	}
	
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	@Column(name = "DESTINO")
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	@Column(name = "DIAS")
	public Long getDias() {
		return dias;
	}
	
	public void setDias(Long dias) {
		this.dias = dias;
	}
	
	@Column(name = "TOTAL_DIAS_ENTREGA")
	public Long getTotalDiasEntrega() {
		return totalDiasEntrega;
	}
	
	public void setTotalDiasEntrega(Long totalDiasEntrega) {
		this.totalDiasEntrega = totalDiasEntrega;
	}
}