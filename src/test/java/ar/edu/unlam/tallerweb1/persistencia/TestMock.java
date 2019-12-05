package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.modelo.Tripulante;
import ar.edu.unlam.tallerweb1.modelo.Vuelo;
import ar.edu.unlam.tallerweb1.servicios.ServicioTripulante;
import ar.edu.unlam.tallerweb1.servicios.ServicioVuelo;

public class TestMock {
	
	@Test
    @Transactional
    @Rollback(true)
	public void testQueCreaUnTripulante() {
		ServicioTripulante servicioTripulanteMock = mock(ServicioTripulante.class);
		Tripulante tripulanteMock = mock(Tripulante.class);
		tripulanteMock.setNombre("seba");
		tripulanteMock.setApellido("Kow");
		tripulanteMock.setDni(123312);
		tripulanteMock.setEmail("sebakow@gmail.com");
		tripulanteMock.setPassword("1234");
		
		servicioTripulanteMock.agregarTripulante(tripulanteMock);
		when(servicioTripulanteMock.consultarTripulante(tripulanteMock)).thenReturn(tripulanteMock);
		Tripulante tripulante = servicioTripulanteMock.consultarTripulante(tripulanteMock);
		assertThat(tripulante).isEqualTo(tripulanteMock);
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testQueNoSePuedeLoguear() {
		ControladorLogin controladorLogin = new ControladorLogin();
		ServicioTripulante servicioTripulanteMock = mock(ServicioTripulante.class);
		Tripulante tripulanteMock = mock(Tripulante.class);
		HttpServletRequest httpMock = mock(HttpServletRequest.class);
		controladorLogin.setServicioTripulante(servicioTripulanteMock);
		when(servicioTripulanteMock.consultarTripulante(any(Tripulante.class))).thenReturn(null);
		
		ModelAndView modelAndView = controladorLogin.validarLogin(tripulanteMock, httpMock);
		assertThat(modelAndView.getModelMap().get("error")).isEqualTo("Los datos ingresados son incorrectos. Por favor, intente nuevamente.");
	}
	
	@Test
    @Transactional
    @Rollback(true)
	public void testQueEliminaUnVuelo() {
		ServicioVuelo servicioVueloMock = mock(ServicioVuelo.class);
		Vuelo vueloMock = mock(Vuelo.class);
		vueloMock.setOrigen("Argentina");
		vueloMock.setDestino("Uruguay");
		vueloMock.setEstado(true);
		servicioVueloMock.agregarVuelo(vueloMock);
		servicioVueloMock.eliminarVuelo(vueloMock);
		assertThat(vueloMock.getEstado()).isEqualTo(false);
	}
}