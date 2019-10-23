package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Itinerario;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
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

	@Override
	public List<Vuelo> listarVuelosDePlan(Long idObtenido) {
		List<Vuelo> listaDeVuelos = sessionFactory.getCurrentSession().createCriteria(Vuelo.class)
				.createAlias("itinerarios", "itinerariosjoin")
				.createAlias("itinerariosjoin.plandevuelo", "plandevuelojoin")
				.add(Restrictions.eq("plandevuelojoin.id", idObtenido))
				.list();
				
		return listaDeVuelos;
	}
	
	@Override
	public void agregarItinerario(Itinerario itinerario) {
		Session session = sessionFactory.getCurrentSession();		
		session.save(itinerario);
	}
	
	@Override
	public void eliminarVueloDePlan(PlanDeVuelo plan, Vuelo vuelo) {
		final Session session = sessionFactory.getCurrentSession();
		Itinerario itinerarioAEliminar = (Itinerario) session.createCriteria(Itinerario.class)
		.createAlias("vuelos", "vuelosjoin")
		.add(Restrictions.eq("vuelosjoin.id", vuelo.getId()))
		.createAlias("vuelosjoin.itinerarios", "itinerariosjoin")
		.createAlias("itinerariosjoin.plandevuelo", "plandevuelojoin")
		.add(Restrictions.eq("plandevuelojoin.id", plan.getId()))
		.uniqueResult();
		
		session.delete(itinerarioAEliminar);
	}
}