package br.com.codersistemas.libs.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testConvertToBoolean() {
		assertTrue(StringUtil.convertToBoolean("S"));
		assertTrue(StringUtil.convertToBoolean("V"));
		assertTrue(StringUtil.convertToBoolean("Y"));
		assertFalse(StringUtil.convertToBoolean("N"));
		assertFalse(StringUtil.convertToBoolean("F"));
	}

	@Test
	public void testCapitalize() {
		assertTrue("Gustavo".equals(StringUtil.capitalize("gustavo")));
		assertTrue("Gustavo da Silva".equals(StringUtil.capitalize("gustavo da Silva")));
	}

	@Test
	public void testUncapitalize() {
		assertTrue("gustavo".equals(StringUtil.uncapitalize("Gustavo")));
		assertTrue("gustavo da Silva".equals(StringUtil.uncapitalize("Gustavo da Silva")));
	}

	@Test
	public void testToCamelCase() {
		assertTrue("gustavoDaSilva".equals(StringUtil.toCamelCase("gustavo da silva")));
		assertTrue("gustavoDaSilva".equals(StringUtil.toCamelCase("gustavo_da_silva")));
	}

	@Test
	public void testToUnderlineCase() { 
		assertTrue("gustavo_Da_Silva".equals(StringUtil.toUnderlineCase("gustavoDaSilva")));
		assertTrue("Gustavo_Da_Silva".equals(StringUtil.toUnderlineCase("GustavoDaSilva")));
	}

	@Test
	public void testLabel() {
		assertTrue("Nome".equals(StringUtil.label("nome")));
		assertTrue("Gustavo da silva".equals(StringUtil.label("gustavo_da_silva")));
		assertTrue("Gustavo da Silva".equals(StringUtil.label("gustavoDaSilva")));
		assertTrue("Gustavo da Silva".equals(StringUtil.label("GustavoDaSilva")));
		assertTrue("Data de Nascimento".equals(StringUtil.label("dataDeNascimento")));
		assertTrue("Descrição".equals(StringUtil.label("descricao")));
		assertTrue("Descrições".equals(StringUtil.label("descricoes")));
	}

}
