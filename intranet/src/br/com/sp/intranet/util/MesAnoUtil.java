package br.com.sp.intranet.util;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MesAnoUtil {

	public static final int INICIO_ANO = 2017;
	
	private MesEnum mes;
	private Integer ano;
	
	public static List<Integer> anos;
	public static MesEnum [] meses;
	
	private int inicio;
	private int atual;
	
	private String mesAno;
	
	public MesAnoUtil() {}
	
	public MesAnoUtil(LocalDate data) {
		ano = data.getYear();
		mes = MesEnum.valueOf(data.getMonth().getDisplayName(TextStyle.SHORT, new Locale("pt", "BR")).toUpperCase());
	}
	
	public MesAnoUtil(MesEnum mes, Integer ano, int inicio, int atual) {
		this.mes = mes;
		this.ano = ano;
		this.inicio = inicio;
		this.atual = atual;
	}

	public static List<Integer> listarAnos(int inicio, int atual){
		anos = new ArrayList<>();
		
		for (int i = inicio; i <= atual ; i++) {
			anos.add(i);
		}
		return anos;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getAtual() {
		return atual;
	}

	public void setAtual(int atual) {
		this.atual = atual;
	}

	public List<Integer> getAnos() {
		anos = this.listarAnos(INICIO_ANO, LocalDate.now().getYear());
		return anos;
	}

	public void setAnos(List<Integer> anos) {
		this.anos = anos;
	}
	
	public MesEnum getMes() {
		return mes;
	}

	public void setMes(MesEnum mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public static MesEnum[] getMeses() {
		meses = MesEnum.values();
		return meses;
	}

	public static void setMeses(MesEnum[] meses) {
		MesAnoUtil.meses = meses;
	}

	public String getMesAno() {
		mesAno = getMes().getMes() + "/" + getAno().toString();
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
}