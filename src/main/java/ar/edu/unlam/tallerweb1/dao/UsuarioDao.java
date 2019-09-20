package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

// INTERFACE QUE DEFINE LOS METODOS DEL DAO DE USUARIOS.
public interface UsuarioDao {
	
	Usuario consultarUsuario (Usuario usuario);
}