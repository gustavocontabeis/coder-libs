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
		return "";
	}

	public static String toCamelCase(String string) {
		if(string.contains("_"))
			string = WordUtils.capitalizeFully(string, new char[]{'_'}).replaceAll("_", "");
		if(string.contains(" "))
			string = WordUtils.capitalizeFully(string, new char[]{' '}).replaceAll(" ", "");
		return WordUtils.uncapitalize(string);
	}

	public static String toUnderlineCase(String string) {
		Matcher matcher = Pattern.compile("[A-Z]").matcher(string);
		while(matcher.find()) {
			System.out.println(matcher.group());
		}
		
		
//		String[] split = string.split("[A-Z]");
//		String str2 = "";
//		for (String string2 : split) {
//			System.out.println(string2);
//			str2 += "_"+string2; 
//		}
		//return StringUtil.removeFirst(str2, "_");
		return "";
	}
}
