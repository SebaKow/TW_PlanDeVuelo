package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;

public interface PlanDeVueloDao {

	public List<PlanDeVuelo> listarPlanesDeVuelo();
	public PlanDeVuelo consultarPlanDeVueloId(Long id);
}