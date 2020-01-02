package br.com.codersistemas.libs.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public class StringUtil extends StringUtils {

	public static Boolean convertToBoolean(String value) {
		if (value == null) {
			return null;
		}
		value = value.trim();
		if (value.equalsIgnoreCase("S") || value.equalsIgnoreCase("V") || value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("T") || value.equalsIgnoreCase("1")) {
			return true;
		} else if (value.equalsIgnoreCase("N") || value.equalsIgnoreCase("F") || value.equalsIgnoreCase("0")) {
			return false;
		} else {
			return null;
		}
	}

	public static String utf8(String valor) throws UnsupportedEncodingException {
		return new String(valor.getBytes(Charset.forName("ISO-8859-1")),"UTF-8");
	}
	
	public static String label(String valor)  {
		String underlineCase = StringUtil.toUnderlineCase(valor);
		String replace = underlineCase.replace("_", " ");
		String capitalize = capitalize(replace);
		String normalizePT = capitalize
				.replace(" Da ", " da ")
				.replace(" De ", " de ")
				.replace(" Do ", " do ")
				.replace("cao", "ção")
				.replace("coes", "ções");
		return normalizePT;
	}

	public static String toCamelCase(String string) {
		if(string.contains("_"))
			string = WordUtils.capitalizeFully(string, new char[]{'_'}).replaceAll("_", "");
		if(string.contains(" "))
			string = WordUtils.capitalizeFully(string, new char[]{' '}).replaceAll(" ", "");
		return WordUtils.uncapitalize(string);
	}

	public static String toUnderlineCase(String string) {
		return string.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
	}

	public static String caplitalizePlural(String name) {
		name = capitalize(name);
		name = plural(name);
		return name;
	}

	private static String plural(String name) {
		
		if(name.endsWith("cao")) {
			name = StringUtils.replace(name, "cao", "coes");
		} else {
			name = name + "s";
		}
		
		return name;
	}

	public static String uncaplitalizePlural(String name) {
		name = uncapitalize(name);
		name = plural(name);
		return name;
	}

	public static String caplitalizeSingular(String name) {
		name = capitalize(name);
		return name;
	}

	public static String uncaplitalizeSingular(String name) {
		name = capitalize(name);
		return name;
	}

}
