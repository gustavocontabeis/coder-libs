package br.com.codersistemas.libs.strategy.literalvalues;

public class LongStrategy extends Strategy {

	@Override
	public String getValue(Object valor) {
		return valor.toString() + "L";
	}

}
