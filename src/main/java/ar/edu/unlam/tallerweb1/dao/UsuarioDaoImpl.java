package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

// Implementacion del DAO de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

	// Como todo dao maneja acciones de persistencia, normalmente estara inyectado el session factory de hibernate,
	// el mismo esta difinido en el archivo hibernateContext.xml.
	@Inject
    private SessionFactory sessionFactory;

	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de usuario donde el email y password sean iguales a los del objeto recibido como parametro.
		// uniqueResult da error si se encuentran mas de un resultado en la busqueda.
			 final Session session = sessionFactory.getCurrentSession();
			 return (Usuario) session.createCriteria(Usuario.class)
					.add(Restrictions.eq("email", usuario.getEmail()))
					.add(Restrictions.eq("password", usuario.getPassword()))
					.uniqueResult(); // uniqueResult() o list() --> Depende de la cantidad de resultados que queremos.
	}
	
	@Override
	public Usuario consultarUsuarioId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
	
	@Override
	public void agregarUsuario(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		session.save(usuario);
	}
	
	@Override
	public void editarUsuario(Usuario usuario) {
		sessionFactory.getCurrentSession().saveOrUpdate(usuario);
	}
	
	@Override
	public void eliminarUsuario(Usuario usuario) {
		sessionFactory.getCurrentSession().delete(usuario);
	}
	
	@Override
	public List<Usuario> listarTripulantes(){
		List<Usuario> listaDeTripulantes = sessionFactory.getCurrentSession()
											.createCriteria(Usuario.class)
//											.add(Restrictions.isNotNull("dni"))
											.list();
		return listaDeTripulantes;
	}
}