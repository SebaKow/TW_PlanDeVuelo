package ar.edu.unlam.tallerweb1.dao;

import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Tripulante;

public interface PlanDeVueloDao {

	List<PlanDeVuelo> listarPlanesDeVuelo();
	PlanDeVuelo consultarPlanDeVueloId(Long id);
	void agregarPlanDeVuelo(PlanDeVuelo planDeVuelo);
	void editarPlanDeVuelo(PlanDeVuelo planDeVuelo);
	void eliminarPlanDeVuelo(PlanDeVuelo planDeVuelo);
	void agregarTripulanteAPlan(PlanDeVuelo plan);
	List<Tripulante> listarTripulantesEnPlan(PlanDeVuelo plan);
	void eliminarTripulanteDePlan(PlanDeVuelo plan);
	List<PlanDeVuelo>ListarPlanesPorTripulanteYFecha(Tripulante tripulante,Date fechaSalidaDePlan,int dias);
}