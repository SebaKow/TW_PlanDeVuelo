package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Tripulante;
import ar.edu.unlam.tallerweb1.servicios.ServicioTripulante;

@Controller
public class ControladorTripulante {

	@Inject
	ServicioTripulante servicioTripulante;
	
	@RequestMapping(path = "/tripulantes", method = RequestMethod.GET)
	public ModelAndView irATripulantes() {
		List<Tripulante> listaTripulantes = servicioTripulante.listarTripulantes();
		ModelMap modelo = new ModelMap();
		modelo.put("listaTripulantes", listaTripulantes);
		return new ModelAndView("tripulantes", modelo);
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public ModelAndView irARegister() {
		return new ModelAndView("register");
	}
	
	@RequestMapping(path = "/registrar-tripulante", method = RequestMethod.POST)
	public ModelAndView registrarTripulante(@ModelAttribute("tripulante") Tripulante tripulante, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		tripulante.setEsAdmin(false);
		servicioTripulante.agregarTripulante(tripulante);		
		return new ModelAndView("redirect:/tripulantes", model);
	}
	
	// ESTO LLEVA A LA VISTA PARA PODER EDITAR EL TRIPULANTE.
	@RequestMapping(path = "/modificarTripulante", method = RequestMethod.POST)
	public ModelAndView modificarTripulante(@ModelAttribute("tripulante") Tripulante tripulante, HttpServletRequest request) {
		Tripulante tripulanteBuscado = servicioTripulante.consultarTripulanteId(tripulante.getId());
		ModelMap modelo = new ModelMap();
		modelo.put("tripulante", tripulanteBuscado);
		
		return new ModelAndView("editarTripulante", modelo);
	}
	
	// ESTO EDITA EL TRIPULANTE.
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
	
	@RequestMapping(path = "/eliminarTripulante", method = RequestMethod.POST)
	public ModelAndView eliminarTripulante(@ModelAttribute("tripulante") Tripulante tripulanteRecibido, HttpServletRequest request) {
		Tripulante tripulanteBuscado = servicioTripulante.consultarTripulanteId(tripulanteRecibido.getId());
		servicioTripulante.eliminarTripulante(tripulanteBuscado);
		return new ModelAndView("redirect:/tripulantes");
	}
}