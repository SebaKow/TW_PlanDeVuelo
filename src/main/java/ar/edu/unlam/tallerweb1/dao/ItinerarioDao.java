package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Itinerario;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

public interface ItinerarioDao {

	Boolean agregarVuelo(Vuelo vuelo);

	void agregarItinerario(Itinerario itinerario);

	List<Vuelo> listarVuelosDePlan(Long idObtenido);

}
