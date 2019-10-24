package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDateTime;
import java.util.Date;

public interface ServicioConvertirHora {
	public LocalDateTime convertirALocalDate(Date date);
	public Date convertirADate (LocalDateTime localDateTime);
}
