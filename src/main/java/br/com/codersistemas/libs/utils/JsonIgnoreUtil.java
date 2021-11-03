package br.com.codersistemas.libs.utils;

import java.lang.reflect.Field;
import java.util.List;

public class JsonIgnoreUtil {

	private Class<?> classe;
	private Field field;

	public JsonIgnoreUtil(Class<?> classe, Field field) {
		this.classe = classe;
		this.field = field;
	}

	public String print() {
		if(field.getType() == List.class) {
			return "@JsonIgnore\n";
		}
		return "";
	}

}
