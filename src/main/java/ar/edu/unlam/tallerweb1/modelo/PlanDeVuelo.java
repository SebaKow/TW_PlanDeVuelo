package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PlanDeVuelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Avion avion;
	
	public PlanDeVuelo() {
		
	}
	
	public PlanDeVuelo(Long id, Avion avion) {
		this.id = id;
		this.avion = avion;
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
}