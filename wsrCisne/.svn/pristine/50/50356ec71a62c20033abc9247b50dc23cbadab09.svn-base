package wsr.model.comercial;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARAMETRO", uniqueConstraints = {})
public class Parametro implements Serializable {

	private Long idParametro;
	private Date dataInicioConsProg;
	private Date dataClientesAtivos;
	private Double precoMedioPrevisao;
	private Long diaInicioPrevisao;
	private Long diaFimPrevisao;
	private String trava;
	private String emailCotacao;
	private Double percentualBloqueio;
	private Double valorPallet;
	private Double valorMaxTransf;
	private Double percentualTolerancia;
	private Long numMesMax;
	private Date dataFimPeNf;
	private Date dataFimNf;
	private Integer diasEmissaoPed;
	private Integer diasProcPed;
	private Integer diasExpedCarrreg;
	private Integer quantidadeMesSemCompra;

	public Parametro() {}

	@Id
	@Column(name = "ID_PARAMETRO", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getIdParametro() {
		return this.idParametro;
	}

	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}
	
	@Column(name = "DATA_INICIO_CONS_PROG", unique = false, nullable = false, insertable = true, updatable = true)
	public Date getDataInicioConsProg() {
		return this.dataInicioConsProg;
	}

	public void setDataInicioConsProg(Date dataInicioConsProg) {
		this.dataInicioConsProg = dataInicioConsProg;
	}

	@Column(name = "DATA_CLIENTES_ATIVOS", unique = false, nullable = false, insertable = true, updatable = true)
	public Date getDataClientesAtivos() {
		return this.dataClientesAtivos;
	}

	public void setDataClientesAtivos(Date dataClientesAtivos) {
		this.dataClientesAtivos = dataClientesAtivos;
	}

	@Column(name = "PRECO_MEDIO_PREVISAO", unique = false, nullable = true, insertable = true, updatable = true, precision = 12)
	public Double getPrecoMedioPrevisao() {
		return this.precoMedioPrevisao;
	}

	public void setPrecoMedioPrevisao(Double precoMedioPrevisao) {
		this.precoMedioPrevisao = precoMedioPrevisao;
	}

	@Column(name = "DIA_INICIO_PREVISAO", unique = false, nullable = true, insertable = true, updatable = true, precision = 2, scale = 0)
	public Long getDiaInicioPrevisao() {
		return this.diaInicioPrevisao;
	}

	public void setDiaInicioPrevisao(Long diaInicioPrevisao) {
		this.diaInicioPrevisao = diaInicioPrevisao;
	}

	@Column(name = "DIA_FIM_PREVISAO", unique = false, nullable = true, insertable = true, updatable = true, precision = 2, scale = 0)
	public Long getDiaFimPrevisao() {
		return this.diaFimPrevisao;
	}

	public void setDiaFimPrevisao(Long diaFimPrevisao) {
		this.diaFimPrevisao = diaFimPrevisao;
	}
	
	@Column(name = "TRAVA", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getTrava() {
		return this.trava;
	}

	public void setTrava(String trava) {
		this.trava = trava;
	}
	
	@Column(name = "EMAIL_COTACAO", unique = false, nullable = true, insertable = true, updatable = true, length = 400)
	public String getEmailCotacao() {
		return this.emailCotacao;
	}

	public void setEmailCotacao(String emailCotacao) {
		this.emailCotacao = emailCotacao;
	}
	
	@Column(name = "PERCENTUAL_BLOQUEIO", unique = false, nullable = true, insertable = true, updatable = true)
	public Double getPercentualBloqueio() {
		return this.percentualBloqueio;
	}

	public void setPercentualBloqueio(Double percentualBloqueio) {
		this.percentualBloqueio = percentualBloqueio;
	}
	
	@Column(name = "VALOR_PALLET", unique = false, nullable = true, insertable = true, updatable = true)
	public Double getValorPallet() {
		return this.valorPallet;
	}

	public void setValorPallet(Double valorPallet) {
		this.valorPallet = valorPallet;
	}
	
	@Column(name = "VALOR_MAX_TRANSF", unique = false, nullable = true, insertable = true, updatable = true)
	public Double getValorMaxTransf() {
		return this.valorMaxTransf;
	}

	public void setValorMaxTransf(Double valorMaxTransf) {
		this.valorMaxTransf = valorMaxTransf;
	}
	
	@Column(name = "PERCENTUAL_TOLERANCIA", unique = false, nullable = true, insertable = true, updatable = true)
	public Double getPercentualTolerancia() {
		return this.percentualTolerancia;
	}

	public void setPercentualTolerancia(Double percentualTolerancia) {
		this.percentualTolerancia = percentualTolerancia;
	}
	
	@Column(name = "NUM_MES_MAX", unique = false, nullable = true, insertable = true, updatable = true, scale = 0)
	public Long getNumMesMax() {
		return this.numMesMax;
	}

	public void setNumMesMax(Long numMesMax) {
		this.numMesMax = numMesMax;
	}

	@Column(name = "DATA_FIM_PENF", unique = false, nullable = true, insertable = true, updatable = true)
	public Date getDataFimPeNf() {
		return dataFimPeNf;
	}

	public void setDataFimPeNf(Date dataFimPeNf) {
		this.dataFimPeNf = dataFimPeNf;
	}
	
	@Column(name = "DATA_FIM_NF", unique = false, nullable = true, insertable = true, updatable = true)
	public Date getDataFimNf() {
		return dataFimNf;
	}

	public void setDataFimNf(Date dataFimNf) {
		this.dataFimNf = dataFimNf;
	}
	
	@Column(name="DIAS_EMISSAO_PED")
	public Integer getDiasEmissaoPed() {
		return diasEmissaoPed;
	}

	public void setDiasEmissaoPed(Integer diasEmissaoPed) {
		this.diasEmissaoPed = diasEmissaoPed;
	}
	
	@Column(name="DIAS_PROC_PED")
	public Integer getDiasProcPed() {
		return diasProcPed;
	}

	public void setDiasProcPed(Integer diasProcPed) {
		this.diasProcPed = diasProcPed;
	}
	
	@Column(name="DIAS_EXPED_CARREG")
	public Integer getDiasExpedCarrreg() {
		return diasExpedCarrreg;
	}

	public void setDiasExpedCarrreg(Integer diasExpedCarrreg) {
		this.diasExpedCarrreg = diasExpedCarrreg;
	}

	@Column(name = "QTD_MES_SEM_COMPRA")
	public Integer getQuantidadeMesSemCompra() {
		return quantidadeMesSemCompra;
	}

	public void setQuantidadeMesSemCompra(Integer quantidadeMesSemCompra) {
		this.quantidadeMesSemCompra = quantidadeMesSemCompra;
	}
}	
