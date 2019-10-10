package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Vuelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String origen;
	private String destino;
	@DateTimeFormat(iso = ISO.TIME)
	@Temporal(TemporalType.TIME)
	private Date duracion;
	private Boolean estado;
	
	@ManyToMany(mappedBy = "vuelos")
	private List<Itinerario> itinerarios;
	
	public Vuelo() {
		
	}

	public Vuelo(Long id, String origen, String destino, Date duracion) {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.duracion = duracion;
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

	public Date getDuracion() {
		return duracion;
	}

	public void setDuracion(Date duracion) {
		this.duracion = duracion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public List<Itinerario> getItinerarios() {
		return itinerarios;
	}

	public void setItinerarios(List<Itinerario> itinerarios) {
		this.itinerarios = itinerarios;
	}
	
	
}