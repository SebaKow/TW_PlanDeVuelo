package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.protobuf.Timestamp;

@Entity
public class PVContieneV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Timestamp horaSalida;
	private Timestamp horaLLegada;
	
	public PVContieneV(Long id, Timestamp horaSalida, Timestamp horaLLegada) {
		this.id = id;
		this.horaSalida = horaSalida;
		this.horaLLegada = horaLLegada;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Timestamp getHoraSalida() {
		return horaSalida;
	}
	
	public void setHoraSalida(Timestamp horaSalida) {
		this.horaSalida = horaSalida;
	}
	
	public Timestamp getHoraLLegada() {
		return horaLLegada;
	}
	
	public void setHoraLLegada(Timestamp horaLLegada) {
		this.horaLLegada = horaLLegada;
	}
}