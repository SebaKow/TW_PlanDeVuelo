package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ItinerarioDao;
import ar.edu.unlam.tallerweb1.modelo.Itinerario;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;
@Service("ServicioItinerario")
@Transactional
public class ServicioItinerarioImpl implements ServicioItinerario {
	@Inject
	private ItinerarioDao itinerarioDao;

//	@Override
//	public Boolean agregarItinerario(PlanDeVuelo plan, Vuelo vuelo) {
//		Boolean validacion1 = validacionPlan();
//		//Validacion 1
//		
//		return true;
//	}
//
//	private Boolean validacion1() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public void agregarItinerario(PlanDeVuelo plan, Vuelo vuelo) {
		// itinerarioDao.agregarItinerario(plan,vuelo);
		Itinerario itinerario = new Itinerario();
		itinerario.setPlandevuelo(plan);
		List<Vuelo>listaVuelos = new ArrayList<>();
		listaVuelos.add(vuelo);
		itinerario.setVuelos(listaVuelos);
		itinerarioDao.agregarItinerario(itinerario);
	}

	@Override
	public List<Vuelo> listarVuelosDePlan(Long idObtenido) {
		return itinerarioDao.listarVuelosDePlan(idObtenido);
	}
	

	

}
