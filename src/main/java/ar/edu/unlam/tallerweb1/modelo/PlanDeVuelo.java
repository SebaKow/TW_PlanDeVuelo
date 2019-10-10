package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class PlanDeVuelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	
	@ManyToOne
	private Avion avion;
	
	@ManyToMany(mappedBy = "planesDeVuelo")
	private List<Tripulante> tripulantes;
	
	public PlanDeVuelo() {
		
	}
	
	public PlanDeVuelo(Long id, String descripcion, Avion avion) {
		this.id = id;
		this.descripcion = descripcion;
		this.avion = avion;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Avion getAvion() {
		return avion;
	}
	
	public void setAvion(Avion avion) {
		this.avion = avion;
	}
}