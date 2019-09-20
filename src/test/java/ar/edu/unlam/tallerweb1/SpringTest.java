package ar.edu.unlam.tallerweb1;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// SE INDICA QUE LOS TEST QUE HEREDEN DE ESTA CLASE CORRAN CON EL RUNNER DE JUNIT PARA SPRING.
@RunWith(SpringJUnit4ClassRunner.class)
// SE INDICA
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
// CLASE BASE PARA LOS TEST QUE SE PRETENDE QUE SE CORRAN DENTRO DEL CONTEXTO DE SPRING.
public abstract class SpringTest {
	
	// TIENE INYECTADO EL SESSION FACTORY PARA QUE LOS TEST QUE HEREDEN DE ESTE TENGAN ACCESO AL MISMO.
    @Inject
    private SessionFactory sessionFactory;

    // MÉTODO PARA OBTENER UNA SESIÓN DE LA BASE DE DATOS.
    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}