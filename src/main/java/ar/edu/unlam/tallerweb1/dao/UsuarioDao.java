package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

// INTERFACE QUE DEFINE LOS METODOS DEL DAO DE USUARIOS.
public interface UsuarioDao {
	
	Usuario consultarUsuario (Usuario usuario);
	void agregarUsuario(Usuario usuario);
	Usuario consultarUsuarioId(Long id);
	public List<Usuario> listarTripulantes();
	void eliminarUsuario(Usuario usuario);
}