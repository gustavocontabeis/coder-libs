package br.com.codersistemas.libs.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
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
		
		//Period é a representação entre duas datas
		LocalDate startDate = LocalDate.of(2015, 2, 20);
		LocalDate endDate = LocalDate.of(2017, 1, 15);
		Period period = Period.between(startDate, endDate);
		
		System.out.println("Years:" + period.getYears() + " months:" + period.getMonths() + " days:"+period.getDays());
		
		int years = 3;
		int months = 10;
		int days = 2;
		Period fromUnits = Period.of(years, months, days);
		Period fromDays = Period.ofDays(50);
		Period fromMonths = Period.ofMonths(5);
		Period fromYears = Period.ofYears(months);
		Period fromWeeks = Period.ofWeeks(40);
		
		Duration.ofHours(8).toMillis();

		//assertEquals(280, fromWeeks.getDays());
		
		

	}

	@Test
	public void testLocalDateParse() {

	}

}
