package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tripulante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private Integer dni;
	
	@ManyToOne
	private PlanDeVuelo planDeVuelo;
	
	public Tripulante(Long id, String nombre, String apellido, Integer dni, PlanDeVuelo planDeVuelo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.planDeVuelo = planDeVuelo;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Integer getDni() {
		return dni;
	}
	
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	public PlanDeVuelo getPlanDeVuelo() {
		return planDeVuelo;
	}
	
	public void setPlanDeVuelo(PlanDeVuelo planDeVuelo) {
		this.planDeVuelo = planDeVuelo;
	}
}