package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.PVContieneV;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

public interface ServicioVuelo {
	List<Vuelo>listarVuelos();
	Vuelo consultarVueloId(Long id);
	void editarVuelo(Vuelo vuelo);
	void eliminarVuelo(Vuelo vuelo);
	
	PVContieneV traerHorasDeUnVuelo(Long id);
}
