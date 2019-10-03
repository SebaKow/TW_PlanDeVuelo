package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.dao.ItinerarioDao;
import ar.edu.unlam.tallerweb1.modelo.PVContieneV;
@Service("ServicioItinerario")
public class ServicioItinerarioImpl implements ServicioItinerario {

	@Inject
	private ItinerarioDao itinerarioDao;

	@Override
	public List<PVContieneV> listarTodosItinerario() {
		
		return itinerarioDao.listarTodosItinerario();
	}

}
