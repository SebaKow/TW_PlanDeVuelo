package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.TripulanteDao;
import ar.edu.unlam.tallerweb1.modelo.Tripulante;

@Service("ServicioTripulante")
@Transactional
public class ServicioTripulanteImpl implements ServicioTripulante{
	
	@Inject
	TripulanteDao tripulanteDao;
	
	@Override
	public List<Tripulante> listarTripulantes() {
		return tripulanteDao.listarTripulantes();
	}
	
	@Override 
	public Tripulante consultarTripulante(Tripulante tripulante) {
		return tripulanteDao.consultarTripulante(tripulante);
	}
	
	@Override
	public Tripulante consultarTripulanteId(Long id) {
		return tripulanteDao.consultarTripulanteId(id);
	}
	
	@Override
	public void agregarTripulante(Tripulante tripulante) {
		tripulanteDao.agregarTripulante(tripulante);
	}
	
	@Override
	public void editarTripulante(Tripulante tripulante) {
		tripulanteDao.editarTripulante(tripulante);
	}
	
	@Override
	public void eliminarTripulante(Tripulante tripulante) {
		tripulanteDao.eliminarTripulante(tripulante);
	}
}