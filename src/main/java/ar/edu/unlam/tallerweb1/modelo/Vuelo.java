package ar.edu.unlam.tallerweb1.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	public Vuelo() {
		
	}

	public Vuelo(Long id, String origen, String destino, String duracionString, Date duracion, Boolean estado) throws ParseException {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.duracionString = duracionString;
		this.duracion = stringAHora(duracionString);
		this.estado = estado;
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
	
	public String getDuracionString() {
		return duracionString;
	}

	public void setDuracionString(String duracionString) {
		this.duracionString = duracionString;
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
	
	public void setDuracionParse() throws ParseException {
		this.duracion = stringAHora(this.duracionString);
	}

	public Date stringAHora(String string) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date = sdf.parse(string);
		return date;
	}
}