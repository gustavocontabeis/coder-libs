package br.com.codersistemas.libs.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.persistence.Column;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
public class MockUtils {

	public static Object create(Object app) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
		Class<? extends Object> class1 = app.getClass();
		//log.inf
		Object newInstance = class1.newInstance();
		Field[] fields = ReflectionUtils.getFields(class1);
		for (Field field : fields) {
			Annotation annotation = ReflectionUtils.getAnnotation(class1, field, Column.class);
			
			ReflectionUtils.setValue(newInstance, field.getName(), getValor(null));
		}
		
		return newInstance;
	}

	private static Object getValor(Class<?> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
