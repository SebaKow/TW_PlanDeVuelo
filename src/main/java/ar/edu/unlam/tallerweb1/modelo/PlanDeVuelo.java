package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class PlanDeVuelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Avion avion;
	
	@ManyToOne
	private PVContieneV pvcontienev;
	
	public PlanDeVuelo() {
		
	}
	
	public PlanDeVuelo(Long id, Avion avion, PVContieneV pvcontienev) {
		this.id = id;
		this.avion = avion;
		this.pvcontienev = pvcontienev;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Avion getAvion() {
		return avion;
	}
	
	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public PVContieneV getPvcontienev() {
		return pvcontienev;
	}

	public void setPvcontienev(PVContieneV pvcontienev) {
		this.pvcontienev = pvcontienev;
	}
}