package br.com.codersistemas.libs.utils;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import br.com.codersistemas.libs.exceptions.BusinessException;

/**
 * 
 * @author Mariano
 * 
 */
public class DateUtils {

	public static final DateFormat TIMESTAMP = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	

	private static DateFormat dfTime = new SimpleDateFormat("HH:mm");
	private static DateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat dfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public enum TimeParts {
		HOUR, MINUTE
	};
	
	public enum LogLevel {
		DEBUG, INFO, WARN, ERROR 
	};

	public enum TimeFormats {
		HOUR_MINUTE("HH:mm", "^[0-9]{2}\\W[0-9]{2}$"), HOUR_MINUTE_SECOND("HH:mm:ss", "^[0-9]{2}\\W[0-9]{2}\\W[0-9]{2}$");

		private String formato;
		private String pattern;

		private TimeFormats(String formato, String pattern) {
			this.formato = formato;
			this.pattern = pattern;
		}

		public String getFormato() {
			return formato;
		}

		public void setFormato(String formato) {
			this.formato = formato;
		}

		public String getPattern() {
			return pattern;
		}

		public void setPattern(String pattern) {
			this.pattern = pattern;
		}

	}


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


	/**
	 * Retorna a Data atual do sistema, com a hora, minuto e segundo passados
	 * por parametro.
	 * @param hora hora
	 * @param minuto minuto
	 * @param segundo segundo
	 * @return dataComHorario dataComHorario
	 */
	public static Date dataComHorario(Integer hora, Integer minuto, Integer segundo) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		calendar.set(Calendar.HOUR_OF_DAY, hora);
		calendar.set(Calendar.MINUTE, minuto);
		calendar.set(Calendar.SECOND, segundo);
		return calendar.getTime();
	}

	/**
	 * Calcula os minutos decorridos a partir de uma dada Data/Hora até a
	 * Data/Hora atual
	 * @param data data
	 * @return minutosDecorridos
	 */
	public static Integer minutosDecorridos(Date data) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		Long timestampAtual = calendar.getTimeInMillis();
		calendar.setTime(data);
		Long timestampParametro = calendar.getTimeInMillis();
		Long timeResultado = (((timestampAtual - timestampParametro) / 1000) / 60);
		return Integer.valueOf(timeResultado.toString());
	}

	/**
	 * Calcula os minutos decorridos a partir de uma dada representada por um TimeStamp
	 * @param timestamp timestamp
	 * @return minutosDecorridos
	 */
	public static Integer minutosDecorridos(long timestamp) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		Long timestampAtual = calendar.getTimeInMillis();
		calendar.setTimeInMillis(timestamp);
		Long timestampParametro = calendar.getTimeInMillis();
		Long timeResultado = (((timestampAtual - timestampParametro) / 1000) / 60);
		return Integer.valueOf(timeResultado.toString());
	}

	/**
	 * Calcula a quantidade de segundos decorridos conforme os milisegundos passados por parametro
	 * @param milisegundos milisegundos
	 * @return Integer segundosDecorridos
	 */
	public static Integer segundosDecorridos(long milisegundos) {
		Long stampResultado = (milisegundos / 1000);
		return Integer.valueOf(Long.toString(stampResultado));
	}

	/**
	 * Calcula a quantidade de segundos decorridos entre duas datas
	 * @param dataInicio dataInicio
	 * @param dataFim dataFim
	 * @return Integer segundosDecorridos
	 */
	public static Integer segundosDecorridos(Date dataInicio, Date dataFim) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		calendar.setTime(dataInicio);
		Long stampInicio = calendar.getTimeInMillis();
		calendar.setTime(dataFim);
		Long stampFim = calendar.getTimeInMillis();
		Long stampResultado = ((stampFim - stampInicio) / 1000);
		return Integer.valueOf(Long.toString(stampResultado));
	}

	/**
	 * @param inicio inicio
	 * @param fim fim
	 * @return String tempoDecorrido
	 */
	public static String tempoDecorrido(Date inicio, Date fim) {
		long milliSeconds = fim.getTime() - inicio.getTime();
		return tempoDecorrido(milliSeconds);
	}
	
	/**
	 * @param tempoEmMilisegundos tempoEmMilisegundos
	 * @return String tempoDecorrido
	 */
	public static String tempoDecorrido(long tempoEmMilisegundos) {
		return tempoDecorrido(tempoEmMilisegundos, null);
	}

	public static String tempoDecorrido(Date startDate, Date endDate, TimeUnit ate) {
		long milliSeconds = endDate.getTime() - startDate.getTime();
		return tempoDecorrido(milliSeconds, ate);
	}
	
	public static String tempoDecorrido(long timeSpentInMillis, TimeUnit ate) {
		StringBuilder str = new StringBuilder();
		List<TimeUnit> units = Arrays.asList(TimeUnit.values());
		Collections.reverse(units);
		Map<TimeUnit, Long> result = new LinkedHashMap<TimeUnit, Long>();
		for (TimeUnit unit : units) {
			long diff = unit.convert(timeSpentInMillis, TimeUnit.MILLISECONDS);
			long diffInMilliesForUnit = unit.toMillis(diff);
			timeSpentInMillis = timeSpentInMillis - diffInMilliesForUnit;
			result.put(unit, diff);
		}
		Map<TimeUnit, String> unitSymbols = new LinkedHashMap<TimeUnit, String>();
		unitSymbols.put(TimeUnit.DAYS, "d");
		unitSymbols.put(TimeUnit.HOURS, "h");
		unitSymbols.put(TimeUnit.MINUTES, "m");
		unitSymbols.put(TimeUnit.SECONDS, "s");
		unitSymbols.put(TimeUnit.MILLISECONDS, "ms");
		for (TimeUnit t : unitSymbols.keySet()) {
			if (result.containsKey(t) && result.get(t) > 0) {
				str.append(result.get(t));
				str.append(unitSymbols.get(t));
				
				if(ate != null && ate == t) {
					break;
				}
			}
		}
		return (StringUtils.isNotBlank(str.toString())) ? str.toString() : "0ms";
	}

	public static void adicionarAoContador(Object obj, String fieldName) {
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			if (field.get(obj) == null) {
				field.set(obj, (Integer.valueOf(1)));
			} else {
				Integer atual = (Integer) field.get(obj);
				field.set(obj, ++atual);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Date agora() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		return cal.getTime();
	}

	public static Date inicioDoDiaAtual() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date fimDoDiaAtual() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * Verifica se o horario de um objeto Date (HORA:MINUTO) esta dentro do
	 * intervalo de horarios. Retorna true se o horario do objeto Date esta
	 * dentro do intervalo. Do contrario retorna false.
	 * 
	 * @param dateTime
	 *            (data/hora a ser avaliada)
	 * @param intervalo
	 *            (intervalo de horarios no formato (HH:mm - HH:mm)
	 * @return Boolean isDataDentroDoIntervalo
	 * @throws BusinessException BusinessException
	 */
	public static Boolean isDataDentroDoIntervalo(Date dateTime, String intervalo) throws BusinessException {
		Pattern pattern = Pattern.compile("^[0-9]{2}\\W[0-9]{2}(\\s{0,1})\\W(\\s{0,1})[0-9]{2}\\W[0-9]{2}$");

		if ("N.D.".equals(intervalo)) {
			return Boolean.FALSE;
		}

		if (StringUtils.isBlank(intervalo)) {
			throw new BusinessException("Intervalo nao informado para verificar dentro do horario");
		}
		Matcher matcher = pattern.matcher(StringUtils.trim(intervalo));
		if (!matcher.matches()) {
			throw new BusinessException("Intervalo de horario apresentado nao esta no formato esperado (HH:mm - HH:mm).");
		}
		String[] hours = StringUtils.split(StringUtils.trim(intervalo), "-");
		Calendar calIni = juntarHoraMinutoComData(dateTime, getHourOrMinuteFromString(TimeParts.HOUR, StringUtils.trim(hours[0])), getHourOrMinuteFromString(TimeParts.MINUTE, StringUtils
				.trim(hours[0])), 0);
		Calendar calFim = juntarHoraMinutoComData(dateTime, getHourOrMinuteFromString(TimeParts.HOUR, StringUtils.trim(hours[1])), getHourOrMinuteFromString(TimeParts.MINUTE, StringUtils
				.trim(hours[1])), 999);
		return ((calIni.getTime().equals(dateTime) || calIni.getTime().before(dateTime)) && (calFim.getTime().equals(dateTime) || calFim.getTime().after(dateTime)));
	}

	/**
	 * Realiza o parser de um horario (String, no formato HH:mm) extraindo a
	 * parte do horario passada por parametro.
	 * 
	 * @param part
	 *            (parte do horario que se deseja extrair)
	 * @param hourMinute
	 *            (String com a representacao de horario)
	 * @return Integer com a representacao da parte do horario.
	 */
	public static Integer getHourOrMinuteFromString(TimeParts part, String hourMinute) {
		String[] parts = StringUtils.split(hourMinute, ":");
		if (part.equals(TimeParts.HOUR)) {
			return Integer.valueOf(parts[0]);
		} else {
			return Integer.valueOf(parts[1]);
		}
	}

	/**
	 * Verifica se um horario (String) esta no formato desejado. Retorna true se
	 * o formato esta correto. Retorna false do contrario.
	 * 
	 * @param format format
	 * @param timeToEvaluate timeToEvaluate
	 * @return Boolean (true em caso de formato correto).
	 */
	public static Boolean isTimeWellFormed(TimeFormats format, String timeToEvaluate) {
		if (StringUtils.isEmpty(timeToEvaluate)) {
			return false;
		}
		Pattern pattern = Pattern.compile(format.getPattern());
		Matcher matcher = pattern.matcher(StringUtils.trim(timeToEvaluate));
		return (matcher.matches());
	}

	/**
	 * Seta o horario (horas, minutos e segundos) de um objeto Date, conforme
	 * parametros passados.
	 * 
	 * @param date
	 *            (Data a ser manipulada)
	 * @param hour
	 *            (Horas a serem setadas no objeto Date)
	 * @param minute
	 *            (Minutos a serem setados no objeto Date)
	 * @param second
	 *            (Segundos a ser setados no objeto Date)
	 * @return Calendar (com Date modificado)
	 */
	public static Calendar juntarHoraMinutoComData(Date date, Integer hour, Integer minute, Integer second) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		calendar.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return calendar;
	}

	/**
	 * Pattern: dd/MM/yy
	 * @param date date
	 * @return String dateToString
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		return sdf.format(date);
	}

	/**
	 * Pattern: dd/MM/yy HH:mm
	 * @param date date
	 * @return String dateToStringWithHour
	 */
	public static String dateToStringWithHour(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
		return sdf.format(date);
	}

	/**
	 * Pattern: HH:mm
	 * @param date date
	 * @return String dateToStringOnlyHour
	 */
	public static String dateToStringOnlyHour(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date);
	}

	public static String dateTimeToString(Date date, String	pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * Pattern: dd/MM/yy HH:mm:
	 * @param date date
	 * @return String dateTimeToString
	 */
	public static String dateTimeToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
		return sdf.format(date);
	}

	/**
	 * Pattern: HH:mm
	 * @param date date
	 * @return String timeToString
	 */
	public static String timeToString(Date date) {
		return dfTime.format(date);
	}

	/**
	 * Pattern: dd/MM/yy HH:mm:ss
	 * @param date date
	 * @return String dateTimeFullToString
	 */
	public static String dateTimeFullToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		return sdf.format(date);
	}
	
	public static Date inicioDaDataInformada(Date date) {
		Calendar cal = juntarHoraMinutoComData(date, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 1);
		return cal.getTime();
	}

	public static Date finalDaDataInformada(Date date) {
		Calendar cal = juntarHoraMinutoComData(date, 23, 59, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Calendar inicioDaDataInformadaToCalendar(Date date) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Calendar finalDaDataInformadaToCalendar(Date date) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 99);
		return cal;
	}

	/**
	 * Monta uma lista de datas (List&lt;Date&gt;) em ordem crescente. O primeiro item
	 * da lista é o parametro dataInicial e o ultimo item da lista é o parametro
	 * dataFinal.
	 * 
	 * @param dataInicial dataInicial
	 * @param dataFinal dataFinal
	 * @return List Date conjuntoDeDatas
	 */
	public static List<Date> conjuntoDeDatas(Date dataInicial, Date dataFinal) {
		List<Date> dates = new ArrayList<Date>();
		if (dataInicial.before(dataFinal)) {
			Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
			dataInicial = inicioDaDataInformada(dataInicial);
			dataFinal = inicioDaDataInformada(dataFinal);
			c.setTime(dataInicial);
			while (c.getTime().compareTo(dataFinal) <= 0) {
				dates.add(c.getTime());
				c.add(Calendar.DAY_OF_MONTH, 1);
			}
		} else {
			dates.add(dataInicial);
		}
		return dates;
	}

	/**
	 * Retorna um objeto Date com base no timestamp passado por parametro.
	 * 
	 * @param timeInMillis timeInMillis
	 * @return Date getDateFromTimeInMillis
	 */
	public static Date getDateFromTimeInMillis(long timeInMillis) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		cal.setTimeInMillis(timeInMillis);
		return cal.getTime();
	}

	/**
	 * Valida uma matricula padrao Caixa utilizando regexp. A matricula
	 * 
	 * @param matricula matricula
	 * @return boolean validarMatricula
	 */
	public static boolean validarMatricula(String matricula) {
		if (StringUtils.isBlank(matricula)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^([a-zA-Z]{1}[0-9]{6})$");
		Matcher matcher = pattern.matcher(matricula);
		return matcher.matches();
	}

	/**
	 * Valida um email utilizando regexp. Retorna true caso o email seja valido.
	 * Do contrario retorna false.
	 * 
	 * @param email email
	 * @return boolean validateEmail
	 */
	public static boolean validateEmail(final String email) {
		if (StringUtils.isBlank(email)) {
			return false;
		}
		Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9_\\-.]*[a-zA-Z0-9]@[a-zA-Z0-9][a-zA-Z0-9.]*\\.[a-zA-Z0-9.]+$");
		Matcher mailMatcher = mailPattern.matcher(email);
		return mailMatcher.matches();
	}

	/**
	 * Valida um dado com base em um padrao. Utiliza o regexp contido no padrao
	 * para testar se o dado informado esta valido. Retorna true se o dado
	 * contempla o padrao informado. Do contrario retorna false.
	 * 
	 * @param pattern pattern
	 * @param dado dado
	 * @return boolean validarDadoComPattern
	 */
	public static boolean validarDadoComPattern(final String pattern, final String dado) {
		if (StringUtils.isBlank(dado) || StringUtils.isBlank(pattern)) {
			return false;
		}
		Pattern padrao = Pattern.compile(pattern);
		Matcher teste = padrao.matcher(dado);
		return teste.matches();
	}
	
	/**
	 * Compara dois valores
	 * @param valor1 valor1
	 * @param valor2 valor2
	 * @return boolean iguais
	 */
	public static boolean iguais(Object valor1, Object valor2) {
		
		if(valor1 == null && valor2 == null) {
			return true;
		}else {
			if(valor1 != null) {
				if(valor1 instanceof String) {
					String str1 = (String) valor1;
					String str2 = (String) valor2;
					if(str2 != null) {
						return str1.trim().equals(str2.trim());
					}else {
						return str1.equals(str2);
					}
				}
				return valor1.equals(valor2);
			}
		}
		return false;
	}

	/**
	 * @param date date
	 * @param i i
	 * @return Date addDia
	 */
	public static Date addDia(Date date, int i) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.DAY_OF_MONTH, i);
		return instance.getTime();
	}

	/**
	 * Adiciona Meses a uma data.
	 * @param data data
	 * @param addmeses addmeses
	 * @return Date adicionarMes
	 */
	public static Date adicionarMes(Date data, int addmeses) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.MONTH, addmeses);
		return cal.getTime();
	}

	/**
	 * Adiciona Dias a uma data.
	 * @param data data
	 * @param addDias adidionar
	 * @return Date adicionarDia
	 */
	public static Date adicionarDia(Date data, int addDias) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_MONTH, addDias);
		return cal.getTime();
	}

	/**
	 * Adiciona Horas a uma data.
	 * @param data data
	 * @param addHoras adidionar
	 * @return Date adicionarHoras
	 */
	public static Date adicionarHoras(Date data, int addHoras) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.HOUR_OF_DAY, addHoras);
		return cal.getTime();
	}

	/**
	 * Adiciona Minutos a uma data.
	 * @param data data
	 * @param addMinutos adidionar
	 * @return Date adicionarMinutos
	 */
	public static Date adicionarMinutos(Date data, int addMinutos) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.MINUTE, addMinutos);
		return cal.getTime();
	}
	
//	public static StopWatch logTempo(LogLevel loglevel, StopWatch sw, Logger log, String prefix, Object...args) {
//		if(sw == null) {
//			sw = new StopWatch();
//			sw.start();
//		}
//		sw.stop();
//		
//		switch (loglevel) {
//		case DEBUG:
//			log.debug(prefix+" [T: "+tempoDecorrido(sw.getTime())+"]", args);
//			break;
//		case INFO:
//			log.info(prefix+" [T: "+tempoDecorrido(sw.getTime())+"]", args);
//			break;
//		case WARN:
//			log.warn(prefix+" [T: "+tempoDecorrido(sw.getTime())+"]", args);
//			break;
//		case ERROR:
//			log.error(prefix+" [T: "+tempoDecorrido(sw.getTime())+"]", args);
//			break;
//		}
//		sw = new StopWatch();
//		sw.start();
//		return sw;
//	}
//
	public static void validar(boolean teste, String mensagem) throws BusinessException {
		if(!teste)
			throw new BusinessException(mensagem);
	}

	public static Date parseTime(String value) throws BusinessException {
		if(StringUtils.isBlank(value)) {
			throw new BusinessException("Um valor é obrigatório para retornar um horário.");
		}
		Date parse = null;
		try {
			parse = dfTime.parse(value);
		} catch (ParseException e) {
			throw new BusinessException("Horário "+ value + " inválido.");
		}
		return parse;
	}

	public static Date parseDate(String value) throws ParseException {
		return dfDate.parse(value);
	}

	public static Date parseDatetime(String value) throws ParseException {
		return dfDateTime.parse(value);
	}

	public static String[] separarHorarios(String horarios) {
		
		if(horarios == null)
			return new String[] {"", ""};
		
		String[] split = null;
		
		if(horarios.contains("-")) {
			split = horarios.split("\\-");
		} else {
			split = horarios.split("\\s");
		}
		
		if (split.length == 2) {
			String trim = split[0].trim();
			String trim2 = split[1].trim();
			return new String[] {trim, trim2};
		}
		
		return null;
	}
	
}
