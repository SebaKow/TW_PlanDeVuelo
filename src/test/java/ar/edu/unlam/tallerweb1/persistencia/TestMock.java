package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;

import org.assertj.core.condition.AnyOf;
import org.hibernate.type.AnyType;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.controladores.ControladorTripulante;
import ar.edu.unlam.tallerweb1.modelo.Tripulante;
import ar.edu.unlam.tallerweb1.servicios.ServicioTripulante;

public class TestMock {
	@Test
	public void testQueCreaUnTripulante() {
		ServicioTripulante servicioTripulanteMock = mock(ServicioTripulante.class);
		Tripulante tripulanteMock = mock(Tripulante.class);
		tripulanteMock.setNombre("seba");
		tripulanteMock.setApellido("Kow");
		tripulanteMock.setDni(123312);
		tripulanteMock.setEmail("sebakow@gmail.com");
		tripulanteMock.setPassword("1234");
		HttpServletRequest requestMock = mock(HttpServletRequest.class);
		ControladorTripulante controladorTripulante = new ControladorTripulante();
		controladorTripulante.setServicioTripulante(servicioTripulanteMock);
		
		servicioTripulanteMock.agregarTripulante(tripulanteMock);
		when(servicioTripulanteMock.consultarTripulante(tripulanteMock)).th
		Tripulante tripulante = servicioTripulanteMock.consultarTripulante(tripulanteMock);
		assertThat(tripulante).isEqualTo(tripulanteMock);
	}
}
