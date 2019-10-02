package ar.edu.unlam.tallerweb1.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.PVContieneV;
import ar.edu.unlam.tallerweb1.modelo.Tripulante;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

@Repository("vueloDao")
public class VueloDaoImpl implements VueloDao{
	@Inject
	SessionFactory sessionFactory;
	
	@Override
	public List<Vuelo> listarVuelos(){
		List<Vuelo> listaDeVuelos= sessionFactory.getCurrentSession()
											.createCriteria(Vuelo.class)
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
	public void editarVuelo(Vuelo vuelo) {
		sessionFactory.getCurrentSession().saveOrUpdate(vuelo);
	}
	@Override
	public void eliminarVuelo(Vuelo vuelo) {
		sessionFactory.getCurrentSession().delete(vuelo);
	}
	@Override
	public PVContieneV traerHorasDeUnVuelo(Long id) {
		PVContieneV horaSalida = (PVContieneV) sessionFactory.getCurrentSession().createCriteria(PVContieneV.class)
				.createAlias("vuelo", "vueloJoin")
				.add(Restrictions.eq("vueloJoin.id", id))
				.uniqueResult();
		return horaSalida;
	}
}
