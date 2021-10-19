package br.com.codersistemas.libs.strategy.literalvalues;

public class IntegerStrategy extends Strategy {

	@Override
	public String getValue(Object vlr) {
		return String.valueOf(vlr);
	}

}
