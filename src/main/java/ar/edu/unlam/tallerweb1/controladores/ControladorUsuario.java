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
	
	@RequestMapping(path = "/tripulantes", method = RequestMethod.GET)
	public ModelAndView irATripulantes() {
		List<Usuario> listaUsuarios = servicioUsuario.listarTripulantes();
		ModelMap modelo = new ModelMap();
		modelo.put("listaUsuarios", listaUsuarios);
		return new ModelAndView("tripulantes",modelo);
	}
	
	// ESTO LLEVA A LA VISTA PARA PODER EDITAR EL USUARIO.
	@RequestMapping(path = "/modificarUsuario", method = RequestMethod.POST)
	public ModelAndView modificarUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		Usuario usuarioBuscado = servicioUsuario.consultarUsuarioId(usuario.getId());
		ModelMap modelo = new ModelMap();
		modelo.put("usuario", usuarioBuscado);
		
		return new ModelAndView("editarUsuario", modelo);
	}
	
	// ESTO EDITA EL USUARIO.
	@RequestMapping(path = "/editarUsuario", method = RequestMethod.POST)
	public ModelAndView editarUsuario(@ModelAttribute("usuario") Usuario usuarioRecibido, HttpServletRequest request) {
		Usuario usuarioBuscado = servicioUsuario.consultarUsuarioId(usuarioRecibido.getId());
		usuarioBuscado.setNombre(usuarioRecibido.getNombre());
		usuarioBuscado.setApellido(usuarioRecibido.getApellido());
		usuarioBuscado.setDni(usuarioRecibido.getDni());
		usuarioBuscado.setEmail(usuarioRecibido.getEmail());
		usuarioBuscado.setPassword(usuarioRecibido.getPassword());
		servicioUsuario.editarUsuario(usuarioBuscado);
		return new ModelAndView("redirect:/tripulantes");
	}
	
	@RequestMapping(path = "/eliminarUsuario", method = RequestMethod.POST)
	public ModelAndView eliminarUsuario(@ModelAttribute("usuario") Usuario usuarioRecibido, HttpServletRequest request) {
		Usuario usuarioBuscado = servicioUsuario.consultarUsuarioId(usuarioRecibido.getId());
		servicioUsuario.eliminarUsuario(usuarioBuscado);
		return new ModelAndView("redirect:/tripulantes");
	}
}