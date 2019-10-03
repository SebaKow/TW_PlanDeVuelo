package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.PVContieneV;
@Repository
@Transactional
public class ItinerarioDaoImpl implements ItinerarioDao {
	
	@Inject
	SessionFactory sessionFactory;
	
	@Override
	public List<PVContieneV> listarTodosItinerario() {
		return sessionFactory.getCurrentSession().createCriteria(PVContieneV.class)
				.list();
	}

}
