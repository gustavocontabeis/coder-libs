package br.com.codersistemas.libs.strategy.literalvalues;

public class ArrayListStrategy extends Strategy {

	@Override
	public String getValue(Object valor) {
		return "new ArrayList()";
	}

}
