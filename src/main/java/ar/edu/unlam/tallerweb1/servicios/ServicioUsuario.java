package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioUsuario {
	
	List<Usuario> listarTripulantes();
	Usuario consultarUsuarioId(Long id);
	void editarUsuario(Usuario usuario);
	void eliminarUsuario(Usuario usuario);
}