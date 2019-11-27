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

import ar.edu.unlam.tallerweb1.modelo.Vuelo;
import ar.edu.unlam.tallerweb1.servicios.ServicioVuelo;

@Controller
public class ControladorVuelo {
	
	@Inject
	ServicioVuelo servicioVuelo;
	
	// VUELOS
	@RequestMapping(path = "/vuelos", method = RequestMethod.GET)
	public ModelAndView irAVuelos() {
		List<Vuelo> vuelos = servicioVuelo.listarVuelos();
		ModelMap modelo = new ModelMap();
		modelo.put("listaVuelos", vuelos);
		return new ModelAndView("vuelos", modelo);
	}
	
	// VISTA AGREGAR VUELO
	@RequestMapping(path = "/agregarVuelo", method = RequestMethod.GET)
	public ModelAndView irAVistaAgregarVuelo() {
		return new ModelAndView("agregarVuelo");
	}
	
	// AGREGAR VUELO
	@RequestMapping(path = "/agregarVuelo", method = RequestMethod.POST)
	public ModelAndView agregarVuelo(@ModelAttribute("vuelo") Vuelo vuelo, HttpServletRequest request) throws ParseException {
		ModelMap model = new ModelMap();
		vuelo.setDuracionParse();
		servicioVuelo.agregarVuelo(vuelo);
		return new ModelAndView("redirect:/vuelos", model);
	}
	
	// VISTA EDITAR VUELO
	@RequestMapping(path = "/modificarVuelo", method = RequestMethod.POST)
	public ModelAndView modificarVuelo(@ModelAttribute("vuelo") Vuelo vuelo, HttpServletRequest request) {
		Vuelo vueloBuscado = servicioVuelo.consultarVueloId(vuelo.getId());
		ModelMap modelo = new ModelMap();
		modelo.put("vuelo", vueloBuscado);
		return new ModelAndView("editarVuelo", modelo);
	}
	
	// EDITAR VUELO
	@RequestMapping(path = "/editarVuelo", method = RequestMethod.POST)
	public ModelAndView editarVuelo(@ModelAttribute("vuelo") Vuelo vueloRecibido, HttpServletRequest request) throws ParseException {
		Vuelo vueloBuscado = servicioVuelo.consultarVueloId(vueloRecibido.getId());
		vueloBuscado.setOrigen(vueloRecibido.getOrigen());
		vueloBuscado.setDestino(vueloRecibido.getDestino());
		vueloBuscado.setDuracionString(vueloRecibido.getDuracionString());
		vueloBuscado.setDuracionParse();
		servicioVuelo.editarVuelo(vueloBuscado);
		return new ModelAndView("redirect:/vuelos");
	}
	
	// VISTA ELIMINAR VUELO
	@RequestMapping(path = "/eliminarVuelo", method = RequestMethod.POST)
	public ModelAndView eliminarVuelo(@ModelAttribute("vuelo") Vuelo vuelo, HttpServletRequest request) {
		Vuelo vueloBuscado = servicioVuelo.consultarVueloId(vuelo.getId());
		ModelMap modelo = new ModelMap();
		modelo.put("vueloAEliminar", vueloBuscado);
		return new ModelAndView("eliminarVuelo", modelo);
	}
	
	// ELIMINAR VUELO
	@RequestMapping(path = "/eliminarVueloDeLista", method = RequestMethod.GET)
	public ModelAndView eliminarVueloDeLista(@RequestParam(value = "id") Long idRecibido) {
		Vuelo vueloBuscado = servicioVuelo.consultarVueloId(idRecibido);
		servicioVuelo.eliminarVuelo(vueloBuscado);
		return new ModelAndView("redirect:/vuelos");
	}
}