package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service("ServicioTripulantes")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario{
	@Inject
	UsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> listarTripulantes(){
		return usuarioDao.listarTripulantes();
	}
	
	@Override
	public Usuario consultarUsuarioId(Long id) {
		return usuarioDao.consultarUsuarioId(id);
	}
	
	@Override
	public void eliminarUsuario(Usuario usuario) {
		usuarioDao.eliminarUsuario(usuario);
	}
}
