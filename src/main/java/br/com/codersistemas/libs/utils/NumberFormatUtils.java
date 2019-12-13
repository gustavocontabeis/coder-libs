/**
 * 
 */
package br.com.codersistemas.libs.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatUtils {
	
	private static NumberFormat nf = NumberFormat.getInstance(new Locale("pt","BR"));
	
	static {
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);
		nf.setRoundingMode(RoundingMode.HALF_DOWN);
	}
	
	public static String formatoBR(Float valor) {
		return nf.format(valor);
	}

}
