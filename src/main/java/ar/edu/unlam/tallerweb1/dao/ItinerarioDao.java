package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Itinerario;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

public interface ItinerarioDao {

	Boolean agregarVuelo(Vuelo vuelo);
	Itinerario consultarItinerarioId(Long idItinerario);
	List<Vuelo> listarVuelosDePlan(Long idObtenido);
	void agregarItinerario(Itinerario itinerario);
	void eliminarVueloDePlan(Itinerario itinerario);
}