package br.com.codersistemas.libs.strategy.literalvalues;

public class DoubleStrategy extends Strategy {

	@Override
	public String getValue(Object valor) {
		return valor.toString();
	}

}
