package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

//CLASE QUE PRUEBA LA CONEXIÓN A LA BASE DE DATOS. HEREDA DE SPRINGTEST, POR LO QUE CORRE DENTRO DEL CONTEXTO DE SPRING.
public class ConexionBaseDeDatosTest extends SpringTest{

	@Test
    @Transactional @Rollback(true)
    public void pruebaConexion(){
        assertThat(getSession().isConnected()).isTrue();
    }
}