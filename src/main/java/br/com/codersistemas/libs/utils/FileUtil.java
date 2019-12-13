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
}
