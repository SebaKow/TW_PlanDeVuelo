package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.modelo.Tripulante;
import ar.edu.unlam.tallerweb1.servicios.ServicioTripulante;

public class TestLoginMock extends SpringTest{
	
	@Test
    @Transactional
    @Rollback(true)
    public void testQueRedirigeALaVistaCorrectaAlLoguearse(){
		Tripulante tripulanteMock = new Tripulante();
		ServicioTripulante servicioTripulanteMock = mock(ServicioTripulante.class);
		HttpSession httpSessionMock = mock(HttpSession.class);
		when(servicioTripulanteMock.consultarTripulante(any(Tripulante.class))).thenReturn(tripulanteMock);
		HttpServletRequest httpMock = mock(HttpServletRequest.class);
		when(httpMock.getSession()).thenReturn(httpSessionMock);
		ControladorLogin controladorLogin = new ControladorLogin();
		controladorLogin.setServicioTripulante(servicioTripulanteMock);
		tripulanteMock.setEsAdmin(true);
		
		ModelAndView vista = controladorLogin.validarLogin(tripulanteMock, httpMock);
		assertThat(vista.getViewName()).isEqualTo("redirect:/homeAdmin");
	}
}