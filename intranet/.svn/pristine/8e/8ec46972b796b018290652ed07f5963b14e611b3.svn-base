package br.com.sp.intranet.model.portaria;

import java.sql.Time;
import java.util.Date;

public class FaixaHorarioPortaria implements Comparable<FaixaHorarioPortaria> {
	private Date dia;
	private Time horaInicio;
	private Time horaFim;
	private Long quantidade;
	private int turno;
	
	public Date getDia() {
		return dia;
	}
	
	public void setDia(Date dia) {
		this.dia = dia;
	}
	
	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}

	public Long getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	@Override
	public int compareTo(FaixaHorarioPortaria o) {
		return this.dia.compareTo(o.dia);
	}
}