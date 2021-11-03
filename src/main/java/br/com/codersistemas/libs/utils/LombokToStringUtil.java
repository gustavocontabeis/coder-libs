package br.com.codersistemas.libs.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class LombokToStringUtil {

	private static final String DIV = ", ";
	private Class<?> classe;
	private List<String> list;

	public LombokToStringUtil(Class<?> classe) {
		this.classe = classe;
	}

	public String print() {
		getBidirecionalFIelds();
		return printValue();
	}

	private String printValue() {
		StringBuilder sb = new StringBuilder();
		if(!list.isEmpty()) {
			sb = new StringBuilder("@ToString({");
			StringBuilder sbItens = new StringBuilder(); 
			for (String f : list) {
				sbItens.append("\"" + f + "\"" + DIV);
			}
			sb.append(StringUtil.removeEnd(sbItens.toString(), DIV));
			sb.append("})\n");
		}
		return sb.toString();
	}

	private void getBidirecionalFIelds() {
		list = new ArrayList<>();
		Field[] fields = ReflectionUtils.getFields(classe);
		//System.out.println(classe);
		for (Field field : fields) {
			//System.out.println(field);
			Class c = ReflectionUtils.getTypeNormalOrGeneric(field);
			if(c.getName().startsWith("java")) {
				continue;
			}
			boolean containsCircularReference = ReflectionUtils.containsCircularReference(classe, field);
			//System.out.println(containsCircularReference);
			if(containsCircularReference) {
				list.add(field.getName());
			}
//			Type genericType = ReflectionUtils.getGenericType(field);
//			if(genericType != null) {
//				list.add(field.getName());
//			} else if(!field.getType().getName().startsWith("java")) {
//				list.add(field.getName());
//			}
		}
	}

	private void containsCircularReference(Class<?> classe2, Field field) {
		// TODO Auto-generated method stub
		
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
