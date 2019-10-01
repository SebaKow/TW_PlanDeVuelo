package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PVContieneV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date despegueEstimado;
	private Date despegue;
	private Date aterrizajeEstimado;
	private Date aterrizaje;
	
	@ManyToOne
	private PlanDeVuelo plandevuelo;
	
	@ManyToOne
	private Vuelo vuelo;
	
	public PVContieneV() {
		
	}
	
	public PVContieneV(Long id, Date despegueEstimado, Date despegue, Date aterrizajeEstimado, Date aterrizaje, PlanDeVuelo plandevuelo, Vuelo vuelo) {
		this.id = id;
		this.despegueEstimado = despegueEstimado;
		this.despegue = despegue;
		this.aterrizajeEstimado = aterrizajeEstimado;
		this.aterrizaje = aterrizaje;
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

	public Date getDespegue() {
		return despegue;
	}

	public void setDespegue(Date despegue) {
		this.despegue = despegue;
	}

	public Date getAterrizajeEstimado() {
		return aterrizajeEstimado;
	}

	public void setAterrizajeEstimado(Date aterrizajeEstimado) {
		this.aterrizajeEstimado = aterrizajeEstimado;
	}

	public Date getAterrizaje() {
		return aterrizaje;
	}

	public void setAterrizaje(Date aterrizaje) {
		this.aterrizaje = aterrizaje;
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