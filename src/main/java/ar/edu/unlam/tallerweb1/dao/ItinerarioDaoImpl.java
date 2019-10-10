package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Vuelo;

@Repository("ItinerarioDao")
public class ItinerarioDaoImpl implements ItinerarioDao {
	
	@Inject
	SessionFactory sessionFactory;
	
	@Override
	public Boolean agregarVuelo(Vuelo vuelo) {
		Session session = sessionFactory.getCurrentSession();
		Boolean seGuardo = (Boolean) session.save(vuelo);
		return seGuardo;
	}

}
