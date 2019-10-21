package ar.edu.unlam.tallerweb1.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.cglib.core.Local;
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
	
//	LocalDateTime localdate = LocalDateTime.parse("");
	
	public Vuelo() {
		
	}

	public Vuelo(Long id, String origen, String destino) throws ParseException {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.duracion = stringAHora(duracionString);
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
	
//	public LocalDateTime dateALocalDateTime(Date date) {
//		LocalDateTime l = LocalDateTime.parse(date.toString());
//		l.plusHours(date.getHours());
//		l.plusMinutes(date.getMinute.0s());
//		return l;
//	}
}