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

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorUsuario {

	@Inject
	ServicioUsuario servicioUsuario;
	
	@RequestMapping(path="/tripulantes", method = RequestMethod.GET)
	public ModelAndView irATripulantes() {
		List<Usuario> listaUsuarios = servicioUsuario.listarTripulantes();
		ModelMap modelo = new ModelMap();
		modelo.put("listaUsuarios", listaUsuarios);
		return new ModelAndView("tripulantes",modelo);
	}
	
	@RequestMapping(path="/eliminarUsuario", method = RequestMethod.POST)
	public ModelAndView eliminarUsuario(@ModelAttribute("usuario") Usuario usuarioRecibido, HttpServletRequest request) {
		Usuario usuarioBuscado = servicioUsuario.consultarUsuarioId(usuarioRecibido.getId());
		servicioUsuario.eliminarUsuario(usuarioBuscado);
		return new ModelAndView("redirect:/tripulantes");
	}
}
