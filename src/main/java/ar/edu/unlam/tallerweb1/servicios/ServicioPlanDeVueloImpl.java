package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
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
		
		validarQueElTripulanteNoSupereLas8HorasDeTV(itinerarios, tripulante, 1);
		validarQueElTripulanteNoSupereLas8HorasDeTV(itinerarios, tripulante, 2);
		validarQueElTripulanteNoSupereLas8HorasDeTV(itinerarios, tripulante, 7);
		validarQueElTripulanteNoSupereLas8HorasDeTV(itinerarios, tripulante, 30);
		validarQueElTripulanteNoSupereLas8HorasDeTV(itinerarios, tripulante, 90);
		validarQueElTripulanteNoSupereLas8HorasDeTV(itinerarios, tripulante, 365);
		
		validarQueElTripulanteNoSupereLasHorasDeTSV(itinerarios, tripulante, 1);
		validarQueElTripulanteNoSupereLasHorasDeTSV(itinerarios, tripulante, 2);
		validarQueElTripulanteNoSupereLasHorasDeTSV(itinerarios, tripulante, 7);
		validarQueElTripulanteNoSupereLasHorasDeTSV(itinerarios, tripulante, 30);
	}

	public void validarQueNoSeAgregueDosVecesElMismoTripulante(PlanDeVuelo plan, Tripulante tripulante) throws Exception {
		List<Tripulante> tripulantesDelPlan = plan.getTripulantes();
		for (Tripulante tripulante2 : tripulantesDelPlan) {
			if (tripulante2.getId() == tripulante.getId()) {
				throw new Exception("El tripulante ya pertenece a este plan de vuelo.");
			}
		}
	}

	public void validarQueElTripulanteNoSupereLas8HorasDeTV(List<Itinerario> itinerarios, Tripulante tripulante, Integer dias) throws Exception {
		long calculoTotalEnMinutos = 0;
		PlanDeVuelo plan = itinerarios.get(0).getPlandevuelo();
		Itinerario primerItinerario = itinerarios.get(0);
		int tiempoMaximo = 0;

		calculoTotalEnMinutos += calcularTVDeUnPlan(plan);

		List<PlanDeVuelo> planes = planDeVueloDao.listarPlanesPorTripulanteYFecha(tripulante, primerItinerario.getDespegueEstimado(), dias);
		for (PlanDeVuelo planDeVuelo : planes) {
			calculoTotalEnMinutos += calcularTVDeUnPlan(planDeVuelo);
		}
		
		switch (dias) {
		case 1:
			tiempoMaximo = 480;
			break;
		case 2:
			tiempoMaximo = 840;
			break;
		case 7:
			tiempoMaximo = 2040;
			break;
		case 30:
			tiempoMaximo = 5400;
		case 90:
			tiempoMaximo = 14400;
			break;
		case 365:
			tiempoMaximo = 51600;
		default:
			break;
		}
		
		if(calculoTotalEnMinutos >= tiempoMaximo) {
			throw new Exception("El tiempo de vuelo del tripulante no puede superar las "+(tiempoMaximo/60)+" horas en los ultimos "+dias+" dias.");
		}
	}

	public void validarQueElTripulanteNoSupereLasHorasDeTSV(List<Itinerario> itinerarios, Tripulante tripulante, Integer dias) throws Exception {
		long calculoTotalEnMinutos = 0;
		PlanDeVuelo plan = itinerarios.get(0).getPlandevuelo();
		int tiempoMaximo = 0;
		
		calculoTotalEnMinutos += calcularTSVDeUnPlan(plan);
		
		List<PlanDeVuelo> planes = planDeVueloDao.listarPlanesPorTripulanteYFecha(tripulante, plan.getFecha(), dias);
		for (PlanDeVuelo planDeVuelo : planes) {
			calculoTotalEnMinutos += calcularTSVDeUnPlan(planDeVuelo);
		}
		
		switch (dias) {
		case 1:
			tiempoMaximo = 780;
			break;
		case 2:
			tiempoMaximo = 1320;
			break;
		case 7:
			tiempoMaximo = 3900;
			break;
		case 30:
			tiempoMaximo = 12000;
		default:
			break;
		}
		
		if(calculoTotalEnMinutos >= tiempoMaximo) {
			throw new Exception("El tiempo de servicio de vuelo del tripulante no puede superar las "+(tiempoMaximo/60)+" horas en los ultimos "+dias+" dias.");
		}
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
		long calculoEnMinutos = (int) (calculo/60000);
		calculoEnMinutos += 90;

		return calculoEnMinutos;
	}

	@Override
	public void eliminarTripulanteDePlan(Tripulante tripulante, PlanDeVuelo plan) {
		List<Tripulante> tripulantesDelPlan = plan.getTripulantes();
		List<Tripulante> nuevaListaTripulantes = new ArrayList<>();
		for (Tripulante tripulante2 : tripulantesDelPlan) {
			if(tripulante2.getId() != tripulante.getId()) {
				nuevaListaTripulantes.add(tripulante2);
			}
		}
		
		tripulantesDelPlan.remove(tripulante);
		plan.setTripulantes(nuevaListaTripulantes);
		planDeVueloDao.eliminarTripulanteDePlan(plan);
	}
}