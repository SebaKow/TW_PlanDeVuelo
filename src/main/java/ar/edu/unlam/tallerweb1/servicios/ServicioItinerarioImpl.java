package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ItinerarioDao;
import ar.edu.unlam.tallerweb1.modelo.Itinerario;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

@Service("ServicioItinerario")
@Transactional
public class ServicioItinerarioImpl implements ServicioItinerario {
	
	@Inject
	private ItinerarioDao itinerarioDao;

	/*private Boolean validacion1() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	@Override
	public List<Vuelo> listarVuelosDePlan(Long idObtenido) {
		return itinerarioDao.listarVuelosDePlan(idObtenido);
	}
	
	@Override
	public void agregarItinerario(PlanDeVuelo plan, Vuelo vuelo) {
		Itinerario itinerario = new Itinerario();
		itinerario.setPlandevuelo(plan);
		List<Vuelo> listaVuelos = new ArrayList<>();
		listaVuelos.add(vuelo);
		itinerario.setVuelos(listaVuelos);
		
		itinerario.setDespegueEstimado(plan.getFecha());
		LocalDateTime l = dateALocalDateTime(plan.getFecha());
		LocalDateTime duracion = dateALocalDateTime(vuelo.getDuracion());
		l.plusHours(duracion.getHour());
		l.plusMinutes(duracion.getMinute());
		
		Date horaAterrizajeEstimado = Date.from(l.atZone(ZoneId.systemDefault()).toInstant());
		itinerario.setAterrizajeEstimado(horaAterrizajeEstimado);
		
		itinerarioDao.agregarItinerario(itinerario);
	}
	
	@Override
	public void eliminarVueloDePlan(PlanDeVuelo plan, Vuelo vuelo) {
		itinerarioDao.eliminarVueloDePlan(plan, vuelo);
	}
	
	public LocalDateTime dateALocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}