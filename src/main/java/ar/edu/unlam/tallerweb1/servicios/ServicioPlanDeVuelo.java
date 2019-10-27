package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;

public interface ServicioPlanDeVuelo {

	List<PlanDeVuelo> listarPlanesDeVuelo();
	PlanDeVuelo consultarPlanDeVueloId(Long id);
	void agregarPlanDeVuelo(PlanDeVuelo planDeVuelo);
	void editarPlanDeVuelo(PlanDeVuelo planDeVuelo);
	void eliminarPlanDeVuelo(PlanDeVuelo planDeVuelo);
}