package br.com.codersistemas.libs.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

/**
 * @author gustavo
 */
public class JavaTimeTest {

	@Test
	public void testInstance() {
		
		//LocalTime - Representa um horário.
		LocalTime horaAgora = LocalTime.now();
		LocalTime horaEspecifica = LocalTime.of(20, 30, 50);
		LocalTime horaEspecificaDoTexto = LocalTime.parse("20:30:50");

		//LocalDate - Representa uma data.
		LocalDate dataHoje = LocalDate.now();
		LocalDate dataEspecifica = LocalDate.of(2017, 12, 25);
		LocalDate dataEspecificaDoTexto = LocalDate.parse("2017-12-25");

		//LocalDateTime - Representa uma data com hora.
		LocalDateTime dataHoraAgora = LocalDateTime.now();
		LocalDateTime dataHoraEspecifica = LocalDateTime.of(2017, 12, 25, 20, 30, 50);
		LocalDateTime dataHoraEspecificaDoTexto = LocalDateTime.parse("2017-12-25T20:30:50");

		//Instant - Representa um momento (data e hora) no tempo em UTC. 
		//Instant usa o padrao ISO 8601
		Instant momentoAgora = Instant.now();
		Instant momentoDoTexto = Instant.parse("2017-12-25T20:30:50Z");
		System.out.println(momentoDoTexto.toString()); 

		//ZoneDateTime - Representa uma data com hora e fuso horário.
		ZonedDateTime dataHoraZonaAgora = ZonedDateTime.now();
		ZonedDateTime dataHoraZonaEspecifica = ZonedDateTime.of(dataHoraEspecifica, ZoneId.of("America/Sao_Paulo"));
		ZonedDateTime dataHoraZonaEspecificaDoTexto = ZonedDateTime.parse("2017-12-25T20:30:50-02:00[America/Sao_Paulo]");

	}

	@Test
	public void testLocalDateParse() {

	}

}
