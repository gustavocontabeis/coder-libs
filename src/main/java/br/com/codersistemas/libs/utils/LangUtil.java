package br.com.codersistemas.libs.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class LangUtil {
	
	private LangUtil() {
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getList(T ... args) {
		return Arrays.asList(args);
	}

	public static BigDecimal toBigDecimal(String value) {
		return StringUtils.isNotBlank(value) ? new BigDecimal(value) : null;
	}
}
