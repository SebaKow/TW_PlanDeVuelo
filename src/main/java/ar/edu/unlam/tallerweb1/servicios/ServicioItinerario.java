package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

public interface ServicioItinerario {
	
	List<Vuelo> listarVuelosDePlan(Long idObtenido);
	void agregarItinerario(PlanDeVuelo plan, Vuelo vuelo);
	void eliminarVueloDePlan(PlanDeVuelo plan, Vuelo vuelo);
}