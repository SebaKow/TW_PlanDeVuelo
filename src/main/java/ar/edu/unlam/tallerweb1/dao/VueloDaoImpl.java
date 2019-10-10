package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Vuelo;

@Repository("vueloDao")
public class VueloDaoImpl implements VueloDao {
	
	@Inject
	SessionFactory sessionFactory;
	
	@Override
	public List<Vuelo> listarVuelos(){
		List<Vuelo> listaDeVuelos = sessionFactory.getCurrentSession()
											.createCriteria(Vuelo.class)
											.add(Restrictions.eq("estado",true))
											.list();
		return listaDeVuelos;
	}
	
	@Override
	public Vuelo consultarVueloId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Vuelo) session.createCriteria(Vuelo.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
	
	@Override
	public void agregarVuelo(Vuelo vuelo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(vuelo);
	}
	
	@Override
	public void editarVuelo(Vuelo vuelo) {
		sessionFactory.getCurrentSession().saveOrUpdate(vuelo);
	}
	
	@Override
	public void eliminarVuelo(Vuelo vuelo) {
		sessionFactory.getCurrentSession().saveOrUpdate(vuelo);
	}
}