package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorLogin {

	/* LA ANOTACIÓN @INJECT INDICA A SPRING QUE EN ESTE ATRIBUTO SE DEBE SETEAR (INYECCIÓN DE DEPENDENCIAS)
	   UN OBJETO DE UNA CLASE QUE IMPLEMENTE LA INTERFACE SERVICIOLOGIN, DICHA CLASE DEBE ESTAR ANOTADA COMO
	   @SERVICE O @REPOSITORY Y DEBE ESTAR EN UN PAQUETE DE LOS INDICADOS EN APPLICATIONCONTEXT.XML. */
	@Inject
	private ServicioLogin servicioLogin;

	// ESTE MÉTODO ESCUCHA LA URL LOCALHOST:8080/NOMBRE_APP/LOGIN SI LA MISMA ES INVOCADA POR MÉTODO HTTP GET.
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		/* SE AGREGA AL MODELO UNA OBJETO DEL TIPO USUARIO CON KEY 'USUARIO' PARA QUE EL MISMO SEA ASOCIADO
		   AL MODELATTRIBUTE DEL FORM QUE ESTÁ DEFINIDO EN LA VISTA 'LOGIN'. */
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		/* SE VA A LA VISTA LOGIN (EL NOMBRE COMPLETO DE LA LISTA SE RESUELVE UTILIZANDO EL VIEW RESOLVER 
		   DEFINIDO EN EL ARCHIVO SPRING-SERVLET.XML) Y SE ENVÍAN LOS DATOS A LA MISMA DENTRO DEL MODELO */
		return new ModelAndView("login", modelo);
	}

	// ESTE MÉTODO ESCUCHA LA URL VALIDAR-LOGIN SIEMPRE Y CUANDO SE INVOQUE CON MÉTODO HTTP POST.
	/* EL MÉTODO RECIBE UN OBJETO USUARIO EL QUE TIENE LOS DATOS INGRESADOS EN EL FORM CORRESPONDIENTE Y
	   SE CORRESPONDE CON EL MODELATTRIBUTE DEFINIDO EN EL TAG FORM: FORM. */
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		/* INVOCA EL MÉTODO CONSULTARUSUARIO DEL SERVICIO Y HACE UN REDIRECT A LA URL /HOME, ESTO ES, EN LUGAR DE
	       ENVIAR A UNA VISTA HACE UNA LLAMADA A OTRO ACTION A TRAVÉS DE LA URL. */
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			return new ModelAndView("redirect:/home");
		} else {
			// SI EL USUARIO NO EXISTE AGREGA UN MENSAJE DE ERROR EN EL MODELO.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	// ESCUCHA LA URL /HOME POR GET, Y REDIRIGE A UNA VISTA.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("home");
	}

	// ESCUCHA LA URL /, Y REDIRIGE A LA URL /LOGIN, ES LO MISMO QUE SI SE INVOCA LA URL /LOGIN DIRECTAMENTE.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}
}