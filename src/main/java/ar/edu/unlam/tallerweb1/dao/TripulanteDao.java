package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Tripulante;

// INTERFAZ QUE DEFINE LOS METODOS DEL DAO DE TRIPULANTES.
public interface TripulanteDao {
	
	public List<Tripulante> listarTripulantes();
	
	Tripulante consultarTripulante(Tripulante tripulante);
	Tripulante consultarTripulanteId(Long id);
	
	void agregarTripulante(Tripulante tripulante);
	void editarTripulante(Tripulante tripulante);
	void eliminarTripulante(Tripulante tripulante);
}