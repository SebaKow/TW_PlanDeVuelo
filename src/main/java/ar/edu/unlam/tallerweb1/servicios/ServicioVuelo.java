package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Vuelo;

public interface ServicioVuelo {
	
	List<Vuelo> listarVuelos();
	Vuelo consultarVueloId(Long id);
	void agregarVuelo(Vuelo vuelo);
	void editarVuelo(Vuelo vuelo);
	void eliminarVuelo(Vuelo vuelo);
}