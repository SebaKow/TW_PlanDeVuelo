package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Itinerario;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;

@Repository("ItinerarioDao")
public class ItinerarioDaoImpl implements ItinerarioDao {
	
	@Inject
	SessionFactory sessionFactory;
	
	@Override
	public List<Itinerario> listarItinerariosDePlan(Long id){
		List<Itinerario> listaItinerarios = sessionFactory.getCurrentSession().createCriteria(Itinerario.class)
				.createAlias("plandevuelo", "plandevuelojoin")
				.add(Restrictions.eq("plandevuelojoin.id", id))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		
		return listaItinerarios;
	}
	
	@Override
	public void agregarItinerario(Itinerario itinerario) {
		Session session = sessionFactory.getCurrentSession();		
		session.save(itinerario);
	}
	
	@Override
	public void eliminarVueloDePlan(PlanDeVuelo plan, Long idItinerario) {
		final Session session = sessionFactory.getCurrentSession();
		Itinerario itinerarioAEliminar = (Itinerario) session.createCriteria(Itinerario.class)
				.add(Restrictions.eq("id", idItinerario))
				.createAlias("plandevuelo", "plandevuelojoin")
				.add(Restrictions.eq("plandevuelojoin.id", plan.getId()))
				.uniqueResult();
		
		session.delete(itinerarioAEliminar);
	}
}