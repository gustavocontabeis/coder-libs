package br.com.codersistemas.libs.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	public static final Properties loadProperties(File file) {
		Properties properties = new Properties();
		try {
			InputStream is = new FileInputStream(file);
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
	
	public static String readResource(Object thisObject, String resourceName) throws IOException {
		InputStream inputStream = thisObject.getClass().getResourceAsStream(resourceName);
		return readFromInputStream(inputStream);
	}
	
	private static String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

	public static void write(File file, String print) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(print);
		writer.close();
	}

	public static List<String> readToList(String fileName) throws IOException {
		List<String> result;
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			result = lines.collect(Collectors.toList());
		}
		return result;
	}

	public static List<String> readToList(File file) throws IOException {
		return readToList(file.getName());
	}

	public static String read(String fileName) throws IOException {
		return new String(Files.readAllBytes(Paths.get(fileName)));
	}
	
	public static String read(File file) throws IOException {
		return read(file.getName());
	}

}
