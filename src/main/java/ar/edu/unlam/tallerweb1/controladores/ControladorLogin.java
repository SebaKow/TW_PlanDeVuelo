package ar.edu.unlam.tallerweb1.controladores;

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
public class ControladorLogin {

	/* LA ANOTACIÓN @INJECT INDICA A SPRING QUE EN ESTE ATRIBUTO SE DEBE SETEAR (INYECCIÓN DE DEPENDENCIAS)
	   UN OBJETO DE UNA CLASE QUE IMPLEMENTE LA INTERFACE SERVICIOLOGIN, DICHA CLASE DEBE ESTAR ANOTADA COMO
	   @SERVICE O @REPOSITORY Y DEBE ESTAR EN UN PAQUETE DE LOS INDICADOS EN APPLICATIONCONTEXT.XML. */
	@Inject
	private ServicioTripulante servicioTripulante;
	
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		Tripulante tripulante = new Tripulante();
		modelo.put("tripulante", tripulante);
		/* SE VA A LA VISTA LOGIN (EL NOMBRE COMPLETO DE LA LISTA SE RESUELVE UTILIZANDO EL VIEW RESOLVER 
		   DEFINIDO EN EL ARCHIVO SPRING-SERVLET.XML) Y SE ENVÍAN LOS DATOS A LA MISMA DENTRO DEL MODELO */
		return new ModelAndView("login", modelo);
	}

	/* EL MÉTODO RECIBE UN OBJETO TRIPULANTE EL QUE TIENE LOS DATOS INGRESADOS EN EL FORM CORRESPONDIENTE Y
	   SE CORRESPONDE CON EL MODELATTRIBUTE DEFINIDO EN EL TAG FORM: FORM. */
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("tripulante") Tripulante tripulante, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		/* INVOCA EL MÉTODO CONSULTARTRIPULANTE DEL SERVICIO Y HACE UN REDIRECT A LA URL /HOME, ESTO ES, EN LUGAR DE
	       ENVIAR A UNA VISTA HACE UNA LLAMADA A OTRO ACTION A TRAVÉS DE LA URL. */
		Tripulante tripulanteBuscado = servicioTripulante.consultarTripulante(tripulante);
		if (tripulanteBuscado != null) {
			if(tripulanteBuscado.getEsAdmin() == true) {
				request.getSession().setAttribute("idTripulante", tripulanteBuscado.getId());
				return new ModelAndView("redirect:/homeAdmin");
			} else {
				request.getSession().setAttribute("idTripulante", tripulanteBuscado.getId());
				return new ModelAndView("redirect:/home");
			}
		} else {
			// SI EL TRIPULANTE NO EXISTE AGREGA UN MENSAJE DE ERROR EN EL MODELO.
			model.put("error", "Los datos ingresados son incorrectos. Por favor, intente nuevamente.");
		}
		
		return new ModelAndView("login", model);
	}
	
	public void setServicioTripulante(ServicioTripulante servicioTripulante) {
		this.servicioTripulante = servicioTripulante;
	}

	// ESCUCHA LA URL /HOME POR GET, Y REDIRIGE A UNA VISTA.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome(HttpServletRequest request) {
		Tripulante tripulanteBuscado = servicioTripulante.consultarTripulanteId((Long) request.getSession().getAttribute("idTripulante"));
		ModelMap modelo = new ModelMap();
		modelo.put("tripulante", tripulanteBuscado);
		return new ModelAndView("home", modelo);
	}
	
	@RequestMapping(path = "/homeAdmin", method = RequestMethod.GET)
	public ModelAndView irAHomeAdministrador() {
		return new ModelAndView("homeAdmin");
	}

	// ESCUCHA LA URL /, Y REDIRIGE A LA URL /LOGIN, ES LO MISMO QUE SI SE INVOCA LA URL /LOGIN DIRECTAMENTE.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}
}