package ar.edu.unlam.tallerweb1.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Tripulante;

@Repository("planDeVueloDao")
public class PlanDeVueloDaoImpl implements PlanDeVueloDao{

	@Inject
	SessionFactory sessionFactory;
	
	@Override
	public List<PlanDeVuelo> listarPlanesDeVuelo() {
		List<PlanDeVuelo> listaDePlanesDeVuelo = sessionFactory.getCurrentSession()
				.createCriteria(PlanDeVuelo.class)
				.add(Restrictions.eq("estado", true))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
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
	
	@Override
	public void agregarPlanDeVuelo(PlanDeVuelo planDeVuelo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(planDeVuelo);
	}
	
	@Override
	public void editarPlanDeVuelo(PlanDeVuelo planDeVuelo) {
		sessionFactory.getCurrentSession().saveOrUpdate(planDeVuelo);
	}
	
	@Override
	public void eliminarPlanDeVuelo(PlanDeVuelo planDeVuelo) {
		sessionFactory.getCurrentSession().saveOrUpdate(planDeVuelo);
	}
	
	@Override
	public void agregarTripulanteAPlan(PlanDeVuelo plan) {
		
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().saveOrUpdate(plan);
	}

	@Override
	public List<Tripulante> listarTripulantesEnPlan(PlanDeVuelo plan) {
		List<Tripulante> listaDeTripulantesEnPlan =  sessionFactory.getCurrentSession()
				.createCriteria(Tripulante.class)
				.createAlias("planesDeVuelo","planesDeVueloJoin")
				.add(Restrictions.eq("planesDeVueloJoin.id", plan.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		
		return listaDeTripulantesEnPlan;
	}

	@Override
	public void eliminarTripulanteDePlan(PlanDeVuelo plan) {
		sessionFactory.getCurrentSession().saveOrUpdate(plan);		
	}

	@Override
	public List<PlanDeVuelo> ListarPlanesPorTripulanteYFecha(Tripulante tripulante, Date fechaSalidaDePlan, int dias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaSalidaDePlan);
		cal.set(Calendar.DATE,cal.get(Calendar.DATE)-dias);
		Date fechaRestada = cal.getTime();
		
		List<PlanDeVuelo> listaDePlanes = sessionFactory.getCurrentSession()
				.createCriteria(PlanDeVuelo.class)
				.add(Restrictions.between("fecha", fechaRestada, fechaSalidaDePlan))
				.createAlias("tripulantes", "tripulantesJoin")
				.add(Restrictions.eq("tripulantesJoin.id",tripulante.getId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		
		return listaDePlanes;
	}
}