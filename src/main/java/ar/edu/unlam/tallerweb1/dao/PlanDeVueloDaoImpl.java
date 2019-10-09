package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;

@Repository("planDeVueloDao")
public class PlanDeVueloDaoImpl implements PlanDeVueloDao{

	@Inject
	SessionFactory sessionFactory;
	
	@Override
	public List<PlanDeVuelo> listarPlanesDeVuelo() {
		List<PlanDeVuelo> listaDePlanesDeVuelo = sessionFactory.getCurrentSession()
				.createCriteria(PlanDeVuelo.class)
				.list();
		
		return listaDePlanesDeVuelo;
	}
	
	@Override
	public PlanDeVuelo consultarPlanDeVueloId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (PlanDeVuelo) session.createCriteria(PlanDeVuelo.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
}