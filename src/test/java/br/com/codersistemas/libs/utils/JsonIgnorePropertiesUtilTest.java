package br.com.codersistemas.libs.utils;

import java.lang.reflect.Field;

import org.junit.Test;

import br.com.codersistemas.libs.utils.mock.Bloco;
import br.com.codersistemas.libs.utils.mock.Condominio;

public class JsonIgnorePropertiesUtilTest {

	@Test
	public void testPrint() {
		String print = null;
		Field[] fields = ReflectionUtils.getFields(Condominio.class);
		for (Field field : fields) {
			print = new JsonIgnorePropertiesUtil(Condominio.class, field).print();
		}
	}

}
