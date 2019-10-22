package ar.edu.unlam.tallerweb1.controladores;

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

import ar.edu.unlam.tallerweb1.modelo.Tripulante;
import ar.edu.unlam.tallerweb1.servicios.ServicioTripulante;

@Controller
public class ControladorTripulante {

	@Inject
	ServicioTripulante servicioTripulante;
	
	// TRIPULANTES
	@RequestMapping(path = "/tripulantes", method = RequestMethod.GET)
	public ModelAndView irATripulantes() {
		List<Tripulante> listaTripulantes = servicioTripulante.listarTripulantes();
		ModelMap modelo = new ModelMap();
		modelo.put("listaTripulantes", listaTripulantes);
		return new ModelAndView("tripulantes", modelo);
	}
	
	// VISTA AGREGAR TRIPULANTE
	@RequestMapping(path = "/agregar-tripulante", method = RequestMethod.GET)
	public ModelAndView irAVistaAgregarTripulante() {
		return new ModelAndView("agregarTripulante");
	}
	
	// AGREGAR TRIPULANTE
	@RequestMapping(path = "/agregarTripulante", method = RequestMethod.POST)
	public ModelAndView agregarTripulante(@ModelAttribute("tripulante") Tripulante tripulante, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		tripulante.setEsAdmin(false);
		servicioTripulante.agregarTripulante(tripulante);		
		return new ModelAndView("redirect:/tripulantes", model);
	}
	
	// VISTA EDITAR TRIPULANTE
	@RequestMapping(path = "/modificarTripulante", method = RequestMethod.POST)
	public ModelAndView modificarTripulante(@ModelAttribute("tripulante") Tripulante tripulante, HttpServletRequest request) {
		Tripulante tripulanteBuscado = servicioTripulante.consultarTripulanteId(tripulante.getId());
		ModelMap modelo = new ModelMap();
		modelo.put("tripulante", tripulanteBuscado);
		return new ModelAndView("editarTripulante", modelo);
	}
	
	// EDITAR TRIPULANTE
	@RequestMapping(path = "/editarTripulante", method = RequestMethod.POST)
	public ModelAndView editarTripulante(@ModelAttribute("tripulante") Tripulante tripulanteRecibido, HttpServletRequest request) {
		Tripulante tripulanteBuscado = servicioTripulante.consultarTripulanteId(tripulanteRecibido.getId());
		tripulanteBuscado.setNombre(tripulanteRecibido.getNombre());
		tripulanteBuscado.setApellido(tripulanteRecibido.getApellido());
		tripulanteBuscado.setDni(tripulanteRecibido.getDni());
		tripulanteBuscado.setEmail(tripulanteRecibido.getEmail());
		tripulanteBuscado.setPassword(tripulanteRecibido.getPassword());
		servicioTripulante.editarTripulante(tripulanteBuscado);
		return new ModelAndView("redirect:/tripulantes");
	}
	
	// ELIMINAR TRIPULANTE
	@RequestMapping(path = "/eliminarTripulante", method = RequestMethod.GET)
	public ModelAndView eliminarTripulante(@RequestParam(value = "idTripulante") Long idRecibido) {
		Tripulante tripulanteBuscado = servicioTripulante.consultarTripulanteId(idRecibido);
		servicioTripulante.eliminarTripulante(tripulanteBuscado);
		return new ModelAndView("redirect:/tripulantes");
	}
}