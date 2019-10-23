package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;
import ar.edu.unlam.tallerweb1.servicios.ServicioItinerario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanDeVuelo;
import ar.edu.unlam.tallerweb1.servicios.ServicioVuelo;

@Controller
public class ControladorPlanDeVuelo {

	@Inject
	private ServicioPlanDeVuelo servicioPlanDeVuelo;
	
	@Inject
	private ServicioVuelo servicioVuelo;
	
	@Inject
	private ServicioItinerario servicioItinerario;
	
	// PLANES DE VUELO
	@RequestMapping(path = "/planesdevuelo", method = RequestMethod.GET)
	public ModelAndView irAPlanesDeVuelo() {
		List<PlanDeVuelo> planesdevuelo = servicioPlanDeVuelo.listarPlanesDeVuelo();
		ModelMap modelo = new ModelMap();
		modelo.put("listaPlanesDeVuelo", planesdevuelo);
		return new ModelAndView("planesDeVuelo", modelo);
	}
	
	// PLAN DE VUELO SELECCIONADO
	@RequestMapping(path = "/plandevueloseleccionado", method = RequestMethod.GET)
	public ModelAndView irAPlanDeVueloSeleccionado(@RequestParam(value = "idPlanDeVuelo") Long idObtenido) {
		PlanDeVuelo planDeVuelo = servicioPlanDeVuelo.consultarPlanDeVueloId(idObtenido);
		ModelMap modelo = new ModelMap();
		modelo.put("planDeVuelo", planDeVuelo);
		
		List<Vuelo> listaDeVuelos = servicioVuelo.listarVuelos();
		modelo.put("listaDeVuelos", listaDeVuelos);
		
		List<Vuelo> vuelosAgregados = servicioItinerario.listarVuelosDePlan(idObtenido);
		modelo.put("vuelosAgregados", vuelosAgregados);
		modelo.put("cantidadVuelosAgregados", vuelosAgregados.size());
		
		return new ModelAndView("vuelosEnPlan", modelo);
	}
	
	// AGREGAR VUELO A PLAN
	@RequestMapping(path = "/agregarVueloAPlan", method = RequestMethod.GET)
	public ModelAndView agregarVueloAPlan(@RequestParam(value = "idVuelo") Long idVuelo, @RequestParam(value = "idPlan") Long idPlan) {
		Vuelo vuelo = servicioVuelo.consultarVueloId(idVuelo);
		PlanDeVuelo plan = servicioPlanDeVuelo.consultarPlanDeVueloId(idPlan);
		servicioItinerario.agregarItinerario(plan, vuelo);
		return new ModelAndView("redirect:/plandevueloseleccionado?idPlanDeVuelo=" + idPlan);
	}
	
	// ELIMINAR VUELO DE PLAN
	@RequestMapping(path = "/eliminarVueloDePlan", method = RequestMethod.GET)
	public ModelAndView eliminarVueloDePlan(@RequestParam(value = "idVuelo") Long idVuelo, @RequestParam(value = "idPlan") Long idPlan) {
		Vuelo vuelo = servicioVuelo.consultarVueloId(idVuelo);
		PlanDeVuelo plan = servicioPlanDeVuelo.consultarPlanDeVueloId(idPlan);
		servicioItinerario.eliminarVueloDePlan(plan, vuelo);
		return new ModelAndView("redirect:/plandevueloseleccionado?idPlanDeVuelo=" + idPlan);
	}
}