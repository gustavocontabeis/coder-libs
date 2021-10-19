package br.com.codersistemas.libs.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MapUtil {

	public static <E> List<E> getContaining(String key, Map<String, E> map) {
		List<E> ret = new ArrayList<>();
		for (String k : map.keySet()) {
			if (k.contains(key)) {
				ret.add(map.get(k));
			}
		}
		return ret;
	}

	public static List<String> getContaining(String key, List<String> list) {
		List<String> ret = new ArrayList<>();
		for (String s : list) {
			if(s == null)
				System.out.println("DEBUG");
			if (s.contains(key)) {
				ret.add(s);
			}
		}
		return ret;
	}
}
