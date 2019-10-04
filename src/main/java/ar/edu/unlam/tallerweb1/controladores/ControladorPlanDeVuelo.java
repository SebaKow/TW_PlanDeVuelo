package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.PlanDeVuelo;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanDeVuelo;

@Controller
public class ControladorPlanDeVuelo {

	@Inject
	ServicioPlanDeVuelo servicioPlanDeVuelo;
	
	// PLANES DE VUELO
	@RequestMapping(path = "/planesdevuelo", method = RequestMethod.GET)
	public ModelAndView irAPlanesDeVuelo() {
		List<PlanDeVuelo> planesdevuelo = servicioPlanDeVuelo.listarPlanesDeVuelo();
		ModelMap modelo = new ModelMap();
		modelo.put("listaPlanesDeVuelo", planesdevuelo);
		return new ModelAndView("planesdevuelo", modelo);
	}
}