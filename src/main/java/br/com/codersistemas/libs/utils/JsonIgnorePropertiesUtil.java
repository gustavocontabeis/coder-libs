package br.com.codersistemas.libs.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class JsonIgnorePropertiesUtil {

	private Class<?> classe;
	private Field field;

	public JsonIgnorePropertiesUtil(Class<?> classe, Field field) {
		this.classe = classe;
		this.field = field;
	}

	public String print() {
		List<String> list = new ArrayList<>();
		
		Field[] fields = ReflectionUtils.getFields(classe);
		for (Field field : fields) {
			if(field.getType() == List.class) {
				continue;
			}
			Class c = ReflectionUtils.getTypeNormalOrGeneric(field);
			boolean containsCircularReference = ReflectionUtils.containsCircularReference(classe, field);
			if(containsCircularReference) {
				list.add(field.getName());
			}
			
//			Annotation annotation = ReflectionUtils.getAnnotation(classe, field, OneToMany.class);
//			if(annotation != null) {
//				list.add(field.getName());
//			}
		}
		
		if(!list.isEmpty()) {
			StringBuilder sb = new StringBuilder("@JsonIgnoreProperties({");
			for (String f : list) {
				sb.append("\"" + f + "\", ");
			}
			String x = StringUtil.removeEnd(sb.toString(), ", ");
			
			return x + "})\n";
		}
		return "";
	}

	private boolean isFk(Field field) {
		
		Class<?> type = field.getType();
		
		if( type.getName().equals(List.class.getName()) 
			|| type.getName().equals(Set.class.getName()) 
			|| type.getName().equals(Map.class.getName())) {
			type = (Class<?>) ReflectionUtils.getGenericType2(field);
		}
		
		if( !type.isEnum() 
				&& !type.getName().startsWith("java.")) {
			return true;
		}
		else {
			return false;
		}
	}

}
