package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Itinerario;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

public interface ServicioItinerario {
	
	Itinerario consultarItinerarioId(Long idItinerario);
	List<Vuelo> listarVuelosDePlan(Long idObtenido);
	void agregarItinerario(PlanDeVuelo plan, Vuelo vuelo);
	void eliminarVueloDePlan(Itinerario itinerario);
}