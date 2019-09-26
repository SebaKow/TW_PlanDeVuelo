package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PVContieneV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date horaSalida;
	private Date horaLlegada;
	
	public PVContieneV() {
		
	}
	
	public PVContieneV(Long id, Date horaSalida, Date horaLLegada) {
		this.id = id;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLLegada;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getHoraSalida() {
		return horaSalida;
	}
	
	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}
	
	public Date getHoraLLegada() {
		return horaLlegada;
	}
	
	public void setHoraLLegada(Date horaLLegada) {
		this.horaLlegada = horaLLegada;
	}
}