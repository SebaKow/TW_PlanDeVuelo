package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Itinerario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date despegueEstimado;
	private Date despegueReal;
	private Date aterrizajeEstimado;
	private Date aterrizajeReal;
	
	@ManyToOne
	private PlanDeVuelo plandevuelo;
	
	@ManyToOne
	private Vuelo vuelo;
	
	public Itinerario() {
		
	}
	
	public Itinerario(Long id, Date despegueEstimado, Date despegueReal, Date aterrizajeEstimado, Date aterrizajeReal, 
			PlanDeVuelo plandevuelo, Vuelo vuelo) {
		this.id = id;
		this.despegueEstimado = despegueEstimado;
		this.despegueReal = despegueReal;
		this.aterrizajeEstimado = aterrizajeEstimado;
		this.aterrizajeReal = aterrizajeReal;
		this.plandevuelo = plandevuelo;
		this.vuelo = vuelo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDespegueEstimado() {
		return despegueEstimado;
	}

	public void setDespegueEstimado(Date despegueEstimado) {
		this.despegueEstimado = despegueEstimado;
	}

	public Date getDespegueReal() {
		return despegueReal;
	}

	public void setDespegueReal(Date despegueReal) {
		this.despegueReal = despegueReal;
	}

	public Date getAterrizajeEstimado() {
		return aterrizajeEstimado;
	}

	public void setAterrizajeEstimado(Date aterrizajeEstimado) {
		this.aterrizajeEstimado = aterrizajeEstimado;
	}

	public Date getAterrizajeReal() {
		return aterrizajeReal;
	}

	public void setAterrizajeReal(Date aterrizajeReal) {
		this.aterrizajeReal = aterrizajeReal;
	}

	public PlanDeVuelo getPlandevuelo() {
		return plandevuelo;
	}

	public void setPlandevuelo(PlanDeVuelo plandevuelo) {
		this.plandevuelo = plandevuelo;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
}