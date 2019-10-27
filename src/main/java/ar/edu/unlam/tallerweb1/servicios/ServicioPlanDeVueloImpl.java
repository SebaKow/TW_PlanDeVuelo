package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PlanDeVueloDao;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;

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
}