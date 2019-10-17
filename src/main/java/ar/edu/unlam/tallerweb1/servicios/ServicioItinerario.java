package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

public interface ServicioItinerario {
	void agregarItinerario(PlanDeVuelo plan, Vuelo vuelo);

	List<Vuelo> listarVuelosDePlan(Long idObtenido);

}
