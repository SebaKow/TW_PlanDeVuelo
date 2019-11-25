package ar.edu.unlam.tallerweb1.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private String fechaString;
	private Date fecha;
	private Boolean estado;
	
	@ManyToOne
	private Avion avion;

	@ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.MERGE)
	private List<Tripulante> tripulantes;
	
	public PlanDeVuelo() {
		
	}
	
	public PlanDeVuelo(Long id, String descripcion, String fechaString, Date fecha, Boolean estado, Avion avion, List<Tripulante> tripulantes) throws ParseException {
		this.id = id;
		this.descripcion = descripcion;
		this.fechaString = fechaString;
		this.fecha = stringAHora(fechaString);
		this.estado = estado;
		this.avion = avion;
		this.tripulantes = tripulantes;
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

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public List<Tripulante> getTripulantes() {
		return tripulantes;
	}

	public void setTripulantes(List<Tripulante> tripulantes) {
		this.tripulantes = tripulantes;
	}

	public void setDuracionParse() throws ParseException {
		this.fecha = stringAHora(this.fechaString);
	}

	public Date stringAHora(String string) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = sdf.parse(string);
		return date;
	}
}