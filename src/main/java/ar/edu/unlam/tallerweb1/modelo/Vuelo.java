package ar.edu.unlam.tallerweb1.modelo;

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
	private PVContieneV pvcontienev;

	public Vuelo(Long id, String origen, String destino, PVContieneV pvcontienev) {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.pvcontienev = pvcontienev;
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

	public PVContieneV getPvcontienev() {
		return pvcontienev;
	}

	public void setPvcontienev(PVContieneV pvcontienev) {
		this.pvcontienev = pvcontienev;
	}
}