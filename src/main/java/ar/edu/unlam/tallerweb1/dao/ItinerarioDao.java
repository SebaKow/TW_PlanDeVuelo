package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Itinerario;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

public interface ItinerarioDao {

//	List<Vuelo> listarVuelosDePlan(Long idObtenido);
	List<Itinerario> listarItinerariosDePlan(Long id);
	void agregarItinerario(Itinerario itinerario);
	void eliminarVueloDePlan(PlanDeVuelo plan, Long idItinerario);
}