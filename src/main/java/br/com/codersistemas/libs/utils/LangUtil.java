package br.com.codersistemas.libs.utils;

import java.util.Arrays;
import java.util.List;

public class LangUtil {
	
	private LangUtil() {
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getList(T ... args) {
		return Arrays.asList(args);
	}
}
