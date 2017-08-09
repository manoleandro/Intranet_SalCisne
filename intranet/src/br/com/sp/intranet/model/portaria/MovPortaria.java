package br.com.sp.intranet.model.portaria;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import br.com.sp.intranet.model.GenericEntity;

@Entity
@Table(name = "MOV_PORTARIA")
@SequenceGenerator(name="SEQ_MOV_PORTARIA",sequenceName="SEQ_MOV_PORTARIA")
public class MovPortaria extends GenericEntity {
	
	// Fields
	private Long idMov;
	private Date data;
	private String placa;
	private String numeroOrdem;
	private TransportadoraPortaria transportadoraPortaria;
	private Veiculo veiculo;
	private String nomeMotorista;
	private String rgMotorista;
	private Date horaOrdemCarrega;
	private Date horaEntrada;
	private Date horaInicioCarregamento;
	private Date horaTerminoCarregamento;
	private Date horaSaida;
	private String contratacao;
	private Double peso;
	private Double peso2;
	private String tipoCarga;
	private Long numeroNf;
	private String tempoEspera;
	private String tempoCarregamento;
	private String tempoLonagem;
	private String tempoPermanencia;
	private String veiculoLimpo;
	private String vestimentaAdequada;
	private String obsPortaria;
	private String obsFaturamento;
	private String dataHora;
	private byte[] foto;
	private int status;
	private Date dataInicio;
	private Date dataFim;
	private String justificativa;

	// Constructors

	/** default constructor */
	public MovPortaria() {
	}

	/** minimal constructor */
	public MovPortaria(Long idMov) {
		this.idMov = idMov;
	}

	/** full constructor */
	public MovPortaria(Long idMov, Date data, String placa, String numeroOrdem, String nomeMotorista,
			Date horaChegada, Date horaOrdemCarrega, Date horaEntrada,
			Date horaInicioCarregamento, Date horaTerminoCarregamento,
			Date horaSaida, String contratacao, Double peso, String tipoCarga,
			Long numeroNf, String tempoEspera, String tempoCarregamento, String tempoLonagem, String tempoPermanencia,
			String veiculoLimpo, String vestimentaAdequada, 
			String obsPortaria, String obsFaturamento) {
		this.idMov = idMov;
		this.data = data;
		this.placa = placa;
		this.numeroOrdem = numeroOrdem;
		this.nomeMotorista = nomeMotorista;
		this.horaOrdemCarrega = horaOrdemCarrega;
		this.horaEntrada = horaEntrada;
		this.horaInicioCarregamento = horaInicioCarregamento;
		this.horaTerminoCarregamento = horaTerminoCarregamento;
		this.horaSaida = horaSaida;
		this.contratacao = contratacao;
		this.peso = peso;
		this.tipoCarga = tipoCarga;
		this.numeroNf = numeroNf;
		this.tempoEspera = tempoEspera;
		this.tempoCarregamento = tempoCarregamento;
		this.tempoLonagem = tempoLonagem;
		this.tempoPermanencia = tempoPermanencia;
		this.vestimentaAdequada = vestimentaAdequada;
		this.veiculoLimpo = veiculoLimpo;
		this.obsPortaria = obsPortaria;
		this.obsFaturamento = obsFaturamento;
	}

	// Property accessors
	@Id @GeneratedValue(strategy=GenerationType.AUTO,generator="SEQ_MOV_PORTARIA")
	@Column(name = "ID_MOV", unique = true, nullable = false, insertable = true, updatable = true, scale = 0)
	public Long getIdMov() {
		return this.idMov;
	}

	public void setIdMov(Long idMov) {
		this.idMov = idMov;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "PLACA", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@Column(name = "NUMERO_ORDEM", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getNumeroOrdem() {
		return this.numeroOrdem;
	}

	public void setNumeroOrdem(String numeroOrdem) {
		this.numeroOrdem = numeroOrdem;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TRANSPORTADORA", unique = false, nullable = true, insertable = true, updatable = true)
	public TransportadoraPortaria getTransportadoraPortaria() {
		return this.transportadoraPortaria;
	}

	public void setTransportadoraPortaria(
			TransportadoraPortaria transportadoraPortaria) {
		this.transportadoraPortaria = transportadoraPortaria;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_VEICULO", unique = false, nullable = true, insertable = true, updatable = true)
	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Column(name = "NOME_MOTORISTA", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getNomeMotorista() {
		return this.nomeMotorista;
	}

	public void setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_ORDEM_CARREGA", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Date getHoraOrdemCarrega() {
		return this.horaOrdemCarrega;
	}

	public void setHoraOrdemCarrega(Date horaOrdemCarrega) {
		this.horaOrdemCarrega = horaOrdemCarrega;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_ENTRADA", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Date getHoraEntrada() {
		return this.horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_INICIO_CARREGAMENTO", unique = false, nullable = true, insertable = true, updatable = true)
	public Date getHoraInicioCarregamento() {
		return this.horaInicioCarregamento;
	}

	public void setHoraInicioCarregamento(Date horaInicioCarregamento) {
		this.horaInicioCarregamento = horaInicioCarregamento;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_TERMINO_CARREGAMENTO", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Date getHoraTerminoCarregamento() {
		return this.horaTerminoCarregamento;
	}

	public void setHoraTerminoCarregamento(Date horaTerminoCarregamento) {
		this.horaTerminoCarregamento = horaTerminoCarregamento;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_SAIDA", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	public Date getHoraSaida() {
		return this.horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	@Column(name = "CONTRATACAO", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getContratacao() {
		return this.contratacao;
	}

	public void setContratacao(String contratacao) {
		this.contratacao = contratacao;
	}

	@Column(name = "PESO", unique = false, nullable = true, insertable = true, updatable = true)
	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	@Column(name = "PESO2", unique = false, nullable = true, insertable = true, updatable = true)
	public Double getPeso2() {
		return peso2;
	}

	public void setPeso2(Double peso2) {
		this.peso2 = peso2;
	}

	@Column(name = "TIPO_CARGA", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getTipoCarga() {
		return this.tipoCarga;
	}
	
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	@Column(name = "NUMERO_NF", unique = false, nullable = true, insertable = true, updatable = true, scale = 0)
	public Long getNumeroNf() {
		return this.numeroNf;
	}

	public void setNumeroNf(Long numeroNf) {
		this.numeroNf = numeroNf;
	}

	
	@Column(name = "TEMPO_ESPERA", unique = false, nullable = true, insertable = true, updatable = true)
	public String getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(String tempoEspera) {
		this.tempoEspera = tempoEspera;
	}
	
	@Column(name = "TEMPO_CARREGAMENTO", unique = false, nullable = true, insertable = true, updatable = true)
	public String getTempoCarregamento() {
		return tempoCarregamento;
	}

	public void setTempoCarregamento(String tempoCarregamento) {
		this.tempoCarregamento = tempoCarregamento;
	}
	
	@Column(name = "TEMPO_LONAGEM", unique = false, nullable = true, insertable = true, updatable = true)
	public String getTempoLonagem() {
		return tempoLonagem;
	}

	public void setTempoLonagem(String tempoLonagem) {
		this.tempoLonagem = tempoLonagem;
	}
	
	@Column(name = "TEMPO_PERMANENCIA", unique = false, nullable = true, insertable = true, updatable = true)
	public String getTempoPermanencia() {
		return tempoPermanencia;
	}

	public void setTempoPermanencia(String tempoPermanencia) {
		this.tempoPermanencia = tempoPermanencia;
	}

	@Column(name = "OBS_PORTARIA", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getObsPortaria() {
		return this.obsPortaria;
	}

	public void setObsPortaria(String obsPortaria) {
		this.obsPortaria = obsPortaria;
	}

	@Column(name = "OBS_FATURAMENTO", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getObsFaturamento() {
		return this.obsFaturamento;
	}

	public void setObsFaturamento(String obsFaturamento) {
		this.obsFaturamento = obsFaturamento;
	}
	
	
	@Column(name = "VEICULO_LIMPO", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getVeiculoLimpo() {
		return veiculoLimpo;
	}

	public void setVeiculoLimpo(String veiculoLimpo) {
		this.veiculoLimpo = veiculoLimpo;
	}
	
	@Column(name = "VESTIMENTA_ADEQUADA", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getVestimentaAdequada() {
		return vestimentaAdequada;
	}

	public void setVestimentaAdequada(String vestimentaAdequada) {
		this.vestimentaAdequada = vestimentaAdequada;
	}
	
	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "RG_MOTORISTA", unique = false, nullable = true, insertable = true, updatable = true)
	public String getRgMotorista() {
		return rgMotorista;
	}

	public void setRgMotorista(String rgMotorista) {
		this.rgMotorista = rgMotorista;
	}

	@Transient
	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	
	@Transient
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	@Transient
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	@Transient
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	@Transient
	public String getJustificativa() {
		return justificativa;
	}
	
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	@Transient
	@Override
	public Object getPrimaryKey() {
		return getIdMov();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMov == null) ? 0 : idMov.hashCode());
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
		MovPortaria other = (MovPortaria) obj;
		if (idMov == null) {
			if (other.idMov != null)
				return false;
		} else if (!idMov.equals(other.idMov))
			return false;
		return true;
	}
}