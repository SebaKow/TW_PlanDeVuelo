package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vuelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String origen;
	private String destino;
	
	@ManyToOne
	private PVContieneV PVContieneV;

	public Vuelo(Long id, String origen, String destino, ar.edu.unlam.tallerweb1.modelo.PVContieneV pVContieneV) {
		super();
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		PVContieneV = pVContieneV;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public PVContieneV getPVContieneV() {
		return PVContieneV;
	}

	public void setPVContieneV(PVContieneV pVContieneV) {
		PVContieneV = pVContieneV;
	}
	
	
	
	

}
