package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Itinerario;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;

public interface ItinerarioDao {

	List<Itinerario> listarItinerariosDePlan(Long id);
	void agregarItinerario(Itinerario itinerario);
	void eliminarVueloDePlan(PlanDeVuelo plan, Long idItinerario);
}