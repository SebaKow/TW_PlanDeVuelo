package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.VueloDao;
import ar.edu.unlam.tallerweb1.modelo.PVContieneV;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;

@Service("ServicioVuelo")
@Transactional
public class ServicioVueloImpl implements ServicioVuelo {
	
	@Inject
	VueloDao vueloDao;
	
	@Override
	public List<Vuelo> listarVuelos(){
		return vueloDao.listarVuelos();
	}
	
	@Override
	public Vuelo consultarVueloId(Long id) {
		return vueloDao.consultarVueloId(id);
	}
	
	@Override
	public void agregarVuelo(Vuelo vuelo) {
		vueloDao.agregarVuelo(vuelo);
	}
	
	@Override
	public void editarVuelo(Vuelo vuelo) {
		vueloDao.editarVuelo(vuelo);
	}
	
	@Override
	public void eliminarVuelo(Vuelo vuelo){
		vueloDao.eliminarVuelo(vuelo);
	}
	
	@Override
	public PVContieneV traerHorasDeUnVuelo(Long id) {
		return vueloDao.traerHorasDeUnVuelo(id);
	}
}