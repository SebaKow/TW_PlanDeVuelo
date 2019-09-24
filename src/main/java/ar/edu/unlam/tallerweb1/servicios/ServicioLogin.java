package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

// INTERFACE QUE DEFINE LOS METODOS DEL SERVICIO DE USUARIOS.
public interface ServicioLogin {

	Usuario consultarUsuario(Usuario usuario);
	void agregarUsuario(Usuario usuario);
	Usuario consultarUsuarioId(Long id);
}