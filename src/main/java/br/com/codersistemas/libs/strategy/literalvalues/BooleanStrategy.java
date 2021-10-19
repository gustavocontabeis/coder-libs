package br.com.codersistemas.libs.strategy.literalvalues;

public class BooleanStrategy extends Strategy {

	@Override
	public String getValue(Object valor) {
		return valor.toString();
	}

}
