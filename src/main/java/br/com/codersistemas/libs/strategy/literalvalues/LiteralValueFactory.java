package br.com.codersistemas.libs.strategy.literalvalues;

public class LiteralValueFactory {

	public static String create(Object valor) {
		if(valor == null) {
			return "null";
		}
		
		String type = valor.getClass().getName();
//		if(valor.getClass().isPrimitive()) {
//			type = c.getClass().getName();
//		}
		if(valor.getClass().isEnum()) {
			type = "java.lang.Enum";
		}
		LiteralValueTypes literalValueTypes  = LiteralValueTypes.from(type);
		Strategy strategy = literalValueTypes.getStrategy();
		return strategy.getValue(valor);
	}

}
