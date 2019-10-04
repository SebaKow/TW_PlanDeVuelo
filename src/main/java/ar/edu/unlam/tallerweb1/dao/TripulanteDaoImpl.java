package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Tripulante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

@Repository("tripulanteDao")
public class TripulanteDaoImpl implements TripulanteDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public List<Tripulante> listarTripulantes(){
		List<Tripulante> listaDeTripulantes = sessionFactory.getCurrentSession()
											.createCriteria(Tripulante.class)
											.list();
		return listaDeTripulantes;
	}
	
	@Override
	public Tripulante consultarTripulante(Tripulante tripulante) {
			 final Session session = sessionFactory.getCurrentSession();
			 return (Tripulante) session.createCriteria(Tripulante.class)
					.add(Restrictions.eq("email", tripulante.getEmail()))
					.add(Restrictions.eq("password", tripulante.getPassword()))
					.uniqueResult(); // uniqueResult() o list() --> Depende de la cantidad de resultados que queremos.
	}
	
	@Override
	public Tripulante consultarTripulanteId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Tripulante) session.createCriteria(Tripulante.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
	
	@Override
	public void agregarTripulante(Tripulante tripulante) {
		Session session = sessionFactory.getCurrentSession();
		session.save(tripulante);
	}
	
	@Override
	public void editarTripulante(Tripulante tripulante) {
		sessionFactory.getCurrentSession().saveOrUpdate(tripulante);
	}
	
	@Override
	public void eliminarTripulante(Tripulante tripulante) {
		sessionFactory.getCurrentSession().delete(tripulante);
	}
}