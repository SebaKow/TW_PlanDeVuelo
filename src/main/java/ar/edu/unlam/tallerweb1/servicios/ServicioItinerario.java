package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Itinerario;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

public interface ServicioItinerario {
	
	List<Vuelo> listarVuelosDePlan(Long idObtenido);
	List<Itinerario> listarItinerariosDePlan(Long id);
	void agregarItinerario(PlanDeVuelo plan, Vuelo vuelo) throws Exception;
	void eliminarVueloDePlan(PlanDeVuelo plan, Vuelo vuelo);
}