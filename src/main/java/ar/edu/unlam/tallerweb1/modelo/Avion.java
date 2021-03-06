package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Avion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer capacidadTripulantes;
	
	public Avion() {
		
	}
	
	public Avion(Long id, Integer capacidadTripulantes) {
		this.id = id;
		this.capacidadTripulantes = capacidadTripulantes;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getCapacidadTripulantes() {
		return capacidadTripulantes;
	}
	
	public void setCapacidadTripulantes(Integer capacidadTripulantes) {
		this.capacidadTripulantes = capacidadTripulantes;
	}
}