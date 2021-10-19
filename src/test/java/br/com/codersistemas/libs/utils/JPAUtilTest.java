package br.com.codersistemas.libs.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.codersistemas.libs.utils.mock.Funcionario;
import lombok.extern.log4j.Log4j;


public class JPAUtilTest {

	@Test
	public void testGerarColunaId() {
		//fail("Not yet implemented");
	}

	@Test
	public void testNomeTabela() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIsId() {
		//fail("Not yet implemented");
	}

	@Test
	public void testNomeColuna() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGerarClasse() {
		String gerarClasse = JPAUtil.gerarClasse(Funcionario.class);
		System.out.println(gerarClasse);
	}

	@Test
	public void testGerarAtributos() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIsEntity() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetId() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetDto() {
		//fail("Not yet implemented");
	}

}
