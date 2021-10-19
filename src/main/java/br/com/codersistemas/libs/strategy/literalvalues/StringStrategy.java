package br.com.codersistemas.libs.strategy.literalvalues;

public class StringStrategy extends Strategy {

	@Override
	public String getValue(Object valor) {
		return "\"" + valor + "\"";
	}

}
