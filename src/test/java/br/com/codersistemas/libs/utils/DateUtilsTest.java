package br.com.codersistemas.libs.utils;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testAddDate() {
		Date addDate = DateUtils.addDate(new GregorianCalendar(1978, Calendar.AUGUST, 26).getTime(),
				Calendar.DAY_OF_MONTH, 4);
		System.out.println(addDate);
	}

	@Test
	public void testGetDifferenceFromDates() {
		Calendar differenceFromDates = DateUtils.getDifferenceFromDates(
				new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime(),
				new GregorianCalendar(2000, Calendar.JANUARY, 2).getTime());
		System.out.println(differenceFromDates.getTime());
	}

	@Test
	public void testNow() {
		Date now = DateUtils.now();
		System.out.println(now);
	}

	@Test
	public void testGetCurrentDate() {

	}

	@Test
	public void testFormatarData() {
		System.out.println(DateUtils.formatarData(new Date(), "dd/MM/yyyy HH:mm:ss"));
	}

	@Test
	public void testIsValid() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIsDataHoraMaior() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIsDataMaior() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIsDataMaiorOuIgual() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSanitizeDate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSanitizeLastDate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTomorrow() {
		//fail("Not yet implemented");
	}

	@Test
	public void testYesterday() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDataComHorario() {
		//fail("Not yet implemented");
	}

	@Test
	public void testMinutosDecorridosDate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testMinutosDecorridosLong() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSegundosDecorridosLong() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSegundosDecorridosDateDate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTempoDecorridoDateDate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTempoDecorridoLong() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTempoDecorridoDateDateTimeUnit() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTempoDecorridoLongTimeUnit() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAdicionarAoContador() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAgora() {
		//fail("Not yet implemented");
	}

	@Test
	public void testInicioDoDiaAtual() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFimDoDiaAtual() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIsDataDentroDoIntervalo() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetHourOrMinuteFromString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIsTimeWellFormed() {
		//fail("Not yet implemented");
	}

	@Test
	public void testJuntarHoraMinutoComData() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDateToString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDateToStringWithHour() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDateToStringOnlyHour() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDateTimeToStringDateString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDateTimeToStringDate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testTimeToString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDateTimeFullToString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testInicioDaDataInformada() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFinalDaDataInformada() {
		//fail("Not yet implemented");
	}

	@Test
	public void testInicioDaDataInformadaToCalendar() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFinalDaDataInformadaToCalendar() {
		//fail("Not yet implemented");
	}

	@Test
	public void testConjuntoDeDatas() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetDateFromTimeInMillis() {
		//fail("Not yet implemented");
	}

	@Test
	public void testValidarMatricula() {
		//fail("Not yet implemented");
	}

	@Test
	public void testValidateEmail() {
		//fail("Not yet implemented");
	}

	@Test
	public void testValidarDadoComPattern() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIguais() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAddDia() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAdicionarMes() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAdicionarDia() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAdicionarHoras() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAdicionarMinutos() {
		//fail("Not yet implemented");
	}

	@Test
	public void testValidar() {
		//fail("Not yet implemented");
	}

	@Test
	public void testParseTime() {
		//fail("Not yet implemented");
	}

	@Test
	public void testParseDate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testParseDatetime() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSepararHorarios() {
		//fail("Not yet implemented");
	}

}
