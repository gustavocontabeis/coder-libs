package br.com.codersistemas.libs.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Mariano
 * 
 */
public class DateUtils {

	public static final DateFormat TIMESTAMP = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	/**
	 * <b>Retorna data com acicao conforme parametro</b>.
	 * 
	 * @param date date
	 * @param field field
	 * @param amount amount
	 * @return java.util.Date date
	 */
	public static Date addDate(Date date, int field, int amount) {
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(date);
		}
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * Retorna calendar com a diferenciacao entre datas.
	 * 
	 * @param firstDate firstDate
	 * @param secondDate secondDate
	 * @return Calendar Calendar
	 */
	public static Calendar getDifferenceFromDates(Date firstDate, Date secondDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDate);

		long t1 = cal.getTimeInMillis();
		cal.setTime(secondDate);

		long diff = Math.abs(cal.getTimeInMillis() - t1);
		final int ONE_DAY = 1000 * 60 * 60 * 24;
		final int ONE_HOUR = ONE_DAY / 24;
		final int ONE_MINUTE = ONE_HOUR / 60;
		final int ONE_SECOND = ONE_MINUTE / 60;

		long d = diff / ONE_DAY;
		diff %= ONE_DAY;

		long h = diff / ONE_HOUR;
		diff %= ONE_HOUR;

		long m = diff / ONE_MINUTE;
		diff %= ONE_MINUTE;

		long s = diff / ONE_SECOND;
		long ms = diff % ONE_SECOND;

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, Long.valueOf(d).intValue());
		calendar.set(Calendar.HOUR, Long.valueOf(h).intValue());
		calendar.set(Calendar.MINUTE, Long.valueOf(m).intValue());
		calendar.set(Calendar.SECOND, Long.valueOf(s).intValue());
		calendar.set(Calendar.MILLISECOND, Long.valueOf(ms).intValue());

		return calendar;
	}

	public static Date now() {
		return Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo")).getTime();
	}

	private final static Integer ZERO = 0;

	/**
	 * Retorna data no formato: {dia da semana}, dd/MM/yyyy hh:mm
	 * 
	 * @return String string
	 */
	public String getCurrentDate() {
		return new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
	}

	public static String formatarData(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * Retorna true se data é uma data valida.
	 * 
	 * @param data Data no formato dd/MM/yyyy
	 * @return boolean true ou false
	 */
	public static boolean isValid(String data) {
		Pattern p = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|"
				+ "(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))"
				+ "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3"
				+ "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|"
				+ "(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])"
				+ "(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
		Matcher m = p.matcher(data);
		if (!m.find()) {
			System.err.println("Data inválida seu mostro");
			return false;
		}
		else {
			System.out.println("Data válida");
			return true;
		}
	}

	/**
	 * Verifica se date1 é maior que date2. <b>PS-1: Passar data no formato
	 * dd/MM/yyyy</b> <b>PS-2: Verificado com TIMESTAMP.</b>
	 * 
	 * @param date1
	 *            Data 1
	 * @param date2
	 *            Data 2
	 * @return boolean true ou false
	 */
	public static boolean isDataHoraMaior(Date date1, Date date2) {
		return date1.after(date2);
	}

	/**
	 * Verifica se date1 é maior que date2. <b>PS-1: Passar data no formato
	 * dd/MM/yyyy</b> <b>PS-2: Verificado com TIME.</b>
	 * 
	 * @param date1
	 *            Data 1
	 * @param date2
	 *            Data 2
	 * @return boolean true ou false
	 */
	public static boolean isDataMaior(Date date1, Date date2) {
		Calendar c1 = sanitizeDate(date1);
		Calendar c2 = sanitizeDate(date2);
		return (c1.after(c2));
	}

	/**
	 * Verifica se date1 é <i>maior ou igual</i> a date2. Considera apenas
	 * dia/mes/ano, sem horarios.
	 * 
	 * @param date1 date1
	 * @param date2 date2
	 * @return boolean true or false
	 */
	public static boolean isDataMaiorOuIgual(Date date1, Date date2) {
		Calendar c1 = sanitizeDate(date1);
		Calendar c2 = sanitizeDate(date2);
		return (c1.compareTo(c2) >= 0);
	}

	public static Calendar sanitizeDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, ZERO);
		cal.set(Calendar.MINUTE, ZERO);
		cal.set(Calendar.SECOND, ZERO);
		cal.set(Calendar.MILLISECOND, ZERO);
		return cal;
	}

	public static Calendar sanitizeLastDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 23);
		int lastTime = 59;
		cal.set(Calendar.MINUTE, lastTime);
		cal.set(Calendar.SECOND, lastTime);
		cal.set(Calendar.MILLISECOND, ZERO);
		return cal;
	}

	public static Date tomorrow() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		c.add(Calendar.DAY_OF_YEAR, 1);
		return c.getTime();
	}

	public static Date yesterday() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		c.add(Calendar.DAY_OF_YEAR, -1);
		return c.getTime();
	}

}
