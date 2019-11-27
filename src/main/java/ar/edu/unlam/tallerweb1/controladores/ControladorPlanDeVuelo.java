package ar.edu.unlam.tallerweb1.controladores;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Itinerario;
import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.modelo.Tripulante;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;
import ar.edu.unlam.tallerweb1.servicios.ServicioItinerario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanDeVuelo;
import ar.edu.unlam.tallerweb1.servicios.ServicioTripulante;
import ar.edu.unlam.tallerweb1.servicios.ServicioVuelo;

@Controller
public class ControladorPlanDeVuelo {

	@Inject
	private ServicioPlanDeVuelo servicioPlanDeVuelo;
	
	@Inject
	private ServicioVuelo servicioVuelo;
	
	@Inject
	private ServicioTripulante servicioTripulante;
	
	@Inject
	private ServicioItinerario servicioItinerario;
	
	// PLANES DE VUELO
	@RequestMapping(path = "/planesDeVuelo", method = RequestMethod.GET)
	public ModelAndView irAPlanesDeVuelo() {
		List<PlanDeVuelo> planesdevuelo = servicioPlanDeVuelo.listarPlanesDeVuelo();
		ModelMap modelo = new ModelMap();
		modelo.put("listaPlanesDeVuelo", planesdevuelo);
		return new ModelAndView("planesDeVuelo", modelo);
	}
	
	// VISTA AGREGAR PLAN DE VUELO
	@RequestMapping(path = "/agregarPlanDeVuelo", method = RequestMethod.GET)
	public ModelAndView irAVistaAgregarPlanDeVuelo() {
		return new ModelAndView("agregarPlanDeVuelo");
	}
	
	// AGREGAR PLAN DE VUELO
	@RequestMapping(path = "/agregarPlanDeVuelo", method = RequestMethod.POST)
	public ModelAndView agregarPlanDeVuelo(@ModelAttribute("planDeVuelo") PlanDeVuelo planDeVuelo, HttpServletRequest request) throws ParseException {
		ModelMap modelo = new ModelMap();
		planDeVuelo.setDuracionParse();
		servicioPlanDeVuelo.agregarPlanDeVuelo(planDeVuelo);
		return new ModelAndView("redirect:/planesDeVuelo", modelo);
	}
	
	// VISTA EDITAR PLAN DE VUELO
	@RequestMapping(path = "/modificarPlanDeVuelo", method = RequestMethod.POST)
	public ModelAndView modificarPlanDeVuelo(@ModelAttribute("planDeVuelo") PlanDeVuelo planDeVuelo, HttpServletRequest request) {
		PlanDeVuelo planDeVueloBuscado = servicioPlanDeVuelo.consultarPlanDeVueloId(planDeVuelo.getId());
		ModelMap modelo = new ModelMap();
		modelo.put("planDeVuelo", planDeVueloBuscado);
		return new ModelAndView("editarPlanDeVuelo", modelo);
	}
	
	// EDITAR PLAN DE VUELO
	@RequestMapping(path = "/editarPlanDeVuelo", method = RequestMethod.POST)
	public ModelAndView editarPlanDeVuelo(@ModelAttribute("planDeVuelo") PlanDeVuelo planDeVueloRecibido, HttpServletRequest request) throws ParseException {
		PlanDeVuelo planDeVueloBuscado = servicioPlanDeVuelo.consultarPlanDeVueloId(planDeVueloRecibido.getId());
		planDeVueloBuscado.setDescripcion(planDeVueloRecibido.getDescripcion());
		planDeVueloBuscado.setFechaString(planDeVueloRecibido.getFechaString());
		planDeVueloBuscado.setDuracionParse();
		servicioPlanDeVuelo.editarPlanDeVuelo(planDeVueloBuscado);
		return new ModelAndView("redirect:/planesDeVuelo");
	}
	
	// VISTA ELIMINAR PLAN DE VUELO
	@RequestMapping(path = "/eliminarPlanDeVuelo", method = RequestMethod.POST)
	public ModelAndView eliminarPlanDeVuelo(@ModelAttribute("planDeVuelo") PlanDeVuelo planDeVuelo, HttpServletRequest request) {
		PlanDeVuelo planDeVueloBuscado = servicioPlanDeVuelo.consultarPlanDeVueloId(planDeVuelo.getId());
		ModelMap modelo = new ModelMap();
		modelo.put("planDeVueloAEliminar", planDeVueloBuscado);
		return new ModelAndView("eliminarPlanDeVuelo", modelo);
	}
	
	// ELIMINAR PLAN DE VUELO
	@RequestMapping(path = "/eliminarPlanDeVueloDeLista", method = RequestMethod.GET)
	public ModelAndView eliminarPlanDeVueloDeLista(@RequestParam(value = "id") Long idRecibido) {
		PlanDeVuelo planDeVueloBuscado = servicioPlanDeVuelo.consultarPlanDeVueloId(idRecibido);
		servicioPlanDeVuelo.eliminarPlanDeVuelo(planDeVueloBuscado);
		return new ModelAndView("redirect:/planesDeVuelo");
	}
	
	// PLAN DE VUELO SELECCIONADO PARA VUELOS
	@RequestMapping(path = "/plandevueloseleccionado", method = RequestMethod.GET)
	public ModelAndView irAPlanDeVueloSeleccionado(
			@RequestParam(value = "idPlanDeVuelo") Long idObtenido, 
			@RequestParam(value = "error", required = false) String error) {
		PlanDeVuelo planDeVuelo = servicioPlanDeVuelo.consultarPlanDeVueloId(idObtenido);
		ModelMap modelo = new ModelMap();
		modelo.put("planDeVuelo", planDeVuelo);
		
		List<Vuelo> listaDeVuelos = servicioVuelo.listarVuelos();
		modelo.put("listaDeVuelos", listaDeVuelos);
		
		List<Vuelo> vuelosAgregados = servicioItinerario.listarVuelosDePlan(idObtenido);
		modelo.put("vuelosAgregados", vuelosAgregados);
		
		List<Itinerario> listaDeItinerarios = servicioItinerario.listarItinerariosDePlan(idObtenido);
		modelo.put("itinerariosAgregados", listaDeItinerarios);
		
		modelo.put("error", error);
		return new ModelAndView("vuelosEnPlan", modelo);
	}
	
	// PLAN DE VUELO SELECCIONADO PARA TRIPULANTES
	@RequestMapping(path = "/plandevueloseleccionado2", method = RequestMethod.GET)
	public ModelAndView irAPlanDeVueloSeleccionado2(@RequestParam(value = "idPlanDeVuelo") Long idObtenido, 
			@RequestParam(value = "error", required = false) String error) {
		PlanDeVuelo planDeVuelo = servicioPlanDeVuelo.consultarPlanDeVueloId(idObtenido);
		ModelMap modelo = new ModelMap();
		modelo.put("planDeVuelo", planDeVuelo);
		
		List<Tripulante> listaDeTripulantes = servicioTripulante.listarTripulantes();
		modelo.put("listaDeTripulantes", listaDeTripulantes);
		List<Tripulante> tripulantesAgregados = servicioPlanDeVuelo.listarTripulantesEnPlan(planDeVuelo);
		modelo.put("tripulantesAgregados",tripulantesAgregados);
		modelo.put("error", error);
		return new ModelAndView("tripulantesEnPlan", modelo);
	}
	
	// AGREGAR VUELO A PLAN
	@RequestMapping(path = "/agregarVueloAPlan", method = RequestMethod.GET)
	public ModelAndView agregarVueloAPlan(@RequestParam(value = "idVuelo") Long idVuelo, @RequestParam(value = "idPlan") Long idPlan) {
		Vuelo vuelo = servicioVuelo.consultarVueloId(idVuelo);
		PlanDeVuelo plan = servicioPlanDeVuelo.consultarPlanDeVueloId(idPlan);
		ModelMap modelo = new ModelMap();
		String error = null;
		try {
			servicioItinerario.agregarItinerario(plan, vuelo);
		} catch (Exception e) {
			 error = e.getMessage();
		}
		
		modelo.put("error", error);
		return new ModelAndView("redirect:/plandevueloseleccionado?idPlanDeVuelo=" + idPlan, modelo);
	}
	
	// AGREGAR TRIPULANTE A PLAN
	@RequestMapping(path = "/agregarTripulanteAPlan", method = RequestMethod.GET)
	public ModelAndView agregarTripulanteAPlan(@RequestParam(value = "idTripulante") Long idTripulante, @RequestParam(value = "idPlan") Long idPlan) {
		Tripulante tripulante = servicioTripulante.consultarTripulanteId(idTripulante);
		PlanDeVuelo plan = servicioPlanDeVuelo.consultarPlanDeVueloId(idPlan);
		ModelMap modelo = new ModelMap();
		String error=null;
		try {
			servicioPlanDeVuelo.agregarTripulanteAPlan(tripulante, plan);
		} catch (Exception e) {
			error=e.getMessage();
		}

		modelo.put("error", error);
		
		
		
		return new ModelAndView("redirect:/plandevueloseleccionado2?idPlanDeVuelo=" + idPlan,modelo);
	}
	
	// ELIMINAR TRIPULANTE DE PLAN
		@RequestMapping(path = "/eliminarTripulanteDePlan", method = RequestMethod.GET)
		public ModelAndView eliminarTripulanteDePlan(@RequestParam(value = "idTripulante") Long idTripulante, @RequestParam(value = "idPlan") Long idPlan) {
			Tripulante tripulante = servicioTripulante.consultarTripulanteId(idTripulante);
			PlanDeVuelo plan = servicioPlanDeVuelo.consultarPlanDeVueloId(idPlan);
		
			servicioPlanDeVuelo.eliminarTripulanteDePlan(tripulante, plan);
			
			return new ModelAndView("redirect:/plandevueloseleccionado2?idPlanDeVuelo=" + idPlan);
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