package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;

public interface PlanDeVueloDao {

	List<PlanDeVuelo> listarPlanesDeVuelo();
	PlanDeVuelo consultarPlanDeVueloId(Long id);
	void agregarPlanDeVuelo(PlanDeVuelo planDeVuelo);
	void editarPlanDeVuelo(PlanDeVuelo planDeVuelo);
	void eliminarPlanDeVuelo(PlanDeVuelo planDeVuelo);
}