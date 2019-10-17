package ar.edu.unlam.tallerweb1.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private String duracionString;
	private Date duracion;
	private Boolean estado;
	@ManyToMany(mappedBy = "vuelos")
	private List<Itinerario> itinerarios;
	
	public Vuelo() {
		
	}

	public Vuelo(Long id, String origen, String destino) throws ParseException {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.duracion = stringAHora(duracionString);
//		Integer horaMilitar = 130;
//		Integer minutos = horaMilitar.toString().substring(horaMilitar.toString().length() - 1);
//		if(horaMilitar > 999)
//		Integer hora = horaMilitar.toString().substring(0,1);
//		else
//			Integer hora = horaMilitar.toString()[0];
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
	
	public String getDuracionString() {
		return duracionString;
	}

	public void setDuracionString(String duracionString) {
		this.duracionString = duracionString;
	}
	
	public void setDuracionParse() throws ParseException {
		this.duracion = stringAHora(this.duracionString);
	}

	public Date stringAHora(String string) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Date date = sdf.parse(string);
		return date;
	}
}