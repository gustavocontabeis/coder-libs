package br.com.codersistemas.libs.strategy.literalvalues;

public class EnumStrategy extends Strategy {

	@Override
	public String getValue(Object valor) {
		return valor.getClass().getSimpleName() + "." + valor.toString();
	}

}
