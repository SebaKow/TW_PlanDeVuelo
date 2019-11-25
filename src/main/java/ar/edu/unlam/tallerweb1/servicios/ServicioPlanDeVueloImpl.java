package ar.edu.unlam.tallerweb1.servicios;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ItinerarioDao;
import ar.edu.unlam.tallerweb1.dao.PlanDeVueloDao;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Tripulante;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

@Service("ServicioPlanDeVuelo")
@Transactional
public class ServicioPlanDeVueloImpl implements ServicioPlanDeVuelo {

	@Inject
	PlanDeVueloDao planDeVueloDao;
	@Inject
	ItinerarioDao itinerarioDao;
	
	@Override
	public List<PlanDeVuelo> listarPlanesDeVuelo() {
		return planDeVueloDao.listarPlanesDeVuelo();
	}
	
	@Override
	public PlanDeVuelo consultarPlanDeVueloId(Long id) {
		return planDeVueloDao.consultarPlanDeVueloId(id);
	}
	
	@Override
	public void agregarPlanDeVuelo(PlanDeVuelo planDeVuelo) {
		planDeVueloDao.agregarPlanDeVuelo(planDeVuelo);
	}
	
	@Override
	public void editarPlanDeVuelo(PlanDeVuelo planDeVuelo) {
		planDeVueloDao.editarPlanDeVuelo(planDeVuelo);
	}
	
	@Override
	public void eliminarPlanDeVuelo(PlanDeVuelo planDeVuelo) {
		planDeVuelo.setEstado(false);
		planDeVueloDao.eliminarPlanDeVuelo(planDeVuelo);
	}
	
	@Override
	public void agregarTripulanteAPlan(Tripulante tripulante, PlanDeVuelo plan) throws Exception {
		validaciones(plan,tripulante);
		List<Tripulante> tripulantesDelPlan = plan.getTripulantes();
		tripulantesDelPlan.add(tripulante);
		plan.setTripulantes(tripulantesDelPlan);
		planDeVueloDao.agregarTripulanteAPlan(plan);
	}

	@Override
	public List<Tripulante> listarTripulantesEnPlan(PlanDeVuelo plan) {
		return planDeVueloDao.listarTripulantesEnPlan(plan);
	}
	
	private void validaciones(PlanDeVuelo plan, Tripulante tripulante) throws Exception {
		validarQueNoSeAgregueDosVecesElMismoTripulante(plan,tripulante);
		validarQueElTripulanteNoSupereLas13HorasDeTSV(plan, tripulante);
	}

	private void validarQueNoSeAgregueDosVecesElMismoTripulante(PlanDeVuelo plan, Tripulante tripulante) throws Exception {
		List<Tripulante> tripulantesDelPlan = plan.getTripulantes();
		for (Tripulante tripulante2 : tripulantesDelPlan) {
			if(tripulante2.getId() == tripulante.getId()) {
				throw new Exception("El tripulante ya pertenece a este plan");
			}
		}
	}
	
	public void validarQueElTripulanteNoSupereLas13HorasDeTSV(PlanDeVuelo plan, Tripulante tripulante) throws Exception {
		Calendar cal = calcularTSVDeUnPlan(plan);		
		
		List<PlanDeVuelo> planes = planDeVueloDao.ListarPlanesPorTripulanteYFecha(tripulante, plan.getFecha(), 1);
		
		for (PlanDeVuelo planDeVuelo : planes) {
			Calendar cal2 = calcularTSVDeUnPlan(planDeVuelo);
			cal.set(Calendar.HOUR,cal.get(Calendar.HOUR) + cal2.get(Calendar.HOUR));
			cal.set(Calendar.MINUTE,cal.get(Calendar.MINUTE) + cal2.get(Calendar.MINUTE));
		}
		
		if(cal.get(Calendar.HOUR) == 13 && cal.get(Calendar.MINUTE) > 0) {
			throw new Exception("El tiempo de servicio de vuelo no puede superar las 13 horas.");
		}
		
		if(cal.get(Calendar.HOUR) > 13 ) {
			throw new Exception("El tiempo de servicio de vuelo no puede superar las 13 horas.");
		}
	}

	@Override
	public void eliminarTripulanteDePlan(Tripulante tripulante, PlanDeVuelo plan) {
		List<Tripulante> tripulantesDelPlan = plan.getTripulantes();
		tripulantesDelPlan.remove(tripulante);
		plan.setTripulantes(tripulantesDelPlan);
		planDeVueloDao.eliminarTripulanteDePlan(plan);
		
	}
	
	public Calendar calcularTSVDeUnPlan(PlanDeVuelo plan) {
		List<Vuelo> listaDeVuelosEnPlan = itinerarioDao.listarVuelosDePlan(plan.getId());
		Calendar cal = Calendar.getInstance();
		Date fechaVacia = new Date();
		fechaVacia.setHours(0);
		fechaVacia.setMinutes(0);
		cal.setTime(fechaVacia);
		for (Vuelo vuelo2 : listaDeVuelosEnPlan) {
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + vuelo2.getDuracion().getHours());
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + vuelo2.getDuracion().getMinutes());
		}
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 30);
		
		if(listaDeVuelosEnPlan.size() == 1){
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 30);
		}
		
		if(listaDeVuelosEnPlan.size() == 2){
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
		}
		
		if(listaDeVuelosEnPlan.size() == 3){
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 30);
		}
		
		if(listaDeVuelosEnPlan.size() == 4){
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 2);
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 30);
		}
		
		if(listaDeVuelosEnPlan.size() == 5){
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 3);
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 30);
		}
		
		return cal;
	}
}