package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ItinerarioDao;
import ar.edu.unlam.tallerweb1.dao.PlanDeVueloDao;
import ar.edu.unlam.tallerweb1.modelo.Itinerario;
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
	
	@Inject
	ServicioItinerario servicioItinerario;

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
		List<Itinerario> itinerarios = servicioItinerario.listarItinerariosDePlan(plan.getId());
		validaciones(plan, tripulante, itinerarios);
		List<Tripulante> tripulantesDelPlan = plan.getTripulantes();
		tripulantesDelPlan.add(tripulante);
		plan.setTripulantes(tripulantesDelPlan);
		planDeVueloDao.agregarTripulanteAPlan(plan);
	}

	@Override
	public List<Tripulante> listarTripulantesEnPlan(PlanDeVuelo plan) {
		return planDeVueloDao.listarTripulantesEnPlan(plan);
	}

	private void validaciones(PlanDeVuelo plan, Tripulante tripulante, List<Itinerario> itinerarios) throws Exception {
		validarQueNoSeAgregueDosVecesElMismoTripulante(plan,tripulante);
		validarQueElTripulanteNoSupereLas8HorasDeTV(itinerarios, tripulante);
		validarQueElTripulanteNoSupereLas13HorasDeTSV(itinerarios, tripulante);
	}

	public void validarQueNoSeAgregueDosVecesElMismoTripulante(PlanDeVuelo plan, Tripulante tripulante) throws Exception {
		List<Tripulante> tripulantesDelPlan = plan.getTripulantes();
		for (Tripulante tripulante2 : tripulantesDelPlan) {
			if (tripulante2.getId() == tripulante.getId()) {
				throw new Exception("El tripulante ya pertenece a este plan de vuelo");
			}
		}
	}

	public void validarQueElTripulanteNoSupereLas8HorasDeTV(List<Itinerario> itinerarios, Tripulante tripulante) throws Exception {
		long calculoTotalEnMinutos = 0;
		PlanDeVuelo plan = itinerarios.get(0).getPlandevuelo();
		Itinerario primerItinerario = itinerarios.get(0);

		calculoTotalEnMinutos += calcularTVDeUnPlan(plan);

		List<PlanDeVuelo> planes = planDeVueloDao.listarPlanesPorTripulanteYFecha(tripulante, primerItinerario.getDespegueEstimado(), 1);
		for (PlanDeVuelo planDeVuelo : planes) {
			calculoTotalEnMinutos += calcularTVDeUnPlan(planDeVuelo);
		}

		if (calculoTotalEnMinutos >= 480) {
			throw new Exception("El tiempo de vuelo del tripulante no puede superar las 8 horas en 24 horas consecutivas.");
		}
	}

	public void validarQueElTripulanteNoSupereLas13HorasDeTSV(List<Itinerario> itinerarios, Tripulante tripulante) throws Exception {
		long calculoTotalEnMinutos = 0;
		PlanDeVuelo plan = itinerarios.get(0).getPlandevuelo();
		
		calculoTotalEnMinutos +=calcularTSVDeUnPlan(plan);
		
		List<PlanDeVuelo> planes = planDeVueloDao.listarPlanesPorTripulanteYFecha(tripulante, plan.getFecha(), 1);
		for (PlanDeVuelo planDeVuelo : planes) {
			calculoTotalEnMinutos += calcularTSVDeUnPlan(planDeVuelo);
		}
		
		if(calculoTotalEnMinutos >= 780) {
			throw new Exception("El tiempo de servicio de vuelo del tripulante no puede superar las 13 horas en 24 horas consecutivas");
		}
		
	}

	@Override
	public void eliminarTripulanteDePlan(Tripulante tripulante, PlanDeVuelo plan) {
		List<Tripulante> tripulantesDelPlan = plan.getTripulantes();
		tripulantesDelPlan.remove(tripulante);
		plan.setTripulantes(tripulantesDelPlan);
		planDeVueloDao.eliminarTripulanteDePlan(plan);

	}

	public long calcularTVDeUnPlan(PlanDeVuelo plan) {
		long calculoTotalEnMinutos = 0;
		List<Itinerario> itinerarios2 = servicioItinerario.listarItinerariosDePlan(plan.getId());
		for (Itinerario itinerario2 : itinerarios2) {
			Date horaAterrizaje2 = itinerario2.getAterrizajeEstimado();
			Date horaDespegue2 = itinerario2.getDespegueEstimado();
			long calculo = horaAterrizaje2.getTime() - horaDespegue2.getTime();
			long calculoEnMinutos = (int) (calculo / 60000);
			calculoTotalEnMinutos += calculoEnMinutos;
		}
		
		return calculoTotalEnMinutos;
	}

	public Long calcularTSVDeUnPlan(PlanDeVuelo plan) {
		List<Itinerario> itinerarios = servicioItinerario.listarItinerariosDePlan(plan.getId());
		Date horaFinal = itinerarios.get(itinerarios.size() - 1).getAterrizajeEstimado();
		Date horaInicial = itinerarios.get(0).getDespegueEstimado();
		long calculo = horaFinal.getTime() - horaInicial.getTime();
		long calculoEnMinutos = (int) (calculo / 60000);
		calculoEnMinutos += 90;

		return calculoEnMinutos;
	}
}