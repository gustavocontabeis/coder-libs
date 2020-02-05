package br.com.codersistemas.libs.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class FileUtil {

	public static final Properties loadProperties(String adress) {
		Properties properties = new Properties();
		try {
			InputStream is = FileUtil.class.getResourceAsStream(adress);
			Reader reader = new InputStreamReader(is);
			properties.load(reader);
		} catch (IOException e) {
			throw new RuntimeException("Falha ao carregar configurações." + e);
		}
		return properties;
	}
	
	public static String humanReadableByteCountSize(long bytes) {
	    String s = bytes < 0 ? "-" : "";
	    long b = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
	    return b < 1000L ? bytes + " B"
	            : b < 999_950L ? String.format("%s%.1f kB", s, b / 1e3)
	            : (b /= 1000) < 999_950L ? String.format("%s%.1f MB", s, b / 1e3)
	            : (b /= 1000) < 999_950L ? String.format("%s%.1f GB", s, b / 1e3)
	            : (b /= 1000) < 999_950L ? String.format("%s%.1f TB", s, b / 1e3)
	            : (b /= 1000) < 999_950L ? String.format("%s%.1f PB", s, b / 1e3)
	            : String.format("%s%.1f EB", s, b / 1e6);
	}
}
