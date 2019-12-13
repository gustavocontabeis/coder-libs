package br.com.codersistemas.libs.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;

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
}
