package br.com.codersistemas.libs.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.codersistemas.libs.utils.mock.Bloco;
import br.com.codersistemas.libs.utils.mock.Condominio;

public class LombokToStringUtilTest {

	@Test
	public void testPrint() {
		String print = new LombokToStringUtil(Condominio.class).print();
		System.out.println(print);
	}

}
