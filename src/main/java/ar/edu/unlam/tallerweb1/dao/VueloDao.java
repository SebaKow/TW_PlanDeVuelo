package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.PVContieneV;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

public interface VueloDao {
	
	public List<Vuelo> listarVuelos();
	public Vuelo consultarVueloId(Long id);
	public void agregarVuelo(Vuelo vuelo);
	public void editarVuelo(Vuelo vuelo);
	public void eliminarVuelo(Vuelo vuelo);
	public PVContieneV traerHorasDeUnVuelo(Long id);
}