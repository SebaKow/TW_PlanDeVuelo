package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PlanDeVueloDao;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Tripulante;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

@Service("ServicioPlanDeVuelo")
@Transactional
public class ServicioPlanDeVueloImpl implements ServicioPlanDeVuelo {

	@Inject
	PlanDeVueloDao planDeVueloDao;
	
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
	}

	private void validarQueNoSeAgregueDosVecesElMismoTripulante(PlanDeVuelo plan, Tripulante tripulante) throws Exception {
		List<Tripulante> tripulantesDelPlan = plan.getTripulantes();
		for (Tripulante tripulante2 : tripulantesDelPlan) {
			if(tripulante2.getId() == tripulante.getId()) {
				throw new Exception("El tripulante ya pertenece a este plan");
			}
		}
	}
}