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

//	@Override
//	public List<Vuelo> listarVuelosDePlan(Long idObtenido) {
//		return itinerarioDao.listarVuelosDePlan(idObtenido);
//	}
	
	@Override
	public List<Itinerario> listarItinerariosDePlan(Long id){
		return itinerarioDao.listarItinerariosDePlan(id);
	}
	
	@Override
	public void agregarItinerario(PlanDeVuelo plan, Vuelo vuelo) throws Exception {
		List<Itinerario> itinerarios = listarItinerariosDePlan(plan.getId());
		Itinerario itinerario = calcularHoraDespegueYAterrizaje(vuelo, plan);
		itinerarios.add(itinerario);
		validaciones(itinerarios);
		itinerarioDao.agregarItinerario(itinerario);
	}
	
	private void validaciones(List<Itinerario> itinerarios) throws Exception {
		validarQueElOrigenSeaIgualAlDestinoAnterior(itinerarios);
		validarQueElVueloAAgregarNoSupereLas8HorasDeTV(itinerarios);
	    validarQueElVueloAAgregarNoSupereLas13HorasDeTSV(itinerarios);
	}
	
	private void validarQueElOrigenSeaIgualAlDestinoAnterior(List<Itinerario> itinerarios) throws Exception {
		
		
		if(itinerarios.size()> 1 ) {
			if(!itinerarios.get(itinerarios.size()-2).getVuelo().getDestino().equals(itinerarios.get(itinerarios.size()-1).getVuelo().getOrigen())) {
				throw new Exception("El origen del vuelo a agregar no coincide con el destino del vuelo anterior");
			}
		}
	}
	
	private void validarQueElVueloAAgregarNoSupereLas8HorasDeTV(List<Itinerario> itinerarios) throws Exception {
		List<Vuelo> listaDeVuelosEnPlan = new ArrayList<>();
		
		for (Itinerario itinerario : itinerarios) {
			listaDeVuelosEnPlan.add(itinerario.getVuelo());
		}
		
		Calendar cal = Calendar.getInstance();
		Date fechaVacia = new Date();
		fechaVacia.setHours(0);
		fechaVacia.setMinutes(0);
		cal.setTime(fechaVacia);
		for (Vuelo vuelo : listaDeVuelosEnPlan) {
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + vuelo.getDuracion().getHours());
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + vuelo.getDuracion().getMinutes());
		}
		
		if(cal.get(Calendar.HOUR) == 8 && cal.get(Calendar.MINUTE) > 0) {
			throw new Exception("El tiempo de vuelo no puede superar las 8 horas.");
		}
		
		if(cal.get(Calendar.HOUR) > 8 ) {
			throw new Exception("El tiempo de vuelo no puede superar las 8 horas.");
		}
	}
	
	private void validarQueElVueloAAgregarNoSupereLas13HorasDeTSV(List<Itinerario> itinerarios) throws Exception {
		
		Date horaFinal = itinerarios.get(itinerarios.size()-1).getAterrizajeEstimado();
		Date horaInicial = itinerarios.get(0).getDespegueEstimado();
		long calculo = horaFinal.getTime()-horaInicial.getTime();
		long calculoEnMinutos = (int)(calculo/60000);

		if(calculoEnMinutos >= 690 ) {
			throw new Exception("El tiempo de servicio no puede superar las 11.30 horas.");
		}
	}
	
	public Itinerario calcularHoraDespegueYAterrizaje(Vuelo vuelo, PlanDeVuelo plan) throws Exception {
		Calendar cal = Calendar.getInstance();
		Date fechaPlan = plan.getFecha();
		Itinerario itinerario = new Itinerario();
		Vuelo vuelo2 = new Vuelo();
		
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
			throw new Exception("No se permite agregar una cantidad mayor a 6 vuelos.");
		}
		
		itinerario.setPlandevuelo(plan);
		itinerario.setVuelo(vuelo);
		return itinerario;
	}
	
	@Override
	public void eliminarVueloDePlan(PlanDeVuelo plan, Long idItinerario) {
		itinerarioDao.eliminarVueloDePlan(plan, idItinerario);
	}
}