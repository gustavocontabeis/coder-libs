package br.com.codersistemas.libs.strategy.literalvalues;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStrategy extends Strategy {

	@Override
	public String getValue(Object valor) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format((Date)valor);
	}

}
