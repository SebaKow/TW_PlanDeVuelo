package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

	/*private Boolean validacion1() {
		// TODO Auto-generated method stub
		return null;
	}*/
	private Boolean validarQueNoAgreguenDosVecesElMismoVuelo(List<Vuelo>listaDeVuelos, Vuelo vuelo) {
		Boolean paso=false;
		for (Vuelo vueloDeLista : listaDeVuelos) {
			if(vueloDeLista.getId()==vuelo.getId()) {
				paso=false;
			}else {
				paso=true;
			}
		}
		return paso;		
	}
	@Override
	public List<Vuelo> listarVuelosDePlan(Long idObtenido) {
		return itinerarioDao.listarVuelosDePlan(idObtenido);
	}
	
	@Override
	public List<Itinerario> listarItinerariosDePlan(Long id){
		return itinerarioDao.listarItinerariosDePlan(id);
	}
	
	@Override
	public void agregarItinerario(PlanDeVuelo plan, Vuelo vuelo) throws Exception {
		Itinerario itinerario = calcularHoraDespegueYAterrizaje(vuelo, plan);
		itinerario.setPlandevuelo(plan);
		validaciones(plan,vuelo);
		itinerarioDao.agregarItinerario(itinerario);
		
		
	}
	
	private void validaciones(PlanDeVuelo plan, Vuelo vuelo) throws Exception {
		validarAeropuertoOrigenSeaIgualAlDestinoAnterior(plan,vuelo);
//		validarQueElVueloAAgregarNoSupereLas8HorasDeTiempoDeVuelo
	}
	private void validarAeropuertoOrigenSeaIgualAlDestinoAnterior(PlanDeVuelo plan, Vuelo vuelo) throws Exception {
		List<Vuelo>listaDeVuelosEnPlan = listarVuelosDePlan(plan.getId());
		if(!listaDeVuelosEnPlan.get(listaDeVuelosEnPlan.size()-1).getDestino().equals(vuelo.getOrigen())) {
			throw new Exception("El aeropuerto de salida del nuevo vuelo es distinto al aeropuerto de llegada del vuelo anterior");
		}
	}
	@Override
	public void eliminarVueloDePlan(PlanDeVuelo plan, Vuelo vuelo) {
		itinerarioDao.eliminarVueloDePlan(plan, vuelo);
	}
	
	public LocalDateTime dateALocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public Itinerario calcularHoraDespegueYAterrizaje(Vuelo vuelo,PlanDeVuelo plan) throws Exception {
		Calendar cal = Calendar.getInstance();
		Date fechaPlan = plan.getFecha();
		Itinerario itinerario = new Itinerario();
		List<Vuelo>listaVuelos = new ArrayList<>();
		
		List<Itinerario> itinerariosEnPlan = listarItinerariosDePlan(plan.getId());
		if(itinerariosEnPlan.size()==0) {
			cal.setTime(fechaPlan);
			Date fechaCalDespegue = cal.getTime();
			itinerario.setDespegueEstimado(fechaCalDespegue);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)+vuelo.getDuracion().getHours());
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+vuelo.getDuracion().getMinutes());
			Date fechaCalAterrizaje = cal.getTime();
			itinerario.setAterrizajeEstimado(fechaCalAterrizaje);
		}else if(itinerariosEnPlan.size()>=1 && itinerariosEnPlan.size() < 4) {
			cal.setTime(itinerariosEnPlan.get(itinerariosEnPlan.size()-1).getAterrizajeEstimado());
			cal.set(Calendar.MINUTE,cal.get(Calendar.MINUTE)+30);
			Date fechaCalDespegue = cal.getTime();
			itinerario.setDespegueEstimado(fechaCalDespegue);
			
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)+vuelo.getDuracion().getHours());
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+vuelo.getDuracion().getMinutes());
			Date fechaCalAterrizaje = cal.getTime();
			itinerario.setAterrizajeEstimado(fechaCalAterrizaje);
		}else if(itinerariosEnPlan.size()>=4 && itinerariosEnPlan.size() < 6) {
			cal.setTime(itinerariosEnPlan.get(itinerariosEnPlan.size()-1).getAterrizajeEstimado());
			cal.set(Calendar.HOUR,cal.get(Calendar.HOUR)+1);
			Date fechaCalDespegue = cal.getTime();
			itinerario.setDespegueEstimado(fechaCalDespegue);
			
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)+vuelo.getDuracion().getHours());
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+vuelo.getDuracion().getMinutes());
			Date fechaCalAterrizaje = cal.getTime();
			itinerario.setAterrizajeEstimado(fechaCalAterrizaje);
		}else {
			throw new Exception("Ese vuelo ya esta agregado");
		}
		listaVuelos.add(vuelo);
		itinerario.setVuelos(listaVuelos);
		return itinerario;
	}
}