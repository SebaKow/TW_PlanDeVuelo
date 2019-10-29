package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Calendar;
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

	@Override
	public List<Vuelo> listarVuelosDePlan(Long idObtenido) {
		return itinerarioDao.listarVuelosDePlan(idObtenido);
	}
	
	@Override
	public List<Itinerario> listarItinerariosDePlan(Long id){
		return itinerarioDao.listarItinerariosDePlan(id);
	}
	
	@Override
	public void agregarItinerario(PlanDeVuelo plan, Vuelo vuelo) throws Exception  {
		Itinerario itinerario = calcularHoraDespegueYAterrizaje(vuelo, plan);
		validaciones(plan, vuelo);
		
		itinerarioDao.agregarItinerario(itinerario);
	}
	
	private void validaciones(PlanDeVuelo plan, Vuelo vuelo) throws Exception {
//		validarQueElOrigenSeaIgualAlDestinoAnterior(plan, vuelo);
		validarQueElVueloAAgregarNoSupereLas8HorasDeTiempoDeVuelo(plan,vuelo);
	}
	
	private void validarQueElVueloAAgregarNoSupereLas8HorasDeTiempoDeVuelo(PlanDeVuelo plan, Vuelo vuelo) throws Exception {
		List<Vuelo> listaDeVuelosEnPlan = listarVuelosDePlan(plan.getId());
		Calendar cal = Calendar.getInstance();
		Date fechaVacia = new Date(1970,01,01);
		fechaVacia.setHours(0);
		fechaVacia.setMinutes(0);
		cal.setTime(fechaVacia);
		for (Vuelo vuelo2 : listaDeVuelosEnPlan) {
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + vuelo2.getDuracion().getHours());
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + vuelo2.getDuracion().getMinutes());
		}
		
		if(cal.get(Calendar.HOUR)>1 && cal.get(Calendar.MINUTE)>=1) {
			throw new Exception("El tiempo de vuelo no puede superar las 8 horas");
		}
	}

	private void validarQueElOrigenSeaIgualAlDestinoAnterior(PlanDeVuelo plan, Vuelo vuelo) throws Exception {
		List<Vuelo> listaDeVuelosEnPlan = listarVuelosDePlan(plan.getId());
		if(listaDeVuelosEnPlan != null) {
			if(!listaDeVuelosEnPlan.get(listaDeVuelosEnPlan.size() - 1).getDestino().equals(vuelo.getOrigen())) {
			throw new Exception("El origen del vuelo a agregar no coincide con el destino del vuelo anterior.");
			}
		}
	}
	
	public Itinerario calcularHoraDespegueYAterrizaje(Vuelo vuelo, PlanDeVuelo plan) throws Exception {
		Calendar cal = Calendar.getInstance();
		Date fechaPlan = plan.getFecha();
		Itinerario itinerario = new Itinerario();
		List<Vuelo> listaVuelos = new ArrayList<>();
		
		List<Itinerario> itinerariosEnPlan = listarItinerariosDePlan(plan.getId());
		if(itinerariosEnPlan.size() == 0) {
			cal.setTime(fechaPlan);
			Date fechaCalDespegue = cal.getTime();
			itinerario.setDespegueEstimado(fechaCalDespegue);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + vuelo.getDuracion().getHours());
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + vuelo.getDuracion().getMinutes());
			Date fechaCalAterrizaje = cal.getTime();
			itinerario.setAterrizajeEstimado(fechaCalAterrizaje);
		} else if(itinerariosEnPlan.size() >= 1 && itinerariosEnPlan.size() < 4) {
			cal.setTime(itinerariosEnPlan.get(itinerariosEnPlan.size() - 1).getAterrizajeEstimado());
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 30);
			Date fechaCalDespegue = cal.getTime();
			itinerario.setDespegueEstimado(fechaCalDespegue);
			
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + vuelo.getDuracion().getHours());
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + vuelo.getDuracion().getMinutes());
			Date fechaCalAterrizaje = cal.getTime();
			itinerario.setAterrizajeEstimado(fechaCalAterrizaje);
		} else if(itinerariosEnPlan.size() >= 4 && itinerariosEnPlan.size() < 6) {
			cal.setTime(itinerariosEnPlan.get(itinerariosEnPlan.size() - 1).getAterrizajeEstimado());
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
			Date fechaCalDespegue = cal.getTime();
			itinerario.setDespegueEstimado(fechaCalDespegue);
			
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + vuelo.getDuracion().getHours());
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + vuelo.getDuracion().getMinutes());
			Date fechaCalAterrizaje = cal.getTime();
			itinerario.setAterrizajeEstimado(fechaCalAterrizaje);
		} else {
			throw new Exception("No se puede agregar m�s de 6 vuelos a un plan.");
		}
		
		itinerario.setPlandevuelo(plan);
		listaVuelos.add(vuelo);
		itinerario.setVuelos(listaVuelos);
		return itinerario;
	}
	
	@Override
	public void eliminarVueloDePlan(PlanDeVuelo plan, Vuelo vuelo) {
		itinerarioDao.eliminarVueloDePlan(plan, vuelo);
	}
}