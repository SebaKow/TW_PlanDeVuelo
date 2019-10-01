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
	private String email;
	private String password;
	private Boolean esAdmin;
	
	@ManyToOne
	private PlanDeVuelo plandevuelo;
	
	public Tripulante() {
	
	}
	
	public Tripulante(Long id, String nombre, String apellido, Integer dni, String email,
			       String password, Boolean esAdmin, PlanDeVuelo plandevuelo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.password = password;
		this.esAdmin = esAdmin;
		this.plandevuelo = plandevuelo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getEsAdmin() {
		return esAdmin;
	}
	
	public void setEsAdmin(Boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public PlanDeVuelo getPlandevuelo() {
		return plandevuelo;
	}

	public void setPlandevuelo(PlanDeVuelo plandevuelo) {
		this.plandevuelo = plandevuelo;
	}
}