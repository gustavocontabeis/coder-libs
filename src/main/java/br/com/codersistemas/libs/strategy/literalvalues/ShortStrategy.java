package br.com.codersistemas.libs.strategy.literalvalues;

public class ShortStrategy extends Strategy {

	@Override
	public String getValue(Object valor) {
		return "(short) " + valor.toString();
	}

}
