package br.com.codersistemas.libs.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import br.com.codersistemas.libs.utils.mock.Genero;
import br.com.codersistemas.libs.utils.mock.Pessoa;

public class MockUtilsTest {

	@Test
	public void testCreate() throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
		Pessoa pessoa = new Pessoa();
		pessoa.setAltura(123.56F);
		pessoa.setAtivo(true);
		pessoa.setFilhos(new ArrayList<Pessoa>());
		pessoa.setGenero(Genero.FEMININO);
		pessoa.setId(123L);
		pessoa.setMae(null);
		pessoa.setNome("Romilda da Silva");
		pessoa.setSalario(15000.0);
		
		Pessoa pessoa2 = new Pessoa();
		pessoa2.setAltura(123.56F);
		pessoa2.setAtivo(true);
		pessoa2.setFilhos(new ArrayList<Pessoa>());
		pessoa2.setGenero(Genero.MASCULINO);
		pessoa2.setId(456L);
		pessoa2.setMae(pessoa);
		pessoa2.setNome("Gustavo da Silva");
		pessoa2.setSalario(15000.0);

		MockUtils.create(pessoa2);
		
	}

}
